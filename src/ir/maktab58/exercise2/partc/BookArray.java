package ir.maktab58.exercise2.partc;

import ir.maktab58.exercise2.Book;
import ir.maktab58.exercise2.BookType;
import ir.maktab58.exercise2.Fiction;
import ir.maktab58.exercise2.NonFiction;
import ir.maktab58.exercise2.database.BookDataBaseAccess;

import java.sql.SQLException;
import java.util.Scanner;

public class BookArray {
   public static void main(String[] args) throws SQLException, ClassNotFoundException {
       BookDataBaseAccess bookAccess = new BookDataBaseAccess();
        Book[] books = new Book[10];
        int bookIndex = 0;
        while (bookIndex != 10){
            Book newBook = createAnBookObj();
            if (newBook != null){
                books[bookIndex] = newBook;
                bookAccess.saveBook(newBook, bookIndex);
                bookIndex++;
            }
        }
        printAllBooks(books);
    }

    public static void printAllBooks(Book[] books){
        for (Book book: books){
            System.out.println(book);
        }
    }

    public static Book createAnBookObj(){
        System.out.println("Please enter type of book.");
        Scanner inputLine = new Scanner(System.in);
        String typeOfBookStr = deleteLastSpaces(inputLine.nextLine());
        System.out.println("Please enter title of book");
        String titleOfBook = inputLine.nextLine();
        if (typeOfBookStr.equalsIgnoreCase(BookType.FICTION.getNameOfBookTypes())){
            Book book = new Fiction(titleOfBook);
            book.setBookPrice();
            return book;
        } else if (typeOfBookStr.equalsIgnoreCase(BookType.NON_FICTION.getNameOfBookTypes())){
            Book book = new NonFiction(titleOfBook);
            book.setBookPrice();
            return book;
        } else {
            return null;
        }
    }

    public static String deleteLastSpaces(String inputLine) {
        if (inputLine.length() == 0) {
            System.out.println("Input buffer is empty.");
            return inputLine;
        }

        if (inputLine.charAt(inputLine.length() - 1) != ' ')
            return inputLine;

        if (inputLine.equals(" ")) {
            System.out.println("Input buffer is just a space char.");
            return inputLine;
        }

        inputLine = inputLine.substring(0, inputLine.length() - 2);
        return deleteLastSpaces(inputLine);
    }
}
