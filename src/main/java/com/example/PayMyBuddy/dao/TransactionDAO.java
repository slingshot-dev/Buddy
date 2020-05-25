package com.example.PayMyBuddy.dao;

import com.example.PayMyBuddy.config.DataBaseConfig;
import com.example.PayMyBuddy.constants.DBConstants;
import com.example.PayMyBuddy.modeles.TransacEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import java.sql.*;


@Repository
public class TransactionDAO implements ITransactionDAO {

    private static final Logger logger = LogManager.getLogger("UserDAO");
    public DataBaseConfig dataBaseConfig = new DataBaseConfig();


    @Override
    public void addTransaction(TransacEmail transactions) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);

        try {
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.GET_USER);
            ps.setString(1, transactions.getEmailPayeur());
            rs = ps.executeQuery();
            rs.next();
            int resultPayeur = rs.getInt(1);

            ps = con.prepareStatement(DBConstants.GET_USER);
            ps.setString(1, transactions.getEmailPaye());
            rs = ps.executeQuery();
            rs.next();
            int resultPaye = rs.getInt(1);

            ps = con.prepareStatement(DBConstants.ADD_TRANSAC);
            ps.setTimestamp(1, timestamp);
            ps.setInt(2, resultPayeur);
            ps.setInt(3, resultPaye);
            ps.setDouble(4, transactions.getMontant());
            ps.setDouble(5, transactions.getMontantPaye());
            ps.setDouble(6, transactions.getPrelevement());
            ps.setString(7, transactions.getTransacType());
            ps.execute();

        } catch (Exception ex) {
            logger.error("Error ", ex);
        } finally {
            dataBaseConfig.closePreparedStatement(ps);
            dataBaseConfig.closeConnection(con);
        }
    }


    @Override
    public double calculateTransaction(int user, int userBuddy) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs;
        double resultBalance = 0;
        double resultBalancePayeur = 0;
        double resultBalancePaye = 0;
        double resultBalanceCharge = 0;

        try {
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.CALC_BALANCE_CHARGE);
            ps.setInt(1, user);
            ps.setInt(2,userBuddy);
            rs = ps.executeQuery();
            rs.next();
            resultBalanceCharge = rs.getDouble(1);

            ps = con.prepareStatement(DBConstants.CALC_BALANCE_PAYEUR);
            ps.setInt(1, user);
            rs = ps.executeQuery();
            rs.next();
            resultBalancePayeur = rs.getDouble(1);

            ps = con.prepareStatement(DBConstants.CALC_BALANCE_PAYE);
            ps.setInt(1, user);
            rs = ps.executeQuery();
            rs.next();
            resultBalancePaye = rs.getDouble(1);

            resultBalance = resultBalanceCharge - (resultBalancePayeur - resultBalanceCharge) - resultBalancePaye;

        } catch (Exception ex) {
            logger.error("Error ", ex);
        } finally {
            dataBaseConfig.closePreparedStatement(ps);
            dataBaseConfig.closeConnection(con);
        }
        return  resultBalance;
    }


    @Override
    public double calculateBalanceFonds() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs;
        double resultBalanceFonds = 0;
        double resultBanque = 0;
        double result = 0;

        try {
            con = dataBaseConfig.getConnection();

            ps = con.prepareStatement(DBConstants.CALC_BALANCE_FONDS);
            rs = ps.executeQuery();
            rs.next();
            resultBalanceFonds = rs.getDouble(1);

            ps = con.prepareStatement(DBConstants.CALC_BALANCE_Banque);
            rs = ps.executeQuery();
            rs.next();
            resultBanque = rs.getDouble(1);

            result = resultBalanceFonds - resultBanque;

        } catch (Exception ex) {
            logger.error("Error ", ex);
        } finally {
            dataBaseConfig.closePreparedStatement(ps);
            dataBaseConfig.closeConnection(con);
        }
        return  result;
    }


    @Override
    public double calculateBalancePrelev() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs;
        double resultBalancePrelev = 0;

        try {
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.CALC_BALANCE_PRELEV);
            rs = ps.executeQuery();
            rs.next();
            resultBalancePrelev = rs.getDouble(1);

        } catch (Exception ex) {
            logger.error("Error ", ex);
        } finally {
            dataBaseConfig.closePreparedStatement(ps);
            dataBaseConfig.closeConnection(con);
        }
        return  resultBalancePrelev;
    }


}
