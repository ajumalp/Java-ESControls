package com.es.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormatSymbols;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

public class EDateTimePicker extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JFormattedTextField FTextArea;
	private JButton FSelectButton;

	public EDateTimePicker() {
		// TODO Auto-generated constructor stub
		InitUI();
	}

	private void InitUI() {
		FTextArea = new JFormattedTextField();
		FSelectButton = new JButton("...");
		// FCanender = new ECalendar();

		FTextArea.setEditable(false);
		FSelectButton.addActionListener(this);

		setLayout(new BorderLayout());
		add(FTextArea, BorderLayout.CENTER);
		add(FSelectButton, BorderLayout.EAST);
	}

	@Override
	public void actionPerformed(ActionEvent aEvent) {
		// TODO Auto-generated method stub
		if (aEvent.getSource() == FSelectButton) {
			ECalendar varCalendar = new ECalendar();
			Point varLocation = FTextArea.getLocationOnScreen();
			varLocation.y += getHeight();			
			varCalendar.ShowAt(varLocation);
		}
	}
}

class ECalendar extends JDialog implements ItemListener {

	private static final long serialVersionUID = 1L;

	private JDialog FContext;
	private ECalendarTable FCalTable;
	private JPanel FTopPanel;
	private JComboBox<String> cbMonths;
	private JComboBox<Integer> cbYears;

	public ECalendar() {
		// TODO Auto-generated constructor stub
		FContext = this;
		FCalTable = new ECalendarTable(2018, 2);
		FTopPanel = new JPanel();
		cbMonths = new JComboBox<String>(new DateFormatSymbols().getMonths());
		cbYears = new JComboBox<Integer>();
		for (int iCntr = 1985; iCntr < 2050; iCntr++) {
			cbYears.addItem(iCntr);
		}
		while (cbMonths.getItemCount() > 12) {
			cbMonths.removeItemAt(12);
		}
		// new DateFormatSymbols().getShortWeekdays()[0]
		addWindowFocusListener(new WindowAdapter() {
			@Override
			public void windowLostFocus(WindowEvent e) {
				// TODO Auto-generated method stub
				FContext.dispose();
			}
		});
		setResizable(false);
		setUndecorated(true);
		FTopPanel.setLayout(new BorderLayout());
		FTopPanel.add(cbMonths, BorderLayout.EAST);
		FTopPanel.add(cbYears, BorderLayout.WEST);
		add(FTopPanel, BorderLayout.NORTH);
		add(FCalTable, BorderLayout.CENTER);

		cbMonths.addItemListener(this);
		cbYears.addItemListener(this);
	}

	@Override
	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		super.setVisible(b);
		if (b == true) {
			setBounds(150, 150, 250, getTotalHeight());
			revalidate();
			repaint();
		}
	}
	
	public void ShowAt(Point aPoint) {
		setVisible(true);
		setLocation(aPoint);
	}

	private int getTotalHeight() {
		return (FCalTable.getRowHeight() * 6) + FTopPanel.getHeight();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if (e.getStateChange() == ItemEvent.SELECTED) {
			remove(FCalTable);
			FCalTable = new ECalendarTable(cbYears.getItemAt(cbYears.getSelectedIndex()), cbMonths.getSelectedIndex());
			add(FCalTable, BorderLayout.CENTER);
			revalidate();
			repaint();
		}
	}
}

class ECalendarTable extends JTable {

	private static final long serialVersionUID = 1L;

	private TableModel FTableModal;
	private Calendar FCalendar;

	public ECalendarTable(int aYear, int aMonth) {
		// TODO Auto-generated constructor stub
		FCalendar = Calendar.getInstance();
		FCalendar.set(aYear, aMonth, 1);
		InitUI();
	}

	private void InitUI() {
		FTableModal = new AbstractTableModel() {

			private static final long serialVersionUID = 1L;

			@Override
			public Object getValueAt(int aRowIndex, int aColumnIndex) {
				// TODO Auto-generated method stub
				if (aRowIndex == 0) {
					DefaultTableCellRenderer varRender = new DefaultTableCellRenderer();
					varRender.setHorizontalAlignment(SwingConstants.CENTER);
					getColumnModel().getColumn(aColumnIndex).setCellRenderer(varRender);
					return new DateFormatSymbols().getShortWeekdays()[aColumnIndex + 1];
				} else if (aRowIndex == 1) {
					if (aColumnIndex >= getFirstDayOfMonth()) {
						return aColumnIndex - getFirstDayOfMonth() + 1;
					}
				} else {
					int iDay = ((aRowIndex - 1) * 7) + (aColumnIndex - getFirstDayOfMonth() + 1);
					if (iDay <= getMaxDaysOfMonth()) {
						return iDay;
					}
				}
				return null;
			}

			@Override
			public int getRowCount() {
				// TODO Auto-generated method stub
				return 6;
			}

			@Override
			public int getColumnCount() {
				// TODO Auto-generated method stub
				return 7; // 7 days a week { Ajmal }
			}
		};

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getClickCount() == 2) {
					JOptionPane.showMessageDialog(null, "Done");
				}
			}
		});
		setAutoResizeMode(AUTO_RESIZE_ALL_COLUMNS);
		setRowHeight(20);
		setAutoscrolls(true);
		setRowSelectionAllowed(false);
		setCellSelectionEnabled(true);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setModel(FTableModal);
	}

	public int getFirstDayOfMonth() {
		return FCalendar.get(Calendar.DAY_OF_WEEK) - 1; // -1 to start from 0, Value of Sunday = 1 { Ajmal }
	}

	public int getMaxDaysOfMonth() {
		return FCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public int getYear() {
		return FCalendar.get(Calendar.YEAR);
	}

	public int getMonth() {
		return FCalendar.get(Calendar.MONTH);
	}
}
