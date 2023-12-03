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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.bookstore.database.BookDAO;
import com.bookstore.model.Book;
import com.bookstore.model.Inventory;

public class EditABook {
	
	private JFrame frame = new JFrame();
    private JLabel searchLabel = new JLabel("Enter ISBN to Search for Book:");
    private JTextField searchTxtField = new JTextField();
    private JButton searchBtn = new JButton("Search");
    
    private JLabel label = new JLabel("Edit Book Details");
    
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

    private JButton editButton = new JButton("Edit Book");

    private JTextArea resultTxtArea = new JTextArea();
    private JScrollPane scrollPane = new JScrollPane(resultTxtArea);

    EditABook() {

        searchLabel.setBounds(50, 0, 300, 50);
        searchLabel.setFont(new Font(null, Font.PLAIN, 15));
        
        searchTxtField.setBounds(50, 40, 200, 30);
        
        searchBtn.setBounds(260, 40, 100, 30);
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBookByIsbn();
            }
        });

        label.setBounds(50, 80, 300, 50);
        label.setFont(new Font(null, Font.PLAIN, 15));

        isbnLabel.setBounds(50, 120, 100, 30);
        isbnTxtField.setBounds(150, 120, 200, 30);

        titleLabelInput.setBounds(50, 160, 100, 30);
        titleTxtField.setBounds(150, 160, 200, 30);

        purchaseLabel.setBounds(50, 200, 100, 30);
        purchaseTxtField.setBounds(150, 200, 200, 30);

        retailLabel.setBounds(50, 240, 100, 30);
        retailTxtField.setBounds(150, 240, 200, 30);

        genreLabel.setBounds(50, 280, 100, 30);
        genreTxtField.setBounds(150, 280, 200, 30);

        authorIdLabel.setBounds(50, 320, 100, 30);
        authorIdTxtField.setBounds(150, 320, 200, 30);

        editButton.setBounds(50, 360, 150, 30);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editBook();
            }
        });

        scrollPane.setBounds(50, 400, 500, 195);

        frame.add(searchLabel);
        frame.add(searchTxtField);
        frame.add(searchBtn);
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
        frame.add(editButton);
        frame.add(scrollPane);

        frame.setTitle("Edit Book");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 800);
        frame.setResizable(false);
        frame.setLayout(null);

        ImageIcon appLogo = new ImageIcon("inventoryLogo.png");
        frame.setIconImage(appLogo.getImage());
        frame.getContentPane().setBackground(new Color(230, 230, 230));

        frame.setVisible(true);
    }

    private void searchBookByIsbn() {
        String isbn = searchTxtField.getText().trim();

        if (!isValidIsbn(isbn)) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid ISBN Number pattern.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Perform the search operation using BookDAO and update resultTxtArea
        BookDAO bookDAO = new BookDAO();
        Book book = bookDAO.getBookByIsbn(isbn);
        bookDAO.closeConnection();

        if (book != null) {
            resultTxtArea.setText(book.toString());
            // Populate edit fields with book details
            isbnTxtField.setText(book.getIsbnNumber());
            titleTxtField.setText(book.getBookTitle());
            purchaseTxtField.setText(String.valueOf(book.getPurchaseCost()));
            retailTxtField.setText(String.valueOf(book.getRetailPrice()));
            genreTxtField.setText(book.getGenre());
            authorIdTxtField.setText(String.valueOf(book.getAuthor().getAuthorId()));
        } else {
            resultTxtArea.setText("No book matches your search criteria.");
        }
    }

    private void editBook() {
        String isbn = isbnTxtField.getText().trim();
        String title = titleTxtField.getText().trim();
        String purchaseStr = purchaseTxtField.getText().trim();
        String retailStr = retailTxtField.getText().trim();
        String genre = genreTxtField.getText().trim();
        String authorIdStr = authorIdTxtField.getText().trim();

        if (isbn.isEmpty() || title.isEmpty() || purchaseStr.isEmpty() || retailStr.isEmpty() || genre.isEmpty() || authorIdStr.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "All fields are required. Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isValidIsbn(isbn)) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid ISBN Number pattern.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double purchase = Double.parseDouble(purchaseStr);
            double retail = Double.parseDouble(retailStr);
            int authorId = Integer.parseInt(authorIdStr);

            BookDAO bookDAO = new BookDAO();
            Book existingBook = bookDAO.getBookByIsbn(isbn);
            bookDAO.closeConnection();

            if (existingBook != null) {
                Book updatedBook = new Book(isbn, title, purchase, retail, true, genre, authorId);
                boolean updateSuccess = bookDAO.editBook(updatedBook);

                if (updateSuccess) {
                    JOptionPane.showMessageDialog(frame, "The book was successfully updated!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to update the book.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "No book found for ISBN: " + isbn, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter valid numeric values for 'Purchase Cost', 'Retail Price', and 'Author ID'.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // validate Isbn Number format
    private static boolean isValidIsbn(String isbn) {
        String isbnPattern = "^\\d{3}-\\d-\\d{4}-\\d{4}-\\d{1}$";
        return isbn.matches(isbnPattern);
    }

    // validate the provided inventory id
    private static boolean isValidInventoryId(int inventoryId, List<Inventory> inventoryList) {
        return inventoryList.stream().anyMatch(inv -> inv.getInventoryId() == inventoryId);
    }

}
