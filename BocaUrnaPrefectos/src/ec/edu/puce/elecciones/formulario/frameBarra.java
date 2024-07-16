package ec.edu.puce.elecciones.formulario;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import ec.edu.puce.elecciones.dominio.Prefecto;

public class frameBarra {
    private JDesktopPane desktopPane;
    private List<Prefecto> prefectos;
    private JInternalFrame frame;

    public frameBarra(JDesktopPane desktopPane, List<Prefecto> prefectos) {
        this.desktopPane = desktopPane;
        this.prefectos = prefectos;
    }

    public void crearResultadosEnBarras() {
        if (frame == null || !frame.isVisible()) {
            frame = new JInternalFrame("Resultado en Barras", true);

            final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (Prefecto prefecto : prefectos) {
                dataset.addValue(prefecto.getVotos(), "Prefecto", prefecto.getNombre());
            }
            final JFreeChart chart = ChartFactory.createBarChart("Resultados", "Category", "Series", dataset,
                    PlotOrientation.VERTICAL, true, true, false);
            final ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(600, 400));

            final JPanel panel = new JPanel();
            panel.setBounds(0, 0, 600, 400);
            panel.setLayout(new BorderLayout());
            panel.add(chartPanel);

            JButton btnCancelar = new JButton("Cancelar");
            btnCancelar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    frame.dispose();
                }
            });

            panel.add(btnCancelar, BorderLayout.SOUTH);
            frame.getContentPane().add(panel);
            desktopPane.add(frame);
            frame.pack();
            frame.setVisible(true);
        }
    }
}
