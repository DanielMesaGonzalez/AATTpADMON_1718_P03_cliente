
package practica_3_aattaa_cliente;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Daniel Mesa y Salvador Trujillo
 */
public class PeticionPOST {
    private String URLServidor; //URL donde vamos a enviar la petición.
    private String DatosUsuario; //Contiene los datos del usuario: NICK y DNI.
 
     public PeticionPOST(String direccion,String Nick,String DNI){ //Constructor.
        this.URLServidor =direccion;
        this.DatosUsuario="Nick="+Nick+"&DNI="+DNI;
    }

    public String getURLServidor() { //Devuelve la dirección.
        return URLServidor;
    }

    public void setURLServidor(String URLServidor) { //Inserta la dirección.
        this.URLServidor = URLServidor;
    }

    public String getDatosUsuario() { //Devuelve los datos del usuario.
        return DatosUsuario;
    }

    public void setDatosUsuario(String DatosUsuario) { //Inserta los datos del usuario.
        this.DatosUsuario = DatosUsuario;
    }
     
    public String Acceder () throws MalformedURLException, IOException{ //throws MalformedURLException: Excepción de error en la URL.
        byte[] envio = this.DatosUsuario.getBytes( StandardCharsets.UTF_8); //Datos del usuario que se envían.
        String respuesta="";// para almacenar lo que se responde
        URL url = new URL(DatosUsuario);   //Instancia del tipo URL
        URLConnection conectar = url.openConnection();// Abrimos la conexión mediante esta instancia
        
  //++++++++++++++++++++++++++ ESPECIFICAMOS LO QUE VAMOS A ESCRIBIR +++++++++++++++++++++++++//
        
        conectar.setDoOutput(true); //Indicamos que vamos a comenzar a escribir 
        conectar.setRequestProperty("Content-Type","application/x-www-form-urlencoded");//Indicamos el tipo de contenido.
        conectar.setRequestProperty("charset","UTF-8");//Indicamos el tipo de codificación.
        conectar.setRequestProperty("Conent-length", Integer.toString(envio.length));//Longitud del contenido.
        OutputStreamWriter escribir = new OutputStreamWriter(conectar.getOutputStream());//obtenemos el flujo de lo que se escribe 
        escribir.write(DatosUsuario);  //Escribe los datos del usuario
        //Cuando se introducen..
        escribir.close(); //Finalizamos 
        return null; //Es un string que nos debe mostrar la cadena (respuesta) 
        
    }
}
