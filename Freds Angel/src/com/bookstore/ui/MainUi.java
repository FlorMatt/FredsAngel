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
		mainFrm.setLayout(new BorderLayout(10,10));
		
		ImageIcon appLogo = new ImageIcon("inventoryLogo.png");
		mainFrm.setIconImage(appLogo.getImage());
		mainFrm.getContentPane().setBackground(new Color(230,230,230));
		
		mainFrm.setVisible(true);
		
		JPanel topPnl = new JPanel();
		//JPanel leftPnl = new JPanel();
		//JPanel rightPnl = new JPanel();
		//JPanel btmPnl = new JPanel();
		JPanel cntPnl = new JPanel();
		
		topPnl.setBackground(new Color(201,201,201));
		//leftPnl.setBackground(Color.green);
		//rightPnl.setBackground(Color.yellow);
		//btmPnl.setBackground(Color.magenta);
		cntPnl.setBackground(Color.gray);
		
		cntPnl.setLayout(new BorderLayout());
		
		topPnl.setPreferredSize(new Dimension(100,100));
		//leftPnl.setPreferredSize(new Dimension(1100,100));
		//rightPnl.setPreferredSize(new Dimension(100,100));
		//btmPnl.setPreferredSize(new Dimension(100,100));
		cntPnl.setPreferredSize(new Dimension(100,100));
		
		//------------------ sub pannels ------------------
		
		JPanel panel6 = new JPanel();
		JPanel panel7 = new JPanel();
		JPanel panel8 = new JPanel();
		JPanel panel9 = new JPanel();
		JPanel topPnl0 = new JPanel();
		
		panel6.setBackground(Color.lightGray);
		panel7.setBackground(Color.lightGray);
		panel8.setBackground(Color.lightGray);
		panel9.setBackground(Color.lightGray);
		topPnl0.setBackground(Color.lightGray);
		
		cntPnl.setLayout(new BorderLayout());
		
		panel6.setPreferredSize(new Dimension(50,50));
		panel7.setPreferredSize(new Dimension(50,50));
		panel8.setPreferredSize(new Dimension(50,50));
		panel9.setPreferredSize(new Dimension(50,50));
		topPnl0.setPreferredSize(new Dimension(50,50));
		
		//cntPnl.add(panel6,BorderLayout.CENTER);
		cntPnl.add(panel7,BorderLayout.CENTER);
		//cntPnl.add(panel8,BorderLayout.CENTER);
		//cntPnl.add(panel9,BorderLayout.CENTER);
		//cntPnl.add(topPnl0,BorderLayout.CENTER);
		
		//------------------ sub pannels ------------------
		
		mainFrm.add(topPnl,BorderLayout.NORTH);
		//mainFrm.add(leftPnl,BorderLayout.WEST);
		//mainFrm.add(rightPnl,BorderLayout.EAST);
		//mainFrm.add(btmPnl,BorderLayout.SOUTH);
		mainFrm.add(cntPnl,BorderLayout.CENTER);
	}

}
