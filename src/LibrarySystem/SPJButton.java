package LibrarySystem;

import javax.swing.JButton;

public class SPJButton extends JButton{
    final static int BOOK_CANCEL = 1 ;
    final static int BOOK_SAVE = 2 ;
    final static int BOOK_EDIT = 3 ;
    final static int BOOK_DELETE = 4 ;
    final static int BOOK_CLEAR = 5 ;
    final static int BOOK_UPDATE = 6 ;
    final static int USER_CANCEL = 7 ;
    final static int USER_SAVE = 8 ;
    final static int USER_EDIT = 9 ;
    final static int USER_DELETE = 10 ;
    final static int USER_CLEAR = 11 ;
    final static int USER_UPDATE = 12 ;
    String row;
    int type;
    SPJButton(String text, String row, int type){
        super(text);
        this.row = row;
        this.type = type;
    }
}
