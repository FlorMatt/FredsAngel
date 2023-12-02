package com.bookstore.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.bookstore.database.BookDAO;
import com.bookstore.model.Inventory;

public class SearchByIsbnNumber {
	
	private JFrame frame = new JFrame();
	private JLabel label = new JLabel("Enter an ISBN Number to Search by:");
	private JTextField isbnTxtField = new JTextField();
	private JButton searchBtn = new JButton("Search");
	private JTextArea resultTxtArea = new JTextArea();
	
	SearchByIsbnNumber(){
		
		label.setBounds(50, 0, 300, 50);
		label.setFont(new Font(null, Font.PLAIN, 15));
		
		isbnTxtField.setBounds(50, 40, 300, 30);
		
		searchBtn.setBounds(360, 40, 100, 30);
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchByIsbn();
			}
		});
		
		resultTxtArea.setBounds(50, 100, 500, 300);
		resultTxtArea.setEditable(false);
		
		frame.add(label);
		frame.add(isbnTxtField);
		frame.add(searchBtn);
		frame.add(resultTxtArea);
		
		frame.setTitle("Search By ISBN Number");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(600, 800);
		frame.setResizable(false);
		frame.setLayout(null);
		
		ImageIcon appLogo = new ImageIcon("inventoryLogo.png");
		frame.setIconImage(appLogo.getImage());
		frame.getContentPane().setBackground(new Color(230, 230, 230));
		
		frame.setVisible(true);
		
	}
	
	private void searchByIsbn() {
        String isbn = isbnTxtField.getText();

        if (isValidIsbn(isbn)) {
            // Perform the search operation using BookDAO and update resultTextArea
            BookDAO bookDAO = new BookDAO();
            com.bookstore.model.Book book = bookDAO.getBookByIsbn(isbn);
            bookDAO.closeConnection();

            if (book != null) {
                resultTxtArea.setText(book.toString());
            } else {
                resultTxtArea.setText("No book matches your search criteria.");
            }
        } else {
            resultTxtArea.setText("Please enter a valid ISBN Number pattern.");
        }
    }
	
	//validate Isbn Number format
    private static boolean isValidIsbn(String isbn) {
        String isbnPattern = "^\\d{3}-\\d-\\d{4}-\\d{4}-\\d{1}$";
        return isbn.matches(isbnPattern);
    }
    
    
    
    //validate the provided inventory id
    private static boolean isValidInventoryId(int inventoryId, List<Inventory> inventoryList) {
        return inventoryList.stream().anyMatch(inv -> inv.getInventoryId() == inventoryId);
    }
	
}
