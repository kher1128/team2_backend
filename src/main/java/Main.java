import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {

        StringBuilder urlBuilder = new StringBuilder("https://sandbox-apigw.koscom.co.kr/v2/market/stocks/{marketcode}/index".replace("{marketcode}", URLEncoder.encode("kospi", "UTF-8")));
        urlBuilder.append("?");
        urlBuilder.append(URLEncoder.encode("apikey","UTF-8") + "=" + URLEncoder.encode("l7xx1340964a7c3242339f76f2d6a983bdf0", "UTF-8"));
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        String json = sb.toString();
        JSONParser jsonParser = new JSONParser();

        JSONObject result = (JSONObject) jsonParser.parse(json);
        System.out.println(result.get("result").toString());


    }
}
