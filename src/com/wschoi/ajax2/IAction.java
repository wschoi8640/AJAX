package com.wschoi.ajax2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAction {

	public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	public boolean isRedirect();
}
