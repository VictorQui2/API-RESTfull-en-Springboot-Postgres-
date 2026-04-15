package festivos.api.dominio.modelos;

import java.util.List;

public class GrupoDias {

    private String clasificacion;

    private List<Dia> dias;

    public GrupoDias() {
    }

    public GrupoDias(String clasificacion, List<Dia> dias) {
        this.clasificacion = clasificacion;
        this.dias = dias;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public List<Dia> getDias() {
        return dias;
    }

    public void setDias(List<Dia> dias) {
        this.dias = dias;
    }

}
