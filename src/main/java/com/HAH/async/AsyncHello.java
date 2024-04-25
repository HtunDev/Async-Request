package com.HAH.async;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/async", asyncSupported = true)
public class AsyncHello extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		var out = resp.getWriter();
//		Async can handle req and resp
		var async = req.startAsync();
		async.setTimeout(5000);

		out.append("""
				<html>
				<head>
				<title>Async Page</title>
				</head>
				<body>
				<h1> Hello Async. I'm a new learner.</h>
				""");
// 		Thread can't handle
//		var thread = new Thread(getTask());
//		thread.start();
		async.start(getTask());
		async.complete();

		out.append("""
				<p> This is heavy processing and waist my time. </p>
				<a href="async">Request Again</a>
				</body>
				</html>
				""");
	}

	private Runnable getTask() {
		return () -> {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
	}

}
