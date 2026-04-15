package festivos.api.core.servicios;

import festivos.api.dominio.modelos.GrupoDias;

import java.util.List;

public interface ICalendarioServicio {

    public boolean generar(int anio);

    public List<GrupoDias> obtener(int anio);
}
