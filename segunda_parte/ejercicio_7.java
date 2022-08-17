package segunda_parte;

// Librerias necesarias para que el programa funcione
import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ejercicio_7{
  public static final int CANT_FILAS = 4;       // Cantidad de filas que va a tener la matriz
  public static final int CANT_COLUMNAS = 5;   // Cantidad de filas que va a tener la matriz
  public static final int COTA_INFERIOR = 1;    // Limite inferior del numero que se puede generar en la matriz
  public static final int COTA_SUPERIOR = 9;    // Limite superior del numero que se puede generar en la matriz
  // Declaramos la entrada de datos como global (Asi no la tenemos que declarar en cada funcion que la vaya a utilizar)
  public static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));  

  public static void main(String[] args){
    int[][] matriz = new int[CANT_FILAS][CANT_COLUMNAS];
    int indiceFilaIngresado, numeroAIngresar;

    cargarMatrizConNumerosAleatorios(matriz);
    System.out.println("Matriz original: ");
    mostrarMatriz(matriz);
    ordenarMatrizAscendentemente(matriz);
    System.out.println("Matriz ordenada: ");
    mostrarMatriz(matriz);
    indiceFilaIngresado = ingresarIndiceFila();
    numeroAIngresar = ingresarNumero();
    ingresarNumeroDeFormaOrdenada(matriz[indiceFilaIngresado], numeroAIngresar);
    System.out.println("Matriz con el nuevo numero insertado: ");
    mostrarMatriz(matriz);
  }

  /*
   * Este procedimiento ingresa en un vector (Ya ordenado) un numero, este será ingresado
   * al vector de tal forma que el vector siga ordenado (Se perderá el valor que este al final)
  */
  public static void ingresarNumeroDeFormaOrdenada(int[] array, int numeroIngresar){
    int flechita = CANT_COLUMNAS-1; // Indice de la ultima posicion del array
    int selector = flechita;

    if(numeroIngresar != array[flechita]){
      array[flechita] = numeroIngresar; // Ingresamos el valor del usuario en la ultima posicion del array.
      // En caso de que el ahora ultimo numero sea mayor que el anteultimo, se procedera a ordenarlo por inserción
      if(numeroIngresar < array[flechita-1]){
        while((selector > 0) && (array[selector - 1] > numeroIngresar)){
          array[selector] = array[selector-1];
          selector--;
        }  
        array[selector] = numeroIngresar;
      }
    }
  }

  public static int ingresarNumero(){
    int valor = -1;

    System.out.print("Ingrese el numero que desea agregar: ");
    try {
      valor = Integer.valueOf(entrada.readLine());
    } catch (Exception e) {
      System.out.println(e);
    }

    return valor;
  }

  public static int ingresarIndiceFila(){
    int valor = -1;

    System.out.println("Indice: Del 0 al " + (CANT_FILAS-1) + " (Inclusive).\n");
    do {
      System.out.print("Ingrese el indice de la fila a la cual le desea agregar el numero: ");
      try {
        valor = Integer.valueOf(entrada.readLine());
      } catch (Exception e) {
        System.out.println(e);
      }
      if((valor < 0) || (valor >= CANT_FILAS)){
        System.out.println("ERROR: El indice ingresado no cumple con el indice preestablecido.\n");
      }
    } while ((valor < 0) || (valor >= CANT_FILAS));

    return valor;
  }

  public static void mostrarMatriz(int[][] matriz){
    for(int fila=0;fila<CANT_FILAS;fila++){
      for(int columna=0;columna<CANT_COLUMNAS;columna++){
        System.out.print(matriz[fila][columna] + ". ");
      }
      System.out.println("");
    }
    System.out.println("");
  }

  /* Dado un vector desordenado, lo ordena ascendentemente por insercion */
  public static void ordenarVectorAscendentemente(int[] vector){
    int aux = 0;
    int selector = 1;

    for(int flechita=1;flechita<CANT_COLUMNAS;flechita++){
      selector = flechita;
      aux = vector[flechita];

      while((selector > 0) && (vector[selector - 1] > aux)){
        vector[selector] = vector[selector-1];
        selector--;
      }
      vector[selector] = aux;
    }   
  }
  
  /* Ordena toda la matriz llamando repetidas veces a un metodo auxiliar, el cual ordena vectores */
  public static void ordenarMatrizAscendentemente(int[][] matriz){
    for(int fila=0;fila<CANT_FILAS;fila++){
      ordenarVectorAscendentemente(matriz[fila]);
    }   
  }

  public static void cargarMatrizConNumerosAleatorios(int[][] matriz){
    Random generador = new Random();
    
    for(int fila=0;fila<CANT_FILAS;fila++){
      for(int columna=0;columna<CANT_COLUMNAS;columna++){
        matriz[fila][columna] = (generador.nextInt(COTA_SUPERIOR - COTA_INFERIOR + 1) + COTA_INFERIOR);
      }
    }   
  }
}