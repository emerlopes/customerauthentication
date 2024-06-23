# Documentação da API - Nome do Micro Serviço

## Endpoints

| Método HTTP | URL                   | Descrição                  | Parâmetros de Entrada              | Resposta                                                                                                                                                                                                          |
|-------------|-----------------------|----------------------------|------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| POST        | /users/register-guest | Cria um novo usuário guest | Objeto com `username` e `password` | 204 No Content                                                                                                                                                                                                    |
| POST        | /users/register-user  | Cria um novo usuário comum | Objeto com `username` e `password` | 204 No Content                                                                                                                                                                                                    |
| POST        | /users/register-admin | Cria um novo usuário admin | Objeto com `username` e `password` | 204 No Content                                                                                                                                                                                                    |
| GET         | /users                | Retorna todos os usuários  | Nenhum                             | `{"data": [{"id": "755d0d95-6851-46fd-93c4-86e72a894d8d", "login": "user_guest", "password": "$2a$10$.tr8M.wu/bPcFp4FWeINee2JPMt5kG341R4JTL/AnxN5.Xrg2P2uG", "role": "GUEST"}]}`                                  |
| POST        | /auth/login           | Faz a autenticação         | Objeto com `username` e `password` | `{"data": {"username": "user", "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJBUEkiLCJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiW1VTRVJdIl0sImV4cCI6MTcxOTE1OTAyOX0.VtoTA4zXnjU1G6rxEwdQm2-zi8C_3yud3wn0qO-e-Ks"}}` |

## Exemplos de Estruturas de Parâmetros

### /users/register-guest, /users/register-user, /users/register-admin (POST)

```json
{
  "username": "user",
  "password": "password"
}
```

### /auth/login (POST)

```json
{
  "data": {
    "username": "user_admin",
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJBUEkiLCJzdWIiOiJ1c2VyX2FkbWluIiwicm9sZXMiOlsiW0FETUlOLCBVU0VSXSJdLCJleHAiOjE3MTkxNTkwMzJ9.WauOsKHgidAedJtMjsfSFUyMUB1D-KmQsazLBLLD278"
  }
}
```
