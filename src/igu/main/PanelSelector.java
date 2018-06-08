package igu.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import igu.listas.PanelListasGeneral;

public class PanelSelector extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btEntradas;
	private JButton btPaquetes;
	private JButton btAlojamientos;
	private VentanaPrincipal vp;

	/**
	 * Create the panel.
	 */
	public PanelSelector(VentanaPrincipal vp) {
		this.vp = vp;
		setLayout(new GridLayout(1, 0, 0, 0));
		add(getBtPaquetes());
		add(getBtAlojamientos());
		add(getBtEntradas());

	}

	private JButton getBtEntradas() {
		if (btEntradas == null) {
			btEntradas = new JButton("Entradas");
			btEntradas.setMnemonic('E');
			btEntradas.setFont(new Font("Gadugi", Font.PLAIN, 24));
			btEntradas.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					vp.cambiarPanelContenidos(new PanelListasGeneral(vp, "Entradas"));
				}
			});
			btEntradas.setForeground(new Color(0, 0, 0));
			btEntradas.setBackground(new Color(0, 0, 255));
		}
		return btEntradas;
	}

	private JButton getBtPaquetes() {
		if (btPaquetes == null) {
			btPaquetes = new JButton("Paquetes");
			btPaquetes.setMnemonic('P');
			btPaquetes.setFont(new Font("Gadugi", Font.PLAIN, 24));
			btPaquetes.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					vp.cambiarPanelContenidos(new PanelListasGeneral(vp, "Paquetes"));
				}
			});
			btPaquetes.setBackground(new Color(255, 0, 0));
		}
		return btPaquetes;
	}

	private JButton getBtAlojamientos() {
		if (btAlojamientos == null) {
			btAlojamientos = new JButton("Alojamientos");
			btAlojamientos.setMnemonic('A');
			btAlojamientos.setFont(new Font("Gadugi", Font.PLAIN, 24));
			btAlojamientos.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					vp.cambiarPanelContenidos(new PanelListasGeneral(vp, "Alojamientos"));
				}
			});
			btAlojamientos.setBackground(new Color(0, 153, 0));
		}
		return btAlojamientos;
	}
}
