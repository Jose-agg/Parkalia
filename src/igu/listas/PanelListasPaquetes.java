package igu.listas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import igu.main.VentanaPrincipal;
import igu.visores.PanelVisorPaquetes;
import logica.tipos.Alojamiento;
import logica.tipos.Paquete;
import logica.tipos.ParqueTematico;

public class PanelListasPaquetes extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel pnFiltros;
	private JScrollPane scrpnMercancias;
	private JPanel pnBuscador;
	private JTextField txtBuscador;
	private JButton btBuscador;
	private JPanel pnSubBuscador;
	private JPanel pnParque;
	private JLabel lblParque;
	private JComboBox<String> cbxParque;
	private JButton btnParque;
	private JPanel pnSubParque;
	private JPanel pnSubSubBuscador;
	private JPanel pnPrecio;
	private JLabel lblPrecio;
	private JPanel pnSubPrecio;
	private JPanel pnSubPrecioAdulto;
	private JLabel lblAdulto;
	private JSlider sldAdulto;
	private JPanel pnSubPrecioKid;
	private JLabel lblKid;
	private JSlider sldKid;
	private JPanel pnSubPrecioBuscar;
	private JButton btnPrecio;
	private JPanel pnVistaMercancias;
	private JButton btnQuitarFiltros;
	private JTextField txtPrecioAdulto;
	private JTextField txtPrecioKid;
	private JPanel pnBuscadorTexto;
	private JLabel lblBuscadorTexto;
	private JPanel pnSubBuscadorTexto;

	private PanelListasGeneral plg;
	private VentanaPrincipal vp;
	private JPanel pnPaquetes;
	private JPanel pnDuracion;
	private JLabel lblDuracion;
	private JPanel pnSubDuracion;
	private JSpinner spinDuracion;
	private JButton btnDuracion;

	public PanelListasPaquetes(PanelListasGeneral plg) {
		this.plg = plg;
		this.vp = this.plg.getVP();
		setLayout(new BorderLayout(0, 0));
		add(getPnFiltros(), BorderLayout.WEST);
		add(getScrpnMercancias(), BorderLayout.CENTER);
		rellenarMercancias(this.vp.getManager().getListaPaquetes());
		limitarSliders();
	}

	private JPanel getPnFiltros() {
		if (pnFiltros == null) {
			pnFiltros = new JPanel();
			pnFiltros.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Filtros", TitledBorder.LEADING,
					TitledBorder.TOP, null, Color.BLACK));
			pnFiltros.setLayout(new BorderLayout(0, 10));
			pnFiltros.add(getPnBuscador(), BorderLayout.NORTH);
			pnFiltros.add(getPnSubBuscador());
			pnFiltros.add(getBtnQuitarFiltros(), BorderLayout.SOUTH);
		}
		return pnFiltros;
	}

	private JScrollPane getScrpnMercancias() {
		if (scrpnMercancias == null) {
			scrpnMercancias = new JScrollPane();
			scrpnMercancias.setFont(new Font("Tahoma", Font.PLAIN, 14));
			scrpnMercancias.setViewportView(getPnVistaMercancias());
		}
		return scrpnMercancias;
	}

	private JPanel getPnBuscador() {
		if (pnBuscador == null) {
			pnBuscador = new JPanel();
			pnBuscador.setLayout(new BorderLayout(0, 0));
			pnBuscador.add(getPnBuscadorTexto(), BorderLayout.NORTH);
			pnBuscador.add(getPnSubBuscadorTexto(), BorderLayout.SOUTH);
		}
		return pnBuscador;
	}

	private JPanel getPnBuscadorTexto() {
		if (pnBuscadorTexto == null) {
			pnBuscadorTexto = new JPanel();
			pnBuscadorTexto.setLayout(new BorderLayout(0, 0));
			pnBuscadorTexto.add(getLblBuscadorTexto(), BorderLayout.CENTER);
		}
		return pnBuscadorTexto;
	}

	private JLabel getLblBuscadorTexto() {
		if (lblBuscadorTexto == null) {
			lblBuscadorTexto = new JLabel("Buscar por palabras");
			lblBuscadorTexto.setDisplayedMnemonic('B');
			lblBuscadorTexto.setLabelFor(getTxtBuscador());
		}
		return lblBuscadorTexto;
	}

	private JPanel getPnSubBuscadorTexto() {
		if (pnSubBuscadorTexto == null) {
			pnSubBuscadorTexto = new JPanel();
			pnSubBuscadorTexto.setLayout(new BorderLayout(0, 0));
			pnSubBuscadorTexto.add(getTxtBuscador(), BorderLayout.CENTER);
			pnSubBuscadorTexto.add(getBtBuscador(), BorderLayout.EAST);
		}
		return pnSubBuscadorTexto;
	}

	private JTextField getTxtBuscador() {
		if (txtBuscador == null) {
			txtBuscador = new JTextField();
			txtBuscador.setColumns(10);
		}
		return txtBuscador;
	}

	private JButton getBtBuscador() {
		if (btBuscador == null) {
			btBuscador = new JButton("Buscar");
			btBuscador.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (txtBuscador.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Introduzca una palabra sobre la que buscar");
					} else {
						rellenarMercancias(vp.getManager().filtrarPorDenominacionPaquete(txtBuscador.getText()));
					}
				}
			});
			redimensionarImagen("/img/Buscador.jpg", 20, 20);
		}
		return btBuscador;
	}

	private void redimensionarImagen(String ruta, int width, int height) {
		Image imgOriginal = new ImageIcon(getClass().getResource(ruta)).getImage();
		Image imgEscalada = imgOriginal.getScaledInstance((width), (height), Image.SCALE_FAST);
		btBuscador.setIcon(new ImageIcon(imgEscalada));
		btBuscador.setDisabledIcon(new ImageIcon(imgEscalada));
	}

	private JPanel getPnSubBuscador() {
		if (pnSubBuscador == null) {
			pnSubBuscador = new JPanel();
			pnSubBuscador.setLayout(new BorderLayout(0, 10));
			pnSubBuscador.add(getPnParque(), BorderLayout.NORTH);
			pnSubBuscador.add(getPnSubSubBuscador(), BorderLayout.CENTER);
		}
		return pnSubBuscador;
	}

	private JPanel getPnParque() {
		if (pnParque == null) {
			pnParque = new JPanel();
			pnParque.setBorder(new MatteBorder(1, 0, 1, 0, new Color(0, 0, 0)));
			pnParque.setLayout(new BorderLayout(0, 5));
			pnParque.add(getLblParque(), BorderLayout.NORTH);
			pnParque.add(getPnSubParque());
		}
		return pnParque;
	}

	private JLabel getLblParque() {
		if (lblParque == null) {
			lblParque = new JLabel("Seleccione un parque tematico");
			lblParque.setDisplayedMnemonic('S');
			lblParque.setLabelFor(getCbxParque());
			lblParque.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblParque;
	}

	private JComboBox<String> getCbxParque() {
		if (cbxParque == null) {
			cbxParque = new JComboBox<String>();
			cbxParque.setFont(new Font("Tahoma", Font.PLAIN, 14));
			cbxParque.setModel(new DefaultComboBoxModel<String>(obtenerParques()));
		}
		return cbxParque;
	}

	private JButton getBtnParque() {
		if (btnParque == null) {
			btnParque = new JButton("Buscar");
			btnParque.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (cbxParque.getSelectedItem() == null) {
						JOptionPane.showMessageDialog(null, "Seleccione un parque sobre el que buscar");
					} else {
						rellenarMercancias(
								vp.getManager().filtrarPorParquePaquete((String) cbxParque.getSelectedItem()));
					}
				}
			});
			btnParque.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return btnParque;
	}

	private JPanel getPnSubParque() {
		if (pnSubParque == null) {
			pnSubParque = new JPanel();
			pnSubParque.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnSubParque.add(getCbxParque());
			pnSubParque.add(getBtnParque());
		}
		return pnSubParque;
	}

	private JPanel getPnSubSubBuscador() {
		if (pnSubSubBuscador == null) {
			pnSubSubBuscador = new JPanel();
			pnSubSubBuscador.setLayout(new BorderLayout(0, 5));
			pnSubSubBuscador.add(getPnPrecio(), BorderLayout.NORTH);
			pnSubSubBuscador.add(getPnPaquetes(), BorderLayout.CENTER);
		}
		return pnSubSubBuscador;
	}

	private JPanel getPnPrecio() {
		if (pnPrecio == null) {
			pnPrecio = new JPanel();
			pnPrecio.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
			pnPrecio.setLayout(new BorderLayout(0, 0));
			pnPrecio.add(getLblPrecio(), BorderLayout.NORTH);
			pnPrecio.add(getPnSubPrecio());
		}
		return pnPrecio;
	}

	private JLabel getLblPrecio() {
		if (lblPrecio == null) {
			lblPrecio = new JLabel("Maximo precio");
			lblPrecio.setLabelFor(getBtnPrecio());
			lblPrecio.setDisplayedMnemonic('M');
			lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblPrecio;
	}

	private JPanel getPnSubPrecio() {
		if (pnSubPrecio == null) {
			pnSubPrecio = new JPanel();
			pnSubPrecio.setLayout(new BorderLayout(0, 5));
			pnSubPrecio.add(getPnSubPrecioAdulto(), BorderLayout.NORTH);
			pnSubPrecio.add(getPnSubPrecioKid(), BorderLayout.CENTER);
			pnSubPrecio.add(getPnSubPrecioBuscar(), BorderLayout.SOUTH);
		}
		return pnSubPrecio;
	}

	private JPanel getPnSubPrecioAdulto() {
		if (pnSubPrecioAdulto == null) {
			pnSubPrecioAdulto = new JPanel();
			pnSubPrecioAdulto.add(getLblAdulto());
			pnSubPrecioAdulto.add(getSldAdulto());
			pnSubPrecioAdulto.add(getTxtPrecioAdulto());
		}
		return pnSubPrecioAdulto;
	}

	private JLabel getLblAdulto() {
		if (lblAdulto == null) {
			lblAdulto = new JLabel("Adulto");
			lblAdulto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblAdulto;
	}

	private JSlider getSldAdulto() {
		if (sldAdulto == null) {
			sldAdulto = new JSlider();
			sldAdulto.setValue(0);
			sldAdulto.setPaintTicks(true);
			sldAdulto.setMinorTickSpacing(25);
			sldAdulto.setMajorTickSpacing(50);
			sldAdulto.setFocusable(false);
			sldAdulto.setFont(new Font("Tahoma", Font.PLAIN, 14));
			sldAdulto.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent arg0) {
					txtPrecioAdulto.setText(String.valueOf(sldAdulto.getValue()));
				}
			});
		}
		return sldAdulto;
	}

	private JPanel getPnSubPrecioKid() {
		if (pnSubPrecioKid == null) {
			pnSubPrecioKid = new JPanel();
			pnSubPrecioKid.add(getLblKid());
			pnSubPrecioKid.add(getSldKid());
			pnSubPrecioKid.add(getTxtPrecioKid());
		}
		return pnSubPrecioKid;
	}

	private JLabel getLblKid() {
		if (lblKid == null) {
			lblKid = new JLabel("  Ni\u00F1o ");
			lblKid.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblKid;
	}

	private JSlider getSldKid() {
		if (sldKid == null) {
			sldKid = new JSlider();
			sldKid.setValue(0);
			sldKid.setPaintTicks(true);
			sldKid.setMinorTickSpacing(25);
			sldKid.setMajorTickSpacing(50);
			sldKid.setFocusable(false);
			sldKid.setFont(new Font("Tahoma", Font.PLAIN, 14));
			sldKid.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent arg0) {
					txtPrecioKid.setText(String.valueOf(sldKid.getValue()));
				}
			});
		}
		return sldKid;
	}

	private JPanel getPnSubPrecioBuscar() {
		if (pnSubPrecioBuscar == null) {
			pnSubPrecioBuscar = new JPanel();
			pnSubPrecioBuscar.setLayout(new BorderLayout(0, 5));
			pnSubPrecioBuscar.add(getBtnPrecio(), BorderLayout.EAST);
		}
		return pnSubPrecioBuscar;
	}

	private JButton getBtnPrecio() {
		if (btnPrecio == null) {
			btnPrecio = new JButton("Buscar");
			btnPrecio.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					rellenarMercancias(
							vp.getManager().filtrarPorPrecioPaquete(sldAdulto.getValue(), sldKid.getValue()));
				}
			});
			btnPrecio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return btnPrecio;
	}

	private JPanel getPnVistaMercancias() {
		if (pnVistaMercancias == null) {
			pnVistaMercancias = new JPanel();
			pnVistaMercancias.setBorder(null);
		}
		return pnVistaMercancias;
	}

	private JButton getBtnQuitarFiltros() {
		if (btnQuitarFiltros == null) {
			btnQuitarFiltros = new JButton("Quitar filtros aplicados");
			btnQuitarFiltros.setMnemonic('Q');
			btnQuitarFiltros.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					limpiarCampos();
				}
			});
			btnQuitarFiltros.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return btnQuitarFiltros;
	}

	public void limpiarCampos() {
		rellenarMercancias(vp.getManager().getListaPaquetes());
	}

	private JTextField getTxtPrecioAdulto() {
		if (txtPrecioAdulto == null) {
			txtPrecioAdulto = new JTextField();
			txtPrecioAdulto.setDisabledTextColor(Color.BLACK);
			txtPrecioAdulto.setEnabled(false);
			txtPrecioAdulto.setEditable(false);
			txtPrecioAdulto.setColumns(4);
			txtPrecioAdulto.setText(String.valueOf(sldAdulto.getValue()));
		}
		return txtPrecioAdulto;
	}

	private JTextField getTxtPrecioKid() {
		if (txtPrecioKid == null) {
			txtPrecioKid = new JTextField();
			txtPrecioKid.setDisabledTextColor(Color.BLACK);
			txtPrecioKid.setEnabled(false);
			txtPrecioKid.setEditable(false);
			txtPrecioKid.setColumns(4);
			txtPrecioKid.setText(String.valueOf(sldKid.getValue()));
		}
		return txtPrecioKid;
	}

	private JPanel getPnPaquetes() {
		if (pnPaquetes == null) {
			pnPaquetes = new JPanel();
			pnPaquetes.setLayout(new BorderLayout(0, 5));
			pnPaquetes.add(getPnDuracion(), BorderLayout.NORTH);
		}
		return pnPaquetes;
	}

	private JPanel getPnDuracion() {
		if (pnDuracion == null) {
			pnDuracion = new JPanel();
			pnDuracion.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
			pnDuracion.setLayout(new BorderLayout(0, 5));
			pnDuracion.add(getPnSubDuracion(), BorderLayout.NORTH);
			pnDuracion.add(getBtnDuracion(), BorderLayout.EAST);
		}
		return pnDuracion;
	}

	private JLabel getLblDuracion() {
		if (lblDuracion == null) {
			lblDuracion = new JLabel("Numero minimo de dias");
			lblDuracion.setLabelFor(getSpinDuracion());
			lblDuracion.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblDuracion.setDisplayedMnemonic('N');
		}
		return lblDuracion;
	}

	private JPanel getPnSubDuracion() {
		if (pnSubDuracion == null) {
			pnSubDuracion = new JPanel();
			pnSubDuracion.setLayout(new BorderLayout(0, 0));
			pnSubDuracion.add(getLblDuracion(), BorderLayout.WEST);
			pnSubDuracion.add(getSpinDuracion(), BorderLayout.EAST);
		}
		return pnSubDuracion;
	}

	private JSpinner getSpinDuracion() {
		if (spinDuracion == null) {
			spinDuracion = new JSpinner();
			spinDuracion.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		}
		return spinDuracion;
	}

	private JButton getBtnDuracion() {
		if (btnDuracion == null) {
			btnDuracion = new JButton("Buscar");
			btnDuracion.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					rellenarMercancias(vp.getManager().filtrarPorDuracionPaquete((int) spinDuracion.getValue()));
				}
			});
			btnDuracion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return btnDuracion;
	}

	// MIS METODOS \\

	private void rellenarMercancias(ArrayList<Paquete> array) {
		pnVistaMercancias.removeAll();
		PanelListasPaquetes panelActual = this;
		for (Paquete paquete : array) {
			ParqueTematico pt = this.vp.getManager().getParqueByCodigo(paquete.getCodigoParque());
			Alojamiento aloja = this.vp.getManager().getAlojamientoByCodigo(paquete.getCodigoAlojamiento());
			String infoExtra = "Parque: " + pt.getDenominacion() + ".\n\rAlojamiento: " + aloja.getDenominacion()
					+ ".\n\rDuracion: " + paquete.getDuracion() + " dias.\n\r";
			infoExtra += "Precio por adulto: " + paquete.getPrecioAdulto() + "€.\n\rPrecio por niño: "
					+ paquete.getPrecioKid() + "€.";
			if (paquete.getCodigoParque().equals(this.vp.getManager().getParqueOferta().getCodigoParque())) {
				infoExtra += "\n\r\n\rEl parque vinculado a este paquete esta en oferta.\n\r¡Compra ahora y llevate un descuento del 20%!";
			}
			PanelMercancias pnMercancia = new PanelMercancias(paquete, infoExtra);
			pnVistaMercancias.add(pnMercancia);
			pnMercancia.getBtVer().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					plg.mostrarPanelesVisores(new PanelVisorPaquetes(panelActual, paquete));
				}
			});
		}
		pnVistaMercancias.setLayout(new GridLayout(pnVistaMercancias.getComponentCount(), 1, 0, 0));
		pnVistaMercancias.updateUI();
	}

	public PanelListasGeneral getPLG() {
		return plg;
	}

	private String[] obtenerParques() {
		return this.vp.getManager().obtenerParques();
	}

	private void limitarSliders() {
		int[] precios = vp.getManager().getPreciosPaquetes();
		sldAdulto.setMinimum(precios[0]);
		sldAdulto.setMaximum(precios[1]);
		sldAdulto.setValue(precios[1]);
		sldKid.setMinimum(precios[2]);
		sldKid.setMaximum(precios[3]);
		sldKid.setValue(precios[3]);
		spinDuracion.setModel(new SpinnerNumberModel(1, 1, this.vp.getManager().getMaximaDuracionPaquete(), 1));
	}
}
