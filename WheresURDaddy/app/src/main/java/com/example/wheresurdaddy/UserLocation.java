package com.example.wheresurdaddy;

public class UserLocation {
    String id;
   Double dolzina;
   Double sirina;


    String komentar;
    public UserLocation(){}

    public UserLocation(String id, Double dolzina, Double sirina, String komentar) {
        this.id = id;
        this.dolzina = dolzina;
        this.sirina = sirina;
        this.komentar= komentar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getDolzina() {
        return dolzina;
    }

    public void setDolzina(Double dolzina) {
        this.dolzina = dolzina;
    }

    public Double getSirina() {
        return sirina;
    }

    public void setSirina(Double sirina) {
        this.sirina = sirina;
    }
    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

}
