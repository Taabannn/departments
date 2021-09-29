package ir.maktab58.exercise2;

public class UseBook {
    public static void main(String[] args) {
        Book[] myBooks = new Book[3];
        myBooks[0] = new Fiction("Prestige");
        myBooks[1] = new NonFiction("Becoming");
        myBooks[2] = new NonFiction("Art Of Thinking Clearly");
        for (Book book : myBooks) {
            book.setBookPrice();
            System.out.println(book);
        }
    }
}
