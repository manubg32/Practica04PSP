package view;

import controller.CtrlSalir;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class FrmMenuPrincipal extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static JPanel contentPane;
    
    private FrmMenuPrincipal context = this;

    private JMenuBar menuBar;
    private static JMenu mnuVisualizar;
    private JMenu mnuValidar;
    private JMenuItem mniVDetalle;
    private JMenuItem mniVResumen;
    private static JMenuItem mniEntrar;
    private static JMenuItem mniSalir;

    private JMenuItem mniInvisible;
    private JMenuItem mniInvisible2;
    private JMenuItem mniInvisible3;
    private JMenuItem mniInvisible4;

    private JSeparator separator;
    private JSeparator separator2;
    private JSeparator separator3;

    private JMenuItem mniAcercaDe;

    public FrmMenuPrincipal() {
        setName("FrmMenuPrincipal");
        setIconImage(Toolkit.getDefaultToolkit().getImage(FrmMenuPrincipal.class.getResource("/icon.png")));
        setTitle("I.E.S El Majuelo - Menú principal ");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        setLocationRelativeTo(null);

        initComponents();
        
        addListeners();

        setVisible(true);
    }

    private void addListeners() {
		
    	mniEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                setTitle("I.E.S El Majuelo - Login");
                try {
                	setContentPane(new PnlEntrar());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al cargar el panel: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                revalidate();
                repaint();
			}
		});
    	
    	mniSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO cortar la conexion e indicar al usuario que se ha desconectado
                setTitle("I.E.S El Majuelo - Menu Principal");
                setContentPane(contentPane);
                JOptionPane.showMessageDialog(null, "Se ha cerrado la sesión.", "Cerrar Sesión", JOptionPane.INFORMATION_MESSAGE);
                CtrlSalir.logout();
                desactivarBotones();
                revalidate();
                repaint();
			}
		});
    	
    	mniVDetalle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("I.E.S El Majuelo - Detalles");
                try {
                	setContentPane(new PnlVDetalle());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al cargar el panel: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                revalidate();
                repaint();
			}
		});
    	
    	mniVResumen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("I.E.S El Majuelo - Resumen");
                setContentPane(new PnlVResumen());
                revalidate();
                repaint();
			}
		});

        mniAcercaDe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog (context, "Acerca De", true);
                dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                
                dialog.setUndecorated(true);
                
                PnlAcercaDe pnlAcercaDe = new PnlAcercaDe(dialog);
                
                dialog.getContentPane().add(pnlAcercaDe);
                
                dialog.setSize(600, 300);
                dialog.setLocationRelativeTo(context);
                
                dialog.setVisible(true);
            }
        });
		
	}

	private void initComponents() {

        //Barra de menu
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //Menu Validar
        mnuValidar = new JMenu("Validar");
        mnuValidar.setHorizontalAlignment(SwingConstants.CENTER);
        menuBar.add(mnuValidar);

        mniEntrar = new JMenuItem("Entrar");
        mniEntrar.setHorizontalAlignment(SwingConstants.CENTER);
        mnuValidar.add(mniEntrar);

        mniSalir = new JMenuItem("Salir");
        mniSalir.setEnabled(false);
        mniSalir.setHorizontalAlignment(SwingConstants.CENTER);
        mnuValidar.add(mniSalir);

        //Un separador para que quede mas bonito
        separator = new JSeparator();
        separator.setOrientation(SwingConstants.VERTICAL);
        menuBar.add(separator);

        //Menu Visualizar
        mnuVisualizar = new JMenu("Visualizar");
        mnuVisualizar.setEnabled(false);
        mnuVisualizar.setHorizontalAlignment(SwingConstants.CENTER);
        menuBar.add(mnuVisualizar);

        mniVDetalle = new JMenuItem("Detalle");
        mniVDetalle.setHorizontalAlignment(SwingConstants.CENTER);
        mnuVisualizar.add(mniVDetalle);

        mniVResumen = new JMenuItem("Resumen");
        mniVResumen.setHorizontalAlignment(SwingConstants.CENTER);
        mnuVisualizar.add(mniVResumen);


        //Otro separador
        separator2 = new JSeparator();
        separator2.setOrientation(SwingConstants.VERTICAL);
        menuBar.add(separator2);

        //Item menu acerca de
        mniAcercaDe = new JMenuItem("Acerca de");
        mniAcercaDe.setHorizontalAlignment(SwingConstants.CENTER);
        menuBar.add(mniAcercaDe);

        //Otro separador
        separator3 = new JSeparator();
        separator3.setOrientation(SwingConstants.VERTICAL);
        menuBar.add(separator3);

        //Menu item invisibles para hacer mas pequeño el acerca de
        mniInvisible = new JMenuItem();
        mniInvisible.setHorizontalAlignment(SwingConstants.CENTER);
        mniInvisible.setEnabled(false);
        menuBar.add(mniInvisible);

        mniInvisible2 = new JMenuItem();
        mniInvisible2.setHorizontalAlignment(SwingConstants.CENTER);
        mniInvisible2.setEnabled(false);
        menuBar.add(mniInvisible2);

        mniInvisible3 = new JMenuItem();
        mniInvisible3.setHorizontalAlignment(SwingConstants.CENTER);
        mniInvisible3.setEnabled(false);
        menuBar.add(mniInvisible3);

        mniInvisible4 = new JMenuItem();
        mniInvisible4.setHorizontalAlignment(SwingConstants.CENTER);
        mniInvisible4.setEnabled(false);
        menuBar.add(mniInvisible4);

        //Panel por defecto
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        //Imagen
        Image tmp = new ImageIcon(FrmMenuPrincipal.class.getResource("/icon.png")).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon logo = new ImageIcon(tmp);
        JLabel lblLogo = new JLabel(logo);
        contentPane.add(lblLogo, BorderLayout.CENTER);


    }

	public static void activarBotones() {
		mnuVisualizar.setEnabled(true);
		mniSalir.setEnabled(true);
		mniEntrar.setEnabled(false);
	}
    public static void desactivarBotones(){
        mnuVisualizar.setEnabled(false);
        mniSalir.setEnabled(false);
        mniEntrar.setEnabled(true);
    }


	
}
