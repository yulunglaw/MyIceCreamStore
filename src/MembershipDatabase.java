import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JLabel;

public class MembershipDatabase {

	private int nextID = 1001;
	private File f = new File("MembershipDatabase.csv");
	private FileWriter fw;
	private ArrayList<String> memberNameList;
	private JLabel label;
	private HashMap<String, String> credentialMap = new HashMap<String, String>();
	private boolean successfullyregistered = false;

	/**
	 * Constructor. This constructor will create a new csv file/database if it is
	 * not already existed
	 */
	public MembershipDatabase() {
		if (!f.exists()) {
			try {
				FileWriter fw = new FileWriter(f);
				fw.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * This function will return the next available memberID
	 * 
	 * @return
	 */
	public int nextAvailableID() {
		try {
			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				s.nextLine();
				nextID++;
			}
			s.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nextID;
	}

	/**
	 * This method will save member name, password, and ID into the csv database
	 * file.
	 * 
	 * @param name
	 * @param membershipID
	 */
	public void saveToDatabase(String name, String password, int membershipID) {
		try {
			Scanner s = new Scanner(f);
			memberNameList = new ArrayList<>();
			// Retrieve all members' name from the database and check if input name has
			// already been used
			while (s.hasNextLine()) {
				String memberName = s.nextLine().split(",")[0];
				memberNameList.add(memberName);
			}
			if (memberNameList.contains(name)) {
				label = new JLabel(
						"Sorry, looks like you are already a memeber of us since this name is already registered.");
			} else {
				// Check if both name and password were inputed.
				if (!name.isBlank() && !password.isBlank()) {
					try {
						FileWriter fw = new FileWriter(f, true);
						PrintWriter pw = new PrintWriter(fw);
						pw.print(name + ",");
						pw.print(password + ",");
						pw.print(membershipID);
						pw.println();
						pw.flush();
						label = new JLabel("Hi " + name
								+ ", Congratulations! You have successfully registered as our member! Your membership ID is : "
								+ membershipID);
						successfullyregistered = true;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					label = new JLabel("Both name and password need to be entered, please try again");
				}
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * This function will read from the database file and return and store all the
	 * names and password combo as a HashMap
	 * 
	 * @return
	 */
	public HashMap<String, String> credentialCheck() {
		try {
			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				String data = s.nextLine();
				String[] crendtialData = data.split(",");
				String name = crendtialData[0];
				String password = crendtialData[1];
				credentialMap.put(name, password);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return credentialMap;
	}

	public JLabel getLabel() {
		return label;
	}

	public boolean isSuccessfullyregistered() {
		return successfullyregistered;
	}
}
