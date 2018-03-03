package com.es.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import com.es.Resource.Res;
import com.es.core.EDateTime;

public class EDateTimePicker extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JTextField FTextArea;
	private JButton FSelectButton;
	private EDateTime FSelectedDate;

	public EDateTimePicker() {
		// TODO Auto-generated constructor stub
		InitUI();
	}

	private void InitUI() {
		FTextArea = new JTextField();
		FSelectButton = new JButton("...");

		FTextArea.setEditable(false);
		FSelectButton.addActionListener(this);

		setLayout(new BorderLayout());
		add(FTextArea, BorderLayout.CENTER);
		add(FSelectButton, BorderLayout.EAST);
		setSelectedDate(getSelectedDate());
	}

	@Override
	public void actionPerformed(ActionEvent aEvent) {
		// TODO Auto-generated method stub
		if (aEvent.getSource() == FSelectButton) {
			ECalendar varCalendar = new ECalendar(getSelectedDate().getDateTime());
			Point varLocation = FTextArea.getLocationOnScreen();
			varLocation.y += getHeight();

			varCalendar.AddOnChangeListener(new IECalendarListener() {

				@Override
				public void onDateSelected(ECalendar aCalendar) {
					// TODO Auto-generated method stub
					getSelectedDate().setDateTime(aCalendar.getSelectedDate().getDateTime());
					UpdateText();
				}

				@Override
				public void OnYearChanged(ECalendar aCalendar) {
				}

				@Override
				public void OnMonthChanged(ECalendar aCalendar) {
				}
			});

			varCalendar.ShowAt(varLocation);
		}
	}

	public EDateTime getSelectedDate() {
		if (FSelectedDate == null) {
			FSelectedDate = new EDateTime();
			FSelectedDate.setFormat("dd/MMM/yyyy");
		}
		return FSelectedDate;
	}

	private void setSelectedDate(EDateTime aSelectedDate) {
		FSelectedDate = aSelectedDate;
		UpdateText();
	}

	public void setSelectedDateTime(Date aDate) {
		getSelectedDate().setDateTime(aDate);
		UpdateText();
	}

	private void UpdateText() {
		FTextArea.setText(getSelectedDate().toString());
	}

	public String getFormat() {
		return getSelectedDate().getFormat();
	}

	public void setFormat(String aFormat) {
		getSelectedDate().setFormat(aFormat);
		setSelectedDate(getSelectedDate());
	}
}

interface IECalendarListener {
	public void OnMonthChanged(ECalendar aCalendar);

	public void OnYearChanged(ECalendar aCalendar);

	public void onDateSelected(ECalendar aCalendar);
}

class ECalendar extends JDialog implements ItemListener, ActionListener {

	private static final long serialVersionUID = 1L;

	private ECalendar FContext;
	private ECalendarTable FCalTable;
	private JToolBar FNavigPnl;
	private JComboBox<String> cbMonths;
	private JComboBox<Integer> cbYears;
	private EDateTime FSelectedDate;
	private JButton FPreYear, FPreMonth, FNextYear, FNextMonth, FToday;
	private IECalendarListener FCalendarListener;

	public ECalendar() {
		// TODO Auto-generated constructor stub
		this(EDateTime.Now());
	}

