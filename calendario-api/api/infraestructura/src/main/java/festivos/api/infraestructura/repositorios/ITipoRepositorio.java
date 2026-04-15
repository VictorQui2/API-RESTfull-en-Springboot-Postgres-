package festivos.api.infraestructura.repositorios;

import festivos.api.dominio.entidades.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoRepositorio extends JpaRepository<Tipo, Integer> {

}
