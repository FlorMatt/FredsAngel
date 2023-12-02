package com.bookstore.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ChangeABookStatus {
	
	ChangeABookStatus(){
		
		JFrame frame = new JFrame();
		JLabel label = new JLabel("Change A Book's Status");
		
		label.setBounds(50,0,300,50);
		label.setFont(new Font(null,Font.PLAIN,25));
		
		frame.add(label);
		
		frame.setTitle("Change A Book's Status");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(600,800);
		frame.setLayout(null);
		
		ImageIcon appLogo = new ImageIcon("inventoryLogo.png");
		frame.setIconImage(appLogo.getImage());
		frame.getContentPane().setBackground(new Color(230,230,230));
		
		frame.setVisible(true);
	}

}
