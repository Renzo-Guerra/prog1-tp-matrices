package segunda_parte;

// Librerias necesarias para que el programa funcione
import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ejercicio_8 {
  public static final int CANT_FILAS = 4;       // Cantidad de filas que va a tener la matriz
  public static final int CANT_COLUMNAS = 5;   // Cantidad de filas que va a tener la matriz
  public static final int COTA_INFERIOR = 1;    // Limite inferior del numero que se puede generar en la matriz
  public static final int COTA_SUPERIOR = 9;    // Limite superior del numero que se puede generar en la matriz
  // Declaramos la entrada de datos como global (Asi no la tenemos que declarar en cada funcion que la vaya a utilizar)
  public static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));  
  public static void main(String[] args) {
    int[][] matriz = new int[CANT_FILAS][CANT_COLUMNAS];
    int indiceFilaIngresado, numeroAEliminar;

    cargarMatrizConNumerosAleatorios(matriz);
    System.out.println("Matriz original: ");
    mostrarMatriz(matriz);
    ordenarMatrizAscendentemente(matriz);
    System.out.println("Matriz ordenada: ");
    mostrarMatriz(matriz);
    indiceFilaIngresado = ingresarIndiceFila();
    numeroAEliminar = ingresarNumero();
    eliminarOcurrencia(matriz[indiceFilaIngresado], numeroAEliminar);
    System.out.println("Matriz resultante: ");
    mostrarMatriz(matriz);
  }

  public static int encontrarUltimoValorEnSecuencia(int[] array, int valor, int indiceActual){
    // En caso de que indiceActual = (CANT_COLUMNAS-1), se terminara el while, e indiceActual
    // seria igual al ultimo indice del arreglo (Lo cual es imposible, ya que validamos ese caso previamente
    // a la invocacion de este metodo)
    while(indiceActual < (CANT_COLUMNAS - 1)){
      if(array[indiceActual+1] != valor){
        // En caso de que el valor del indice sea distinto al valor a buscar, indiceActual es el ultimo
        break;
      }
      indiceActual++;
    }
    
    // En caso de que
    return indiceActual;
  }

  /*
   * A pesar que me pide la primer ocurrencia, buscaremos la ultima, ya que al realizar el corrimiento
   * arrojara el mismo resultado si eliminamos la ultima ocurrencia, es mas, seria incluso mas efectivo,
   * ya que nos ahorrariamos N iteraciones, siendo N la cantidad de veces que se repite ese numero.
   * (Recordar que esto solo sirve porque el array esta ordenado, de lo contrario no seria lo mismo eliminar
   * el ultimo, a eliminar el primero).
   * 
   * En caso de que no se encuentre alguna ocurrencia, devolvera -1.
   * NOTA: El metodo devuelve un indice, no el valor que se haya en dicho indice
  */
  public static int encontrarUltimoIndiceDeNumero(int[] array, int numeroEncontrar){
    int 
      limiteInferior = 0,
      limiteSuperior = CANT_COLUMNAS-1,
      posicionActual;

    while(limiteInferior <= limiteSuperior){
      posicionActual = (limiteInferior + limiteSuperior) / 2;
      if(array[posicionActual] == numeroEncontrar){
        // Encontramos 1 posicion donde hay una coincidencia pero, puede o no, ser la ultima.
        /*
          En caso de que posicion actual sea el ULTIMA indice del array, 
          sabremos que es la ultima ocurrencia del numero "numeroEncontrar".
          Caso contrario, llamaremos al metodo "encontrarUltimoValorEnSecuencia" para que encuentre
          el ultimo valor de la secuencia de numeros repetidos.

          Ej:
            Buscamos el numero 4 del array [1, 1, 3, 4, 4, (4), 4, 6, 7]
            El (4) es "posicionActual" (La cual encontro el metodo "encontrarPosicionUltimaOcurrencia"),
            el metodo que llama se encargará de recorrer desde esa posicion lo que queda del array,
            hasta que llegue al final o hasta que se encuentre con un numero el cual sea distinto de 4.
        */ 
        if(posicionActual < (CANT_COLUMNAS - 1)){
          return encontrarUltimoValorEnSecuencia(array, numeroEncontrar, posicionActual);
        } 

        return posicionActual;
      }else{
        if(array[posicionActual] > numeroEncontrar){
          limiteSuperior = posicionActual - 1;
        }else{
          limiteInferior = posicionActual + 1;
        }
      }
    }
    
    return -1;
  }

  public static void realizarCorrimientoIzquierda(int[] array, int posicionActual){
    final int DISCERNIBLE = 0;

    while(posicionActual < (CANT_COLUMNAS-1)){
      array[posicionActual] = array[posicionActual + 1];
      posicionActual++;
    }
    array[posicionActual] = DISCERNIBLE;
  }

  public static void eliminarOcurrencia(int[] array, int numeroAEliminar){
    int posicion;
    posicion = encontrarUltimoIndiceDeNumero(array, numeroAEliminar);
    /*
     * En caso de que posicion sea menor a cero, el numero almacenado 
     * en "numeroAEliminar" no se encuentra en el array (Fila de la matriz)
    */
    if(posicion >= 0){
      System.out.println("El numero " + numeroAEliminar + " se encuentra en la fila.");
      realizarCorrimientoIzquierda(array, posicion);
    }else{
      System.out.println("El numero " + numeroAEliminar + " NO se encontraba en la fila.");
    }
  }

  public static int ingresarNumero(){
    int valor = -1;

    System.out.print("Ingrese el numero que desea eliminar: ");
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
      System.out.print("Ingrese el indice de la fila a la cual le desea eliminar la ultima ocurrencia: ");
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