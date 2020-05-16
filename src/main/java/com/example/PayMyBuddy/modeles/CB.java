package com.example.PayMyBuddy.modeles;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cb")
public class CB {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pk_cb")
    private int idcb;

    @NotNull
    @Column(name = "cb_nom", nullable = false)
    private String cbNom;

    @NotNull
    @Column(name = "cb_number", nullable = false)
    private String cbNumber;

    @NotNull
    @Column(name = "cb_validite", nullable = false)
    private String cbValide;

    @NotNull
    @Column(name = "fk_moyen_paiement", nullable = false)
    private int fkMoyenP;


    public int getIdcb() {
        return idcb;
    }

    public void setIdcb(int idcb) {
        this.idcb = idcb;
    }

    public String getCbNom() {
        return cbNom;
    }

    public void setCbNom(String cbNom) {
        this.cbNom = cbNom;
    }

    public String getCbNumber() {
        return cbNumber;
    }

    public void setCbNumber(String cbNumber) {
        this.cbNumber = cbNumber;
    }

    public String getCbValide() {
        return cbValide;
    }

    public void setCbValide(String cbValide) {
        this.cbValide = cbValide;
    }

    public int getFkMoyenP() {
        return fkMoyenP;
    }

    public void setFkMoyenP(int fkMoyenP) {
        this.fkMoyenP = fkMoyenP;
    }
}
