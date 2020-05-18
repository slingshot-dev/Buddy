package com.example.PayMyBuddy.modeles;

import org.springframework.stereotype.Component;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Entity
@Table(name = "transactions")
@Component
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pk_transaction")
    private int idtransac;

    @NotNull
    @Column(name = "transaction_date", nullable = false, unique = true)
    private LocalDateTime transacDate;

    @NotNull
    @Column(name = "transaction_user_payeur", nullable = false, unique = true)
    private int transacPayeur;

    @NotNull
    @Column(name = "transaction_user_paye", nullable = false, unique = true)
    private int transacPaye;

    @NotNull
    @Column(name = "transaction_montant", nullable = false, unique = true)
    private double transacMontant;

    @NotNull
    @Column(name = "transaction_prelevement", nullable = false, unique = true)
    private double transacPrelevement;

    @NotNull
    @Column(name = "transaction_type", nullable = false, unique = true)
    private String transacType;



    public int getIdtransac() {
        return idtransac;
    }

    public void setIdtransac(int idtransac) {
        this.idtransac = idtransac;
    }

    public LocalDateTime getTransacDate() {
        return transacDate;
    }

    public void setTransacDate(LocalDateTime transacDate) {
        this.transacDate = transacDate;
    }

    public int getTransacPayeur() {
        return transacPayeur;
    }

    public void setTransacPayeur(int transacPayeur) {
        this.transacPayeur = transacPayeur;
    }

    public int getTransacPaye() {
        return transacPaye;
    }

    public void setTransacPaye(int transacPaye) {
        this.transacPaye = transacPaye;
    }

    public double getTransacMontant() {
        return transacMontant;
    }

    public void setTransacMontant(double transacMontant) {
        this.transacMontant = transacMontant;
    }

    public double getTransacPrelevement() {
        return transacPrelevement;
    }

    public void setTransacPrelevement(double transacPrelevement) {
        this.transacPrelevement = transacPrelevement;
    }

    public String getTransacType() {
        return transacType;
    }

    public void setTransacType(String transacType) {
        this.transacType = transacType;
    }
}
