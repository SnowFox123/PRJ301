/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.utils.DBUtils;

/**
 *
 * @author tranl
 */
public class UserDAO {
    public UserDTO checkLogin(String userID, String password) throws ClassNotFoundException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        UserDTO result = null;
        
         try{
             con = DBUtils.getConnection();
             if(con != null){
                    String sql = "Select fullName, roleID, status "
                        + "From tblUsers "
                        + "Where userID = ? "
                        + "And password = ?";
                 stm = con.prepareStatement(sql);
                 stm.setString(1, userID);
                 stm.setString(2, password);
                 rs = stm.executeQuery();
                 if (rs.next()) {
                     result = new UserDTO();
                     result.setUserID(userID);
                     result.setFullName(rs.getString("fullName"));
                     result.setPassword(password);
                     result.setRoleID(rs.getString("roleID"));
                     result.setStatus(rs.getBoolean("status"));
                 }
            }
                  
             
        }finally{
             if(rs != null){
                 rs.close();
             }
             if(stm != null){
                 stm.close();
             }
             if(con != null){
                 con.close();
             }       
        }         
        return result;
    }
}
