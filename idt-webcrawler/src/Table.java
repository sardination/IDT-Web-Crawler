import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class Table extends JFrame{

	private final String[] columnNames = {"Page Name", "Error", "Yes"}; 
	private String[][] data = {{"Calculator", "False", "Maybe?"},{"Page2", "True", "WTF"},{"SDGIOSDN","Im","insane"},{"Calculator", "False", "Maybe?"},{"Calculator", "False", "Maybe?"},{"Calculator", "False", "Maybe?"},{"Calculator", "False", "Maybe?"},{"Calculator", "False", "Maybe?"},{"Calculator", "False", "Maybe?"},{"Calculator", "False", "Maybe?"},{"Calculator", "False", "Maybe?"},{"Calculator", "False", "Maybe?"},{"Calculator", "False", "Maybe?"}};
	
	private Table frame;
	private JPanel panel;
	private JLabel instructions;
	private JScrollPane table;
	private JTable info;
	//private static JTextArea area;
	//private static JScrollPane pane;
	
    public Table() {
		super("Web Crawler Application");
		setBounds(100, 100, 400, 200);
		setLayout(new BorderLayout());
		
		//Initialize fields
		panel = new JPanel();
		instructions = new JLabel("Insert URL:");
		info = new JTable(data, columnNames);
		table = new JScrollPane(info);
		
		//Add items to JPanel
		//panel.add(insertURLLabel);
		panel.add(instructions, BorderLayout.NORTH);
		panel.add(table, BorderLayout.CENTER);
		//panel.add(letsCrawlButton);
		
		//Add items to frame
		Container con = this.getContentPane();
		con.add(panel);
		//con.add(letsCrawlButton);
		setVisible(true);
		
		//Add ActionListener
		//letsCrawlButton.addActionListener(this);

        
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
    
	public static void main(String[] args){
		new Table();
	}
	
}
