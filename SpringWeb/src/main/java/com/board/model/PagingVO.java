package com.board.model;

import javax.servlet.http.HttpSession;

import lombok.Data;


/* 페이징 처리 및 검색 기능을 모듈화하여 재사용할 수 있도록 하자.
 * 
 */
@Data
public class PagingVO {
   // 페이징 처리 관련 프로퍼티
   private int cpage; // 현재 보여줄 페이지 번호
   private int pageSize = 5;// 한 페이지 당 보여줄 목록 개수
   private int totalCount; // 총 게시글 수
   private int pageCount;// 페이지 수

   // DB에서 레코드를 끊어오기 위한 프로퍼티
   private int start;
   private int end;

   // 페이징 블럭 처리를 위한 프로퍼티
   private int pagingBlock = 5; // 한 블럭당 보여줄 페이지 수
   private int prevBlock; // 이전 5개 (이전 블록)
   private int nextBlock;// 이후 5개 (이후 블록)

   // 검색 관련 프로퍼티
   private String findType; // 검색 유형
   private String findKeyword; // 검색어

   // 페이징 처리 연산을 수행하는 메서드
   public void init(HttpSession session) {
      if(session!=null) {
         session.setAttribute("pageSize", pageSize);
      }
      
      // 페이지 수 구하기
      pageCount = (totalCount - 1) / pageSize + 1;

      if (cpage < 1)
         cpage = 1; // 1페이지를 디폴트로 지정
      if (cpage > pageCount)
         cpage = pageCount; // 마지막 페이지로 설정

      // where rn> A and rn <B;
      /*
       * 0 6 5 11 10 16
       */

      start = (cpage - 1) * pageSize;
      end = (cpage * pageSize) + 1;

      // 페이징 블럭 연산
      /*
       * cpage [1][2][3][4][5] | [6][7][8][9][10] | [11][12][13][14][15] |
       * [16][17][18][19][20]
       * -----------------------------------------------------------------------------
       * cpage pagingBlock preBlock(이전 5개) nextBlock(이후 5개) 
       *  1-5       5          0                 6 
       *  6-10               5                11
       *  11-15                10             16
       * -----------------------------------------------------------------------------
       * 
       * 
       * preBlock = (cpage -1 /pagingBlock)*pagingBlock nextBlock =
       * nextBlock=prevBlock+(pagingBlock+1)
       */
      prevBlock = (cpage-1)/pagingBlock*pagingBlock;
      nextBlock=prevBlock+(pagingBlock+1);
   }// init() end ----------------------------------------------------
   
   
   // myctx : 컨텍스트명 /myapp
   // loc : list
    public String getPageNavi(String myctx, String loc) {
    	if(findType==null) {
    		findType="";
    	}
    	if(findKeyword==null) {
    		findKeyword="";
    	}
    	
    	
    	
         String link=myctx+"/"+loc; //"/myapp/board/list"
         String qStr="?pageSize"+pageSize+"&findType="+findType+"&findKeyword="+findKeyword;
         link+=qStr;
         StringBuilder buf=new StringBuilder();
         buf.append("<ul class=\"pagination justify-content-center\" >");
         if(prevBlock > 0) {
            buf.append("<li class=\"page-item\">")
               .append("<a class='page-link' href='"+link+"&cpage="+prevBlock+"'>")
               .append("Prev")
               .append("</a>")
               .append("</li>");
         }
         for(int i=prevBlock+1;i<nextBlock-1 && i<=pageCount;i++) {
            String css=(i==cpage)?"active":"";
            buf.append("<li class='page-item"+css+"'>");
            buf.append("<a class='page-link' href='"+link+"&cpage="+i+"'>");
            buf.append(i);
            buf.append("</a>");
            buf.append("</li>");
         }//for
         if(nextBlock > 0) {
            buf.append("<li class=\"page-item\">")
               .append("<a class='page-link' href='"+link+"&cpage="+nextBlock+"'>")
               .append("Next")
               .append("</a>")
               .append("</li>");
         }
         
         buf.append("</ul>");

      return buf.toString();
   }
   
   
   

} // ----------------------------------------------------