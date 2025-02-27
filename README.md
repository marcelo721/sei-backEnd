## SEI - Sistema de Estudos Integrado

## Introdução
Bem-vindo à documentação da **SEI API**, uma aplicação desenvolvida em Java utilizando o framework Spring Boot. Esta API foi projetada para atender ao **Sistema de Ensino Integrado (SEI)**, uma plataforma de estudos integrada voltada para alunos de engenharia de universidades. O objetivo principal da API é fornecer uma solução robusta e escalável para gerenciar e integrar dados acadêmicos, facilitando o acesso a informações relevantes e melhorando a experiência dos estudantes.

A API segue os princípios RESTful, garantindo uma arquitetura em camadas, de fácil manutenção e altamente escalável. Com endpoints bem definidos, ela permite operações como  por exemplo: autenticação de usuários, gestão de disciplinas, acesso a materiais de estudo, registro de notas e desempenho acadêmico, integração com sistemas universitários existentes etc.

Este projeto foi desenvolvido com o intuito de simplificar o acesso dos alunos de engenharia aos recursos acadêmicos, centralizar informações importantes em uma única plataforma, facilitar a comunicação entre alunos, professores e administradores, etc. Ele é especialmente útil para alunos de engenharia que buscam uma plataforma integrada para estudos, professores que desejam gerenciar conteúdos e avaliações, "administradores universitários que precisam de uma solução centralizada para dados acadêmicos,mas futuramente, nossa ferramente irá abordar aspectos além dos cursos de engenharia para ser mais abrangente à toda universidade etc.].

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal.
- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **Spring Security**: Para autenticação e autorização de usuários.
- **JWT (JSON Web Tokens)**: Para autenticação segura e stateless.
- **Spring Data JPA**: Para persistência de dados e integração com bancos de dados.
- **Hibernate**: Como ORM (Object-Relational Mapping) para mapeamento de entidades.
- **MySQL**: Banco de dados relacional para ambiente de produção.
- **H2 Database**: Banco de dados em memória para testes e desenvolvimento.
- **Docker**: Para conteinerização e facilidade de deploy em diferentes ambientes.
- **Swagger**: Para documentação interativa da API.
-  **MailSender**: Para gerenciamento de envio de emails transacionais.
- **Postman**: Para testes de endpoints e coleções de requisições.
- **Ambientes de Testes**: Configuração de ambientes de desenvolvimento, teste e produção.
- **JaCoCo**: Para cobertura de testes e análise de código.
- **Maven/Gradle**: Para gerenciamento de dependências e build do projeto.


## Funcionalidades Principais

- **Autenticação e Autorização**: Autenticação de usuários (alunose administradores) via JWT.
- **Gestão de Disciplinas e Turmas**: CRUD de disciplinas, turmas e associações entre alunos e disciplinas.
- **Materiais de Estudo**: Upload e download de materiais de estudo (PDFs, vídeos, etc.).
- **Registro de Notas e Desempenho**: Registro e consulta de notas e desempenho acadêmico.
- **Integração com Sistemas Universitários**: Integração com sistemas existentes para sincronização de dados.
- **Documentação Interativa**: Documentação da API via Swagger para facilitar o uso por desenvolvedores.

- ## Estrutura do Projeto

A API foi desenvolvida utilizando uma **arquitetura em camadas**, seguindo boas práticas de desenvolvimento e organização de código. Os pacotes principais da aplicação são:

### Pacotes Principais

1. **Repository**:
   - Contém as interfaces que estendem `JpaRepository` para acesso e manipulação de dados no banco de dados.
   - Responsável pela camada de persistência.

2. **Service**:
   - Contém a lógica de negócio da aplicação.
   - Implementa as regras de negócio e faz a mediação entre a camada de controle (Controller) e a camada de persistência (Repository).

3. **Controller**:
   - Expõe os endpoints da API, recebendo requisições HTTP e retornando respostas adequadas.
   - Responsável pela camada de apresentação.

### Pacotes Modulares

Além dos pacotes principais, a aplicação foi organizada em módulos para melhorar a modularidade e a manutenção do código:

1. **Components**:
   - Contém componentes personalizados e reutilizáveis, como filtros, listeners ou serviços específicos.

2. **Config**:
   - Responsável por centralizar as configurações da aplicação.
   - Inclui configurações do **Swagger** para documentação da API, configurações do **Spring Security** para autenticação e autorização, e configurações web.

3. **Entities**:
   - Contém as entidades da aplicação, que representam as tabelas do banco de dados.
   - Dentro deste pacote, há um subpacote **enum**, que armazena os enums utilizados para mapear valores específicos das entidades (por exemplo, roles de usuários, status de disciplinas, etc.).

4. **JWT**:
   - Pacote dedicado às funcionalidades relacionadas ao **JSON Web Tokens (JWT)**.
   - Inclui classes para geração, validação e manipulação de tokens JWT.

5. **Utils**:
   - Contém métodos e classes utilitárias estáticas, como validadores, formatações e auxiliares para operações comuns.

6. **Web**:
   - Pacote responsável por gerenciar a camada web da aplicação. Ele é dividido em três subpacotes:
     - **Controllers**: Contém os controladores que expõem os endpoints da API.
     - **DTOs (Data Transfer Objects)**: Contém objetos utilizados para transferência de dados entre as camadas da aplicação, garantindo que apenas as informações necessárias sejam expostas.
     - **Exception**: Contém as classes de exceção personalizadas e handlers para tratamento global de erros.

  # Pacote `resources`

O pacote `resources` contém os arquivos de configuração da aplicação, que são essenciais para definir comportamentos específicos em diferentes ambientes. Esses arquivos permitem que a aplicação seja configurada de maneira dinâmica, dependendo do ambiente em que está sendo executada (desenvolvimento, teste ou produção).

---

## Arquivos de Configuração

### 1. **application.properties**
- **Descrição**: Arquivo principal de configuração da aplicação.
- **Funções**:
  - Define configurações globais, como a porta do servidor, configurações básicas do Spring Boot e propriedades comuns a todos os ambientes.
  - Permite definir o perfil ativo (`spring.profiles.active`), que determina qual arquivo de configuração específico do ambiente será carregado (por exemplo, `dev` ou `prod`).

---

### 2. **application-dev.properties**
- **Descrição**: Arquivo de configuração específico para o ambiente de **desenvolvimento**.
- **Configurações comuns**:
  - Conexão com o banco de dados H2 (banco em memória), ideal para testes rápidos e desenvolvimento local.
  - Ativação de logs detalhados para facilitar a depuração.
  - Configurações de segurança relaxadas para facilitar o desenvolvimento.
- **Exemplo de uso**:
  ```properties
  spring.profiles.active=dev

### 3. **application-prod.properties**
- **Descrição**: Arquivo de configuração específico para o ambiente de **produção**.
- **Configurações comuns**:
  - Conexão com o banco de dados principal e configuração de serviços de produção.
  - Ativação de logs detalhados para facilitar a depuração.
  - Configurações de segurança o desenvolvimento.
- **Exemplo de uso**:
  ```properties
  spring.profiles.active=prod
