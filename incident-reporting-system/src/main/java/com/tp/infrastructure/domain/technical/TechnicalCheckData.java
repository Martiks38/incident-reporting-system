package com.tp.infrastructure.domain.technical;

import java.util.List;
import java.util.UUID;

import com.tp.application.MediaNotification;
import com.tp.infrastructure.domain.specialty.Specialty;

public class TechnicalCheckData {

  private static String message;

  public static void check(Technical t){
    String id = t.getTechnical_id();
    int id_length = id.length();
    
    String name = t.getTechnical_name();
    String media = t.getMeans_notification();
    int number_incidents_resolved = t.getNumber_incidents_resolved();
    Long incident_resolution_speed = t.getIncident_resolution_speed();
    List<Specialty> specialties = t.getSpecialties();

    if(id_length != UUID.randomUUID().toString().length()){
      message += "El id del técnico no es válido.\n";
    }

    if(name == null || name.length() == 0){
      message += "El nombre del técnico no puede estar vacío.\n";
    }

    if(media == null){
      String mediaType = MediaNotification.MEANS_1.getMedia();

      t.setMeans_notification(mediaType);
    }

    if(number_incidents_resolved < 0){
      message += "El número de incidentes resultos no puede ser menor a cero (0).\n";
    }

    if(incident_resolution_speed < 0){
      message += "La velocidad de resolución de incidentes no puede ser menor a cero (0).\n";
    }

    if(specialties.size() == 0){
      message += "El técnico debe tener al menos una especialidad.\n";
    }

    if(message != null){
      throw new RuntimeException(message);
    }
  }
}
