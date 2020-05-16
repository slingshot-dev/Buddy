package com.example.PayMyBuddy.modeles;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "moyen_paiement")
public class MoyenPaiement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pk_moyen_paiement")
    private int idmoyenPaie;

    @NotNull
    @Column(name = "moyen_paiement_type", nullable = false)
    private String moyenType;

    @NotNull
    @Column(name = "fk_user", nullable = false)
    private int fkuser;


    public int getIdmoyenPaie() {
        return idmoyenPaie;
    }

    public void setIdmoyenPaie(int idmoyenPaie) {
        this.idmoyenPaie = idmoyenPaie;
    }

    public String getMoyenType() {
        return moyenType;
    }

    public void setMoyenType(String moyenType) {
        this.moyenType = moyenType;
    }

    public int getFkuser() {
        return fkuser;
    }

    public void setFkuser(int fkuser) {
        this.fkuser = fkuser;
    }
}
