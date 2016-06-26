import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class SupplierReportNew extends JInternalFrame {

	static final int xOffset = 3, yOffset = 30;
	JFrame jf;
	JTable jTable;
	Connection con = null;
	ResultSet rs = null;
	Statement st = null;

	public SupplierReportNew() {

		//super("Document #" + (++openFrameCount),resizable,closable,maximizable,iconifiable);
		super("Supplier Report", false, true, true, true);
		JDesktopPane jf = new JDesktopPane();
		getContentPane().add(jf, BorderLayout.CENTER);
		setSize(1020, 770);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(xOffset, yOffset);
		JLabel l1 = new JLabel("SUPPLIER REPORT");
		l1.setBounds(300, 40, 800, 40);
		l1.setFont(new Font("areal", Font.BOLD, 28));
		jf.add(l1);
		connect c = new connect();
		try {
			con = c.getConnection(con);
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery("select COUNT(*) from supplier_master");
			rs.next();
			Object rowData[][] = new Object[rs.getInt(1)][6];
			Object columnNames[] = { "ID", "Supplier Name", "Contact No", "Email", "Contact Person", "Address" };
			int i = 0, j = 0;
			rs = st.executeQuery("select * from supplier_master order by supplier_id");
			while (rs.next()) {
				i = 0;
				rowData[j][i++] = (j + 1);
				rowData[j][i++] = rs.getString(2);
				rowData[j][i++] = rs.getString(3);
				rowData[j][i++] = rs.getString(4);
				rowData[j][i++] = rs.getString(5);
				rowData[j++][i++] = rs.getString(6);
			}

			jTable = new JTable(rowData, columnNames);

			jTable.setFont(new Font("Serif", Font.PLAIN, 14));
			jTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 120, 980, 600);
			scrollPane.setViewportView(jTable);
			jf.add(scrollPane);

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}
		//================================Button add===========================

		JLabel lborder = new JLabel();
		lborder.setBounds(5, 5, 1000, 730);
		lborder.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.gray));
		jf.add(lborder);

	}
}
