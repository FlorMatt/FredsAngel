package com.bookstore.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import com.bookstore.database.BookDAO;
import com.bookstore.model.Book;

public class AuditReport {
	
	private JFrame frame = new JFrame();
    private JButton reportBtn = new JButton("Generate Report");
    private JTextArea resultTxtArea = new JTextArea();
    private JScrollPane scrollPane = new JScrollPane(resultTxtArea);

    public AuditReport() {

    	reportBtn.setFocusable(false);
    	reportBtn.setBounds(50, 20, 200, 30);
    	reportBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateAuditReport();
            }
        });

        
        scrollPane.setBounds(50, 70, 500, 300);
        resultTxtArea.setEditable(false);

        frame.add(reportBtn);
        frame.add(scrollPane);

        frame.setTitle("Generate Audit Report");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setResizable(false);
        frame.setLayout(null);

        ImageIcon appLogo = new ImageIcon("inventoryLogo.png");
        frame.setIconImage(appLogo.getImage());
        frame.getContentPane().setBackground(new Color(230, 230, 230));

        frame.setVisible(true);
    }

    private void generateAuditReport() {
        
        BookDAO bookDAO = new BookDAO();
        List<Book> books = bookDAO.getAllBooks();
        bookDAO.closeConnection();

        if (books.isEmpty()) {
        	JOptionPane.showMessageDialog(frame, "Failed to find inventory", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            for (Book book : books) {
                resultTxtArea.append(book.toString() + "\n");
            }
            resultTxtArea.setCaretPosition(0);
        }
    }

    public static void main(String[] args) {
        new AuditReport();
    }

}
