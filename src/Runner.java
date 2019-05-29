import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Runner extends JFrame {

	private String selectedFlavor;
	private String selectedTopping;
	private ArrayList<IceCream> cartList;
	private Menu myMenu;
	private Boolean discountApplied = false;

	public Runner() {
		super("Yu Lung Ice Cream Main Menu");
		setVisible(true);
		setSize(400, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		JLabel welcomeLabel = new JLabel("Welcome! Please choose below what you want to do:");
		JPanel middlePanel = new JPanel();

		// Create "New Member Register" button and corresponding action listener
		JButton registeyourCartButtonutton = new JButton("New Member Register");
		registeyourCartButtonutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new MembershipForm();
			}
		});

		// Create "Order" button and corresponding action listener
		JButton ordeyourCartButtonutton = new JButton("Order");
		ordeyourCartButtonutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				myMenu = new Menu();
			}
		});

		// Create "View Your Cart" button and corresponding action listener
		JButton viewYourCartButton = new JButton("View Your Cart");
		viewYourCartButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Cart myCart = new Cart(myMenu.getCartList());
				}
				// if NullPointerException, that means myMenu was not initialized and order
				// button was not pressed and cart must be empty
				catch (NullPointerException e2) {
					new PopUpInfo("You cart is empty");
				}
			}
		});

		middlePanel.add(registeyourCartButtonutton, BorderLayout.WEST);
		middlePanel.add(ordeyourCartButtonutton, BorderLayout.CENTER);
		middlePanel.add(viewYourCartButton, BorderLayout.EAST);

		JButton closeButton = new JButton("EXIT");
		closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		add(welcomeLabel, BorderLayout.NORTH);
		add(middlePanel, BorderLayout.CENTER);
		add(closeButton, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		Runner r = new Runner();
	}
}
