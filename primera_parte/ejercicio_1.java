package primera_parte;

import java.util.Random;

public class ejercicio_1 {
  public static final int CANT_FILAS = 5;
  public static final int CANT_COLUMNAS = 10;
  public static final int COTA_INFERIOR = 1;
  public static final int COTA_SUPERIOR = 9;
  public static void main(String[] args){
    int[][] matriz = new int[CANT_FILAS][CANT_COLUMNAS];

    cargarMatrizConNumerosAleatorios(matriz);
    System.out.println("\nMatriz cargada");
    imprimirMatriz(matriz);
    invertirMatriz(matriz);
    System.out.println("\nMatriz con filas invertidas");
    imprimirMatriz(matriz);
  }

  public static void invertirArray(int[] array){
    int aux;
    for(int columna =0;columna < CANT_COLUMNAS/2;columna++){
      aux = array[columna];
      array[columna] = array[array.length - 1 - columna];
      array[array.length - 1 - columna] = aux;
    }
  }
  public static void invertirMatriz(int[][] matriz){
    for(int fila=0;fila<CANT_FILAS;fila++){
      invertirArray(matriz[fila]);
    }
  }
  public static void imprimirMatriz(int[][] matriz){
    for(int fila=0;fila<CANT_FILAS;fila++){
      for(int columna=0;columna<CANT_COLUMNAS;columna++){
        System.out.print(matriz[fila][columna] + ". ");
      }
      System.out.println("");
    }
  }

  public static void cargarArrayConNumerosAleatorios(int[] array){
    Random r = new Random();
    for(int columna=0;columna<CANT_COLUMNAS;columna++){
      array[columna] = (r.nextInt(COTA_SUPERIOR - COTA_INFERIOR + 1) + COTA_INFERIOR);
    }
  }

  public static void cargarMatrizConNumerosAleatorios(int[][] matriz){
    for(int fila=0 ; fila< CANT_FILAS;fila++){
      cargarArrayConNumerosAleatorios(matriz[fila]);
    }
  }
}
