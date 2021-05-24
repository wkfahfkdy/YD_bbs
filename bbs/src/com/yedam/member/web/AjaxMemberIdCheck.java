package com.yedam.member.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.member.serviceImpl.MemberServiceImpl;

@WebServlet("/ajaxMemberIdCheck")
public class AjaxMemberIdCheck extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		
		MemberServiceImpl service = new MemberServiceImpl(); // MemberService 에 만들지 않아서 override 가 아님. 그래서 impl 에서
		
		int cnt = 0; 	// 아이디가 존재하지 않으면 0
		if(service.idCheck(id)) {
			cnt = 1;	// 존재하면 1
		};
		
		resp.getWriter().print(cnt);
	}
}
