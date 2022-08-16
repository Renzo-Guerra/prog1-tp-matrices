package primera_parte;

import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ejercicio_3 {
  public static final int CANT_FILAS = 5;
  public static final int CANT_COLUMNAS = 10;
  public static final int COTA_INFERIOR = 0;
  public static final int COTA_SUPERIOR = 9;
  public static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

  public static void main(String[] args){
    int[][] matriz = new int[CANT_FILAS][CANT_COLUMNAS];
    int posicionFila, posicionColumna;

    cargarMatrizConNumerosAleatorios(matriz);
    mostrarMatriz(matriz);
    posicionFila = cargarPosicionFila();
    posicionColumna = cargarPosicionColumna();
    realizarCorrimiento(matriz, posicionFila, posicionColumna);
    mostrarMatriz(matriz);
  }

  public static void realizarCorrimiento(int[][] matriz, int filaIndicada, int columnaIndicada){
    int columnaActual = 0;
    while(columnaActual <= columnaIndicada - 1){
      matriz[filaIndicada][columnaActual] = matriz[filaIndicada][columnaActual+1];
      columnaActual++;
    }
  }
  //     a  
  // 2 3 3 4
  // 0 1 2 3
  public static int cargarPosicionFila(){  
    int indiceFila = -1;
    
    do{
      System.out.print("Ingrese la posicion de la fila: ");
      try{
        indiceFila = Integer.valueOf(entrada.readLine());
      }catch(Exception e){
        System.out.println(e);
      }
      if((indiceFila < 0) || (indiceFila >= CANT_FILAS)){
        System.out.println("\nIngrese un indice mayor o igual a 0, y menor que " + CANT_FILAS + ".");
      }
    }while((indiceFila < 0) || (indiceFila >= CANT_FILAS));
    
    return indiceFila;
  }

  public static int cargarPosicionColumna(){  
    int indiceColumna = -1;
    
    do{
      System.out.print("Ingrese la posicion de la columna: ");
      try{
        indiceColumna = Integer.valueOf(entrada.readLine());
      }catch(Exception e){
        System.out.println(e);
      }
      if((indiceColumna < 0) || (indiceColumna >= CANT_COLUMNAS)){
        System.out.println("\nIngrese un indice mayor o igual a 0, y menor que " + CANT_COLUMNAS + ".");
      }
    }while((indiceColumna < 0) || (indiceColumna >= CANT_COLUMNAS));
    
    return indiceColumna;
  }

  public static void cargarMatrizConNumerosAleatorios(int[][] matriz){
    Random generador = new Random();

    for(int fila=0;fila<CANT_FILAS;fila++){
      for(int columna=0;columna<CANT_COLUMNAS;columna++){
        matriz[fila][columna] = (generador.nextInt(COTA_SUPERIOR - COTA_INFERIOR + 1) + COTA_INFERIOR);
      }
    }
  }

  public static void mostrarMatriz(int[][] matriz){
    System.out.println("");
    for(int fila=0;fila<CANT_FILAS;fila++){
      for(int columna=0;columna<CANT_COLUMNAS;columna++){
        System.out.print(matriz[fila][columna] + ". ");
      }
      System.out.println("");
    }
    System.out.println("");
  }
}
