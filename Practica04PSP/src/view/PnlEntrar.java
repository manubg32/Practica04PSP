package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.Conn;
import db.Database;

public class PnlEntrar extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String user;
	public static Integer idAlumn; 
	
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	
	private JLabel lblEntrar;
	private JLabel lblUsuario;
	private JLabel lblPassword;
	
	private JButton btnValidar;
	
	public PnlEntrar() throws SQLException {
		setLayout(new GridLayout(7, 1, 5, 5));
		
		//Inicializamos los componentes
		initComponents();
		
		//Añadimos los eventos
		addListeners();
		
	}

	private void addListeners() {
		//Agregamos funcionalidad al btnValidar
				btnValidar.addActionListener(new ActionListener()  {
					public void actionPerformed(ActionEvent e) {
						//TODO proceso de validacion
						
						//Hay que validar el usuario para evitar un SQLInjection
						
						//Tras validar al usuario y que sea correcto guardamos su usuario y numero en una variable global para realizar las consultas
						user = txtUsuario.getText();
						idAlumn = getAlumnNumber();
						
					}

					//Método que devuelve el id del Alumno
					private int getAlumnNumber() {
						try {
							Conn.open();
							ResultSet rs = Database.executeQuery("SELECT numero FROM Alumno WHERE usuario = '" + user + "');");
							Conn.close();
							return rs.getInt("numero");
						} catch (SQLException e) {
							JOptionPane.showMessageDialog(btnValidar, e.getMessage());
							return 0;
						}
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
