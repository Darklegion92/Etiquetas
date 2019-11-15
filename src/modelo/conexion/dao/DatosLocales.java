package modelo.conexion.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import controlador.Coordinador;
import modelo.conexion.vo.DatosVo;

public class DatosLocales {

	private Coordinador miCoordinador;
	private static String nombre = "datos.config";

	public DatosVo cargar() throws IOException {

		File archivo = new File(nombre);
		DatosVo misDatos = null;
		if (archivo.exists()) {

			String[] datos;
			FileReader f = new FileReader(nombre);
			BufferedReader b = new BufferedReader(f);
			
			if ((datos = b.readLine().split("-")) != null) {
				String[] licencia = miCoordinador.desEncryptar(datos[6]).split("-");
				if(!miCoordinador.obtenerSerial().equals(licencia[0])) licencia[2] = "false";
				misDatos = new DatosVo(datos[0], datos[1], datos[2], miCoordinador.desEncryptar(datos[3]), datos[4], datos[5],licencia[2]);
			}
			b.close();
		} else {

			do {
				miCoordinador.abrirVentanaDatos();

			} while (misDatos != null);

			misDatos = miCoordinador.getMisDatos();

		}
		return misDatos;
	}

	public void guardar(DatosVo misDatos) {

		FileWriter fichero = null;
		PrintWriter pw = null;

		try {
			fichero = new FileWriter(nombre);
			pw = new PrintWriter(fichero);
			pw.println(misDatos.getServidor() + "-" + misDatos.getRuta() + "-" + misDatos.getUsuario() + "-"
					+miCoordinador.encryptar(misDatos.getContrasenia()) + "-" + misDatos.getListaPrecios() + "-" + misDatos.getEmpresa()+"-"+misDatos.getLicencia());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	public Coordinador getMiCoordinador() {
		return miCoordinador;
	}

	public void setMiCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

	public void eliminarArchivo() {
		File fichero = new File(nombre);
		fichero.delete();

	}
}
