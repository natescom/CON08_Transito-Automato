package com.nathan.util;

import javafx.scene.image.Image;
/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 23/03/2021
 * Ultima alteracao: 27/03/2021
 * Nome: Gallery
 * Funcao: Lista e instanica todas as imagens para serem utilizadas
 * no ImageView da classe PeopleGUI
 * ***************************************************************/
public abstract class Gallery {

  private static final String path = "/com/nathan/res/img";

  public static final Image AVATAR_MARIO = new Image(Gallery.class.getResourceAsStream(path+"/mario.gif"));
  public static final Image AVATAR_LUIGI = new Image(Gallery.class.getResourceAsStream(path+"/luigi.gif"));
  public static final Image AVATAR_PEACH = new Image(Gallery.class.getResourceAsStream(path+"/peach.gif"));
  public static final Image AVATAR_TOAD = new Image(Gallery.class.getResourceAsStream(path+"/toad.gif"));
  public static final Image AVATAR_YOSHI = new Image(Gallery.class.getResourceAsStream(path+"/yoshi.gif"));
  public static final Image AVATAR_WARIO = new Image(Gallery.class.getResourceAsStream(path+"/wario.gif"));
  public static final Image AVATAR_GOOMBA = new Image(Gallery.class.getResourceAsStream(path+"/goomba.gif"));
  public static final Image AVATAR_KOOPA = new Image(Gallery.class.getResourceAsStream(path+"/koopa.gif"));

}
