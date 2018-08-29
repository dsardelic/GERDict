package views;

import javax.swing.table.AbstractTableModel;

import properties.Messages;

public class StatsTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 6336636288626817979L;

	private final String[] columnNames = { Messages.getMessage(getClass(), "word"),
		Messages.getMessage(getClass(), "recentAnswers"),
		Messages.getMessage(getClass(), "recentAccuracy"),
		Messages.getMessage(getClass(), "overallAnswers"),
		Messages.getMessage(getClass(), "overallAccuracy") };

	private Object[][] tableData;

	StatsTableModel(Object[][] tableData) {
		this.tableData = tableData;
	}

	public void setTableData(Object[][] tableData) {
		this.tableData = tableData;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return tableData.length;
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		return tableData.length == 0 ? null : tableData[row][col];
	}

	@Override
	public Class<?> getColumnClass(int c) {
		Object cellValue = getValueAt(0, c);
		return cellValue == null ? new Object().getClass() : cellValue.getClass();
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}
}
