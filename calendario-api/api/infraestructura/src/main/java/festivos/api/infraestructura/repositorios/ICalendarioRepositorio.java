package festivos.api.infraestructura.repositorios;

import festivos.api.dominio.entidades.Calendario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ICalendarioRepositorio extends JpaRepository<Calendario, Integer> {

    List<Calendario> findByFechaBetweenOrderByFechaAsc(Date inicio, Date fin);

    void deleteByFechaBetween(Date inicio, Date fin);
}
