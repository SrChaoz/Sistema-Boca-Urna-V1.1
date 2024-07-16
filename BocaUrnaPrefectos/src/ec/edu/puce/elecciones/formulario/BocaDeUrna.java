package ec.edu.puce.elecciones.formulario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import ec.edu.puce.elecciones.dominio.Prefecto;

import java.awt.event.*;
import java.util.*;
import java.awt.Color;

public class BocaDeUrna extends JInternalFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    private JComboBox<String> comboBoxPrefectos;
    private JComboBox<String> comboBoxProvincias;
    private JComboBox<String> comboBoxCiudades;
    private JButton btnAgregarVoto;
    private JButton btnEliminarVoto;
    
    private JTable table;
    private DefaultTableModel model;
    private JButton btnCancelar;
    private List<Prefecto> prefectos;
    private Map<String, String[]> ciudadesPorProvincia;

    public BocaDeUrna(List<Prefecto> prefectos) {
    	setClosable(true);
        this.prefectos = prefectos;
        ciudadesPorProvincia = new HashMap<>();
        initCiudadesPorProvincia();

        setTitle("BOCA DE URNA - REGISTRO");
        setBounds(100, 100, 600, 427);
        getContentPane().setLayout(null);

        JLabel lblPrefecto = new JLabel("Prefecto");
        lblPrefecto.setBounds(12, 21, 70, 15);
        getContentPane().add(lblPrefecto);

        comboBoxPrefectos = new JComboBox<>();
        for (Prefecto prefecto : prefectos) {
            comboBoxPrefectos.addItem(prefecto.getNombre());
        }
        comboBoxPrefectos.setBounds(79, 16, 231, 24);
        getContentPane().add(comboBoxPrefectos);

        JLabel lblProvincia = new JLabel("Provincia");
        lblProvincia.setBounds(12, 50, 70, 15);
        getContentPane().add(lblProvincia);

        List<String> provinciasOrdenadas = new ArrayList<>(ciudadesPorProvincia.keySet());
        Collections.sort(provinciasOrdenadas);

        comboBoxProvincias = new JComboBox<>(provinciasOrdenadas.toArray(new String[0]));
        comboBoxProvincias.setBounds(79, 45, 231, 24);
        comboBoxProvincias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCiudades();
            }
        });
        getContentPane().add(comboBoxProvincias);

        JLabel lblCiudad = new JLabel("Ciudad");
        lblCiudad.setBounds(12, 79, 70, 15);
        getContentPane().add(lblCiudad);

        comboBoxCiudades = new JComboBox<>();
        comboBoxCiudades.setBounds(79, 74, 231, 24);
        getContentPane().add(comboBoxCiudades);

        btnAgregarVoto = new JButton("Agregar Voto");
        btnAgregarVoto.setBackground(new Color(204, 255, 204));
        btnAgregarVoto.setBounds(320, 16, 137, 25);
        getContentPane().add(btnAgregarVoto);
        btnAgregarVoto.addActionListener(this);
        
        btnEliminarVoto = new JButton("Eliminar Voto");
        btnEliminarVoto.setBackground(new Color(255, 153, 153));
        btnEliminarVoto.setBounds(320, 45, 137, 25);
        getContentPane().add(btnEliminarVoto);
        btnEliminarVoto.addActionListener(this);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 120, 566, 220);
        getContentPane().add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Nombre","Partido", "Votos"}));
        scrollPane.setViewportView(table);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(255, 153, 153));
        btnCancelar.setBounds(234, 351, 117, 25);
        getContentPane().add(btnCancelar);
        btnCancelar.addActionListener(this);

        model = (DefaultTableModel) table.getModel();
        llenarTabla();
    }

    private void llenarTabla() {
        model.setRowCount(0);
        for (Prefecto prefecto : prefectos) {
            model.addRow(new Object[]{prefecto.getNombre(), prefecto.getPartido(), prefecto.getVotos()});
        }
    }

    private void actualizarCiudades() {
        String provinciaSeleccionada = (String) comboBoxProvincias.getSelectedItem();
        String[] ciudades = ciudadesPorProvincia.get(provinciaSeleccionada);
        comboBoxCiudades.setModel(new DefaultComboBoxModel<>(ciudades));
    }

    private void initCiudadesPorProvincia() {
        ciudadesPorProvincia.put("Azuay", new String[]{"Cuenca", "Gualaceo", "Sigsig", "Chordeleg", "El Pan", "Paute", "Sevilla de Oro", "Guachapala", "Camilo Ponce Enríquez", "Suscal", "Nabón", "Ona", "Pucará", "San Fernando", "Santa Isabel"});
        ciudadesPorProvincia.put("Bolívar", new String[]{"Guaranda", "San Miguel", "Chillanes", "Chimbo", "Echeandía", "Las Naves"});
        ciudadesPorProvincia.put("Cañar", new String[]{"Azogues", "Biblián", "Cañar", "La Troncal", "Déleg", "El Tambo", "Suscal"});
        ciudadesPorProvincia.put("Carchi", new String[]{"Tulcán", "Bolívar", "Espejo", "Mira", "Montúfar", "San Pedro de Huaca"});
        ciudadesPorProvincia.put("Chimborazo", new String[]{"Riobamba", "Alausí", "Chambo", "Chunchi", "Colta", "Cumandá", "Guamote", "Guano", "Pallatanga", "Penipe"});
        ciudadesPorProvincia.put("Cotopaxi", new String[]{"Latacunga", "La Maná", "Pangua", "Pujilí", "Salcedo", "Saquisilí", "Sigchos"});
        ciudadesPorProvincia.put("El Oro", new String[]{"Machala", "Arenillas", "Atahualpa", "Balsas", "Chilla", "El Guabo", "Huaquillas", "Las Lajas", "Marcabelí", "Pasaje", "Piñas", "Portovelo", "Santa Rosa", "Zaruma"});
        ciudadesPorProvincia.put("Esmeraldas", new String[]{"Esmeraldas", "Eloy Alfaro", "Muisne", "Quinindé", "San Lorenzo"});
        ciudadesPorProvincia.put("Galápagos", new String[]{"Puerto Baquerizo Moreno", "Puerto Ayora", "Isabela"});
        ciudadesPorProvincia.put("Guayas", new String[]{"Guayaquil", "Alfredo Baquerizo Moreno (Jujan)", "Balao", "Balzar", "Colimes", "Coronel Marcelino Maridueña", "Daule", "Durán", "El Empalme", "El Triunfo", "Milagro", "Naranjal", "Naranjito", "Palestina", "Pedro Carbo", "Samborondón", "Santa Lucía", "Simón Bolívar", "Yaguachi"});
        ciudadesPorProvincia.put("Imbabura", new String[]{"Ibarra", "Antonio Ante", "Cotacachi", "Otavalo", "Pimampiro", "San Miguel de Urcuquí"});
        ciudadesPorProvincia.put("Loja", new String[]{"Loja", "Calvas", "Catamayo", "Celica", "Chaguarpamba", "Espíndola", "Gonzanamá", "Macará", "Paltas", "Pindal", "Quilanga", "Saraguro", "Sozoranga", "Zapotillo"});
        ciudadesPorProvincia.put("Los Ríos", new String[]{"Babahoyo", "Baba", "Buena Fé", "Mocache", "Montalvo", "Palenque", "Pueblo Viejo", "Quevedo", "Quinsaloma", "Urdaneta", "Valencia", "Ventanas", "Vinces"});
        ciudadesPorProvincia.put("Manabí", new String[]{"Portoviejo", "Bolívar", "Chone", "El Carmen", "Flavio Alfaro", "Jipijapa", "Junín", "Manta", "Montecristi", "Paján", "Pedernales", "Pichincha", "Rocafuerte", "Santa Ana", "Sucre", "Tosagua"});
        ciudadesPorProvincia.put("Morona Santiago", new String[]{"Macas", "Gualaquiza", "Huamboya", "Limón Indanza", "Logroño", "Morona", "Palora", "San Juan Bosco", "Santiago de Méndez", "Sucúa", "Taisha", "Tiwinza"});
        ciudadesPorProvincia.put("Napo", new String[]{"Tena", "Archidona", "El Chaco", "Quijos"});
        ciudadesPorProvincia.put("Orellana", new String[]{"Francisco de Orellana (El Coca)", "Aguarico", "Loreto", "Carlos Julio Arosemena Tola"});
        ciudadesPorProvincia.put("Pastaza", new String[]{"Puyo", "Arajuno", "Mera", "Santa Clara"});
        ciudadesPorProvincia.put("Pichincha", new String[]{"Quito", "Cayambe", "Mejía", "Pedro Moncayo", "Rumiñahui", "San Miguel de los Bancos"});
        ciudadesPorProvincia.put("Santa Elena", new String[]{"Santa Elena", "La Libertad", "Salinas"});
        ciudadesPorProvincia.put("Santo Domingo de los Tsáchilas", new String[]{"Santo Domingo", "La Concordia"});
        ciudadesPorProvincia.put("Sucumbíos", new String[]{"Nueva Loja", "Cascales", "Cuyabeno", "Gonzalo Pizarro", "Lago Agrio", "Putumayo", "Shushufindi", "Sucumbíos"});
        ciudadesPorProvincia.put("Tungurahua", new String[]{"Ambato", "Baños de Agua Santa", "Cevallos", "Mocha", "Patate", "Quero"});
        ciudadesPorProvincia.put("Zamora Chinchipe", new String[]{"Zamora", "Chinchipe", "Chinchipe (Zamora)", "El Pangui", "Yacuambi", "Yantzaza"});
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancelar) {
            dispose();
        } else if (e.getSource() == btnAgregarVoto) {
            String seleccionado = (String) comboBoxPrefectos.getSelectedItem();
            String ciudad = (String) comboBoxCiudades.getSelectedItem();
            
            if (ciudad == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una ciudad.", "Error", JOptionPane.ERROR_MESSAGE);
                return; 
            }

            for (Prefecto prefecto : prefectos) {
                if (prefecto.getNombre().equals(seleccionado)) {
                    prefecto.agregarVoto((String) comboBoxProvincias.getSelectedItem());
                    prefecto.agregarVotoPorCiudad(ciudad);
                    break;
                }
            }
            llenarTabla();
        } else if (e.getSource() == btnEliminarVoto) {
            String seleccionado = (String) comboBoxPrefectos.getSelectedItem();
            String ciudad = (String) comboBoxCiudades.getSelectedItem();
            
            if (ciudad == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una ciudad.", "Error", JOptionPane.ERROR_MESSAGE);
                return; 
            }

            for (Prefecto prefecto : prefectos) {
                if (prefecto.getNombre().equals(seleccionado)) {
                    prefecto.eliminarVoto((String) comboBoxProvincias.getSelectedItem());
                    prefecto.eliminarVotoPorCiudad(ciudad);
                    break;
                }
            }
            llenarTabla();
        } 
    }
}
