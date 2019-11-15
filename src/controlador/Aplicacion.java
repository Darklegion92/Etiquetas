package controlador;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelo.conexion.ConexionFireBird;
import modelo.conexion.dao.ArticulosDao;
import modelo.conexion.dao.DatosLocales;
import modelo.conexion.dao.gestionLicenciaDao;
import modelo.conexion.vo.DatosVo;
import vistas.ventanaPrincipal;
import vistas.vistaDatos;

public class Aplicacion {

	public void iniciarSistema() {

		ventanaPrincipal miVentanaPrincipal = new ventanaPrincipal();

		Coordinador miCoordinador = new Coordinador();

		ArticulosDao misArticulosDao = new ArticulosDao();

		vistaDatos miVistaDatos = new vistaDatos(miVentanaPrincipal, true);

		DatosLocales misDatosLocales = new DatosLocales();

		ConexionFireBird miConexion = null;

		gestionLicenciaDao gestionLicenciaDao = new gestionLicenciaDao();

		miVentanaPrincipal.setMiCoordinador(miCoordinador);
		misArticulosDao.setMiCoordinador(miCoordinador);
		miVistaDatos.setMiCoordinador(miCoordinador);
		misDatosLocales.setMiCoordinador(miCoordinador);
		gestionLicenciaDao.setMiCoordinador(miCoordinador);

		miCoordinador.setMiGestionLicencia(gestionLicenciaDao);
		miCoordinador.setMiVentanaPrincipal(miVentanaPrincipal);
		miCoordinador.setMisDatos(misDatosLocales);

		miCoordinador.setMisArticulosDao(misArticulosDao);
		miCoordinador.setMiVistaDatos(miVistaDatos);

		try {
			DatosVo misDatos = miCoordinador.cargarDatos();
			if (misDatos == null) misDatos = miCoordinador.getMisDatos();
			boolean licenciado = false;
			if(misDatos.getLicencia().length()==24) {			
					licenciado = gestionLicenciaDao.validarLicencia(misDatos.getLicencia(),miCoordinador.obtenerSerial());
					miCoordinador.eliminarArchivo();
					misDatos = miCoordinador.getMisDatos();
					miCoordinador.guardarDatos(misDatos);
			} else if(misDatos.getLicencia().equals("true")) licenciado = true;

			if (licenciado) {
				String URL = "jdbc:firebirdsql:" + misDatos.getServidor() + "/3050:" + misDatos.getRuta() + "/Datos/"
						+ misDatos.getEmpresa() + "/sysplus.fdb?lc_ctype=ISO8859_1";

				miConexion = new ConexionFireBird(URL, misDatos.getUsuario(), misDatos.getContrasenia());

				miCoordinador.setListaPrecios(misDatos.getListaPrecios());
				miVentanaPrincipal.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Error de licencia, Intente Nuevamente\n o llame a su proveedor");
				miCoordinador.eliminarArchivo();
				System.exit(0);
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			JOptionPane.showMessageDialog(null, "Se ha prensentado un error\n" + e.getMessage());
			System.exit(0);
		}

		if (miConexion != null) {

		} else {
			JOptionPane.showMessageDialog(null, "Usuario o Clave incorrectas");
			System.exit(0);
		}

		miCoordinador.setMiConexion(miConexion);

	}
}