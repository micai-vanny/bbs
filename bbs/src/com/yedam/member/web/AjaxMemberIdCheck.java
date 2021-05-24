package com.yedam.member.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.yedam.member.serviceImpl.MemberServiceImpl;

@WebServlet("/ajaxMemberIdCheck")
public class AjaxMemberIdCheck extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		
		//Memberservice에 override로 만든 메소드가 아니기 때문에 MemberServiceImpl로 사용
		MemberServiceImpl service = new MemberServiceImpl();
		int cnt = 0;	// 존재하지 않으면 0이 리턴
		
		if(service.idCheck(id)) {
			cnt=1;	// 존재하면 1이 리턴
		}
		resp.getWriter().print(cnt);
	}
}