	public ECalendar(Date aSelectedDate) {
		// TODO Auto-generated constructor stub
		FContext = this;
		FCalTable = null;
		FCalendarListener = null;
		FNavigPnl = new JToolBar(JToolBar.HORIZONTAL);
		cbMonths = new JComboBox<String>(new DateFormatSymbols().getShortMonths());
		cbYears = new JComboBox<Integer>();
		FPreYear = new JButton(new ImageIcon(Res.Images.PNG.Size16.PreYear));
		FPreMonth = new JButton(new ImageIcon(Res.Images.PNG.Size16.PreMonth));
		FNextMonth = new JButton(new ImageIcon(Res.Images.PNG.Size16.NextMonth));
		FNextYear = new JButton(new ImageIcon(Res.Images.PNG.Size16.NextYear));
		FToday = new JButton(new ImageIcon(Res.Images.PNG.Size16.Today));

		for (int iCntr = 1985; iCntr < 2050; iCntr++) {
			cbYears.addItem(iCntr);
		}
		setSelectedDate(aSelectedDate);

		while (cbMonths.getItemCount() > 12) {
			cbMonths.removeItemAt(12);
		}

		addWindowFocusListener(new WindowAdapter() {
			@Override
			public void windowLostFocus(WindowEvent e) {
				// TODO Auto-generated method stub
				FContext.dispose();
			}
		});
		setType(Type.POPUP);
		setResizable(false);
		setUndecorated(true);
		Dimension varNvDim = new Dimension(22, 20);
		FToday.setToolTipText("Today");
		FPreYear.setToolTipText("Previous Year");
		FPreMonth.setToolTipText("Previous Month");
		FNextMonth.setToolTipText("Next Month");
		FNextYear.setToolTipText("Next Year");
		FToday.setPreferredSize(varNvDim);
		FPreYear.setPreferredSize(varNvDim);
		FPreMonth.setPreferredSize(varNvDim);
		FNextYear.setPreferredSize(varNvDim);
		FNextMonth.setPreferredSize(varNvDim);
		cbYears.setPreferredSize(new Dimension(60, 20));
		cbMonths.setPreferredSize(new Dimension(60, 20));

		FNavigPnl.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
		FNavigPnl.setFloatable(false);
		FNavigPnl.add(FPreYear);
		FNavigPnl.add(FPreMonth);
		FNavigPnl.add(FToday);
		FNavigPnl.add(FNextMonth);
		FNavigPnl.add(FNextYear);
		FNavigPnl.addSeparator();
		FNavigPnl.add(cbYears);
		FNavigPnl.addSeparator(new Dimension(3, 1));
		FNavigPnl.add(cbMonths);
		JPanel varContPnl = new JPanel();
		varContPnl.setLayout(new BorderLayout());
		varContPnl.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		setContentPane(varContPnl);
		varContPnl.add(FNavigPnl, BorderLayout.NORTH);

		UpdateCalendar(false);

		/* Add Listeners */
		FToday.addActionListener(this);
		FPreYear.addActionListener(this);
		FPreMonth.addActionListener(this);
		FNextMonth.addActionListener(this);
		FNextYear.addActionListener(this);
		FToday.addActionListener(this);
		cbMonths.addItemListener(this);
		cbYears.addItemListener(this);
	}

	@Override
	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		super.setVisible(b);
		if (b == true) {
			setSize(275, getTotalHeight());
			revalidate();
			repaint();
		}
	}

	public void AddOnChangeListener(IECalendarListener aListener) {
		FCalendarListener = aListener;
	}

	public void ShowAt(Point aPoint) {
		setVisible(true);
		setLocation(aPoint);
	}

	private int getTotalHeight() {
		return (FCalTable.getRowHeight() * FCalTable.getRowCount()) + FNavigPnl.getHeight() + 5;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		Object varSrc = e.getSource();
		if (e.getStateChange() == ItemEvent.SELECTED) {
			UpdateCalendar(true);

			if (FCalendarListener != null) {
				if (varSrc == cbMonths) {
					FCalendarListener.OnMonthChanged(FContext);
				} else if (varSrc == cbYears) {
					FCalendarListener.OnYearChanged(FContext);
				}
			}
		}
	}

	private void UpdateCalendar(boolean aRepaint) {
		if (FCalTable != null) {
			remove(FCalTable);
		}

		FSelectedDate.setYear(cbYears.getItemAt(cbYears.getSelectedIndex()));
		FSelectedDate.setMonth(cbMonths.getSelectedIndex());
		FCalTable = new ECalendarTable(getSelectedDate());

		getContentPane().add(FCalTable, BorderLayout.CENTER);
		FCalTable.AddOnSelectListener(new IECalendarTableListener() {

			@Override
			public void OnItemSelectListener(ECalendarTable aCalendarTable) {
				// TODO Auto-generated method stub
				getSelectedDate().setDate(Integer.parseInt(
						aCalendarTable.getValueAt(aCalendarTable.getSelectedRow(), aCalendarTable.getSelectedColumn())
								.toString().trim()));
				FContext.dispose();

				if (FCalendarListener != null) {
					FCalendarListener.onDateSelected(FContext);
				}
			}
		});

		if (aRepaint) {
			setVisible(true);
		}
	}

	public EDateTime getSelectedDate() {
		if (FSelectedDate == null) {
			FSelectedDate = new EDateTime();
		}
		return FSelectedDate;
	}

	private void setSelectedDate(Date aSelectedDate) {
		getSelectedDate().setDateTime(aSelectedDate);
		cbYears.setSelectedItem(getSelectedDate().getYear());
		cbMonths.setSelectedIndex(getSelectedDate().getMonth());
	}

	private void SelectCombItem(JComboBox<?> aCombo, int aValue) {
		int iSelectedYearIndex = aCombo.getSelectedIndex();
		int iNewIndex = iSelectedYearIndex + aValue;

		if ((aValue < 0) && (iNewIndex >= 0)) {
			aCombo.setSelectedIndex(iNewIndex);
		} else if ((aValue > 0) && (iNewIndex < aCombo.getItemCount())) {
			aCombo.setSelectedIndex(iNewIndex);
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		Object varSource = ae.getSource();
		if (varSource == FToday) {
			setSelectedDate(EDateTime.Now());
		} else if (varSource == FPreYear) {
			SelectCombItem(cbYears, -1);
		} else if (varSource == FPreMonth) {
			SelectCombItem(cbMonths, -1);
		} else if (varSource == FNextMonth) {
			SelectCombItem(cbMonths, 1);
		} else if (varSource == FNextYear) {
			SelectCombItem(cbYears, 1);
		}
	}
}

