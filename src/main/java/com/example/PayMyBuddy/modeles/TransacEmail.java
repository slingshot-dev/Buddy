package com.example.PayMyBuddy.modeles;

import org.springframework.stereotype.Component;

@Component
public class TransacEmail {

    private String emailPayeur;

    private String emailPaye;

    private double montant;

    private double prelevement;

    private String transacType;



    public String getEmailPayeur() {
        return emailPayeur;
    }

    public void setEmailPayeur(String emailPayeur) {
        this.emailPayeur = emailPayeur;
    }

    public String getEmailPaye() {
        return emailPaye;
    }

    public void setEmailPaye(String emailPaye) {
        this.emailPaye = emailPaye;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public double getPrelevement() {
        return prelevement;
    }

    public void setPrelevement(double prelevement) {
        this.prelevement = prelevement;
    }

    public String getTransacType() {
        return transacType;
    }

    public void setTransacType(String transacType) {
        this.transacType = transacType;
    }
}
