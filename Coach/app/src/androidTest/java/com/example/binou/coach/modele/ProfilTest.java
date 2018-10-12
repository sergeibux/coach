package com.example.binou.coach.modele;

import android.media.Image;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProfilTest {

    // création d'un profil : femme de 67kg, 1m65, 35 ans
    private Profil profil = new Profil(67, 165, 35, 0);

    // résultat de l'img correspondant
    private float img = (float)32.2 ;
    // message correspondant
    private String message = "trop élevé" ;


    @Test
    public void getfImg() {
        assertEquals(img, profil.getfImg(), (float)0.1);
    }

    @Test
    public void getStrMsg() {
        assertEquals(message, profil.getStrMsg());
    }
}