package com.nathan.util;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 09/06/2021
 * Ultima alteracao: 09/06/2021
 * Nome: Carro
 * Funcao: Percorre um circuito defino do construtor
 * ************************************************************** */
public class Carro extends Thread {
  private final String modelo;     // Nome do carro
  private final Circuito circuito; // Circuito que ele percorre
  private boolean clockwise;       // true -> Horario   false -> Anti-horario

  private Rua previousRua;        // Rua anterior a que estava
  private Rua currentRua;         // Rua atual
  private Rua nextRua;            // Rua posterior
  private int currentprogress;    // Progresso na rua atual
  private int totalprogress;      // Progresso total do circuito

  private final GuiCar gui;       // Objeto com os metodos da interface grafica

  /**
   * Construtor
   * @param modelo    Nome do carro
   * @param circuito  Circuito que atravessa
   * @param gui       Objeto da interface
   */
  public Carro(String modelo, Circuito circuito, GuiCar gui) {
    this.modelo = modelo;
    this.circuito = circuito;
    this.clockwise = gui.isClockwise();
    this.gui = gui;
  }

  /**
   * Faz a acao de percorrer uma rua
   * Tambem gerencia as zonas criticas
   * @return se estah se movendo
   */
  private boolean move() {
    // VERIFICA SE ESTAH PERTO DE UMA ENCRUZILHADA //
    if(gui.pertoDaEncruzilhada(currentprogress,currentRua.getTamanho(), currentRua.isVertical())){
      try {
        nextRua = circuito.nextStreet(currentRua, clockwise);
        currentRua.getVezDeSair().acquire();     // Pede a vez de sair da rua atual
        nextRua.getVezDeEntrar().acquire();      // Pede a vez de entrar na proxima rua
        nextRua.getMutex().acquire();            // Entra na rua
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    // VERIFICA SE JA TERMINOU DE ENTRAR NA NOVA RUA PARA LIBERAR A PASSAGEM //
    if(gui.termineiDeEntrarNaNovaRua(currentprogress,currentRua.getTamanho(), currentRua.isVertical()) && previousRua != null && nextRua == currentRua){
      currentRua.getVezDeEntrar().release();      // Libera o pedido de entrar da rua
      previousRua.getVezDeSair().release();       // Libera o pedido de sair da rua
      gui.verificaON();
      previousRua.getMutex().release();           // Libera a rua
    }

    // SERVE PRA TORCAR DE RUA //
    if (currentprogress >= currentRua.getTamanho()) {
      currentprogress = 0;
      previousRua = currentRua;
      currentRua = nextRua;
      if (currentRua == circuito.getStart()) totalprogress = 0;
      return true;
    }
    currentprogress++;
    totalprogress++;
    gui.publishProgress(totalprogress, circuito.getPerimetro(), clockwise);
    return true;
  }

  @Override
  public synchronized void start() {
    this.currentRua = circuito.getStart();
    currentprogress = 0;
    totalprogress = 0;
    try {
      this.currentRua.getMutex().acquire();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    super.start();
  }

  @Override
  public void run() {
    while (move()) {
      try {
        sleep((long) (1000 / gui.getSlider().getValue()));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}
