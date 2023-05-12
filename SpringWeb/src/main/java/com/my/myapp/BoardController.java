package com.my.myapp;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.board.model.BoardVO;
import com.board.service.BoardService;
import com.common.CommonUtil;

import lombok.extern.log4j.Log4j;


   @Controller
   @RequestMapping("/board")
   @Log4j
   public class BoardController {
      
      @Resource(name="boardService")
      private BoardService boardService;
      
      @Autowired
      private CommonUtil util;
      
      @GetMapping("/write")
      public String boardForm() {
         
         return "board/boardWrite";
         //WEB-INF/views/board/boardWrite.jsp
      }
    //------------------------------------------------------------------
      
      @PostMapping("/write")
      public String boardInsert(Model m, @ModelAttribute BoardVO board, @RequestParam("mfilename") MultipartFile mf,HttpSession session) {
         log.info("board="+board);
         //1. 파일 업로드 처리
         //[1]. 업로드 디렉토리 절대경로 얻기 (resources/board_upload)
         ServletContext app = session.getServletContext();
        String upDir= app.getRealPath("/resources/board_upload");
        File dir = new File(upDir);
        
        if(!dir.exists()) {
           dir.mkdirs(); // 업로드 디렉토리 생성
        }
         
         //[2]. 업로드한 파일명과 파일 크기 알아내기 => board에 setFilename(파일명),serFilesize(파일크기)
         if(!mf.isEmpty()) { // 첨부파일이 존재한다면
            
            String fname = mf.getOriginalFilename(); // 원본 파일명
            long fsize = mf.getSize(); // 파일 크기
           // String ftype = mf.getContentType();
//            session.setAttribute("filetype", ftype);
            
            UUID uid = UUID.randomUUID();
            String filename = uid.toString()+""+fname;
            log.info("fname ="+fname+", filename = "+filename+", uuid = "+uid);
            

            
            board.setOriginFilename(fname); // 원본 파일명
            board.setFilename(filename); // 물리적 파일명
            board.setFilesize(fsize);
         //[3] 업로드 처리
            try {
            mf.transferTo(new File(upDir, filename));
            log.info("upDir : "+upDir );
         } catch (IOException e) {
            log.error("파일 업로드 에러"+e);
         }
            
            //[4]mode가 edit이고 예전에 첨부했던 파일이 있다면 => 예전 첨부파일 삭제처리
            if(board.getMode().equals("edit") && board.getOld_filename()!=null) {
            	File df=new File(upDir, board.getOld_filename());
            	if(df.exists()) {
            		boolean b=df.delete();
            		log.info("old file delete: "+b);
            	}
            }
         
         }//if----
         

         //2. 유효성 체크 (제목,작성자,비번)=> write로 redirect 이동
         if(board.getSubject()==null||board.getUserid()==null||board.getPasswd()==null||
            board.getSubject().trim().isEmpty()||board.getUserid().trim().isEmpty()||board.getPasswd().trim().isEmpty()){
               
               return "redirect:write";
            }
         
         //3. boardService의 insertBoard()호출
         int n=0;
         String str="";
         if("write".equals(board.getMode())) {
           // 비밀번호 암호화 처리
            n=boardService.insertBoard(board);
            str="글쓰기";
         }else if("edit".equals(board.getMode())) {
            //수정처리
            str="글수정";
            n=boardService.updateBoard(board);
            
         }else if("rewrite".equals(board.getMode())) {
            //답변 글쓰기
            str="답변 글쓰기";
            n=boardService.rewriteBoard(board);
         }
         str+=(n>0)?"성공":"실패";
         String loc=(n>0)?"list":"javascript:history.back()";
         //4. 그 결과 메시지,이동경로처리
         
         return util.addMsgLoc(m, str, loc);
      }
    //------------------------------------------------------------------
      @GetMapping("/list")
      public String boardList(Model m, @RequestParam(defaultValue="1") int cpage) {
    	  log.info("cpage: "+cpage);
    	  if(cpage<0) {
    		  cpage=1;
    	  }
         //1. 총 게시글 수 가져오기
         int totalCount = boardService.getTotalCount();
         
         int pageSize=5;
         
         int pageCount=(totalCount-1)/pageSize+1;
         
         if(cpage>pageCount) {
        	 cpage=pageCount;//마지막페이지
         }
         
         int start=(cpage-1)*pageSize;
         int end=cpage*pageSize+1;
         Map<String,Integer> map=new HashMap<>();
         map.put("start", start);
         map.put("end", end);
         
         //2. 게시목록 가져오기
         List<BoardVO> boardArr = this.boardService.selectBoardAll(map);
         
         m.addAttribute("boardArr", boardArr);
         m.addAttribute("totalCount",totalCount);
         m.addAttribute("pageCount",pageCount);
         m.addAttribute("cpage",cpage);
         return "board/boardList";
         //3. board Model에 저장
         
      }
    //------------------------------------------------------------------
      // Path 접근 방식으로 데이터를 넘길 경우
      @GetMapping("/view/{num}")
      public String boardView(Model m, @PathVariable("num") int num) {
//         log.info("num = "+num);
         // 1. 조회수 증가
         this.boardService.updateReadnum(num);
         // 2. 글 번호로 해당글 가져오기
         BoardVO vo = this.boardService.selectBoardByIdx(num);
         
         m.addAttribute("board",vo);
          
         return "board/boardView";
      }
    //------------------------------------------------------------------
      
      @PostMapping("/delete")
      public String boardDelete(Model m, @RequestParam(defaultValue="0") int num,
            @RequestParam(defaultValue="") String passwd,HttpSession session) {
         log.info("num : "+num+", passwd : "+passwd);
         
         if(num == 0 || passwd.isEmpty()) {
            return "redirect:list";
         }
         // 해당글을 DB에서 가져오기
         BoardVO vo = this.boardService.selectBoardByIdx(num);
         String dbPasswd = vo.getPasswd();
         if(!dbPasswd.equals(passwd)) {
            return util.addMsgBack(m, "비밀번호가 일치하지 않아요");
         }
         // db 글 삭제 처리
         
         int n = boardService.deleteBoard(num);
         String upDir = session.getServletContext().getRealPath("/resources/board_upload");
         
         // 서버에 첨부한 파일이 존재하다면 서버에서 삭제 처리
         if(n>0 && vo.getFilename()!=null) {
            File f =new File(upDir,vo.getFilename());
            if(f.exists()) {
               boolean b = f.delete();
               log.info("파일 삭제 여부 : "+b);
            }
         }
         
         String str =(n>0)?"삭제 성공":"삭제 실패";
         String loc =(n>0)?"list":"javascript:history.back()";
         
         
         return util.addMsgLoc(m, str, loc);
      }
      
      @PostMapping("/edit")
      public String boardEditForm(Model m,@ModelAttribute BoardVO vo) {
         log.info("vo: "+vo);
         if(vo.getNum()==0||vo.getPasswd()==null) {
            return "redirect:list";
         }
         BoardVO dbVo=this.boardService.selectBoardByIdx(vo.getNum());
         if(dbVo==null) {
            return util.addMsgBack(m, "해당 글은 없어요");
         }
         if(!dbVo.getPasswd().equals(vo.getPasswd())) {
            return util.addMsgBack(m, "비밀번호가 일치하지 않아요");
         }
         m.addAttribute("board",dbVo);
         
         
         return "board/boardEdit";
      }
      
      @PostMapping("/rewrite")
      public String boardRewriteForm(Model m,@ModelAttribute BoardVO vo) {
    	  log.info("vo: "+vo);
    	  m.addAttribute("num", vo.getNum());//부모글의 글번호
    	  m.addAttribute("subject", vo.getSubject());//제목
    	  return "board/boardRewrite";
      }
      
      
   }
  