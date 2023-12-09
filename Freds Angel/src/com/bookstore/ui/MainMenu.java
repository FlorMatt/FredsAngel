package com.bookstore.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainMenu implements ActionListener {
	
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JButton oneBtn = new JButton("Search by ISBN Number");
	JButton twoBtn = new JButton("Add a New Book");
	JButton threeBtn = new JButton("Change a Book's Status");
	JButton fourBtn = new JButton("Edit Inventory Qty");
	JButton fiveBtn = new JButton("Audit Report");
	JButton sixBtn = new JButton("Edt a Book");
	
	public MainMenu(){
		
		int buttonWidth = 200;
        int buttonHeight = 50;
		
		oneBtn.setFocusable(false);
		oneBtn.addActionListener(this);
		
		twoBtn.setFocusable(false);
		twoBtn.addActionListener(this);
		
		threeBtn.setFocusable(false);
		threeBtn.addActionListener(this);
		
		fourBtn.setFocusable(false);
		fourBtn.addActionListener(this);
		
		fiveBtn.setFocusable(false);
		fiveBtn.addActionListener(this);
		
		sixBtn.setFocusable(false);
		sixBtn.addActionListener(this);
		
		panel.setBackground(new Color(201,201,201));
		panel.setLayout(null);
		
		oneBtn.setBounds((600 - buttonWidth) / 2, 50, buttonWidth, buttonHeight);
        twoBtn.setBounds((600 - buttonWidth) / 2, 150, buttonWidth, buttonHeight);
        threeBtn.setBounds((600 - buttonWidth) / 2, 250, buttonWidth, buttonHeight);
        fourBtn.setBounds((600 - buttonWidth) / 2, 350, buttonWidth, buttonHeight);
        fiveBtn.setBounds((600 - buttonWidth) / 2, 450, buttonWidth, buttonHeight);
        sixBtn.setBounds((600 - buttonWidth) / 2, 550, buttonWidth, buttonHeight);
		
		panel.add(oneBtn);
		panel.add(twoBtn);
		panel.add(threeBtn);
		panel.add(fourBtn);
		panel.add(fiveBtn);
		panel.add(sixBtn);
		
		
		frame.setTitle("Inventory Management System");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,800);
		frame.setResizable(false);
		
		ImageIcon appLogo = new ImageIcon("inventoryLogo.png");
		frame.setIconImage(appLogo.getImage());
		frame.getContentPane().setBackground(new Color(230,230,230));
		
		frame.setLayout(new BorderLayout());
		frame.getContentPane().add(panel,BorderLayout.CENTER);
		
		frame.setVisible(true);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==oneBtn) {
			SearchByIsbnNumber optOne = new SearchByIsbnNumber();
		}
		
		if (e.getSource()==twoBtn) {
			AddANewBook optTwo = new AddANewBook();
		}
		if (e.getSource()==threeBtn) {
			ChangeABookStatus optThree = new ChangeABookStatus();
		}
		if (e.getSource()==fourBtn) {
			EditQty optFour = new EditQty();
		}
		if (e.getSource()==fiveBtn) {
			AuditReport optFive = new AuditReport();
		}
		if (e.getSource()==sixBtn) {
			EditABook optSix = new EditABook();
		}
		
	}
	
}
