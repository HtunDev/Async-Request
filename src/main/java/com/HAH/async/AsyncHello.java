package com.HAH.async;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = "/async",
		asyncSupported = true
		)
public class AsyncHello extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		var out = resp.getWriter();

		var asyncContext = req.startAsync();

		out.append("""
				<html>
				<head>
				<title>Async Page</title>
				</head>
				<body>
				<h1> Hello Async. I'm a new learner.</h>
				""");

		asyncContext.start(() -> {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		asyncContext.complete();

		out.append("""
				<p> This is heavy processing and waist my time. </p>
				<a href="async">Request Again</a>
				</body>
				</html>
				""");
	}

}
