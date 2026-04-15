package festivos.api.core.servicios;

import festivos.api.dominio.modelos.Festivo;

import java.util.List;

public interface IFestivoServicio {

    public List<Festivo> listarPorAnio(int anio);
}
