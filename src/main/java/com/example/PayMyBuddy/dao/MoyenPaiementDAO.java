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
public class MoyenPaiementDAO {

    public DataBaseConfig dataBaseConfig = new DataBaseConfig();
    private static final Logger logger = LogManager.getLogger("MoyenPaiementDAO");

    public int addMoyenPaiement(AccountFull account) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result = 0;
        try {
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.ADD_MOYENP);

            ps.setString(1, account.getMoyenType());
            ps.setInt(2, account.getFkUser());
            ps.execute();

            ps = con.prepareStatement(DBConstants.GET_LastID_MoyenPaiement);
            rs = ps.executeQuery();
            rs.next();
            result = rs.getInt(1);

        } catch (Exception ex) {
            logger.error("Error lors de l'ajout d'un moyen de paiement", ex);
        } finally {
            dataBaseConfig.closePreparedStatement(ps);
            dataBaseConfig.closeConnection(con);
        }
        return result;
    }

    public void deleteMoyrenP(int moyenp) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = dataBaseConfig.getConnection();

            ps = con.prepareStatement(DBConstants.DELETE_MOYENP);
            ps.setInt(1, moyenp);
            ps.execute();

        } catch (Exception ex) {
            logger.error("Error lors de l'ajout d'un moyen de paiement", ex);
        } finally {
            dataBaseConfig.closePreparedStatement(ps);
            dataBaseConfig.closeConnection(con);
        }
    }

}
