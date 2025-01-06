package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class FrmMenuPrincipal extends JFrame {

    private JPanel contentPane;

    private JMenuBar menuBar;
    private JMenu mnuVisualizar;
    private JMenu mnuValidar;
    private JMenuItem mniVDetalle;
    private JMenuItem mniVResumen;
    private JMenuItem mniEntrar;
    private JMenuItem mniSalir;
    private JSeparator separator;
    private JMenuItem mniAcercaDe;

    public FrmMenuPrincipal() {
        setName("FrmMenuPrincipal");

        setTitle("-Menú principal-");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        setLocationRelativeTo(null);

        initComponents();

        setVisible(true);
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

        //Panel por defecto
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));


    }
}
