package com.shop.mapper;

import java.util.List;

import com.shop.model.CategoryVO;
import com.shop.model.ProductVO;

public interface ProductMapper {
	//상품등록 관련[admin]
	int productInsert(ProductVO item);
	List<ProductVO> getProducts();
	//상품진열 관련[고객]
	List<ProductVO> selectByPspec(String pspec);
	List<ProductVO> selectByPspec(int cg_code);
	ProductVO selectByPnum(int pnum);
	
	//상위 카테고리 목록 가져오기
	List<CategoryVO> getUpcategory();
	//상위 카테고리에 해당하는 하위 카테고리 목록 가져오기
	List<CategoryVO> getDowncategory(int upCg_code);
}
