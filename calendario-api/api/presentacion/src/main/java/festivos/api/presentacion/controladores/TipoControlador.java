package festivos.api.presentacion.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import festivos.api.core.servicios.ITipoServicio;
import festivos.api.dominio.entidades.Tipo;

@RestController
@RequestMapping("/api/tipos")
public class TipoControlador {

    @Autowired
    private ITipoServicio servicio;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Tipo> listar() {
        return servicio.listar();
    }

    @GetMapping(value = "/obtener/{id}")
    public Tipo obtener(@PathVariable int id) {
        return servicio.obtener(id);
    }

    @PostMapping(value = "/")
    public Tipo agregar(@RequestBody Tipo tipo) {
        return servicio.agregar(tipo);
    }

}
