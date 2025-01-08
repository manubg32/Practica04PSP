package view;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Font;

public class PnlVDetalle extends JPanel {
	
	private JTextField txtNota;
	
	private JLabel lblAsignatura;
	
	private JPanel pnlDetalles;
	private JPanel pnlBotones;
	private JButton btnPrimero;
	private JButton btnUltimo;
	
	public PnlVDetalle() {
		setLayout(new BorderLayout(0, 0));
		
		//Inicializamos los componentes
		initComponents();
		
		//Damos funcionalidad
		addListeners();
	}

	private void addListeners() {
		
	}

	private void initComponents() {
		pnlDetalles = new JPanel();
		add(pnlDetalles, BorderLayout.CENTER);
		pnlDetalles.setLayout(new GridLayout(0, 1, 5, 10));
		
		lblAsignatura = new JLabel("Matemáticas");
		lblAsignatura.setHorizontalAlignment(SwingConstants.CENTER);
		lblAsignatura.setFont(new Font("Tahoma", Font.PLAIN, 50));
		pnlDetalles.add(lblAsignatura);
		
		txtNota = new JTextField();
		txtNota.setHorizontalAlignment(SwingConstants.CENTER);
		txtNota.setFont(new Font("Tahoma", Font.PLAIN, 40));
		txtNota.setToolTipText("Introduzca una nueva nota");
		txtNota.setText("7");
		pnlDetalles.add(txtNota);
		txtNota.setColumns(10);
		
		pnlBotones = new JPanel();
		add(pnlBotones, BorderLayout.SOUTH);
		pnlBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnPrimero = new JButton("Primero");
		pnlBotones.add(btnPrimero);
		
		JButton btnAnterior = new JButton("Anterior");
		pnlBotones.add(btnAnterior);
		
		JButton btnGuardar = new JButton("Guardar");
		pnlBotones.add(btnGuardar);
		
		JButton btnSiguiente = new JButton("Siguiente");
		pnlBotones.add(btnSiguiente);
		
		btnUltimo = new JButton("Último");
		pnlBotones.add(btnUltimo);
	}
}
