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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.bookstore.database.InventoryDAO;
import com.bookstore.model.Inventory;

public class EditQty {
	
	private JFrame frame = new JFrame();
    private JLabel isbnLbl = new JLabel("Enter an ISBN to change the Qty");
    private JLabel qtyLbl = new JLabel("Enter the new inventory Qty");
    private JTextField isbnTxtField = new JTextField();
    private JTextField qtyTxtField = new JTextField();
    private JButton updateQtyBtn = new JButton("Update Quantity");

    EditQty() {
    	
    	isbnLbl.setBounds(50, 20, 300, 30);
    	isbnLbl.setFont(new Font(null, Font.PLAIN, 15));
    	isbnTxtField.setBounds(50, 50, 300, 30);
    	
    	qtyLbl.setBounds(50, 80, 300, 30);
    	qtyLbl.setFont(new Font(null, Font.PLAIN, 15));        
        qtyTxtField.setBounds(50, 110, 300, 30);

        updateQtyBtn.setBounds(50, 180, 150, 30);
        updateQtyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateInventoryQty();
            }
        });

        frame.add(isbnLbl);
        frame.add(qtyLbl);
        frame.add(isbnTxtField);
        frame.add(qtyTxtField);
        frame.add(updateQtyBtn);

        frame.setTitle("Edit Inventory Quantity");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setResizable(false);
        frame.setLayout(null);

        ImageIcon appLogo = new ImageIcon("inventoryLogo.png");
        frame.setIconImage(appLogo.getImage());
        frame.getContentPane().setBackground(new Color(230, 230, 230));

        frame.setVisible(true);
    }

    private void updateInventoryQty() {
    	
        String isbn = isbnTxtField.getText();
        String qtyStr = qtyTxtField.getText();

        if (isValidIsbn(isbn) && isValidQty(qtyStr)) {
            int qty = Integer.parseInt(qtyStr);
            
            InventoryDAO inventoryDAO = new InventoryDAO();
            Inventory inventory = new Inventory();
            inventory.setIsbnNumber(isbn);
            inventory.setQty(qty);

            boolean qtyUpdated = inventoryDAO.updateInventory(inventory);
            inventoryDAO.closeConnection();

            if (qtyUpdated) {
                JOptionPane.showMessageDialog(frame, "Inventory quantity has been updated successfully!");
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to update inventory quantity", "Error", JOptionPane.ERROR_MESSAGE);
            }

            clearInputFields();
        } else {
            JOptionPane.showMessageDialog(frame, "Please enter a valid ISBN Number and quantity.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearInputFields() {
    	
        isbnTxtField.setText("");
        qtyTxtField.setText("");
    }

    private static boolean isValidIsbn(String isbn) {
    	
        String isbnPattern = "^\\d{3}-\\d-\\d{4}-\\d{4}-\\d{1}$";
        return isbn.matches(isbnPattern);
    }

    private static boolean isValidQty(String qtyStr) {
    	
        try {
            int qty = Integer.parseInt(qtyStr);
            return qty >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
