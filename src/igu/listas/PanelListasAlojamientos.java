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
import javax.swing.JCheckBox;
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
import igu.visores.PanelVisorAlojamientos;
import logica.tipos.Alojamiento;

public class PanelListasAlojamientos extends JPanel {

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
	private JPanel pnSubPrecioNoche;
	private JLabel lblNoche;
	private JSlider sldNoche;
	private JPanel pnSubNoche;
	private JLabel lblAclaracion;
	private JPanel pnSubPrecioBuscar;
	private JButton btnPrecio;
	private JPanel pnVistaMercancias;
	private JButton btnQuitarFiltros;
	private JTextField txtNoche;
	private JPanel pnBuscadorTexto;
	private JLabel lblBuscadorTexto;
	private JPanel pnSubBuscadorTexto;
	private JLabel lblSubAclaracion;
	private JPanel pnAlojamientos;
	private JPanel pnTipo;
	private JPanel pnTipoAlojamiento;
	private JLabel lblTipo;
	private JPanel pnTipoBoton;
	private JButton btnTipo;
	private JCheckBox chckbxHotel;
	private JCheckBox chckbxApartahotel;
	private JCheckBox chckbxApartamento;
	private JPanel pnSubAlojamientos;
	private JPanel pnCategoria;
	private JLabel lblCategoria;
	private JPanel pnSubCategoria;
	private JSpinner spinCategoria;
	private JButton btnCategoria;

	private PanelListasGeneral plg;
	private VentanaPrincipal vp;

