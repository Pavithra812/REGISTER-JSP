package com.xxx.Conntroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxx.Model.Customer;
import com.xxx.bl.customCRUD;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public register() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd;
		Customer cs = new Customer();
		try {
			cs.setUsername(request.getParameter("username"));
			cs.setEmail(request.getParameter("email"));
			String pass = request.getParameter("user_password").toString();
			String cpass = request.getParameter("confirm").toString();
			if (pass.equals(cpass)) {
				cs.setUser_pass(pass);

				customCRUD c1 = new customCRUD();

				if (c1.addCustomer(cs)) {
					request.setAttribute("status", true);
					request.setAttribute("message", "inserted successfully");
					request.setAttribute("cs", new Customer());

				} else {
					request.setAttribute("status", true);
					request.setAttribute("message", "not inserted");
					request.setAttribute("cs", cs);

				}
			}

			else {
				request.setAttribute("status", true);
				request.setAttribute("message", "password and confirm password not match");

				request.setAttribute("cs", cs);
			}
		} catch (Exception e) {
			if (e.getMessage().contains("primary key")) {
				request.setAttribute("status", true);
				request.setAttribute("message", "emailid already exists");

				System.err.println(e.getMessage());
			} else {
				request.setAttribute("status", true);
				request.setAttribute("message", "server problem");
				System.err.println(e.getMessage());

			}
		}
		rd = request.getRequestDispatcher("views/index.jsp");

		rd.forward(request, response);
	}
}
