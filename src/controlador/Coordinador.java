package controlador;

import java.io.IOException;
import java.sql.SQLException;

import modelo.conexion.ConexionFireBird;
import modelo.conexion.dao.ArticulosDao;
import modelo.conexion.dao.DatosLocales;
import modelo.conexion.dao.gestionLicenciaDao;
import modelo.conexion.vo.ArticuloVo;
import modelo.conexion.vo.DatosVo;
import vistas.ventanaPrincipal;
import vistas.vistaDatos;

public class Coordinador {

	private ventanaPrincipal miVentanaPrincipal;
	private ConexionFireBird miConexion;
	private ArticulosDao misArticulosDao;
	private DatosLocales misDatos;
	private vistaDatos miVistaDatos;
	private gestionLicenciaDao miGestionLicencia;

	private String listaPrecios;

	public gestionLicenciaDao getMiGestionLicencia() {
		return miGestionLicencia;
	}

	public void setMiGestionLicencia(gestionLicenciaDao miGestionLicencia) {
		this.miGestionLicencia = miGestionLicencia;
	}

	public String getListaPrecios() {
		return listaPrecios;
	}

	public void setListaPrecios(String listaPrecios) {
		this.listaPrecios = listaPrecios;
	}

	public vistaDatos getMiVistaDatos() {
		return miVistaDatos;
	}

	public void setMiVistaDatos(vistaDatos miVistaDatos) {
		this.miVistaDatos = miVistaDatos;
	}

	public void setMisDatos(DatosLocales misDatos) {
		this.misDatos = misDatos;
	}

	public ventanaPrincipal getMiVentanaPrincipal() {
		return miVentanaPrincipal;
	}

	public void setMiVentanaPrincipal(ventanaPrincipal miVentanaPrincipal) {
		this.miVentanaPrincipal = miVentanaPrincipal;
	}

	public ConexionFireBird getMiConexion() {
		return miConexion;
	}

	public void setMiConexion(ConexionFireBird miConexion) {
		this.miConexion = miConexion;
	}

	public ArticulosDao getMisArticulosDao() {
		return misArticulosDao;
	}

	public void setMisArticulosDao(ArticulosDao misArticulosDao) {
		this.misArticulosDao = misArticulosDao;
	}

	public ArticuloVo consultarArticulo(String codigo, ConexionFireBird miConexion) throws SQLException {

		return misArticulosDao.consultarArticulo(codigo, miConexion, listaPrecios);
	}

	public void guardarDatos(DatosVo datos) {
		misDatos.guardar(datos);

	}

	public void cerrarVentanaDatos() {
		miVistaDatos.setVisible(false);

	}

	public void abrirVentanaDatos() {
		miVistaDatos.setVisible(true);

	}

	public DatosVo getMisDatos() {
		return miVistaDatos.getMisDatos();
	}

	public DatosVo cargarDatos() throws IOException {
		return misDatos.cargar();
	}

	public boolean validarLicencia(String licencia) throws IOException {
		return miGestionLicencia.validarLicencia(licencia);
	}

	public void encryptarLicencia(String dato) {
		DatosVo datos = getMisDatos();		
		datos.setLicencia(miGestionLicencia.encryptar(dato));
		setDatos(datos);		
	}

	public void setDatos(DatosVo datos) {
		miVistaDatos.setMisDatos(datos);
	}

	public void eliminarArchivo() {
		misDatos.eliminarArchivo();
		
	}

	public String desEncryptar(String dato) {
		return miGestionLicencia.desencryptar(dato);
	}

	public String obtenerSerial() {
		return gestionLicenciaDao.obtenertSerial();
	}

}