package primera_parte;

// Librerias necesarias para que el programa funcione
import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ejercicio_5 {
  public static final int CANT_FILAS = 5;       // Cantidad de filas que va a tener la matriz
  public static final int CANT_COLUMNAS = 10;   // Cantidad de filas que va a tener la matriz
  public static final int COTA_INFERIOR = 0;    // Limite inferior del numero que se puede generar en la matriz
  public static final int COTA_SUPERIOR = 9;    // Limite superior del numero que se puede generar en la matriz
  public static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));  // Hacemos la entrada de datos global

  public static void main(String[] args) {
    int[][] matriz = new int[CANT_FILAS][CANT_COLUMNAS];
    int numeroSolicitado;

    cargarMatrizConNumerosAleatorios(matriz);
    System.out.println("Matriz original: ");
    mostrarMatriz(matriz);
    numeroSolicitado = ingresarNumero();
    eliminarPrimerCoinsidencia(matriz, numeroSolicitado);
    System.out.println("Matriz resultante: ");
    mostrarMatriz(matriz);
  }

  public static void realizarCorrimiento(int[] arreglo, int indiceColumna){
    while(indiceColumna < (CANT_COLUMNAS-1)){
      arreglo[indiceColumna] = arreglo[indiceColumna + 1];
      indiceColumna++;
    }
    // Por último, ponemos un 0 al final del array
    arreglo[indiceColumna] = 0;
  }

  public static void eliminarPrimerCoinsidencia(int[][] matriz, int numeroSolicitado){
    int filaActual = 0, columnaActual;
    boolean encontrado = false;

    while((filaActual < CANT_FILAS) && (!encontrado)){
      // Reiniciamos la posicion de la columna a 0, para que comience a recorrer desde el inicio de la fila
      columnaActual = 0; 
      while((columnaActual < CANT_COLUMNAS) && (!encontrado)){
        if(matriz[filaActual][columnaActual] == numeroSolicitado){
          encontrado = true;
          realizarCorrimiento(matriz[filaActual], columnaActual);          
        }
        columnaActual++;
      }
      filaActual++;
    }
    System.out.println((encontrado)?"La primer ocurrencia fue eliminada\n": "El numero no se encontraba en la matriz\n");
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
