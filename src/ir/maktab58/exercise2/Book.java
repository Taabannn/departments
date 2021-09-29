package ir.maktab58.exercise2;

public abstract class Book {
    private String bookTitle;
    protected double bookPrice;

    public Book(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public abstract void setBookPrice();

    @Override
    public String toString() {
        return "bookTitle='" + bookTitle + '\'' +
                ", bookPrice=" + bookPrice;
    }
}
