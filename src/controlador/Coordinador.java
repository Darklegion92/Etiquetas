package controlador;

import java.sql.SQLException;

import modelo.conexion.ConexionFireBird;
import modelo.conexion.dao.ArticulosDao;
import modelo.conexion.vo.ArticuloVo;
import vistas.ventanaPrincipal;

public class Coordinador {

	private ventanaPrincipal miVentanaPrincipal;
	private ConexionFireBird miConexion;
	private ArticulosDao misArticulosDao;

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

	public ArticuloVo consultarArticulo(String codigo,ConexionFireBird miConexion,int lista) throws SQLException {
		
		return misArticulosDao.consultarArticulo(codigo, miConexion, lista);
	}
	
}