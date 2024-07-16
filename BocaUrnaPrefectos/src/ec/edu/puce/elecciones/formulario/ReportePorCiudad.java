package ec.edu.puce.elecciones.formulario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ec.edu.puce.elecciones.dominio.Prefecto;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportePorCiudad extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> comboBoxProvincia;
    private JComboBox<String> comboBoxCiudad;
    private JTable table;
    private DefaultTableModel model;
    private List<Prefecto> prefectos;
    private Map<String, String[]> ciudadesPorProvincia;

    public ReportePorCiudad(List<Prefecto> prefectos) {
        setClosable(true);
        this.prefectos = prefectos;
        ciudadesPorProvincia = new HashMap<>();

        setTitle("Reporte por Ciudad");
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panelSeleccion = new JPanel();
        contentPane.add(panelSeleccion, BorderLayout.NORTH);

        JLabel lblProvincia = new JLabel("Provincia");
        panelSeleccion.add(lblProvincia);

        ciudadesPorProvincia = new HashMap<>();
        initCiudadesPorProvincia();
        List<String> provinciasOrdenadas = new ArrayList<>(ciudadesPorProvincia.keySet());
        Collections.sort(provinciasOrdenadas);

        comboBoxProvincia = new JComboBox<>(provinciasOrdenadas.toArray(new String[0]));
        comboBoxProvincia.addActionListener(e -> actualizarCiudades());
        panelSeleccion.add(comboBoxProvincia);

        JLabel lblCiudad = new JLabel("Ciudad");
        panelSeleccion.add(lblCiudad);

        comboBoxCiudad = new JComboBox<>();
        panelSeleccion.add(comboBoxCiudad);
        actualizarCiudades();

        model = new DefaultTableModel(new Object[][]{}, new String[]{"Nombre", "Partido", "Votos"});
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        comboBoxProvincia.addActionListener(e -> llenarTabla());
        comboBoxCiudad.addActionListener(e -> llenarTabla());

        llenarTabla();
    }

    private void actualizarCiudades() {
        String provinciaSeleccionada = (String) comboBoxProvincia.getSelectedItem();
        String[] ciudades = ciudadesPorProvincia.get(provinciaSeleccionada);
        comboBoxCiudad.setModel(new DefaultComboBoxModel<>(ciudades));
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

    private void llenarTabla() {
        model.setRowCount(0); 
        String provinciaSeleccionada = (String) comboBoxProvincia.getSelectedItem();
        String ciudadSeleccionada = (String) comboBoxCiudad.getSelectedItem();

        for (Prefecto prefecto : prefectos) {
        
                Map<String, Integer> votosPorCiudad = prefecto.getVotosPorCiudad();
             
                int votos = votosPorCiudad.getOrDefault(ciudadSeleccionada, 0);
   
                if (votos > 0) {
                    model.addRow(new Object[]{prefecto.getNombre(), prefecto.getPartido(), votos});
                
            }
        }
    }
}
