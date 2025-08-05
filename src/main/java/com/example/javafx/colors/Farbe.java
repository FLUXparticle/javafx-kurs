package com.example.javafx.colors;

public enum Farbe {

   ROT("Rot"),
   GRUEN("Grün"),
   BLAU("Blau"),
   GRAU("Grau");

   private String text;

   private Farbe(String text) {
       this.text = text;
   }

   @Override
   public String toString() {
       return text;
   }

}

