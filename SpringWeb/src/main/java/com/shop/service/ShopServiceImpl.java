package com.shop.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.shop.mapper.ProductMapper;
import com.shop.model.ProductVO;

@Service(value = "shopServiceImpl")
public class ShopServiceImpl implements ShopService {
	
	@Inject
	private ProductMapper productMapper;

	@Override
	public List<ProductVO> selectByPspec(String pspec) {
		
		return this.productMapper.selectByPspec(pspec);
	}

	@Override
	public List<ProductVO> selectByCategory(int cg_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductVO selectByPnum(int pnum) {
		// TODO Auto-generated method stub
		return null;
	}

}
