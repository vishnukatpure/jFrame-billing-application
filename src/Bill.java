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
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class Bill extends JInternalFrame {

	static final int xOffset = 50, yOffset = 35;
	JButton btnprint;
	JLabel ladd, l0, l1, l2, l3, l5, l8, l13, l14;
	JLabel lbillno, lname, lcontact, ldiscount, lgtotal, lsign, lDiscount;
	JFrame jf;
	Connection con = null;
	ResultSet rs = null, rs1 = null;
	Statement st = null;
	JTable jTable;

	public Bill(int no) {

		//super("Document #" + (++openFrameCount),resizable,closable,maximizable,iconifiable);
		super("Bill", false, true, false, true);
		JDesktopPane jf = new JDesktopPane();
		getContentPane().add(jf, BorderLayout.CENTER);
		setSize(480, 700);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(xOffset, yOffset);
		try {
			//------------------------label initialize------------------------------------  
			ladd = new JLabel("s/r no-39, NDA Road, Shivane, Pune.");
			l0 = new JLabel("-------------------------------------------------------------");
			l1 = new JLabel("Maharashtra Marbles");
			l2 = new JLabel("Sales Bill No :");
			l3 = new JLabel("Customer Name :");
			l5 = new JLabel("Contact No :");
			l8 = new JLabel("" + new Date());
			l13 = new JLabel("Grant Total");
			l14 = new JLabel("Authorized Dealer");
			lbillno = new JLabel("" + no);

			ldiscount = new JLabel("@--------@");
			lgtotal = new JLabel("@--------@");
			lbillno.setFont(new Font("areal", Font.PLAIN, 12));
			ldiscount.setFont(new Font("areal", Font.PLAIN, 12));
			lgtotal.setFont(new Font("areal", Font.PLAIN, 12));
			ladd.setFont(new Font("areal", Font.PLAIN, 12));
			lsign = new JLabel("( Authorised Sign )");

			btnprint = new JButton("Print");
			//-------------------DB connection------
			connect c = new connect();
			con = c.getConnection(con);
			st = con.createStatement();
			rs = st.executeQuery("Select COUNT(*) from sales_master where s_order_no=" + no);
			rs.next();
			Object rowData[][] = new Object[rs.getInt(1)][8];
			Object columnNames[] = { "Sr.No", "Description", "Quantity", "Rate", "Total" };
			int cid = 0;
			int iid = 0;
			st = con.createStatement();
			rs = st.executeQuery("Select * from sales_master where s_order_no=" + no);
			int i, j = 0;
			while (rs.next()) {
				iid = rs.getInt("i_id");
				cid = rs.getInt("c_id");
				i = 0;
				rowData[j][i++] = (j + 1);

				st = con.createStatement();
				rs1 = st.executeQuery("Select * from item_master where item_id=" + iid);
				if (rs1.next()) {
					rowData[j][i++] = rs1.getString("i_name");
					rs1.close();
				}
				rowData[j][i++] = rs.getString("qty");
				rowData[j][i++] = rs.getString("rate");
				rowData[j++][i++] = rs.getString("total");
				ldiscount.setText(rs.getString("discount"));
				lgtotal.setText(rs.getString("g_total"));

			}
			jTable = new JTable(rowData, columnNames);
			jTable.setFont(new Font("Serif", Font.PLAIN, 14));
			jTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(15, 180, 450, 290);
			scrollPane.setViewportView(jTable);
			jf.add(scrollPane);

			st = con.createStatement();
			rs1 = st.executeQuery("Select * from customer_master where customer_id=" + cid);
			if (rs1.next()) {
				lname = new JLabel(rs1.getString("c_name"));
				lcontact = new JLabel(rs1.getString("contact"));
				lname.setFont(new Font("areal", Font.PLAIN, 12));
				lcontact.setFont(new Font("areal", Font.PLAIN, 12));
				rs1.close();
			}

			//===========================Text box initialize=============================

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

			l8.setBounds(260, 80, 520, 50);
			jf.add(l8);

			ldiscount.setBounds(400, 520, 100, 50);
			jf.add(ldiscount);

			l13.setBounds(300, 550, 100, 50);
			jf.add(l13);

			lgtotal.setBounds(400, 550, 100, 50);
			jf.add(lgtotal);

			lsign.setBounds(330, 590, 110, 50);
			jf.add(lsign);

			//================================Button add===========================

			btnprint.setBounds(150, 620, 75, 25);
			jf.add(btnprint);

			JLabel lborder = new JLabel();
			lborder.setBounds(5, 5, 460, 655);
			lborder.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.gray));
			jf.add(lborder);

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(jf, e);
		}
	}

}
