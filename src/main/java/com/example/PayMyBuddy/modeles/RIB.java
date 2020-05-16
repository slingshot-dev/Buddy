package com.example.PayMyBuddy.modeles;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "rib")
public class RIB {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pk_rib")
    private int idrib;

    @NotNull
    @Column(name = "rib_nom", nullable = false)
    private String ribNom;

    @NotNull
    @Column(name = "rib_number", nullable = false)
    private String ribNumber;

    @NotNull
    @Column(name = "fk_moyen_paiement", nullable = false)
    private int fkMoyenP;


    public int getIdrib() {
        return idrib;
    }

    public void setIdrib(int idrib) {
        this.idrib = idrib;
    }

    public String getRibNom() {
        return ribNom;
    }

    public void setRibNom(String ribNom) {
        this.ribNom = ribNom;
    }

    public String getRibNumber() {
        return ribNumber;
    }

    public void setRibNumber(String ribNumber) {
        this.ribNumber = ribNumber;
    }

    public int getFkMoyenP() {
        return fkMoyenP;
    }

    public void setFkMoyenP(int fkMoyenP) {
        this.fkMoyenP = fkMoyenP;
    }
}
