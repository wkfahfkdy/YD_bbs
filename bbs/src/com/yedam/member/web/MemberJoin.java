package com.yedam.member.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.DAO;
import com.yedam.common.DbCommand;
import com.yedam.member.service.MemberService;
import com.yedam.member.serviceImpl.MemberServiceImpl;
import com.yedam.member.vo.MemberVO;

public class MemberJoin implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// DB 저장, main.do 호출
		
		HttpSession session = request.getSession();
		
		String id = request.getParameter("memberId"); // memberJoinForm 의 name 값 불러옴
		String pwd = request.getParameter("memberPwd");
		String name = request.getParameter("memberName");
		String addr = request.getParameter("memberAddr");
		
		MemberVO vo = new MemberVO();
		vo.setAddr(addr);
		vo.setId(id);
		vo.setName(name);
		vo.setPwd(pwd);
		
		MemberService service =  new MemberServiceImpl();
		service.insertMember(vo);
		
		session.setAttribute("id", id);
		session.setAttribute("member", vo);
		
		return "main/main.tiles";
	}

}
