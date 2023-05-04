package com.shop.model;

import lombok.Data;
@Data
public class ProductVO {
	//property

	private String upCg_code;
	private String upCg_name;
	
	private String downCg_code;
	private String downCg_name;
	
	private int pnum;
	private String pname;
	private String pimage1;
	private String pimage2;
	private String pimage3;
	
	private int price;
	private int saleprice;
	private int point;
	private int pqty;

	private String pspec;//HIT,NEW.BEST
	private String pcontents;
	private String pcompany;
	private java.sql.Date pindate;
	
	private int totalPrice;/*총판매가 = 상품판매가 * 수량 */
	private int totalPoint;/*총포인트 = 포인트 * 수량 */
	
	public void setPqty(int pqty) {
		this.pqty=pqty;
		this.totalPrice=this.saleprice*this.pqty;	
		this.totalPoint=this.point*this.pqty;
	}
	/*할인율을 반환하는 메서드 */
	public int getPercent() {
		//(정가 - 판매가)/정가
		int percent=(price-saleprice)*100/price;
		
		return percent;
	}
	
}
