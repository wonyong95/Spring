package com.shop.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.shop.mapper.ProductMapper;
import com.shop.model.CategoryVO;
import com.shop.model.ProductVO;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
	
	@Inject
	private ProductMapper prodMapper;

	@Override
	public List<CategoryVO> getUpcategory() {
		
		return prodMapper.getUpcategory();
	}

	@Override
	public List<CategoryVO> getDowncategory(int upCg_code) {

		return prodMapper.getDowncategory(upCg_code);
	}

	@Override
	public int categoryAdd(CategoryVO cvo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int categoryDelete(int cg_code) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int productInsert(ProductVO prod) {
		
		return this.prodMapper.productInsert(prod);
	}

	@Override
	public List<ProductVO> productList() {
		
		return this.prodMapper.getProducts();
	}

}