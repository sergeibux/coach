package com.example.binou.coach.controleur;

import android.content.Context;
import android.util.Log;

import com.example.binou.coach.modele.Profil;

import static com.example.emds.coach.outils.Serializer.deSerialize;

public final class Controle {
    // instance unique de la classe
    private static Controle instance = null;
    private static Profil profil;
    private static String nomFic = "saveprofile";

    // constructeur
    private Controle() {
        super();
    }

    /**
     * Ne permettre qu'une instance
     * @return l'instance de controle pour l'affichage
     */
    public final static Controle getInstance(Context context){
        if (instance==null) {
            Controle.instance = new Controle();
            recupSerialize(context);
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
    public void creerProfil (Integer iPoids, Integer iTaille, Integer iAge, Integer iSexe, Context context){

        profil = new Profil(iPoids, iTaille, iAge, iSexe);

        com.example.emds.coach.outils.Serializer.serialize(nomFic, profil, context);
    }

    public static void recupSerialize(Context context){
        profil = (Profil)deSerialize(nomFic, context);

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

    /**
     *
     * @return poids
     */
    public Integer getPoids(){
        if (profil == null){
            return null;
        } else {
            return profil.getiPoids();
        }
    }

    /**
     *
     * @return taille
     */
    public Integer getTaille(){
        if (profil == null){
            return null;
        } else {
            return profil.getiTaille();
        }
    }

    /**
     *
     * @return age
     */
    public Integer getAge(){
        if (profil == null){
            return null;
        } else {
            return profil.getiAge();
        }
    }

    /**
     *
     * @return sexe
     */
    public Integer getSexe(){
        if (profil == null){
            return null;
        } else {
            return profil.getiSexe();
        }
    }
}
