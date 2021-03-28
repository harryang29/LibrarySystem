package LibrarySystem;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SettingView extends JFrame implements ActionListener{
    Setting settings;
    JLabel day;
    JLabel book;
    JLabel penalty;
    JTextField dayTF;
    JLabel bookTF;
    JTextField penaltyTF;
    JButton save;
    JButton clear;
    public SettingView(){
        super("Settings");
        setLayout(new GridLayout(4,2));
        day = new JLabel("Allowed days");
        book = new JLabel("Allowed books");
        penalty = new JLabel("Penalty Fee per day");
        dayTF = new JTextField();
        bookTF = new JLabel("3");
        penaltyTF = new JTextField();
        save = new JButton("Save");
        clear = new JButton("Clear");
        
        add(day);
        add(dayTF);
        add(book);
        add(bookTF);
        add(penalty);
        add(penaltyTF);
        add(clear);
        add(save);
        
        save.addActionListener(this);
        clear.addActionListener(this);
        setSize(300,400);
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
        if(e.getSource() == save){
            settings = new Setting(dayTF.getText(),bookTF.getText(),penaltyTF.getText());
            controller.editSetting(settings);
        }
        if(e.getSource() == clear){
            dayTF.setText("");
            penaltyTF.setText("");
        }
    }
}
