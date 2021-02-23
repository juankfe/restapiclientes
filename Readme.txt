REST API Clientes

A API permite adicionar, editar e listar clientes.

Código disponível em:
https://github.com/juankfe/restapiclientes

A aplicação foi hospedada no Heroku, onde encontra-se disponível para acesso.
http://jk-restapiclientes.herokuapp.com/clientes

Documentação para uso da API:

Criação de cliente:
https://documenter.getpostman.com/view/14552178/TWDZJGMe

Atualização de clientes:
https://documenter.getpostman.com/view/14552178/TWDZJGHK

Listagem de clientes (paginado):
https://documenter.getpostman.com/view/14552178/TWDZJGMf


Processo criativo

A API foi desenvolvida em Java com Spring Boot.
Foi criada uma classe modelo Clientes com atributos básicos (nome, idade, email).
Essa classe utiliza-se do Spring Data/JPA para criar a tabela Clientes automaticamente no banco de dados.
A classe ClienteController intercepta as chamadas às urls da API, processa os dados e retorna o resultado em formato json.
Nesse controller foram criados endpoints para cadastro, atualização e listagem de clientes.
As operações podem ser feita com o objeto por inteiro ou de forma granular (apenas nome, idade ou email).
Utilizou-se o Repository do Spring Data para comunicação com bd PostgreSQL.
Todas as operações são cobertas por Unit Tests (mocados com Mockito) e testes de integração que testam a operação por completo incluindo o acesso ao bd.
Adicionalmente criou-se tratamento de exceções com erros customizados (package error).
Esses erros customizados são apresentados em diferentes operações da API.
Por exemplo, quando registro não é encontrado (ClienteAPINotFoundException), erro no request (ClienteAPIBadRequestException), outros erros (ClienteAPIException).
A classe handler especifica o comportamento de cada uma desses exceções e mais algumas.

