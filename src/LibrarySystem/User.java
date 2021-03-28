package LibrarySystem;

public class User {
    String userid;
    String un;
    String pw;
    String borrowA;
    String borrowB;
    String borrowC;
    String reserveA;
    String reserveB;
    String reserveC;
    
    public User(String userid, String un, String pw, String borrowA, String borrowB,
                String borrowC, String reserveA, String reserveB, String reserveC){
        this.userid = userid;
        this.un = un;
        this.pw = pw;
        this.borrowA = borrowA;
        this.borrowB = borrowB;
        this.borrowC = borrowC;
        this.reserveA = reserveA;
        this.reserveB = reserveB;
        this.reserveC = reserveC;
    }
}
