# Microserviço de Autenticação de Login - `customerauthentication`

## Visão Geral

Este repositório contém o microserviço de autenticação de login desenvolvido para uma aplicação de e-commerce. O serviço
permite que clientes se cadastrem e façam login na plataforma, gerenciando suas sessões e permissões de acesso com base
em roles.

## Funcionalidades

- Registro de novos clientes
- Login de clientes existentes
- Geração e validação de tokens JWT
- Proteção de endpoints com base em roles
- Consulta de usuários cadastrados (apenas para administradores)

## Estrutura do Projeto

A estrutura do projeto segue o padrão de arquitetura limpa, dividida em camadas de aplicação, domínio e infraestrutura:

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.3.1
- Spring Security
- Spring Data JPA
- JWT (JSON Web Token) - versão 4.4.0
- H2 Database (para testes)
- Gradle
- Lombok

## Dependências

```gradle
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-security'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation group: 'com.auth0', name: 'java-jwt', version: '4.4.0'
    implementation 'org.springframework.boot:spring-boot-starter-validation'

    compileOnly 'org.projectlombok:lombok'
    runtimeOnly 'com.h2database:h2'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testImplementation 'org.springframework.security:spring-security-test'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}

```

## Configuração e Execução

### Pré-requisitos

- JDK 17 ou superior
- Gradle 6.8 ou superior

### Configuração

1. Clone o repositório:
    ```bash
    git clone https://github.com/suaempresa/customerauthentication.git
    cd customerauthentication
    ```

2. Execute o projeto:
    ```bash
    ./gradlew bootRun
    ```

### Endpoints

A API oferece os seguintes endpoints:

- `POST /auth/register` - Registro de novos clientes
- `POST /auth/login` - Autenticação de clientes
- `GET /users` - Consulta de usuários cadastrados (necessita role ADMIN)

### Exemplo de Requisição

**Registro de Cliente**

```bash
curl -X POST http://localhost:8080/auth/register -H "Content-Type: application/json" -d '{
  "username": "user",
  "password": "password123",
  "role": "USER"
}'
```

**Login de Cliente**

```bash
curl -X POST http://localhost:8080/auth/login -H "Content-Type: application/json" -d '{
  "username": "user",
  "password": "password123"
}'
```

**Consulta de Usuários (necessita role ADMIN)**

```bash
curl -X GET http://localhost:8080/users -H "Authorization: Bearer seu_token_jwt_aqui"
```




