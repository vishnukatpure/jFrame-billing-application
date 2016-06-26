import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import javax.swing.JRootPane;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class login extends JFrame implements ActionListener, KeyListener {
	JFrame jf;
	JLabel title, lid, lpass, lborder;
	public JTextField tid;
	public JPasswordField tpass;
	JButton login, exitb;

	Connection con = null;
	Statement st = null;
	ResultSet rs = null;

	public login() throws ClassNotFoundException, SQLException {

		jf = new JFrame();
		jf.setUndecorated(true);

		jf.getContentPane().setLayout(null);
		jf.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		connect c = new connect();
		con = c.getConnection(con);

		title = new JLabel("Login");
		title.setFont(new Font("comicsansms", Font.BOLD, 25));
		title.setBounds(120, 5, 220, 50);
		jf.getContentPane().add(title);

		lid = new JLabel("USER_ID");
		lid.setBounds(20, 100, 250, 20);

		tid = new JTextField(50);
		tid.setBounds(140, 100, 170, 20);
		jf.getContentPane().add(lid);
		jf.getContentPane().add(tid);

		lpass = new JLabel("PASSWORD");
		lpass.setBounds(20, 120, 300, 20);

		tpass = new JPasswordField(50);
		tpass.setBounds(140, 120, 170, 20);

		jf.getContentPane().add(lpass);
		jf.getContentPane().add(tpass);

		login = new JButton("LOGIN");
		login.setBounds(100, 150, 70, 20);
		login.addActionListener(this);
		jf.getContentPane().add(login);

		exitb = new JButton("EXIT");
		exitb.setBounds(180, 150, 80, 20);
		exitb.addActionListener(this);
		jf.getContentPane().add(exitb);
		exitb.addKeyListener(this);
		login.addKeyListener(this);

		jf.setVisible(true);
		jf.setBounds(300, 240, 350, 300);
		jf.setResizable(false);
		jf.setTitle("Login");
		jf.setIconImage(Toolkit.getDefaultToolkit().getImage("bin\\icon.png"));
		jf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		lborder = new JLabel();
		lborder.setBounds(5, 5, 330, 255);
		lborder.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.gray));
		jf.add(lborder);

	}

	@SuppressWarnings({ "deprecation" })
	public void check() {
		String strid = "", strpass = "";
		try {
			strid = tid.getText();
			strpass = (String) tpass.getText();
			if (!(strid.equals(""))) {
				if (!(strpass.equals(""))) {
					st = con.createStatement();
					rs = st.executeQuery("select * from login where User_ID='" + strid + "' and Password='" + strpass + "'");
					if (rs.next()) {
						jf.setVisible(false);
						rs.close();
						con.close();
						st.close();
						dispose();
						Mainwindow frame = new Mainwindow();
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						frame.setUndecorated(true);
						frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
						frame.setVisible(true);
					} else
						JOptionPane.showMessageDialog(jf, "Not authorized user", "Error Message", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(jf, "Enter Password", "Error Message", JOptionPane.ERROR_MESSAGE);
					tpass.setFocusable(true);
				}
			} else {
				JOptionPane.showMessageDialog(jf, "Enter User_ID", "Error Message", JOptionPane.ERROR_MESSAGE);
				tid.setFocusable(true);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	public void close() {
		int conf = JOptionPane.showConfirmDialog(null, "Are You Sure. ", "Confirmation", JOptionPane.YES_NO_OPTION);
		if (conf == JOptionPane.YES_OPTION)
			System.exit(0);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == exitb) {
			close();
		}
		if (ae.getSource() == login) {
			check();

			/*jf.setVisible(false);
			dispose();
			Mainwindow frame = new Mainwindow();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setUndecorated(true);
			frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
			frame.setVisible(true);*/

		}//action performed close
	}//login close

	public void keyPressed(KeyEvent ke) {
		if (ke.getSource() == exitb)
			close();
		check();
	}

	public void keyReleased(KeyEvent arg0) {

	}

	public void keyTyped(KeyEvent ke) {

	}
}
