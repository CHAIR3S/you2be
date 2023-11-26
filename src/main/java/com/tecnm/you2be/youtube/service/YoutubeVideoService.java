package com.tecnm.you2be.youtube.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecnm.you2be.Environment;
import com.tecnm.you2be.youtube.models.Search;
import com.tecnm.you2be.youtube.models.Video;
import com.tecnm.you2be.youtube.models.YoutubeResponse;
import lombok.extern.java.Log;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.List;

/**
 * Clase encargada de realizar todas las busquedas a YouTube respecto a videos
 * @author luisf
 */
@Log
public class YoutubeVideoService {

    private ObjectMapper mapper = new ObjectMapper();


    private String responseBody;

    private TypeReference<YoutubeResponse<Video>> typeVideoClass = new TypeReference<>() {};


    private TypeReference<YoutubeResponse<Search>> typeSearchClass = new TypeReference<>() {};


    /**
     * Busca el video de YouTube por un Id dado
     * Ejemplo: "NEJ3bnieiIU"
     * @param videoId Id del video a buscar, se encuentra en dentro del link con el que se accede a el
     * @return YoutubeResponse<Video> Respuesta de la API convertida en objeto con items Video
     * @throws JsonProcessingException En caso de no mapear bien de JSON a Objeto
     */
    public YoutubeResponse<Video> getVideoById(String videoId) throws JsonProcessingException {

        try(CloseableHttpClient httpClient = HttpClients.createDefault()){


            URIBuilder uriBuilder = new URIBuilder(Environment.YOUTUBE_URL + "videos");
            //uriBuilder.setParameter("part", "snippet,contentDetails,statistics,player")

            uriBuilder.setParameter("part", "snippet,player,statistics")
                    .setParameter("key", Environment.API_KEY)
                    .setParameter("id", videoId);

            HttpGet request = new HttpGet(uriBuilder.build());


            log.info("getVideoById : {} " + videoId);

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                responseBody = EntityUtils.toString(response.getEntity());
            }


        }catch (Exception e){
            e.printStackTrace();
        }

        return mapper.readValue(responseBody, typeVideoClass);
    }


    /**
     * Busqueda general en YouTube
     * Ejemplo: "Videos de gatitos",
     * "Queso badon"
     * @param search String de la busqueda
     * @param maxResults Resultados maximos que se desean obtener por peticion
     * @return YoutubeResponse<Search> Respuesta de la API convertida en objeto con items Search
     * @throws JsonProcessingException En caso de no mapear bien de JSON a Objeto
     */
    public YoutubeResponse<Search> getVideoByTitle(String search, int maxResults) throws JsonProcessingException {

        try(CloseableHttpClient httpClient = HttpClients.createDefault()){


            URIBuilder uriBuilder = new URIBuilder(Environment.YOUTUBE_URL + "search");
            //uriBuilder.setParameter("part", "snippet,contentDetails,statistics,player")

            uriBuilder.setParameter("part", "snippet")
                    .setParameter("key", Environment.API_KEY)
                    .setParameter("q", search)
                    .setParameter("maxResults", String.valueOf(maxResults));

            HttpGet request = new HttpGet(uriBuilder.build());


            log.info("getVideoByTitle : {} " + search);

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                responseBody = EntityUtils.toString(response.getEntity());
            }

//            System.out.println(responseBody);

        }catch (Exception e){
            e.printStackTrace();
        }

        return mapper.readValue(responseBody, typeSearchClass);
    }


    /**
     * Busqueda general en YouTube especificando que pagina se debe traer por el Id
     * Ejemplo: "Videos de gatitos"
     * @implNote Las busquedas en YouTube devuelven un maximo de resultados que tu puedes especificar,
     * si la busqueda da 1000 resultados y pusiste un maximo de 10 resultados, la busqueda solo traera
     * 10 videos y traera un nextPageToken y prevPageToken con el id de la pagina, si se quiere traer
     * los 10 datos siguientes a la busqueda anterior, se especificara en este metodo el id de la pagina
     * @param search String de la busqueda
     * @param maxResults Resultados maximos que se desean obtener por peticion
     * @param nextPage Id de la pagina que se quiere traer de la busqueda
     * @return YoutubeResponse<Search> Respuesta de la API convertida en objeto con items Search
     * @throws JsonProcessingException En caso de no mapear bien de JSON a Objeto
     */
    public YoutubeResponse<Search> getVideoByTitleOtherPage(String search, int maxResults, String nextPage) throws JsonProcessingException {

        try(CloseableHttpClient httpClient = HttpClients.createDefault()){


            URIBuilder uriBuilder = new URIBuilder(Environment.YOUTUBE_URL + "search");
            //uriBuilder.setParameter("part", "snippet,contentDetails,statistics,player")

            uriBuilder.setParameter("part", "snippet")
                    .setParameter("key", Environment.API_KEY)
                    .setParameter("q", search)
                    .setParameter("pageToken", nextPage)
                    .setParameter("maxResults", String.valueOf(maxResults));

            HttpGet request = new HttpGet(uriBuilder.build());


            log.info("getVideoByTitleOtherPage : {} " + search);

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                responseBody = EntityUtils.toString(response.getEntity());
            }

//            System.out.println(responseBody);

        }catch (Exception e){
            e.printStackTrace();
        }

        return mapper.readValue(responseBody, typeSearchClass);
    }

}
