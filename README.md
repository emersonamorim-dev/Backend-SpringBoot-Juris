# Backend SpringBoot Juris - Aplicação de Gerenciamento Jurídico

Codificação em Java com SpringBoot de uma aplicação de gerenciamento jurídico que permite ao usuário gerenciar contratos, petições, processos, prazos e escritórios.
Implementação da classe DbSeeder é responsável por preencher os bancos de dados com dados de exemplo. Ela utiliza repositórios do Spring Data para salvar os dados no banco de dados. Alguns dados de exemplo incluem usuários, clientes, contratos, escritórios, prazos, petições e processos.

Tecnologias Utilizadas
- Spring Boot
- PostgreSQL
- RabbitMQ
- Docker

Pré-requisitos 
- Docker 
- Docker Compose

Execute o seguinte comando para criar as imagens Docker da aplicação e do banco de dados: docker-compose build

Execute o seguinte comando para iniciar os contêineres: docker-compose up

Agora a aplicação está executando e pode ser acessada no endereço http://localhost:8080.

Endpoints
| /contratos: | endpoints para gerenciar contratos |

/peticoes: endpoints para gerenciar petições
/processos: endpoints para gerenciar processos
/prazos: endpoints para gerenciar prazos
/escritorios: endpoints para gerenciar escritórios

Exemplo de Uso Contratos

Criar um contrato:

POST
http://localhost:8080/contratos 
{ "descricao": "Contrato de Prestação de Serviços", "dataInicio": "2022-01-01", "dataFim": "2023-01-01", "valor": 5000.00, "escritorioId": 1 } Buscar um contrato por ID:

GET
http://localhost:8080/contratos/1

Atualizar um contrato:

PUT
http://localhost:8080/contratos/1 
{ "descricao": "Contrato de Prestação de Serviços", "dataInicio": "2022-01-01", "dataFim": "2024-01-01", "valor": 6000.00, "escritorioId": 1 } Excluir um contrato:

DELETE
http://localhost:8080/contratos/1 Petições Criar uma petição:

POST
http://localhost:8080/peticoes 
{ "descricao": "Petição Inicial", "dataCriacao": "2022-01-01", "dataVencimento": "2022-02-01", "processoId": 1 }

Buscar uma petição por ID:

GET http://localhost:8080/peticoes/1

Atualizar uma petição:

PUT http://localhost:8080/peticoes/1 
{ "descricao": "Petição Inicial", "dataCriacao": "2022-01-01", "dataVencimento": "2022-03-01", "processoId": 1 }

Excluir uma petição:
DELETE http://localhost:8080/peticoes/1
