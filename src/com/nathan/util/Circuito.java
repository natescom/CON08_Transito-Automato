package com.nathan.util;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 09/06/2021
 * Ultima alteracao: 09/06/2021
 * Nome: Circuito
 * Funcao: Organizar as ruas em quadrante
 * ************************************************************** */
public class Circuito {
  private String nome;
  private final int id;
  private static int cont;
  private final Rua[] ruas;
  private final int perimetro;

  /**
   * Montar o circuito em ordem horaria do mais a esquerda pro mais a direita
   * @param ruas
   */
  public Circuito(Rua... ruas) {
    this.id = ++cont;
    this.ruas = ruas;
    this.perimetro = calcularPerimetro(this.ruas);
  }

  /**
   * Calcula o tamanho do circuito
   * @param ruas
   * @return
   */
  private int calcularPerimetro(Rua[] ruas) {
    int tamanho = 0;
    for (int i = 0; i < ruas.length; i++) {
      tamanho += ruas[i].getTamanho();
    }
    return tamanho;
  }

  /**
   * Procura a proxima rua do circuito
   * @param current Rua atual
   * @param sentido Sentido da busca
   * @return
   */
  public Rua nextStreet(Rua current, boolean sentido) {
    int i;
    for (i = 0; i < ruas.length; i++) {
      if (current == ruas[i]) break;
    }
    if (sentido) {
      if (i == ruas.length - 1) i = 0;
      else i++;
    } else {
      if (i == 0) i = ruas.length - 1;
      else i--;
    }
    return ruas[i];
  }

  /**
   * Retorna a primeira rua do circuito
   * @return
   */
  public Rua getStart() {
    return ruas[0];
  }

  public int getPerimetro() {
    return perimetro;
  }

}
