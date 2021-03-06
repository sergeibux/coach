package com.example.binou.coach.modele;

import java.io.Serializable;

/**
 *
 * Created by Binou
 * Date 12-10-2018
 *
 */
public class Profil implements Serializable {

    //  Constantes
    private static final int minFemme = 15; // Maigre si en dessous
    private static final int maxFemme = 30; // Gros si en dessous
    private static final int minHomme = 10; // Maigre si en dessous
    private static final int maxHomme = 20; // Maigre si en dessous

    //  Variables
    private int iPoids, iTaille, iAge, iSexe;
    private float fImg;
    private String strMsg;

    /**
     * Constructeur de Profil
     * @param iPoids
     * @param iTaille
     * @param iAge
     * @param iSexe
     */
    public Profil (int iPoids, int iTaille, int iAge, int iSexe) {
        this.iPoids = iPoids;
        this.iTaille = iTaille;
        this.iAge = iAge;
        this.iSexe = iSexe;

        calculIMG();
        resultIMG();
    }

    //Getters
    /**
     *
     * @return poids
     */
    public int getiPoids() {
        return iPoids;
    }

    /**
     *
     * @return taille
     */
    public int getiTaille() {
        return iTaille;
    }

    /**
     *
     * @return age
     */
    public int getiAge() {
        return iAge;
    }

    /**
     *
     * @return sexe (1 = homme // 0 = femme)
     */
    public int getiSexe() {
        return iSexe;
    }

    /**
     *
     * @return IMG
     */
    public float getfImg() {
        return fImg;
    }

    /**
     *
     * @return message
     */
    public String getStrMsg() {
        return strMsg;
    }

    //  Méthodes
    /**
     * calculIMG()
     * IMG = (1,2 * Poids/Taille2) + (0,23 * age) - (10,83 * S) - 5,4
     * avec S=0 pour une femme, =1 pour un homme
     * taille en mètres mais que l'on saisira en cm pour éviter la saisie de la virgule.
     */
    private void calculIMG(){
        fImg = (((float)1.2 * (float)iPoids) / (((float)iTaille/100.0f)*((float)iTaille/100.0f))) + ((float)0.23 * ((float)iAge)) - ((float)10.83 * ((float)iSexe)) - (float)5.4;
    }


    /**
     * resultIMG()
     * Afiche le message
     */
    private void resultIMG(){
        if (iSexe==1){     // c'est un homme
            if (fImg<minHomme){
                strMsg = "trop faible";
            } else if (fImg>maxHomme){
                strMsg = "trop élevé";
            } else {
                strMsg = "normal";
            }
        } else {         // c'est une femme
            if (fImg<minFemme){
                strMsg = "trop faible";
            } else if (fImg>maxFemme){
                strMsg = "trop élevé";
            } else {
                strMsg = "normal";
            }
        }
    }
}
