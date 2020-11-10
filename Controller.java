package hw3;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.*;

/*
 * Cody Claxton
 * HW3
 * Sources:  Stackoverflow on how to read characters & words from a file
 */
public class Controller extends JFrame{
	private JLabel fileLabel;
	private JTextArea fileNames;
	private JButton submit;
	
	public Controller(){
		super("HW3");
		setLayout(new FlowLayout());
		
		fileNames = new JTextArea(10,20);
		add(fileNames);
		
		fileLabel = new JLabel("Please input your file names");
		add(fileLabel);

		submit = new JButton("Submit");
		add(submit);
		
		Event e = new Event();
		submit.addActionListener(e);
	}
	public class Event implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String files = getTextArea();
			if(files == ""){
				fileLabel.setText("Please enter in a file");
			}
			else{
				//Passing each line to our thread
				for (String line : fileNames.getText().split("\\n")){
					RunnableThread rt = new RunnableThread(line);
					new Thread(rt).start();;
				}
			}
		}
		/*
		 * Getting all of the lines from our textarea
		 */
		public String getTextArea(){
			String files = fileNames.getText();
			return files;
		}
		
	}
	
}
	

