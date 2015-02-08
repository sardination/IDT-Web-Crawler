import javax.swing.*;

import java.awt.*;
import java.awt.Container;
import java.awt.event.*;
import java.util.ArrayList;

public class UserInputWindow extends JFrame implements ActionListener {
	ArrayList<Input> instructions = new ArrayList<Input>();
	//ArrayList<String> input = new ArrayList<String>();
	JPanel wholeWindow;
	JButton doneButton;

	public UserInputWindow() {
		super("Input Fields");
		setBounds(0, 0, 500, 600);
		
		//Initialize fields
		doneButton = new JButton("Done!");
		wholeWindow = new JPanel();
		wholeWindow.setLayout(new BoxLayout(wholeWindow, BoxLayout.Y_AXIS));
		
		//TODO Get user input fields here
		Input input1 = new Input("String", "Poke", "D:");
		Input input2 = new Input("Checkbox", "100", "?");
		instructions.add(input1);
		instructions.add(input2);
		
		JPanel panel = new JPanel();
		for(int i = 0; i < instructions.size(); i++) {
			if(instructions.get(i).getType() == "String") {
				JLabel label = new JLabel(instructions.get(i).getValue());
				JTextField field = new JTextField(20);
				panel.add(label); 
				panel.add(field);
				wholeWindow.add(panel);
				panel = null;
			} else if(instructions.get(i).getType() == "Checkbox") {
				JCheckBox checkbox = new JCheckBox(instructions.get(i).getValue());
				panel = new JPanel();
				panel.add(checkbox);
				wholeWindow.add(panel);
				panel = null;
			}
		}
		
		wholeWindow.add(doneButton);
		
		Container con = this.getContentPane();
		con.add(wholeWindow);
		setVisible(true);
		
		doneButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		for(int i = 0; i < instructions.size(); i++) {
			if(instructions.get(i).getType() == "String") {
				
			} else if(instructions.get(i).getType() == "Checkbox") {
				
			}
		}
		
	}
	
	public static void main(String[] args) {
		new UserInputWindow();
	}

}
