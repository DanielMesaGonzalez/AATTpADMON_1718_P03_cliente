
package practica_3_aattaa_cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Daniel Mesa y Salvador Trujillo
 */


public class PeticionPOST {
    private String DatosUsuario=""; //Contiene los datos del usuario: NICK y DNI.
    private URL URLServidor; //URL donde vamos a enviar la petición.
    
 
     public PeticionPOST(String URLServidor) throws MalformedURLException{ //Constructor. throws MalformedURLException: Excepción de error en la URL.
        this.URLServidor =new URL(URLServidor);
        this.DatosUsuario="";
    }

    public String getDatosUsuario() {
        return DatosUsuario;
    }

    public void setDatosUsuario(String DatosUsuario) {
        this.DatosUsuario=DatosUsuario;
    }

    String NotFound="USUARIO NO ENCONTRADO";
    String Found="USUARIO AUTENTICADO CORRECTAMENTE";
    
     public void add (String nick, String nif) throws UnsupportedEncodingException{
		//codificamos cada uno de los valores
		if (DatosUsuario.length()>0)
		DatosUsuario+= "&"+ URLEncoder.encode(nick, "UTF-8")+ "=" +URLEncoder.encode(nif, "UTF-8");
		else
		DatosUsuario+= URLEncoder.encode(nick, "UTF-8")+ "=" +URLEncoder.encode(nif, "UTF-8");
	}
     
    public String Acceder (String nick, String nif) throws MalformedURLException, IOException{ //throws MalformedURLException: Excepción de error en la URL.
		String respuesta = "";
		//abrimos la conexion
		URLConnection conn = URLServidor.openConnection();
                
     //++++++++++++++++++++++++++ ESCRIBIR +++++++++++++++++++++++++//
		
                 conn.setDoOutput(true);    //Especificamos que vamos a escribir
		OutputStreamWriter escribir = new OutputStreamWriter(conn.getOutputStream()); //Para obtener el flujo de lectura
		escribir.write(DatosUsuario); //Escribimos los datos del usuario
		escribir.close(); //Finalizamos la escritura
		
    //+++++++++++++++++++++++++ LECTURA DE FLUJO ++++++++++++++++++++++++++++++++++++++++++++++//
		BufferedReader lectura = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String cadena;
                
		while ((cadena = lectura.readLine()) !=null) { //Realizamos la lectura concatenando la respuesta del jsp
		
                    if(NotFound.equals(cadena) || Found.equals(cadena)){
                        respuesta+= cadena;
                    }
                }
		return respuesta;
	}
  

        
  

}
