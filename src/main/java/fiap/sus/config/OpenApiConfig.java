package fiap.sus.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    // Injeta a porta do servidor definida em application.properties/yaml
    // O :8080 fornece um valor padrão caso a propriedade não seja definida
    @Value("${server.port:8032}")
    private String serverPort;

    // Injeta o context-path, se houver (ex: /api)
    // O : fornece um valor padrão vazio se não for definido
    @Value("${server.servlet.context-path:}")
    private String contextPath;

    @Bean
    public OpenAPI customOpenAPI() {


        // Constrói a URL base dinamicamente
        String localUrl = "http://localhost:" + serverPort + contextPath;

        // Cria o objeto Server para o ambiente local
        Server localServer = new Server();
        localServer.setUrl(localUrl);
        localServer.setDescription("Servidor de Desenvolvimento Local");


        return new OpenAPI()
                .servers(List.of(localServer))
                .info(new Info()
                        .title("API SUS.")
                        .version("1.0")
                        .description("API que gerencia cadastro de Unidades de atendimento para o SUS - FIAP Phase 5 Hackathon."));
    }
}