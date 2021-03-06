/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_3_aattaa_cliente;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JOptionPane;


/**
 * Aplicaciones Telemáticas para la Administración
 * 
 * Este programa debe leer el nombre y NIF de un usuario del DNIe, formar el identificador de usuario y autenticarse con un servidor remoto a travÃ©s de HTTP 
 * @author Daniel Mesa y Salvador Trujillo
 */
public class Main {
    Usuario user=new Usuario();
 
    public static void main(String[] args) throws Exception{
        ByteArrayInputStream bais=null;
        
        //TAREA 2. Conseguir que el mÃ©todo LeerNIF de ObtenerDatos devuelva el 
        //         correctamente los datos de usuario 
        ObtenerDatos od = new ObtenerDatos();
        Usuario user = od.LeerNIF();
        if(user!=null)
            
            System.out.println("usuario: "+user.toString());
        else{
            JOptionPane.showMessageDialog(null, "ERROR al leer los datos de la tarjeta");}
    
        //TAREA 3. Conseguir que el cliente se identifique correctamente
        //con el servidor
         String nif="", nick="";
         
  //+++++++++++++++++ CREAMOS LA PETICIÓN PARA EL SERVIDOR +++++++++++++++++++++//
  
       PeticionPOST peticion=new PeticionPOST("http://localhost:8080/DNIE/CompruebaBBDD");
       peticion.add("nick",user.getNick()); 
       peticion.add("nif",user.getNif());
       String respuesta = peticion.Acceder(nick, nif);
        JOptionPane.showMessageDialog(null, ""+respuesta);
    
    }
}
