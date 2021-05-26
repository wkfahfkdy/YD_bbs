package com.yedam.bulletin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.bulletin.service.BulletinService;
import com.yedam.bulletin.serviceImpl.BulletinServiceImpl;
import com.yedam.bulletin.vo.BulletinVO;
import com.yedam.common.DbCommand;

public class BulletinUpdate implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		String content = request.getParameter("content");
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		
		BulletinVO vo = new BulletinVO();
		vo.setId(Integer.parseInt(id));
		vo.setTitle(title);
		vo.setContent(content);
		
		BulletinService service = new BulletinServiceImpl();
		int r = service.updateBulletin(vo);
		System.out.println(r + "건 수정");
			
		if (r > 0) {
			return "bulletinListPaging.do";
		} else {
			return "main.do";
		}
	}
}
