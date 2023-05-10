package com.shop.model;

import java.sql.Date;

import lombok.Data;

@Data
public class CartVO {
	
	private int cartNum;//장바구니 번호
	private int idx_fk;//회원번호
	private int pnum_fk;//상품번호
	private int pqty;//장바구니 상품 수량
	private Date indate;
	
	//장바구니 상품정보
	private String pname;
	private String pimage1;
	private int price;
	private int saleprice;
	private int point;
	
	private int totalPrice;//saleprice*pqty => 개별 상품의 총액
	private int totalPoint;//point*pqty => 개별 상품의 총 적립포인트
	
	private int cartTotalPrice;//장바구니에 담은 모든 상품의 총액
	private int cartTotalPoint;
}
