/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webkauppa.ohjaus;

import com.mycompany.webkauppa.ohjaus.OstoksenLisaysKoriin;
import com.mycompany.webkauppa.ohjaus.OstoksenPoistoKorista;
import com.mycompany.webkauppa.sovelluslogiikka.Ostoskori;

public class Komentotehdas {

    public static OstoksenLisaysKoriin ostoksenLisaysKoriin(Ostoskori ostoskori, long tuoteId) {
        return new OstoksenLisaysKoriin(ostoskori, tuoteId);
    }

    public static OstoksenPoistoKorista poistaKorista(Ostoskori ostoskori, long tuoteId) {
        return new OstoksenPoistoKorista(ostoskori, tuoteId);
    }
    
    public static OstoksenSuoritus suoritaOstos(String nimi, String osoite, String luottokorttinumero, Ostoskori ostoskori) {
        return new OstoksenSuoritus(nimi, osoite, luottokorttinumero, ostoskori);
    }
}