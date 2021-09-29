package ir.maktab58.exercise2;

public class NonFiction extends Book {
    public NonFiction(String bookTitle) {
        super(bookTitle);
    }

    @Override
    public void setBookPrice() {
        bookPrice = 37.99;
    }

    @Override
    public String toString() {
        return "NonFiction{" +
                super.toString() +
                '}';
    }
}
