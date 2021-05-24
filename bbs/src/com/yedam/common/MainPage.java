package com.yedam.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainPage implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// tiles.xml에 있는 <put-attribute name="body" value="/WEB-INF/jsp/{1}/{2}.jsp" />
		//															  └>이 부분에 들어감
		return "main/main.tiles";
	}

}
