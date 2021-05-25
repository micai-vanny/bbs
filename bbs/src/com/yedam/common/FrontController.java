package com.yedam.common;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.member.web.MemberJoin;
import com.yedam.member.web.MemberJoinForm;
import com.yedam.member.web.MemberLogin;
import com.yedam.member.web.MemberLoginForm;
import com.yedam.member.web.MemberLogout;
import com.yedam.notice.web.Notice;
import com.yedam.notice.web.NoticeForm;
import com.yedam.notice.web.NoticeInsert;
import com.yedam.notice.web.NoticeList;
import com.yedam.notice.web.NoticeListpaging;
import com.yedam.notice.web.NoticeUpdate;

public class FrontController extends HttpServlet {
	private HashMap<String, DbCommand> map = new HashMap<String, DbCommand>();
	@Override
	public void init(ServletConfig config) throws ServletException {
		// 요청페이지 - 실행 컨트롤
		map.put("/main.do", new MainPage());
		map.put("/memberJoinForm.do", new MemberJoinForm());
		map.put("/memberJoin.do", new MemberJoin());
		map.put("/memberLoginForm.do", new MemberLoginForm());
		map.put("/memberLogin.do", new MemberLogin());
		map.put("/memberLoginOut.do", new MemberLogout());
		
		// 공지사항
		map.put("/noticeList.do", new NoticeList());
		map.put("/noticeListPaging.do", new NoticeListpaging());
		map.put("/notice.do", new Notice());
		map.put("/noticeUpdate.do", new NoticeUpdate());
		map.put("/noticeForm.do", new NoticeForm());
		map.put("/noticeInsert.do", new NoticeInsert());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String uri = req.getRequestURI();
		String cpath = req.getContextPath();
		String path = uri.substring(cpath.length());
		DbCommand command = map.get(path);
		String viewPage = command.execute(req, resp);
		
		RequestDispatcher rd = req.getRequestDispatcher(viewPage);
		rd.forward(req, resp);		
	}
}
