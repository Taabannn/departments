package ir.maktab58.exercise2.database;

import ir.maktab58.exercise2.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDataBaseAccess {
    protected static Connection connection = null;

    public BookDataBaseAccess() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/booksArray",
                "root", "61378");
    }

    public boolean saveBook(Book book, int bookIndex) throws SQLException {
        if (connection != null) {

            String sqlQuery = String.format("INSERT INTO booksArray.books " +
                    "(book_id, book_title, book_type, book_price) VALUES (?, ?, ?, ?)");
            PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
            pstmt.setInt(1, bookIndex + 1);
            pstmt.setString(2, book.getBookTitle());
            pstmt.setString(3, String.valueOf(book.getClass()));
            pstmt.setDouble(4, book.getBookPrice());
            boolean result = pstmt.execute();
            return result;

        }
        return false;
    }
}
