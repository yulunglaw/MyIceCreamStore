import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Menu extends JFrame {

	private String selectedFlavor;
	private String selectedTopping;
	private double flavorPrice = 0;
	private double toppingPrice = 0;
	private ArrayList<IceCream> cartList;

	/**
	 * Constructor to create the menu JFrame
	 */
	public Menu() {
		super("Main Menu");
		setVisible(true);
		setSize(400, 400);
		setLayout(new BorderLayout());

		PriceMapping pm = new PriceMapping();
		cartList = new ArrayList<>();

		/**
		 * Upper Panel Section
		 */
		JPanel flavorPanel = new JPanel();
		JLabel flavorLabel = new JLabel("Flavors:");
		flavorPanel.add(flavorLabel, BorderLayout.NORTH);
		ButtonGroup flavorButtonGroup = new ButtonGroup();
		// Group flavor into one group (only one flavor can be selected per group)
		for (String flavor : pm.getFlavorMapping().keySet()) {
			JRadioButton rb = new JRadioButton(flavor);
			flavorButtonGroup.add(rb);
			flavorPanel.add(rb, BorderLayout.SOUTH);
		}

		/**
		 * Center Panel Section
		 */
		JPanel toppingPanel = new JPanel();
		JLabel toppingLabel = new JLabel("Toppings:");
		toppingPanel.add(toppingLabel, BorderLayout.NORTH);
		ButtonGroup toppingButtonGroup = new ButtonGroup();
		// Group topping into one group (only one flavor can be selected per group)
		for (String topping : pm.getToppingMapping().keySet()) {
			JRadioButton rb = new JRadioButton(topping);
			toppingButtonGroup.add(rb);
			toppingPanel.add(rb, BorderLayout.SOUTH);
		}
		add(flavorPanel, BorderLayout.NORTH);
		add(toppingPanel, BorderLayout.CENTER);

		/**
		 * Bottom Panel Section
		 */
		JPanel BottomPanel = new JPanel();
		// Create a JButton to add selection into the cart
		JButton addToCartButton = new JButton("Add To Cart");
		addToCartButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// React to the selection of chosen flavor
				for (Enumeration<AbstractButton> buttons = flavorButtonGroup.getElements(); buttons
						.hasMoreElements();) {
					AbstractButton button = buttons.nextElement();

					if (button.isSelected()) {
						selectedFlavor = button.getText();
						flavorPrice = pm.getFlavorMapping().get(selectedFlavor);
					}
				}
				// React to the selection of chosen topping
				for (Enumeration<AbstractButton> buttons = toppingButtonGroup.getElements(); buttons
						.hasMoreElements();) {
					AbstractButton button = buttons.nextElement();

					if (button.isSelected()) {
						selectedTopping = button.getText();
						toppingPrice = pm.getToppingMapping().get(selectedTopping);
					}
				}

				IceCream ic = new IceCream(selectedFlavor, flavorPrice, selectedTopping, toppingPrice);
				cartList.add(ic);
				new PopUpInfo("Your item succussfully added to your cart.");
			}
		});
		BottomPanel.add(addToCartButton, BorderLayout.NORTH);

		JButton finishButton = new JButton("Finish Order");
		finishButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		BottomPanel.add(finishButton, BorderLayout.CENTER);

		add(BottomPanel, BorderLayout.SOUTH);
	}

	public ArrayList<IceCream> getCartList() {
		return cartList;
	}
}
