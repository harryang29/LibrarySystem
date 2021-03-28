package LibrarySystem;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.border.Border;

public class UserView {
    final static Border BKLINEBORDER = BorderFactory.createLineBorder(Color.BLACK);
    JFrame view = null;
    JPanel cPane = null;
    JTextField userid1;
    JTextField un1;
    JTextField pw1;
    JTextField borrowA1;
    JTextField borrowB1;
    JTextField borrowC1;    
    JTextField reserveA1;
    JTextField reserveB1;
    JTextField reserveC1;
    String row;
    void generateView(LinkedList <User> users, String row, ActionListener al, WindowListener wl){
        this.row = row;
        if(view == null){
            view = new JFrame();
        }
        
        cPane = new JPanel();
        if(!row.equals("")){
            view.setSize(1100,(users.size()+1)*50);
            cPane.setLayout(new GridLayout(users.size()+1, 11));
        }else{
            view.setSize(1100,(users.size()+2)*50);
            cPane.setLayout(new GridLayout(users.size()+2, 11));
        }
        
        LinkedList<JLabel> titles = new LinkedList();
        titles.add(new JLabel("ID"));
        titles.add(new JLabel("USERNAME"));
        titles.add(new JLabel("PASSWORD"));
        titles.add(new JLabel("BORROW"));
        titles.add(new JLabel("BORROW"));
        titles.add(new JLabel("BORROW"));        
        titles.add(new JLabel("RESERVE"));
        titles.add(new JLabel("RESERVE"));
        titles.add(new JLabel("RESERVE"));
        titles.add(new JLabel(""));
        titles.add(new JLabel(""));
        
        for(JLabel title: titles){
            cPane.add(title);
            title.setHorizontalAlignment(JLabel.CENTER);
            title.setBorder(BKLINEBORDER);
            title.setOpaque(true);
            title.setBackground(Color.GRAY);
        }
  
        LinkedList<JLabel> fields = new LinkedList();
        for(User user : users){
            if(!user.userid.equalsIgnoreCase(row)){
                JLabel userid2 = new JLabel(user.userid);
                JLabel un2 = new JLabel(user.un);
                JLabel pw2 = new JLabel(user.pw);
                JLabel borrowA2 = new JLabel(user.borrowA);
                JLabel borrowB2 = new JLabel(user.borrowB);
                JLabel borrowC2 = new JLabel(user.borrowC);
                JLabel reserveA2 = new JLabel(user.reserveA);
                JLabel reserveB2 = new JLabel(user.reserveB);
                JLabel reserveC2 = new JLabel(user.reserveC);
                SPJButton edit = new SPJButton("Edit", user.userid, SPJButton.USER_EDIT);
                SPJButton delete = new SPJButton("Delete", user.userid, SPJButton.USER_DELETE);

                cPane.add(userid2);
                cPane.add(un2);
                cPane.add(pw2);
                cPane.add(borrowA2);
                cPane.add(borrowB2);
                cPane.add(borrowC2);
                cPane.add(reserveA2);
                cPane.add(reserveB2);
                cPane.add(reserveC2);
                cPane.add(edit);
                cPane.add(delete);
                
                fields.add(userid2);
                fields.add(un2);
                fields.add(pw2);
                fields.add(borrowA2);
                fields.add(borrowB2);
                fields.add(borrowC2);
                fields.add(reserveA2);
                fields.add(reserveB2);
                fields.add(reserveC2);
                
                edit.setHorizontalAlignment(JButton.CENTER);
                delete.setHorizontalAlignment(JButton.CENTER);
                edit.setBorder(BKLINEBORDER);
                delete.setBorder(BKLINEBORDER);
                edit.addActionListener(al);
                delete.addActionListener(al);
            }else{
                JLabel userid3 = new JLabel(user.userid);
                un1 = new JTextField(user.un);
                pw1 = new JTextField(user.pw);
                borrowA1 = new JTextField(user.borrowA);
                borrowB1 = new JTextField(user.borrowB);
                borrowC1 = new JTextField(user.borrowC);
                reserveA1 = new JTextField(user.reserveA);
                reserveB1 = new JTextField(user.reserveB);
                reserveC1 = new JTextField(user.reserveC);
                SPJButton save = new SPJButton("Save", user.userid, SPJButton.USER_UPDATE);
                SPJButton cancel = new SPJButton("Cancel", user.userid, SPJButton.USER_CANCEL);
                
                save.addActionListener(al);
                cancel.addActionListener(al);
                
                cPane.add(userid3);
                cPane.add(un1);
                cPane.add(pw1);
                cPane.add(borrowA1);
                cPane.add(borrowB1);
                cPane.add(borrowC1);
                cPane.add(reserveA1);
                cPane.add(reserveB1);
                cPane.add(reserveC1);
                cPane.add(save);
                cPane.add(cancel);
            }
        }
        for(JLabel field: fields){
            field.setHorizontalAlignment(JLabel.CENTER);
            field.setBorder(BKLINEBORDER);
        }
        if(row.equals("")){
            userid1 = new JTextField("");
            un1 = new JTextField("");
            pw1 = new JTextField("");
            borrowA1 = new JTextField("");
            borrowB1 = new JTextField("");
            borrowC1 = new JTextField("");
            reserveA1 = new JTextField("");
            reserveB1 = new JTextField("");
            reserveC1 = new JTextField("");
            SPJButton save = new SPJButton("Save", (users.size()+1)+"", SPJButton.USER_SAVE);
            SPJButton clear = new SPJButton("Clear", (users.size()+1)+"", SPJButton.USER_CLEAR);

            save.addActionListener(al);
            clear.addActionListener(al);

            cPane.add(userid1);
            cPane.add(un1);
            cPane.add(pw1);
            cPane.add(borrowA1);
            cPane.add(borrowB1);
            cPane.add(borrowC1);
            cPane.add(reserveA1);
            cPane.add(reserveB1);
            cPane.add(reserveC1);
            cPane.add(save);
            cPane.add(clear);
        }
        view.setContentPane(cPane);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.addWindowListener(wl);
        view.revalidate();
        view.repaint();
        if(!view.isVisible()){
            view.setVisible(true);
        }
    }
    void displayMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    User getUserInput(){
        if(row.equals("")){
            row = userid1.getText();
        }
        return new User(row, un1.getText(), pw1.getText(), borrowA1.getText(), borrowB1.getText(), borrowC1.getText(),
                           reserveA1.getText(),reserveB1.getText(),reserveC1.getText());
    }
}
