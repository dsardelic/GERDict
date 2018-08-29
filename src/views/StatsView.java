package views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import presenters.Presenter;
import presenters.StatsPresenter;
import properties.Messages;

public class StatsView extends JFrame implements View {

	private static final long serialVersionUID = 249838040134572238L;

	private JTable table;
	private JScrollPane scrollPane;

	private StatsPresenter presenter;

	public StatsView() {
		initialize();
	}

	private void initialize() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		table = new JTable();
		table.setAutoCreateRowSorter(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		scrollPane = new JScrollPane(table);
		panel.add(scrollPane, BorderLayout.CENTER);

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		panel.add(rightPanel, BorderLayout.LINE_END);

		rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		rightPanel.add(Box.createVerticalGlue());

		JButton btnDeleteRows = new JButton(Messages.getMessage(this.getClass(), "btnDeleteRows"));
		btnDeleteRows.setAlignmentX(CENTER_ALIGNMENT);
		btnDeleteRows.setMinimumSize(new Dimension(200, 50));
		btnDeleteRows.setPreferredSize(new Dimension(250, 50));
		btnDeleteRows.setMaximumSize(new Dimension(250, 50));
		btnDeleteRows.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnDeleteRowsActionPerformed();
			}
		});
		rightPanel.add(btnDeleteRows);

		rightPanel.add(Box.createRigidArea(new Dimension(280, 50)));

		rightPanel.add(Box.createVerticalGlue());

		JButton btnClearRecentStats = new JButton(Messages.getMessage(this.getClass(), "btnClearRecentStats"));
		btnClearRecentStats.setAlignmentX(CENTER_ALIGNMENT);
		btnClearRecentStats.setMinimumSize(new Dimension(200, 50));
		btnClearRecentStats.setPreferredSize(new Dimension(250, 50));
		btnClearRecentStats.setMaximumSize(new Dimension(250, 50));
		btnClearRecentStats.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnClearRecentStatsActionPerformed();
			}
		});
		rightPanel.add(btnClearRecentStats);

		rightPanel.add(Box.createRigidArea(new Dimension(0, 50)));

		JButton btnDeleteStats = new JButton(Messages.getMessage(this.getClass(), "btnDeleteStats"));
		btnDeleteStats.setAlignmentX(CENTER_ALIGNMENT);
		btnDeleteStats.setMinimumSize(new Dimension(200, 50));
		btnDeleteStats.setPreferredSize(new Dimension(250, 50));
		btnDeleteStats.setMaximumSize(new Dimension(250, 50));
		btnDeleteStats.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnDeleteStatsActionPerformed();
			}
		});
		rightPanel.add(btnDeleteStats);

		rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		setTitle(Messages.getMessage(this.getClass(), "title"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setContentPane(panel);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = (StatsPresenter) presenter;
	}

	@Override
	public void displaySelf() {
		pack();
		setVisible(true);
	}

	private int resizeColumnWidths(JTable table) {
		int totalWidth = 0, padding = 30;
		TableColumnModel columnModel = table.getColumnModel();
		for (int columnIndex = 0; columnIndex < table.getColumnCount(); columnIndex++) {
			JTableHeader tableHeader = table.getTableHeader();
			FontMetrics headerFontMetrics = tableHeader.getFontMetrics(tableHeader.getFont());
			int width = headerFontMetrics.stringWidth(table.getColumnName(columnIndex));
			for (int rowIndex = 0; rowIndex < table.getRowCount(); rowIndex++) {
				TableCellRenderer renderer = table.getCellRenderer(rowIndex, columnIndex);
				Component comp = table.prepareRenderer(renderer, rowIndex, columnIndex);
				width = Math.max(comp.getPreferredSize().width, width);
			}
			width += padding;
			columnModel.getColumn(columnIndex).setPreferredWidth(width);
			totalWidth += width;
		}
		return totalWidth;
	}

	public void setStats(Object[][] tableData) {
		table.setModel(new StatsTableModel(tableData));
		table.getColumnModel().getColumn(2).setCellRenderer(new PercentValueCellRenderer());
		table.getColumnModel().getColumn(4).setCellRenderer(new PercentValueCellRenderer());
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		int tableWidth = resizeColumnWidths(table);
		int tableHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		scrollPane.setMinimumSize(new Dimension(tableWidth, tableHeight));
		scrollPane.setPreferredSize(new Dimension(tableWidth, tableHeight));
		scrollPane.setMaximumSize(new Dimension(tableWidth, Integer.MAX_VALUE));
	}

	public List<String> getSelectedWords() {
		ArrayList<String> selectedRows = new ArrayList<>();
		for (int rowIndex : table.getSelectedRows()) {
			selectedRows.add((String) table.getValueAt(rowIndex, 0));
		}
		return selectedRows;
	}

	private void btnDeleteRowsActionPerformed() {
		if (JOptionPane.showConfirmDialog(this, Messages.getMessage(this.getClass(), "deleteSelectedRowsQuestion"),
				Messages.getMessage(this.getClass(), "deleteSelectedRowsTitle"),
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			presenter.deleteSelectedWordsStats();
		}
	}

	private void btnClearRecentStatsActionPerformed() {
		if (JOptionPane.showConfirmDialog(this, Messages.getMessage(this.getClass(), "clearRecentStatsQuestion"),
				Messages.getMessage(this.getClass(), "clearRecentStatsTitle"),
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			presenter.clearRecentStats();
		}
	}

	private void btnDeleteStatsActionPerformed() {
		if (JOptionPane.showConfirmDialog(this, Messages.getMessage(this.getClass(), "deleteStatsQuestion"),
				Messages.getMessage(this.getClass(), "deleteStatsTitle"),
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			presenter.deleteStats();
		}
	}

	private class PercentValueCellRenderer extends DefaultTableCellRenderer {

		private static final long serialVersionUID = 6479085532218487303L;

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
					column);
			if (value instanceof Double) {
				label.setText(new DecimalFormat("#%").format(value));
				label.setHorizontalAlignment(JLabel.RIGHT);
			}
			return label;
		}
	}
}
