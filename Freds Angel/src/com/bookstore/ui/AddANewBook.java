package com.bookstore.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.bookstore.database.BookDAO;
import com.bookstore.model.Book;
import com.bookstore.model.Inventory;

public class AddANewBook {
	
	private JFrame frame = new JFrame();
	private JLabel label = new JLabel("Enter the details for adding a New Book");
	
	private JLabel isbnLabel = new JLabel("ISBN:");
    private JTextField isbnTxtField = new JTextField();

    private JLabel titleLabelInput = new JLabel("Title:");
    private JTextField titleTxtField = new JTextField();

    private JLabel purchaseLabel = new JLabel("Purchase Cost:");
    private JTextField purchaseTxtField = new JTextField();

    private JLabel retailLabel = new JLabel("Retail Price:");
    private JTextField retailTxtField = new JTextField();

    private JLabel genreLabel = new JLabel("Genre:");
    private JTextField genreTxtField = new JTextField();

    private JLabel authorIdLabel = new JLabel("Author ID:");
    private JTextField authorIdTxtField = new JTextField();

    private JButton addButton = new JButton("Add Book");
	
	AddANewBook(){
		
		label.setBounds(50, 0, 300, 50);
		label.setFont(new Font(null, Font.PLAIN, 15));

        isbnLabel.setBounds(50, 40, 100, 30);
        isbnTxtField.setBounds(150, 40, 200, 30);

        titleLabelInput.setBounds(50, 80, 100, 30);
        titleTxtField.setBounds(150, 80, 200, 30);

        purchaseLabel.setBounds(50, 120, 100, 30);
        purchaseTxtField.setBounds(150, 120, 200, 30);

        retailLabel.setBounds(50, 160, 100, 30);
        retailTxtField.setBounds(150, 160, 200, 30);

        genreLabel.setBounds(50, 200, 100, 30);
        genreTxtField.setBounds(150, 200, 200, 30);

        authorIdLabel.setBounds(50, 240, 100, 30);
        authorIdTxtField.setBounds(150, 240, 200, 30);

        addButton.setBounds(50, 280, 150, 30);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

		frame.add(label);
        frame.add(isbnLabel);
        frame.add(isbnTxtField);
        frame.add(titleLabelInput);
        frame.add(titleTxtField);
        frame.add(purchaseLabel);
        frame.add(purchaseTxtField);
        frame.add(retailLabel);
        frame.add(retailTxtField);
        frame.add(genreLabel);
        frame.add(genreTxtField);
        frame.add(authorIdLabel);
        frame.add(authorIdTxtField);
        frame.add(addButton);
		
		frame.setTitle("Add a New Book");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(600, 800);
		frame.setResizable(false);
		frame.setLayout(null);
		
		ImageIcon appLogo = new ImageIcon("inventoryLogo.png");
		frame.setIconImage(appLogo.getImage());
		frame.getContentPane().setBackground(new Color(230, 230, 230));
		
		frame.setVisible(true);
	}
	
	 private void addBook() {
        
	 	String isbn = isbnTxtField.getText().trim();
        String title = titleTxtField.getText().trim();
        String purchaseStr = purchaseTxtField.getText().trim();
        String retailStr = retailTxtField.getText().trim();
        String genre = genreTxtField.getText().trim();
        String authorIdStr = authorIdTxtField.getText().trim();

        /*
         * validate inputs
         */
        if (isbn.isEmpty() || title.isEmpty() || purchaseStr.isEmpty() || retailStr.isEmpty() || genre.isEmpty() || authorIdStr.isEmpty()) {
        	
            JOptionPane.showMessageDialog(frame, "All fields are required. Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        /*
         * validate isbn number
         */
        if (!isValidIsbn(isbn)) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid ISBN Number pattern.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double purchase = Double.parseDouble(purchaseStr);
            double retail = Double.parseDouble(retailStr);
            int authorId = Integer.parseInt(authorIdStr);

            // Check if the ISBN already exists
            if (isIsbnAlreadyExists(isbn)) {
                JOptionPane.showMessageDialog(frame, "This ISBN already exists. Please enter a unique ISBN.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create new book
            Book newBook = new Book(isbn, title, purchase, retail, true, genre, authorId);

            // Add book to the database
            BookDAO bookDAO = new BookDAO();
            bookDAO.addBook(newBook);
            bookDAO.closeConnection();

            JOptionPane.showMessageDialog(frame, "The new book was successfully added!");

            clearInputFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter valid numeric values for 'Purchase Cost', 'Retail Price', and 'Author ID'.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
	 
	 private boolean isIsbnAlreadyExists(String isbn) {
        BookDAO bookDAO = new BookDAO();
        boolean exists = bookDAO.doesIsbnExists(isbn);
        bookDAO.closeConnection();
        return exists;
	   }

	    private void clearInputFields() {
	    	
	        isbnTxtField.setText("");
	        titleTxtField.setText("");
	        purchaseTxtField.setText("");
	        retailTxtField.setText("");
	        genreTxtField.setText("");
	        authorIdTxtField.setText("");
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
