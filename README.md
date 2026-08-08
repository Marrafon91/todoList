# 📝 TodoList — Gerenciador de Tarefas

[![Java](https://img.shields.io/badge/Java-25-ED8B00?style=for-the-badge\&logo=openjdk\&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-6DB33F?style=for-the-badge\&logo=springboot\&logoColor=white)](https://spring.io/projects/spring-boot)
[![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-4.0-6DB33F?style=for-the-badge\&logo=spring\&logoColor=white)](https://spring.io/projects/spring-data-jpa)
[![Hibernate](https://img.shields.io/badge/Hibernate-7.4-59666C?style=for-the-badge\&logo=hibernate\&logoColor=white)](https://hibernate.org/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-4169E1?style=for-the-badge\&logo=postgresql\&logoColor=white)](https://www.postgresql.org/)
[![H2](https://img.shields.io/badge/H2-Database-1E90FF?style=for-the-badge\&logo=h2\&logoColor=white)](https://www.h2database.com/)
[![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge\&logo=apachemaven\&logoColor=white)](https://maven.apache.org/)
[![JUnit 5](https://img.shields.io/badge/JUnit-5-25A162?style=for-the-badge\&logo=junit5\&logoColor=white)](https://junit.org/junit5/)
[![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-85EA2D?style=for-the-badge\&logo=swagger\&logoColor=black)](https://swagger.io/)

[![React](https://img.shields.io/badge/React-19-61DAFB?style=for-the-badge\&logo=react\&logoColor=black)](https://react.dev/)
[![TypeScript](https://img.shields.io/badge/TypeScript-5-3178C6?style=for-the-badge\&logo=typescript\&logoColor=white)](https://www.typescriptlang.org/)
[![Vite](https://img.shields.io/badge/Vite-7-646CFF?style=for-the-badge\&logo=vite\&logoColor=white)](https://vite.dev/)
[![Axios](https://img.shields.io/badge/Axios-1-5A29E4?style=for-the-badge\&logo=axios\&logoColor=white)](https://axios-http.com/)
[![CSS3](https://img.shields.io/badge/CSS3-3-1572B6?style=for-the-badge\&logo=css3\&logoColor=white)](https://developer.mozilla.org/en-US/docs/Web/CSS)

[![Docker](https://img.shields.io/badge/Docker-Container-2496ED?style=for-the-badge\&logo=docker\&logoColor=white)](https://www.docker.com/)
[![Railway](https://img.shields.io/badge/Railway-Backend-000000?style=for-the-badge\&logo=railway\&logoColor=white)](https://railway.com/)
[![Vercel](https://img.shields.io/badge/Vercel-Frontend-000000?style=for-the-badge\&logo=vercel\&logoColor=white)](https://vercel.com/)

[![GitHub](https://img.shields.io/badge/GitHub-Repositório-181717?style=for-the-badge\&logo=github\&logoColor=white)](https://github.com/Marrafon91/todoList)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Guilherme%20Marrafon-0A66C2?style=for-the-badge\&logo=linkedin\&logoColor=white)](https://www.linkedin.com/in/guilherme-marrafon/)
[![Aplicação](https://img.shields.io/badge/Aplicação-Online-000000?style=for-the-badge\&logo=vercel\&logoColor=white)](https://todo-list-five-alpha-25.vercel.app/)
[![API](https://img.shields.io/badge/API-Online-000000?style=for-the-badge\&logo=railway\&logoColor=white)](https://todolist-production-9e3f.up.railway.app/)
[![Swagger](https://img.shields.io/badge/Swagger-Documentação-85EA2D?style=for-the-badge\&logo=swagger\&logoColor=black)](https://todolist-production-9e3f.up.railway.app/swagger-ui/index.html)

---

## 🌐 Projeto Online

### 🖥️ Aplicação

👉 **[Acessar TodoList](https://todo-list-five-alpha-25.vercel.app/)**


### 📚 Documentação Swagger

👉 **[Acessar Swagger](https://todolist-production-9e3f.up.railway.app/swagger-ui/index.html)**

---

# 📸 Interface

## Desktop

![Desktop](docs/images/Print-full-screen.jpeg)

---

## Tablet

![Tablet](docs/images/Print-tablet.jpeg)

---

## Mobile

<p align="center">
  <img src="docs/images/Print-celular.jpeg" width="300"/>
</p>

---

## Diagrama UML

![UML](docs/images/Print-uml.png)

---

# 🎯 Objetivo

O **TodoList** é uma aplicação Full Stack desenvolvida com o objetivo de consolidar conhecimentos em desenvolvimento de aplicações web utilizando **Java, Spring Boot, React e TypeScript**.

O projeto foi desenvolvido seguindo uma arquitetura baseada em **API REST**, separando o backend e frontend em aplicações independentes.

Além da implementação das funcionalidades, o projeto também teve como objetivo colocar em prática conceitos importantes de desenvolvimento profissional, como:

* Arquitetura REST
* Programação Orientada a Objetos
* Separação de responsabilidades
* DTOs
* Bean Validation
* Tratamento global de exceções
* Persistência de dados
* Testes automatizados
* Documentação de API
* CORS
* Variáveis de ambiente
* Containerização com Docker
* Deploy em produção

---

# 🚀 Funcionalidades

A aplicação permite ao usuário:

* ✅ Cadastro de tarefas
* ✅ Atualização de tarefas
* ✅ Exclusão de tarefas
* ✅ Exclusão de todas as tarefas
* ✅ Marcar tarefas como concluídas
* ✅ Pesquisa por título
* ✅ Filtro por categoria
* ✅ Filtro por prioridade
* ✅ Filtro por status
* ✅ Dashboard com indicadores
* ✅ Sidebar com resumo das tarefas
* ✅ Organização por categorias
* ✅ Definição de prioridade
* ✅ Definição de data de vencimento
* ✅ Interface responsiva
* ✅ Seleção de categorias através de modal
* ✅ Criação de tarefas diretamente pelo frontend

---

# 🏗️ Arquitetura

```text
                         ┌──────────────────────┐
                         │      React 19        │
                         │    TypeScript        │
                         │        Vite          │
                         └──────────┬───────────┘
                                    │
                                  Axios
                                    │
                              HTTP / JSON
                                    │
                                    ▼
                         ┌──────────────────────┐
                         │    Spring Boot 4    │
                         │       REST API       │
                         └──────────┬───────────┘
                                    │
                              Spring Data JPA
                                    │
                               Hibernate
                                    │
                                    ▼
                         ┌──────────────────────┐
                         │     PostgreSQL       │
                         │       Railway        │
                         └──────────────────────┘
```

---

# 📦 Tecnologias

## Backend

* ☕ Java 25
* 🍃 Spring Boot 4.1.0
* Spring Data JPA
* Hibernate
* Bean Validation
* PostgreSQL
* H2 Database
* Maven
* Swagger / OpenAPI
* JUnit 5
* MockMvc
* JaCoCo

## Frontend

* ⚛️ React 19
* TypeScript
* Vite
* Axios
* CSS
* React Router

## Infraestrutura e Deploy

* 🐳 Docker
* 🚂 Railway
* ▲ Vercel
* GitHub

---

# 🗄️ Banco de Dados

## Produção

O banco de dados utilizado em produção é o **PostgreSQL**, hospedado através do Railway.

```text
React
  │
  ▼
Vercel
  │
  ▼
Spring Boot
  │
  ▼
Railway
  │
  ▼
PostgreSQL
```

## Desenvolvimento

Durante o desenvolvimento foram utilizados:

* PostgreSQL
* H2 Database

O H2 também foi utilizado para testes e desenvolvimento local.

---

# 🧪 Testes

O backend possui testes automatizados utilizando:

* JUnit 5
* Spring Boot Test
* MockMvc
* Mockito

Foram implementados testes envolvendo:

* Controllers
* Services
* Repositories

## 📊 Cobertura

* ✅ 75 testes automatizados
* ✅ 99% de cobertura utilizando JaCoCo

---

# 📚 Documentação da API

A API REST foi documentada utilizando **Swagger / OpenAPI**.

### Produção

👉 **[Swagger UI](https://todolist-production-9e3f.up.railway.app/swagger-ui/index.html)**

### Principais endpoints

```text
GET    /api/tasks
GET    /api/tasks/{id}
POST   /api/tasks
PUT    /api/tasks/{id}
DELETE /api/tasks/{id}

GET    /api/categories
GET    /api/categories/{id}
```

---

# 🔐 Configuração

O projeto utiliza variáveis de ambiente para configurações específicas de cada ambiente.

Exemplo:

```properties
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}

server.port=${PORT:8080}
```

# 🐳 Docker

O backend possui um `Dockerfile` para criação da imagem da aplicação.

Exemplo da estrutura:

```text
back-end/
│
├── Dockerfile
├── pom.xml
├── mvnw
├── mvnw.cmd
└── src/
```

O Docker é utilizado no processo de deploy do backend no Railway.

---

# ▶️ Como executar o projeto

## 1. Clonar o repositório

```bash
git clone https://github.com/Marrafon91/todoList.git
```

```bash
cd todoList
```

---

# ⚙️ Backend

Entre na pasta:

```bash
cd back-end
```

Execute:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

A API ficará disponível em:

```text
http://localhost:8080
```

Swagger:

```text
http://localhost:8080/swagger-ui/index.html
```

---

# 💻 Frontend

Entre na pasta:

```bash
cd front-end
```

Instale as dependências:

```bash
npm install
```

Execute o projeto:

```bash
npm run dev
```

O frontend será disponibilizado pelo Vite.

Normalmente:

```text
http://localhost:5173
```

---

# 🌐 Deploy

## Frontend — Vercel

O frontend está hospedado gratuitamente na **Vercel**.

👉 **[TodoList — Aplicação Online](https://todo-list-five-alpha-25.vercel.app/)**

```text
https://todo-list-five-alpha-25.vercel.app/
```
---

## Swagger

👉 **[Documentação da API](https://todolist-production-9e3f.up.railway.app/swagger-ui/index.html)**

```text
https://todolist-production-9e3f.up.railway.app/swagger-ui/index.html
```

---

# 📂 Estrutura do Projeto

```text
todoList/
│
├── back-end/
│   │
│   ├── src/
│   │   ├── main/
│   │   │   └── java/
│   │   │       └── io/github/marrafon91/todoList/
│   │   │
│   │   └── test/
│   │
│   ├── Dockerfile
│   ├── pom.xml
│   └── mvnw
│
├── front-end/
│   │
│   ├── src/
│   │   ├── components/
│   │   ├── models/
│   │   ├── pages/
│   │   ├── services/
│   │   └── ...
│   │
│   ├── package.json
│   ├── vite.config.ts
│   └── tsconfig.json
│
├── docs/
│   └── images/
│
├── LICENSE
└── README.md
```

---

# 🔄 Fluxo da Aplicação

```text
Usuário
   │
   ▼
React + TypeScript
   │
   │ Axios
   ▼
Spring Boot REST API
   │
   ├── Controllers
   │
   ├── Services
   │
   ├── DTOs
   │
   ├── Repositories
   │
   └── Entities
   │
   ▼
Hibernate / JPA
   │
   ▼
PostgreSQL
```

---

# 📈 Conhecimentos Aplicados

Durante o desenvolvimento foram aplicados conhecimentos de:

### Backend

* Java
* POO
* Spring Boot
* REST
* Spring Data JPA
* Hibernate
* DTO
* Bean Validation
* Exceptions
* Profiles
* CORS
* PostgreSQL
* H2
* Testes automatizados
* JaCoCo
* Swagger
* Docker

### Frontend

* React
* TypeScript
* Componentização
* Props
* Hooks
* Estados
* React Router
* Axios
* Consumo de API REST
* Tipagem
* Variáveis de ambiente
* Responsividade
* CSS

### Deploy

* GitHub
* Docker
* Railway
* Vercel
* Configuração de variáveis de ambiente
* Deploy contínuo através do GitHub

---

# 👨‍💻 Autor

## Guilherme Marrafon

Desenvolvedor **Full Stack em formação**, com foco no ecossistema **Java e Spring Boot**, estudando também **React e TypeScript** para desenvolvimento de aplicações completas.

### 🔗 Links

[![GitHub](https://img.shields.io/badge/GitHub-Marrafon91-181717?style=for-the-badge\&logo=github\&logoColor=white)](https://github.com/Marrafon91)

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Guilherme%20Marrafon-0A66C2?style=for-the-badge\&logo=linkedin\&logoColor=white)](https://www.linkedin.com/in/guilherme-marrafon/)

---

# ⭐ Projeto desenvolvido para fins de estudo, prática e evolução profissional.

Se este projeto foi útil ou interessante, considere deixar uma ⭐ no repositório!
