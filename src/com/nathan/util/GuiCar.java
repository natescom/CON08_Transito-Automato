package com.nathan.util;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Polyline;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 09/06/2021
 * Ultima alteracao: 09/06/2021
 * Nome: GuiCar
 * Funcao: Gerenciar o Carro na Interface
 * ************************************************************** */
public class GuiCar {
  private final double x;             // Posicao inicial X
  private final double y;             // Posicao inicial Y
  private final double width;         // Largura da linha
  private final double heigth;        // Comprimento da linha
  private final ImageView image;      // Imagem do carro
  private final Slider slider;        // Slider com a velocidade do carro
  private final boolean clockwise;    // Sentido do carro
  private final double[] coordenadas; // Vetor com as coordenadas do percurso

  private boolean verificaEncruzilhada;
  private boolean verificaNovaRua;

  /**
   * Construtor
   * @param image       Imagem do carro
   * @param polyline    Circuito desenhado com um Polyline na interface
   * @param slider      O Slider com a velocidade
   * @param clockwise   O sentido do movimento
   * @param pane        O painel pra adicionar a imagem
   */
  public GuiCar(Image image, Polyline polyline, Slider slider, boolean clockwise, Pane pane) {
    this.image = new ImageView(image);
    pane.getChildren().add(this.image);
    this.slider = slider;
    this.slider.setValue((Math.random() + Math.random()) * 500);
    this.coordenadas = coordenadas(polyline);
    this.x = this.coordenadas[0];
    this.y = this.coordenadas[1];
    this.width = this.coordenadas[8];
    this.heigth = this.coordenadas[9];
    this.image.setX(x - 21 / 2);
    this.image.setY(y - 21 / 2);
    this.clockwise = clockwise;
    if (clockwise) this.image.setY(this.image.getY() + heigth);
    Platform.runLater(() -> {
      StackPane thumb = (StackPane) slider.lookup(".thumb");
      thumb.getChildren().clear();
      thumb.getChildren().add(new ImageView(image));
    });
    verificaEncruzilhada = true;
    verificaNovaRua = false;
  }

  /**
   * Cria um vetor com as coordenadas do polyline
   * @param polyline
   * @return
   */
  private double[] coordenadas(Polyline polyline) {
    double[] valores = new double[10];
    ObservableList<Double> pontos = polyline.getPoints();
    for (int i = 0; i < 8; i++) {
      if(i%2==0) valores[i] = pontos.get(i) + polyline.getLayoutX();
      else valores[i] = pontos.get(i) + polyline.getLayoutY();
    }
    double x1 = valores[0];
    double y1 = valores[1];
    double x2 = valores[4];
    double y2 = valores[5];
    double witdh = x2 - x1;
    double heigth = y2 - y1;
    if (witdh < 0) witdh *= -1;
    if (heigth < 0) heigth *= -1;
    valores[8] = witdh;
    valores[9] = heigth;
    return valores;
  }

  /**
   * Faz a movimentacao na interface
   * @param totalprogress
   * @param perimetro
   * @param clockwise
   */
  public void publishProgress(int totalprogress, double perimetro, boolean clockwise) {
    Platform.runLater(() -> {
      double porcent = (totalprogress / perimetro) * 100;
      double frerelativa = porcent / 25;
      double distanciaDeCorpoX = 21 / 2;
      double distanciaDeCorpoY = 21 / 2;
      if (porcent >= 0 && porcent < 25) {
        if (clockwise) image.setY((y + heigth) - (heigth * frerelativa) - distanciaDeCorpoY);
        else image.setY(y + (heigth * frerelativa) - distanciaDeCorpoY);
      }
      if (porcent >= 25 && porcent < 50) {
        frerelativa = (porcent - 25) / 25;
        image.setX(x + (width * frerelativa) - distanciaDeCorpoX);
      }
      if (porcent >= 50 && porcent < 75) {
        frerelativa = (porcent - 50) / 25;
        if (clockwise) image.setY(y + (heigth * frerelativa) - distanciaDeCorpoY);
        else image.setY((y + heigth) - (heigth * frerelativa) - distanciaDeCorpoY);
      }
      if (porcent >= 75 && porcent < 100) {
        frerelativa = (porcent - 75) / 25;
        image.setX((x + width) - (width * frerelativa) - distanciaDeCorpoX);
      }
    });

  }

  /**
   * Serve pra selecionar qual taamanho usado no calculo das coordenadas
   * @param vertical
   * @return
   */
  private double selecionaTamanho(boolean vertical) {
    if (vertical) return heigth;
    else return width;
  }

  /**
   * Serve pra verificar se ele esta antes da encruzilhada e deve parar
   * @param currentProgess
   * @param currentCircuirtSize
   * @param isVertical
   * @return
   */
  public boolean pertoDaEncruzilhada(double currentProgess, double currentCircuirtSize, boolean isVertical) {
    if (verificaEncruzilhada) {
      double tamanho = selecionaTamanho(isVertical);
      double porcent = (currentProgess / currentCircuirtSize) * 100;
      double objetivo = tamanho * ((100 - porcent)/100);
      if (objetivo <= 30) {
        verificaNovaRua = true;
        verificaEncruzilhada = false;
        return true;
      }
    }
    return false;
  }

  /**
   * Serve pra verificar se ele ja entrou com tdo o corpo na nova rua pra liverar o acesso ao proximo
   * @param currentProgess
   * @param currentCircuirtSize
   * @param isVertical
   * @return
   */
  public boolean termineiDeEntrarNaNovaRua(double currentProgess, double currentCircuirtSize, boolean isVertical) {
    if (verificaNovaRua) {
      double tamanho = selecionaTamanho(isVertical);
      double porcent = (currentProgess / currentCircuirtSize) * 100;
      double carProgress = tamanho * porcent/100;
      if (carProgress >= 30) {
        return true;
      }
    }
    return false;
  }

  /**
   * Liga as verificacoes
   */
  public void verificaON() {
    verificaNovaRua = false;
    verificaEncruzilhada = true;
  }


  public boolean isClockwise() {
    return clockwise;
  }

  public Slider getSlider() {
    return slider;
  }

}
