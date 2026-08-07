# 📝 TodoList — Gerenciador de Tarefas

![Java](https://img.shields.io/badge/Java-25-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.1.0-6DB33F)
![React](https://img.shields.io/badge/React-19-61DAFB)
![TypeScript](https://img.shields.io/badge/TypeScript-5-blue)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-336791)
![JUnit](https://img.shields.io/badge/JUnit-5-green)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-85EA2D)
![License](https://img.shields.io/badge/license-MIT-blue)

Aplicação **Full Stack** para gerenciamento de tarefas, desenvolvida com **Java + Spring Boot** no backend e **React + TypeScript** no frontend.

O projeto foi criado para consolidar conhecimentos em desenvolvimento Full Stack utilizando uma arquitetura moderna baseada em **API REST**, aplicando boas práticas de organização de código, testes automatizados, documentação, banco de dados relacional e desenvolvimento de interfaces responsivas.

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

O principal objetivo deste projeto foi desenvolver uma aplicação Full Stack completa para colocar em prática conceitos estudados durante a formação em Java e React, tais como:

- Arquitetura REST
- Programação Orientada a Objetos
- Spring Boot
- Spring Data JPA
- DTOs
- Bean Validation
- Tratamento global de exceções
- PostgreSQL
- H2 Database
- React
- TypeScript
- Axios
- Componentização
- Responsividade
- Testes automatizados
- Documentação Swagger/OpenAPI

---

# 🚀 Funcionalidades

A aplicação permite:

- ✅ Cadastro de tarefas
- ✅ Atualização de tarefas
- ✅ Exclusão de tarefas
- ✅ Exclusão de todas as tarefas
- ✅ Marcar tarefas como concluídas
- ✅ Pesquisa por título
- ✅ Filtro por categoria
- ✅ Filtro por prioridade
- ✅ Filtro por status
- ✅ Dashboard com indicadores
- ✅ Sidebar com resumo das tarefas
- ✅ Organização por categorias
- ✅ Definição de prioridade
- ✅ Definição de data de vencimento
- ✅ Interface responsiva

---

# 🏗 Arquitetura

```text
                React + TypeScript
                       │
                  Axios (HTTP)
                       │
                  API REST JSON
                       │
                Spring Boot 4
                       │
               Spring Data JPA
                       │
                  PostgreSQL
```

---

# 📦 Tecnologias

## Backend

- Java 25
- Spring Boot 4
- Spring Data JPA
- Hibernate
- PostgreSQL
- H2 Database
- Bean Validation
- Swagger OpenAPI

## Frontend

- React 19
- TypeScript
- Vite
- Axios
- CSS

## Testes

- JUnit 5
- Spring Boot Test
- MockMvc
- JaCoCo

---

# 🗄 Banco de Dados

## Desenvolvimento

- PostgreSQL 17

## Testes

- H2 Database

---

# 🧪 Testes

O backend possui testes automatizados implementados utilizando:

- JUnit 5
- Spring Boot Test
- MockMvc

Foram implementados testes para:

- Controllers
- Services
- Repositories

### Cobertura

- ✔ 75 testes automatizados
- ✔ 99% de cobertura utilizando JaCoCo

---

# 📚 Documentação da API

A API foi documentada utilizando **Swagger/OpenAPI**.

Após iniciar a aplicação:

```text
http://localhost:8080/swagger-ui/index.html
```

Após o deploy:

```text
https://SEU-BACKEND/swagger-ui/index.html
```

---

# ▶ Como executar o projeto

## Clonar o repositório

```bash
git clone https://github.com/Marrafon91/todoList.git
```

---

## Backend

```bash
cd backend

mvn spring-boot:run
```

---

## Frontend

```bash
cd frontend

npm install

npm run dev
```

---

# 🌐 Deploy

## Frontend

> Em breve

```
https://SEU-FRONTEND.vercel.app
```

## Backend

> Em breve

```
https://SEU-BACKEND.up.railway.app
```

---

# 📂 Estrutura do projeto

```text
todoList
│
├── backend
│   ├── src
│   ├── pom.xml
│   └── ...
│
├── frontend
│   ├── src
│   ├── package.json
│   └── ...
│
└── README.md
```

---

# 👨‍💻 Autor

## Guilherme Marrafon

Desenvolvedor Full Stack em formação, apaixonado por tecnologia e focado no ecossistema Java.

### GitHub

https://github.com/Marrafon91

### LinkedIn

> Adicione seu LinkedIn aqui.

---

# ⭐ Projeto desenvolvido para fins de estudo, prática e evolução profissional.
