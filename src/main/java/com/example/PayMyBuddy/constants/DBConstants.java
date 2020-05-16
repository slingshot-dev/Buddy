package com.example.PayMyBuddy.constants;

public class DBConstants {

    public static final String INSCRIPTION_USER = "insert into user(user_email, user_nom, user_prenom, user_password, user_role) values(?,?,?,?,?)";
    public static final String UPDATE_USER = "UPDATE user SET user_email=?, user_nom=?, user_prenom=?, user_password=? where pk_user=?";
    public static final String CHECK_USER = "select count(*) from user where user_email=?";
    public static final String DELETE_USER = "delete from user where user_email=?";
    public static final String CHECK_AMIS_LIST = "select count(*) from amis where amis_user=? && amis_amis=?";
    public static final String GET_USER = "select pk_user, user_email, user_nom, user_prenom, user_password, user_role from user where user_email=?";
    public static final String ADD_USER_LIST = "insert into amis(amis_user, amis_amis) values(?,?)";
    public static final String ADD_TRANSAC = "insert into transactions(transaction_date, transaction_user_payeur, transaction_user_paye, transaction_montant, transaction_prelevement, transaction_type) values(?,?,?,?,?,?)";
    public static final String CALC_BALANCE_CHARGE = "select SUM(transaction_montant) from transactions where transaction_user_payeur=? and transaction_user_paye=?";
    public static final String CALC_BALANCE_PAYEUR = "select SUM(transaction_montant) from transactions where transaction_user_payeur=?";
    public static final String CALC_BALANCE_PAYE = "select SUM(transaction_montant) from transactions where transaction_user_paye=?";
    public static final String GET_LastID_MoyenPaiement = "SELECT LAST_INSERT_ID() from moyen_paiement;";
    public static final String ADD_MOYENP = "insert into moyen_paiement(moyen_paiement_type, fk_user) values(?,?)";
    public static final String ADD_CB = "insert into cb(cb_nom, cb_number, cb_validite, fk_moyen_paiement) values(?,?,?,?)";
    public static final String ADD_RIB = "insert into rib(rib_nom, rib_number, fk_moyen_paiement) values(?,?,?)";

}
