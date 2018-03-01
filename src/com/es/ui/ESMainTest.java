package com.es.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ESMainTest extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ESMainTest();
	}

	public ESMainTest() {
		// TODO Auto-generated constructor stub
		JButton varButton = new JButton("Test");
		setTitle("Test Frame");
		setBounds(150, 150, 300, 300);
		setLayout(new BorderLayout());		
		add(new EDateTimePicker(), BorderLayout.NORTH);
		varButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// new ECalendar();
			}
		});
		
		setVisible(true); 
	}
}
