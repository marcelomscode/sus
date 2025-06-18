# Tech Challenger FIAP F5 - MS Unidade

Bem-vindo ao repositório do projeto "unidade", desenvolvido como parte do desafio tecnológico da FIAP. 
Este projeto visa implementar uma API para gestão de unidades de saude.

## 🎓 Projeto Acadêmico

Projeto de pós-graduação em **arquitetura e desenvolvimento JAVA** pela FIAP ALUNOS 5ADJT.

## 👨‍💻 Desenvolvedores

- Edson Antonio da Silva Junior
- Gabriel Ricardo dos Santos
- Luiz Henrique Romão de Carvalho
- Marcelo de Souza

## 💡 Tecnologias Utilizadas

![Java](https://img.shields.io/badge/Java-17-blue?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.4-brightgreen?style=for-the-badge)
![Maven](https://img.shields.io/badge/Maven-3.9.9-C71A36?style=for-the-badge&logo=apachemaven)
![MySql](https://img.shields.io/badge/MySQL-8.4-4479A1?style=for-the-badge&logo=mysql)
![Docker](https://img.shields.io/badge/Docker-27.5.1-2496ED?style=for-the-badge&logo=docker)
![Swagger](https://img.shields.io/badge/Swagger-3.0-85EA2D?style=for-the-badge&logo=swagger)

## Estrutura do projeto

```markdown
src
    ├── main
        ├── java
        │   └── fiap
        │   │   ├── SusApplication.java
        │   │   └── sus
        │   │       ├── api
        │   │           ├── controllers
        │   │           │   ├── CheckInOutController.java
        │   │           │   ├── EspecialidadeController.java
        │   │           │   └── UnidadeController.java
        │   │           ├── dto
        │   │           │   ├── MedicoResponse.java
        │   │           │   ├── checkinout
        │   │           │   │   ├── CheckInOutRequest.java
        │   │           │   │   └── CheckInOutResponse.java
        │   │           │   ├── especialidade
        │   │           │   │   ├── EspecialidadeRequest.java
        │   │           │   │   ├── EspecialidadeResponse.java
        │   │           │   │   ├── EspecialidadeUnidadeRequest.java
        │   │           │   │   └── NovaEspecialidadeRequest.java
        │   │           │   └── unidade
        │   │           │   │   ├── MedicosAtendendoUnidadeResponse.java
        │   │           │   │   ├── UnidadeRequest.java
        │   │           │   │   └── UnidadeResponse.java
        │   │           └── mappers
        │   │           │   ├── CheckInOutMapper.java
        │   │           │   ├── EspecialidadeDomainMapper.java
        │   │           │   ├── EspecialidadeMapperOld.java
        │   │           │   ├── MedicoMapper.java
        │   │           │   └── UnidadeDomainMapper.java
        │   │       ├── application
        │   │           └── usecases
        │   │           │   ├── checkinout
        │   │           │       ├── BuscaCheckInOutUseCase.java
        │   │           │       ├── CheckInUseCase.java
        │   │           │       └── CheckOutUseCase.java
        │   │           │   ├── especialidades
        │   │           │       ├── AtualizaEspecialidadeUseCase.java
        │   │           │       ├── BuscaEspecialidadesUseCase.java
        │   │           │       └── SalvaEspecialidadeUseCase.java
        │   │           │   ├── medicos
        │   │           │       └── BuscaInformacoesMedicoUseCase.java
        │   │           │   └── unidades
        │   │           │       ├── AtualizaUnidadeUseCase.java
        │   │           │       ├── BuscaMedicosAtendendoNaUnidadeUseCase.java
        │   │           │       ├── BuscaUnidadesUseCase.java
        │   │           │       ├── DeletaUnidadeUseCase.java
        │   │           │       └── SalvaUnidadeUseCase.java
        │   │       ├── config
        │   │           ├── CorsConfig.java
        │   │           ├── DotenvInitializer.java
        │   │           ├── OpenApiConfig.java
        │   │           └── WebConfig.java
        │   │       ├── domain
        │   │           ├── exceptions
        │   │           │   ├── CheckOutInException.java
        │   │           │   ├── DominioException.java
        │   │           │   ├── EspecialidadeException.java
        │   │           │   ├── MedicoException.java
        │   │           │   └── UnidadeException.java
        │   │           ├── model
        │   │           │   ├── CheckInOutDomain.java
        │   │           │   ├── EspecialidadesDomain.java
        │   │           │   ├── MedicoDomain.java
        │   │           │   └── UnidadeDomain.java
        │   │           └── repository
        │   │           │   ├── checkinout
        │   │           │       ├── BuscaCheckInOutDomainRepository.java
        │   │           │       ├── CheckInDomainRepository.java
        │   │           │       └── CheckOutDomainRepository.java
        │   │           │   ├── especialidade
        │   │           │       ├── BuscaEspecialidadesRepository.java
        │   │           │       └── EspecialidadeRepository.java
        │   │           │   ├── medico
        │   │           │       └── BuscaInformacoesMedicoRepository.java
        │   │           │   └── unidade
        │   │           │       └── UnidadeDomainRepository.java
        │   │       ├── infrastructure
        │   │           ├── exceptionhandler
        │   │           │   └── RestResponseEntityExceptionHandler.java
        │   │           ├── external
        │   │           │   └── MedicoFeignClient.java
        │   │           ├── mappers
        │   │           │   ├── CheckinOutPersistenceMapper.java
        │   │           │   ├── EspecialidadePersistenceMapper.java
        │   │           │   └── UnidadePersistenceMapper.java
        │   │           ├── persistence
        │   │           │   ├── CheckInOutPersistence.java
        │   │           │   ├── EspecialidadesPersistence.java
        │   │           │   └── UnidadePersistence.java
        │   │           └── repository
        │   │           │   ├── impl
        │   │           │       ├── checkinout
        │   │           │       │   ├── BuscaCheckInOutRepositoryImpl.java
        │   │           │       │   ├── CheckInDomainRepositoryImpl.java
        │   │           │       │   └── CheckOutDomainRepositoryImpl.java
        │   │           │       ├── especialidades
        │   │           │       │   ├── BuscaEspecialidadesRepositoryImpl.java
        │   │           │       │   └── EspecialidadesRepositoryImpl.java
        │   │           │       ├── medico
        │   │           │       │   └── BuscaInformacoesMedicoRepositoryImpl.java
        │   │           │       └── unidade
        │   │           │       │   └── UnidadeRepositoryImpl.java
        │   │           │   └── jpa
        │   │           │       ├── CheckInOutJpaRepository.java
        │   │           │       ├── EspecialidadesJpaRepository.java
        │   │           │       └── UnidadeJpaRepository.java
        │   │       └── uteis
        │   │           ├── DatasConversao.java
        │   │           └── variaveis
        │   │               └── ConstantesGlobais.java
        └── resources
        │   ├── application-dev.properties
        │   └── application.properties
    └── test
        ├── java
            └── fiap
            │   ├── Helper.java
            │   ├── api
            │       └── controllers
            │       │   ├── checkinout
            │       │       └── CheckInOutControllerIT.java
            │       │   ├── especialidades
            │       │       └── EspecialidadeControllerIT.java
            │       │   └── unidades
            │       │       └── UnidadeControllerTestIT.java
            │   ├── application
            │       └── usecase
            │       │   ├── checkinout
            │       │       ├── BuscaCheckInOutUseCaseTest.java
            │       │       ├── CheckInUseCaseTest.java
            │       │       └── CheckOutUseCaseTest.java
            │       │   ├── especialidades
            │       │       ├── integrados
            │       │       │   ├── AtualizaEspecialidadeUseCaseIT.java
            │       │       │   ├── BuscaEspecialidadesUseCaseTestIT.java
            │       │       │   └── SalvaEspecialidadeUseCaseIT.java
            │       │       └── unitarios
            │       │       │   ├── AtualizaEspecialidadeUseCaseTest.java
            │       │       │   ├── BuscaEspecialidadesUseCaseTest.java
            │       │       │   └── SalvaEspecialidadeUseCaseTest.java
            │       │   └── unidades
            │       │       ├── SalvaUnidadeUseCaseITTest.java
            │       │       └── SalvaUnidadeUseCaseTest.java
            │   ├── bdd
            │       ├── CucumberSpringConfiguration.java
            │       └── CucumberTest.java
            │   └── infrastructure
            │       └── repository
            │           ├── impl
            │               ├── checkinout
            │               │   ├── BuscaCheckInOutRepositoryImplTest.java
            │               │   ├── CheckInDomainRepositoryImplTest.java
            │               │   └── CheckOutDomainRepositoryImplTest.java
            │               ├── especialidades
            │               │   ├── BuscaEspecialidadesRepositoryImplTest.java
            │               │   ├── BuscaEspecialidadesRepositoryImplTestIT.java
            │               │   ├── EspecialidadesRepositoryImplTest.java
            │               │   └── EspecialidadesRepositoryImplTestIT.java
            │               ├── medico
            │               │   └── BuscaInformacoesMedicoRepositoryImplTest.java
            │               └── unidade
            │               │   ├── UnidadeJpaRepositoryImplITTest.java
            │               │   └── UnidadeJpaRepositoryImplTest.java
            │           └── jpa
            │               ├── checkinout
            │                   ├── CheckInOutJpaRepositoryTest.java
            │                   └── CheckInOutJpaRepositoryTestIT.java
            │               ├── especialidades
            │                   ├── EspecialidadesJpaRepositoryTest.java
            │                   └── EspecialidadesJpaRepositoryTestIT.java
            │               └── unidade
            │                   ├── UnidadeJpaRepositoryIT.java
            │                   └── UnidadeJpaRepositoryTest.java
        └── resources
            ├── application.properties
            ├── db
                └── test
                │   ├── data.sql
                │   └── schema.sql
            ├── features
                └── unidade.feature
            ├── junit-platform.properties
            └── schemas
                ├── unidades.invalida.schema.json
                └── unidades.schema.json
```

⚙️ Configurando Variáveis de Ambiente
Deve-se criar um arquivo .env na raiz do projeto contendo as variáveis:
| Variável | Descrição                 | Exemplo         |
| --- |---------------------------|-----------------|
| `PORT_SERVER` | Porta do servidor | `8032`     |
| `DATA_BASE_URL` | url base da base de dados | `jdbc:postgresql:`     |
| `USER_NAME` | Usuário da base de dados | `usuario`     |
| `PASSWORD` | password | `usuari123`     |
| `CLASS_NAME` | Class name do tipo da base de dados | `org.postgresql.Driver`     |
| `HIBERNATE_DIALECT` | dialect do tipo da base de dados | `org.hibernate.dialect.PostgreSQLDialect`     |

## ▶️ Como Executar o Projeto

Para executar o projeto localmente, siga as etapas abaixo:

1. **Clone o repositorio:**

    ```bash
    git clone <https://github.com/Power-Rangers-FIAP/tc-f5-ms-ms-unidade.git>
    
    ```

2. **Navegue até o diretorio do projeto:**

    ```bash
    cd tc-f5-ms-unidade
    
    ```

3. **Compile o projeto com Maven:**

    ```bash
    mvn clean install -U
    
    ``` 

4. **Inicie a aplicação localmente:**

    ```bash
    mvn spring-boot:run
    
    ```

## 🧪 Como executar testes

- **Para testes de unidade:**

    ```bash
    mvn test
    
    ```

- **Para testes de integração:**

    ```bash
    mvn test -P integration-test
    
    ``` 

## 🧪 API Endpoint

A API pode ser executada e testada usando o Swagger. A documentação está disponivel na URL:
[`Swagger`](http://localhost:8081/swagger-ui/index.html)

## Contribuindo

Contribuições são bem-vindas! Para contribuir com o projeto, por favor siga estes passos:

1. Faça um fork do repositório.
2. Crie uma branch para sua feature ou correção (`git checkout -b feature/nova-feature`).
3. Faça commit das suas mudanças (`git commit -am 'Adiciona nova feature'`).
4. Envie suas alterações para o repositório (`git push origin feature/nova-feature`).
5. Abra um pull request.
   
## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).
