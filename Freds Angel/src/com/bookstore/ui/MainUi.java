package com.bookstore.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class MainUi {
	
	public static void main(String[] args) {
		
		JFrame mainFrm = new JFrame();
		mainFrm.setTitle("Inventory Management System");
		mainFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrm.setSize(600,800);
		mainFrm.setLayout(new BorderLayout());
		
		ImageIcon appLogo = new ImageIcon("inventoryLogo.png");
		mainFrm.setIconImage(appLogo.getImage());
		mainFrm.getContentPane().setBackground(new Color(230,230,230));
		
		mainFrm.setVisible(true);
		
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		
		panel1.setBackground(Color.red);
		panel2.setBackground(Color.green);
		panel3.setBackground(Color.yellow);
		panel4.setBackground(Color.magenta);
		panel5.setBackground(Color.blue);
		
		panel1.setPreferredSize(new Dimension(100,100));
		panel2.setPreferredSize(new Dimension(100,100));
		panel3.setPreferredSize(new Dimension(100,100));
		panel4.setPreferredSize(new Dimension(100,100));
		panel5.setPreferredSize(new Dimension(100,100));
		
		mainFrm.add(panel1,BorderLayout.NORTH);
		mainFrm.add(panel2,BorderLayout.WEST);
		mainFrm.add(panel3,BorderLayout.EAST);
		mainFrm.add(panel4,BorderLayout.SOUTH);
		mainFrm.add(panel5,BorderLayout.CENTER);
	}

}
