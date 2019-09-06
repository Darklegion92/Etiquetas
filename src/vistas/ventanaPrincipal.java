package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.PrinterName;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controlador.Coordinador;
import modelo.conexion.ConexionFireBird;
import modelo.conexion.vo.ArticuloVo;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

public class ventanaPrincipal extends JFrame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int lista = 1;
	private JPanel contentPane;
	private Coordinador miCoordinador;
	private JTextField txtCodigo;
	JLabel lblError;

	/**
	 * Create the frame.
	 */
	public ventanaPrincipal() {
		iniciarComponentes();
		//seleccionarImpresora();		 
	}

	private void iniciarComponentes() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/recursos/imagenes/logoVentana.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 438, 408);
		setTitle("Etiquetas Impresión");
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel(" ");
		panel.add(lblNewLabel, BorderLayout.NORTH);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/superLogo.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_1, BorderLayout.CENTER);
		
		JLabel lblNewLabel_2 = new JLabel(" ");
		panel.add(lblNewLabel_2, BorderLayout.SOUTH);
		
		JLabel lblNewLabel_3 = new JLabel(" ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 86));
		contentPane.add(lblNewLabel_3, BorderLayout.SOUTH);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignOnBaseline(true);
		flowLayout.setVgap(10);
		flowLayout.setHgap(10);
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		txtCodigo = new JTextField();
		panel_1.add(txtCodigo);
		txtCodigo.setColumns(25);
		txtCodigo.addKeyListener(this);
		
		lblError = new JLabel("                                                                                 ");
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_1.add(lblError);
		
	}
	

	private void consultarArtiulo(String codigo) {
		
		ConexionFireBird miConexion = miCoordinador.getMiConexion();
		
		if(miConexion != null) {
			if(!codigo.isEmpty()) {
				try {
					ArticuloVo miArticulo = miCoordinador.consultarArticulo(codigo,miConexion,lista);
					System.err.println(miArticulo.getDescripcion());
					if(!miArticulo.getDescripcion().equals("") || !miArticulo.getDescripcion().equals(null) ) {
						System.err.println(miArticulo.getDescripcion());	
						imprimir(miArticulo);
						txtCodigo.selectAll();
						lblError.setText("Artículo "+codigo+" Impreso Correctamente");
					}else {
						lblError.setText("Artículo "+codigo+" No Existe");
						txtCodigo.selectAll();
					}
				} catch (SQLException e) {
					lblError.setText("Artículo "+codigo+" No Existe");
					txtCodigo.selectAll();
				}	catch (NullPointerException ex ) {
					lblError.setText("Artículo "+codigo+" No Existe");
					txtCodigo.selectAll();
				}
				
			}else {
				lblError.setText("El código no puede estar Vacío");
			}
		}else {
			JOptionPane.showMessageDialog(null, "Se ha desconectado de la Base de datos \n Inicie el Programa Nuevamente");
			System.exit(0);
		}

	}
	
	private void imprimir(ArticuloVo miArticulo) {
		String dir_current = System.getProperty("user.dir");
		System.err.println(dir_current);
		URL ruta = getClass().getResource(dir_current+"/reportes/etiquetas.jrxml");
		Map<String, Object> parametros = new HashMap<>();
		
		NumberFormat FM = NumberFormat.getNumberInstance(new Locale("en"));
	    FM.setMaximumFractionDigits(0);
	    
	    String[] pum = new String[] {};
	   try {
		   pum = miArticulo.getEmbalaje().split(" ");
	   }catch (NullPointerException e) {
		   
	   }
	    
	    Double precioUnidad = 0.0;
	    if(pum.length == 2) {
	    	try {
	    		precioUnidad = miArticulo.getPrecio() / Double.parseDouble(pum[0]);
	    		parametros.put("pum","Precio Por "+pum[1]+" "+precioUnidad);
	    	}catch (NumberFormatException e) {
	    		parametros.put("pum","");
			}
	    }
	    Double precio = 0.0;
	    if(miArticulo.getPrecio()<100) {
	    	precio =  miArticulo.getPrecio()*1000;
	    }else {
	    	precio = miArticulo.getPrecio();
	    }
	    
		parametros.put("codigo",miArticulo.getCodigo());
		parametros.put("descripcion",miArticulo.getDescripcion());
		parametros.put("precio","$ "+FM.format(precio));
		PrintService[] printService = PrintServiceLookup.lookupPrintServices(null, null);
		PrintService impresora = null;
		if( printService.length>0 ){
			for (PrintService imp : printService) {
				if(imp.getName().equals("ETIQUETAS")) {
					impresora = imp;
					break;
				}
			}
		
		if(impresora != null) {
	    JasperPrint jasperPrint;
			try {				
				JasperReport report = JasperCompileManager.compileReport(dir_current+"/reportes/etiquetas.jrxml");//(JasperReport) JRLoader.loadObject(ruta);
				jasperPrint = JasperFillManager.fillReport(report, parametros, new JREmptyDataSource());
				JRPrintServiceExporter jrprintServiceExporter = new JRPrintServiceExporter();
				jrprintServiceExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint );
				jrprintServiceExporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, impresora );
				jrprintServiceExporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
				jrprintServiceExporter.exportReport();
				//JasperPrintManager.printReport(jasperPrint, false);
				txtCodigo.selectAll();
			} catch (Exception e) {
				System.err.println(e.getMessage());
				System.err.println(e.getLocalizedMessage());
				e.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(this, "La impresora 'ETIQUETAS' \nNo ha sido configuracda");
		}
		}else {
			JOptionPane.showMessageDialog(this, "No hay Impresoras Instaladas");
		}
	    
	}

	public Coordinador getMiCoordinador() {
		return miCoordinador;
	}

	public void setMiCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			consultarArtiulo(txtCodigo.getText().trim());
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
