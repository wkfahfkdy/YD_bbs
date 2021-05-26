package com.yedam.bulletin.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.bulletin.service.BulletinService;
import com.yedam.bulletin.serviceImpl.BulletinServiceImpl;
import com.yedam.bulletin.vo.BulletinVO;

@WebServlet("/deleteServlet")
public class DeleteServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		// System.out.println(id);
		
		BulletinVO vo = new BulletinVO();
		vo.setId(Integer.parseInt(id));
		
		BulletinService service = new BulletinServiceImpl();
		int r = service.deleteBulletin(vo);
		
		System.out.println(r + "건 삭제");
	}

}
