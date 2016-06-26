import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class Mainwindow extends JFrame implements ActionListener {
	JDesktopPane desktop;
	JMenuItem a;
	JMenuItem hel, cust_master, supplier_master, item_master, about, employee_master, purchase, sales, c_report, sup_report, p_report,
			supplier_report, employee_report, exit1, adduser, changepass, deluser, find_bill, salesReport;
	JMenu master, transaction, reports, exit, user, help;
	JSeparator k;
	int bno = 0, ono = 0;
	Connection con = null;

	public Mainwindow() {
		super("Marble Shop Management");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
		int taskBarSize = scnMax.bottom;
		setSize(screenSize.width - getWidth(), screenSize.height - taskBarSize - getHeight());
		desktop = new JDesktopPane();
		setContentPane(desktop);
		desktop.setBackground(Color.GRAY);
		desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);

		JMenuBar mb = new JMenuBar();
		mb.setBounds(0, 0, screenSize.width, 30);
		desktop.add(mb);
		master = new JMenu("   Master");
		master.setMnemonic('m');
		mb.add(master);

		cust_master = new JMenuItem("   Customer_Master");
		master.add(cust_master);
		cust_master.addActionListener(this);

		supplier_master = new JMenuItem("   Supplier Master");
		master.add(supplier_master);
		supplier_master.addActionListener(this);

		item_master = new JMenuItem("   Item Master");
		master.add(item_master);
		item_master.addActionListener(this);

		employee_master = new JMenuItem("   Employee Master");
		master.add(employee_master);
		employee_master.addActionListener(this);

		transaction = new JMenu("   Transaction");
		transaction.setMnemonic('t');
		mb.add(transaction);

		purchase = new JMenuItem("Purchase");
		transaction.add(purchase);
		purchase.addActionListener(this);

		sales = new JMenuItem("Sales");
		transaction.add(sales);
		sales.addActionListener(this);

		reports = new JMenu("   Reports");
		reports.setMnemonic('r');
		mb.add(reports);

		c_report = new JMenuItem("Customer Report");
		reports.add(c_report);
		c_report.addActionListener(this);

		sup_report = new JMenuItem("Supplier Report");
		reports.add(sup_report);
		sup_report.addActionListener(this);

		employee_report = new JMenuItem("Employee Report");
		reports.add(employee_report);
		employee_report.addActionListener(this);

		find_bill = new JMenuItem("Find Bill");
		reports.add(find_bill);
		find_bill.addActionListener(this);

		salesReport = new JMenuItem("Sales Report");
		reports.add(salesReport);
		salesReport.addActionListener(this);

		user = new JMenu("    User");
		user.setMnemonic('u');
		mb.add(user);

		changepass = new JMenuItem("Change Password");
		user.add(changepass);
		changepass.addActionListener(this);

		adduser = new JMenuItem("Add user");
		user.add(adduser);
		adduser.addActionListener(this);

		deluser = new JMenuItem("Delete User");
		user.add(deluser);
		deluser.addActionListener(this);

		exit = new JMenu("   System");
		exit.setMnemonic('s');
		mb.add(exit);

		about = new JMenuItem("About us");
		exit.add(about);
		about.addActionListener(this);

		exit1 = new JMenuItem("Logout");
		exit.add(exit1);
		exit1.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cust_master) {
			customer_master frame = new customer_master();
			frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			frame.setVisible(true); //necessary as of 1.3
			desktop.add(frame);
		}

		if (e.getSource() == supplier_master) {
			supplier_master frame = new supplier_master();
			frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			frame.setVisible(true); //necessary as of 1.3
			desktop.add(frame);
		}

		if (e.getSource() == item_master) {
			item_master frame = new item_master();
			frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			frame.setVisible(true); //necessary as of 1.3
			desktop.add(frame);
		}
		if (e.getSource() == employee_master) {
			employee_master frame = new employee_master();
			frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			frame.setVisible(true); //necessary as of 1.3
			desktop.add(frame);
		}
		if (e.getSource() == purchase) {
			purchase_master frame = new purchase_master();
			frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			frame.setVisible(true); //necessary as of 1.3
			desktop.add(frame);
		}
		if (e.getSource() == sales) {
			sales_master frame = new sales_master();
			frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			frame.setVisible(true); //necessary as of 1.3
			desktop.add(frame);
		}
		if (e.getSource() == c_report) {
			//customerreport frame = new customerreport();
			CustomerReportNew frame = new CustomerReportNew();
			frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			frame.setVisible(true); //necessary as of 1.3
			desktop.add(frame);
		}

		if (e.getSource() == sup_report) {
			//supplierreport frame = new supplierreport();
			SupplierReportNew frame = new SupplierReportNew();
			frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			frame.setVisible(true); //necessary as of 1.3
			desktop.add(frame);
		}
		if (e.getSource() == employee_report) {
			EmployeeReport frame = new EmployeeReport();
			//employee_report frame = new employee_report();
			frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			frame.setVisible(true); //necessary as of 1.3
			desktop.add(frame);
		}
		if (e.getSource() == find_bill) {
			String str = "";
			str = JOptionPane.showInputDialog(desktop, "Enter Sales Order number");

			if (!str.equalsIgnoreCase("")) {
				bno = Integer.parseInt(str);
				connect c = new connect();
				con = c.getConnection(con);
				try {
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("Select * from sales_master where s_order_no=" + bno);
					if (rs.next()) {
						ono = rs.getInt("s_order_no");
						//findbill s = new findbill(ono);
						Bill s = new Bill(ono);
						s.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						s.setVisible(true); //necessary as of 1.3
						desktop.add(s);
						s.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					} else
						JOptionPane.showMessageDialog(desktop, "Bill no. Not Found");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(desktop, "hi");
				}
			} else
				JOptionPane.showMessageDialog(desktop, "Enter Bill no. Properly");
		}
		if (e.getSource() == changepass) {
			changepass frame = new changepass();
			frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			frame.setVisible(true); //necessary as of 1.3
			desktop.add(frame);
		}
		if (e.getSource() == adduser) {
			adduser frame = new adduser();
			frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			frame.setVisible(true); //necessary as of 1.3
			desktop.add(frame);
		}
		if (e.getSource() == deluser) {
			deluser frame = new deluser();
			frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			frame.setVisible(true); //necessary as of 1.3
			desktop.add(frame);
		}
		if (e.getSource() == about) {
			About frame = new About();
			frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			frame.setVisible(true); //necessary as of 1.3
			desktop.add(frame);
		}
		if (e.getSource() == exit1) {
			int ans = JOptionPane.showConfirmDialog(desktop, "Are You sure ?", "confirm", JOptionPane.YES_NO_OPTION);
			if (ans == JOptionPane.YES_OPTION)
				System.exit(0);
		}
		if (e.getSource() == salesReport) {
			SalesReport frame = new SalesReport();
			frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			frame.setVisible(true); //necessary as of 1.3
			desktop.add(frame);
		}

	}

}
