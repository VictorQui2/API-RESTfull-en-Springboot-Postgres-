package festivos.api.presentacion.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import festivos.api.core.servicios.IFestivoServicio;
import festivos.api.dominio.modelos.Festivo;

@RestController
@RequestMapping("/api/festivos")
public class FestivoControlador {

    @Autowired
    private IFestivoServicio servicio;

    @GetMapping(value = "/obtener/{anio}")
    public List<Festivo> obtener(@PathVariable int anio) {
        return servicio.listarPorAnio(anio);
    }

}
