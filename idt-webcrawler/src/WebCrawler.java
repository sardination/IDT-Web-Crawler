import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class will be a window that prompts the user for base URL.
 */
public class WebCrawler extends JFrame implements ActionListener {
	
	private JPanel insertURLPanel; //Container that has input and instructions for entering URL
	private JLabel insertURLLabel; //Instructs users to enter the URL
	private JTextField insertURLField; //Field where users enter the URL
	
	private JButton letsCrawlButton; //Once user is done, press this button to start crawling the website entered
	
	public WebCrawler () {
		super("Web Crawler Application");
		setBounds(100, 100, 400, 200);
		
		//Initialize fields
		insertURLPanel = new JPanel();
		insertURLLabel = new JLabel("Insert URL:");
		insertURLField = new JTextField("", 30);
		letsCrawlButton = new JButton("Crawl This Website");
		
		//Add items to JPanel
		insertURLPanel.add(insertURLLabel);
		insertURLPanel.add(insertURLField);
		
		//Add items to frame
		Container con = this.getContentPane();
		con.add(insertURLPanel);
		con.add(letsCrawlButton);
		setVisible(true);
		
		//Add ActionListener
		letsCrawlButton.addActionListener(this);
	}

	public static void main(String[] args) {
		new WebCrawler();
	}

	/**
	 * Listens for "Crawl" button click
	 */
	public void actionPerformed(ActionEvent ae) {
		//TODO Crawl the website
	}

}
