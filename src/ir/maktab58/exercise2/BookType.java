package ir.maktab58.exercise2;

public enum BookType {
    FICTION("fiction"),
    NON_FICTION("non-fiction");
    private String nameOfBookTypes;

    BookType(String nameOfBookTypes) {
        this.nameOfBookTypes = nameOfBookTypes;
    }

    public String getNameOfBookTypes() {
        return nameOfBookTypes;
    }
}
