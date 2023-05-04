package com.user.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.mapper.UserMapper;
import com.user.model.NotUserException;
import com.user.model.PagingVO;
import com.user.model.UserVO;
//서비스계층에는 @Service
@Service("userService") //resource name
public class UserServiceImpl implements UserService {
	
	@Inject
	private BCryptPasswordEncoder passwordEncoder;
	
	@Inject
	private UserMapper userMapper;
	
	@Override
	public int createUser(UserVO user) {
		System.out.println(">>>"+userMapper);
		//비밀번호 암호화 처리
		user.setPwd(passwordEncoder.encode(user.getPwd()));
		return userMapper.createUser(user);
	}

	@Override
	public int getUserCount(PagingVO pvo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<UserVO> listUser(PagingVO pvo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean idCheck(String userid) {
		Integer idx=userMapper.idCheck(userid);
		System.out.println("idx: "+idx);
		if(idx==null) return true;
		return false;
	}

	@Override
	public int deleteUser(Integer midx) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateUser(UserVO user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserVO getUser(Integer midx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserVO findUser(UserVO findUser) throws NotUserException {
		UserVO user=userMapper.findUser(findUser);
		return user;
	}

	@Override
	public UserVO loginCheck(String userid, String pwd) throws NotUserException {
		UserVO tmpUser=new UserVO();
		tmpUser.setUserid(userid);
		tmpUser.setPwd(pwd);
		UserVO dbuser=this.findUser(tmpUser);
		if(dbuser==null) throw new NotUserException("존재하지 않는 아이디 입니다");
		//비밀번호 일치여부 체크 <=암호화된 비밀번호 일치여부 체크
		//if(!dbuser.getPwd().equals(pwd)) {
		boolean isMatch=passwordEncoder.matches(pwd, dbuser.getPwd());
		System.out.println("isMatch: "+isMatch);
		if(!isMatch) {
			throw new NotUserException("비밀번호가 일치하지 않아요");
		}

		
		return dbuser;
	}

}
