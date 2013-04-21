package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;

public class Main {

    public static void main(String[] args) throws IOException {
        String studentNr = "14029938";
        if (args.length > 0) {
            studentNr = args[0];
        }
//        studentNr = "14029938";
        String url = "http://ohtustats-2013.herokuapp.com/opiskelija/" + studentNr + ".json";

        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        client.executeMethod(method);

        InputStream stream = method.getResponseBodyAsStream();

        String bodyText = IOUtils.toString(stream);

//        System.out.println("json-muotoinen data:");
//        System.out.println(bodyText);

        Gson mapper = new Gson();
        Palautukset palautukset = mapper.fromJson(bodyText, Palautukset.class);

//        System.out.println("oliot:");
//        for (Palautus palautus : palautukset.getPalautukset()) {
//            System.out.println(palautus);
//        }
        int indeksi = 0;
        int yhtT = 0;
        int yhtA = 0;
        System.out.println("opiskelijanumero " + palautukset.getPalautukset().get(0).getOpiskelijanumero());
        System.out.println("");
        for (Palautus palautus : palautukset.getPalautukset()) {
            System.out.println("viikko " + palautus.getViikko() + ": " + palautus.getTehtavia() + " tehtävää "
                    + palautus.getTehtavat() + "        aikaa kului " + palautus.getTunteja() + " tuntia");
            indeksi++;
            yhtT += palautus.getTunteja();
            yhtA += palautus.getTehtavia();
        }
        System.out.println("");
        System.out.println("yhteensä " + yhtT + " tehtävää " + yhtA + " tuntia");

    }
}