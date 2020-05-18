package com.example.PayMyBuddy.dao;

import com.example.PayMyBuddy.config.DataBaseConfig;
import com.example.PayMyBuddy.constants.DBConstants;
import com.example.PayMyBuddy.modeles.AccountFull;
import com.example.PayMyBuddy.modeles.CB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class CBDao {

    private static final Logger logger = LogManager.getLogger("CBDao");
    public DataBaseConfig dataBaseConfig = new DataBaseConfig();


    public void addCB(AccountFull account, int result) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = dataBaseConfig.getConnection();

            ps = con.prepareStatement(DBConstants.ADD_CB);
            ps.setString(1, account.getCbNom());
            ps.setString(2, account.getCbNumber());
            ps.setString(3, account.getCbValide());
            ps.setInt(4, result);
            ps.execute();

        } catch (Exception ex) {
            logger.error("Error lors de l'ajout d'un moyen de paiement", ex);
        } finally {
            dataBaseConfig.closePreparedStatement(ps);
            dataBaseConfig.closeConnection(con);
        }
    }

    public void deleteCB(int cb) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = dataBaseConfig.getConnection();

            ps = con.prepareStatement(DBConstants.DELETE_CB);
            ps.setInt(1, cb);
            ps.execute();

        } catch (Exception ex) {
            logger.error("Error lors de l'ajout d'un moyen de paiement", ex);
        } finally {
            dataBaseConfig.closePreparedStatement(ps);
            dataBaseConfig.closeConnection(con);
        }
    }


}
