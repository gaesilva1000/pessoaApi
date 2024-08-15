# pessoaApi

## Descrição

A aplicação pessoaApi é um serviço RESTful desenvolvido com Spring Boot, responsável pelo gerenciamento de pessoas e integração com um serviço de boletos. A API permite criar, buscar, atualizar e excluir registros de pessoas, além de buscar boletos associados a uma pessoa específica.

## Funcionalidades

- *Gerenciamento de Pessoas*:
  - Criar uma nova pessoa.
  - Buscar todas as pessoas.
  - Buscar uma pessoa pelo ID.
  - Atualizar informações de uma pessoa existente.
  - Excluir uma pessoa pelo ID.

- *Integração com Serviço de Boletos*:
  - Buscar boletos associados a uma pessoa pelo ID.

## Tecnologias Utilizadas

- *Spring Boot*: Framework para desenvolvimento de aplicações Java.
- *Spring Cloud OpenFeign*: Cliente HTTP para comunicação com o serviço de boletos.
- *Spring Data JPA*: Mapeamento objeto-relacional e acesso ao banco de dados.
- *Lombok*: Biblioteca para reduzir o boilerplate code.
- *H2 Database*: Banco de dados em memória para desenvolvimento e testes.
- *JasperReports*: Biblioteca para geração de relatórios.
- *Swagger (Springdoc OpenAPI)*: Documentação da API.

## Dependências

As principais dependências utilizadas são:
- spring-boot-starter-web
- spring-cloud-starter-openfeign
- spring-boot-starter-data-jpa
- lombok
- h2
- jasperreports
- springdoc-openapi-starter-webmvc-ui

## Configuração

### Banco de Dados

A aplicação utiliza o banco de dados H2 para desenvolvimento e testes. O banco de dados é inicializado automaticamente com o Spring Boot.

### Configuração do Feign Client

O BoletoClient está configurado para se comunicar com o serviço de boletos na URL http://localhost:8181/boletos. Certifique-se de que o serviço de boletos está em execução nessa URL.

## Endpoints

### Pessoas

- *POST /api/pessoas*
  - Cria uma nova pessoa.
  - *Request Body*: Pessoa
  - *Response*: Pessoa criada com status 201 Created.

- *GET /api/pessoas*
  - Retorna a lista de todas as pessoas.
  - *Response*: Lista de Pessoa.

- *GET /api/pessoas/{id}*
  - Retorna uma pessoa pelo ID.
  - *Path Variable*: id - ID da pessoa.
  - *Response*: Pessoa.

- *PUT /api/pessoas/{id}*
  - Atualiza uma pessoa existente.
  - *Path Variable*: id - ID da pessoa.
  - *Request Body*: Pessoa com os dados atualizados.
  - *Response*: Pessoa atualizada.

- *DELETE /api/pessoas/{id}*
  - Exclui uma pessoa pelo ID.
  - *Path Variable*: id - ID da pessoa.
  - *Response*: Status 204 No Content.

### Boletos

- *GET /api/pessoas/{id}/boletos*
  - Busca boletos associados a uma pessoa pelo ID.
  - *Path Variable*: id - ID da pessoa.
  - *Response*: Lista de Boleto.

## Executando a Aplicação

Para executar a aplicação, siga estes passos:

1. Clone o repositório:
   ```bash
   git clone <URL_DO_REPOSITORIO>