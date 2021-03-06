import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Cart extends JFrame {

	private String itemDescription;
	private double itemPrice;
	private double finalPrice = 0;
	private JLabel finalPriceLabel;
	private JPanel bottomPanel;
	private String inputName;
	private String inputPassword;
	private boolean discountApplied = false;
	private StringBuilder usernameBuilder;
	private StringBuilder passwordBuilder;

	public Cart(ArrayList<IceCream> cartList) {
		super("View your Cart");
		setVisible(true);
		setSize(400, 400);

		JPanel itemPanel = new JPanel();
		itemPanel.setLayout(new GridLayout(10, 1));

		for (IceCream iceCream : cartList) {
			itemDescription = "Ice Cream: " + iceCream.getFlavor() + " &  " + iceCream.getTopping();
			itemPrice = iceCream.getTotalPrice();
			finalPrice += itemPrice;
			itemPanel.add(new JLabel(itemDescription + "     -     Price: $" + itemPrice));
		}
		add(itemPanel, BorderLayout.NORTH);

		// This Panel will be responsible for triggering membership login for 10%
		// discount
		JPanel middlePanel = new JPanel();
		JButton memberLogIn = new JButton("Membership Login");
		memberLogIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Check if discount already applied before
				if (discountApplied) {
					new PopUpInfo("Discount already applied.");
				} else {
				JFrame memberLogInForm = new JFrame();
				memberLogInForm.setVisible(true);
				memberLogInForm.setSize(300, 200);
				memberLogInForm.setResizable(false);
				memberLogInForm.setLayout(new FlowLayout());
				JPanel panel = new JPanel();
				panel.setLayout(new GridLayout(4, 0));

				// Ask to input name here
				JLabel label = new JLabel("Please enter your name here:");
				JTextField inputField = new JTextField(20);

				// add document listener to listen to what user type in as user name
				// we don't use actionListener as user must press "Enter" key to have username recongized
				inputField.getDocument().addDocumentListener(new DocumentListener() {
					
					@Override
					public void removeUpdate(DocumentEvent e) {
						usernameBuilder = new StringBuilder();
						usernameBuilder.append(inputField.getText());
					}
					
					@Override
					public void insertUpdate(DocumentEvent e) {
						usernameBuilder = new StringBuilder();
						usernameBuilder.append(inputField.getText());
					}
					
					@Override
					public void changedUpdate(DocumentEvent e) {
						// TODO Auto-generated method stub
						System.out.println("There's a update!!!");
					}
				});

				// Ask to input password here
				JLabel label2 = new JLabel("Please enter your password here:");
				JTextField inputField2 = new JTextField(20);
				inputField2.getDocument().addDocumentListener(new DocumentListener() {
					
					@Override
					public void removeUpdate(DocumentEvent e) {
						passwordBuilder = new StringBuilder();
						passwordBuilder.append(inputField2.getText());
					}
					
					@Override
					public void insertUpdate(DocumentEvent e) {
						passwordBuilder = new StringBuilder();
						passwordBuilder.append(inputField2.getText());
					}
					
					@Override
					public void changedUpdate(DocumentEvent e) {
						// TODO Auto-generated method stub
						System.out.println("There's a update!!!");
					}
				});

				/**
				 * This section will be responsible for the "log In" button. Once both name and
				 * password were entered, It will first check if the name already exists in the
				 * database. If not, will prompt the user the name is not a registered member.
				 * If name already exists, will check if password matches, if yes, 10% discount
				 * will applied to the cart. New discounted price will be shown in the cart.
				 */
				JButton logInButton = new JButton("Log In");
				logInButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						MembershipDatabase md = new MembershipDatabase();
						HashMap<String, String> credentialMap = md.credentialCheck();
						inputName = usernameBuilder.toString();
						inputPassword = passwordBuilder.toString();
						if (credentialMap.containsKey(inputName)) {
							try {
								if (inputPassword.equals(credentialMap.get(inputName))) {
									bottomPanel.remove(finalPriceLabel);
									finalPrice = finalPrice * 0.9;
									new PopUpInfo(
											"Welcome back, " + inputName + " we applied 10% off your total price!");
									memberLogInForm.dispose();
									finalPriceLabel = new JLabel("Member discounted Amount (10% off): $" + finalPrice);
									bottomPanel.add(finalPriceLabel);
									bottomPanel.revalidate();
									bottomPanel.repaint();
									discountApplied = true;
								} else {
									new PopUpInfo("Sorry, invalid password. Please try again");
								}
							} catch (NullPointerException e2) {
								new PopUpInfo("Please enter both your name and password");
							}
						} else {
							new PopUpInfo("Sorry, you are not a memeber of us yet.");
						}
					}
				});

				panel.add(label);
				panel.add(inputField);
				panel.add(label2);
				panel.add(inputField2);
				add(panel);
				memberLogInForm.add(panel, BorderLayout.NORTH);
				memberLogInForm.add(logInButton, BorderLayout.SOUTH);
				}
			}
		});
		middlePanel.add(memberLogIn);
		add(middlePanel);

		bottomPanel = new JPanel();
		finalPriceLabel = new JLabel("Total Amount: " + finalPrice);
		bottomPanel.add(finalPriceLabel);
		add(bottomPanel, BorderLayout.SOUTH);
	}
}
