package com.example.PayMyBuddy.modeles;

public class AccountFull {

    private String cbNom;
    private String cbNumber;
    private String cbValide;
    private String ribNom;
    private String ribNumber;
    private String moyenType;
    private String userMail;
    private int fkUser;


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

    public String getMoyenType() {
        return moyenType;
    }

    public void setMoyenType(String moyenType) {
        this.moyenType = moyenType;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public int getFkUser() {
        return fkUser;
    }

    public void setFkUser(int fkUser) {
        this.fkUser = fkUser;
    }
}
