package com.es.ui;

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
		setSize(new Dimension(450, 450));
		setTitle("This is a test project");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		EDateTimePicker dateTimePicker = new EDateTimePicker();
		dateTimePicker.setBounds(10, 26, 152, 23);
		getContentPane().add(dateTimePicker);
		setVisible(true);
	}
}
