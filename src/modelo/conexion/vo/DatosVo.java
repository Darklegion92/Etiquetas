package modelo.conexion.vo;

public class DatosVo {

	private String servidor;
	private String ruta;
	private String usuario;
	private String contrasenia;
	private String listaPrecios;
	private String empresa;
	private String licencia;

	public DatosVo(String servidor, String ruta, String usuario, String contrasenia, String listaPrecios,
			String empresa, String licencia) {
		super();
		this.servidor = servidor;
		this.ruta = ruta;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.listaPrecios = listaPrecios;
		this.empresa = empresa;
		this.licencia = licencia;
	}

	public String getLicencia() {
		return licencia;
	}

	public void setLicencia(String licencia) {
		this.licencia = licencia;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getListaPrecios() {
		return listaPrecios;
	}

	public void setListaPrecios(String listaPrecios) {
		this.listaPrecios = listaPrecios;
	}

	public String getServidor() {
		return servidor;
	}

	public void setServidor(String servidor) {
		this.servidor = servidor;
	}
}
