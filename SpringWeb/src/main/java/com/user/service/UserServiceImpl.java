package com.user.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.user.mapper.UserMapper;
import com.user.model.NotUserException;
import com.user.model.PagingVO;
import com.user.model.UserVO;
//서비스계층에는 @Service
@Service("userService") //resource name
public class UserServiceImpl implements UserService {
	
	@Inject
	private UserMapper userMapper;
	
	@Override
	public int createUser(UserVO user) {
		System.out.println(">>>"+userMapper);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserVO loginCheck(String userid, String pwd) throws NotUserException {
		// TODO Auto-generated method stub
		return null;
	}

}
