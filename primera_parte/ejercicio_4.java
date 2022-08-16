package primera_parte;

// Librerias necesarias para que funcione el programa
import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ejercicio_4 {
  public static final int CANT_FILAS = 5;       // Cantidad de filas que va a tener la matriz
  public static final int CANT_COLUMNAS = 10;   // Cantidad de filas que va a tener la matriz
  public static final int COTA_INFERIOR = 0;    // Limite inferior del numero que se puede generar en la matriz
  public static final int COTA_SUPERIOR = 9;    // Limite superior del numero que se puede generar en la matriz
  public static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));  // Hacemos la entrada de datos global

  public static void main(String[] args){
    int[][] matriz = new int[CANT_FILAS][CANT_COLUMNAS];
    int indiceFila, indiceColumna, numeroSolicitado;

    cargarMatrizConNumerosAleatorios(matriz);
    mostrarMatriz(matriz);
    indiceFila = ingresarIndiceFila();
    indiceColumna = ingresarIndiceColumna();
    numeroSolicitado = ingresarNumero();
    insertarNumeroALaMatrizEnPosicionIndicada(matriz, indiceFila, indiceColumna, numeroSolicitado);
    mostrarMatriz(matriz);
  }

  public static void insertarNumeroALaMatrizEnPosicionIndicada(int[][] matriz, int indiceFila, int indiceColumna, int numeroIngresar){
    int posicionColumnaActual = CANT_COLUMNAS - 1; // Empezaremos por la ultima posicion de la fila, indicada

    while(posicionColumnaActual > indiceColumna){
      matriz[indiceFila][posicionColumnaActual] = matriz[indiceFila][posicionColumnaActual-1];
      posicionColumnaActual--;
    }

    // Insertamos en la posicion indicada por el usuario el numero que nos indicó
    matriz[indiceFila][posicionColumnaActual] = numeroIngresar;
  }

  public static int ingresarNumero(){
    int numero = 0;
    
    System.out.print("Ingrese el numero que va a ingresar a la matriz: ");
    try {
      numero = Integer.valueOf(entrada.readLine());
    } catch (Exception e) {
      System.out.println(e);
    }

    return numero;
  }

  public static int ingresarIndiceColumna(){
    int indiceColumnaIgresada = 0;
    do {
      System.out.print("Ingrese el indice de la columna: ");
      try {
        indiceColumnaIgresada = Integer.valueOf(entrada.readLine());
      } catch (Exception e) {
        System.out.println(e);
      }
      if((indiceColumnaIgresada < 0) || (indiceColumnaIgresada > CANT_COLUMNAS - 1)){
        System.out.println("\nError: El indice de columna ingresado debe ser mayor o igual a 0, y menor a " + (CANT_COLUMNAS) + ".\n");
      }
    } while ((indiceColumnaIgresada < 0) || (indiceColumnaIgresada > CANT_COLUMNAS - 1));

    return indiceColumnaIgresada;
  }

  public static int ingresarIndiceFila(){
    int indiceFilaIgresada = 0;
    do {
      System.out.print("Ingrese el indice de la fila: ");
      try {
        indiceFilaIgresada = Integer.valueOf(entrada.readLine());
      } catch (Exception e) {
        System.out.println(e);
      }
      if((indiceFilaIgresada < 0) || (indiceFilaIgresada > CANT_FILAS - 1)){
        System.out.println("\nError: El indice de fila ingresado debe ser mayor o igual a 0, y menor a " + (CANT_FILAS) + ".\n");
      }
    } while ((indiceFilaIgresada < 0) || (indiceFilaIgresada > CANT_FILAS - 1));

    return indiceFilaIgresada;
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
