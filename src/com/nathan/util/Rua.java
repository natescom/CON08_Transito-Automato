package com.nathan.util;

import java.util.concurrent.Semaphore;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 09/06/2021
 * Ultima alteracao: 09/06/2021
 * Nome: Rua
 * Funcao: Ser percorrida
 * ************************************************************** */
public class Rua {
  private String nome;                  // Nome da rua
  private final double tamanho;         // Metros
  private final Semaphore mutex;        // Semaforo da rua
  private final Semaphore vezDeEntrar;  // Semaforo que da prioridade pra alguem entrar
  private final Semaphore vezDeSair;    // Semafoto que da prioridade pra alguem sair
  private final boolean vertical;       // Se a rua eh vertical ou horizontal

  /**
   * Contrutos
   * @param nome      Nome da rua
   * @param tamanho   Tamanho
   * @param vertical  Orientacao
   */
  public Rua(String nome, double tamanho, boolean vertical) {
    this.nome = nome;
    this.tamanho = tamanho;
    this.vertical = vertical;
    this.mutex = new Semaphore(1);
    this.vezDeEntrar = new Semaphore(1);
    this.vezDeSair = new Semaphore(1);
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public double getTamanho() {
    return tamanho;
  }

  public Semaphore getMutex() {
    return mutex;
  }

  public Semaphore getVezDeEntrar() {
    return vezDeEntrar;
  }

  public Semaphore getVezDeSair() {
    return vezDeSair;
  }

  public boolean isVertical() {
    return vertical;
  }
}
