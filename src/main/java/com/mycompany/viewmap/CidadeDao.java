package com.mycompany.viewmap;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CidadeDao {
    
    public CidadeDao(){
        
    }
    public String getViewBox(String cidade){
        try{
            Connection con = ConFactory.getConnection();
            PreparedStatement st = con.prepareStatement("SELECT getTamanhoViewBox(?)");
            st.setString(1, cidade);
            
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                return rs.getString(1);
            }
            
            st.close();
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public String getAsSVG(String cidade){
        try{
            Connection con = ConFactory.getConnection();
            PreparedStatement st = con.prepareStatement("SELECT ST_AsSVG(geom) FROM municipio WHERE nome ILIKE ?");
            st.setString(1, cidade);
            
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                return rs.getString(1);
            }
            
            st.close();
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
