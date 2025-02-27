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
- **Postman**: Para testes de endpoints e coleções de requisições.
- **Ambientes de Testes**: Configuração de ambientes de desenvolvimento, teste e produção.
- **JaCoCo**: Para cobertura de testes e análise de código.
- **Maven/Gradle**: Para gerenciamento de dependências e build do projeto.


## Funcionalidades Principais

- **Autenticação e Autorização**: Autenticação de usuários (alunos, professores e administradores) via JWT.
- **Gestão de Disciplinas e Turmas**: CRUD de disciplinas, turmas e associações entre alunos e disciplinas.
- **Materiais de Estudo**: Upload e download de materiais de estudo (PDFs, vídeos, etc.).
- **Registro de Notas e Desempenho**: Registro e consulta de notas e desempenho acadêmico.
- **Integração com Sistemas Universitários**: Integração com sistemas existentes para sincronização de dados.
- **Documentação Interativa**: Documentação da API via Swagger para facilitar o uso por desenvolvedores.

