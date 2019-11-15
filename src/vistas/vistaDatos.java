package vistas;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controlador.Coordinador;
import modelo.conexion.vo.DatosVo;

public class vistaDatos extends JDialog implements KeyListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtServidor;
	private JTextField txtListaPrecios;
	private JTextField txtEmpresa;
	private JTextField txtRuta;
	private JTextField txtUsuario;
	private JTextField txtContrasenia;
	private JTextField txtLicencia;
	private JLabel lblServidor;
	private JLabel lblEmpresa;
	private JLabel lblListaPrecios;
	private JLabel lblRuta;
	private JLabel lblUsuario;
	private JLabel lblContrasenia;
	private JLabel lblLicencia;
	private JButton btnGuardar;
	private JButton btnSalir;

	private DatosVo misDatos;

	private Coordinador miCoordinador;

	/**
	 * Launch the application.
	 * 
	 * public static void main(String[] args) { try { vistaDatos dialog = new
	 * vistaDatos(); dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 */

	/**
	 * Create the dialog.
	 */
	public vistaDatos(JFrame miVentana, boolean modal) {
		super(miVentana, modal);
		setUndecorated(true);
		setBounds(100, 100, 314, 600);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		{
			lblServidor = new JLabel("SERVIDOR: ");
			lblServidor.setHorizontalAlignment(SwingConstants.CENTER);
		}
		{
			txtServidor = new JTextField();
			txtServidor.setColumns(10);
			txtServidor.addKeyListener(this);
		}
		{
			lblRuta = new JLabel("RUTA: ");
			lblRuta.setHorizontalAlignment(SwingConstants.CENTER);
		}
		{
			txtRuta = new JTextField();
			txtRuta.setColumns(10);
			txtRuta.addKeyListener(this);
		}
		{
			lblUsuario = new JLabel("USUARIO: ");
			lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		}
		{
			txtUsuario = new JTextField();
			txtUsuario.setColumns(10);
			txtUsuario.addKeyListener(this);
		}
		{
			lblContrasenia = new JLabel("CONTRASEÑA: ");
			lblContrasenia.setHorizontalAlignment(SwingConstants.CENTER);
		}
		{
			txtContrasenia = new JTextField();
			txtContrasenia.setColumns(10);
			txtContrasenia.addKeyListener(this);
		}

		{
			lblEmpresa = new JLabel("EMPRESA: ");
			lblEmpresa.setHorizontalAlignment(SwingConstants.CENTER);
		}
		{
			txtEmpresa = new JTextField();
			txtEmpresa.setColumns(10);
			txtEmpresa.addKeyListener(this);
		}

		{
			lblListaPrecios = new JLabel("LISTA PRECIOS: ");
			lblListaPrecios.setHorizontalAlignment(SwingConstants.CENTER);
		}
		{
			txtListaPrecios = new JTextField();
			txtListaPrecios.setColumns(10);
			txtListaPrecios.addKeyListener(this);
		}
		{
			lblLicencia = new JLabel("CÓDIGO DE LICENCIA: ");
			lblLicencia.setHorizontalAlignment(SwingConstants.CENTER);
		}
		{
			txtLicencia = new JTextField();
			txtLicencia.setColumns(10);
			txtLicencia.addKeyListener(this);
		}

		btnGuardar = new JButton("GUARDAR");
		btnGuardar.addMouseListener(this);

		btnSalir = new JButton("SALIR");
		btnSalir.addMouseListener(this);

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(
				Alignment.TRAILING,
				gl_contentPanel.createSequentialGroup().addGap(51).addGroup(gl_contentPanel
						.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblServidor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
						.addComponent(lblEmpresa, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
						.addComponent(lblRuta, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
						.addComponent(lblUsuario, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
						.addComponent(lblContrasenia, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
						.addComponent(lblListaPrecios, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 208,
								Short.MAX_VALUE)
						.addComponent(lblLicencia, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 208,
								Short.MAX_VALUE)
						.addComponent(btnGuardar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
						.addComponent(txtServidor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
						.addComponent(txtEmpresa, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
						.addComponent(btnSalir, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
						.addComponent(txtListaPrecios, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 193,
								Short.MAX_VALUE)
						.addComponent(txtRuta, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
						.addComponent(txtUsuario, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
						.addComponent(txtContrasenia, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 193,
								Short.MAX_VALUE)
						.addComponent(txtLicencia, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 193,
						Short.MAX_VALUE))
						.addGap(69)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
				.createSequentialGroup().addGap(30)
				.addComponent(lblServidor, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(txtServidor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(lblEmpresa, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(txtEmpresa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(lblRuta, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(txtRuta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(lblUsuario, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(lblContrasenia, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(txtContrasenia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(lblListaPrecios, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(txtListaPrecios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(lblLicencia, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(txtLicencia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED).addGap(20).addComponent(btnGuardar)
				.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnSalir).addContainerGap()));
		contentPanel.setLayout(gl_contentPanel);
	}

	public Coordinador getMiCoordinador() {
		return miCoordinador;
	}

	public void setMiCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (e.getSource() == txtServidor) {
				txtListaPrecios.requestFocus();
			}

		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == btnSalir) {
			System.exit(0);
		}
		if (e.getSource() == btnGuardar) {
			if (!txtServidor.getText().isEmpty() && !txtListaPrecios.getText().isEmpty()) {
				this.misDatos = new DatosVo(txtServidor.getText(), txtRuta.getText(), txtUsuario.getText(),
						txtContrasenia.getText(), txtListaPrecios.getText(), txtEmpresa.getText(), txtLicencia.getText());
				miCoordinador.guardarDatos(misDatos);
				miCoordinador.cerrarVentanaDatos();
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public DatosVo getMisDatos() {
		return misDatos;
	}

	public void setMisDatos(DatosVo misDatos) {
		this.misDatos = misDatos;
	}

}
