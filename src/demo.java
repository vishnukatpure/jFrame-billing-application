import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;

@SuppressWarnings("serial")
public class demo extends JWindow {
	private Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

	@SuppressWarnings({ "deprecation", "unused" })
	public demo() throws Exception {

		JLabel lbImage = new JLabel(new ImageIcon("bin//Fit.jpg"));
		lbImage.setBorder(BorderFactory.createLineBorder(Color.blue, 0));

		getContentPane().add(lbImage, BorderLayout.CENTER);
		pack();
		setSize(getSize().width, getSize().height);
		setLocation(d.width / 2 - getWidth() / 2, d.height / 2 - getHeight() / 2);
		show();
		for (int i = 1; i <= 900000000; i++) {
		}
		for (int i = 1; i <= 900000000; i++) {
		}
		dispose();
		toFront();
		login l = new login();

	}

	@SuppressWarnings("unused")
	public static void main(String args[]) throws Exception {

		demo d = new demo();
	}
}
