package com.example.PayMyBuddy.dao;

import com.example.PayMyBuddy.config.DataBaseConfig;
import com.example.PayMyBuddy.constants.DBConstants;
import com.example.PayMyBuddy.modeles.AccountFull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class RIBDao implements IRIBDao {

    private static final Logger logger = LogManager.getLogger("RIBDao");
    public DataBaseConfig dataBaseConfig = new DataBaseConfig();


    @Override
    public void addRIB(AccountFull account, int result) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = dataBaseConfig.getConnection();

            ps = con.prepareStatement(DBConstants.ADD_RIB);
            ps.setString(1, account.getRibNom());
            ps.setString(2, account.getRibNumber());
            ps.setInt(3, result);
            ps.execute();

        } catch (Exception ex) {
            logger.error("Error lors de l'ajout d'un moyen de paiement", ex);
        } finally {
            dataBaseConfig.closePreparedStatement(ps);
            dataBaseConfig.closeConnection(con);
        }
    }

    @Override
    public void deleteRIB(int rib) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = dataBaseConfig.getConnection();

            ps = con.prepareStatement(DBConstants.DELETE_RIB);
            ps.setInt(1, rib);
            ps.execute();

        } catch (Exception ex) {
            logger.error("Error lors de l'ajout d'un moyen de paiement", ex);
        } finally {
            dataBaseConfig.closePreparedStatement(ps);
            dataBaseConfig.closeConnection(con);
        }
    }

}
