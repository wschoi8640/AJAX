package com.wschoi.ajax2;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObjectBuilder;

public class JsonBuildAction extends HttpServlet implements IAction{

	private boolean redirect = false;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
			{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; chatset=UTF-8");
		// key와 value로 구성되는 HashMap 설정
		Map<String, String> map = new HashMap<String, String>();
		// map을 BuilderFactory에 넣어준다. (JsonBuilderFactory는 Json을 만들기 위한 뼈대)
		JsonBuilderFactory factory = Json.createBuilderFactory(map);

		// createObjectBuilder는 하나씩 넣을때, createArrayBuilder는 배열로 넣을 때
		JsonObjectBuilder ob_1 = factory.createObjectBuilder();
		JsonObjectBuilder ob_2 = factory.createObjectBuilder();
		JsonObjectBuilder ob_3 = factory.createObjectBuilder();
		JsonArrayBuilder ab = factory.createArrayBuilder();
		
		// 첫번째 JsonObjectBuilder에 이름과 나이 추가
		ob_1.add(encodeStr("이름"), encodeStr("최원석"));
		ob_1.add(encodeStr("나이"), encodeStr("29세"));
		ob_1.add(encodeStr("외모"), encodeStr("얼짱"));
		// 두번째 JsonObjectBuilder에 홍길동의 위치 추가
		ob_2.add(encodeStr("1전공"), encodeStr("독일어"));
		ob_2.add(encodeStr("수준"), encodeStr("보통"));
		
		// 세번째 JsonObjectBuilder에  홍길동의 위치 추가
		ob_3.add(encodeStr("2전공"),encodeStr("컴퓨터 공학"));
		ob_3.add(encodeStr("수준"),encodeStr("중상"));
		
		// 두번째와 세번째 JsonObjectBuilder를 ArrayBuilder에 넣어준다.
		ab.add(ob_2);
		ab.add(ob_3);
		
		// 첫번째 JsonObjectBuilder에 ArrayBuilder를 추가
		ob_1.add(encodeStr("학업"), ab);
		
		// 최종적으로 ob_1 JsonObject를 Build
		
		
		try {
			response.getWriter().print(ob_1.build());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "/jsonTest.jsp";
	}

	public String encodeStr(String str) throws UnsupportedEncodingException {
		return URLEncoder.encode(str,"UTF-8");
	}
	
	@Override
	public boolean isRedirect() {
		return redirect;
	}

}
