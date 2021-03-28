package LibrarySystem;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Login extends JFrame implements ActionListener{
    final String user = "admin";
    final String pass = "admin";
    
    JLabel username;
    JLabel password;
    JTextField un;
    JPasswordField pw;
    JButton clear;
    JButton login;
    
    public Login(){
        super("Login");
        setLayout(new GridLayout(3,2));
        username = new JLabel("Username");
        password = new JLabel("Password");
        un = new JTextField();
        pw = new JPasswordField();
        clear = new JButton("Clear");
        login = new JButton("Login");
        
        add(username);
        add(un);
        add(password);
        add(pw);
        add(clear);
        add(login);
        
        login.addActionListener(this);
        clear.addActionListener(this);
        setSize(300,200);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==login){
            if(un.getText().equals(user) && pw.getText().equals(pass)){
                this.dispose();
                new Menu();
            }
            else{
                un.setText("");
                pw.setText("");
                JOptionPane.showMessageDialog(rootPane,"Username/Password is incorrect!","Login failed",JOptionPane.INFORMATION_MESSAGE);
            }
        }
        if(e.getSource()==clear){
            un.setText("");
            pw.setText("");
        }
    }
    public static void main(String[] args) {
        new Login();
    }
}
