package com.xxx.bl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.xxx.Model.Customer;

public class customCRUD {

	public boolean addCustomer(Customer c) throws SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver");
		Connection con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "sa");
		PreparedStatement st = con.prepareStatement("insert into customer values(?,?,?)");
		st.setString(1, c.getUsername());
		st.setString(2, c.getEmail());
		st.setString(3, c.getUser_pass());

		if (st.executeUpdate() != 0) {

			return true;
		} else {
			return false;
		}
	}

	public ArrayList<Customer> Selectdet() throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		Connection con1 = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "sa");
		Statement st1 = con1.createStatement();
		ResultSet r = st1.executeQuery("select *from customer");
		ArrayList<Customer> li = new ArrayList<>();
		while (r.next()) {
			Customer c1 = new Customer();
			c1.setUsername(r.getString(1));
			c1.setEmail(r.getString(2));
			li.add(c1);

		}
		return li;
	}

}
