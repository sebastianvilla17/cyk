package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.Collection;
import java.util.Iterator;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class Gramatica {

	 private Multimap<String, String> gramatica; 
	
	/**
	 * Este método permite crear una gramática vacía.
	 */
	    public Gramatica(){
	        gramatica = new ArrayListMultimap<>();
	    }
	    
	/**
	 * Este método permite crear una gramática con reglas definidas.
	 * @param gram Una cadena con las reglas de la gramática.
	 * @throws IOException Si el lector presenta alguna excepción.
	 */
	    public Gramatica(String gram) throws IOException{
	        gramatica = new ArrayListMultimap<>();
	        
	        String regla;
	        
	        BufferedReader b = new BufferedReader(new StringReader(gram));
	        while((regla = b.readLine())!=null) {
	            String value = regla.substring(0, regla.indexOf("->"));
	            regla = regla.substring(regla.indexOf("->")+2,regla.length());
	            int numOfPipes = regla.length() - regla.replace("|", "").length();
	            for (int i = 0; i <= numOfPipes; i++) {
	                String key;
	                if(regla.indexOf('|') != -1){
	                    key = regla.substring(0,regla.indexOf('|'));
	                    this.addRule(key, value);
	                    regla = regla.substring(regla.indexOf('|')+1,regla.length());
	                }
	                else{
	                    key = regla;
	                    this.addRule(key, value);
	                }
	            }
	        }
	        b.close();
	    }
	    
	/**
	 * Este método añade una regla a la gramática.
	 * @param key La clave a asignar.
	 * @param value El valor a guardar.
	 */
	    public void addRule(String key, String value){
	        gramatica.put(key, value);
	    }
	    
	/**
	 * Este método obtiene los valores que pertenecen a una misma clave.
	 * @param key La clave a buscar.
	 * @return Una cadena con todos los valores que contenia la clave.
	 */
	    public String getValues(String key){
	        String values = "";
	        
	        Collection<String> coleccion = gramatica.get(key);
	        if(!coleccion.isEmpty()){
	            for (Iterator<String> it = coleccion.iterator(); it.hasNext();) {
	            String valor = it.next();
	            values += valor+" ";
	            }
	            values = values.substring(0,values.length()-1);
	        }
	        
	        return values;
	    }

	/**
	 * Este método muestra en consola el contenido del mapa de la gramática.
	 */
	    public void showMapContent(){
	        Iterator it = gramatica.keySet().iterator();
	        while(it.hasNext()){
	            String key = (String) it.next();
	            System.out.println(gramatica.get(key) + "->" + key);
	        }
	        System.out.println();
	    }
	    
	/**
	 * Este metodo obtiene el mapa de la gramática.
	 * @return El mapa de la gramática.
	 */
	    public Multimap<String, String> getMapContent(){
	        return gramatica;
	    }
}
