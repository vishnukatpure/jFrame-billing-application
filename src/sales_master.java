import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class sales_master extends JInternalFrame implements ActionListener, FocusListener {

	static final int xOffset = 5, yOffset = 30;
	JButton btnnew, btnsave, btnbill, btnadd, btndelete;
	JTextField txtono, txtid, txtcontact, txtperson, txtqty, txttotal, txtrate, txtdiscount, txtHeight, txtWidth, txtJobRate, txtVat,
			txtTax;
	JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l15, l16, l17, l18, l19, l20, lableHeight, lableWidth, actualsize,
			lableIsHavingJob, vat, tax;
	List lstsr, lstid, lstname, lstqty, lstrate, lsttotal, lstActualsizeHeight, lstActualsizeWidth, lstSqrtFeet;
	int i = 1;
	double total = 0.0, gtotal;
	@SuppressWarnings("rawtypes")
	JComboBox sname, pname, ename, jobNames;
	Connection con = null;
	ResultSet rs = null, rs1 = null;
	Statement st = null;
	int id;
	JDesktopPane jf;
	JCheckBox isHavingJob;

	@SuppressWarnings("rawtypes")
	public sales_master() {

		//super("Document #" + (++openFrameCount),resizable,closable,maximizable,iconifiable);
		super("sales master", false, true, false, true);
		jf = new JDesktopPane();
		getContentPane().add(jf, BorderLayout.CENTER);
		setSize(1110, 760);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(xOffset, yOffset);
		connect c = new connect();
		con = c.getConnection(con);
		l1 = new JLabel("SALES MASTER");
		l2 = new JLabel("Customer Name");
		l3 = new JLabel("Customer ID");
		l4 = new JLabel("Contact No");
		l5 = new JLabel("Registration Date");
		l6 = new JLabel("Product Name");
		l7 = new JLabel("Product ID");

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
		//l19.setVisible(false);
		l20 = new JLabel("Discount (%)");

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
		sname = new JComboBox();
		pname = new JComboBox();
		ename = new JComboBox();

		txtid = new JTextField(15);
		txtcontact = new JTextField(15);
		txtperson = new JTextField(15);
		txtqty = new JTextField(15);
		txttotal = new JTextField(15);
		txttotal.setEditable(false);

		txtono = new JTextField(15);
		txtono.setEditable(false);
		txtrate = new JTextField(15);
		txtrate.setVisible(true);
		txtdiscount = new JTextField(15);

		//===============text field add====================================== 

		JLabel lborder1 = new JLabel();
		lborder1.setBounds(40, 65, 700, 150);
		lborder1.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.gray));
		jf.add(lborder1);

		//-Middle V row
		JLabel lborder2 = new JLabel();
		lborder2.setBounds(5, 250, 1090, 5);
		lborder2.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.gray));
		jf.add(lborder2);

		//-down H row
		JLabel lborder3 = new JLabel();
		lborder3.setBounds(280, 570, 1090, 5);
		lborder3.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.gray));
		jf.add(lborder3);

		JLabel lborder4 = new JLabel();
		lborder4.setBounds(280, 250, 5, 480);
		lborder4.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.gray));
		jf.add(lborder4);

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
		pname.setBounds(50, 300, 160, 25);
		jf.add(pname);

		lableHeight = new JLabel("Height");
		lableHeight.setBounds(50, 340, 150, 20);
		jf.add(lableHeight);

		txtHeight = new JTextField(15);
		txtHeight.setBounds(50, 365, 70, 25);
		jf.add(txtHeight);

		lableWidth = new JLabel("Width");
		lableWidth.setBounds(150, 340, 150, 20);
		jf.add(lableWidth);

		txtWidth = new JTextField(15);
		txtWidth.setBounds(150, 365, 70, 25);
		jf.add(txtWidth);

		l9.setBounds(50, 380, 100, 55);
		jf.add(l9);
		txtqty.setBounds(50, 425, 70, 25);
		jf.add(txtqty);

		l19.setBounds(150, 380, 100, 55);
		jf.add(l19);
		txtrate.setBounds(150, 425, 70, 25);
		jf.add(txtrate);

		lableIsHavingJob = new JLabel("Is Having Job");
		lableIsHavingJob.setBounds(50, 445, 100, 55);
		jf.add(lableIsHavingJob);

		isHavingJob = new JCheckBox();
		isHavingJob.setBounds(150, 460, 20, 20);
		jf.add(isHavingJob);
		isHavingJob.addActionListener(this);

		jobNames = new JComboBox<String>();
		jobNames.setBounds(50, 495, 160, 25);
		jf.add(jobNames);
		jobNames.setVisible(false);
		jobNames.addActionListener(this);

		txtJobRate = new JTextField();
		txtJobRate.setBounds(50, 540, 160, 25);
		jf.add(txtJobRate);
		txtJobRate.setVisible(false);

		btnadd.setBounds(50, 610, 100, 25);
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
		lstqty.setBounds(650, 310, 55, 250);
		jf.add(lstqty);

		actualsize = new JLabel("Actual Size");
		actualsize.setBounds(710, 250, 300, 55);
		jf.add(actualsize);
		lstActualsizeHeight = new List();
		lstActualsizeHeight.setBounds(710, 310, 60, 250);
		jf.add(lstActualsizeHeight);

		lstActualsizeWidth = new List();
		lstActualsizeWidth.setBounds(775, 310, 65, 250);
		jf.add(lstActualsizeWidth);

		lstSqrtFeet = new List();
		lstSqrtFeet.setBounds(850, 310, 65, 250);
		jf.add(lstSqrtFeet);

		l10.setBounds(920, 250, 300, 55);
		jf.add(l10);
		lstrate.setBounds(920, 310, 55, 250);
		jf.add(lstrate);

		l11.setBounds(980, 250, 300, 55);
		jf.add(l11);
		lsttotal.setBounds(980, 310, 85, 250);
		jf.add(lsttotal);

		vat = new JLabel("Vat(%)");
		vat.setBounds(890, 570, 300, 55);
		jf.add(vat);
		txtVat = new JTextField(20);
		txtVat.setBounds(970, 585, 100, 25);
		jf.add(txtVat);
		txtVat.addFocusListener(this);

		tax = new JLabel("Tax(%)");
		tax.setBounds(890, 600, 300, 55);
		jf.add(tax);

		txtTax = new JTextField(20);
		txtTax.setBounds(970, 615, 100, 25);
		jf.add(txtTax);
		txtTax.addFocusListener(this);

		l20.setBounds(890, 630, 300, 55);
		jf.add(l20);
		txtdiscount.setBounds(970, 645, 100, 25);
		jf.add(txtdiscount);

		l12.setBounds(890, 660, 300, 55);
		jf.add(l12);
		txttotal.setBounds(970, 675, 100, 25);
		jf.add(txttotal);

		//================================Button add===========================

		btnnew.setBounds(340, 680, 100, 25);
		jf.add(btnnew);

		btnsave.setBounds(450, 680, 100, 25);
		jf.add(btnsave);

		btndelete.setBounds(560, 680, 100, 25);
		jf.add(btndelete);
		btndelete.setEnabled(false);

		btnbill.setBounds(670, 680, 100, 25);
		jf.add(btnbill);
		btnbill.addActionListener(this);

		JLabel lborder = new JLabel();
		lborder.setBounds(5, 5, 1090, 720);
		lborder.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.gray));
		jf.add(lborder);

		btnnew.addActionListener(this);
		btnadd.addActionListener(this);
		btnsave.addActionListener(this);
		btnsave.setEnabled(false);
		sname.addActionListener(this);
		pname.addActionListener(this);
		ename.addActionListener(this);
		btnadd.setEnabled(false);
		btndelete.addActionListener(this);
		txtdiscount.addFocusListener(this);

	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == isHavingJob) {
			try {
				if (isHavingJob.isSelected()) {
					jobNames.setVisible(true);
					txtJobRate.setVisible(true);
				} else {
					jobNames.setVisible(false);
					txtJobRate.setVisible(false);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (ae.getSource() == sname) {
			try {
				String str = (String) sname.getSelectedItem();
				st = con.createStatement();
				rs = st.executeQuery("select * from customer_master where c_name='" + str + "'");

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
				e.printStackTrace();
			}
		}

		if (ae.getSource() == pname) {
			String str = (String) pname.getSelectedItem();
			setRateOfItem(str, txtrate);
		}
		if (ae.getSource() == jobNames) {
			String str = (String) jobNames.getSelectedItem();
			setRateOfItem(str, txtJobRate);
		}

		if (ae.getSource() == btnnew) {
			sname.removeAllItems();
			pname.removeAllItems();
			ename.removeAllItems();
			jobNames.removeAllItems();
			sname.addItem("");
			pname.addItem("");
			ename.addItem("");
			jobNames.addItem("");
			try {
				st = con.createStatement();
				rs = st.executeQuery("select c_name from customer_master");
				while (rs.next())
					sname.addItem("" + rs.getString(1));

				rs = st.executeQuery("select i_name from item_master where is_job=0");
				while (rs.next())
					pname.addItem("" + rs.getString(1));

				rs = st.executeQuery("select e_name from employee_master");
				while (rs.next())
					ename.addItem("" + rs.getString(1));

				rs = st.executeQuery("select i_name from item_master where is_job=1");
				while (rs.next()) {
					jobNames.addItem("" + rs.getString("i_name"));
				}

				rs = st.executeQuery("select s_order_no from sales_master order by s_order_no DESC");
				if (rs.next()) {
					id = rs.getInt("s_order_no");
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
									int stock = rs.getInt("stock");
									int a = Integer.parseInt(txtqty.getText());
									boolean is_job = rs.getBoolean("is_job");

									if (a > stock && !is_job) {
										JOptionPane.showMessageDialog(jf, "Oh Sorry! Only " + stock + " items are remaining");
										txtqty.setText("");
									} else {
										double b = Double.parseDouble(txtrate.getText());

										lstname.addItem("" + rs.getString("i_name"));
										lstid.addItem("" + rs.getString("item_id"));
										lstqty.addItem(txtqty.getText());
										lstrate.addItem(txtrate.getText());
										lstActualsizeHeight.addItem(txtHeight.getText());
										lstActualsizeWidth.addItem(txtWidth.getText());
										Float height = Float.parseFloat(txtHeight.getText()) % 6;
										Float width = Float.parseFloat(txtWidth.getText()) % 6;

										Float squareInch = (Float.parseFloat(txtHeight.getText()) + (6 - height))
												* (Float.parseFloat(txtWidth.getText()) + (6 - width));
										Float squareFeet = squareInch * 0.00694444444F;
										lstSqrtFeet.addItem(squareFeet + "");
										lsttotal.addItem("" + a * b * squareFeet);
										lstsr.add("" + i++);
										if (isHavingJob.isSelected()) {
											rs = st.executeQuery("select * from item_master where i_name='"
													+ (String) jobNames.getSelectedItem() + "'");
											rs.next();
											lstid.addItem("" + rs.getString("item_id"));
											lstname.addItem("" + rs.getString("i_name"));
											lstqty.addItem("");
											Float jobRate = Float.parseFloat(txtJobRate.getText());
											lstrate.addItem("" + jobRate);
											lstActualsizeHeight.addItem(txtHeight.getText());
											lstActualsizeWidth.addItem(txtWidth.getText());
											lstSqrtFeet.addItem(squareFeet + "");
											lsttotal.addItem(""
													+ new DecimalFormat("#.00").format(squareFeet * Float.parseFloat(txtJobRate.getText())));
											total = total + squareFeet * jobRate;
											lstsr.add("" + i++);
										}
										total = total + (a * b * squareFeet);
										txttotal.setText("" + new DecimalFormat("#.00").format(total));

										txtqty.setText("");
										txtrate.setText("");
										pname.setSelectedIndex(0);
										jobNames.setSelectedIndex(0);
										jobNames.setVisible(false);
										txtJobRate.setVisible(false);
										btndelete.setEnabled(true);
										btnsave.setEnabled(true);
										isHavingJob.setSelected(false);
										txtJobRate.setText("");
									}
								} else {

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
			try {
				int order = Integer.parseInt(txtono.getText());
				int cid = Integer.parseInt(txtid.getText());
				double discount = 0;
				if (Utilities.isNotNull(txtdiscount.getText())) {
					discount = Double.parseDouble(txtdiscount.getText());
				}
				double gtotal = Double.parseDouble(txttotal.getText());
				int size = lstsr.getItemCount();
				for (int i = 0; i < size; i++) {

					int iid = Integer.parseInt(lstid.getItem(i));
					int qty = 0;
					if (Utilities.isNotNull(txtdiscount.getText())) {
						qty = Integer.parseInt(txtdiscount.getText());
					}
					int rem = 0;
					double rate = Double.parseDouble(lstrate.getItem(i));
					double total = Double.parseDouble(lsttotal.getItem(i));
					String height = lstActualsizeHeight.getItem(i);
					String width = lstActualsizeWidth.getItem(i);
					String sqFeet = lstActualsizeWidth.getItem(i);

					st = con.createStatement();
					st.executeUpdate("insert into sales_master values(" + order + ",'" + Utilities.getNewDate() + "'," + cid + "," + iid
							+ "," + qty + "," + rate + "," + total + "," + discount + "," + gtotal + ",'" + height + "','" + width + "','"
							+ sqFeet + "')");

					st = con.createStatement();
					boolean is_job = false;
					rs = st.executeQuery("select stock,is_job from item_master where item_id=" + iid);
					if (rs.next()) {
						rem = rs.getInt("stock");
						is_job = rs.getBoolean("is_job");
						rem = rem - qty;
					}
					if (is_job) {
						st.executeUpdate("update item_master set stock=" + rem + " where item_id=" + iid);
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
				txtdiscount.setText("");
				txttotal.setText("");

				sname.removeAllItems();
				pname.removeAllItems();
				btnbill.setEnabled(true);
				btnadd.setEnabled(false);
				btndelete.setEnabled(false);
				btnsave.setEnabled(false);

			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(jf, e);
			}

		}

		if (ae.getSource() == btnbill) {
			findbill s = new findbill(id);
			s.setVisible(true); //necessary as of 1.3
			jf.add(s);

		}
		if (ae.getSource() == btndelete) {
			String str = lstname.getSelectedItem();
			int index = lstname.getSelectedIndex();
			if (!(str.equals(" "))) {
				Float c = Float.parseFloat(txttotal.getText());
				lstsr.remove(index);
				lstname.remove(index);
				lstid.remove(index);
				lstqty.remove(index);
				lstrate.remove(index);
				lsttotal.remove(index);
				total = total - c;
				txttotal.setText("" + total);
			} else
				JOptionPane.showMessageDialog(jf, "Select Item Name Only For Removing");
		}

	}

	private void setRateOfItem(String str, JTextField txtrate2) {
		try {
			if (Utilities.isNotNull(str)) {
				st = con.createStatement();
				rs = st.executeQuery("select rate from item_master where i_name='" + str + "'");
				if (rs.next()) {
					txtrate2.setText(rs.getString(1));
				}
			} else {
				txtrate2.setText("");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void focusLost(FocusEvent arg) {
		gtotal = total;
		if (Utilities.isNotNull(txtVat.getText())) {
			double dis = Float.parseFloat(txtVat.getText());
			double val = total / 100 * dis;
			gtotal += val;
		}
		if (Utilities.isNotNull(txtTax.getText())) {
			double dis = Float.parseFloat(txtTax.getText());
			double val = total / 100 * dis;
			gtotal += val;

		}
		if (Utilities.isNotNull(txtdiscount.getText())) {
			double dis = Float.parseFloat(txtdiscount.getText());
			double val = total / 100 * dis;
			gtotal -= val;
		}
		txttotal.setText("" + gtotal);
	}

	public void focusGained(FocusEvent arg0) {
	}

}
