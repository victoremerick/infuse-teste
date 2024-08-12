# API de Gestão de Pedidos

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- Lombok
- MySQL
- JPA/Hibernate
- Flyway
- Docker
- Swagger

## Estrutura do Projeto

### Camadas

- **Controller:** Camada responsável por expor os endpoints da API.
- **Service:** Camada responsável por implementar as regras de negócio.
- **Repository:** Camada responsável por interagir com o banco de dados.
- **Model:** Classes que representam as entidades e os dados manipulados pela aplicação.

### Tratamento de Exceções

Foi implementado um `ControllerAdvice` para tratamento centralizado de erros, garantindo respostas consistentes e informativas.

## Como usar

Acessar a pasta devops e executar:

````shell
docker-compose up
````

Ele irá configurar um ambiente pronto para uso com banco de dados, load balance e duas instâncias da aplicação.

Os endpoints presentes:

#### Criar Pedidos

````
POST /api/pedidos

Content-type: application/xml
Body:
<List>
    <Pedido>
        <numeroControle>1234456</numeroControle>
        <dataCadastro>2024-08-11</dataCadastro>
        <nome>Produto X</nome>
        <valor>100.50</valor>
        <quantidade>6</quantidade>
        <codigoCliente>1</codigoCliente>
    </Pedido>
    <Pedido>
        <numeroControle>7890512</numeroControle>
        <nome>Produto Y</nome>
        <valor>200.75</valor>
        <codigoCliente>2</codigoCliente>
    </Pedido>
</List>

Content-type: application/json
Body:
[
    {
        "numeroControle": "123456",
        "dataCadastro": "2024-08-11",
        "nome": "Produto X",
        "valor": 100.50,
        "quantidade": 6,
        "codigoCliente": 1
    },
    {
        "numeroControle": "789012",
        "nome": "Produto Y",
        "valor": 200.75,
        "codigoCliente": 2
    }
]
````

#### Buscar Pedidos

````
GET /api/pedidos?numeroControle=&dataCadastro=
Accept: application/xml
Response:

<List>
    <item>
        <numeroControle>123456</numeroControle>
        <dataCadastro>2024-08-11</dataCadastro>
        <nome>Produto X</nome>
        <valor>100.50</valor>
        <quantidade>6</quantidade>
        <codigoCliente>1</codigoCliente>
        <valorTotal>572.85</valorTotal>
    </item>
    <item>
        <numeroControle>789012</numeroControle>
        <dataCadastro>2024-08-11</dataCadastro>
        <nome>Produto Y</nome>
        <valor>200.75</valor>
        <quantidade>1</quantidade>
        <codigoCliente>2</codigoCliente>
        <valorTotal>200.75</valorTotal>
    </item>
</List>

Accept: application/json
Response:

[
    {
        "numeroControle": "123456",
        "dataCadastro": "2024-08-11",
        "nome": "Produto X",
        "valor": 100.50,
        "quantidade": 6,
        "codigoCliente": 1,
        "valorTotal": 572.85
    },
    {
        "numeroControle": "789012",
        "dataCadastro": "2024-08-11",
        "nome": "Produto Y",
        "valor": 200.75,
        "quantidade": 1,
        "codigoCliente": 2,
        "valorTotal": 200.75
    },
]
````
