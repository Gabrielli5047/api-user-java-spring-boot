# Dependências de desenvolvimento

- **_Lombok_** (consulte o guia de instalação para sua IDE)
  - https://projectlombok.org/

# Para utilizar a api

    A aplicação será iniciada na porta 8086

### Build

    mvn clean compile package -Dmaven.test.skip -DskipTests -Dmaven.javadoc.skip=true

### Iniciar a aplicação

    java -jar target/group5-0.0.1-SNAPSHOT.jar

### Variáveis de ambiente:

**DB**

```
DATABASE_USER
DATABASE_PASSWORD
DATABASE_URL
```

# Status code

- 200 (success) 😀
- 201 (created) 😀
- 400 (bad request) 😱
- 401 (unauthorized)😱

```json
// exemplo de resposta de erro
{
  "status": "BAD_REQUEST",
  "code": 400,
  "message": "Erro de validação.",
  "timestamp": 1654264708196,
  "path": "/users",
  "validationErrors": [
    {
      "message": "Este CPF já está em uso.",
      "field": "cpf"
    },
    {
      "message": "Este email já está em uso. Tente outro.",
      "field": "email"
    }
  ]
}
```

```
 🚩 OBRIGATÓRIO O TOKEN NO HEADER DE QUALQUER REQUISIÇÃO
```

# Endpoints

## Cadastro de usuário

## Post: /users

**Requisitos:**

- body
  - name
  - email
  - cpf
  - phone
  - birthdate

```json
// Exemplo de requisição

{
  "name": "Lucas Kennedy",
  "email": "lucas@email.com",
  "cpf": "12345678901", // ou 123.456.789-01
  "phone": "11 9 9999-9999",
  "birthdate": "1994-11-11"
}
```

## Consulta de usuários

## Get: /users?page=1&limit=20

**Requisitos:**

- query params
  - page ==> default (0)
  - limit ==> default (20)

```json
// Exemplo de resposta

{
	"totalPages": 1,
	"currentPage": 0,
	"totalElements": 1,
	"limit": 10,
	"content": [
		{
			"id": 10,
			"name": "lucas",
			"cpf": "86612160012",
			"email": "lucaskennedy@email.com",
			"phone": null,
			"birthdate": null
		}
	],
	"isFirstPage": true,
	"isLastPage": true,
	"isEmpty": false
}
```

## Consulta de usuário pelo id

## Get: /users/:id

**Requisitos:**

- path variable
  - id

```json
// Exemplo de resposta

{
  "name": "Lucas Kennedy",
  "email": "lucas@email.com",
  "cpf": "12345678901",
  "phone": "11 9 9999-9999",
  "birthdate": "1994-11-11"
}
```

## Edita usuário pelo id

## Put: /users/:id

**Requisitos:**

- path variable
  - id
- body
  - name
  - email
  - cpf
  - phone
  - birthdate

```json
// Exemplo de resposta

{
  "name": "Lucas Kennedy",
  "email": "lucas@email.com",
  "cpf": "12345678901",
  "phone": "11 9 9999-9999",
  "birthdate": "1994-11-11"
}
```

## Deleta usuário pelo id

## Delete: /users/:id
Usuário só poderá ser excluido caso não possua nenhum pedido cadastrado.

**Requisitos:**

- path variable
  - id
