import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class About extends JInternalFrame implements ActionListener {
	JLabel label, title, label1, label2;
	JButton okButton;

	public About() {

		//super("Document #" + (++openFrameCount),resizable,closable,maximizable,iconifiable);
		super("About us...", false, true, false, false);
		JDesktopPane jf = new JDesktopPane();
		getContentPane().add(jf, BorderLayout.CENTER);
		setSize(500, 160);
		setLocation(200, 200);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		title = new JLabel("Shop Management System");
		title.setFont(new Font("Ariel", Font.BOLD, 25));
		title.setForeground(Color.orange);
		title.setBounds(50, 2, 420, 50);
		jf.add(title);

		label = new JLabel("Copyright ( c ) 20016 ");
		label.setBounds(150, 50, 400, 20);
		jf.add(label);

		label1 = new JLabel("Developed By Vishnu S. Katpure ");
		label1.setBounds(100, 80, 400, 20);
		jf.add(label1);

	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == okButton)
			setVisible(false);

	}
}