package com.nathan.util;

import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.shape.*;

import java.net.URL;
import java.util.ResourceBundle;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 09/06/2021
 * Ultima alteracao: 09/06/2021
 * Nome: Controller
 * Funcao: Controla as alteracoes da interface
 * ************************************************************** */
public class Controller implements Initializable {

  public AnchorPane pane;
  public Polyline polyline_lw;
  public Polyline polyline_el;
  public Polyline polyline_rv;
  public Polyline polyline_hc;
  public Polyline polyline_la;
  public Polyline polyline_ge;
  public Polyline polyline_fa;
  public Polyline polyline_hy;
  public Slider sld_lw;
  public Slider sld_el;
  public Slider sld_rv;
  public Slider sld_hc;
  public Slider sld_la;
  public Slider sld_ge;
  public Slider sld_fa;
  public Slider sld_hy;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // ----------- MAPA FINAL ----------- //
    Rua he0, he1, he2;          // Hebra
    Rua lw1, lw2, lw3;          // Lost Woods
    Rua el1, el2, el3;          // Eldin
    Rua ak0, ak1;               // Akkala
    Rua rv1, rv2, rv3, rv4;     // Rito Village
    Rua la1, la2, la3, la4;     // Lanayru
    Rua gf0, gf1, gf2, gf3;     // Great Fairy
    Rua ge1, ge2, ge3, ge4;     // Gerudp
    Rua fa1, fa2, fa3, fa4;     // Faron
    Rua ne0, ne1, ne2;          // Necluda

    int tamanhoMax = 1000;
    int tamanhoMin = 500;

    he0 = new Rua("Hebra 0", tamanhoMax, true);
    he1 = new Rua("Hebra 1", tamanhoMin, false);
    he2 = new Rua("Hebra 2", tamanhoMax, true);
    lw1 = new Rua("Lost Woods 1", tamanhoMax, false);
    lw2 = new Rua("Lost Woods 2", tamanhoMax, true);
    lw3 = new Rua("Lost Woods 3", tamanhoMin, false);
    el1 = new Rua("Eldin 1", tamanhoMax, false);
    el2 = new Rua("Eldin 2", tamanhoMax, true);
    el3 = new Rua("Eldin 3", tamanhoMin, false);
    ak0 = new Rua("Akkala 0", tamanhoMax, true);
    ak1 = new Rua("Akkala 1", tamanhoMin, false);
    rv1 = new Rua("Rito Village 1", tamanhoMin, false);
    rv2 = new Rua("Rito Village 2", tamanhoMin, false);
    rv3 = new Rua("Rito Village 3", tamanhoMax, true);
    rv4 = new Rua("Rito Village 4", tamanhoMax, true);
    la1 = new Rua("Lanayru 1", tamanhoMin, false);
    la2 = new Rua("Lanayru 2", tamanhoMin, false);
    la3 = new Rua("Lanayru 3", tamanhoMax, true);
    la4 = new Rua("Lanayru 4", tamanhoMax, true);
    gf0 = new Rua("Great Fairy 0", tamanhoMax, true);
    gf1 = new Rua("Great Fairy 1", tamanhoMin, false);
    gf2 = new Rua("Great Fairy 2", tamanhoMax, true);
    gf3 = new Rua("Great Fairy 3", tamanhoMin, false);
    ge1 = new Rua("Gerudo 1", tamanhoMin, false);
    ge2 = new Rua("Gerudo 2", tamanhoMin, false);
    ge3 = new Rua("Gerudo 3", tamanhoMax, true);
    ge4 = new Rua("Gerudo 4", tamanhoMax, false);
    fa1 = new Rua("Faron 1", tamanhoMin, false);
    fa2 = new Rua("Faron 2", tamanhoMin, false);
    fa3 = new Rua("Faron 3", tamanhoMax, true);
    fa4 = new Rua("Faron 4", tamanhoMax, false);
    ne0 = new Rua("Necluda 0", tamanhoMax, true);
    ne1 = new Rua("Necluda 1", tamanhoMin, false);
    ne2 = new Rua("Necluda 2", tamanhoMin, false);

    Circuito lostWoods = new Circuito(he2, lw1, lw2, lw3, rv2);
    Circuito eldin = new Circuito(lw2, el1, el2, la1, el3);
    Circuito ritoVillage = new Circuito(rv4, rv1, rv2, rv3, ge1, gf1);
    Circuito hyruleCentral = new Circuito(rv3, lw3, el3, la4, fa1, ge2);
    Circuito lanayru = new Circuito(la4, la1, la2, la3, ne1, fa2);
    Circuito gerudo = new Circuito(gf2, ge1, ge2, ge3, ge4);
    Circuito faron = new Circuito(ge3, fa1, fa2, fa3, fa4);
    Circuito hyruleComplete = new Circuito(gf0, rv4, he0, he1, lw1, el1, ak1, ak0, la3, ne0, ne2, fa4, ge4, gf3);

    Carro goomba = new Carro("Goomba", lostWoods, new GuiCar(Gallery.AVATAR_GOOMBA, polyline_lw, sld_lw, true, pane));
    Carro koopa = new Carro("Koopa ", eldin, new GuiCar(Gallery.AVATAR_KOOPA, polyline_el, sld_el, false, pane));
    Carro mario = new Carro("Mario ", ritoVillage, new GuiCar(Gallery.AVATAR_MARIO, polyline_rv, sld_rv, false, pane));
    Carro peach = new Carro("Peach ", hyruleCentral, new GuiCar(Gallery.AVATAR_PEACH, polyline_hc, sld_hc, true, pane));
    Carro luigi = new Carro("Luigi ", lanayru, new GuiCar(Gallery.AVATAR_LUIGI, polyline_la, sld_la, false, pane));
    Carro toad = new Carro("Toad  ", gerudo, new GuiCar(Gallery.AVATAR_TOAD, polyline_ge, sld_ge, false, pane));
    Carro wario = new Carro("Wario ", faron, new GuiCar(Gallery.AVATAR_WARIO, polyline_fa, sld_fa, true, pane));
    Carro yoshi = new Carro("Yoshi ", hyruleComplete, new GuiCar(Gallery.AVATAR_YOSHI, polyline_hy, sld_hy, true, pane));

    goomba.start();
    koopa.start();
    mario.start();
    peach.start();
    luigi.start();
    toad.start();
    wario.start();
    yoshi.start();
  }


}
