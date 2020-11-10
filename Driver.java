package hw3;
/*
 * Cody Claxton
 * HW3
 * Sources:  Stackoverflow on how to read characters & words from a file
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import javax.swing.JFrame;

public class Driver {

	public static void main(String[] args) {
		//new RunnableThread();
		Controller c = new Controller();
		c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setSize(400,300);
		c.setVisible(true);
	}

}

