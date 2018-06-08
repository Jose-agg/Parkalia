package igu.carrito;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import igu.listas.PanelListasGeneral;
import igu.main.VentanaPrincipal;

public class PanelCarritoCliente extends JPanel {
	private static final long serialVersionUID = 1L;

	private JPanel pnCarrito;
	private JPanel pnCliente;
	private JPanel pnDatos;
	private JPanel pnBotones;
	private JPanel pnSubBotones;
	private JButton btnReservar;
	private JButton btnBorrarTodo;
	private JButton btnAtras;
	private JPanel pnAtras;
	private JScrollPane scrpnListaCarrito;
	private JPanel pnCarritoBotones;
	private JPanel pnSubCarritoBotones;
	private JButton btnModificar;
	private JButton btnBorrar;
	private JList<String> lstCarrito;
	private DefaultListModel<String> modeloListCarrito;
	private JPanel pnNombreCliente;
	private JPanel pnSubNombreCliente;
	private JLabel lblNombreCliente;
	private JTextField txtNombreCliente;
	private JPanel pnDatosCliente;
	private JPanel pnApellidosCliente;
	private JPanel pnSubApellidosCliente;
	private JLabel lblApellidosCliente;
	private JTextField txtApellidosCliente;
	private JPanel pnSubDatosCliente;
	private JPanel pnNIF;
	private JPanel pnSubNIF;
	private JLabel lblNIFCliente;
	private JTextField txtNifCliente;
	private JPanel pnSubPrecios;
	private JLabel lblPrecios;
	private JTextField txtPrecios;
	private JPanel pnInformacionExtra;
	private JScrollPane scrpnSubComentario;
	private JTextArea txaComentarios;
	private JPanel pnComentario;
	private JLabel lblComentario;
	private JButton btnLimpiar;

	private VentanaPrincipal vp;
	private PanelListasGeneral panelPrevio;

	/**
	 * Create the panel.
	 */
	public PanelCarritoCliente(VentanaPrincipal vp, PanelListasGeneral panelPrevio) {
		this.vp = vp;
		this.panelPrevio = panelPrevio;
		setLayout(new BorderLayout(0, 0));
		add(getPnDatos(), BorderLayout.CENTER);
		add(getPnBotones(), BorderLayout.SOUTH);
		refrescarCarritoArticulos();
	}

	private JPanel getPnCarrito() {
		if (pnCarrito == null) {
			pnCarrito = new JPanel();
			pnCarrito.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Resumen de compra",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnCarrito.setLayout(new BorderLayout(0, 0));
			pnCarrito.add(getScrpnListaCarrito());
			pnCarrito.add(getPnCarritoBotones(), BorderLayout.SOUTH);
		}
		return pnCarrito;
	}

	private JPanel getPnCliente() {
		if (pnCliente == null) {
			pnCliente = new JPanel();
			pnCliente.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Datos del cliente",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnCliente.setLayout(new BorderLayout(0, 5));
			pnCliente.add(getPnNombreCliente(), BorderLayout.NORTH);
			pnCliente.add(getPnDatosCliente(), BorderLayout.CENTER);
		}
		return pnCliente;
	}

	private JPanel getPnDatos() {
		if (pnDatos == null) {
			pnDatos = new JPanel();
			pnDatos.setLayout(new GridLayout(0, 2, 0, 0));
			pnDatos.add(getPnCarrito());
			pnDatos.add(getPnCliente());
		}
		return pnDatos;
	}

	private JPanel getPnBotones() {
		if (pnBotones == null) {
			pnBotones = new JPanel();
			pnBotones.setLayout(new BorderLayout(0, 0));
			pnBotones.add(getPnSubBotones(), BorderLayout.EAST);
			pnBotones.add(getPnAtras(), BorderLayout.WEST);
		}
		return pnBotones;
	}

