package com.veciapp.veciapp.Util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Util {



    public static void toJsonPrint(String title, Object object) throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String formattedJson = objectMapper.writeValueAsString(object);
        System.out.println(title + " : " + formattedJson);


    }

    public static String obtenerTiempo(Date date) {
        // Convertir Date a LocalDateTime
        LocalDateTime fechaIngresada = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime ahora = LocalDateTime.now();

        // Calcular las diferencias en cada unidad de tiempo
        long anos = ChronoUnit.YEARS.between(fechaIngresada, ahora);
        if (anos > 0) {
            return "hace " + anos + " años";
        }

        long meses = ChronoUnit.MONTHS.between(fechaIngresada, ahora);
        if (meses > 0) {
            return  "hace " + meses + " meses";
        }

        long dias = ChronoUnit.DAYS.between(fechaIngresada, ahora);
        if (dias > 0) {
            return  "hace " + dias + " días";
        }

        long horas = ChronoUnit.HOURS.between(fechaIngresada, ahora);
        if (horas > 0) {
            return  "hace " + horas + " horas";
        }

        long minutos = ChronoUnit.MINUTES.between(fechaIngresada, ahora);
        if (minutos > 0) {
            return  "hace " + minutos + " minutos";
        }

        long segundos = ChronoUnit.SECONDS.between(fechaIngresada, ahora);
        return "hace " + segundos + " segundos";
    }


}
