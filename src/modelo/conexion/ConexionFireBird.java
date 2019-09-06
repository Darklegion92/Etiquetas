package modelo.conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConexionFireBird {
    String URL = "jdbc:firebirdsql:servidor/3050:E:/sysplus/Datos/JYK/sysplus.fdb?lc_ctype=ISO8859_1";
    //String URL = "jdbc:firebirdsql:localhost/3050:d:/firebird/sysplus.fdb?lc_ctype=ISO8859_1";
    String Usuario = "CLECTOR";
    String Contrasena = "1234";
    String Driver = "org.firebirdsql.jdbc.FBDriver";
    Connection con;

    public ConexionFireBird(Connection con) {
        this.con = con;
    }

    public ConexionFireBird() throws ClassNotFoundException, SQLException{
        con = null;
    
        Class.forName(Driver);
        con = DriverManager.getConnection(URL, Usuario, Contrasena);
        
       if (con !=null){
                    System.out.println("Conexion Establecida");
       }else {
    	   JOptionPane.showMessageDialog(null, "Se ha prensentado un error al momento de conectar a la bd");
       } 
    }
    public ConexionFireBird(String Usuario, String Contrasena){
        con = null;
         //Realizar conexion
     try {
        Class.forName(Driver);
        con = DriverManager.getConnection(URL, Usuario, Contrasena);
       if (con !=null){
                    System.out.println("Conexion Establecida");
                }
            } catch (ClassNotFoundException | SQLException e){
                System.out.println("error " + e);
                
            }
         }

        // este metodo nos retorna la conexion
    public Connection getConnection (){
        return con;
    }
        
   // con este metodo nos desconectamos de la Base de Datos
    public void desconectar(){
        con= null;
        if (con ==null){
                    System.out.println("Conexion Terminada");
        }
    }
   
    
}   
    
  

