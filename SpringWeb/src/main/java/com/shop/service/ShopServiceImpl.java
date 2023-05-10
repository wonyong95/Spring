package com.shop.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.shop.mapper.ProductMapper;
import com.shop.mapper.CartMapper;
import com.shop.model.CartVO;
import com.shop.model.ProductVO;

@Service(value = "shopServiceImpl")
public class ShopServiceImpl implements ShopService {
	
	@Inject
	private ProductMapper productMapper;
	
	@Inject
	private CartMapper cartMapper;

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

		return productMapper.selectByPnum(pnum);
	}

	@Override
	public int addCart(CartVO cartVo) {
		//[1] 회원번호와 상품번호로 cart테이블에 있는 상품개수 가져오기
		Integer cnt=cartMapper.selectCartCountByPnum(cartVo);
		
		//[1_1] 장바구니에 추가할 상품이 이미 장바구니에 담겨있다면 => 수량만 수정 => update문
		if(cnt!=null) {
			int n=cartMapper.updateCartQty(cartVo);
			return n;
		}else {
		//[1_2] 장바구니에 없는 상품을 추가한다면 => insert문 수행
			int n=cartMapper.addCart(cartVo);
			return n;
		}
	}
	@Override
	public int updateCartQty(CartVO cartVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int editCart(CartVO cartVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CartVO> selectCartView(int midx) {

		return this.cartMapper.selectCartView(midx);
	}

	@Override
	public int delCart(int cartNum) {
		return cartMapper.delCart(cartNum);
	}

	@Override
	public int delCartAll(CartVO cartVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delCartOrder(Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCartCountByIdx(CartVO cartVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CartVO getCartTotal(int midx_fk) {
		
		return this.cartMapper.getCartTotal(midx_fk);
	}

	@Override
	public void delCartByOrder(int midx_fk, int pnum) {
		// TODO Auto-generated method stub
		
	}

}
