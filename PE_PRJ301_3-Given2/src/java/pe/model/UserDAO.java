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
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.utils.DBUtils;

/**
 *
 * @author tranl
 */
public class UserDAO {

    public UserDTO checkLogin(String userID, String password) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        UserDTO result = null;

        try {
            //1.create connection
            con = DBUtils.getConnection();

            //2.create SQL String
            if (con != null) {
                String sql = "Select fullName, roleID, status "
                        + "From tblUsers "
                        + "Where userID = ? "
                        + "And password = ?";
                //3.create Statement Obj
                stm = con.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, password);//so nho hoac lon xuat hien loi unknown web
                //4.excute Query
                rs = stm.executeQuery();
                if (rs.next()) {
                  result = new UserDTO();
                  result.setUserID(userID);
                  result.setFullName(rs.getString("fullName"));
                  result.setPassword(password);
                  result.setRoleID(rs.getString("roleID"));
                  result.setStatus(rs.getBoolean("status"));

                }//end username and password are existed
                //5.Process
            }//end connection í avaible

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
}
