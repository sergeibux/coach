package com.example.binou.coach.controleur;

import android.util.Log;

import com.example.binou.coach.modele.Profil;

public final class Controle {
    // instance unique de la classe
    private static Controle instance = null;
    private static Profil profil;

    // constructeur
    private Controle() {
        super();
    }

    public final static Controle getInstance(){
        if (instance==null) {
            Controle.instance = new Controle();
        }
        return Controle.instance;
    }

    /**
     * Création de profil
     * @param iPoids
     * @param iTaille
     * @param iAge
     * @param iSexe
     */
    public void creerProfil (Integer iPoids, Integer iTaille, Integer iAge, Integer iSexe){
        Log.d("\n#p,t,a,s:", iPoids+", "+iTaille+", "+iAge+", "+iSexe);
        profil = new Profil(iPoids, iTaille, iAge, iSexe);
    }

    /**
     *
     * @return IMG
     */
    public float getImg(){
        return profil.getfImg();
    }

    /**
     *
     * @return message
     */
    public String getMsg(){
        return profil.getStrMsg();
    }
}
