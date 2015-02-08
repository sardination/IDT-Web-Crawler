import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ButtonEditor extends DefaultCellEditor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JButton button;
	private String label;
	private boolean isPushed;
	private String name;
	private Page page;

	public ButtonEditor(JCheckBox checkBox) {
		super(checkBox);
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
			}
		});
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		if (isSelected) {
			button.setForeground(table.getSelectionForeground());
			button.setBackground(table.getSelectionBackground());
		} else {
			button.setForeground(table.getForeground());
			button.setBackground(table.getBackground());
		}
		label = (value == null) ? "" : value.toString();
		button.setText(label);
		name = (String) table.getValueAt(row, 0);
		page = getPage();
		isPushed = true;
		return button;
	}

	private Page getPage() {
		for (int i = 0; i < Table.data.length; i++) {
			if (name.equals(Table.data[i][0])) {
				return Table.rp.nodes.get(i);
			}
		}
		return null;
	}

	public Object getCellEditorValue() {
		if (isPushed) {
			//
			//
			new UserInputWindow(page);
			System.out.println(page.getPathName());
			// System.out.println(label + ": Ouch!");
		}
		isPushed = false;
		return new String(label);
	}

	public boolean stopCellEditing() {
		isPushed = false;
		return super.stopCellEditing();
	}

	protected void fireEditingStopped() {
		super.fireEditingStopped();
	}
}