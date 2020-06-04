package com.example.wheresurdaddy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class PrijavljenUser {
    String id;
    String email;
    Double dolzina;
    Double sirina;
    LocalDateTime datum;
    String komentar;

    public PrijavljenUser(){

    }

    public PrijavljenUser(String id, String email, Double dolzina, Double sirina, LocalDateTime datum, String komentar) {
        this.id = id;
        this.email = email;
        this.dolzina = dolzina;
        this.sirina = sirina;
        this.datum = datum;
        this.komentar=komentar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }
    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }
}
