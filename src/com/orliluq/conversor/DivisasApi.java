package com.orliluq.conversor;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DivisasApi {

    private static final String API_KEY = "dbb474987b88b85239503560";

    public ConversionResponse obtenerConversion(String deDivisa, String aDivisa, double cantidad) {
        ConversionResponse response = new ConversionResponse();

        if (API_KEY == null || API_KEY.isEmpty()) {
            System.out.println("Error: La clave de API no está configurada. Por favor, establece la variable de entorno EXCHANGE_RATE_API_KEY.");
            return response;
        }

        try {
            String urlString = String.format(
                    "https://v6.exchangerate-api.com/v6/%s/pair/%s/%s/%s",
                    API_KEY, deDivisa, aDivisa, cantidad
            );

            URL url = new URL(urlString);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");
            conexion.connect();

            int respuesta = conexion.getResponseCode();
            if (respuesta != 200) {
                throw new RuntimeException("HttpResponseCode: " + respuesta);
            } else {
                JsonObject jsonObject = JsonParser.parseReader(new InputStreamReader(conexion.getInputStream())).getAsJsonObject();

                String estado = jsonObject.get("result").getAsString();
                if (!estado.equals("success")) {
                    throw new RuntimeException("Error en la respuesta de la API: " + estado);
                }

                response.setConversionResult(jsonObject.get("conversion_result").getAsDouble());
                response.setConversionRate(jsonObject.get("conversion_rate").getAsDouble());
                response.setLastUpdate(jsonObject.get("time_last_update_utc").getAsString());
            }

            conexion.disconnect();
        } catch (IOException e) {
            System.out.println("Error al conectar con la API: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Error en la API: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }

        return response;
    }
}
