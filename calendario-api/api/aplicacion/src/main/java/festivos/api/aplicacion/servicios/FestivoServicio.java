package festivos.api.aplicacion.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import festivos.api.aplicacion.clientes.FestivoCliente;
import festivos.api.core.servicios.IFestivoServicio;
import festivos.api.dominio.modelos.Festivo;

@Service
public class FestivoServicio implements IFestivoServicio {

    @Autowired
    private FestivoCliente cliente;

    public List<Festivo> listarPorAnio(int anio) {
        return cliente.obtenerPorAnio(anio);
    }

}
