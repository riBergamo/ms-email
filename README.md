# ms-email

Microservico para envio de e-mails usando Spring Boot, PostgreSQL e RabbitMQ.

O projeto recebe dados de e-mail por API REST ou por fila RabbitMQ, tenta enviar a mensagem usando SMTP do Gmail e salva o resultado no banco com status `SENT` ou `ERROR`.

## Tecnologias

- Java 17
- Spring Boot 2.7.11
- Spring Web
- Spring Data JPA
- Spring Mail
- Spring AMQP
- PostgreSQL
- RabbitMQ
- Docker Compose
- Lombok

## Como rodar o ambiente local

Na raiz do projeto, suba o PostgreSQL e o RabbitMQ:

```bash
docker compose up -d
```

Servicos disponiveis:

- PostgreSQL: `localhost:5432`
- RabbitMQ: `localhost:5672`
- Painel RabbitMQ: `http://localhost:15672`

Credenciais do RabbitMQ:

```text
usuario: guest
senha: guest
```

Credenciais do PostgreSQL:

```text
database: email-db
usuario: postgres
senha: postgres
```

## Variaveis de ambiente

Antes de iniciar a aplicacao, configure as credenciais do Gmail:

```bash
MAIL_USERNAME=seu-email@gmail.com
MAIL_PASSWORD=sua-senha-de-app
```

No IntelliJ, configure em:

```text
Run > Edit Configurations > Environment variables
```

Exemplo:

```text
MAIL_USERNAME=seu-email@gmail.com;MAIL_PASSWORD=sua-senha-de-app
```

Use uma senha de app do Google, nao a senha normal da conta.

## Como rodar a aplicacao

Entre na pasta do modulo:

```bash
cd email
```

Rode com Maven:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

A aplicacao sobe em:

```text
http://localhost:8080
```

## Endpoint

Enviar e-mail:

```http
POST /sending-email
```

Exemplo de corpo da requisicao:

```json
{
  "ownerRef": "user-123",
  "emailFrom": "seu-email@gmail.com",
  "emailTo": "destino@email.com",
  "subject": "Teste de envio",
  "text": "Mensagem enviada pelo microservico de email."
}
```

Resposta esperada:

```http
201 Created
```

## RabbitMQ

A aplicacao tambem escuta mensagens na fila:

```text
ms.email
```

Configuracao usada:

```properties
spring.rabbitmq.addresses=amqp://guest:guest@localhost:5672
spring.rabbitmq.queue=ms.email
```

## Banco de dados

A aplicacao usa PostgreSQL e cria/atualiza as tabelas automaticamente com:

```properties
spring.jpa.hibernate.ddl-auto=update
```

A entidade principal e salva na tabela:

```text
tb_email
```

## Observacoes de seguranca

Nao suba senhas reais no GitHub. O projeto usa variaveis de ambiente para as credenciais do Gmail:

```properties
spring.mail.username=${MAIL_USERNAME}
spring.mail.password=${MAIL_PASSWORD}
```

Se alguma senha ja foi commitada antes, revogue essa senha e gere uma nova.
