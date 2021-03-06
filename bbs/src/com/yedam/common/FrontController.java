package com.yedam.common;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.bulletin.web.Bulletin;
import com.yedam.bulletin.web.BulletinDelete;
import com.yedam.bulletin.web.BulletinForm;
import com.yedam.bulletin.web.BulletinInsertPage;
import com.yedam.bulletin.web.BulletinList;
import com.yedam.bulletin.web.BulletinListPaging;
import com.yedam.bulletin.web.BulletinUpdate;
import com.yedam.member.web.MemberJoin;
import com.yedam.member.web.MemberJoinForm;
import com.yedam.member.web.MemberLogin;
import com.yedam.member.web.MemberLoginForm;
import com.yedam.member.web.MemberLogout;
import com.yedam.notice.web.Notice;
import com.yedam.notice.web.NoticeDelete;
import com.yedam.notice.web.NoticeForm;
import com.yedam.notice.web.NoticeInsertPage;
import com.yedam.notice.web.NoticeList;
import com.yedam.notice.web.NoticeListPaging;
import com.yedam.notice.web.NoticeUpdate;
import com.yedam.product.web.AddCart;
import com.yedam.product.web.ProductFavList;
import com.yedam.product.web.ProductList;
import com.yedam.product.web.cartList;

public class FrontController extends HttpServlet {
	
	private HashMap<String, DbCommand> map = new HashMap<>();
	
	@Override
	public void init() throws ServletException {
		// 요청페이지 - 실행컨트롤
		map.put("/index.do", new IndexPage());
		map.put("/main.do", new MainPage());
		map.put("/memberJoinForm.do", new MemberJoinForm());
		map.put("/memberJoin.do", new MemberJoin());
		map.put("/memberLoginForm.do", new MemberLoginForm());
		map.put("/memberLogin.do", new MemberLogin());
		map.put("/memberLoginOut.do", new MemberLogout());
		
		// 공지사항
		map.put("/noticeList.do", new NoticeList());
		map.put("/noticeListPaging.do", new NoticeListPaging());
		map.put("/notice.do", new Notice());
		map.put("/noticeUpdate.do", new NoticeUpdate());
		map.put("/noticeForm.do", new NoticeForm());
		map.put("/noticeInsert.do", new NoticeInsertPage());
		map.put("/noticeDelete.do", new NoticeDelete());
		
		// 게시글 관련
		map.put("/bulletinList.do", new BulletinList());
		map.put("/bulletinInsert.do", new BulletinInsertPage());
		map.put("/bulletinForm.do", new BulletinForm());
		map.put("/bulletin.do", new Bulletin());
		map.put("/bulletinUpdate.do", new BulletinUpdate());
		map.put("/bulletinDelete.do", new BulletinDelete());
		map.put("/bulletinListPaging.do", new BulletinListPaging());
		
		
		// 상품 관련
		map.put("/productList.do", new ProductList());
		map.put("/productFavList.do", new ProductFavList());
		map.put("/addCart.do", new AddCart());
		map.put("/cartList.do", new cartList());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String uri = req.getRequestURI();
		String cpath = req.getContextPath();
		String path = uri.substring(cpath.length());
		DbCommand command = map.get(path);
		String viewPage = command.execute(req, resp);
		
		RequestDispatcher rd = req.getRequestDispatcher(viewPage);
		rd.forward(req, resp);
	}
	
}
