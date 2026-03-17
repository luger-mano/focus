# Focus — Business Metrics & Insights SaaS
![Java](https://img.shields.io/badge/Java-000?logo=openjdk&logoColor=fff&style=flat)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?logo=springboot&logoColor=fff&style=flat)
![Docker](https://img.shields.io/badge/Docker-2496ED?logo=docker&logoColor=fff&style=flat)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?logo=postgresql&logoColor=fff&style=flat)
![Redis](https://img.shields.io/badge/-Redis-DC382D?logo=Redis&logoColor=FFF)
![SOLID](https://img.shields.io/badge/Solid-2C4F7C?logo=solid&logoColor=fff&style=flat)
![DDD](https://img.shields.io/badge/Architecture-DDD-blue)
![SaaS](https://img.shields.io/badge/Type-SaaS-darkgreen)

---

## 📖 Sobre o Projeto

**Focus** é uma plataforma SaaS voltada para empresas que desejam acompanhar métricas operacionais, análise de dados e insights de vendas através de dashboards e gráficos baseados em dados reais.

O projeto foi criado com foco principal em evolução técnica e aprofundamento em boas práticas de engenharia de software, incluindo:

- Domain Driven Design (DDD)
- Princípios SOLID
- Arquitetura modular e escalável
- Modelagem avançada de entidades e relacionamentos JPA
- Integração com APIs externas

Além de atender necessidades reais de negócio, o projeto serve como laboratório prático para estudo de arquitetura corporativa e construção de sistemas escaláveis.

---

## 🎯 Objetivos do Sistema

O Focus permite:

- 📊 Monitorar métricas empresariais (Employees, Products, Stock)
- 📈 Gerar insights de vendas
- 📉 Visualizar dados através de gráficos analíticos
- 🧠 Auxiliar tomada de decisão baseada em dados reais

---


## 🏗️ Arquitetura Atual

A aplicação segue princípios inspirados em **DDD (Domain Driven Design)** com separação clara entre:

- Camada de domínio
- Adaptadores (entrada/saída)
- Infraestrutura
- Mapeamento de dados

Estrutura atual:

```plaintext
focus /
├── adapter 
    ├── controller     # (BrandController, StoreController)
    ├── dto    
        ├── address    # (AddressRequestDto, AddressResponseDto)
        ├── brand      # (BrandRequestoDto, BrandResponseDto)
        ├── security   # (LoginRequestDto, LoginResponseDto)
        ├── store      # (CreateadStoreRequestDto, StorePagination...)
├── domain
    ├── algorithms
        ├── researcher # (Researcher, SearchStrategy)
        ├── sorter     # (Sorter, SortsStrategy) 
    ├── model          # (Address, Brand, Contact, Store, Role, Product...)
    ├── repository     # (AddressRepository, BrandRepository, StoreRepository...)
    ├── service        
        ├── address    # (AddressService, AddressServiceImpl)
        ├── brand      # (BrandService, BrandServiceImpl)
        ├── product    # (ProductService, ProductServiceImpl)
        ├── store      # (StoreService, StoreServiceImpl)
├── infra
    ├── config         # (SecurityConfig)
    ├── exception      # (GlobalHandlerException)
├── mapper             # (AddressMapper, BrandMapper, StoreMapper)
```

---

# 🧩 Conceitos Arquiteturais Aplicados

---

## 🧱 Domain Driven Design (DDD)

- Separação clara entre domínio e infraestrutura
- Entidades focadas em regras de negócio
- Serviços de domínio organizados por contexto

---

## 🔗 Modelagem de Relacionamentos

O projeto explora profundamente relacionamentos entre entidades:

- `OneToMany`
- `ManyToOne`
- `OneToOne`

Boas práticas aplicadas:

- Estratégias de **cascade**
- Configuração adequada de **fetch strategy**
- Prevenção de problemas comuns como `LazyInitializationException`

---

## 🧼 Princípios SOLID

O código segue os principais princípios de design orientado a objetos:

- Single Responsibility Principle
- Open/Closed Principle
- Liskov Substitution Principle
- Interface Segregation Principle
- Dependency Inversion Principle

Aplicados principalmente em:

- Services
- Strategies
- Algorithms (`Sorter` / `Researcher patterns`)

---

# ⚙️ Integrações

## 📍 ViaCEP API

- Preenchimento automático de endereço
- Redução de erros de cadastro
- Padronização de dados

---

## 💳 Efí Bank (Em desenvolvimento)

Planejado:

- Pagamentos SaaS
- Assinaturas
- Cobrança automatizada

---


# 🧠 Estratégias e Algoritmos

O projeto inclui abstrações utilizando Strategy Pattern:

- Strategy Pattern para ordenação (`SortStrategy`)
- Strategy Pattern para busca (`SearchStrategy`)

Benefícios:

- Extensão fácil
- Baixo acoplamento
- Código aberto para evolução

---

# 🔐 Segurança

- Configuração centralizada via `SecurityConfig`
- DTOs específicos para autenticação
- Base preparada para expansão futura (JWT / OAuth2)

---

## 🛠️ Como Executar o Projeto

### 📋 Pré-requisitos

Certifique-se de ter instalado em sua máquina:

* IntelliJ IDE ou IDE da sua preferência
```bash
https://download.jetbrains.com/idea/ideaIC-2025.2.6.1.exe?_gl=1*fup02*_ga*NTE1Mzk2NTQwLjE3NjU1MDMwNDg.*_ga_9J976DJZ68*czE3Njk1NDA2OTUkbzMkZzAkdDE3Njk1NDA2OTYkajYwJGwwJGgw*_gcl_au*MTQwMjQwODg2MS4xNzY1NTAzMDQ4*FPAU*MTQwMjQwODg2MS4xNzY1NTAzMDQ4
```
* Java 17+ (JDK)
```bash
https://www.oracle.com/br/java/technologies/javase/javase8-archive-downloads.html#:~:text=jdk%2D8u202%2Dwindows%2Dx64.exe
```
* Maven 3.8+
* Docker Desktop
```bash
https://desktop.docker.com/win/main/amd64/Docker%20Desktop%20Installer.exe?utm_source=docker&utm_medium=webreferral&utm_campaign=docs-driven-download-win-amd64
```
* Git
```bash
https://github.com/git-for-windows/git/releases/download/v2.52.0.windows.1/Git-2.52.0-64-bit.exe
```

---

### 🚀 Passo a Passo

#### 1️⃣ Clonar o Repositório

```bash
git clone https://github.com/luger-mano/focus.git
cd focus
```

---

#### 2️⃣ Subir Banco de Dados

```bash
docker-compose up -d
```

---

#### 3️⃣ Gerar os Artefatos (Build do Maven)

```bash
mvn clean package -DskipTests
```

---

> ⚠️ Certifique-se de que os nomes das imagens correspondem aos definidos no `docker-compose.yml`.

---

#### 4️⃣ Executar a Aplicação (DockerFile)

```bash
docker build -t focus-msb .
```

---

#### 5️⃣ Executar Localmente (Opcional)

```bash
# Focus
cd focus
mvn spring-boot:run
```

---
#### Coleção das requisições para testar a aplicação

```bash
# https://.postman.co/workspace/My-Workspace~912e6447-255d-4e54-8700-1363f4cb3c57/collection/32811777-cb78d763-ca71-477c-a717-f7297f6a52e8?action=share&creator=32811777 
```

---

# 📈 Roadmap

## 🧩 Domínio

- [ ] Employee Metrics
- [ ] Sales Analytics Engine
- [ ] Dashboard Insights

---

## 💳 Financeiro

- [ ] Integração Efí Bank
- [ ] Sistema de assinaturas SaaS

---

## ⚙️ Arquitetura

- [ ] Eventos de domínio
- [ ] Cache estratégico
- [ ] Melhorias de performance em queries

---

# 👨‍💻 Objetivo do Projeto

Este projeto foi desenvolvido com foco em:

- Evolução técnica contínua
- Boas práticas de arquitetura corporativa
- Preparação para sistemas reais de produção
- Domínio avançado de modelagem relacional em JPA

---

# ✨ Autor

Desenvolvido com foco em engenharia de software moderna, arquitetura escalável e melhoria contínua.

---
