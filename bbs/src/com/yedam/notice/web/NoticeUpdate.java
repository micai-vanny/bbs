package com.yedam.notice.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.DbCommand;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.serviceImpl.NoticeServiceImpl;
import com.yedam.notice.vo.NoticeVO;

public class NoticeUpdate implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// 파라미터로 넘어온 값(id, title, content)들을 활용해서 id 기준으로 update하는 메소드 추가.
		// serviceImpl => update 기능 작성
		// 공지사항 리스트로 페이지 호출.
//		NoticeList command = new NoticeList();
//		command.execute(request, response);
		
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		NoticeVO vo = new NoticeVO();
		vo.setId(Integer.parseInt(id));
		vo.setTitle(title);
		vo.setContent(content);
		
		NoticeService service = new NoticeServiceImpl();
		service.updateNotice(vo);
		
		request.setAttribute("notice", vo);
		
		return "noticeListPaging.do";
	}

}
