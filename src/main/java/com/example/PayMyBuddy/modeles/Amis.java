package com.example.PayMyBuddy.modeles;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "amis")
public class Amis {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pk_amis")
    private int idamis;

    @NotNull
    @Column(name = "amis_user", nullable = false)
    private int amisUser;

    @NotNull
    @Column(name = "amis_amis", nullable = false)
    private int amisAmis;


    public int getIdamis() {
        return idamis;
    }

    public void setIdamis(int idamis) {
        this.idamis = idamis;
    }

    public int getAmisUser() {
        return amisUser;
    }

    public void setAmisUser(int amisUser) {
        this.amisUser = amisUser;
    }

    public int getAmisAmis() {
        return amisAmis;
    }

    public void setAmisAmis(int amisAmis) {
        this.amisAmis = amisAmis;
    }
}
