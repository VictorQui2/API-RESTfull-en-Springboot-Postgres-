package festivos.api.presentacion.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import festivos.api.core.servicios.ICalendarioServicio;
import festivos.api.dominio.modelos.GrupoDias;

@RestController
@RequestMapping("/api/calendario")
public class CalendarioControlador {

    @Autowired
    private ICalendarioServicio servicio;

    @PostMapping(value = "/generar/{anio}")
    public boolean generar(@PathVariable int anio) {
        return servicio.generar(anio);
    }

    @GetMapping(value = "/obtener/{anio}")
    public List<GrupoDias> obtener(@PathVariable int anio) {
        return servicio.obtener(anio);
    }

}
