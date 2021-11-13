package model;

import java.util.ArrayList;

public class Algoritmo {
	
	/**
	 * Este método inicializa la matriz
	 * @param miMatriz La matriz a inicializar.
	 * @param palabra La cadena a probar.
	 */
	public static void inicializarMatriz(ArrayList<ArrayList<String>> miMatriz, String palabra){
	        for (int i = 0; i < palabra.length(); i++) {
	            ArrayList<String> elArray = new ArrayList<>();
	            for (int j = 0; j < palabra.length()-i; j++) {
	                elArray.add(null);
	            }
	            miMatriz.add(elArray);
	        }
	    }
	 
	/**
	 * Este método muestra la matriz en consola.
	 * @param miMatriz La matriz a mostrar.
	 */
	public static void mostrarMatriz(ArrayList<ArrayList<String>> miMatriz){
	        for (int i=0; i < miMatriz.size();i++){
	            for (int j=0; j < miMatriz.get(i).size(); j++){
	                String unChat= miMatriz.get(i).get(j);
	                System.out.print(unChat);
	                if (j!=miMatriz.get(i).size()-1) {
	                    System.out.print("\t");
	                }
	            }
	            System.out.println();
	        }
	        System.out.println();
	    }
	 
	/**
	 * Este método realiza el algoritmo CYK
	 * @param miMatriz La matriz para verificar.
	 * @param palabra La cadena a probar.
	 * @param miGramatica La gramática dada.
	 * @return Si la palabra cumple con la gramática dada.
	 */
	public static boolean algoritmoCYK(ArrayList<ArrayList<String>> miMatriz, String palabra, Gramatica miGramatica){
	        boolean pertenece = false;
	        String values = new String();
	        int n = miMatriz.size();
	        
	        for (int i = 0; i < palabra.length(); i++) {
	            values = miGramatica.getValues(Character.toString(palabra.charAt(i)));
	            miMatriz.get(0).set(i, values);
	        }
	        
	        mostrarMatriz(miMatriz);
	        
	        for (int i = 1; i < n; i++) {
	            for (int j = 0; j < n-i; j++) {
	                values = "";
	                for (int k = 0; k < i; k++) {
	                    mostrarMatriz(miMatriz);
	                    String resultado = comprobarCombinaciones(miMatriz.get(k).get(j), miMatriz.get(i-k-1).get(j+k+1), miGramatica);
	                    if((resultado == "0" && values.isEmpty()) || (values == "0" && resultado != "0") || (values == "0" && resultado == "0"))
	                        values = resultado;
	                    else if(resultado != "0"){
	                        if(values.isEmpty())
	                            values += resultado;
	                        else
	                            values = values.concat(" ".concat(resultado));
	                    }
	                }
	                values = eliminarRepetidos(values);
	                miMatriz.get(i).set(j, values);
	                mostrarMatriz(miMatriz);
	            }
	        }
	        
	        if(miMatriz.get(n-1).get(0).contains("S"))
	            pertenece = true;
	        return pertenece;
	    }
	 
	
	/**
	 * Este método comprueba las combinaciones que cumplen con la gramática.
	 * @param a .
	 * @param b .
	 * @param miGramatica La gramática dada.
	 * @return Una cadena con una combinación.
	 */
	 public static String comprobarCombinaciones(String a, String b, Gramatica miGramatica){
	        String values = "";
	        String var1 = null, var2 = null;
	        int numOfSpacesA, numOfSpacesB;
	        numOfSpacesA = a.length() - a.replace(" ", "").length();
	        for (int i = 0; i <= numOfSpacesA; i++) {
	            if(numOfSpacesA == 0){
	                var1 = a;
	            }
	            else{
	                var1 = a.substring(0, a.indexOf(" "));
	                a = a.substring(a.indexOf(" ")+1, a.length());
	                numOfSpacesA--;
	                i--;
	            }
	            numOfSpacesB = b.length() - b.replace(" ", "").length();
	            for (int j = 0; j <= numOfSpacesB; j++) {
	                if(numOfSpacesB == 0){
	                    var2 = b;
	                }
	                else{
	                    var2 = b.substring(0, b.indexOf(" "));
	                    b = b.substring(b.indexOf(" ")+1, b.length());
	                    numOfSpacesB--;
	                    j--;
	                }
	                if(!miGramatica.getValues(var1.concat(var2)).isEmpty())
	                    values += miGramatica.getValues(var1.concat(var2)) + " ";
	            }
	        }
	        if(values.isEmpty())
	            values = "0";
	        else{
	            values = values.substring(0, values.length()-1);
	        }
	        return values;
	    }
	    
	/**
	* Este método elimina los caracteres repetidos en la cadena dada.
	 * @param cadena La cadena a modificar.
	 * @return La cadena sin caracteres repetidos.
	 */
	    public static String eliminarRepetidos(String cadena){
	        String salida = "";
	        CharSequence caracter = "";
	        int numOfSpaces = cadena.length() - cadena.replaceAll(" ", "").length();
	        for (int i = 0; i <= numOfSpaces; i++) {
	            if(numOfSpaces == 0){
	                caracter = cadena;
	            }
	            else{
	                caracter = cadena.substring(0,cadena.indexOf(" ")+1);
	                cadena = cadena.substring(cadena.indexOf(" ")+1,cadena.length());
	                numOfSpaces--;
	                i--;
	            }
	            if(!salida.contains(caracter))
	                salida += caracter;
	        }
	        return salida;
	    }
	    
}
