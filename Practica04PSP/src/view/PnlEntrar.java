package view;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Rectangle;

public class PnlEntrar extends JPanel {
	
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	
	private JLabel lblEntrar;
	private JLabel lblUsuario;
	private JLabel lblPassword;
	
	private JButton btnValidar;
	
	public PnlEntrar() {
		setLayout(new GridLayout(7, 1, 5, 5));
		
		//Inicializamos los componentes
		initComponents();
		
		//Añadimos los eventos
		addListeners();
		
	}

	private void addListeners() {
		//Agregamos funcionalidad al btnValidar
				btnValidar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					}
				});
		
	}

	private void initComponents() {
		lblEntrar = new JLabel("Login");
		lblEntrar.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblEntrar.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblEntrar);
		
		lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setMaximumSize(new Dimension(100, 2147483647));
		txtUsuario.setToolTipText("Introduce el usuario");
		add(txtUsuario);
		txtUsuario.setColumns(10);
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblPassword = new JLabel("Contraseña");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setToolTipText("Introduce la contraseña");
		add(txtPassword);
		txtPassword.setColumns(10);
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnValidar = new JButton("Validar");
		add(btnValidar);		
	}
}
