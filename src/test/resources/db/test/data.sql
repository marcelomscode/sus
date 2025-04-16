INSERT INTO unidade
    (NOME, ENDERECO, ATIVO)
VALUES
    ('Unidade 1', 'Rua A, 123', true),
    ('Unidade 2', 'Rua B, 456', true),
    ('Unidade 3', 'Rua C, 789', true),
    ('Unidade 4', 'Rua D, 101', true),
    ('Unidade 5', 'Rua E, 112', true);

INSERT INTO especialidades
    (NOME, DESCRICAO)
VALUES
    ('Dermatologia', 'Especialidade médica que cuida da pele e suas doenças'),
    ('Pediatria', 'Especialidade médica que cuida da saúde infantil'),
    ('Ginecologia', 'Especialidade médica que cuida da saúde da mulher'),
    ('Oftalmologia', 'Especialidade médica que cuida dos olhos e visão');

INSERT INTO checkinout
    (id_medico, id_unidade, UUID, check_in, check_out, data)
VALUES
    (1, 1, '123e4567-e89b-12d3-a456-426614174000', '2023-10-01 08:00:00', '2023-10-01 17:00:00', '2023-10-01'),
    (2, 2, '123e4567-e89b-12d3-a456-426614174001', '2023-10-02 08:00:00', '2023-10-02 17:00:00', '2023-10-02'),
    (3, 3, '123e4567-e89b-12d3-a456-426614174002', '2023-10-03 08:00:00', '2023-10-03 17:00:00', '2023-10-03'),
    (4, 4, '123e4567-e89b-12d3-a456-426614174003', '2023-10-04 08:00:00', '2023-10-04 17:00:00', '2023-10-04');

INSERT INTO UNIDADE_ESPECIALIDADE
    (unidade_id, especialidade_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 3),
    (3, 4),
    (4, 1),
    (5, 2);

