package Coffeee.gui.basic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Coffee.ShareControl;

public interface Basic extends ShareControl {
	//Các chức năng lấy dữ liệu
	public ResultSet get(String sql, int value);

	public ResultSet get(String sql, String name, String pass);

	public ResultSet gets(String sql);

	public ResultSet[] get(String[] sqls);

	


}
