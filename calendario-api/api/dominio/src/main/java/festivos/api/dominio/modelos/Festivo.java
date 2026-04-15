package festivos.api.dominio.modelos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class Festivo {

    private String festivo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    public Festivo() {
    }

    public Festivo(String festivo, LocalDate fecha) {
        this.festivo = festivo;
        this.fecha = fecha;
    }

    public String getFestivo() {
        return festivo;
    }

    public void setFestivo(String festivo) {
        this.festivo = festivo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

}
