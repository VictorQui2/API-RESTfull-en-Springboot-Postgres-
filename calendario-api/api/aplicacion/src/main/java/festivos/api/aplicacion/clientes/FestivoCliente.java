package festivos.api.aplicacion.clientes;

import festivos.api.dominio.modelos.Festivo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
public class FestivoCliente {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public FestivoCliente(RestTemplate restTemplate,
                          @Value("${festivos.api.base-url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    public List<Festivo> obtenerPorAnio(int anio) {
        String url = baseUrl + "/api/festivos/obtener/" + anio;

        ResponseEntity<List<Festivo>> respuesta = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Festivo>>() {}
        );

        List<Festivo> cuerpo = respuesta.getBody();
        return cuerpo != null ? cuerpo : Collections.emptyList();
    }
}
