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
── src
    ├── main
        ├── java
        │   └── fiap
        │   │   ├── SusApplication.java
        │   │   └── sus
        │   │       ├── api
        │   │           ├── controllers
        │   │           ├── dto
        │   │           │   ├── checkinout
        │   │           │   ├── especialidade
        │   │           │   └── unidade
        │   │           └── mappers
        │   │       ├── application
        │   │           └── usecases
        │   │           │   ├── checkinout
        │   │           │   ├── especialidades
        │   │           │   ├── medicos
        │   │           │   └── unidades
        │   │       ├── config
        │   │       ├── domain
        │   │           ├── exceptions
        │   │           ├── model
        │   │           └── repository
        │   │           │   ├── checkinout
        │   │           │   ├── especialidade
        │   │           │   ├── medico
        │   │           │   └── unidade
        │   │       ├── infrastructure
        │   │           ├── exceptionhandler
        │   │           ├── external
        │   │           ├── mappers
        │   │           ├── persistence
        │   │           └── repository
        │   │           │   ├── impl
        │   │           │       ├── checkinout
        │   │           │       ├── especialidades
        │   │           │       ├── medico
        │   │           │       └── unidade
        │   │           │   └── jpa
        │   │       └── uteis
        │   │           └── variaveis
```

⚙️ Configurando Variáveis de Ambiente
Deve-se criar um arquivo .env na raiz do projeto contendo as variáveis:
| Variável | Descrição                 | Exemplo         |
| --- |---------------------------|-----------------|
| `DB_USER` | Usuário do servidor atals | `usuari123`     |

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

