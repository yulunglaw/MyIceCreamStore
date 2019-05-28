import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MembershipForm extends JFrame {

	private int membershipID;
	private String name;
	private String password;
	private JLabel label;
	private JPanel bottomPanel;
	private JPanel inputPanel;

	/**
	 * Constructor
	 */
	public MembershipForm() {
		super("Membership Registration");
		setVisible(true);
		setSize(700, 400);
		setLayout(new BorderLayout());

		JLabel welcomeLabel = new JLabel("Hello, welcome to our membership Registration form!");
		add(welcomeLabel, BorderLayout.NORTH);

		inputPanel = new JPanel();
		JLabel inputName = new JLabel("Please enter your name: ");
		JTextField tf1 = new JTextField(20);
		JLabel inputPassword = new JLabel("Please enter your password: ");
		JTextField tf2 = new JTextField(20);
		JButton enterButton = new JButton("ENTER");

		inputPanel.setLayout(new GridLayout(4, 0));
		inputPanel.add(inputName);
		inputPanel.add(tf1);
		inputPanel.add(inputPassword);
		inputPanel.add(tf2);
		add(inputPanel, BorderLayout.CENTER);

		bottomPanel = new JPanel();
		bottomPanel.add(enterButton);
		add(bottomPanel, BorderLayout.SOUTH);

		// Add action listener to the JButton
		enterButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MembershipDatabase md = new MembershipDatabase();
				name = tf1.getText();
				password = tf2.getText();
				membershipID = md.nextAvailableID();
				md.saveToDatabase(name, password, membershipID);
				new PopUpInfo(md.getLabel().getText());
				// if successfully registered, close the membership form
				if (md.isSuccessfullyregistered()) {
					dispose();
				}
			}
		});
	}
}
