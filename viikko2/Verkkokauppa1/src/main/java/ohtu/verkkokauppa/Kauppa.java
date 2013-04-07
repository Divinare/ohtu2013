package ohtu.verkkokauppa;

public class Kauppa {

    private VarastoRajapinta varasto;
    private PankkiRajapinta pankki;
    private Ostoskori ostoskori;
    private ViitegeneraattoriRajapinta viitegeneraattori;
    private String kaupanTili;

    public Kauppa(VarastoRajapinta v, PankkiRajapinta p, ViitegeneraattoriRajapinta viiteg) {
        varasto = v;
        pankki = p;
        viitegeneraattori = viiteg;
        kaupanTili = "33333-44455";
        System.out.println("lol");
        System.out.println("lol");
        System.out.println("lol");
        System.out.println("lol");
        System.out.println("lol");
        System.out.println("lol");
        System.out.println("lol");
        System.out.println("lol");
        System.out.println("lol");
        if (true) {
            if (true) {
                System.out.println("sisäkkäinen if");
            }
        }
                for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.println("jeejee");
            }
        }
    }

    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
    }

    public void poistaKorista(int id) {
        Tuote t = varasto.haeTuote(id);
        varasto.palautaVarastoon(t);
    }

    public void lisaaKoriin(int id) {
        if (varasto.saldo(id) > 0) {
            Tuote t = varasto.haeTuote(id);
            ostoskori.lisaa(t);
            varasto.otaVarastosta(t);
        }
    }

    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = viitegeneraattori.uusi();
        int summa = ostoskori.hinta();

        return pankki.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }
}
