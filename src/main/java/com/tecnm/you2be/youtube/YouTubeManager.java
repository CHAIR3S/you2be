package com.tecnm.you2be.youtube;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class YouTubeManager {


    private static final String API_KEY = "AIzaSyBpisUZaEUABRXlv_ucfpgx7cYkKVhW_zk";

    public static final String YOUTUBE_URL = "https://www.googleapis.com/youtube/v3/";


    public void getVideoById(String videoId){

        try(CloseableHttpClient httpClient = HttpClients.createDefault()){
//            URL url = new URL(YOUTUBE_URL);


            URIBuilder uriBuilder = new URIBuilder(YOUTUBE_URL);
            uriBuilder.setParameter("part", "snippet,contentDetails,statistics")
                    .setParameter("key", API_KEY)
                    .setParameter("id", videoId);

            HttpGet request = new HttpGet(uriBuilder.build());

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String responseBody = EntityUtils.toString(response.getEntity());
                System.out.println(responseBody);
            }


//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//            conn.setRequestMethod("GET");
//            conn.connect();
//
//            int responseCode = conn.getResponseCode();
//
//            if(responseCode != 200){
//                throw new RuntimeException("Ocurrio un error: " + responseCode);
//            }
//
//            StringBuilder informationString = new StringBuilder();
//
//            Scanner sc = new Scanner(url.openStream());
//
//            while (sc.hasNext()){
//                informationString.append(sc);
//            }
//
//            sc.close();
//
//            System.out.println(informationString);

        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
