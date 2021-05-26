package com.yedam.bulletin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.bulletin.service.BulletinService;
import com.yedam.bulletin.serviceImpl.BulletinServiceImpl;
import com.yedam.bulletin.vo.BulletinVO;
import com.yedam.common.DbCommand;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.serviceImpl.NoticeServiceImpl;
import com.yedam.notice.vo.NoticeVO;

public class BulletinForm implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("id");
		
		BulletinVO vo = new BulletinVO();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(writer);
		
		BulletinService service = new BulletinServiceImpl();
		int r = service.insertBulletin(vo);
		
		System.out.println(r + "건 입력");
		
		request.setAttribute("bulletinInsert", vo);
		
		String path = "";
		if (r > 0) {
			path = "bulletinListPaging.do";
		} else {
			path = "main.do";
		}
		
		return path;
	}

}
