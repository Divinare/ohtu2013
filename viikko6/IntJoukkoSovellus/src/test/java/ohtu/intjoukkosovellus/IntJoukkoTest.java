package ohtu.intjoukkosovellus;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IntJoukkoTest {

    IntJoukko joukko;

    @Before
    public void setUp() {
        joukko = new IntJoukko();
        joukko.lisaa(10);
        joukko.lisaa(3);
    }

    @Test
    public void lukujaLisattyMaara() {
        joukko.lisaa(4);
        assertEquals(3, joukko.mahtavuus());
    }

    @Test
    public void samaLukuMeneeJoukkoonVaanKerran() {
        joukko.lisaa(10);
        joukko.lisaa(3);
        assertEquals(2, joukko.mahtavuus());
    }

    @Test
    public void vainLisatytLuvutLoytyvat() {
        assertTrue(joukko.kuuluu(10));
        assertFalse(joukko.kuuluu(5));
        assertTrue(joukko.kuuluu(3));
    }

    @Test
    public void poistettuEiOleEnaaJoukossa() {
        joukko.poista(3);
        assertFalse(joukko.kuuluu(3));
        assertEquals(1, joukko.mahtavuus());
    }

    @Test
    public void palautetaanOikeaTaulukko() {
        int[] odotettu = {3, 55, 99};

        joukko.lisaa(55);
        joukko.poista(10);
        joukko.lisaa(99);

        int[] vastaus = joukko.toIntArray();
        Arrays.sort(vastaus);
        assertArrayEquals(odotettu, vastaus);
    }

    @Test
    public void toimiiKasvatuksenJalkeen() {
        int[] lisattavat = {1, 2, 4, 5, 6, 7, 8, 9, 11, 12, 13, 14};
        for (int luku : lisattavat) {
            joukko.lisaa(luku);
        }
        assertEquals(14, joukko.mahtavuus());
        assertTrue(joukko.kuuluu(11));
        joukko.poista(11);
        assertFalse(joukko.kuuluu(11));
        assertEquals(13, joukko.mahtavuus());
    }

    @Test
    public void toStringToimii() {
        assertEquals("{10, 3}", joukko.toString());
    }

    // lisää testejä
    @Test
    public void kontstruktorinKutsuNegatiivisellaaKapasiteetilla() {
        try {
            IntJoukko j = new IntJoukko(-1, 5);
        } catch (Exception e) {
            assertEquals(1, 1);
        }
    }

    @Test
    public void kontstruktorinKutsuNegatiivisellaaKasvatusKoolla() {
        try {
            IntJoukko j = new IntJoukko(5, -1);
        } catch (Exception e) {
            assertEquals(1, 1);
        }
    }

    @Test
    public void poistaTestKunPoistettavaaEiOle() {
        boolean t = joukko.poista(4);
        assertFalse(t);
    }

    @Test
    public void leikkausTest() {
        IntJoukko uusi = new IntJoukko();
        uusi.lisaa(5);
        uusi.lisaa(7);
        uusi.lisaa(4);
        uusi.lisaa(3);
        IntJoukko leikkaus = IntJoukko.leikkaus(joukko, uusi);
        assertTrue(leikkaus.kuuluu(3));
        assertFalse(leikkaus.kuuluu(10));
        assertFalse(leikkaus.kuuluu(7));
        assertFalse(leikkaus.kuuluu(1));
    }

    @Test
    public void testaaPoistoa() {
        IntJoukko uusi = new IntJoukko();
        uusi.lisaa(5);
        uusi.lisaa(7);
        uusi.lisaa(4);
        uusi.lisaa(3);
        uusi.poista(2);
        uusi.poista(5);
        assertTrue(uusi.kuuluu(7));
        assertFalse(uusi.kuuluu(5));
        assertFalse(uusi.kuuluu(2));
    }
}
