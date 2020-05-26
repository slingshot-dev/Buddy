package com.example.PayMyBuddy.dao;

import com.example.PayMyBuddy.config.DataBaseConfig;
import com.example.PayMyBuddy.constants.DBConstants;
import com.example.PayMyBuddy.modeles.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class UserDAO implements IUserDAO {

    @Autowired
    PasswordEncoder passwordEncoder;

    private static final Logger logger = LogManager.getLogger("UserDAO");
    public DataBaseConfig dataBaseConfig = new DataBaseConfig();

    @Override
    public void saveUser(User inscription) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.INSCRIPTION_USER);

            ps.setString(1, inscription.getEmail());
            ps.setString(2, inscription.getNom());
            ps.setString(3, inscription.getPrenom());
            ps.setString(4, passwordEncoder.encode(inscription.getPassword()));
            ps.setString(5, inscription.getRole());

            if (!checkUser(inscription.getEmail())) {
                logger.info("OK User inscris");
                ps.execute();

            } else {
                logger.error("Erreur, Email deja existant");
            }

        } catch (Exception ex) {
            logger.error("Error Lors de l'ajout de cet Utilisateur", ex);
        } finally {
            dataBaseConfig.closePreparedStatement(ps);
            dataBaseConfig.closeConnection(con);
        }
    }

    @Override
    public boolean checkUser(String user) {
        Connection con = null;
        int test = 0;
        boolean rec = false;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.CHECK_USER);
            ps.setString(1, user);
            rs = ps.executeQuery();
            rs.next();
            test = rs.getInt(1);
            if (test >= 1) {
                rec = true;
            }
        } catch (Exception ex) {
            logger.error("Error", ex);
        } finally {
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
            dataBaseConfig.closeConnection(con);
        }
        return rec;
    }

    @Override
    public User getUser(String email) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User result = new User();

        try {
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.GET_USER);

            ps.setString(1, email);
            rs = ps.executeQuery();
            rs.next();
            result.setIduser(rs.getInt(1));
            result.setPrenom(rs.getString(4));
            result.setNom(rs.getString(3));
            result.setEmail(email);
            result.setPassword(rs.getString(5));

        } catch (Exception ex) {
            logger.error("Error lors de la recuperation des information User", ex);
        } finally {
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
            dataBaseConfig.closeConnection(con);
        }
        return result;
    }

    @Override
    public void updateUser(User update) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.UPDATE_USER);

            ps.setString(1, update.getEmail());
            ps.setString(2, update.getNom());
            ps.setString(3, update.getPrenom());
            ps.setString(4, passwordEncoder.encode(update.getPassword()));
            ps.setInt(5, update.getIduser());
            ps.execute();

        } catch (Exception ex) {
            logger.error("Error Update User", ex);
        } finally {
            dataBaseConfig.closePreparedStatement(ps);
            dataBaseConfig.closeConnection(con);
        }
    }

    @Override
    public void deleteUser(String delete) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.DELETE_USER);

            ps.setString(1, delete);
            ps.execute();

        } catch (Exception ex) {
            logger.error("Error Update User", ex);
        } finally {
            dataBaseConfig.closePreparedStatement(ps);
            dataBaseConfig.closeConnection(con);
        }
    }
}