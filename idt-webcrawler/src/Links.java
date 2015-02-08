import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

public class Links extends JFrame implements ActionListener {

	private String[] columnNames = {"Page Name", "Page Tag"};
	
	public static Links current;
	private Table frame;
	private JPanel panel;
	private JLabel instructions;
	private JScrollPane table;
	private JTable info;
	private RecursiveParser rp;

	// private static JTextArea area;
	// private static JScrollPane pane;

	public Links(String[][] data, String name) {
		super("Links");
		current = this;
		setBounds(100, 100, 800, 200);
		setLayout(new GridBagLayout());
		
		// Initialize fields
		panel = new JPanel();
		instructions = new JLabel("Links from " + name + ":");
		info = new JTable(data, columnNames);
		table = new JScrollPane(info);

		GridBagConstraints c = new GridBagConstraints();
		DefaultTableModel dm = new DefaultTableModel();
		dm.setDataVector(data, columnNames);

		
		JTable table = new JTable(dm);
		
		table.getColumn("Page Name").setCellRenderer(new ButtonRenderer());
		table.getColumn("Page Name").setCellEditor(new ButtonEditorLinks(new JCheckBox()));
		
		table.getColumnModel().getColumn(0).setPreferredWidth(300);
		
		//make everything unclickable except for buttons
		for (int i = 0; i < table.getColumnCount(); i++)
		{
			if(i == table.getColumnCount() - 1)
				continue;
		    Class<?> col_class = table.getColumnClass(i);
		    table.setDefaultEditor(col_class, null);        // remove editor
		}
		JScrollPane scroll = new JScrollPane(table);

		
		
		// Add items to JPanel
		// panel.add(insertURLLabel);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		getContentPane().add(instructions, c);

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		getContentPane().add(scroll, c);
		// panel.add(letsCrawlButton);

		// Add items to frame
		// Container con = this.getContentPane();
		// con.add(panel);
		// con.add(letsCrawlButton);
		setVisible(true);

		// Add ActionListener
		// letsCrawlButton.addActionListener(this);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	/*
	public static void main(String[] args) {
		new Table();
	}
	*/
	

}
