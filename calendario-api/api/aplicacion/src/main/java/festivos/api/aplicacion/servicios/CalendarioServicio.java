package festivos.api.aplicacion.servicios;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import festivos.api.core.servicios.ICalendarioServicio;
import festivos.api.core.servicios.IFestivoServicio;
import festivos.api.dominio.entidades.Calendario;
import festivos.api.dominio.entidades.Tipo;
import festivos.api.dominio.modelos.Dia;
import festivos.api.dominio.modelos.Festivo;
import festivos.api.dominio.modelos.GrupoDias;
import festivos.api.infraestructura.repositorios.ICalendarioRepositorio;
import festivos.api.infraestructura.repositorios.ITipoRepositorio;

@Service
public class CalendarioServicio implements ICalendarioServicio {

    public static final int TIPO_LABORAL    = 1;
    public static final int TIPO_FIN_SEMANA = 2;
    public static final int TIPO_FESTIVO    = 3;

    private static final String DESC_LABORAL    = "Día laboral";
    private static final String DESC_FIN_SEMANA = "Fin de Semana";
    private static final String DESC_FESTIVO    = "Día festivo";

    @Autowired
    private ICalendarioRepositorio calendarioRepositorio;

    @Autowired
    private ITipoRepositorio tipoRepositorio;

    @Autowired
    private IFestivoServicio festivoServicio;

    @Transactional
    public boolean generar(int anio) {
        try {
            LocalDate inicio = LocalDate.of(anio, 1, 1);
            LocalDate fin = LocalDate.of(anio, 12, 31);

            calendarioRepositorio.deleteByFechaBetween(Date.valueOf(inicio), Date.valueOf(fin));

            List<Festivo> festivos = festivoServicio.listarPorAnio(anio);
            Map<LocalDate, String> mapaFestivos = new HashMap<>();
            for (Festivo f : festivos) {
                if (f.getFecha() != null) {
                    mapaFestivos.put(f.getFecha(), f.getFestivo());
                }
            }

            Tipo tLaboral = tipoRepositorio.findById(TIPO_LABORAL).orElseThrow();
            Tipo tFinSemana = tipoRepositorio.findById(TIPO_FIN_SEMANA).orElseThrow();
            Tipo tFestivo = tipoRepositorio.findById(TIPO_FESTIVO).orElseThrow();

            List<Calendario> lote = new ArrayList<>();
            LocalDate fecha = inicio;
            while (!fecha.isAfter(fin)) {
                Calendario c = new Calendario();
                c.setId(0);
                c.setFecha(Date.valueOf(fecha));

                if (mapaFestivos.containsKey(fecha)) {
                    c.setTipo(tFestivo);
                    c.setDescripcion(mapaFestivos.get(fecha));
                } else if (esFinDeSemana(fecha)) {
                    c.setTipo(tFinSemana);
                    c.setDescripcion(DESC_FIN_SEMANA);
                } else {
                    c.setTipo(tLaboral);
                    c.setDescripcion(DESC_LABORAL);
                }

                lote.add(c);
                fecha = fecha.plusDays(1);
            }

            calendarioRepositorio.saveAll(lote);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public List<GrupoDias> obtener(int anio) {
        LocalDate inicio = LocalDate.of(anio, 1, 1);
        LocalDate fin = LocalDate.of(anio, 12, 31);

        List<Calendario> dias = calendarioRepositorio
                .findByFechaBetweenOrderByFechaAsc(Date.valueOf(inicio), Date.valueOf(fin));

        Map<String, List<Dia>> agrupado = new LinkedHashMap<>();
        agrupado.put(DESC_LABORAL, new ArrayList<>());
        agrupado.put(DESC_FIN_SEMANA, new ArrayList<>());
        agrupado.put(DESC_FESTIVO, new ArrayList<>());

        for (Calendario c : dias) {
            String clasificacion = c.getTipo().getTipo().trim();
            agrupado.computeIfAbsent(clasificacion, k -> new ArrayList<>())
                    .add(new Dia(c.getFecha().toLocalDate(), c.getDescripcion()));
        }

        List<GrupoDias> resultado = new ArrayList<>();
        for (Map.Entry<String, List<Dia>> e : agrupado.entrySet()) {
            resultado.add(new GrupoDias(e.getKey(), e.getValue()));
        }
        return resultado;
    }

    private boolean esFinDeSemana(LocalDate fecha) {
        DayOfWeek d = fecha.getDayOfWeek();
        return d == DayOfWeek.SATURDAY || d == DayOfWeek.SUNDAY;
    }

}
