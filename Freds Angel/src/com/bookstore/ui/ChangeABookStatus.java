package com.bookstore.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.bookstore.database.BookDAO;

public class ChangeABookStatus {
	
	private JFrame frame = new JFrame();
    private JLabel label = new JLabel("Enter ISBN and Select Status:");
    private JTextField isbnTxtField = new JTextField();
    private JCheckBox activeCheckBox = new JCheckBox("Active");
    private JCheckBox inactiveCheckBox = new JCheckBox("Inactive");
    private JButton changeStatusBtn = new JButton("Change Status");

    ChangeABookStatus() {
        label.setBounds(50, 0, 300, 50);
        label.setFont(new Font(null, Font.PLAIN, 15));

        isbnTxtField.setBounds(50, 40, 300, 30);

        activeCheckBox.setBounds(50, 80, 100, 30);
        inactiveCheckBox.setBounds(160, 80, 100, 30);

        changeStatusBtn.setBounds(50, 120, 150, 30);
        changeStatusBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeBookStatus();
            }
        });

        frame.add(label);
        frame.add(isbnTxtField);
        frame.add(activeCheckBox);
        frame.add(inactiveCheckBox);
        frame.add(changeStatusBtn);

        frame.setTitle("Change Book Status");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setResizable(false);
        frame.setLayout(null);

        ImageIcon appLogo = new ImageIcon("inventoryLogo.png");
        frame.setIconImage(appLogo.getImage());
        frame.getContentPane().setBackground(new Color(230, 230, 230));

        frame.setVisible(true);
    }

    private void changeBookStatus() {
        String isbn = isbnTxtField.getText();
        boolean isActive = activeCheckBox.isSelected();
        boolean isInactive = inactiveCheckBox.isSelected();

        if (isValidIsbn(isbn) && (isActive || isInactive) && !(isActive && isInactive)) {
            BookDAO bookDAO = new BookDAO();
            boolean statusChanged = bookDAO.changeActiveStatus(isbn, isActive);
            bookDAO.closeConnection();

            if (statusChanged) {
                JOptionPane.showMessageDialog(frame, "The book's status has been updated successfully!");
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to update the book's status", "Error", JOptionPane.ERROR_MESSAGE);
            }

            clearInputFields();
        } else {
            JOptionPane.showMessageDialog(frame, "Please enter a valid ISBN Number pattern and select only one status.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearInputFields() {
        isbnTxtField.setText("");
        activeCheckBox.setSelected(false);
        inactiveCheckBox.setSelected(false);
    }

    private static boolean isValidIsbn(String isbn) {
        String isbnPattern = "^\\d{3}-\\d-\\d{4}-\\d{4}-\\d{1}$";
        return isbn.matches(isbnPattern);
    }

}
