package LibrarySystem;

import static LibrarySystem.SPJButton.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class LibraryController implements ActionListener, WindowListener{
    LibraryModel model;
    LibraryView view;
    DBConnection dbcon;
    LibraryController(LibraryModel model, LibraryView view, DBConnection dbcon) {
        this.model = model;
        this.view = view;
    }
    void updateBookView(){
        view.bookview.generateView(model.getBooks(),"", this, this);
    }
    void createBookTable(){
        view.bookview.displayMessage(model.createBookTable());
        updateBookView();
    }
    void addBook(Book std){
        view.bookview.displayMessage(model.addBookRow(std));
        updateBookView();
    }
    void removeBook(String col, String row){
        view.bookview.displayMessage(model.removeBookRow(col,row));
        updateBookView();
    }
    void editBook(String row, Book std){
        view.bookview.displayMessage(model.editBookRow(row, std));
        updateBookView();
    }
    void updateUserView(){
        view.userview.generateView(model.getUsers(),"", this, this);
    }
    void createUserTable(){
        view.userview.displayMessage(model.createUserTable());
        updateUserView();
    }
    void addUser(User std){
        view.userview.displayMessage(model.addUserRow(std));
        updateUserView();
    }
    void removeUser(String col, String row){
        view.userview.displayMessage(model.removeUserRow(col,row));
        updateUserView();
    }
    void editUser(String row, User std){
        view.userview.displayMessage(model.editUserRow(row, std));
        updateUserView();
    }
    void updateSettingView(){
        new SettingView();
    }
    void editSetting(Setting settings){
        model.editSettingsRow(settings);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof SPJButton){
            int type = ((SPJButton)(e.getSource())).type;
            String row = ((SPJButton)(e.getSource())).row;
            switch(type){
                case BOOK_CANCEL:{
                    view.bookview.generateView(model.getBooks(),"", this, this);
                    break;
                }
                case BOOK_SAVE:{
                    addBook(view.bookview.getBookInput());
                    view.bookview.generateView(model.getBooks(),"", this, this);
                    break;
                }
                case BOOK_EDIT:{
                    view.bookview.generateView(model.getBooks(),row, this, this);
                    break;
                }
                case BOOK_UPDATE:{
                    editBook(row,view.bookview.getBookInput());
                    view.bookview.generateView(model.getBooks(),"", this, this);
                    break;
                }
                case BOOK_DELETE:{
                    removeBook("bookid",row);
                    view.bookview.generateView(model.getBooks(),"", this, this);
                    break;
                }
                case BOOK_CLEAR:{
                    view.bookview.generateView(model.getBooks(),"", this, this);
                    break;
                }
                case USER_CANCEL:{
                    view.userview.generateView(model.getUsers(),"", this, this);
                    break;
                }
                case USER_SAVE:{
                    addUser(view.userview.getUserInput());
                    view.userview.generateView(model.getUsers(),"", this, this);
                    break;
                }
                case USER_EDIT:{
                    view.userview.generateView(model.getUsers(),row, this, this);
                    break;
                }
                case USER_UPDATE:{
                    editUser(row,view.userview.getUserInput());
                    view.userview.generateView(model.getUsers(),"", this, this);
                    break;
                }
                case USER_DELETE:{
                    removeUser("userid",row);
                    view.userview.generateView(model.getUsers(),"", this, this);
                    break;
                }
                case USER_CLEAR:{
                    view.userview.generateView(model.getUsers(),"", this, this);
                    break;
                }
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {  
    }

    @Override
    public void windowClosed(WindowEvent e) {
        dbcon.disconnect();
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
