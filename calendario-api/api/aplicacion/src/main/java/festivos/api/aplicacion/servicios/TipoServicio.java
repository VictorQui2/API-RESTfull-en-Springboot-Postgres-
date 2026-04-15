package festivos.api.aplicacion.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import festivos.api.core.servicios.ITipoServicio;
import festivos.api.dominio.entidades.Tipo;
import festivos.api.infraestructura.repositorios.ITipoRepositorio;

@Service
public class TipoServicio implements ITipoServicio {

    @Autowired
    private ITipoRepositorio repositorio;

    public List<Tipo> listar() {
        return repositorio.findAll();
    }

    public Tipo obtener(int id) {
        var tipo = repositorio.findById(id);
        return tipo.isEmpty() ? null : tipo.get();
    }

    public Tipo agregar(Tipo tipo) {
        tipo.setId(0);
        return repositorio.save(tipo);
    }

}
