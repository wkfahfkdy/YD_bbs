package com.yedam.product.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.DbCommand;
import com.yedam.product.service.ProductService;
import com.yedam.product.serviceImpl.ProductServiceImpl;
import com.yedam.product.vo.ProductVO;

public class ProductList implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		ProductService service = new ProductServiceImpl();
		List<ProductVO> list = service.productSelectList();
		
		String id = request.getParameter("id");
		
		request.setAttribute("id", id); // 아직 안만들어서 임의 값 user1 		
		request.setAttribute("list", list);
		
		return "product/productList.tiles";
	}
}
