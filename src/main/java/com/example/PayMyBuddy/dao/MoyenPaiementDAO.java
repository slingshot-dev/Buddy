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
import java.sql.SQLException;


@Repository
public class MoyenPaiementDAO implements IMoyenPaiementDAO {

    public DataBaseConfig dataBaseConfig = new DataBaseConfig();
    private static final Logger logger = LogManager.getLogger("MoyenPaiementDAO");

    @Override
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

    @Override
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

    @Override
    public void addMoyenPaiementTransac(AccountFull account) throws SQLException {

        Connection con = null;
        PreparedStatement ps = null;
        boolean transactionOk = false;
        ResultSet rs;
        int result = 0;
        try {
            con = dataBaseConfig.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(DBConstants.ADD_MOYENP);

            ps.setString(1, account.getMoyenType());
            ps.setInt(2, account.getFkUser());
            ps.execute();

            ps = con.prepareStatement(DBConstants.GET_LastID_MoyenPaiement);
            rs = ps.executeQuery();
            rs.next();
            result = rs.getInt(1);
            int result2 = 0; // pour test d'echec de la transacation

            ps = con.prepareStatement(DBConstants.ADD_RIB);
            ps.setString(1, account.getRibNom());
            ps.setString(2, account.getRibNumber());
            ps.setInt(3, result);
            ps.execute();

            transactionOk = true;
            con.commit();

        } catch (Exception ex) {
            logger.error("Error lors de l'ajout d'un moyen de paiement", ex);
            if (transactionOk){
                con.rollback();
            }
            else {
            logger.error("error before commit");
            }

        } finally {
            dataBaseConfig.closePreparedStatement(ps);
            dataBaseConfig.closeConnection(con);
        }
    }
}
