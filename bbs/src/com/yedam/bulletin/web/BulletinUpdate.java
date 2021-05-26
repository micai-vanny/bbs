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
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BulletinVO vo = new BulletinVO();
		vo.setId(Integer.parseInt(id));
		vo.setTitle(title);
		vo.setContent(content);
		
		BulletinService service = new BulletinServiceImpl();
		service.updateBulletin(vo);
		
		request.setAttribute("bulletin", vo);
		
		return "bulletinSelect.do";
	}

}
