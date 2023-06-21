package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BookService {
static Connection con = null;
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gitHub?user=root&password=sql123");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int addBooks(Book book) {
		String query = "insert into book_info1 values(?,?,?,?,?)";
		PreparedStatement pstmt=null;
		int count = 0;
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1,0);
			pstmt.setString(2, book.getBookName());
			pstmt.setString(3,book.getBookGenre());
			pstmt.setString(4,book.getBookAuthor());
			pstmt.setDouble(5, book.getBookPrice());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	public ArrayList<Book> displayBooks() {
		String query = "select * from book_info1";
		Statement stmt = null;
		ArrayList<Book> bookList = new ArrayList();
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				int bookId = rs.getInt(1);
				String name = rs.getString(2);
				String genre = rs.getString(3);
				String author = rs.getString(4);
				double price = rs.getDouble(5);
				
				Book book = new Book(bookId,name,genre,author,price);
				
				bookList.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookList;
	}
}