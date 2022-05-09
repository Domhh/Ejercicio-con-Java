package com.mycompany.ejerciciositrackbackend;
import java.io.*;
import java.net.*;
import java.util.Scanner;
/**
 *
 * @author Brian Osca
 */
public class Main {
    
    public void realizarDescarga(){
        Scanner leer = new Scanner(System.in);

        try {
            System.out.println("URL input: ");
            String pagina = leer.next();
            leer.nextLine(); //para evitar el salto de linea automático
            System.out.println("Frase a buscar: ");
            String frase = leer.nextLine();
            System.out.println("Resultados:");
            
            //realizamos la conexión a la página solicitada
            StringBuffer codeBuffered = new StringBuffer();
            String code;
            
            URL url = new URL(pagina);
            InputStream in = url.openStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in));
            
            String line;
            
            while((line = read.readLine()) != null)
            {
                codeBuffered.append(line).append("\n"); 
            } 
            //Conversión del código de la página a string
            code = codeBuffered.toString();
            
            //Decodificación de caracteres y acentos
            byte[] bytes= code.getBytes("ISO-8859-1");
            String s2 = new String(bytes, "UTF-8");
            
           //este bucle busca la frase completa
            bucle(s2, frase);
            //separo la frase por palabras usando el espacio como separador, y las almaceno en el array "parts"
            String[] parts = frase.split("\\s+");
            
            if(parts.length>1){
                for(int i=0;i<parts.length;i++){
                //este bucle busca cada palabra de la frase
                bucle(s2, parts[i]);
                }  
            }else{
                System.out.println("Fin de programa");
            }
            //cerramos los streams
            in.close();
            read.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }   
    }
    public void bucle(String texto, String frase){
        int contador = 0;
            while(texto.contains(frase)){
                texto = texto.substring(texto.indexOf(frase)+frase.length(), texto.length());
                contador++;
            }
            System.out.println("'"+frase+"' se repite "+contador+ " veces");
    }
    public static void main(String[]args){
        Main dw = new Main();
        dw.realizarDescarga();
    }
    
}