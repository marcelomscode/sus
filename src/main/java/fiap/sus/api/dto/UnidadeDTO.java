package fiap.sus.api.dto;

import lombok.Getter;


@Getter
public class UnidadeDTO {

    private final Long id;
    private final String nome;
    private final String endereco;
    private final boolean ativo;

    public UnidadeDTO(Long id, String nome, String endereco, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.ativo = ativo;
    }

}
