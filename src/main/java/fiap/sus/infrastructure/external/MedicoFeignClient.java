package fiap.sus.infrastructure.external;

import fiap.sus.api.dto.MedicoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "medico-client", url = "https://localhost:8003")
public interface MedicoFeignClient {

    @GetMapping("/detail/{id}")
    MedicoResponse getMedico(@PathVariable("uuid") String uuid);
}
