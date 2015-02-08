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

	private String[] columnNames = {"Page Name (Click to see links)", "Page Tag"};
	
	public static Links current;
	public static String lastName;
	private Table frame;
	private JPanel panel;
	private JLabel instructions;
	private JScrollPane table;
	private JTable info;
	private RecursiveParser rp;
	private JButton goBack;

	// private static JTextArea area;
	// private static JScrollPane pane;

	public Links(String name, String previous){
		super("Links");
		current = this;
		setBounds(200, 200, 800, 200);
		setLayout(new GridBagLayout());
		
		// Initialize fields
		lastName = previous;
		goBack = new JButton("Previous Page");
		goBack.addActionListener(this);
		instructions = new JLabel("No links found in " + name + ".");

		GridBagConstraints c = new GridBagConstraints();
		
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
		c.gridx = 1;
		c.gridy = 0;
		getContentPane().add(goBack, c);
		// panel.add(letsCrawlButton);

		// Add items to frame
		// Container con = this.getContentPane();
		// con.add(panel);
		// con.add(letsCrawlButton);
		setVisible(true);
	}
	
	public Links(String[][] data, String name) {
		super("Links");
		current = this;
		setBounds(200, 200, 800, 200);
		setLayout(new GridBagLayout());
		
		// Initialize fields
		lastName = name;
		panel = new JPanel();
		instructions = new JLabel("Links from " + name + ":");
		info = new JTable(data, columnNames);
		table = new JScrollPane(info);

		GridBagConstraints c = new GridBagConstraints();
		DefaultTableModel dm = new DefaultTableModel();
		dm.setDataVector(data, columnNames);

		
		JTable table = new JTable(dm);
		
		table.getColumn("Page Name (Click to see links)").setCellRenderer(new ButtonRenderer());
		table.getColumn("Page Name (Click to see links)").setCellEditor(new ButtonEditorLinks(new JCheckBox()));
		
		table.getColumnModel().getColumn(0).setPreferredWidth(300);
		
		table.setRowHeight(30);
		
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
		if(e.getSource() == goBack){
			System.out.println("HI");
			String[] data = Links.getLinkedData(lastName);
			System.out.println(data);
			int length = 0;
			for(int i = 0; i < data.length; i++){
				System.out.println(Table.data[i][0] + "\t" + data[i]);
				if(!data[i].equals("")){
					length++;
				}
			}
			String[][] linkTable = new String[length][2];
			int count = 0;
			for(int i = 0; i < data.length; i++){
				if(data[i].equals(""))
					continue;
				linkTable[count][0] = (String)Table.data[i][0];
				linkTable[count][1] = data[i];
				count++;
			}
			if(Links.current != null)
				Links.current.dispose();
			new Links(linkTable, lastName);
		}
	}
	
	public static String[] getLinkedData(String name){
		for(int i = 0; i < Table.data.length; i++){
			if(Table.data[i][0].equals(name)){
				return Table.tagsGraph[i];
			}
		}
		return null;
	}

	/*
	public static void main(String[] args) {
		new Table();
	}
	*/
	

}
