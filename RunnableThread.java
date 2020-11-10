package hw3;
/*
 * Cody Claxton
 * HW3
 * Sources:  Stackoverflow on how to read characters & words from a file
 */

import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javax.swing.JOptionPane;

public class RunnableThread implements Runnable {
	Thread t;
	String ourFile;

	/*
	 * @param is the filename we are looking for
	 */
	public RunnableThread(String files) {
		this.t = new Thread(this);
		this.ourFile = files;

	}

	/*
	 * Gets our files and calls our getWords method to count them Creates dialog
	 * boxes for each file that was passed into our thread
	 */
	@Override
	public void run() {
		int wordCount = 0;
		int letterCount = 0;
		String fileContents = "";

		if (ourFile.equals("exit")) {
			System.exit(0);
		} else {
			File file = new File(ourFile);
			try {
				Scanner inputFile = new Scanner(file);
				if (file.exists()) {
					wordCount = getWords(file);
					letterCount = getLetters(file);
					fileContents = getContents(file);
					String wordsString = file + " has " + wordCount + " words";
					String lettersString = file + " has " + letterCount + " letters";

					// Creating our dialog box
					JOptionPane.showMessageDialog(null, wordsString + "\n" + lettersString + "\n" + fileContents,
							file.toString(), JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "File does not exist", file.toString(),
						JOptionPane.INFORMATION_MESSAGE);
			} catch (NullPointerException e) {
				String zeroWords = ourFile + " has 0 words";
				String zeroLetters = ourFile + " has 0 letters";
				JOptionPane.showMessageDialog(null, zeroWords + "\n" + zeroLetters, file.toString(),
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	/*
	 * Counting the words in a given file param - The file that we are looking
	 */
	public int getWords(File f) throws FileNotFoundException {
		int count = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(f))) {
			String line = br.readLine();
			line = line.trim().replaceAll("\\s+", " ");// Replace with only one
														// space if multiple
														// spaces

			while (line != null) {
				String[] parts = line.split(" ");// Splitting our words with a
													// space
				for (String w : parts) {
					count++;
				}
				line = br.readLine();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Could not find file " + f.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return count;
	}

	/*
	 * Counting how many letters are in a given file
	 */
	public int getLetters(File f) throws FileNotFoundException {
		int letterCount = 0;
		Scanner scanner = new Scanner(f);
		while (scanner.hasNext()) {
			char[] chars = scanner.nextLine().toLowerCase().toCharArray();// Building
																			// a
																			// character
																			// array
																			// from
																			// our
																			// line
			for (Character c : chars) {
				if (!Character.isLetter(c)) {
					continue;// Do nothing if it is not a letter
				} else {
					letterCount = letterCount + 1;
				}
			}
		}
		return letterCount;
	}

	/*
	 * Getting our original contents from our file
	 * @param - The file we are looking in
	 */
	public String getContents(File f) throws FileNotFoundException {
		String contents = "";
		try (BufferedReader br = new BufferedReader(new FileReader(f))) {

			String line = br.readLine();
			while (line != null) {
				contents += line + "\n";
				line = br.readLine();
			}

		} catch (FileNotFoundException e) {
			System.out.println("Could not find file " + f.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contents;

	}
}
