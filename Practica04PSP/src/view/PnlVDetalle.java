package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.CtrlVDetalle;

public class PnlVDetalle extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nota;

	private CtrlVDetalle cd = new CtrlVDetalle();
	
	public static JTextField txtNota;
	
	public static JLabel lblAsignatura;
	
	private JPanel pnlDetalles;
	private JPanel pnlBotones;
	private JButton btnPrimero;
	private JButton btnUltimo;
	private JButton btnSiguiente;
	private JButton btnAnterior;
	private JButton btnGuardar;
	
	public PnlVDetalle() throws SQLException {
		setLayout(new BorderLayout(0, 0));
		
		//Inicializamos los componentes
		initComponents();
		
		//Damos funcionalidad
		addListeners();
		
		//Mostramos el primer elemento
		cd.mostrarPrimero();
		nota = txtNota.getText();
	}

	

	private void addListeners() {
		
		//Si hay un cambio en el txtNota activamos el btnGuardar
		txtNota.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {}

			@Override
			public void removeUpdate(DocumentEvent e) {
				btnGuardar.setEnabled(true);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {}});
		
		//Damos funcionalidad al btnPrimero
		btnPrimero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cd.mostrarPrimero();
				cd.comprobarBotonesVista(btnPrimero, btnAnterior, btnSiguiente, btnUltimo);
				nota = txtNota.getText();
			}
		});
		
		//Damos funcionalidad al btnUltimo
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cd.mostrarUltimo();
				cd.comprobarBotonesVista(btnPrimero, btnAnterior, btnSiguiente, btnUltimo);
				nota = txtNota.getText();
			}
		});
		
		//Damos funcionalidad al btnSiguiente
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cd.mostrarSiguiente();
				cd.comprobarBotonesVista(btnPrimero, btnAnterior, btnSiguiente, btnUltimo);
				nota = txtNota.getText();
			}
		});
		
		//Damos funcionalidad al btnAnterior
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cd.mostrarAnterior();
				cd.comprobarBotonesVista(btnPrimero, btnAnterior, btnSiguiente, btnUltimo);
				nota = txtNota.getText();
			}
		});
		
		//Damos funcionalidad al btnGuardar
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cd.guardarNota(nota, txtNota);
			}
		});
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
		
		btnAnterior = new JButton("Anterior");
		pnlBotones.add(btnAnterior);
		
		btnGuardar = new JButton("Guardar");
		pnlBotones.add(btnGuardar);
		btnGuardar.setEnabled(false);
		
		btnSiguiente = new JButton("Siguiente");
		pnlBotones.add(btnSiguiente);
		
		btnUltimo = new JButton("Último");
		pnlBotones.add(btnUltimo);
	}
}
