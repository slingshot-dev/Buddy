package com.example.PayMyBuddy.modeles;

import com.fasterxml.jackson.annotation.JsonFilter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@JsonFilter("userObject")
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pk_user")
    private int iduser;

    @NotNull
    @Column(name = "user_email", nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(name = "user_nom", nullable = false, unique = false)
    private String nom;

    @NotNull
    @Column(name = "user_prenom", nullable = false, unique = false)
    private String prenom;

    @NotNull
    @Column(name = "user_password", nullable = false, unique = true)
    private String password;

    @NotNull
    @Column(name = "user_role", nullable = false, unique = false)
    private String role;



    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
}
