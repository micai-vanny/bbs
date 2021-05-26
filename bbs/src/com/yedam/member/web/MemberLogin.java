package com.yedam.member.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.DbCommand;
import com.yedam.member.serviceImpl.MemberServiceImpl;
import com.yedam.member.vo.MemberVO;

public class MemberLogin implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// 입력한 ID와 패스워드를 회원 테이블에서 확인해 결과를 리턴.
		// 존재하는 회원일 경우 이름을 화면에 출력.
		HttpSession session = request.getSession();
		
		String id = request.getParameter("memberId");
		String pwd = request.getParameter("memberPwd");
		
		
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPwd(pwd);
		
		MemberServiceImpl service = new MemberServiceImpl();
		MemberVO rvo = service.loginCheck(vo);
		String path = "";
		
		if(rvo == null) {
			// 존재하지 않는 회원 => memberLoginFail.jsp
			path = "member/memberLoginFail.tiles";
		}else {
			// 로그인 처리 => memberLoginSuccess.jsp
			session.setAttribute("id", rvo.getId());
			session.setAttribute("mname", rvo.getName());
	
			request.setAttribute("vo", rvo);
			path = "member/memberLoginSuccess.tiles";
		}
		
		return path;
	}

}
