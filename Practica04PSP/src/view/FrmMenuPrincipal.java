package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.naming.Context;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class FrmMenuPrincipal extends JFrame {

    private JPanel contentPane;
    
    private FrmMenuPrincipal context = this;

    private JMenuBar menuBar;
    private JMenu mnuVisualizar;
    private JMenu mnuValidar;
    private JMenuItem mniVDetalle;
    private JMenuItem mniVResumen;
    private JMenuItem mniEntrar;
    private JMenuItem mniSalir;
    private JSeparator separator;
    private JSeparator separator2;
    private JMenuItem mniAcercaDe;
    
    private PnlEntrar pnlEntrar = new PnlEntrar();
    private PnlSalir pnlSalir = new PnlSalir();
    private PnlVDetalle pnlVDetalle = new PnlVDetalle();
    private PnlVResumen pnlVResumen = new PnlVResumen();

    private ImageIcon imagen;
    private JLabel lblImagen;

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
                setContentPane(pnlEntrar);
                revalidate();
                repaint();
			}
		});
    	
    	mniSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO cortar la conexion e indicar al usuario que se ha desconectado
                setTitle("I.E.S El Majuelo - Login");
                setContentPane(pnlEntrar);
                revalidate();
                repaint();
			}
		});
    	
    	mniVDetalle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("I.E.S El Majuelo - Detalles");
                setContentPane(pnlVDetalle);
                revalidate();
                repaint();
			}
		});
    	
    	mniVResumen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("I.E.S El Majuelo - Resumen");
                setContentPane(pnlVResumen);
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
        mniSalir.setHorizontalAlignment(SwingConstants.CENTER);
        mnuValidar.add(mniSalir);

        //Menu Visualizar
        mnuVisualizar = new JMenu("Visualizar");
        mnuVisualizar.setHorizontalAlignment(SwingConstants.CENTER);
        menuBar.add(mnuVisualizar);

        mniVDetalle = new JMenuItem("Detalle");
        mniVDetalle.setHorizontalAlignment(SwingConstants.CENTER);
        mnuVisualizar.add(mniVDetalle);

        mniVResumen = new JMenuItem("Resumen");
        mniVResumen.setHorizontalAlignment(SwingConstants.CENTER);
        mnuVisualizar.add(mniVResumen);


        //Un separador para que quede mas bonito
        separator = new JSeparator();
        separator.setOrientation(SwingConstants.VERTICAL);
        menuBar.add(separator);

        //Item menu acerca de
        mniAcercaDe = new JMenuItem("Acerca de");
        mniAcercaDe.setHorizontalAlignment(SwingConstants.CENTER);
        menuBar.add(mniAcercaDe);

        //Otro
        separator2 = new JSeparator();
        separator2.setOrientation(SwingConstants.VERTICAL);
        menuBar.add(separator2);

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
}
