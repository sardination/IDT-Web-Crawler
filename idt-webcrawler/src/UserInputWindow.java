import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UserInputWindow extends JFrame implements ActionListener {
	ArrayList<Input> instructions = new ArrayList<Input>();
	//ArrayList<String> input = new ArrayList<String>();
	JPanel wholeWindow;
	JButton doneButton;

	public UserInputWindow(Page page) {
		super("Input Fields");
		setBounds(0, 0, 500, 600);
		setLayout(new GridBagLayout());
		
		//Initialize fields
		doneButton = new JButton("Test These Input Values");
		doneButton.addActionListener(this);
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
		int i = 0;
		for(i = 0; i < instructions.size(); i++) {
			c.gridy = i;
			if(instructions.get(i).getType().equals("text") || instructions.get(i).getType().equals("password")) {
				JLabel label = new JLabel(instructions.get(i).getValue());
				JTextField field = new JTextField(20);
				System.out.println(label.getText());
				c.gridwidth = 1;
				panel.add(label, c);
				
				c.gridx = 1;
				panel.add(field, c);
				
				c.gridwidth = 2;
				c.gridx = 0;
				//wholeWindow.add(panel);
				//panel = null;
			} else if(instructions.get(i).getType().equals("checkbox")) {
				JCheckBox checkbox = new JCheckBox(instructions.get(i).getValue());
				panel = new JPanel();
				panel.add(checkbox, c);
				//wholeWindow.add(panel);
				//panel = null;
			} else if (instructions.get(i).getType().equals("submit") || instructions.get(i).getType().equals("button")) {
				JCheckBox checkbox = new JCheckBox("Click "+instructions.get(i).getValue()+" button.");
				//panel = new JPanel();
				panel.add(checkbox, c);
				//wholeWindow.add(panel);
			} else if (instructions.get(i).getType().equals("radio")) {
				JRadioButton radio = new JRadioButton(instructions.get(i).getValue());
				//panel = new JPanel();
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
		wholeWindow.add(doneButton, c);
		
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
		if(arg0.getSource() == doneButton)
			this.dispose();
		
	}

}
