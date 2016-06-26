import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class customerreport extends JInternalFrame implements ActionListener {

	static final int xOffset = 3, yOffset = 30;
	JButton btnnext, btnprevious;
	JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12;
	JFrame jf;

	Connection con = null;
	ResultSet rs = null;
	Statement st = null;
	JTextArea ans, add, contact, email, cperson;
	int i = 0;
	String str = "", stradd = "", stremail = "", strp = "", strcontact = "";

	public customerreport() {

		//super("Document #" + (++openFrameCount),resizable,closable,maximizable,iconifiable);
		super("Customer Report", false, true, true, true);
		JDesktopPane jf = new JDesktopPane();
		getContentPane().add(jf, BorderLayout.CENTER);
		setSize(1020, 700);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(xOffset, yOffset);
		connect c = new connect();
		con = c.getConnection(con);
		l1 = new JLabel("CUSTOMER REPORT");
		l2 = new JLabel("Cusomer ID");
		l3 = new JLabel("Customer Name");
		l5 = new JLabel("Contact No");
		l6 = new JLabel("Email");
		l7 = new JLabel("Register Date");

		l9 = new JLabel("Address");

		btnnext = new JButton("Next");
		btnprevious = new JButton("Previous");
		//===========================Text box initialize=============================

		//===============text field add====================================== 
		l1.setBounds(300, 40, 800, 40);
		l1.setFont(new Font("areal", Font.BOLD, 28));
		jf.add(l1);

		l2.setBounds(50, 130, 100, 50);
		jf.add(l2);

		l3.setBounds(130, 130, 100, 50);
		jf.add(l3);

		l5.setBounds(490, 130, 100, 50);
		jf.add(l5);

		l6.setBounds(620, 130, 100, 50);
		jf.add(l6);

		l7.setBounds(820, 130, 100, 50);
		jf.add(l7);

		l9.setBounds(300, 130, 100, 50);
		jf.add(l9);

		ans = new JTextArea();
		ans.setBounds(50, 165, 220, 450);
		jf.add(ans);

		add = new JTextArea();
		add.setBounds(275, 165, 200, 450);
		jf.add(add);

		contact = new JTextArea();
		contact.setBounds(480, 165, 120, 450);
		jf.add(contact);

		email = new JTextArea();
		email.setBounds(605, 165, 200, 450);
		jf.add(email);
		cperson = new JTextArea();
		cperson.setBounds(810, 165, 180, 450);
		jf.add(cperson);

		try {
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery("select * from customer_master order by customer_id");
			while (rs.next()) {
				if (i < 27) {
					str = str + "  " + rs.getInt(1) + "                        " + rs.getString(2) + "\n";
					stradd = stradd + rs.getString(3) + "\n";
					strcontact = strcontact + rs.getString(4) + "\n";
					stremail = stremail + rs.getString(5) + "\n";
					strp = strp + rs.getString(6) + "\n";
					i++;
				} else
					break;
			}
			ans.setText(str);
			add.setText(stradd);
			contact.setText(strcontact);
			email.setText(stremail);
			cperson.setText(strp);
			//ans.setEditable(false);add.setEditable(false);contact.setEditable(false); email.setEditable(false);cperson.setEditable(false);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		//================================Button add===========================
		JLabel lborder1 = new JLabel();
		lborder1.setBounds(45, 163, 950, 457);
		lborder1.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.gray));
		jf.add(lborder1);

		btnnext.setBounds(550, 620, 75, 25);
		jf.add(btnnext);

		btnprevious.setBounds(400, 620, 90, 25);
		jf.add(btnprevious);

		JLabel lborder = new JLabel();
		lborder.setBounds(5, 5, 1000, 685);
		lborder.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.gray));
		jf.add(lborder);

	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == btnnext) {
			int j = i + 27;
			try {
				while (rs.next()) {
					if (i < j) {
						str = str + "  " + rs.getInt(1) + "                        " + rs.getString(2) + "\n";
						stradd = stradd + rs.getString(3) + "\n";
						strcontact = strcontact + rs.getString(4) + "\n";
						stremail = stremail + rs.getString(5) + "\n";
						strp = strp + rs.getString(6) + "\n";
						i++;
					} else
						break;
				}
				ans.setText(str);
				add.setText(stradd);
				contact.setText(strcontact);
				email.setText(stremail);
				cperson.setText(strp);
				ans.setEditable(false);
				add.setEditable(false);
				contact.setEditable(false);
				email.setEditable(false);
				cperson.setEditable(false);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}

		if (ae.getSource() == btnprevious) {
			i = i - 27;
			int j = i + 27;
			try {
				for (int k = 0; k < j; k++)
					rs.previous();
				while (rs.next()) {
					if (i < j) {
						str = str + "  " + rs.getInt(1) + "                        " + rs.getString(2) + "\n";
						stradd = stradd + rs.getString(3) + "\n";
						strcontact = strcontact + rs.getString(4) + "\n";
						stremail = stremail + rs.getString(5) + "\n";
						strp = strp + rs.getString(6) + "\n";
						i++;
					} else
						break;
				}
				ans.setText(str);
				add.setText(stradd);
				contact.setText(strcontact);
				email.setText(stremail);
				cperson.setText(strp);
				ans.setEditable(false);
				add.setEditable(false);
				contact.setEditable(false);
				email.setEditable(false);
				cperson.setEditable(false);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}

}
