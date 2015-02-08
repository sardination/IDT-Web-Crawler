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

public class Table extends JFrame implements ActionListener {
	
	private final JFileChooser fc = new JFileChooser();
	private final String[] columnNames = { "Page Name", "HTTP Response Code", "Pass/Fail", "Page"};
	private Object[][] data = new Object[0][0];
	private static String[][] tagsGraph = new String[0][0];

	private Table frame;
	private JPanel panel;
	private JLabel instructions;
	private JScrollPane table;
	private JTable info;
	private JButton saveFile;
	private RecursiveParser rp;

	// private static JTextArea area;
	// private static JScrollPane pane;

	public Table(RecursiveParser rp) {
		super("Web Crawler Application");
		setBounds(100, 100, 400, 200);
		setLayout(new GridBagLayout());

		this.rp = rp;
		listsToArray();
		
		// Initialize fields
		panel = new JPanel();
		instructions = new JLabel("Insert URL:");
		info = new JTable(data, columnNames);
		table = new JScrollPane(info);
		saveFile = new JButton("Save Version Report");
		saveFile.addActionListener(this);
		
		//file chooser only accepts certain files
		fc.setFileFilter(new FileFilter() {
			@Override
			public boolean accept(File f) {
				if (f.isDirectory()) {
					return true;
				}
				final String name = f.getName();
				return name.endsWith(".csv");
			}

			@Override
			public String getDescription() {
				return "*.csv";
			}
		});

		GridBagConstraints c = new GridBagConstraints();
		DefaultTableModel dm = new DefaultTableModel();
		dm.setDataVector(data, columnNames);

		
		JTable table = new JTable(dm);
		table.getColumn("Page").setCellRenderer(new ButtonRenderer());
		table.getColumn("Page").setCellEditor(new ButtonEditor(new JCheckBox()));
		
		table.getColumn("Page Name").setCellRenderer(new ButtonRenderer());
		table.getColumn("Page Name").setCellEditor(new ButtonEditorLinks(new JCheckBox()));
		
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
		c.weightx = .5;
		c.gridx = 0;
		c.gridy = 0;
		getContentPane().add(instructions, c);

		c.weightx = .5;
		c.gridx = 1;
		c.gridy = 0;
		getContentPane().add(saveFile, c);

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 2;
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
   
	public void listsToArray() {
		data = new Object[rp.nodes.size()][4];
		for (int i=0; i<rp.nodes.size(); i++) {
			//data stuff
			data[i][0] = rp.nodes.get(i).getPathName();
			data[i][1] = rp.nodes.get(i).getResponseCode();
			if (String.valueOf(rp.nodes.get(i).getResponseCode()).substring(0,1).equals("4") 
					|| String.valueOf(rp.nodes.get(i).getResponseCode()).substring(0,1).equals("5")) {
				data[i][2] = "Fail";
			} else {
				data[i][2] = "Pass";
			}
			data[i][3] = "Value Testing";
		}
		
		tagsGraph = new String[rp.linkTexts.size()][rp.linkTexts.size()];
		
		for (int i=0; i<rp.linkTexts.size(); i++) {
			for (int j=0; j<rp.linkTexts.size(); j++) {
				tagsGraph[i][j] = rp.linkTexts.get(i).get(j);
			}
		}
		
	}
	
	public void changePageStatus (String pageName, String newError) {
    	int index = -1;
    	
    	for(int i = 0; i < data.length; i++) {
    		if (data[i][1].equals(pageName)) {index = i;}
    	}
    	
    	if(index != -1) {
    		data[index][2] = newError;
    	}
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == saveFile) {
			int returnVal = fc.showSaveDialog(Table.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				String export = "";
				//export +=
			}
		}
	}

	/*
	public static void main(String[] args) {
		new Table();
	}
	*/
	

}
