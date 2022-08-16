package primera_parte;

// Librerias necesarias para que el programa funcione
import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ejercicio_6 {
  public static final int CANT_FILAS = 5;       // Cantidad de filas que va a tener la matriz
  public static final int CANT_COLUMNAS = 10;   // Cantidad de filas que va a tener la matriz
  public static final int COTA_INFERIOR = 1;    // Limite inferior del numero que se puede generar en la matriz
  public static final int COTA_SUPERIOR = 9;    // Limite superior del numero que se puede generar en la matriz
  public static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));  // Hacemos la entrada de datos global
  public static void main(String[] args) {
    int[][] matriz = new int[CANT_FILAS][CANT_COLUMNAS];
    int numeroSolicitado;

    cargarMatrizConNumerosAleatorios(matriz);
    System.out.println("Matriz original: ");
    mostrarMatriz(matriz);
    numeroSolicitado = ingresarNumero();
    eliminarCoinsidencias(matriz, numeroSolicitado);
    System.out.println("Matriz resultante: ");
    mostrarMatriz(matriz);
  }

  public static void rellenarUltimasPosiciones(int[] arreglo, int cantCoincidencias){
    /* 
      ?Se rellenará las n ultimas posiciones (depende el valor de "cantCoincidencias") 
      ?con el valor asignado en "VALOR_RELLENAR"
    */
    final int VALOR_RELLENAR = 0;
    
    // (CANT_COLUMNAS - 1) = Ultima posicion del arreglo (De la fila de la matriz)
    for(int i=(CANT_COLUMNAS - cantCoincidencias);i<CANT_COLUMNAS;i++){
      arreglo[i] = VALOR_RELLENAR;
    }
  }

  public static void realizarCorrimiento(int[] arreglo, int posicionActual, int cantCoincidencias){
    /*
     * Debido a que este procedimiento solo se invocará cuando "cantCoincidencias" sea mayor a 0,
     * en el condicional del while NO hay que poner: posicionActual < (CANT_COLUMNAS - 1 - cantCoincidencias)
    */
    while(posicionActual < (CANT_COLUMNAS - cantCoincidencias)){
      arreglo[posicionActual] = arreglo[posicionActual + 1];
      posicionActual++;
    }
  }

  public static void verificarFila(int[] arreglo, int numeroSolicitado){
    int cantCoincidencias = 0, posicionActual = 0;
    
    while(posicionActual < (CANT_COLUMNAS - cantCoincidencias)){
      if(arreglo[posicionActual] == numeroSolicitado){
        System.out.println("En la posicion " + posicionActual + " se encontro el valor");
        cantCoincidencias++;
        realizarCorrimiento(arreglo, posicionActual, cantCoincidencias);
        /* 
          * En caso de que en la posicion actual se encontrase una coincidencia, 
          * todos los valores a la derecha se movieron 1 posicion a la izquierda.
          * por lo tanto, en array[posicionActual] ahora se encuentra un nuevo valor,
          * el cual tambien hay que verificar si coincide con el numero pedido 
        */
        posicionActual--;
      }
      posicionActual++;
    }
    System.out.println("La cantidad de coincidencias en la fila es de " + cantCoincidencias + ".\n");
    if(cantCoincidencias > 0){
      rellenarUltimasPosiciones(arreglo, cantCoincidencias);
    }
  }

  public static void eliminarCoinsidencias(int[][] matriz, int numeroSolicitado){
    System.out.println("Entro");
    for(int fila=0;fila<CANT_FILAS;fila++){
      System.out.println("Indice fila: " + fila);
      verificarFila(matriz[fila], numeroSolicitado);
    } 
  }

  public static int ingresarNumero(){
    int numero = 0;
    
    System.out.print("Ingrese el numero que va a eliminar de la matriz: ");
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
