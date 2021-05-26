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

public class Bulletin implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// 한 건 조회 -> bulletin.jsp
			
		String id = request.getParameter("id"); // noticeList.jsp
			
		BulletinVO vo = new BulletinVO();
		vo.setId(Integer.parseInt(id));
			
		BulletinService service = new BulletinServiceImpl();
		BulletinServiceImpl ser2 = new BulletinServiceImpl();
			
		ser2.hitCount(Integer.parseInt(id));
		service.bulletinSelect(vo);
			
		request.setAttribute("bulletin", vo);
			
		return "bulletin/bulletin.tiles";
	}

}
