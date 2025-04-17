CREATE TABLE IF NOT EXISTS  unidade(
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    ativo BOOLEAN NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS especialidades(
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS checkinout(
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_medico BIGINT,
    id_unidade BIGINT NOT NULL ,
    UUID varchar NOT NULL ,
    check_in TIMESTAMP,
    check_out TIMESTAMP,
    data TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS unidade_especialidade(
    id BIGINT NOT NULL AUTO_INCREMENT,
    unidade_id BIGINT NOT NULL,
    especialidade_id BIGINT NOT NULL,
    PRIMARY KEY (id)
);
