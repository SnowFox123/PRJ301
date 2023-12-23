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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.utils.DBUtils;

/**
 *
 * @author tranl
 */
public class FashionDAO {
    public List<FashionDTO> searchFashion(String name) throws ClassNotFoundException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<FashionDTO> result = null;

        try {
            //1.create connection
            con = DBUtils.getConnection();

            //2.create SQL String
            if (con != null) {
                String sql = "Select id, name, description, price, size, status "
                        + "From tblFashion "
                        + "Where name LIKE ? "
                        + "AND status = 1";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%"+name+"%");
                //4.excute Query
                rs = stm.executeQuery();
                while (rs.next()) {
                    if(result==null){
                        result = new ArrayList<FashionDTO>();
                    }
                    FashionDTO dto = new FashionDTO();
                    dto.setId(rs.getString("id"));
                    dto.setName(rs.getString("name"));
                    dto.setDescription(rs.getString("description"));
                    dto.setPrice(rs.getFloat("price"));
                    dto.setSize(rs.getString("size"));
                    dto.setStatus(rs.getBoolean("status"));
                    
                    result.add(dto);
                }//end username and password are existed
                //5.Process
            }//end connection í avaible

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
    
    public boolean deleteFashion(String id) throws ClassNotFoundException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            //1.create connection
            con = DBUtils.getConnection();

            //2.create SQL String
            if (con != null) {
                String sql = "Update tblFashion "
                        + "Set status = 0 "
                        + "where id = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                //4.excute Query
                int effectRow = stm.executeUpdate();
                if(effectRow>0){
                    result = true;
                }
                //5.Process
            }//end connection í avaible

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