	public PanelListasAlojamientos(PanelListasGeneral plg) {
		this.plg = plg;
		this.vp = this.plg.getVP();
		setLayout(new BorderLayout(0, 0));
		add(getPnFiltros(), BorderLayout.WEST);
		add(getScrpnMercancias(), BorderLayout.CENTER);
		rellenarMercancias(this.vp.getManager().getListaAlojamientos());
		limitarSlider();
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
						rellenarMercancias(vp.getManager().filtrarPorDenominacionAlojamiento(txtBuscador.getText()));
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
								vp.getManager().filtrarPorParqueAlojamiento((String) cbxParque.getSelectedItem()));
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
			pnSubSubBuscador.setLayout(new BorderLayout(0, 0));
			pnSubSubBuscador.add(getPnPrecio(), BorderLayout.NORTH);
			pnSubSubBuscador.add(getPnAlojamientos(), BorderLayout.CENTER);
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
			pnSubPrecio.add(getPnSubPrecioNoche(), BorderLayout.NORTH);
			pnSubPrecio.add(getPnSubNoche(), BorderLayout.CENTER);
			pnSubPrecio.add(getPnSubPrecioBuscar(), BorderLayout.SOUTH);
		}
		return pnSubPrecio;
	}

	private JPanel getPnSubPrecioNoche() {
		if (pnSubPrecioNoche == null) {
			pnSubPrecioNoche = new JPanel();
			pnSubPrecioNoche.add(getLblNoche());
			pnSubPrecioNoche.add(getSldNoche());
			pnSubPrecioNoche.add(getTxtNoche());
		}
		return pnSubPrecioNoche;
	}

	private JLabel getLblNoche() {
		if (lblNoche == null) {
			lblNoche = new JLabel("Noche");
			lblNoche.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblNoche;
	}

	private JSlider getSldNoche() {
		if (sldNoche == null) {
			sldNoche = new JSlider();
			sldNoche.setValue(0);
			sldNoche.setPaintTicks(true);
			sldNoche.setMinorTickSpacing(10);
			sldNoche.setMajorTickSpacing(20);
			sldNoche.setFocusable(false);
			sldNoche.setFont(new Font("Tahoma", Font.PLAIN, 14));
			sldNoche.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent arg0) {
					txtNoche.setText(String.valueOf(sldNoche.getValue()));
				}
			});
		}
		return sldNoche;
	}

	private JPanel getPnSubNoche() {
		if (pnSubNoche == null) {
			pnSubNoche = new JPanel();
			pnSubNoche.setLayout(new BorderLayout(5, 5));
			pnSubNoche.add(getLblAclaracion(), BorderLayout.NORTH);
			pnSubNoche.add(getLblSubAclaracion());
		}
		return pnSubNoche;
	}

	private JLabel getLblAclaracion() {
		if (lblAclaracion == null) {
			lblAclaracion = new JLabel("Hoteles: por persona y noche");
			lblAclaracion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblAclaracion;
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
					rellenarMercancias(vp.getManager().filtrarPorPrecioAlojamiento(sldNoche.getValue()));
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
		rellenarMercancias(vp.getManager().getListaAlojamientos());
	}

	private JTextField getTxtNoche() {
		if (txtNoche == null) {
			txtNoche = new JTextField();
			txtNoche.setDisabledTextColor(Color.BLACK);
			txtNoche.setEnabled(false);
			txtNoche.setEditable(false);
			txtNoche.setColumns(4);
			txtNoche.setText(String.valueOf(sldNoche.getValue()));
		}
		return txtNoche;
	}

	private JLabel getLblSubAclaracion() {
		if (lblSubAclaracion == null) {
			lblSubAclaracion = new JLabel("Otros: por noche");
			lblSubAclaracion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblSubAclaracion;
	}

	private JPanel getPnAlojamientos() {
		if (pnAlojamientos == null) {
			pnAlojamientos = new JPanel();
			pnAlojamientos.setLayout(new BorderLayout(0, 5));
			pnAlojamientos.add(getPnTipo(), BorderLayout.NORTH);
			pnAlojamientos.add(getPnSubAlojamientos(), BorderLayout.CENTER);
		}
		return pnAlojamientos;
	}

	private JPanel getPnTipo() {
		if (pnTipo == null) {
			pnTipo = new JPanel();
			pnTipo.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
			pnTipo.setLayout(new BorderLayout(0, 5));
			pnTipo.add(getLblTipo(), BorderLayout.NORTH);
			pnTipo.add(getPnTipoAlojamiento(), BorderLayout.CENTER);
			pnTipo.add(getPnTipoBoton(), BorderLayout.SOUTH);
		}
		return pnTipo;
	}

	private JPanel getPnTipoAlojamiento() {
		if (pnTipoAlojamiento == null) {
			pnTipoAlojamiento = new JPanel();
			pnTipoAlojamiento.add(getChckbxHotel());
			pnTipoAlojamiento.add(getChckbxApartahotel());
			pnTipoAlojamiento.add(getChckbxApartamento());
		}
		return pnTipoAlojamiento;
	}

	private JLabel getLblTipo() {
		if (lblTipo == null) {
			lblTipo = new JLabel("Tipo de alojamiento");
			lblTipo.setDisplayedMnemonic('T');
			lblTipo.setLabelFor(getChckbxHotel());
		}
		return lblTipo;
	}

	private JPanel getPnTipoBoton() {
		if (pnTipoBoton == null) {
			pnTipoBoton = new JPanel();
			pnTipoBoton.setLayout(new BorderLayout(0, 0));
			pnTipoBoton.add(getBtnTipo(), BorderLayout.EAST);
		}
		return pnTipoBoton;
	}

	private JButton getBtnTipo() {
		if (btnTipo == null) {
			btnTipo = new JButton("Buscar");
			btnTipo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					rellenarMercancias(vp.getManager().filtrarPorTipoAlojamiento(chckbxHotel.isSelected(),
							chckbxApartahotel.isSelected(), chckbxApartamento.isSelected()));
				}
			});
			btnTipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return btnTipo;
	}

	private JCheckBox getChckbxHotel() {
		if (chckbxHotel == null) {
			chckbxHotel = new JCheckBox("Hotel");
			chckbxHotel.setSelected(true);
		}
		return chckbxHotel;
	}

	private JCheckBox getChckbxApartahotel() {
		if (chckbxApartahotel == null) {
			chckbxApartahotel = new JCheckBox("Apartahotel");
			chckbxApartahotel.setSelected(true);
		}
		return chckbxApartahotel;
	}

	private JCheckBox getChckbxApartamento() {
		if (chckbxApartamento == null) {
			chckbxApartamento = new JCheckBox("Apartamento");
			chckbxApartamento.setSelected(true);
		}
		return chckbxApartamento;
	}

	private JPanel getPnSubAlojamientos() {
		if (pnSubAlojamientos == null) {
			pnSubAlojamientos = new JPanel();
			pnSubAlojamientos.setLayout(new BorderLayout(0, 5));
			pnSubAlojamientos.add(getPnCategoria(), BorderLayout.NORTH);
		}
		return pnSubAlojamientos;
	}

	private JPanel getPnCategoria() {
		if (pnCategoria == null) {
			pnCategoria = new JPanel();
			pnCategoria.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
			pnCategoria.setLayout(new BorderLayout(0, 5));
			pnCategoria.add(getPnSubCategoria(), BorderLayout.NORTH);
			pnCategoria.add(getBtnCategoria(), BorderLayout.EAST);
		}
		return pnCategoria;
	}

	private JLabel getLblCategoria() {
		if (lblCategoria == null) {
			lblCategoria = new JLabel("Categoria minima");
			lblCategoria.setDisplayedMnemonic('G');
			lblCategoria.setLabelFor(getSpinCategoria());
		}
		return lblCategoria;
	}

	private JPanel getPnSubCategoria() {
		if (pnSubCategoria == null) {
			pnSubCategoria = new JPanel();
			pnSubCategoria.setLayout(new BorderLayout(0, 0));
			pnSubCategoria.add(getLblCategoria(), BorderLayout.WEST);
			pnSubCategoria.add(getSpinCategoria(), BorderLayout.EAST);
		}
		return pnSubCategoria;
	}

	private JSpinner getSpinCategoria() {
		if (spinCategoria == null) {
			spinCategoria = new JSpinner();
			spinCategoria.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		}
		return spinCategoria;
	}

	private JButton getBtnCategoria() {
		if (btnCategoria == null) {
			btnCategoria = new JButton("Buscar");
			btnCategoria.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					rellenarMercancias(vp.getManager().filtrarPorCategoriaAlojamiento((int) spinCategoria.getValue()));
				}
			});
			btnCategoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return btnCategoria;
	}

	// MIS METODOS \\

	private void rellenarMercancias(ArrayList<Alojamiento> array) {
		pnVistaMercancias.removeAll();
		PanelListasAlojamientos panelActual = this;
		for (Alojamiento aloja : array) {
			String infoExtra = "Numero de estrellas: " + aloja.getNumEstrellas() + ".\n\rPrecio por noche: "
					+ aloja.getPrecio() + "€.";
			if (aloja.getCodigoParque().equals(vp.getManager().getParqueOferta().getCodigoParque())) {
				infoExtra += "\n\r\n\rEl parque vinculado a este alojamiento esta en oferta.\n\r¡Compra ahora y llevate un descuento del 20%!";
			}
			PanelMercancias pnMercancia = new PanelMercancias(aloja, infoExtra);
			pnVistaMercancias.add(pnMercancia);
			pnMercancia.getBtVer().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					plg.mostrarPanelesVisores(new PanelVisorAlojamientos(panelActual, aloja));
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

	private void limitarSlider() {
		int[] precios = vp.getManager().getPreciosAlojamientos();
		sldNoche.setMinimum(precios[0]);
		sldNoche.setMaximum(precios[1]);
		sldNoche.setValue(precios[1]);
	}
}
