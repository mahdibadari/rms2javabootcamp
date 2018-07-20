package com.mycompany.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class UnitDAOImpl implements UnitDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Unit unit) {
		String query = "insert into Unit (name, exp, bblvl, type) values (?,?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		try{
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, unit.getName());
			ps.setInt(2, unit.getExp());
			ps.setInt(3, unit.getBblvl());
			ps.setString(4, unit.getType());
			int out = ps.executeUpdate();
			if(out !=0){
				System.out.println("Unit saved with unitId="+unit.getId());
			}else System.out.println("Unit save failed with unitId="+unit.getId());
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Unit getById(int unitId) {
		String query = "select name, exp, bblvl, type from Unit where unitId = ?";
		Unit emp = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, unitId);
			rs = ps.executeQuery();
			if(rs.next()){
				emp = new Unit();
				emp.setId(unitId);
				emp.setName(rs.getString("name"));
				emp.setExp(rs.getInt("exp"));
				emp.setBblvl(rs.getInt("bblvl"));
				emp.setType(rs.getString("type"));
				System.out.println("Unit Found::"+emp);
			}else{
				System.out.println("No Unit found with unitId="+unitId);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return emp;
	}

	@Override
	public void update(Unit unit) {
		String query = "update Unit set name=?, exp=?, bblvl=?, type=? where unitId=?";
		Connection con = null;
		PreparedStatement ps = null;
		try{
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, unit.getName());
			ps.setInt(2, unit.getExp());
			ps.setInt(3, unit.getBblvl());
			ps.setString(4, unit.getType());
			ps.setInt(5, unit.getId());
			int out = ps.executeUpdate();
			if(out !=0){
				System.out.println("Unit updated with unitId="+unit.getId());
			}else System.out.println("No Unit found with unitId="+unit.getId());
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteById(int unitId) {
		String query = "delete from Unit where unitId=?";
		Connection con = null;
		PreparedStatement ps = null;
		try{
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, unitId);
			int out = ps.executeUpdate();
			if(out !=0){
				System.out.println("Unit deleted with unitId="+unitId);
			}else System.out.println("No Unit found with unitId="+unitId);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Unit> getAll() {
		String query = "select unitId, name, exp, bblvl, type from Unit";
		List<Unit> empList = new ArrayList<Unit>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				Unit emp = new Unit();
				emp.setId(rs.getInt("unitId"));
				emp.setName(rs.getString("name"));
				emp.setExp(rs.getInt("exp"));
				emp.setBblvl(rs.getInt("bblvl"));
				emp.setType(rs.getString("type"));
				empList.add(emp);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return empList;
	}

}