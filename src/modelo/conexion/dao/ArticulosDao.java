package modelo.conexion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controlador.Coordinador;
import modelo.conexion.ConexionFireBird;
import modelo.conexion.vo.ArticuloVo;

public class ArticulosDao {
	
	private Coordinador miCoordinador;
	
	public ArticuloVo consultarArticulo(String codigo, ConexionFireBird miConexion, String lista) throws SQLException {
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		ArticuloVo miArticulo =  new ArticuloVo();
		
		connection = miConexion.getConnection();
		
		String consulta = "SELECT a.arti_des AS descripcion, a.arti_descorta AS embalaje, p.prar_fijo as precio FROM articulo a\r\n" + 
				"INNER JOIN precios_articulo p ON a.arti_cod = p.arti_cod LEFT OUTER JOIN barras_articulo b ON  a.arti_cod = b.arti_cod\r\n" + 
				"WHERE (a.arti_cod = '"+codigo+"' OR b.coba_cod = '"+codigo+"') AND p.lipr_cod = "+lista;
		statement = connection.prepareStatement(consulta);
		resultado = statement.executeQuery();

		if (resultado.next()) {
			miArticulo.setCodigo(codigo);
			miArticulo.setDescripcion(resultado.getString("descripcion"));
			miArticulo.setEmbalaje(resultado.getString("embalaje"));
			miArticulo.setPrecio(resultado.getDouble("precio"));
		}
		resultado.close();
		statement.close();
		return miArticulo;	
	}

	public Coordinador getMiCoordinador() {
		return miCoordinador;
	}

	public void setMiCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

}
