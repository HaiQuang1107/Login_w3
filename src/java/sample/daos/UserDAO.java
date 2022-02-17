/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.dtos.UserDTO;
import sample.utils.DBUtils;

/**
 *
 * @author TienVM_PC
 */
public class UserDAO {
    public boolean checkLogin1(String userID, String password) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if(conn != null){
                String sql = "SELECT userID "
                        + "FROM tblUsers "
                        + "WHERE userID =? AND password = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if(rs.next()){
                    result = true;
                }
            }
        } catch (Exception e) {
            
        } finally {
            if(conn != null) 
                conn.close();
            if(stm != null)
                stm.close();
            if(rs != null)
                rs.close();
        }
        return result;
    }
    
    public UserDTO checkLogin (String userID, String password) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if(conn != null){
                String sql = "SELECT fullName, roleID FROM tblUsers WHERE userID = ? AND password = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if(rs.next()){
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    user = new UserDTO(userID, fullName, "", roleID);
                }
            }
        } catch (Exception e) {
        } finally {
            if(conn != null)
                conn.close();
            if(rs != null)
                rs.close();
            if(stm != null)
                stm.close();
        }
        return user;
    }
    
    public List<UserDTO> getListUser(String search) throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT userID, fullName, roleID FROM tblUsers WHERE fullName LIKE ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, "%" + search +"%");
            rs = stm.executeQuery();
            while(rs.next()){
                String userID = rs.getString("userID");
                String fullName = rs.getString("fullName");
                String roleID = rs.getString("roleID");
                list.add(new UserDTO(userID, fullName, "**", roleID));
            }
        } catch (Exception e) {
        } finally {
            if(rs != null)
                rs.close();
            if(stm != null)
                stm.close();
            if(conn != null)
                conn.close();
        }
        
        return list;
    }
    
}