interface IECalendarTableListener {
	public void OnItemSelectListener(ECalendarTable aCalendarTable);
}

class ECalendarTable extends JTable {

	private static final long serialVersionUID = 1L;

	private ECalendarTable FContext;
	private TableModel FTableModal;
	private EDateTime FCalendar;
	private IECalendarTableListener FCalendarTableListener;
	private int iTodayRowIndex, iTodayColIndex;

	public ECalendarTable(EDateTime aSelectedDate) {
		// TODO Auto-generated constructor stub
		FCalendarTableListener = null;
		FCalendar = aSelectedDate;
		FContext = this;
		iTodayColIndex = -1;
		iTodayRowIndex = -1;
		InitUI();

		if ((iTodayRowIndex > -1) && (iTodayColIndex > -1)) {
			changeSelection(iTodayRowIndex, iTodayColIndex, false, false);
		}
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
					if (aColumnIndex >= (FCalendar.getFirstDayOfMonth())) {
						int iDay = aColumnIndex - FCalendar.getFirstDayOfMonth() + 1;
						FocusOnToday(iDay, aRowIndex, aColumnIndex);
						return iDay;
					}
				} else {
					int iDay = ((aRowIndex - 1) * 7) + (aColumnIndex - FCalendar.getFirstDayOfMonth() + 1);
					if (iDay <= FCalendar.getMaxDaysInMonth()) {
						FocusOnToday(iDay, aRowIndex, aColumnIndex);
						return iDay;
					}
				}
				return null;
			}

			@Override
			public int getRowCount() {
				// TODO Auto-generated method stub
				return 6 + (((FCalendar.getMaxDaysInMonth() - 28) + (FCalendar.getFirstDayOfMonth() - 1)) / 7);
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
					if (FCalendarTableListener != null) {
						FCalendarTableListener.OnItemSelectListener(FContext);
					}
				}
			}
		});

		setAutoResizeMode(AUTO_RESIZE_ALL_COLUMNS);
		setRowHeight(20);
		setAutoscrolls(true);
		setCellSelectionEnabled(true);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setModel(FTableModal);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	private void FocusOnToday(int aDate, int aRow, int aCol) {
		if ((Calendar.getInstance().get(Calendar.DATE) == aDate) && FCalendar.isThisYear() && FCalendar.isThisMonth()) {
			iTodayRowIndex = aRow;
			iTodayColIndex = aCol;
			// changeSelection(aRow, aCol, false, true);
		}
	}

	public void AddOnSelectListener(IECalendarTableListener aCalendarTableListener) {
		FCalendarTableListener = aCalendarTableListener;
	}
}
