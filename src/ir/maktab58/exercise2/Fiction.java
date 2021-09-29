package ir.maktab58.exercise2;

public class Fiction extends Book{
    public Fiction(String bookTitle) {
        super(bookTitle);
    }

    @Override
    public void setBookPrice() {
        bookPrice = 24.99;
    }

    @Override
    public String toString() {
        return "Fiction{" +
                super.toString() +
                '}';
    }


}
