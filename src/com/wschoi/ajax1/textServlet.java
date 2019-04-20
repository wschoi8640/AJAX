package com.wschoi.ajax1;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/textServlet2")
public class textServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; chatset=UTF-8");
		String oldText = request.getParameter("oldText");
		String newText = "검색 가능한 데이터 : 이름, 성별, 나이, 이메일";

		if (oldText.equals("이름")) {
			newText = "최원석";
		} else if (oldText.equals("성별")) {
			newText = "남자";
		} else if (oldText.equals("나이")) {
			newText = "29세";
		} else if (oldText.equals("이메일")) {
			newText = "cws8640@naver.com";
		}

		String result = URLEncoder.encode(newText, "UTF-8");
		response.getWriter().write(result);
	}

}
