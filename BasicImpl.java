package Coffeee.gui.basic;

import java.sql.*;

import Coffee.ConnectionPool;
import Coffee.ConnectionPoolImpl;

public class BasicImpl implements Basic {

	private ConnectionPool cp;
	protected Connection con;
	private String objectName;

	public BasicImpl(ConnectionPool cp, String objectName) {

		if (cp == null) {
			this.cp = new ConnectionPoolImpl();

		} else {
			this.cp = cp;
		}
		try {
			
			//xin kết nối để làm việc
			this.con = this.cp.getConnection(this.objectName);
			if (this.con.getAutoCommit()) {
				this.con.setAutoCommit(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	@Override
	public ConnectionPool getCP() {

		return this.cp;
	}

	@Override
	public void releaseConnection() {
		try {
			this.cp.releaseConnection(con, objectName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public ResultSet get(String sql, int value) {
		try {
			PreparedStatement preGet = this.con.prepareStatement(sql);
			if (value > 0) {
				preGet.setInt(1, value);
				return preGet.executeQuery();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			sql = null;
		}

		return null;
	}

	@Override
	public ResultSet get(String sql, String name, String pass) {
		try {
			PreparedStatement preGet = this.con.prepareStatement(sql);
			preGet.setString(1, name);
			preGet.setString(2, pass);
			return preGet.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public ResultSet gets(String sql) {
		// TODO Auto-generated method stub
		return this.get(sql, 0);
	}

	@Override
	public ResultSet[] get(String[] sqls) {
		ResultSet[] tmp = new ResultSet[sqls.length];
		for (int i = 0; i < sqls.length; i++) {
			tmp[i] = this.get(sqls[i], 0);
		}
		return tmp;
	}
	

}
