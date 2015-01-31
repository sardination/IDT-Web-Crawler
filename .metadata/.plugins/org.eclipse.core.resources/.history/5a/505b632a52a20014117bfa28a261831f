import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * This class will be a window that prompts the user for input fields.
 */
public class WebCrawler extends Frame implements ActionListener, WindowListener {
	
	public Panel insertURLPanel; //Container that has input and instructions for entering URL
	public Label insertURLLabel; //Instructs users to enter the URL
	public TextField insertURLField; //Field where users enter the URL
	
	//If there is any other input we need to take, add here
	
	public Button letsCrawlButton; //Once user is done, press this button to start crawling the website entered
	
	public WebCrawler () {
		super("Web Crawler Application");
		setLayout(new FlowLayout());
		setSize(400, 200);
		
		//Initialize fields
		insertURLPanel = new Panel();
		insertURLLabel = new Label("Insert URL:");
		insertURLField = new TextField("", 30);
		letsCrawlButton = new Button("Crawl This Website");
		
		//Add items to JPanel
		insertURLPanel.add(insertURLLabel);
		insertURLPanel.add(insertURLField);
		
		//Add items to frame
		add(insertURLPanel);
		add(letsCrawlButton);
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
	
	/**
	 * Close the window when close button is pressed.
	 */
	public void windowClosing (WindowEvent we) {
		System.exit(0);
	}

	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowDeactivated(WindowEvent arg0){}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}

}
