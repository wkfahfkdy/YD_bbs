package com.yedam.product.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.DbCommand;
import com.yedam.product.serviceImpl.ProductServiceImpl;
import com.yedam.product.vo.ProductVO;

public class AddCart implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// cart table 에 한 건 추가. user_id, item_code, item_qty(=1)

		String id = request.getParameter("id");
		String itemCode = request.getParameter("itemCode");
		int qty = 1;
		
		ProductServiceImpl service = new ProductServiceImpl();
		service.addCart(id, itemCode, qty);
		ProductServiceImpl service2 = new ProductServiceImpl();
		int cnt = service2.getCountCart(id);
		
		HttpSession session = request.getSession();
		session.setAttribute("cnt", cnt);
		
		return "productList.do";
	}

}
