package ir.maktab58.exercise1;

public abstract class Admin {
    private final static String username = "root";
    private final static String password = "61378"; //My password for access databases

    public static boolean isThisUserAnAdmin(String enteredUsername, String enteredPassword){
        if (!enteredUsername.equals(username)){
            System.out.println("This username is not existed! Please try again.");
            return false;
        }

        if (!enteredPassword.equals(password)){
            System.out.println("This password is not correct! Please try again.");
            return false;
        }

        return true;
    }
}
