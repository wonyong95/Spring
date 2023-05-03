package com.memo.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
//컨트롤러 계층: @Controller, 서비스 계층: @service
//영속성 계층(Persistence Layer): @Repository
//[주의] servlet-context.xml에 component-scan 대상으로 패키지 등록해야함


@Repository
public class MemoDAOMyBatis implements MemoDAO {
	
	private final String NS="com.memo.model.MemoMapper";
	//MemoMapper.xml에 등록된 네임스페이스와 동일해야 함
	
	//리소스 이름으로 주입한다. id가 sqlSessionTemplate인 객체를 찾아서 주입한다 
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate session;
	//datasource-context.xml에 등록되어 있음

	@Override
	public int insertMemo(MemoVO memo) {
		int n=session.insert(NS+".insertMemo", memo);
		System.out.println("방금 등록된 글의 글번호: "+memo.getNo());
		return n;
	}

	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return session.selectOne(NS+".getTotalCount");
	}

	@Override
	public List<MemoVO> listMemo(int start, int end) {
		Map<String, Integer> map=new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		
		List<MemoVO> arr=session.selectList(NS+".listMemo", map);
			
		
		return arr;
	}

	@Override
	public int deleteMemo(int no) {
		// delete()메서드 호출하기 ==>MemoMapper.xml에서 <delete>delete문 완성</delete>
		
		return session.delete(NS+".deleteMemo", no);
	}

	@Override
	public int updateMemo(MemoVO memo) {
		
		return session.update(NS+".updateMemo", memo);
	}

	@Override
	public MemoVO getMemo(int no) {
		MemoVO vo=session.selectOne(NS+".getMemo", no);
		
		return vo;
	}

}
