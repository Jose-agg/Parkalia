package igu.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.net.URL;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jvnet.substance.SubstanceLookAndFeel;

import logica.manager.Manager;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel mainPanelVP;
	private JPanel pnCabecera;
	private JPanel pnContents;
	private PanelSelector panelSelector;
	private JLabel lblCabecera;

	private Manager manager;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);
					SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.ModerateSkin");
					// SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.DustSkin");
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaPrincipal() {
		this.manager = new Manager();
		setTitle("Parkalia");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(VentanaPrincipal.class.getResource("/img/Logo_Transparente_Negro.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 800);
		mainPanelVP = new JPanel();
		mainPanelVP.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanelVP);
		mainPanelVP.setLayout(new BorderLayout(0, 0));
		mainPanelVP.add(getPnCabecera(), BorderLayout.NORTH);
		mainPanelVP.add(getPnContents(), BorderLayout.CENTER);
		cambiarPanelContenidos(getPanelSelector());
		cargaAyuda();
	}

	private JPanel getPnCabecera() {
		if (pnCabecera == null) {
			pnCabecera = new JPanel();
			pnCabecera.setLayout(new BorderLayout(0, 0));
			pnCabecera.add(getLblCabecera(), BorderLayout.CENTER);
		}
		return pnCabecera;
	}

	private JPanel getPnContents() {
		if (pnContents == null) {
			pnContents = new JPanel();
			pnContents.setLayout(new BorderLayout(0, 0));
		}
		return pnContents;
	}

	public JPanel getPanelSelector() {
		panelSelector = new PanelSelector(this);
		return panelSelector;
	}

	private JLabel getLblCabecera() {
		if (lblCabecera == null) {
			lblCabecera = new JLabel("");
			lblCabecera.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent arg0) {
					redimensionarImagen(lblCabecera, "/img/CabeceraReducida.jpg");
				}
			});
		}
		return lblCabecera;
	}

	// MIS METODOS \\

	/**
	 * Metodo que permite intercambiar entre paneles
	 */
	public void cambiarPanelContenidos(JPanel nuevoPanel) {
		pnContents.removeAll();
		pnContents.add(nuevoPanel, BorderLayout.CENTER);
		pnContents.updateUI();
	}

	private void redimensionarImagen(JLabel label, String ruta) {
		Image imgOriginal = new ImageIcon(getClass().getResource(ruta)).getImage();
		Image imgEscalada = imgOriginal.getScaledInstance((label.getWidth()), (180), Image.SCALE_FAST);
		label.setIcon(new ImageIcon(imgEscalada));
		label.setDisabledIcon(new ImageIcon(imgEscalada));
	}

	public Manager getManager() {
		return manager;
	}

	private void cargaAyuda() {

		URL hsURL;
		HelpSet hs;

		try {
			File fichero = new File("help/ayuda.hs");
			hsURL = fichero.toURI().toURL();
			hs = new HelpSet(null, hsURL);
		}

		catch (Exception e) {
			System.out.println("Ayuda no encontrada");
			return;
		}

		HelpBroker hb = hs.createHelpBroker();
		hb.initPresentation();
		hb.enableHelpKey(getRootPane(), "introduccion", hs);
		// hb.enableHelpOnButton(componente, "alias html introduccion", hs);
		// hb.enableHelp(componente, "alias html concreto", hs);
	}

}
