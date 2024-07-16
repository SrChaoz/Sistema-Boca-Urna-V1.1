package ec.edu.puce.elecciones.dominio;

import java.util.HashMap;
import java.util.Map;

public class Prefecto {
    private int id;
    private String nombre;
    private String provincia;
    private String partido;
    private int votos;
    private Map<String, Integer> votosPorProvincia;
    private Map<String, Integer> votosPorCiudad;

    public Prefecto() {
        votosPorProvincia = new HashMap<>();
        votosPorCiudad = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }

    public Map<String, Integer> getVotosPorProvincia() {
        return votosPorProvincia;
    }
    
    public Map<String, Integer> getVotosPorCiudad() {
        return votosPorCiudad;
    }

    public void agregarVoto(String provincia) {
        this.votos++;
        votosPorProvincia.put(provincia, votosPorProvincia.getOrDefault(provincia, 0) + 1);
    }

    public void eliminarVoto(String provincia) {
        if (votos > 0) {
            votos--;
            votosPorProvincia.put(provincia, votosPorProvincia.getOrDefault(provincia, 0) - 1);
        }
    }

    public void agregarVotoPorCiudad(String ciudad) {
    	
        if (votosPorCiudad.containsKey(ciudad)) {
            votosPorCiudad.put(ciudad, votosPorCiudad.get(ciudad) + 1);
        } else {
            votosPorCiudad.put(ciudad, 1);
        }
    }

 

    public void eliminarVotoPorCiudad(String ciudad) {
    	int votosCiudad = votosPorCiudad.get(ciudad);
    	if(votosCiudad > 0) {
    		
    	 if (votosPorCiudad.containsKey(ciudad)) {
             votosPorCiudad.put(ciudad, votosPorCiudad.get(ciudad) - 1);
         } else {
             votosPorCiudad.put(ciudad, 1);
         };
    	}
    }
    
    
}
