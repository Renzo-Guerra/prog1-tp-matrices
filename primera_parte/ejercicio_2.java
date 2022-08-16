package primera_parte;

import java.util.Random;

public class ejercicio_2 {
  public static int CANT_FILAS = 5;
  public static int CANT_COLUMNAS = 10;
  public static int COTA_INFERIOR = 0;
  public static int COTA_SUPERIOR = 9;
  public static void main(String[] args){
    int[][] matriz = new int[CANT_FILAS][CANT_COLUMNAS];
    int cantPares;

    cargarMatrizConNumerosAleatorios(matriz);
    cantPares = obtenerCantidadNumerosParesEnMatriz(matriz);
    mostrarMatriz(matriz);
    System.out.println((cantPares > 0)
      ? "\nLa cantidad de numeros pares en la matriz es de: " + cantPares  + "\n"
      : "\nNo se encontró ningun numero par en la matriz\n");
  }

  public static int obtenerCantidadNumerosParesEnMatriz(int[][] matriz){
    int acumulador = 0;
    for(int fila=0;fila<CANT_FILAS;fila++){
      for(int columna=0;columna<CANT_COLUMNAS;columna++){
        if(matriz[fila][columna] % 2 ==0){
          acumulador++;
        }
      }
    }

    return acumulador;
  }

  // Directamente muestra la matriz sin llamar a un procedimiento que muestre una fila a la vez
  public static void mostrarMatriz(int[][] matriz){
    for(int fila=0;fila<CANT_FILAS;fila++){
      for(int columna=0;columna<CANT_COLUMNAS;columna++){
        System.out.print(matriz[fila][columna] + ". ");
      }
      System.out.println(""); // Hace un salto de linea cada vez que se muestra una fila
    }
  }

  public static void cargarArrayConNumerosAleatorios(int[] array){
    Random generador = new Random();
    
    for(int columna=0;columna<CANT_COLUMNAS;columna++){
      array[columna] = (generador.nextInt(COTA_SUPERIOR - COTA_INFERIOR + 1) + COTA_INFERIOR);
    }
  }

  public static void cargarMatrizConNumerosAleatorios(int[][] matriz){
    for(int fila=0;fila<CANT_FILAS;fila++){
      cargarArrayConNumerosAleatorios(matriz[fila]);
    }
  }
}