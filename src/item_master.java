import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class item_master extends JInternalFrame implements ActionListener {

	static final int xOffset = 55, yOffset = 30;
	JButton btnadd, btnsave, btnupdate, btndelete;
	JButton btnfirst, btnlast, btnnext, btnprevious;

	JTextField txtid, txtname, txtrate, txtstock;;
	JLabel l1, l2, l3, l4, l5, l8, is_job;
	JFrame jf;
	JCheckBox chkBox;

	Connection con = null;
	ResultSet rs = null;
	Statement st = null;

	public item_master() {

		//super("Document #" + (++openFrameCount),resizable,closable,maximizable,iconifiable);
		super("ITEM MASTER", false, true, false, true);
		JDesktopPane jf = new JDesktopPane();
		getContentPane().add(jf, BorderLayout.CENTER);
		setSize(540, 550);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(xOffset, yOffset);
		setFocusable(true);

		connect c = new connect();
		con = c.getConnection(con);

		l1 = new JLabel("ITEM MASTER");
		l2 = new JLabel("Item ID");
		l3 = new JLabel("Item Name");
		l4 = new JLabel("Rate");
		l5 = new JLabel("Remaning Stock");

		btnadd = new JButton("New");
		btnsave = new JButton("Save");
		btnupdate = new JButton("Update");
		btndelete = new JButton("Delete");
		btnfirst = new JButton("First");
		btnlast = new JButton("Last");
		btnnext = new JButton(">>");
		btnprevious = new JButton("<<");

		txtid = new JTextField(15);
		txtname = new JTextField(15);
		txtrate = new JTextField(15);
		txtstock = new JTextField(15);

		l1.setBounds(150, 40, 800, 40);
		l1.setFont(new Font("areal", Font.BOLD, 28));
		jf.add(l1);

		l2.setBounds(50, 100, 300, 55);
		jf.add(l2);
		txtid.setBounds(200, 115, 200, 25);
		jf.add(txtid);
		txtid.setEditable(false);

		l3.setBounds(50, 140, 300, 55);
		jf.add(l3);
		txtname.setBounds(200, 155, 200, 25);
		jf.add(txtname);

		l4.setBounds(50, 180, 300, 55);
		jf.add(l4);
		txtrate.setBounds(200, 195, 200, 25);
		jf.add(txtrate);

		l5.setBounds(50, 220, 300, 55);
		jf.add(l5);
		txtstock.setBounds(200, 235, 200, 25);
		jf.add(txtstock);
		txtstock.setEditable(false);
		txtstock.setText("0");

		is_job = new JLabel("is it Job");
		is_job.setBounds(50, 255, 300, 55);
		jf.add(is_job);
		chkBox = new JCheckBox();
		chkBox.setBounds(200, 275, 20, 20);
		jf.add(chkBox);

		JLabel lborder1 = new JLabel();
		lborder1.setBounds(70, 380, 430, 120);
		lborder1.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.gray));
		jf.add(lborder1);

		btnadd.setBounds(100, 400, 75, 25);
		jf.add(btnadd);

		btnsave.setBounds(200, 400, 75, 25);
		jf.add(btnsave);

		btnupdate.setBounds(300, 400, 75, 25);
		jf.add(btnupdate);

		btndelete.setBounds(400, 400, 75, 25);
		jf.add(btndelete);

		btnfirst.setBounds(100, 450, 75, 25);
		jf.add(btnfirst);

		btnnext.setBounds(200, 450, 75, 25);
		jf.add(btnnext);

		btnprevious.setBounds(300, 450, 75, 25);
		jf.add(btnprevious);

		btnlast.setBounds(400, 450, 75, 25);
		jf.add(btnlast);

		btnsave.setEnabled(false);
		btndelete.setEnabled(false);
		btnupdate.setEnabled(false);

		btnadd.addActionListener(this);
		btnsave.addActionListener(this);
		btnfirst.addActionListener(this);
		btnlast.addActionListener(this);
		btnnext.addActionListener(this);
		btnprevious.addActionListener(this);
		btndelete.addActionListener(this);
		btnupdate.addActionListener(this);

		JLabel lborder = new JLabel();
		lborder.setBounds(5, 5, 520, 510);
		lborder.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.gray));
		jf.add(lborder);
		try {
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery("select * from item_master");
			if (rs.next()) {
				rs.first();
				displayRecord(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == btnadd) {
			btnsave.setEnabled(true);
			btnupdate.setEnabled(false);
			txtname.setText("");
			txtrate.setText("");
			txtstock.setText("00");

			try {
				st = con.createStatement();
				rs = st.executeQuery("SELECT item_id FROM item_master order by item_id DESC");
				if (rs.next()) {
					int id = rs.getInt("item_id");
					id++;
					txtid.setText("" + id);
				} else
					txtid.setText("" + 1);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}

		if (ae.getSource() == btnsave) {
			String name, rate, stock;
			int id = Integer.parseInt(txtid.getText());
			name = txtname.getText();
			rate = txtrate.getText();
			stock = txtstock.getText();
			boolean isJob = chkBox.isSelected();
			try {
				name = txtname.getText();
				if (!(name.equals(""))) {
					if (!(rate.equals(""))) {

						if (!(stock.equals(""))) {//checkemail();

							st = con.createStatement();
							st.executeUpdate("insert into item_master values(" + id + ",'" + name + "','" + rate + "','" + stock + "',"
									+ isJob + ")");
							JOptionPane.showMessageDialog(jf, "record saved");
							txtid.setText("");
							txtname.setText("");
							txtrate.setText("");
							txtstock.setText("");
							chkBox.setSelected(false);
						} else
							JOptionPane.showMessageDialog(jf, "Enter Contact No.", "Error Message", JOptionPane.ERROR_MESSAGE);
					} else
						JOptionPane.showMessageDialog(jf, "Enter Rate", "Error Message", JOptionPane.ERROR_MESSAGE);
				} else
					JOptionPane.showMessageDialog(jf, "Enter name", "Error Message", JOptionPane.ERROR_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}

		if (ae.getSource() == btnupdate) {
			String name, rate, stock;

			int id = Integer.parseInt(txtid.getText());

			name = txtname.getText();
			rate = txtrate.getText();
			stock = txtstock.getText();
			boolean b = chkBox.isSelected();
			try {
				name = txtname.getText();
				if (!(name.equals(""))) {
					if (!(rate.equals(""))) {
						if (!(stock.equals(""))) {

							st = con.createStatement();
							st.executeUpdate("update item_master set i_name='" + name + "',rate='" + rate + "',stock='" + stock
									+ "',is_job=" + b + " where item_id=" + id);
							JOptionPane.showMessageDialog(jf, "record updated");
							txtid.setText("");
							txtname.setText("");
							txtrate.setText("");
							chkBox.setSelected(false);
						} else
							JOptionPane.showMessageDialog(jf, "Enter Contact No.", "Error Message", JOptionPane.ERROR_MESSAGE);
					} else
						JOptionPane.showMessageDialog(jf, "Enter Address", "Error Message", JOptionPane.ERROR_MESSAGE);
				} else
					JOptionPane.showMessageDialog(jf, "Enter name", "Error Message", JOptionPane.ERROR_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}

		}

		if (ae.getSource() == btnfirst) {
			try {
				st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				rs = st.executeQuery("select * from item_master");
				if (rs.next()) {
					rs.first();
					displayRecord(rs);
				}
			} catch (SQLException e) {

				JOptionPane.showMessageDialog(null, e);
			}

		}

		if (ae.getSource() == btnlast) {
			try {
				st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				rs = st.executeQuery("select * from item_master");
				if (rs.next()) {
					rs.last();
					displayRecord(rs);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e);
			}

		}

		if (ae.getSource() == btnnext) {
			try {
				int id = Integer.parseInt(txtid.getText());
				st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				rs = st.executeQuery("select * from item_master where item_id >" + id);
				if (rs.next()) {
					rs.first();
					displayRecord(rs);

				} else
					JOptionPane.showMessageDialog(null, "you are at last record");

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}
		if (ae.getSource() == btnprevious) {
			try {
				int id = Integer.parseInt(txtid.getText());
				st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				rs = st.executeQuery("select * from item_master where item_id <" + id);
				if (rs.next()) {
					rs.last();
					displayRecord(rs);

				} else
					JOptionPane.showMessageDialog(null, "you are at first record");

			} catch (SQLException e) {

				JOptionPane.showMessageDialog(null, e);
			}

		}

		if (ae.getSource() == btndelete) {
			int ans = JOptionPane.showConfirmDialog(jf, " Are You sure", "confirm", JOptionPane.YES_NO_OPTION);
			if (ans == JOptionPane.YES_OPTION) {
				try {
					int id = Integer.parseInt(txtid.getText());
					st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					st.executeUpdate("delete * from item_master where item_id=" + id);

					txtid.setText("");
					txtname.setText("");
					txtrate.setText("");
					txtstock.setText("");
					chkBox.setSelected(false);
					JOptionPane.showMessageDialog(jf, "Your record has been deleted");
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		}
	}

	public void displayRecord(ResultSet rs) {
		btndelete.setEnabled(true);
		btnupdate.setEnabled(true);
		try {
			txtid.setText(rs.getString(1));
			txtname.setText(rs.getString(2));
			txtrate.setText(rs.getString(3));
			txtstock.setText(rs.getString(4));
			System.out.println(rs.getBoolean(5));
			chkBox.setSelected(rs.getBoolean(5));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
