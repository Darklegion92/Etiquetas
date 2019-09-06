package controlador;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelo.conexion.ConexionFireBird;
import modelo.conexion.dao.ArticulosDao;
import vistas.ventanaPrincipal;

public class Aplicacion {

	public void iniciarSistema() {
		
		ventanaPrincipal miVentanaPrincipal = new ventanaPrincipal();
		
		Coordinador miCoordinador = new Coordinador();
		
		ArticulosDao misArticulosDao = new ArticulosDao();

		ConexionFireBird miConexion = null;
		
		try {
			miConexion = new ConexionFireBird();
			miVentanaPrincipal.setVisible(true);
		} catch (ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "Se ha prensentado un error\n"+e.getMessage()); 
            System.exit(0);
         }
		
		if(miConexion != null) {
			
		}else {
			JOptionPane.showMessageDialog(null, "Usuario o Clave incorrectas"); 
			System.exit(0);
		}
		
		miVentanaPrincipal.setMiCoordinador(miCoordinador);
		misArticulosDao.setMiCoordinador(miCoordinador);
		
		miCoordinador.setMiVentanaPrincipal(miVentanaPrincipal);
		miCoordinador.setMiConexion(miConexion);
		miCoordinador.setMisArticulosDao(misArticulosDao);
		
		
		
	}
}