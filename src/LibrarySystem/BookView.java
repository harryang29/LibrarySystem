package LibrarySystem;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.border.Border;

public class BookView{
    final static Border BKLINEBORDER = BorderFactory.createLineBorder(Color.BLACK);
    JFrame view = null;
    JPanel cPane = null;
    JTextField bookid1;
    JTextField isbn1;
    JTextField title1;
    JTextField year1;
    JTextField author1;
    JTextField place1;    
    JTextField publisher1;
    JTextField available1;
    JTextField reserve1;
    JTextField copies1;
    String row;
    void generateView(LinkedList <Book> books, String row, ActionListener al, WindowListener wl){
        this.row = row;
        if(view == null){
            view = new JFrame();
        }
        
        cPane = new JPanel();
        if(!row.equals("")){
            view.setSize(1200,(books.size()+1)*50);
            cPane.setLayout(new GridLayout(books.size()+1, 12));
        }else{
            view.setSize(1200,(books.size()+2)*50);
            cPane.setLayout(new GridLayout(books.size()+2, 12));
        }
        
        LinkedList<JLabel> titles = new LinkedList();
        titles.add(new JLabel("ID"));
        titles.add(new JLabel("ISBN"));
        titles.add(new JLabel("TITLE"));
        titles.add(new JLabel("YEAR"));
        titles.add(new JLabel("AUTHOR"));
        titles.add(new JLabel("PLACE"));
        titles.add(new JLabel("PUBLISHER"));
        titles.add(new JLabel("AVAILABLE"));
        titles.add(new JLabel("RESERVE"));
        titles.add(new JLabel("Copies"));
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
        for(Book book : books){
            if(!book.bookid.equalsIgnoreCase(row)){
                JLabel bookid2 = new JLabel(book.bookid);
                JLabel isbn2 = new JLabel(book.isbn);
                JLabel title2 = new JLabel(book.title);
                JLabel year2 = new JLabel(book.year);
                JLabel author2 = new JLabel(book.author);
                JLabel place2 = new JLabel(book.place);
                JLabel publisher2 = new JLabel(book.publisher);
                JLabel available2 = new JLabel(book.available);
                JLabel reserve2 = new JLabel(book.reserve);
                JLabel copies2 = new JLabel(book.copies);
                SPJButton edit = new SPJButton("Edit", book.bookid, SPJButton.BOOK_EDIT);
                SPJButton delete = new SPJButton("Delete", book.bookid, SPJButton.BOOK_DELETE);

                cPane.add(bookid2);
                cPane.add(isbn2);
                cPane.add(title2);
                cPane.add(year2);
                cPane.add(author2);
                cPane.add(place2);
                cPane.add(publisher2);
                cPane.add(available2);
                cPane.add(reserve2);
                cPane.add(copies2);
                cPane.add(edit);
                cPane.add(delete);
                
                fields.add(bookid2);
                fields.add(isbn2);
                fields.add(title2);
                fields.add(year2);
                fields.add(author2);
                fields.add(place2);
                fields.add(publisher2);
                fields.add(available2);
                fields.add(reserve2);
                fields.add(copies2);

                edit.setHorizontalAlignment(JButton.CENTER);
                delete.setHorizontalAlignment(JButton.CENTER);
                edit.setBorder(BKLINEBORDER);
                delete.setBorder(BKLINEBORDER);
                edit.addActionListener(al);
                delete.addActionListener(al);
            }else{
                JLabel bookid3 = new JLabel(book.bookid);
                JLabel isbn3 = new JLabel(book.isbn);
                JLabel available3 = new JLabel(book.available);
                JLabel reserve3 = new JLabel(book.reserve);
                title1 = new JTextField(book.title);
                year1 = new JTextField(book.year);
                author1 = new JTextField(book.author);
                place1 = new JTextField(book.place);
                publisher1 = new JTextField(book.publisher);
                copies1 = new JTextField(book.copies);
                SPJButton save = new SPJButton("Save", book.bookid, SPJButton.BOOK_UPDATE);
                SPJButton cancel = new SPJButton("Cancel", book.bookid, SPJButton.BOOK_CANCEL);
                
                save.addActionListener(al);
                cancel.addActionListener(al);
                
                cPane.add(bookid3);
                cPane.add(isbn3);
                cPane.add(title1);
                cPane.add(year1);
                cPane.add(author1);
                cPane.add(place1);
                cPane.add(publisher1);
                cPane.add(available3);
                cPane.add(reserve3);
                cPane.add(copies1);
                cPane.add(save);
                cPane.add(cancel);
            }
        }
        for(JLabel field: fields){
            field.setHorizontalAlignment(JLabel.CENTER);
            field.setBorder(BKLINEBORDER);
        }
        if(row.equals("")){
            bookid1 = new JTextField("");
            isbn1 = new JTextField("");
            title1 = new JTextField("");
            year1 = new JTextField("");
            author1 = new JTextField("");
            place1 = new JTextField("");
            publisher1 = new JTextField("");
            available1 = new JTextField("");
            reserve1 = new JTextField("");
            copies1 = new JTextField("");
            SPJButton save = new SPJButton("Save", (books.size()+1)+"", SPJButton.BOOK_SAVE);
            SPJButton clear = new SPJButton("Clear", (books.size()+1)+"", SPJButton.BOOK_CLEAR);

            save.addActionListener(al);
            clear.addActionListener(al);

            cPane.add(bookid1);
            cPane.add(isbn1);
            cPane.add(title1);
            cPane.add(year1);
            cPane.add(author1);
            cPane.add(place1);
            cPane.add(publisher1);
            cPane.add(available1);
            cPane.add(reserve1);
            cPane.add(copies1);
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
    Book getBookInput(){
        if(row.equals("")){
            row = bookid1.getText();
        }
        return new Book(row, isbn1.getText(), title1.getText(), year1.getText(), author1.getText(), place1.getText(),
                           publisher1.getText(),available1.getText(),reserve1.getText(),copies1.getText());
    }
}
