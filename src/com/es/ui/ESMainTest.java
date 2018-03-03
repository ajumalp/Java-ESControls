package com.es.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class ESMainTest extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ESMainTest();
	}

	public ESMainTest() {
		setLocationByPlatform(true);
		setSize(new Dimension(250, 250));
		setTitle("This is a test project");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		EDateTimePicker dateTimePicker = new EDateTimePicker();
		getContentPane().add(dateTimePicker, BorderLayout.NORTH);
		setVisible(true);
	}
}
