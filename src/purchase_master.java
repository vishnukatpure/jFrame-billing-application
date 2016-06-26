import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class purchase_master extends JInternalFrame implements ActionListener {

	static final int xOffset = 10, yOffset = 30;
	JButton btnnew, btnsave, btnbill, btnadd, btndelete;
	JTextField txtono, txtid, txtcontact, txtperson, txtqty, txttotal, txtrate;
	JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l15, l16, l17, l18, l19;
	List lstsr, lstid, lstname, lstqty, lstrate, lsttotal;
	int i = 1, c;
	@SuppressWarnings("rawtypes")
	JComboBox sname, pname;
	Connection con = null;
	ResultSet rs = null, rs1 = null;
	Statement st = null;
	JDesktopPane jf = new JDesktopPane();

	@SuppressWarnings("rawtypes")
	public purchase_master() {

		//super("Document #" + (++openFrameCount),resizable,closable,maximizable,iconifiable);
		super("Purchase Master", false, true, false, true);

		getContentPane().add(jf, BorderLayout.CENTER);
		setSize(990, 680);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(xOffset, yOffset);
		setFocusable(true);
		connect c = new connect();
		con = c.getConnection(con);

		l1 = new JLabel("PURCHASE MASTER");
		l2 = new JLabel("Supplier Name");
		l3 = new JLabel("Supplier ID");
		l4 = new JLabel("Contact No");
		l5 = new JLabel("Contact Person");
		l6 = new JLabel("Product Name");
		l7 = new JLabel("Product ID");
		l8 = new JLabel("" + new Date());
		l9 = new JLabel("Quantity");
		l10 = new JLabel("Rate");
		l11 = new JLabel("Total");
		l12 = new JLabel("Grant Total");
		l13 = new JLabel("Order No:");
		l15 = new JLabel("Sr No");

		l16 = new JLabel("Product id");
		l17 = new JLabel("Product Name");
		l18 = new JLabel("Quantity");
		l19 = new JLabel("Rate");

		btnnew = new JButton("New");
		btnsave = new JButton("Save");
		btnbill = new JButton("Print Bill");
		btnbill.setEnabled(false);
		btnadd = new JButton("Add Item");
		btndelete = new JButton("Delete Item");

		lstsr = new List();
		lstid = new List();
		lstname = new List();
		lstqty = new List();
		lstrate = new List();
		lsttotal = new List();

		//===========================Text box initialize=============================
		JLabel lborder1 = new JLabel();
		lborder1.setBounds(40, 65, 700, 150);
		lborder1.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.gray));
		jf.add(lborder1);

		JLabel lborder2 = new JLabel();
		lborder2.setBounds(5, 250, 970, 5);
		lborder2.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.gray));
		jf.add(lborder2);

		JLabel lborder3 = new JLabel();
		lborder3.setBounds(5, 570, 970, 5);
		lborder3.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.gray));
		jf.add(lborder3);

		JLabel lborder4 = new JLabel();
		lborder4.setBounds(280, 250, 5, 320);
		lborder4.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.gray));
		jf.add(lborder4);

		sname = new JComboBox();
		pname = new JComboBox();

		txtid = new JTextField(15);
		txtcontact = new JTextField(15);
		txtperson = new JTextField(15);
		txtqty = new JTextField(15);
		txttotal = new JTextField(15);
		txttotal.setEditable(false);

		txtono = new JTextField(15);
		txtono.setEditable(false);
		txtrate = new JTextField(15);

		//===============text field add====================================== 
		l13 = new JLabel("Order No:");
		l13.setBounds(50, 60, 300, 55);
		jf.add(l13);

		txtono.setBounds(150, 75, 200, 25);
		jf.add(txtono);

		l1.setBounds(300, 10, 800, 40);
		l1.setFont(new Font("areal", Font.BOLD, 28));
		jf.add(l1);

		l2.setBounds(50, 100, 300, 55);
		jf.add(l2);
		sname.setBounds(150, 115, 200, 20);
		jf.add(sname);

		l4.setBounds(400, 100, 300, 55);
		jf.add(l4);
		txtcontact.setBounds(500, 115, 200, 25);
		jf.add(txtcontact);

		l3.setBounds(50, 140, 300, 55);
		jf.add(l3);
		txtid.setBounds(150, 155, 200, 25);
		jf.add(txtid);

		l5.setBounds(400, 140, 300, 55);
		jf.add(l5);
		txtperson.setBounds(500, 155, 200, 25);
		jf.add(txtperson);

		l6.setBounds(50, 250, 300, 55);
		jf.add(l6);
		pname.setBounds(50, 305, 150, 20);
		jf.add(pname);

		l9.setBounds(50, 330, 100, 55);
		jf.add(l9);
		txtqty.setBounds(50, 375, 70, 25);
		jf.add(txtqty);

		l19.setBounds(140, 330, 100, 55);
		jf.add(l19);
		txtrate.setBounds(140, 375, 70, 25);
		jf.add(txtrate);

		btnadd.setBounds(50, 430, 100, 25);
		jf.add(btnadd);

		l15.setBounds(350, 250, 50, 55);
		jf.add(l15);
		lstsr.setBounds(350, 310, 30, 250);
		jf.add(lstsr);

		l16.setBounds(390, 250, 300, 55);
		jf.add(l16);
		lstid.setBounds(385, 310, 65, 250);
		jf.add(lstid);

		l17.setBounds(460, 250, 300, 55);
		jf.add(l17);
		lstname.setBounds(455, 310, 190, 250);
		jf.add(lstname);

		l18.setBounds(650, 250, 300, 55);
		jf.add(l18);
		lstqty.setBounds(650, 310, 85, 250);
		jf.add(lstqty);

		l10.setBounds(740, 250, 300, 55);
		jf.add(l10);
		lstrate.setBounds(740, 310, 85, 250);
		jf.add(lstrate);

		l11.setBounds(830, 250, 300, 55);
		jf.add(l11);
		lsttotal.setBounds(830, 310, 85, 250);
		jf.add(lsttotal);

		l12.setBounds(750, 560, 300, 55);
		jf.add(l12);
		txttotal.setBounds(830, 580, 80, 25);
		jf.add(txttotal);

		//================================Button add===========================

		btnnew.setBounds(100, 610, 75, 25);
		jf.add(btnnew);

		btnsave.setBounds(200, 610, 75, 25);
		jf.add(btnsave);

		btndelete.setBounds(300, 610, 100, 25);
		jf.add(btndelete);
		btndelete.setEnabled(false);

		btnbill.setBounds(450, 610, 85, 25);
		jf.add(btnbill);

		l8.setBounds(800, 50, 510, 50);
		jf.add(l8);

		btnbill.addActionListener(this);

		JLabel lborder = new JLabel();
		lborder.setBounds(5, 5, 970, 680);
		lborder.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.gray));
		jf.add(lborder);

		btnnew.addActionListener(this);
		btnadd.addActionListener(this);
		btnsave.addActionListener(this);
		btnsave.setEnabled(false);
		sname.addActionListener(this);
		btnadd.setEnabled(false);
		btndelete.addActionListener(this);

	}

	@SuppressWarnings({ "deprecation", "unchecked", })
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == sname) {
			try {
				String str = (String) sname.getSelectedItem();
				st = con.createStatement();
				rs = st.executeQuery("select * from supplier_master where s_name='" + str + "'");

				if (rs.next()) {
					String contact = "", person = "";
					int id;
					id = rs.getInt(1);
					contact = rs.getString(4);
					person = rs.getString(6);
					txtcontact.setText("" + contact);
					txtperson.setText("" + person);
					txtid.setText("" + id);
					txtcontact.setEditable(false);
					txtperson.setEditable(false);
					txtid.setEditable(false);
				}

			} catch (Exception e) {

			}

		}
		if (ae.getSource() == btnnew) {
			sname.removeAllItems();
			pname.removeAllItems();
			sname.addItem("");
			pname.addItem("");
			try {
				st = con.createStatement();
				rs = st.executeQuery("select s_name from supplier_master");
				while (rs.next())
					sname.addItem("" + rs.getString(1));

				rs = st.executeQuery("select i_name from item_master where is_job=0");
				while (rs.next())
					pname.addItem("" + rs.getString(1));

				rs = st.executeQuery("select p_order_no from purchase_master order by p_order_no DESC");
				if (rs.next()) {
					int id = rs.getInt("p_order_no");
					id++;
					txtono.setText("" + id);
				} else
					txtono.setText("" + 1);
				btnadd.setEnabled(true);
			} catch (Exception e) {

			}
		}

		if (ae.getSource() == btnadd) {

			String str = (String) pname.getSelectedItem();
			String str1 = (String) sname.getSelectedItem();
			String qty = txtqty.getText();
			String rate = txtrate.getText();
			try {
				if (!(str1.equals(""))) {
					if (!(str.equals(""))) {
						if (!(qty.equals(""))) {
							if (!(rate.equals(""))) {
								st = con.createStatement();
								rs = st.executeQuery("select * from item_master where i_name='" + str + "'");
								if (rs.next()) {
									int a = Integer.parseInt(txtqty.getText());
									int b = Integer.parseInt(txtrate.getText());

									c = c + a * b;
									lsttotal.addItem("" + a * b);
									lstname.addItem("" + rs.getString("i_name"));
									lstid.addItem("" + rs.getString("item_id"));
									lstqty.addItem(txtqty.getText());
									lstrate.addItem(txtrate.getText());

									txttotal.setText("" + c);

									txtqty.setText("");
									txtrate.setText("");
									pname.setSelectedIndex(0);
									lstsr.add("" + i++);
									btndelete.setEnabled(true);
									btnsave.setEnabled(true);
								}
							} else
								JOptionPane.showMessageDialog(jf, "Enter rate of Item");
						} else
							JOptionPane.showMessageDialog(jf, "Enter Quantity of Item");
					} else
						JOptionPane.showMessageDialog(jf, "Select Item Name");
				} else
					JOptionPane.showMessageDialog(jf, "Select Supplier Name");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(jf, e);
			}

		}

		if (ae.getSource() == btnsave) {
			btnbill.setEnabled(true);
			try {
				int order = Integer.parseInt(txtono.getText());
				int sid = Integer.parseInt(txtid.getText());
				int gtotal = Integer.parseInt(txttotal.getText());

				int size = lstsr.getItemCount();
				for (int i = 0; i < size; i++) {

					int iid = Integer.parseInt(lstid.getItem(i));
					int qty = Integer.parseInt(lstqty.getItem(i));
					int rate = Integer.parseInt(lstrate.getItem(i));
					int total = Integer.parseInt(lsttotal.getItem(i));

					st = con.createStatement();
					st.executeUpdate("insert into purchase_master values(" + order + ",'" + new Date() + "'," + sid + "," + iid + "," + qty
							+ "," + rate + "," + total + "," + gtotal + ")");

					st = con.createStatement();
					rs = st.executeQuery("select stock from item_master where item_id=" + iid);
					if (rs.next()) {
						int avl = rs.getInt("stock") + qty;

						st.executeUpdate("update item_master set stock=" + avl + " where item_id=" + iid);
					}
				}
				JOptionPane.showMessageDialog(jf, "Record Saved Successfully");

				lstsr.removeAll();
				lstid.removeAll();
				lstname.removeAll();
				lstqty.removeAll();
				lstrate.removeAll();
				lsttotal.removeAll();

				txtono.setText("");
				txtid.setText("");
				txtcontact.setText("");
				txtperson.setText("");
				txtqty.setText("");
				txtrate.setText("");
				txttotal.setText("");

				sname.removeAllItems();
				pname.removeAllItems();

				btnadd.setEnabled(false);
				btndelete.setEnabled(false);
				btnsave.setEnabled(false);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(jf, e);
			}

		}

		if (ae.getSource() == btnbill) {
			purchasebill frame = new purchasebill();
			frame.setVisible(true);
			jf.add(frame);

		}
		if (ae.getSource() == btndelete) {
			String str = lstname.getSelectedItem();
			int index = lstname.getSelectedIndex();
			if (!(str.equals(" "))) {
				int a = Integer.parseInt(lstqty.getItem(index));
				int b = Integer.parseInt(lstrate.getItem(index));
				int c = Integer.parseInt(txttotal.getText());
				lstsr.remove(index);
				lstname.remove(index);
				lstid.remove(index);
				lstqty.remove(index);
				lstrate.remove(index);
				lsttotal.remove(index);
				c = c - a * b;
				txttotal.setText("" + c);
			} else
				JOptionPane.showMessageDialog(jf, "Select Item Name Only For Removing");
		}

	}

}
