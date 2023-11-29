package com.bookstore.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.Inventory;

public class InventoryDAO {
	
	private Connection conn;
	
	public InventoryDAO() {
		//initialize the connection
		try {
			this.conn = DatabaseConnection.getConnection();
		}
		catch(SQLException e){
			handleSQLException(e);
		}
	}
	
	/**
	 * 
	 * @param inventory
	 * updates the qty of inventory for a given book record
	 * action 4
	 */
	public boolean updateInventory(Inventory inventory) {
		String qry = "UPDATE Inventory SET qty = ? WHERE isbn_number = ?";
		
		try(PreparedStatement prepSt = conn.prepareStatement(qry)){
			prepSt.setInt(1, inventory.getQty());
			prepSt.setString(2, inventory.getIsbnNumber());
			
			int rowsAffected = prepSt.executeUpdate();
			
			return rowsAffected > 0;
		}
		catch (SQLException e) {
			handleSQLException(e);
			return false;
		}
	}
	
	/**
	 * 
	 * @param isbnNumber
	 * @return invList
	 */
	public List<Inventory> getInventoryByIsbn(String isbnNumber){
		List<Inventory> invList = new ArrayList<>();
		String qry = "SELECT * FROM Inventory WHERE isbn_number = ?";
		
		try(PreparedStatement prepSt = conn.prepareStatement(qry)){
			prepSt.setString(1, isbnNumber);
			
			try(ResultSet resSet = prepSt.executeQuery()){
				while(resSet.next()) {
					Inventory inv = extractInventoryFromResultSet(resSet);
					invList.add(inv);
				}
			}
		}
		catch(SQLException e) {
			handleSQLException(e);
		}
		return invList;
	}
	
	/**
	 * 
	 * @param resSet
	 * @return inv
	 * @throws SQLException
	 */
	private Inventory extractInventoryFromResultSet(ResultSet resSet) throws SQLException {
		Inventory inv = new Inventory();
		inv.setInventoryId(resSet.getInt("inventory_id"));
		inv.setIsbnNumber(resSet.getString("isbn_number"));
		inv.setQty(resSet.getInt("qty"));
        inv.setCreatedDate(resSet.getTimestamp("created_date"));
        inv.setUpdatedDate(resSet.getTimestamp("updated_date"));
		
		return inv;
	}

	
	
	
	
	/**
	 * 
	 * @param e
	 */
	private void handleSQLException(SQLException e) {    	
	    e.printStackTrace();        
	}
    
	/**
	 * @param closeConnection
	 */
    public void closeConnection() {    	
        try {        	
            if (conn != null && !conn.isClosed()) {            	
                conn.close();                
            }
        } catch (SQLException e) {        	
            handleSQLException(e);            
        }
    }
	
}
