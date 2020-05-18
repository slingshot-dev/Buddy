package com.example.PayMyBuddy.dao;

import com.example.PayMyBuddy.config.DataBaseConfig;
import com.example.PayMyBuddy.constants.DBConstants;
import com.example.PayMyBuddy.modeles.Transactions;
import com.example.PayMyBuddy.modeles.TransacEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


@Repository
public class TransactionDAO {

    private static final Logger logger = LogManager.getLogger("UserDAO");
    public DataBaseConfig dataBaseConfig = new DataBaseConfig();

    private final Transactions transactionsfull;


    DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public TransactionDAO(Transactions transactionsfull) {
        this.transactionsfull = transactionsfull;
    }


    public void addTransaction(TransacEmail transactions) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

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
            ps.setDouble(5, transactions.getPrelevement());
            ps.setString(6, transactions.getTransacType());

            ps.execute();

        } catch (Exception ex) {
            logger.error("Error ", ex);
        } finally {
            dataBaseConfig.closePreparedStatement(ps);
            dataBaseConfig.closeConnection(con);
        }
    }


    public float calculateTransaction(int user, int userBuddy) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        float resultBalance = 0;
        float resultBalancePayeur = 0;
        float resultBalancePaye = 0;
        float resultBalanceCharge = 0;

        try {
            con = dataBaseConfig.getConnection();

            ps = con.prepareStatement(DBConstants.CALC_BALANCE_CHARGE);
            ps.setInt(1, user);
            ps.setInt(2,userBuddy);
            rs = ps.executeQuery();
            rs.next();
            resultBalanceCharge = rs.getInt(1);

            ps = con.prepareStatement(DBConstants.CALC_BALANCE_PAYEUR);
            ps.setInt(1, user);
            rs = ps.executeQuery();
            rs.next();
            resultBalancePayeur = rs.getInt(1);

            ps = con.prepareStatement(DBConstants.CALC_BALANCE_PAYE);
            ps.setInt(1, user);
            rs = ps.executeQuery();
            rs.next();
            resultBalancePaye = rs.getInt(1);

            resultBalance = resultBalanceCharge - (resultBalancePayeur - resultBalanceCharge) + resultBalancePaye;


        } catch (Exception ex) {
            logger.error("Error ", ex);
        } finally {
            dataBaseConfig.closePreparedStatement(ps);
            dataBaseConfig.closeConnection(con);
        }
        return  resultBalance;
    }
}
