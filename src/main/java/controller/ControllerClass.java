package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;
@WebServlet(urlPatterns = {"/loginlink","/bookslink"})
public class ControllerClass extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getServletPath();
		
		switch(url) {
		case "/loginlink": loginValidation(req,resp);
			break;
		case "/bookslink" : addBooks(req,resp);
		break;
		}
	}

	private void loginValidation(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		HttpSession sess = req.getSession(true);
		String user = req.getParameter("username");
		String pass = req.getParameter("password");
		PrintWriter pw = resp.getWriter();
		Service service = new Service();
		User user1 = new User();
		boolean status =  service.login(user,pass);
		RequestDispatcher rd;
			if(!status) {
				rd = req.getRequestDispatcher("index.html");
				rd.forward(req, resp);
			}else{
				sess.setAttribute("username", user);
				rd = req.getRequestDispatcher("books.jsp");
				rd.forward(req, resp);
		}
	}
	
	private void addBooks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("bookname");
		String genre = req.getParameter("bookgenre");
		String author = req.getParameter("author");
		double price= Double.parseDouble(req.getParameter("bookprice"));
		HttpSession sess = req.getSession(false);
		if(sess!=null) {
			Book book = new Book();
			book.setBookName(name);
			book.setBookGenre(genre);
			book.setBookAuthor(author);
			book.setBookPrice(price);
			BookService bookService = new BookService();
			int count = bookService.addBooks(book);
			RequestDispatcher rd = req.getRequestDispatcher("books.jsp");
			rd.forward(req, resp);
		}
	}
}
