import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Table extends JFrame {

	private final String[] columnNames = { "Page Name", "Error Code", "Pass/Fail", "Page"};
	private Object[][] data = { { "Calculator", "False", new Button("TEST") },
			{ "Page2", "True", "WTF", "ButtonTest" }, { "SDGIOSDN", "Im", "insane", "ButtonTest" },
			{ "Calculator", "False", "Maybe?", "ButtonTest" },
			{ "Calculator", "False", "Maybe?", "ButtonTest" },
			{ "Calculator", "False", "Maybe?", "ButtonTest" },
			{ "Calculator", "False", "Maybe?", "ButtonTest" },
			{ "Calculator", "False", "Maybe?", "ButtonTest" },
			{ "Calculator", "False", "Maybe?", "ButtonTest" },
			{ "Calculator", "False", "Maybe?", "ButtonTest" },
			{ "Calculator", "False", "Maybe?", "ButtonTest" },
			{ "Calculator", "False", "Maybe?", "ButtonTest" },
			{ "Calculator", "False", "Maybe?", "ButtonTest" } };

	private Table frame;
	private JPanel panel;
	private JLabel instructions;
	private JScrollPane table;
	private JTable info;

	// private static JTextArea area;
	// private static JScrollPane pane;

	public Table() {
		super("Web Crawler Application");
		setBounds(100, 100, 400, 200);
		setLayout(new GridBagLayout());

		// Initialize fields
		panel = new JPanel();
		instructions = new JLabel("Insert URL:");
		info = new JTable(data, columnNames);
		table = new JScrollPane(info);

		GridBagConstraints c = new GridBagConstraints();
		DefaultTableModel dm = new DefaultTableModel();
		dm.setDataVector(data, columnNames);

		
		JTable table = new JTable(dm);
		table.getColumn("Page").setCellRenderer(new ButtonRenderer());
		table.getColumn("Page").setCellEditor(
				new ButtonEditor(new JCheckBox()));
		
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

    /**
     * Changes the status of a page.
     * @param pageName - name of page whose status was changed
     * @param newStatus - the new status of that page
     */
    public void changePageStatus (String pageName, String newError) {
    	int index = -1;
    	
    	for(int i = 0; i < data.length; i++) {
    		if (data[i][1].equals(pageName)) {index = i;}
    	}
    	
    	if(index != -1) {
    		data[index][2] = newError;
    	}
    }

	public static void main(String[] args) {
		new Table();
	}

}
