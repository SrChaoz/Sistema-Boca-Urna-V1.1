package ec.edu.puce.elecciones.formulario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import ec.edu.puce.elecciones.dominio.Prefecto;

import java.awt.event.*;
import java.util.*;
import java.awt.Color;

public class CrearPrefecto extends JInternalFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JTextField txtNombre;
    private JTextField txtPartido;
    private Prefecto prefecto;
    private JTable table;
    private DefaultTableModel model;
    private JButton btnGuardar;
    private JButton btnNuevo;
    private JButton btnCancelar;
    private List<Prefecto> prefectos;
    private int idPrefecto;
    private Map<String, String[]> ciudadesPorProvincia;

    public CrearPrefecto(List<Prefecto> prefectos) {
    	setClosable(true);
        idPrefecto = 1;
        this.prefectos = prefectos;

        setTitle("CANDIDATOS A PREFECTO");
        setBounds(100, 100, 500, 450);
        getContentPane().setLayout(null);

        JLabel lblNombres = new JLabel("Nombres");
        lblNombres.setBounds(10, 39, 70, 15);
        getContentPane().add(lblNombres);

        txtNombre = new JTextField();
        txtNombre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                agregarPrefecto();
            }
        });
        txtNombre.setBounds(67, 36, 231, 19);
        getContentPane().add(txtNombre);
        txtNombre.setColumns(10);

        JLabel lblPartido = new JLabel("Partido");
        lblPartido.setBounds(10, 68, 70, 15);
        getContentPane().add(lblPartido);

        txtPartido = new JTextField();
        txtPartido.setBounds(67, 65, 231, 19);
        getContentPane().add(txtPartido);
        txtPartido.setColumns(10);

        btnNuevo = new JButton("Nuevo");
        btnNuevo.setBackground(new Color(204, 255, 255));
        btnNuevo.addActionListener(this);
        btnNuevo.setBounds(341, 9, 117, 25);
        getContentPane().add(btnNuevo);

        btnGuardar = new JButton("Agregar");
        btnGuardar.setBackground(new Color(204, 255, 204));
        btnGuardar.addActionListener(this);
        btnGuardar.setBounds(341, 45, 117, 25);
        getContentPane().add(btnGuardar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(255, 153, 153));
        btnCancelar.addActionListener(this);
        btnCancelar.setBounds(341, 81, 117, 25);
        getContentPane().add(btnCancelar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 140, 466, 270);
        getContentPane().add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Nombre", "Partido" }));
        scrollPane.setViewportView(table);

        model = (DefaultTableModel) table.getModel();
        agregarFila();
    }

    private void agregarPrefecto() {
        String nombre = txtNombre.getText().trim();
        String partido = txtPartido.getText().trim();
        
        if (nombre.isEmpty() || partido.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos (Nombres y Partido).", "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
            return; 
        }
        
        prefecto = new Prefecto();
        prefecto.setId(idPrefecto);
        prefecto.setNombre(nombre);
       // prefecto.setProvincia((String) comboBoxProvincia.getSelectedItem());
        prefecto.setPartido(partido);
        prefectos.add(prefecto);
        agregarFila();
        txtNombre.setText("");
        txtPartido.setText("");
        idPrefecto++;
    }

    private void agregarFila() {
        model.setRowCount(0);
        for (Prefecto prefecto : prefectos) {
            Object[] fila = new Object[3];
            fila[0] = prefecto.getNombre();
            //fila[1] = prefecto.getProvincia();
            fila[1] = prefecto.getPartido();
            model.addRow(fila);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnNuevo) {
            nuevo();
        } else if (e.getSource() == btnGuardar) {
            agregarPrefecto();
        } else if (e.getSource() == btnCancelar) {
            dispose();
        } else if (e.getSource() == txtNombre) {
            agregarPrefecto();
        }
    }

    private void nuevo() {
        prefecto = new Prefecto();
        txtNombre.setText(prefecto.getNombre());
        txtPartido.setText("");
    }

    public List<Prefecto> getPrefectos() {
        return prefectos;
    }

    public void setPrefectos(List<Prefecto> prefectos) {
        this.prefectos = prefectos;
    }
}
