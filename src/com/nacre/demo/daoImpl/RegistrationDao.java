package com.nacre.demo.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.nacre.demo.bo.RegistrationBo;
import com.nacre.demo.daoI.IRegistrationDao;
import com.nacre.demo.utility.DB_Connection;
import com.nacre.demo.utility.QueryConstants;

public class RegistrationDao implements IRegistrationDao {

	@Override
	public int insert(RegistrationBo registrationBo) {
		Connection con = null;
		PreparedStatement pst = null;
		int count = 0;
		
		//getting the values from Bo object and setting into database
		try {
			con = DB_Connection.getConnection();//helper class support for reusage of connection
			pst = con.prepareStatement(QueryConstants.REG_INSERT_QUERY);//get the query from another class
			pst.setString(1, registrationBo.getFname());				
			pst.setString(2, registrationBo.getLname());
			pst.setString(3, registrationBo.getGender());
			pst.setDate(4, registrationBo.getDob());
			pst.setInt(5, registrationBo.getAge());
			pst.setDouble(6, registrationBo.getPer());
			//return the no. of rows updated
			count = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/*try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "surya");
			pst = con.prepareStatement("insert into registration1 values(?,?,?,?,?,?)");
			pst.setString(1, registrationBo.getFname());
			pst.setString(2, registrationBo.getLname());
			pst.setString(3, registrationBo.getGender());
			pst.setDate(4, registrationBo.getDob());
			pst.setInt(5, registrationBo.getAge());
			pst.setDouble(6, registrationBo.getPer());
			//return the no. of rows updated
			count = pst.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return count;*/
}
