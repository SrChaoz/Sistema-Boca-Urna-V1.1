package ec.edu.puce.elecciones.formulario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import ec.edu.puce.elecciones.dominio.Prefecto;

import java.awt.event.*;
import java.util.List;
import java.awt.Color;

public class ReporteGeneral extends JInternalFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    private JTable table;
    private DefaultTableModel model;
    private List<Prefecto> prefectos;
    private JComboBox<String> comboBox;

    public ReporteGeneral(List<Prefecto> prefectos) {
        setClosable(true);
        this.prefectos = prefectos;
        setTitle("REPORTE GENERAL POR PROVINCIA");
        setBounds(100, 100, 600, 309);
        getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 48, 566, 167);
        getContentPane().add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Nombre", "Partido", "Votos"}));
        scrollPane.setViewportView(table);

        JLabel lblNombres = new JLabel("Provincia");
        lblNombres.setBounds(12, 21, 70, 15);
        getContentPane().add(lblNombres);

        comboBox = new JComboBox<>(new String[]{
                "Azuay", "Bolívar", "Cañar", "Carchi", "Chimborazo", "Cotopaxi",
                "El Oro", "Esmeraldas", "Galápagos", "Guayas", "Imbabura", "Loja",
                "Los Ríos", "Manabí", "Morona Santiago", "Napo", "Orellana",
                "Pastaza", "Pichincha", "Santa Elena", "Santo Domingo de los Tsáchilas",
                "Sucumbíos", "Tungurahua", "Zamora Chinchipe"
        });

        comboBox.setBounds(79, 12, 231, 24);
        comboBox.addActionListener(this);
        getContentPane().add(comboBox);

        model = (DefaultTableModel) table.getModel();
        llenarTabla();
    }

    private void llenarTabla() {
        model.setRowCount(0);
        String provinciaSeleccionada = (String) comboBox.getSelectedItem();
        for (Prefecto prefecto : prefectos) {
            int votosEnProvincia = prefecto.getVotosPorProvincia().getOrDefault(provinciaSeleccionada, 0);
            if (votosEnProvincia > 0) {
                model.addRow(new Object[]{prefecto.getNombre(), prefecto.getPartido(), votosEnProvincia});
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboBox) {
            llenarTabla();
        }
    }
}
