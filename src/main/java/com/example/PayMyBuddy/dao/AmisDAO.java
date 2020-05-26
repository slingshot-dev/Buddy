package com.example.PayMyBuddy.dao;

import com.example.PayMyBuddy.config.DataBaseConfig;
import com.example.PayMyBuddy.constants.DBConstants;
import com.example.PayMyBuddy.modeles.Amis;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class AmisDAO implements IAmisDAO {


    private static final Logger logger = LogManager.getLogger("AmisDAO");
    public DataBaseConfig dataBaseConfig = new DataBaseConfig();

    @Override
    public void addList(Amis amis) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.ADD_USER_LIST);

            ps.setInt(1, amis.getAmisUser());
            ps.setInt(2, amis.getAmisAmis());
            ps.execute();

        } catch (Exception ex) {
            logger.error("Error fetching next available slot", ex);
        } finally {
            dataBaseConfig.closePreparedStatement(ps);
            dataBaseConfig.closeConnection(con);
        }
    }

    @Override
    public boolean checkAmisList(Amis amis) {
        Connection con = null;
        int test=0;
        boolean rec = false;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.CHECK_AMIS_LIST);
            ps.setInt(1, amis.getAmisUser());
            ps.setInt(2, amis.getAmisAmis());
            rs = ps.executeQuery();
            rs.next();
            test = rs.getInt(1);
            if(test>0){rec = true;}
        }catch (Exception ex) {
            logger.error("Error", ex);
        }finally {
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
            dataBaseConfig.closeConnection(con);
        }
        return rec;
    }

    @Override
    public void deleteAmis(int delete) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.DELETE_AMIS);

            ps.setInt(1, delete);
            ps.execute();

        } catch (Exception ex) {
            logger.error("Error Delete Amis", ex);
        } finally {
            dataBaseConfig.closePreparedStatement(ps);
            dataBaseConfig.closeConnection(con);
        }
    }


}
