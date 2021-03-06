import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;

/**
 * This class will be a window that prompts the user for base URL.
 */
public class WebCrawler extends JFrame implements ActionListener {
	
	private JPanel insertURLPanel; //Container that has input and instructions for entering URL
	private JLabel insertURLLabel; //Instructs users to enter the URL
	private JTextField insertURLField; //Field where users enter the URL
	private JPanel wholeWindow;
	
	private JButton letsCrawlButton; //Once user is done, press this button to start crawling the website entered
	
	public WebCrawler () {
		super("Web Crawler Application");
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		
		setBounds(100, 100, 550, 200);
		
		//Initialize fields
		insertURLPanel = new JPanel();
		wholeWindow = new JPanel();
		insertURLLabel = new JLabel("Insert URL:");
		insertURLField = new JTextField("", 30);
		letsCrawlButton = new JButton("Crawl This Website");
		
		//Add items to JPanels
		insertURLPanel.add(insertURLLabel);
		insertURLPanel.add(insertURLField);
		wholeWindow.add(insertURLPanel);
		wholeWindow.add(letsCrawlButton);
		
		//Add items to frame
		Container con = this.getContentPane();
		con.add(wholeWindow);
		setVisible(true);
		
		//Add ActionListener
		letsCrawlButton.addActionListener(this);
	}

	
	
	public static void main(String[] args) {
		new WebCrawler();
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {

		}
	}

	/**
	 * Listens for "Crawl" button click
	 */
	public void actionPerformed(ActionEvent ae) {
		RecursiveParser parser = new RecursiveParser(insertURLField.getText());
		parser.parseNow();
		if (parser.nodes.size()!=0) {
			new Table(parser);
			this.dispose();
		}
	}

}
