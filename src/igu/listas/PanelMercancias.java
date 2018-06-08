package igu.listas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import logica.tipos.Mercancia;

public class PanelMercancias extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel pnImagen;
	private JLabel lblImagen;
	private JLabel lblDenominacion;
	private JScrollPane scrpnInfoExtra;
	private JTextArea txaInfoExtra;
	private JPanel pnBoton;
	private JButton btVer;

	private Mercancia mercancia;
	private String informacionExtra;

	public PanelMercancias(Mercancia mercancia, String informacionExtra) {
		this.mercancia = mercancia;
		this.informacionExtra = informacionExtra;
		setBorder(null);
		setLayout(new BorderLayout(5, 5));
		add(getPnImagen(), BorderLayout.WEST);
		add(getScrpnInfoExtra(), BorderLayout.CENTER);
		add(getPnBoton(), BorderLayout.SOUTH);
		add(getLblDenominacion(), BorderLayout.NORTH);
		rellenarDatos();
	}

	private JPanel getPnImagen() {
		if (pnImagen == null) {
			pnImagen = new JPanel();
			pnImagen.setLayout(new BorderLayout(5, 5));
			pnImagen.add(getLblImagen(), BorderLayout.CENTER);
		}
		return pnImagen;
	}

	private JLabel getLblImagen() {
		if (lblImagen == null) {
			lblImagen = new JLabel();
			lblImagen.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent arg0) {
					redimensionarImagen();
				}
			});
		}
		return lblImagen;
	}

	private JLabel getLblDenominacion() {
		if (lblDenominacion == null) {
			lblDenominacion = new JLabel();
		}
		return lblDenominacion;
	}

	private JScrollPane getScrpnInfoExtra() {
		if (scrpnInfoExtra == null) {
			scrpnInfoExtra = new JScrollPane();
			scrpnInfoExtra.setViewportView(getTxaInfoExtra());
		}
		return scrpnInfoExtra;
	}

	private JTextArea getTxaInfoExtra() {
		if (txaInfoExtra == null) {
			txaInfoExtra = new JTextArea();
			txaInfoExtra.setDisabledTextColor(Color.BLACK);
			txaInfoExtra.setBackground(new Color(240, 240, 240));
			txaInfoExtra.setWrapStyleWord(true);
			txaInfoExtra.setLineWrap(true);
			txaInfoExtra.setEnabled(false);
			txaInfoExtra.setEditable(false);
		}
		return txaInfoExtra;
	}

	private JPanel getPnBoton() {
		if (pnBoton == null) {
			pnBoton = new JPanel();
			pnBoton.setLayout(new BorderLayout(0, 0));
			pnBoton.add(getBtVer(), BorderLayout.EAST);
		}
		return pnBoton;
	}

	// El actionPerformance esta en los paneles listas porque aqui no sabes que
	// objeto le corresponde
	public JButton getBtVer() {
		if (btVer == null) {
			btVer = new JButton("Ver");
			btVer.setFont(new Font("Calibri", Font.BOLD, 14));
		}
		return btVer;
	}

	// MIS METODOS \\

	private void rellenarDatos() {
		lblDenominacion.setText(mercancia.getDenominacion());
		txaInfoExtra.setText(informacionExtra);
		redimensionarImagen();
	}

	private void redimensionarImagen() {
		Image imgOriginal = mercancia.getImagen();
		Image imgEscalada = imgOriginal.getScaledInstance((120), (120), Image.SCALE_FAST);
		lblImagen.setIcon(new ImageIcon(imgEscalada));
		lblImagen.setDisabledIcon(new ImageIcon(imgEscalada));
	}
}
