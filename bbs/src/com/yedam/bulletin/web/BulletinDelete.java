package com.yedam.bulletin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.runtime.directive.Parse;

import com.yedam.bulletin.service.BulletinService;
import com.yedam.bulletin.serviceImpl.BulletinServiceImpl;
import com.yedam.bulletin.vo.BulletinVO;
import com.yedam.common.DbCommand;

public class BulletinDelete implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
//		String id = request.getParameter("id");
//		
//		BulletinVO vo = new BulletinVO();
//		vo.setId(Integer.parseInt(id));
//		
//		BulletinService service = new BulletinServiceImpl();
//		int r = service.deleteBulletin(vo);
//		
//		System.out.println(r + "건 삭제");
		
		return "bulletinListPaging.do";
	}

}
