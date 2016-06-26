import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class findbill extends JInternalFrame {

	static final int xOffset = 50, yOffset = 35;
	JButton btnprint;
	JLabel ladd, l0, l1, l2, l3, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, lborder;
	JLabel lbillno, lname, lcontact, ldiscount, lgtotal, lsign;
	JTextArea litem_no, ldesc, lrate, lqty, ltotal;
	JFrame jf;
	Connection con = null;
	ResultSet rs = null, rs1 = null;
	Statement st = null;
	int bno = 0, ono = 0;

	public findbill(int no) {

		//super("Document #" + (++openFrameCount),resizable,closable,maximizable,iconifiable);
		super("Bill", false, true, false, true);
		JDesktopPane jf = new JDesktopPane();
		getContentPane().add(jf, BorderLayout.CENTER);
		setSize(480, 590);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(xOffset, yOffset);
		try {
			connect c = new connect();
			con = c.getConnection(con);
			//------------------------label initialize------------------------------------  
			ladd = new JLabel("s/r no-39, NDA Road, Shivane, Pune.");
			l0 = new JLabel("-------------------------------------------------------------");
			l1 = new JLabel("Maharashtra Marbles");
			l2 = new JLabel("Sales Bill No :");
			l3 = new JLabel("Customer Name :");
			l5 = new JLabel("Contact No :");
			l6 = new JLabel("Item No");
			l7 = new JLabel("Description");
			l8 = new JLabel("" + new Date());
			l9 = new JLabel("Quantity");
			l10 = new JLabel("Rate");
			l11 = new JLabel("Total");
			l12 = new JLabel("Discount (%)");
			l13 = new JLabel("Grant Total");
			l14 = new JLabel("Authorized Dealer");

			lbillno = new JLabel("@--------@");
			lname = new JLabel("@--------@");
			lcontact = new JLabel("@--------@");
			litem_no = new JTextArea();
			ldesc = new JTextArea();
			lrate = new JTextArea();
			lqty = new JTextArea();
			ltotal = new JTextArea();
			ldiscount = new JLabel("@--------@");
			lgtotal = new JLabel("@--------@");
			lbillno.setFont(new Font("areal", Font.PLAIN, 12));
			lname.setFont(new Font("areal", Font.PLAIN, 12));
			lcontact.setFont(new Font("areal", Font.PLAIN, 12));
			litem_no.setFont(new Font("areal", Font.PLAIN, 12));
			ldesc.setFont(new Font("areal", Font.PLAIN, 12));
			lrate.setFont(new Font("areal", Font.PLAIN, 12));
			lqty.setFont(new Font("areal", Font.PLAIN, 12));
			ltotal.setFont(new Font("areal", Font.PLAIN, 12));
			ldiscount.setFont(new Font("areal", Font.PLAIN, 12));
			lgtotal.setFont(new Font("areal", Font.PLAIN, 12));
			ladd.setFont(new Font("areal", Font.PLAIN, 12));
			lsign = new JLabel("( Authorised Sign )");

			btnprint = new JButton("Print");
			//===========================Text box initialize=============================

			//===============text field add====================================== 
			l1.setBounds(120, 5, 800, 40);
			l0.setBounds(120, 25, 800, 30);
			ladd.setBounds(140, 35, 800, 30);
			l1.setFont(new Font("areal", Font.BOLD, 24));
			jf.add(l1);
			jf.add(l0);
			jf.add(ladd);

			l2.setBounds(30, 80, 100, 50);
			jf.add(l2);

			lbillno.setBounds(140, 80, 100, 50);
			jf.add(lbillno);

			l3.setBounds(30, 110, 110, 50);
			jf.add(l3);

			lname.setBounds(140, 110, 100, 50);
			jf.add(lname);

			l5.setBounds(30, 140, 100, 50);
			jf.add(l5);

			lcontact.setBounds(140, 140, 100, 50);
			jf.add(lcontact);

			l6.setBounds(30, 170, 100, 50);
			jf.add(l6);

			litem_no.setBounds(30, 210, 70, 200);
			jf.add(litem_no);

			l7.setBounds(100, 170, 100, 50);
			jf.add(l7);

			ldesc.setBounds(100, 210, 150, 200);
			jf.add(ldesc);

			l8.setBounds(260, 80, 520, 50);
			jf.add(l8);

			l9.setBounds(250, 170, 100, 50);
			jf.add(l9);

			lqty.setBounds(250, 210, 70, 200);
			jf.add(lqty);

			l10.setBounds(320, 170, 100, 50);
			jf.add(l10);

			lrate.setBounds(320, 210, 80, 200);
			jf.add(lrate);

			l11.setBounds(400, 170, 100, 50);
			jf.add(l11);

			ltotal.setBounds(400, 210, 50, 200);
			jf.add(ltotal);

			l12.setBounds(300, 420, 100, 50);
			jf.add(l12);

			ldiscount.setBounds(400, 420, 100, 50);
			jf.add(ldiscount);

			l13.setBounds(300, 450, 100, 50);
			jf.add(l13);

			lgtotal.setBounds(400, 450, 100, 50);
			jf.add(lgtotal);

			lsign.setBounds(330, 490, 110, 50);
			jf.add(lsign);

			lborder = new JLabel();
			lborder.setBounds(15, 180, 440, 245);
			lborder.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.gray));
			jf.add(lborder);
			//================================Button add===========================

			btnprint.setBounds(150, 500, 75, 25);
			jf.add(btnprint);

			JLabel lborder = new JLabel();
			lborder.setBounds(5, 5, 460, 545);
			lborder.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.gray));
			jf.add(lborder);

			String a = "", b = "", c1 = "", d = "", e = "";
			int cid = 0;
			int iid = 0;

			lbillno.setText("" + no);
			ono = Integer.parseInt(lbillno.getText());

			st = con.createStatement();
			rs = st.executeQuery("Select * from sales_master where s_order_no=" + ono);

			while (rs.next()) {
				iid = rs.getInt("i_id");
				cid = rs.getInt("c_id");

				a = a + iid + "\n";
				c1 = c1 + rs.getString("qty") + "\n";
				d = d + rs.getString("rate") + "\n";
				e = e + rs.getString("total") + "\n";

				st = con.createStatement();
				rs1 = st.executeQuery("Select * from item_master where item_id=" + iid);
				if (rs1.next()) {
					b = b + rs1.getString("i_name") + "\n";
					rs1.close();
				}
				ldiscount.setText(rs.getString("discount"));
				lgtotal.setText(rs.getString("g_total"));

			}

			litem_no.setText(a);
			ldesc.setText(b);
			lrate.setText(d);
			lqty.setText(c1);
			ltotal.setText(e);

			st = con.createStatement();
			rs1 = st.executeQuery("Select * from customer_master where customer_id=" + cid);
			if (rs1.next()) {
				lname.setText(rs1.getString("c_name"));
				lcontact.setText(rs1.getString("contact"));
				rs1.close();
			}

			litem_no.setEditable(false);
			ldesc.setEditable(false);
			lrate.setEditable(false);
			lqty.setEditable(false);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(jf, e);
		}
	}

}
