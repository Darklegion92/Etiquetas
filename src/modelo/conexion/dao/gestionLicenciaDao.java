package modelo.conexion.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.Key;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.google.gson.Gson;
import com.lowagie.text.pdf.codec.Base64;

import controlador.Coordinador;
import modelo.conexion.vo.Licencia;

public class gestionLicenciaDao {

	private Coordinador miCoordinador;
	private static String ENCRYPT_KEY = "SOLTEC2018-18yr5";

	public boolean validarLicencia(String licencia) throws IOException {
		String datos = "";

		URL url = new URL("https://licenciassoltec.herokuapp.com/licencias/validar");

		Map<String, Object> params = new LinkedHashMap<>();

		params.put("key", licencia);

		StringBuilder postData = new StringBuilder();
		for (Map.Entry<String, Object> param : params.entrySet()) {
			if (postData.length() != 0)
				postData.append('&');
			postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
			postData.append('=');
			postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
		}
		byte[] postDataBytes = postData.toString().getBytes("UTF-8");

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
		conn.setDoOutput(true);
		conn.getOutputStream().write(postDataBytes);

		Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		for (int c = in.read(); c != -1; c = in.read())
			datos = datos + (char) c;

		Gson gson = new Gson();
		try {
			datos = datos.substring(13);
			datos = datos.substring(0, datos.length() - 1);
			String JSON = gson.toJson(datos);
			JSON = JSON.replace("\"", "");
			JSON = JSON.replace("\\", "");
			JSON = JSON.replace(":", ":\'");
			JSON = JSON.replace(",", "\',");
			JSON = JSON.replace("}", "\'}");
			Licencia miLicencia = gson.fromJson(JSON, Licencia.class);
			if (miLicencia.isActiva()) {

				miCoordinador.encryptarLicencia(
						miCoordinador.obtenerSerial() + "-" + miLicencia.get_id() + "-" + miLicencia.isActiva());
				return true;
			}

			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public String encryptar(String miDato) {
		try {
			Key aesKey = new SecretKeySpec(ENCRYPT_KEY.getBytes(), "AES");
			Cipher cipher;

			cipher = Cipher.getInstance("AES");

			cipher.init(Cipher.ENCRYPT_MODE, aesKey);

			byte[] encrypted = cipher.doFinal((miDato).getBytes());
			return Base64.encodeBytes(encrypted);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}

	}

	public String desencryptar(String encrypted) {
		try {
			byte[] encryptedBytes = Base64.decode(encrypted.replace("\n", ""));

			Key aesKey = new SecretKeySpec(ENCRYPT_KEY.getBytes(), "AES");

			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, aesKey);

			String decrypted = new String(cipher.doFinal(encryptedBytes));

			return decrypted;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	public Coordinador getMiCoordinador() {
		return miCoordinador;
	}

	public void setMiCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}
	/**
	 * Solo para winodwsw no funciona en otros sistemas operativos
	 * @return
	 */
	public static String obtenertSerial() {
		try {
			String line = null;
			String serial = null;
			Process process = Runtime.getRuntime().exec("cmd /c vol C:");
			BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
			while ((line = in.readLine()) != null) {
				if (line.toLowerCase().contains("serie")) {
					String[] strings = line.split(" ");
					serial = strings[strings.length - 1];
				}
			}
			in.close();
			return serial.replace("-", "");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

}
