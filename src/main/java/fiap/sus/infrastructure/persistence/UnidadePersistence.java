package fiap.sus.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(name = "unidade_especialidade",
            joinColumns = @JoinColumn(name = "unidade_id"),
            inverseJoinColumns = @JoinColumn(name = "especialidade_id"))
    private Set<EspecialidadesPersistence> especialidades = new HashSet<>();

}
