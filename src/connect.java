import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class connect {
	//String DBLocation = "E:\\WorkSpace\\branch6Nov\\Inventory\\src\\bin\\marble_shop.mdb";
	String DBLocation = "jdbc:mysql://localhost:3306/movedb";

	public Connection getConnection(Connection con) {
		try {
			/*
			 * File f = new File(DBLocation); if (!f.exists()) { JOptionPane.showMessageDialog(null, "Unable to Find. Database Location: " +
			 * DBLocation); } String DBSource = "jdbc:odbc:;DRIVER={Microsoft Access Driver (*.mdb)};" + "DBQ=" + DBLocation +
			 * ";DriverID=22;READONLY=true}";
			 */

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DBLocation, "root", "grex1234");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
			System.exit(1);

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Unable to connect database. Database Location: " + DBLocation);
			System.exit(1);
		}
		return con;

	}

}