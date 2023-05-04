package com.shop.mapper;

import java.util.List;

import com.shop.model.ProductVO;

public interface ProductMapper {
	
	int productInsert(ProductVO item);
	List<ProductVO> getProducts();
}
