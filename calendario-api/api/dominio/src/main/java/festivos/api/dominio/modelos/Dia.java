package festivos.api.dominio.modelos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class Dia {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    private String descripcion;

    public Dia() {
    }

    public Dia(LocalDate fecha, String descripcion) {
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
