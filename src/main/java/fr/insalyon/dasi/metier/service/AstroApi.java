/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.ProfilAstral;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class AstroApi {
    
    public static final String MA_CLE_ASTRO_API = "ASTRO-02-M0lGLURBU0ktQVNUUk8tQjAy";

    public static final String ENCODING_UTF8 = "UTF-8";
    public static final SimpleDateFormat JSON_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static final String ASTRO_API_URL
            = "https://servif-cocktail.insa-lyon.fr/WebDataGenerator/Astro";
    
    /**
     * Renvoie le profil astral du client passé en paramètre.
     * @param client
     * @return Un ProfilAstral
     * @throws java.io.IOException
     */
    public static ProfilAstral recupererProfil(Client client) throws IOException {
        JsonObject response = post(
                ASTRO_API_URL,
                new BasicNameValuePair("service", "profil"),
                new BasicNameValuePair("key", MA_CLE_ASTRO_API),
                new BasicNameValuePair("prenom", client.getPrenom()),
                new BasicNameValuePair("date-naissance", JSON_DATE_FORMAT.format(client.getDateNaissance()))
        );

        JsonObject profil = response.get("profil").getAsJsonObject();
        
        String zodiaque = profil.get("signe-zodiaque").getAsString();
        String zodiaqueChinois = profil.get("signe-chinois").getAsString();
        String couleur = profil.get("couleur").getAsString();
        String animal = profil.get("animal").getAsString();
        
        ProfilAstral result = new ProfilAstral(zodiaque, zodiaqueChinois, couleur, animal);
        
        return result;
    }
    
    public static List<String> getPredictions(Client client, int amour, int sante, int travail) throws IOException {

        ArrayList<String> result = new ArrayList<>();
        ProfilAstral profil = client.getProfilAstral();
        
        JsonObject response = post(
                ASTRO_API_URL,
                new BasicNameValuePair("service", "predictions"),
                new BasicNameValuePair("key", MA_CLE_ASTRO_API),
                new BasicNameValuePair("couleur", profil.getCouleur()),
                new BasicNameValuePair("animal", profil.getAnimal()),
                new BasicNameValuePair("niveau-amour", Integer.toString(amour)),
                new BasicNameValuePair("niveau-sante", Integer.toString(sante)),
                new BasicNameValuePair("niveau-travail", Integer.toString(travail))
        );

        if (!response.get("profil-valide").getAsBoolean()) {
            throw new IllegalArgumentException("Profil du client invalide, communication avec AstropAPI impossible.");
        }
        
        result.add(response.get("prediction-amour").getAsString());
        result.add(response.get("prediction-sante").getAsString());
        result.add(response.get("prediction-travail").getAsString());

        return result;
    }

    /* Méthode interne pour réaliser un appel HTTP et interpréter le résultat comme Objet JSON */
    protected static JsonObject post(String url, NameValuePair... parameters) throws IOException {

        JsonElement responseElement;
        
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            responseElement = null;
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(Arrays.asList(parameters), ENCODING_UTF8));
            
            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                HttpEntity entity = response.getEntity();
                
                if (entity != null) {
                    try (JsonReader jsonReader = new JsonReader(new InputStreamReader(entity.getContent(), ENCODING_UTF8))) {
                        JsonParser parser = new JsonParser();
                        responseElement = parser.parse(jsonReader);
                    }
                }
            }
        }

        JsonObject responseContainer = null;
        try {
            if (responseElement != null) {
                responseContainer = responseElement.getAsJsonObject();
            }
        } catch (IllegalStateException ex) {
            throw new IOException("Wrong HTTP Response Format - not a JSON Object (bad request?)", ex);
        }

        return responseContainer;
    }
}
