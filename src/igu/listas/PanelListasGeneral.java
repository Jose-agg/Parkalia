package igu.listas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import igu.carrito.PanelCarritoCliente;
import igu.main.VentanaPrincipal;

public class PanelListasGeneral extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel pnTipos;
	private JButton btEntradas;
	private JButton btPaquetes;
	private JButton btAlojamientos;
	private JPanel pnContenidos;
	private JPanel pnCarrito;
	private JScrollPane scrpnCarrito;
	private JTextArea txaCarrito;
	private JPanel pnCarritoBotones;
	private JButton btVerCarrito;
	private JButton btLimpiarCarrito;
	private JPanel pnContenidosFiltros;
	private JPanel pnContenidosLista;

	private VentanaPrincipal vp;

	private PanelListasEntradas pnListasEntradas;
	private PanelListasPaquetes pnListasPaquetes;
	private PanelListasAlojamientos pnListasAlojamientos;

	public PanelListasGeneral(VentanaPrincipal vp, String s) {
		this.vp = vp;
		setLayout(new BorderLayout(0, 0));
		add(getPnTipos(), BorderLayout.NORTH);
		add(getPnContenidos(), BorderLayout.CENTER);
		add(getPnCarrito(), BorderLayout.EAST);
		cambiarEntreTiposActivo(s);
	}

	private PanelListasEntradas getPnListasEntradas() {
		if (pnListasEntradas == null) {
			pnListasEntradas = new PanelListasEntradas(this);
		}
		return pnListasEntradas;
	}

	private PanelListasPaquetes getPnListasPaquetes() {
		if (pnListasPaquetes == null) {
			pnListasPaquetes = new PanelListasPaquetes(this);
		}
		return pnListasPaquetes;
	}

	private PanelListasAlojamientos getPnListasAlojamientos() {
		if (pnListasAlojamientos == null) {
			pnListasAlojamientos = new PanelListasAlojamientos(this);
		}
		return pnListasAlojamientos;
	}

	private JPanel getPnTipos() {
		if (pnTipos == null) {
			pnTipos = new JPanel();
			pnTipos.setLayout(new GridLayout(1, 0, 0, 0));
			pnTipos.add(getBtPaquetes());
			pnTipos.add(getBtAlojamientos());
			pnTipos.add(getBtEntradas());
		}
		return pnTipos;
	}

	private JButton getBtEntradas() {
		if (btEntradas == null) {
			btEntradas = new JButton("Entradas");
			btEntradas.setMnemonic('E');
			btEntradas.setFont(new Font("Gadugi", Font.PLAIN, 16));
			btEntradas.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					cambiarEntreTiposActivo("Entradas");
				}
			});
		}
		return btEntradas;
	}

	private JButton getBtPaquetes() {
		if (btPaquetes == null) {
			btPaquetes = new JButton("Paquetes");
			btPaquetes.setMnemonic('P');
			btPaquetes.setFont(new Font("Gadugi", Font.PLAIN, 16));
			btPaquetes.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					cambiarEntreTiposActivo("Paquetes");
				}
			});
		}
		return btPaquetes;
	}

	private JButton getBtAlojamientos() {
		if (btAlojamientos == null) {
			btAlojamientos = new JButton("Alojamientos");
			btAlojamientos.setMnemonic('A');
			btAlojamientos.setFont(new Font("Gadugi", Font.PLAIN, 16));
			btAlojamientos.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					cambiarEntreTiposActivo("Alojamientos");
				}
			});
		}
		return btAlojamientos;
	}

	private JPanel getPnContenidos() {
		if (pnContenidos == null) {
			pnContenidos = new JPanel();
			pnContenidos.setLayout(new BorderLayout(5, 0));
			pnContenidos.add(getPnContenidosFiltros(), BorderLayout.WEST);
			pnContenidos.add(getPnContenidosLista(), BorderLayout.CENTER);
		}
		return pnContenidos;
	}

	private JPanel getPnCarrito() {
		if (pnCarrito == null) {
			pnCarrito = new JPanel();
			pnCarrito.setLayout(new BorderLayout(0, 0));
			pnCarrito.add(getScrpnCarrito(), BorderLayout.CENTER);
			pnCarrito.add(getPnCarritoBotones(), BorderLayout.SOUTH);
		}
		return pnCarrito;
	}

	private JScrollPane getScrpnCarrito() {
		if (scrpnCarrito == null) {
			scrpnCarrito = new JScrollPane();
			scrpnCarrito.setViewportView(getTxaCarrito());
		}
		return scrpnCarrito;
	}

	private JTextArea getTxaCarrito() {
		if (txaCarrito == null) {
			txaCarrito = new JTextArea();
			txaCarrito.setDisabledTextColor(Color.BLACK);
			txaCarrito.setWrapStyleWord(true);
			txaCarrito.setLineWrap(true);
			txaCarrito.setEnabled(false);
			txaCarrito.setEditable(false);
		}
		return txaCarrito;
	}

	private JPanel getPnCarritoBotones() {
		if (pnCarritoBotones == null) {
			pnCarritoBotones = new JPanel();
			pnCarritoBotones.setLayout(new GridLayout(1, 0, 0, 0));
			pnCarritoBotones.add(getBtVerCarrito());
			pnCarritoBotones.add(getBtLimpiarCarrito());
		}
		return pnCarritoBotones;
	}

	private JButton getBtVerCarrito() {
		if (btVerCarrito == null) {
			btVerCarrito = new JButton("Carrito");
			btVerCarrito.setMnemonic('R');
			btVerCarrito.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					PanelListasGeneral PLG = getEstadoActualPnlg();
					vp.cambiarPanelContenidos(new PanelCarritoCliente(vp, PLG));
				}
			});
		}
		return btVerCarrito;
	}

	private JButton getBtLimpiarCarrito() {
		if (btLimpiarCarrito == null) {
			btLimpiarCarrito = new JButton("Limpiar");
			btLimpiarCarrito.setMnemonic('L');
			btLimpiarCarrito.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int respuesta = JOptionPane.showConfirmDialog(null, "Se borraran todas las reservas. ¿Continuar?");
					if (JOptionPane.YES_OPTION == respuesta) {
						vp.getManager().limpiarReserva();
						actualizarCarrito();
					}
				}
			});
		}
		return btLimpiarCarrito;
	}

	private JPanel getPnContenidosFiltros() {
		if (pnContenidosFiltros == null) {
			pnContenidosFiltros = new JPanel();
		}
		return pnContenidosFiltros;
	}

	private JPanel getPnContenidosLista() {
		if (pnContenidosLista == null) {
			pnContenidosLista = new JPanel();
		}
		return pnContenidosLista;
	}

	// MIS METODOS \\

	private void cambiarEntreTiposActivo(String s) {
		btEntradas.setBackground(new Color(240, 240, 240));
		btPaquetes.setBackground(new Color(240, 240, 240));
		btAlojamientos.setBackground(new Color(240, 240, 240));
		btEntradas.setFont(new Font("Gadugi", Font.PLAIN, 16));
		btPaquetes.setFont(new Font("Gadugi", Font.PLAIN, 16));
		btAlojamientos.setFont(new Font("Gadugi", Font.PLAIN, 16));
		switch (s) {
		case "Entradas":
			btEntradas.setBackground(new Color(0, 0, 255));
			btEntradas.setFont(new Font("Gadugi", Font.BOLD, 16));
			break;
		case "Paquetes":
			btPaquetes.setBackground(new Color(255, 0, 0));
			btPaquetes.setFont(new Font("Gadugi", Font.BOLD, 16));
			break;
		case "Alojamientos":
			btAlojamientos.setBackground(new Color(0, 153, 0));
			btAlojamientos.setFont(new Font("Gadugi", Font.BOLD, 16));
			break;
		default:
			btEntradas.setBackground(new Color(0, 0, 0));
			btPaquetes.setBackground(new Color(0, 0, 0));
			btAlojamientos.setBackground(new Color(0, 0, 0));
			break;
		}
		actualizarContenidos(s);
	}

	private void actualizarContenidos(String s) {
		pnContenidos.removeAll();
		switch (s) {
		case "Entradas":
			pnContenidos.add(getPnListasEntradas(), BorderLayout.CENTER);
			getPnListasEntradas().limpiarCampos();
			break;
		case "Paquetes":
			pnContenidos.add(getPnListasPaquetes(), BorderLayout.CENTER);
			getPnListasPaquetes().limpiarCampos();
			break;
		case "Alojamientos":
			pnContenidos.add(getPnListasAlojamientos(), BorderLayout.CENTER);
			getPnListasAlojamientos().limpiarCampos();
			break;
		default:
			break;
		}
		pnContenidos.updateUI();
	}

	public void mostrarPanelesVisores(JPanel panel) {
		pnContenidos.removeAll();
		pnContenidos.add(panel);
		pnContenidos.updateUI();
	}

	public VentanaPrincipal getVP() {
		return vp;
	}

	private PanelListasGeneral getEstadoActualPnlg() {
		return this;
	}

	public void actualizarCarrito() {
		txaCarrito.setText(vp.getManager().getStringMercanciaCarrito());
	}
}
