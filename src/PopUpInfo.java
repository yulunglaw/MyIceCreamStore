import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * This Class will create a pop up JFrame when created.
 * 
 * @author yulaw
 *
 */
public class PopUpInfo extends JFrame {

	/**
	 * This constructor will take a string as an parameter. this string parameter
	 * will be the message shows in the pop up JFrame
	 * 
	 * @param message
	 */
	public PopUpInfo(String message) {
		setVisible(true);
		setSize(300, 300);

		JLabel testlabel = new JLabel(message);
		add(testlabel, BorderLayout.NORTH);
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		add(closeButton, BorderLayout.SOUTH);
	}
}
