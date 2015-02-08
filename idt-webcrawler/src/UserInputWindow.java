import javax.swing.*;
import javax.swing.text.JTextComponent;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UserInputWindow extends JFrame implements ActionListener {
	ArrayList<Input> instructions = new ArrayList<Input>();
	//ArrayList<String> input = new ArrayList<String>();
	JPanel wholeWindow;
	JButton correctButton;
	JButton testButton;
	String[] correct;
	ArrayList<Object> inputs;
	Page page;

	public UserInputWindow(Page page) {
		super("Correct Values");
		this.page = page;
		setBounds(0, 0, 500, 600);
		setLayout(new GridBagLayout());
		
		//Initialize fields
		correctButton = new JButton("Input Correct Values");
		correctButton.addActionListener(this);
		wholeWindow = new JPanel();
		wholeWindow.setLayout(new BoxLayout(wholeWindow, BoxLayout.Y_AXIS));
		instructions = page.getInputs();
		
		/*Input input1 = new Input("String", "Poke", "D:");
		Input input2 = new Input("Checkbox", "100", "?");
		instructions.add(input1);
		instructions.add(input2); */
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		
		//Create instances of input types (ex. create a text field to input text, etc.)
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		inputs = new ArrayList<Object>();
		
		int i = 0;
		for(i = 0; i < instructions.size(); i++) {
			c.gridy = i;
			if(instructions.get(i).getType().equals("text") || instructions.get(i).getType().equals("password")) {
				JLabel label = new JLabel(instructions.get(i).getValue());
				JTextField field = new JTextField(20);
				c.gridwidth = 1;
				panel.add(label, c);
				
				c.gridx = 1;
				panel.add(field, c);
				inputs.add(field);
				c.gridwidth = 2;
				c.gridx = 0;
				//wholeWindow.add(panel);
				//panel = null;
			} else if(instructions.get(i).getType().equals("checkbox")) {
				JCheckBox checkbox = new JCheckBox(instructions.get(i).getValue());
				panel = new JPanel();
				panel.add(checkbox, c);
				inputs.add(checkbox);
				//wholeWindow.add(panel);
				//panel = null;
			} else if (instructions.get(i).getType().equals("submit") || instructions.get(i).getType().equals("button")) {
				JCheckBox checkbox = new JCheckBox("Click "+instructions.get(i).getValue()+" button.");
				//panel = new JPanel();
				panel.add(checkbox, c);
				inputs.add(checkbox);
				//wholeWindow.add(panel);
			} else if (instructions.get(i).getType().equals("radio")) {
				JRadioButton radio = new JRadioButton(instructions.get(i).getValue());
				//panel = new JPanel();
				inputs.add(radio);
				panel.add(radio, c);
				//wholeWindow.add(panel);
				//panel = null;
			}
		}
		
		c.gridy = 0;
		JScrollPane pane = new JScrollPane(panel);
		wholeWindow.add(pane, c);
		//Showing the window and button.... prob. don't need to change this
		c.gridy = 1;
		wholeWindow.add(correctButton, c);
		
		Container con = this.getContentPane();
		con.add(wholeWindow);
		setVisible(true);
	}
	
	public UserInputWindow(Page page, String[] correct) {
		super("Test Values");
		setBounds(0, 0, 500, 600);
		setLayout(new GridBagLayout());
		
		//Initialize fields
		this.correct = correct;
		this.page = page;
		testButton = new JButton("Test Input Values");
		testButton.addActionListener(this);
		wholeWindow = new JPanel();
		wholeWindow.setLayout(new BoxLayout(wholeWindow, BoxLayout.Y_AXIS));
		instructions = page.getInputs();
		
		/*Input input1 = new Input("String", "Poke", "D:");
		Input input2 = new Input("Checkbox", "100", "?");
		instructions.add(input1);
		instructions.add(input2); */
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		
		//Create instances of input types (ex. create a text field to input text, etc.)
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		inputs = new ArrayList<Object>();
		
		int i = 0;
		for(i = 0; i < instructions.size(); i++) {
			c.gridy = i;
			if(instructions.get(i).getType().equals("text") || instructions.get(i).getType().equals("password")) {
				JLabel label = new JLabel(instructions.get(i).getValue());
				JTextField field = new JTextField(20);
				c.gridwidth = 1;
				panel.add(label, c);
				
				c.gridx = 1;
				panel.add(field, c);
				inputs.add(field);
				c.gridwidth = 2;
				c.gridx = 0;
				//wholeWindow.add(panel);
				//panel = null;
			} else if(instructions.get(i).getType().equals("checkbox")) {
				JCheckBox checkbox = new JCheckBox(instructions.get(i).getValue());
				panel = new JPanel();
				panel.add(checkbox, c);
				inputs.add(checkbox);
				//wholeWindow.add(panel);
				//panel = null;
			} else if (instructions.get(i).getType().equals("submit") || instructions.get(i).getType().equals("button")) {
				JCheckBox checkbox = new JCheckBox("Click "+instructions.get(i).getValue()+" button.");
				//panel = new JPanel();
				panel.add(checkbox, c);
				inputs.add(checkbox);
				//wholeWindow.add(panel);
			} else if (instructions.get(i).getType().equals("radio")) {
				JRadioButton radio = new JRadioButton(instructions.get(i).getValue());
				//panel = new JPanel();
				inputs.add(radio);
				panel.add(radio, c);
				//wholeWindow.add(panel);
				//panel = null;
			}
		}
		
		c.gridy = 0;
		JScrollPane pane = new JScrollPane(panel);
		wholeWindow.add(pane, c);
		//Showing the window and button.... prob. don't need to change this
		c.gridy = 1;
		wholeWindow.add(testButton, c);
		
		Container con = this.getContentPane();
		con.add(wholeWindow);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//get inputted info
		for(int i = 0; i < instructions.size(); i++) {
			if(instructions.get(i).getType() == "String") {
				
			} else if(instructions.get(i).getType() == "Checkbox") {
				
			}
		}
		if(arg0.getSource() == correctButton){
			String[] correctTemp = new String[inputs.size()];
			for(int i = 0; i < inputs.size(); i++){
				try{
					if(((AbstractButton) inputs.get(i)).isSelected()){
						correctTemp[i] = "true";
					}else
						correctTemp[i] = "false";
				}catch(Exception e){
					correctTemp[i] = (String)((JTextComponent) inputs.get(i)).getText();
				}
			}
			new UserInputWindow(this.page, correctTemp);
			this.dispose();
		}
		
		if(arg0.getSource() == testButton){
			System.out.println("HI");
			String[] testTemp = new String[inputs.size()];
			for(int i = 0; i < inputs.size(); i++){
				try{
					if(((AbstractButton) inputs.get(i)).isSelected()){
						testTemp[i] = "true";
					}else
						testTemp[i] = "false";
				}catch(Exception e){
					testTemp[i] = (String)((JTextComponent) inputs.get(i)).getText();
				}
			}
			boolean yesno = this.page.testInput(correct, testTemp);
			ArrayList<Input> test = this.page.getInputs();
			for(Input i : test)
				i.setPass(yesno);
		}
		
		
	}

}
