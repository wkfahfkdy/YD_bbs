package com.yedam.product.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.DbCommand;
import com.yedam.product.serviceImpl.ProductServiceImpl;
import com.yedam.product.vo.CartVO;

public class cartList implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		System.out.println(id);
		ProductServiceImpl service = new ProductServiceImpl();
		List<CartVO> list = service.getCartList(id);
		
		request.setAttribute("list", list);		
		
		return "product/cartList.tiles";
	}

}
