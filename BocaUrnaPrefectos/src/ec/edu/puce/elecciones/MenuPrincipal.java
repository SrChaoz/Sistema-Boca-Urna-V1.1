package ec.edu.puce.elecciones;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import ec.edu.puce.elecciones.dominio.Prefecto;
import ec.edu.puce.elecciones.formulario.BocaDeUrna;
import ec.edu.puce.elecciones.formulario.CrearPrefecto;
import ec.edu.puce.elecciones.formulario.ReporteGeneral;
import ec.edu.puce.elecciones.formulario.ReportePorCiudad;
import ec.edu.puce.elecciones.formulario.frameBarra;
import ec.edu.puce.elecciones.formulario.framePastel;

public class MenuPrincipal extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contenedor;
    private JDesktopPane desktopPane;
    private JMenuItem mntmSalir;
    private JMenuItem mntmCrearPrefectos;
    private JMenuItem mntmBocaDeUrna;
    private JMenuItem mntmResultadosBarras;
    private JMenuItem mntmResultadosPastel;
    private JMenuItem mntmResultadosPorProvincia;
    private JMenuItem mntmResultadosPorCiudad;

    private JInternalFrame crearPrefectoFrame;
    private JInternalFrame bocaDeUrnaFrame;
    private JInternalFrame reporteGeneralFrame;
    private JInternalFrame reportePorCiudadFrame;

    private frameBarra frameBarra;
    private framePastel framePastel;

    public List<Prefecto> prefectos = new ArrayList<>();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MenuPrincipal frame = new MenuPrincipal();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MenuPrincipal() {
        setTitle("SISTEMA CONTEO DE VOTOS BOCA DE URNA");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(204, 255, 255));
        setJMenuBar(menuBar);

        JMenu mnSistema = new JMenu("Sistema");
        mnSistema.setFont(new Font("Dialog", Font.BOLD, 16));
        menuBar.add(mnSistema);

        mntmSalir = new JMenuItem("Salir");
        mntmSalir.setBackground(new Color(204, 255, 204));
        mntmSalir.addActionListener(this);
        mntmSalir.setFont(new Font("Dialog", Font.BOLD, 16));
        mnSistema.add(mntmSalir);

        JMenu mnAdministracin = new JMenu("Candidatos");
        mnAdministracin.setFont(new Font("Dialog", Font.BOLD, 16));
        menuBar.add(mnAdministracin);

        mntmCrearPrefectos = new JMenuItem("Crear Prefectos");
        mntmCrearPrefectos.setBackground(new Color(204, 255, 204));
        mntmCrearPrefectos.setFont(new Font("Dialog", Font.BOLD, 16));
        mntmCrearPrefectos.addActionListener(this);
        mnAdministracin.add(mntmCrearPrefectos);

        JMenu mnProceso = new JMenu("Proceso");
        mnProceso.setFont(new Font("Dialog", Font.BOLD, 16));
        menuBar.add(mnProceso);

        mntmBocaDeUrna = new JMenuItem("Boca de Urna");
        mntmBocaDeUrna.setBackground(new Color(204, 255, 204));
        mntmBocaDeUrna.setFont(new Font("Dialog", Font.BOLD, 16));
        mntmBocaDeUrna.addActionListener(this);
        mnProceso.add(mntmBocaDeUrna);

        JMenu mnReportes = new JMenu("Reportes");
        mnReportes.setFont(new Font("Dialog", Font.BOLD, 16));
        menuBar.add(mnReportes);

        mntmResultadosPorProvincia = new JMenuItem("Resultados por provincia");
        mntmResultadosPorProvincia.setBackground(new Color(204, 255, 204));
        mntmResultadosPorProvincia.addActionListener(this);
        mntmResultadosPorProvincia.setFont(new Font("Dialog", Font.BOLD, 16));
        mnReportes.add(mntmResultadosPorProvincia);

        mntmResultadosPorCiudad = new JMenuItem("Resultados por cantón o ciudad");
        mntmResultadosPorCiudad.setBackground(new Color(204, 255, 204));
        mntmResultadosPorCiudad.addActionListener(this);
        mntmResultadosPorCiudad.setFont(new Font("Dialog", Font.BOLD, 16));
        mnReportes.add(mntmResultadosPorCiudad);

        JMenu mnGrficos = new JMenu("Gráficos");
        mnGrficos.setFont(new Font("Dialog", Font.BOLD, 16));
        menuBar.add(mnGrficos);

        mntmResultadosBarras = new JMenuItem("Grafico Barras");
        mntmResultadosBarras.setBackground(new Color(204, 255, 204));
        mnGrficos.add(mntmResultadosBarras);
        mntmResultadosBarras.addActionListener(this);
        mntmResultadosBarras.setFont(new Font("Dialog", Font.BOLD, 16));

        mntmResultadosPastel = new JMenuItem("Grafico Pastel");
        mntmResultadosPastel.setBackground(new Color(204, 255, 204));
        mnGrficos.add(mntmResultadosPastel);
        mntmResultadosPastel.addActionListener(this);
        mntmResultadosPastel.setFont(new Font("Dialog", Font.BOLD, 16));

        contenedor = new JPanel();
        contenedor.setBackground(new Color(255, 255, 255));
        contenedor.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contenedor);
        contenedor.setLayout(new CardLayout(0, 0));

        desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(255, 255, 255));
        contenedor.add(desktopPane, "name_35522358088801");

        frameBarra = new frameBarra(desktopPane, prefectos);
        framePastel = new framePastel(desktopPane, prefectos);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mntmSalir) {
            dispose();
        } else if (e.getSource() == mntmCrearPrefectos) {
            if (crearPrefectoFrame == null || !crearPrefectoFrame.isVisible()) {
                crearPrefectoFrame = new CrearPrefecto(prefectos);
                desktopPane.add(crearPrefectoFrame);
                crearPrefectoFrame.setVisible(true);
            }
        } else if (e.getSource() == mntmBocaDeUrna) {
            if (bocaDeUrnaFrame == null || !bocaDeUrnaFrame.isVisible()) {
                bocaDeUrnaFrame = new BocaDeUrna(prefectos);
                desktopPane.add(bocaDeUrnaFrame);
                bocaDeUrnaFrame.setVisible(true);
            }
        } else if (e.getSource() == mntmResultadosPorProvincia) {
            if (reporteGeneralFrame == null || !reporteGeneralFrame.isVisible()) {
                reporteGeneralFrame = new ReporteGeneral(prefectos);
                desktopPane.add(reporteGeneralFrame);
                reporteGeneralFrame.setVisible(true);
            }
        } else if (e.getSource() == mntmResultadosPorCiudad) {
            if (reportePorCiudadFrame == null || !reportePorCiudadFrame.isVisible()) {
                reportePorCiudadFrame = new ReportePorCiudad(prefectos);
                desktopPane.add(reportePorCiudadFrame);
                reportePorCiudadFrame.setVisible(true);
            }
        } else if (e.getSource() == mntmResultadosBarras) {
            frameBarra.crearResultadosEnBarras();
        } else if (e.getSource() == mntmResultadosPastel) {
            framePastel.crearResultadosEnPastel();
        }
    }
}
