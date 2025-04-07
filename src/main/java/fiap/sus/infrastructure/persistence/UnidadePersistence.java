package fiap.sus.infrastructure.persistence;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "unidade")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UnidadePersistence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 30)
    @Column(nullable = false, length = 30)
    private String nome;
    private String endereco;
    private boolean ativo;

}
