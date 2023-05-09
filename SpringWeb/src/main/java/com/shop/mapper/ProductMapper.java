package com.shop.mapper;

import java.util.List;

import com.shop.model.ProductVO;

public interface ProductMapper {
	//상품등록 관련[admin]
	int productInsert(ProductVO item);
	List<ProductVO> getProducts();
	//상품진열 관련[고객]
	List<ProductVO> selectByPspec(String pspec);
	List<ProductVO> selectByPspec(int cg_code);
	ProductVO selectByPnum(int pnum);
}
