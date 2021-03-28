    package LibrarySystem;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Menu extends JFrame implements ActionListener{
    JButton books;
    JButton users;
    JButton settings;
    JButton exit;
    
    public Menu(){
        super("Menu");
        setLayout(new GridLayout(4,1));
        books = new JButton("Books");
        users = new JButton("Users");
        settings = new JButton("Settings");
        exit = new JButton("Exit");
        
        add(books);
        add(users);
        add(settings);
        add(exit);
        
        books.addActionListener(this);
        users.addActionListener(this);
        settings.addActionListener(this);
        exit.addActionListener(this);
        setSize(200,400);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        DBConnection dbCon = new DBConnection();
        dbCon.login("harry", "harry");
        dbCon.connect();
        LibraryView view = new LibraryView();
        LibraryModel model = new LibraryModel(dbCon.getConnection());
        LibraryController controller = new LibraryController(model, view, dbCon);
        if(e.getSource()==books){
            controller.updateBookView();
        }
        if(e.getSource()==users){
            controller.updateUserView();
        }
        if(e.getSource()==settings){
            controller.updateSettingView();
        }
        if(e.getSource()==exit){
            this.dispose();
        }
    }
}