	private JPanel getPnSubBotones() {
		if (pnSubBotones == null) {
			pnSubBotones = new JPanel();
			pnSubBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnSubBotones.add(getBtnReservar());
			pnSubBotones.add(getBtnBorrarTodo());
		}
		return pnSubBotones;
	}

	private JButton getBtnReservar() {
		if (btnReservar == null) {
			btnReservar = new JButton("Reservar");
			btnReservar.setMnemonic('R');
			btnReservar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					comprobacionesCarrito();
				}
			});
		}
		return btnReservar;
	}

	private JButton getBtnBorrarTodo() {
		if (btnBorrarTodo == null) {
			btnBorrarTodo = new JButton("Borrar todo");
			btnBorrarTodo.setMnemonic('T');
			btnBorrarTodo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int respuesta = JOptionPane.showConfirmDialog(null,
							"Se borraran  todos los datos guardados. ¿Continuar?");
					if (JOptionPane.YES_OPTION == respuesta) {
						vp.getManager().reiniciar();
						vp.cambiarPanelContenidos(vp.getPanelSelector());
					}
				}
			});
		}
		return btnBorrarTodo;
	}

	private JButton getBtnAtras() {
		if (btnAtras == null) {
			btnAtras = new JButton("Atras");
			btnAtras.setMnemonic('A');
			btnAtras.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					vp.cambiarPanelContenidos(panelPrevio);
				}
			});
		}
		return btnAtras;
	}

	private JPanel getPnAtras() {
		if (pnAtras == null) {
			pnAtras = new JPanel();
			pnAtras.add(getBtnAtras());
		}
		return pnAtras;
	}

	private JScrollPane getScrpnListaCarrito() {
		if (scrpnListaCarrito == null) {
			scrpnListaCarrito = new JScrollPane();
			scrpnListaCarrito.setViewportView(getLstCarrito());
		}
		return scrpnListaCarrito;
	}

	private JPanel getPnCarritoBotones() {
		if (pnCarritoBotones == null) {
			pnCarritoBotones = new JPanel();
			pnCarritoBotones.setLayout(new BorderLayout(0, 0));
			pnCarritoBotones.add(getPnSubPrecios(), BorderLayout.WEST);
			pnCarritoBotones.add(getPnSubCarritoBotones(), BorderLayout.EAST);
		}
		return pnCarritoBotones;
	}

	private JPanel getPnSubPrecios() {
		if (pnSubPrecios == null) {
			pnSubPrecios = new JPanel();
			pnSubPrecios.add(getLblPrecios());
			pnSubPrecios.add(getTxtPrecios());
		}
		return pnSubPrecios;
	}

	private JLabel getLblPrecios() {
		if (lblPrecios == null) {
			lblPrecios = new JLabel("Total (sin descuentos)");
		}
		return lblPrecios;
	}

	private JTextField getTxtPrecios() {
		if (txtPrecios == null) {
			txtPrecios = new JTextField();
			txtPrecios.setHorizontalAlignment(SwingConstants.RIGHT);
			txtPrecios.setEnabled(false);
			txtPrecios.setEditable(false);
			txtPrecios.setColumns(6);
		}
		return txtPrecios;
	}

	private JPanel getPnSubCarritoBotones() {
		if (pnSubCarritoBotones == null) {
			pnSubCarritoBotones = new JPanel();
			pnSubCarritoBotones.add(getBtnModificar());
			pnSubCarritoBotones.add(getBtnBorrar());
			pnSubCarritoBotones.add(getBtnLimpiar());
		}
		return pnSubCarritoBotones;
	}

	private JButton getBtnModificar() {
		if (btnModificar == null) {
			btnModificar = new JButton("Modificar");
			btnModificar.setMnemonic('M');
			btnModificar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String seleccionado = getLstCarrito().getSelectedValue();
					modificarEntradaReserva(seleccionado);
				}
			});
		}
		return btnModificar;
	}

	private JButton getBtnBorrar() {
		if (btnBorrar == null) {
			btnBorrar = new JButton("Borrar");
			btnBorrar.setMnemonic('B');
			btnBorrar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String seleccionado = getLstCarrito().getSelectedValue();
					borrarEntradaReserva(seleccionado);
				}
			});
		}
		return btnBorrar;
	}

	private JButton getBtnLimpiar() {
		if (btnLimpiar == null) {
			btnLimpiar = new JButton("Limpiar");
			btnLimpiar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int respuesta = JOptionPane.showConfirmDialog(null, "Se borraran todas las reservas. ¿Continuar?");
					if (JOptionPane.YES_OPTION == respuesta) {
						vp.getManager().limpiarReserva();
						refrescarCarritoArticulos();
						panelPrevio.actualizarCarrito();
					}
				}
			});
		}
		return btnLimpiar;
	}

	private JList<String> getLstCarrito() {
		if (lstCarrito == null) {
			modeloListCarrito = new DefaultListModel<String>();
			lstCarrito = new JList<String>(modeloListCarrito);
			lstCarrito.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return lstCarrito;
	}

	private JPanel getPnNombreCliente() {
		if (pnNombreCliente == null) {
			pnNombreCliente = new JPanel();
			pnNombreCliente.setLayout(new BorderLayout(0, 5));
			pnNombreCliente.add(getPnSubNombreCliente(), BorderLayout.WEST);
			pnNombreCliente.add(getTxtNombreCliente(), BorderLayout.EAST);
		}
		return pnNombreCliente;
	}

	private JPanel getPnSubNombreCliente() {
		if (pnSubNombreCliente == null) {
			pnSubNombreCliente = new JPanel();
			pnSubNombreCliente.add(getLblNombreCliente());
		}
		return pnSubNombreCliente;
	}

	private JLabel getLblNombreCliente() {
		if (lblNombreCliente == null) {
			lblNombreCliente = new JLabel("Nombre");
			lblNombreCliente.setDisplayedMnemonic('N');
			lblNombreCliente.setLabelFor(getTxtNombreCliente());
		}
		return lblNombreCliente;
	}

	private JTextField getTxtNombreCliente() {
		if (txtNombreCliente == null) {
			txtNombreCliente = new JTextField();
			txtNombreCliente.setColumns(25);
		}
		return txtNombreCliente;
	}

	private JPanel getPnDatosCliente() {
		if (pnDatosCliente == null) {
			pnDatosCliente = new JPanel();
			pnDatosCliente.setLayout(new BorderLayout(0, 5));
			pnDatosCliente.add(getPnApellidosCliente(), BorderLayout.NORTH);
			pnDatosCliente.add(getPnSubDatosCliente(), BorderLayout.CENTER);
		}
		return pnDatosCliente;
	}

	private JPanel getPnApellidosCliente() {
		if (pnApellidosCliente == null) {
			pnApellidosCliente = new JPanel();
			pnApellidosCliente.setLayout(new BorderLayout(0, 0));
			pnApellidosCliente.add(getPnSubApellidosCliente(), BorderLayout.WEST);
			pnApellidosCliente.add(getTxtApellidosCliente(), BorderLayout.EAST);
		}
		return pnApellidosCliente;
	}

	private JPanel getPnSubApellidosCliente() {
		if (pnSubApellidosCliente == null) {
			pnSubApellidosCliente = new JPanel();
			pnSubApellidosCliente.add(getLblApellidosCliente());
		}
		return pnSubApellidosCliente;
	}

	private JLabel getLblApellidosCliente() {
		if (lblApellidosCliente == null) {
			lblApellidosCliente = new JLabel("Apellidos");
			lblApellidosCliente.setDisplayedMnemonic('P');
			lblApellidosCliente.setLabelFor(getTxtApellidosCliente());
		}
		return lblApellidosCliente;
	}

	private JTextField getTxtApellidosCliente() {
		if (txtApellidosCliente == null) {
			txtApellidosCliente = new JTextField();
			txtApellidosCliente.setColumns(25);
		}
		return txtApellidosCliente;
	}

	private JPanel getPnSubDatosCliente() {
		if (pnSubDatosCliente == null) {
			pnSubDatosCliente = new JPanel();
			pnSubDatosCliente.setLayout(new BorderLayout(0, 5));
			pnSubDatosCliente.add(getPnNIF(), BorderLayout.NORTH);
			pnSubDatosCliente.add(getPnInformacionExtra(), BorderLayout.CENTER);
		}
		return pnSubDatosCliente;
	}

	private JPanel getPnNIF() {
		if (pnNIF == null) {
			pnNIF = new JPanel();
			pnNIF.setLayout(new BorderLayout(0, 0));
			pnNIF.add(getPnSubNIF(), BorderLayout.WEST);
			pnNIF.add(getTxtNifCliente(), BorderLayout.EAST);
		}
		return pnNIF;
	}

	private JPanel getPnSubNIF() {
		if (pnSubNIF == null) {
			pnSubNIF = new JPanel();
			pnSubNIF.add(getLblNIFCliente());
		}
		return pnSubNIF;
	}

	private JLabel getLblNIFCliente() {
		if (lblNIFCliente == null) {
			lblNIFCliente = new JLabel("NIF");
			lblNIFCliente.setDisplayedMnemonic('I');
			lblNIFCliente.setLabelFor(getTxtNifCliente());
		}
		return lblNIFCliente;
	}

	private JTextField getTxtNifCliente() {
		if (txtNifCliente == null) {
			txtNifCliente = new JTextField();
			txtNifCliente.setColumns(11);
		}
		return txtNifCliente;
	}

	private JPanel getPnInformacionExtra() {
		if (pnInformacionExtra == null) {
			pnInformacionExtra = new JPanel();
			pnInformacionExtra.setLayout(new BorderLayout(0, 0));
			pnInformacionExtra.add(getPnComentario(), BorderLayout.NORTH);
		}
		return pnInformacionExtra;
	}

	private JScrollPane getScrpnSubComentario() {
		if (scrpnSubComentario == null) {
			scrpnSubComentario = new JScrollPane();
			scrpnSubComentario.setViewportView(getTxaComentarios());
		}
		return scrpnSubComentario;
	}

	private JTextArea getTxaComentarios() {
		if (txaComentarios == null) {
			txaComentarios = new JTextArea();
			txaComentarios.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					if (txaComentarios.getText().contains("...")) {
						txaComentarios.setText("");
					}
				}
			});
			txaComentarios.setRows(6);
			txaComentarios.setWrapStyleWord(true);
			txaComentarios.setLineWrap(true);
			txaComentarios.setText("A\u00F1ada aqu\u00ED cualquier comentario sobre la reserva ...");
		}
		return txaComentarios;
	}

	private JPanel getPnComentario() {
		if (pnComentario == null) {
			pnComentario = new JPanel();
			pnComentario.setLayout(new BorderLayout(0, 0));
			pnComentario.add(getLblComentario(), BorderLayout.NORTH);
			pnComentario.add(getScrpnSubComentario());
		}
		return pnComentario;
	}

	private JLabel getLblComentario() {
		if (lblComentario == null) {
			lblComentario = new JLabel("Comentario");
			lblComentario.setDisplayedMnemonic('C');
			lblComentario.setLabelFor(getTxaComentarios());
		}
		return lblComentario;
	}

	// MIS METODOS \\

	private void modificarEntradaReserva(String seleccionado) {
		if (seleccionado != null) {
			if (seleccionado.contains("-<")) {
				JOptionPane.showMessageDialog(null,
						"Esta seleccionando para modificar una leyenda, por favor seleccione una reserva valida");
			} else {
				if (seleccionado.contains("Entrada ")) {
					mostrarVentanaCambiosEntrada(seleccionado, "Modificar");
					refrescarCarritoArticulos();
					panelPrevio.actualizarCarrito();
				}
				if (seleccionado.contains("Alojamiento:")) {
					mostrarVentanaCambiosAlojamiento(seleccionado, "Modificar");
					refrescarCarritoArticulos();
					panelPrevio.actualizarCarrito();
				}
				if (seleccionado.contains("Paquete:")) {
					mostrarVentanaCambiosPaquete(seleccionado, "Modificar");
					refrescarCarritoArticulos();
					panelPrevio.actualizarCarrito();
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Seleccione una reserva");
		}
	}

	private void borrarEntradaReserva(String seleccionado) {
		if (seleccionado != null) {
			if (seleccionado.contains("-<")) {
				JOptionPane.showMessageDialog(null,
						"Esta seleccionando para borrar una leyenda, por favor seleccione una reserva valida");
			} else {
				if (seleccionado.contains("Entrada ")) {
					mostrarVentanaCambiosEntrada(seleccionado, "Borrar");
					refrescarCarritoArticulos();
					panelPrevio.actualizarCarrito();
				}
				if (seleccionado.contains("Alojamiento:")) {
					mostrarVentanaCambiosAlojamiento(seleccionado, "Borrar");
					refrescarCarritoArticulos();
					panelPrevio.actualizarCarrito();
				}
				if (seleccionado.contains("Paquete:")) {
					mostrarVentanaCambiosPaquete(seleccionado, "Borrar");
					refrescarCarritoArticulos();
					panelPrevio.actualizarCarrito();
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Seleccione una reserva");
		}
	}

	private void refrescarCarritoArticulos() {
		modeloListCarrito.clear();
		modeloListCarrito = this.vp.getManager().refrescarCarrito(modeloListCarrito);
		txtPrecios.setText(this.vp.getManager().actualizarPrecioCarrito() + " €");
	}

	private void mostrarVentanaCambiosEntrada(String datos, String tipoAccion) {
		VentanaCambiosEntrada vce = new VentanaCambiosEntrada(vp, datos, tipoAccion);
		vce.setLocationRelativeTo(null);
		vce.setModalityType(ModalityType.APPLICATION_MODAL);
		vce.setVisible(true);
	}

	private void mostrarVentanaCambiosAlojamiento(String datos, String tipoAccion) {
		VentanaCambiosAlojamiento vca = new VentanaCambiosAlojamiento(vp, datos, tipoAccion);
		vca.setLocationRelativeTo(null);
		vca.setModalityType(ModalityType.APPLICATION_MODAL);
		vca.setVisible(true);
	}

	private void mostrarVentanaCambiosPaquete(String datos, String tipoAccion) {
		VentanaCambiosPaquete vce = new VentanaCambiosPaquete(vp, datos, tipoAccion);
		vce.setLocationRelativeTo(null);
		vce.setModalityType(ModalityType.APPLICATION_MODAL);
		vce.setVisible(true);
	}

	private void comprobacionesCarrito() {
		if (vp.getManager().reservaIsEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay ninguna articulo para reservar");
			return;
		}
		if (txtNombreCliente.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Es obligatorio rellenar el campo del nombre del cliente");
			return;
		}
		if (txtApellidosCliente.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Es obligatorio rellenar el campo de los apellidos del cliente");
			return;
		}
		if (txtNifCliente.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Es obligatorio rellenar el campo del NIF del cliente");
			return;
		}

		this.vp.getManager().crearCliente(txtNombreCliente.getText(), txtApellidosCliente.getText(),
				txaComentarios.getText());
		String validezNif = this.vp.getManager().introducirNif(txtNifCliente.getText());

		if (!validezNif.equals("Valido")) {
			JOptionPane.showMessageDialog(null, validezNif);
			return;
		}
		mostrarVentanaResumenReserva();
	}

	private void mostrarVentanaResumenReserva() {
		VentanaResumenReserva vce = new VentanaResumenReserva(vp);
		vce.setLocationRelativeTo(null);
		vce.setModalityType(ModalityType.APPLICATION_MODAL);
		vce.setVisible(true);
	}
}
