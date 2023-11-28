package com.tp.application;

public enum MediaNotification {
  MEANS_1("Email"),
  MEANS_2("WhatsApp");

  private final String mean;

  MediaNotification(String mean){
    this.mean = mean;
  }

  public String getMedia(){
    return mean;
  }
}
