package festivos.api.core.servicios;

import festivos.api.dominio.entidades.Tipo;

import java.util.List;

public interface ITipoServicio {

    public List<Tipo> listar();

    public Tipo obtener(int id);

    public Tipo agregar(Tipo tipo);
}
