import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class ButtonEditorLinks extends DefaultCellEditor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JButton button;
	private String label;
	private boolean isPushed;

	public ButtonEditorLinks(JCheckBox checkBox) {
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
		isPushed = true;
		return button;
	}

	public Object getCellEditorValue() {
		if (isPushed) {
			//
			//
			String[] data = Links.getLinkedData(label);
			System.out.println(data);
			int length = 0;
			for (int i = 0; i < data.length; i++) {
				System.out.println(Table.data[i][0] + "\t" + data[i]);
				if (!data[i].equals("")) {
					length++;
				}
			}
			String[][] linkTable = new String[length][2];
			int count = 0;
			for (int i = 0; i < data.length; i++) {
				if (data[i].equals(""))
					continue;
				linkTable[count][0] = (String) Table.data[i][0];
				linkTable[count][1] = data[i];
				count++;
			}
			if (length != 0) {
				if (Links.current != null)
					Links.current.dispose();
				new Links(linkTable, label);
			}else{
				if (Links.current != null)
					Links.current.dispose();
				new Links(label, Links.lastName);
			}
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