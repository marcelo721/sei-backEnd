## SEI - Sistema de Estudos Integrado

## Introdução
Bem-vindo à documentação da **SEI API**, uma aplicação desenvolvida em Java utilizando o framework Spring Boot. Esta API foi projetada para atender ao **Sistema de Ensino Integrado (SEI)**, uma plataforma de estudos integrada voltada para alunos de engenharia de universidades. O objetivo principal da API é fornecer uma solução robusta e escalável para gerenciar e integrar dados acadêmicos, facilitando o acesso a informações relevantes e melhorando a experiência dos estudantes.

A API segue os princípios RESTful, garantindo uma arquitetura em camadas, de fácil manutenção e altamente escalável. Com endpoints bem definidos, ela permite operações como  por exemplo: autenticação de usuários, gestão de disciplinas, acesso a materiais de estudo, registro de notas e desempenho acadêmico, integração com sistemas universitários existentes etc.

Este projeto foi desenvolvido com o intuito de simplificar o acesso dos alunos de engenharia aos recursos acadêmicos, centralizar informações importantes em uma única plataforma, facilitar a comunicação entre alunos, professores e administradores, etc. Ele é especialmente útil para alunos de engenharia que buscam uma plataforma integrada para estudos, professores que desejam gerenciar conteúdos e avaliações, "administradores universitários que precisam de uma solução centralizada para dados acadêmicos,mas futuramente, nossa ferramente irá abordar aspectos além dos cursos de engenharia para ser mais abrangente à toda universidade etc.].

# Como Executar o Projeto SEI

Este guia explica como configurar e executar o projeto SEI em sua máquina local. Siga os passos abaixo para compilar e rodar a aplicação.

---

## Requisitos

Antes de começar, certifique-se de que você possui os seguintes requisitos instalados:

1. **Java 17 ou superior (LTS)**:
   - O projeto foi desenvolvido usando Java 17. Certifique-se de que você tem uma versão compatível instalada.
   - Para verificar a versão do Java, execute no terminal:
     ```bash
     java -version
     ```
   - Caso não tenha o Java instalado, baixe e instale a partir do [site oficial](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html).

2. **Maven (Opcional)**:
   - O projeto inclui o wrapper do Maven (`mvnw`), que permite compilar e executar o projeto sem precisar instalar o Maven manualmente.
   - Se preferir instalar o Maven, siga as instruções no [site oficial](https://maven.apache.org/install.html).

---

## Passo a Passo para Executar o Projeto

### 1. **Clone o Repositório**
   - Abra o terminal (de preferência, use o **Bash** no Linux/macOS ou o **Git Bash** no Windows).
   - Navegue até a pasta onde deseja clonar o projeto e execute:
     ```bash
     git clone https://github.com/marcelo721/sei-backEnd
     ```

### 2. **Navegue até a Pasta do Projeto**
   - Acesse a pasta do projeto clonado:
     ```bash
     cd SEI
     ```

### 3. **Compile o Projeto**
   - Execute o seguinte comando para compilar o projeto e gerar o arquivo `.jar`:
     ```bash
     ./mvnw clean package
     ```
   - Esse comando faz o seguinte:
     - **`clean`**: Limpa a pasta `target` (se existir).
     - **`package`**: Compila o projeto e gera o arquivo `.jar` dentro da pasta `target`.

### 4. **Execute a Aplicação**
   - Após a compilação, execute o seguinte comando para iniciar a aplicação:
     ```bash
     java -jar target/SEI-0.0.1-SNAPSHOT.jar
     ```
   - Esse comando inicia o servidor Spring Boot embutido.

### 5. **Acesse a Aplicação**
   - A aplicação estará disponível no endereço:
     ```
     http://localhost:8080
     ```
   - Abra esse link no seu navegador para interagir com a aplicação.


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


## O que é o arquivo `pom.xml`?

O `pom.xml` (Project Object Model) é o arquivo central de configuração do Maven. Ele contém informações sobre o projeto, suas dependências, plugins e outras configurações necessárias para compilar, testar e empacotar a aplicação.

---

## Estrutura do `pom.xml`

### 1. **Informações Básicas do Projeto**
- **groupId**: `com.marcelo721` - Identifica o grupo ou organização do projeto.
- **artifactId**: `SEI` - Nome do projeto.
- **version**: `0.0.1-SNAPSHOT` - Versão atual do projeto (em desenvolvimento).
- **name**: `SEI` - Nome do projeto.
- **description**: `SEI is an academic project to help students in computer engineering and electrical engineering.` - Descrição do projeto.

### 2. **Configurações do Projeto**
- **Java Version**: `17` - Versão do Java utilizada no projeto.
- **Maven Compiler**: Configurado para usar a versão 21 do Java para compilação.

### 3. **Dependências**
As dependências são bibliotecas externas que o projeto utiliza para funcionar. Abaixo estão as principais dependências e suas funções:

#### Dependências Principais
- **Spring Boot Starter Data JPA** (`spring-boot-starter-data-jpa`):
  - Fornece suporte para acesso a banco de dados usando JPA (Java Persistence API).
  - Facilita a integração com bancos de dados relacionais.

- **Spring Boot Starter Web** (`spring-boot-starter-web`):
  - Fornece suporte para desenvolvimento de aplicações web com Spring MVC.
  - Inclui dependências como Tomcat (servidor embutido) e Jackson (para manipulação de JSON).

- **H2 Database** (`h2`):
  - Banco de dados em memória, ideal para desenvolvimento e testes.
  - Escopo `runtime`: Disponível apenas durante a execução do projeto.

- **MySQL Connector Java** (`mysql-connector-java`):
  - Driver JDBC para conexão com bancos de dados MySQL.
  - Utilizado em ambientes de produção.

- **Spring Boot Starter Mail** (`spring-boot-starter-mail`):
  - Fornece suporte para envio de e-mails.

- **Spring Boot Starter Test** (`spring-boot-starter-test`):
  - Fornece ferramentas para testes unitários e de integração.
  - Escopo `test`: Disponível apenas durante os testes.

- **Lombok** (`lombok`):
  - Biblioteca que simplifica a escrita de código Java, gerando automaticamente métodos como getters, setters, construtores, etc.
  - Escopo `annotationProcessor`: Processa anotações durante a compilação.

- **Java JWT** (`java-jwt`):
  - Biblioteca para criação e validação de tokens JWT (JSON Web Tokens).
  - Utilizada para autenticação e autorização.

- **Spring Boot Starter Security** (`spring-boot-starter-security`):
  - Fornece suporte para segurança em aplicações Spring Boot.
  - Inclui autenticação, autorização e proteção contra ataques comuns.

- **Spring Boot Starter Webflux** (`spring-boot-starter-webflux`):
  - Fornece suporte para programação reativa com Spring WebFlux.
  - Ideal para aplicações que exigem alta escalabilidade.

- **Springdoc OpenAPI Starter WebMVC UI** (`springdoc-openapi-starter-webmvc-ui`):
  - Gera documentação automática da API usando o padrão OpenAPI (Swagger).
  - Facilita a visualização e teste dos endpoints da API.

- **PostgreSQL** (`postgresql`):
  - Driver JDBC para conexão com bancos de dados PostgreSQL.

- **Spring Boot Starter Validation** (`spring-boot-starter-validation`):
  - Fornece suporte para validação de dados em entidades e objetos.

### 4. **Plugins**
- **Maven Compiler Plugin**:
  - Configura o compilador do Maven para usar a versão especificada do Java.

- **Spring Boot Maven Plugin**:
  - Facilita a construção e execução de aplicações Spring Boot.
  - Permite empacotar a aplicação como um arquivo JAR executável.

---

# Configuração do Swagger (SpringDoc OpenAPI)

Este tópico descreve a configuração e utilização do Swagger (SpringDoc OpenAPI) no projeto SEI. O Swagger é uma ferramenta que facilita a documentação e teste de APIs RESTful, gerando automaticamente uma interface interativa para explorar os endpoints da API.

---

---

## Configuração do Swagger no Projeto

A configuração do Swagger foi feita na classe `SpringDocOpenApi`, localizada no pacote `com.marcelo721.SEI.Config`. Abaixo estão os detalhes dessa configuração:

### 1. **Classe `SpringDocOpenApi`**
- **Anotação `@Configuration`**:
  - Indica que a classe contém configurações do Spring.
- **Método `springShopOpenAPI()`**:
  - Define as configurações globais do Swagger, como o título, descrição e versão da API.
  - Configura o esquema de segurança para autenticação via JWT (JSON Web Token).

#### Código da Classe:
```java
@Configuration
public class SpringDocOpenApi {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("security", securityScheme()))
                .info(new Info().title("SEI - API")
                        .description("Sei API")
                        .version("v0.0.1"));
    }

    private SecurityScheme securityScheme() {
        return new SecurityScheme()
                .description("Insert token to continue")
                .type(SecurityScheme.Type.HTTP)
                .in(SecurityScheme.In.HEADER)
                .scheme("bearer")
                .bearerFormat("JWT")
                .name("security");
    }
}
```



## exemplo de uso do swagger em um endpoint de um controller 
```java
 @Operation(
            summary = "create a new user", description = "resource to create a new user",
            responses = {
                    @ApiResponse(responseCode = "201", description = "resource created successfully",
                            content = @Content(mediaType= "application/json", schema = @Schema(implementation = UserResponseDto.class))),

                    @ApiResponse(responseCode = "409", description = "User email is already registered",
                            content = @Content(mediaType= "application/json", schema = @Schema(implementation = ErrorMessage.class))),

                    @ApiResponse(responseCode = "422", description = "Invalid Data",
                            content = @Content(mediaType= "application/json", schema = @Schema(implementation = ErrorMessage.class)))

            }
    )
    @PostMapping//tested
    public ResponseEntity<Void> createUser(@RequestBody @Valid UserCreateDto user) {
        User obj = user.toUser();
        userService.save(obj);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
```

# Testes

Este tópico descreve a estratégia de testes adotada no projeto SEI, utilizando **JUnit** para testes unitários camada de serviço e testes integrados. Os testes foram desenvolvidos para garantir a qualidade do código, validar a lógica de negócio e garantir a integração correta entre os componentes.

---

## Estratégia de Testes

### 1. **Testes Unitários**
- **O que são?**
  - Testes unitários focam em validar o comportamento de unidades individuais de código (como métodos ou classes) de forma isolada.
  - São rápidos e não dependem de recursos externos (banco de dados, serviços, etc.).

- **Onde foram aplicados?**
  - Na camada de **serviço** (`Service`), onde a lógica de negócio do projeto é implementada.
  - Exemplos: validação de regras de negócio, cálculos, transformações de dados, etc.

- **Ferramentas utilizadas:**
  - **JUnit 5**: Framework de testes para Java.
  - **Mockito**: Biblioteca para criar mocks e simular comportamentos de dependências.

---

- **Exemplo de Teste unitário**:
```java
@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender; // Mock do JavaMailSender

    @Mock
    private MimeMessage mimeMessage; // Mock do MimeMessage

    @InjectMocks
    private EmailService emailService; // Injeta os mocks no EmailService

    private User user;

    @BeforeEach
    public void setUp() {
        // Configura um usuário de teste
        user = new User();
        user.setName("Marcelo");
        user.setEmail("marcelo@example.com");
        user.setVerificationCode("123456");
    }

    @Test
    public void sendVerifyEmail_ShouldSendEmailSuccessfully() throws MessagingException, UnsupportedEncodingException {
        // Configura o comportamento do mock do JavaMailSender
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        // Chama o método a ser testado
        emailService.sendVerifyEmail(user);

        // Verifica se o método createMimeMessage foi chamado
        verify(mailSender, times(1)).createMimeMessage();

        // Verifica se o email foi enviado
        verify(mailSender, times(1)).send(mimeMessage);
    }

}
```

### 2. **Testes Integrados**
- **O que são?**
  - Testes integrados validam a interação entre múltiplos componentes do sistema, como serviços, repositórios e controladores.
  - Podem envolver recursos externos, como banco de dados.

- **Ferramentas utilizadas:**
  - **JUnit 5**: Framework de testes.
  - **Spring Boot Test**: Suporte do Spring Boot para testes integrados.
  - **H2 Database**: Banco de dados em memória para simular o ambiente de banco de dados em testes.

---

- **Exemplo de Teste Integrado**:
```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AuthenticationIT {

    @Autowired
    private WebTestClient testClient;

    @Test
    public void authenticationWithValidCredentials_returnStatus200(){

        JwtToken responseBody = testClient
                .post()
                .uri("/api/v1/login")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UserLoginDto("marcelo@alu.ufc.br", "M@rcelo222"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(JwtToken.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
    }
}
```

# JaCoCo - Cobertura de Código no Projeto SEI

O **JaCoCo** é uma ferramenta de cobertura de código para projetos Java. Ele mede quantas linhas, métodos e classes do seu código foram exercitadas pelos testes, fornecendo relatórios detalhados que ajudam a identificar áreas não testadas.

Este documento explica como o JaCoCo foi configurado no projeto SEI, como utilizá-lo e como interpretar seus relatórios.

---

## O que é o JaCoCo?

O JaCoCo (Java Code Coverage) é uma biblioteca que analisa a cobertura de código em projetos Java. Ele funciona em conjunto com ferramentas de teste, como JUnit, para gerar relatórios que mostram:

- **Cobertura de Linhas**: Quantas linhas de código foram executadas durante os testes.
- **Cobertura de Métodos**: Quantos métodos foram chamados.
- **Cobertura de Classes**: Quantas classes foram utilizadas.
- **Cobertura de Branches**: Quantos caminhos de decisão (if/else, switch, etc.) foram percorridos.

---

## Configuração do JaCoCo no Projeto

O JaCoCo foi configurado no projeto SEI usando o **Maven**. Abaixo estão os detalhes da configuração:

### 1. **Adicionando o Plugin no `pom.xml`**
O JaCoCo foi adicionado como um plugin no arquivo `pom.xml`:

```xml
<plugin>
             <groupId>org.jacoco</groupId>
             <artifactId>jacoco-maven-plugin</artifactId>
             <version>0.8.8</version>
             <executions>
                 <execution>
                     <goals>
                         <goal>prepare-agent</goal>
                     </goals>
                 </execution>
                 <execution>
                     <id>report</id>
                     <phase>verify</phase>
                     <goals>
                         <goal>report</goal>
                     </goals>
                 </execution>
             </executions>
</plugin>
```

## Executando o JaCoCo no SEI

Para gerar o relatório de cobertura, execute o seguinte comando no terminal:
```bash
mvn clean test jacoco:report
```
Esse comando irá executar todos os testes do projeto e Gerar um relatório de cobertura na pasta **target/site/jacoco/index.html**

## Gerando o relatório 
Após executar o comando mvn clean test jacoco:report, o relatório será gerado e para acessá-lo digite o seguinte comando na pasta raiz do projeto:
```bash
target/site/jacoco/index.html
```

# Funcionalidade de Envio de E-mails

Este tópico explica como a funcionalidade de envio de e-mails foi implementada no projeto SEI, utilizando a ferramenta de e-mail transacional **Brevo** (antigo Sendinblue).

---

## Ferramenta Utilizada: Brevo

O **Brevo** é uma plataforma de e-mail transacional que permite enviar e-mails de forma confiável e escalável. Ele oferece APIs simples e documentação clara, facilitando a integração com aplicações Spring Boot.

### Por que o Brevo?
- **Facilidade de Integração**: APIs RESTful bem documentadas.
- **Confiança**: Entregabilidade alta e suporte a templates de e-mail.
- **Gratuito para Testes**: Oferece um plano gratuito com limite de 300 envios por di para desenvolvimento e testes.

---

## Implementação no Projeto

### 1. **Configuração no `application.properties`**
As credenciais e configurações do Brevo foram adicionadas no arquivo de configuração da aplicação:

```properties
#CONFIGURAÇÕES
spring.mail.host=smtp-relay.brevo.com
spring.mail.port=587
#Credencias configuradas como variáveis de Ambiente
spring.mail.username=${LOGIN}
spring.mail.password=${PASSWORD}
```

# ApiExceptionHandler

## Descrição

O `ApiExceptionHandler` é uma classe centralizada para o tratamento de exceções em uma aplicação Spring Boot. Ele atua como um `@RestControllerAdvice`, o que significa que ele intercepta exceções lançadas em qualquer controlador da aplicação e as trata de maneira consistente, retornando respostas HTTP apropriadas.

## Por que foi usado?

O uso do `ApiExceptionHandler` é crucial para garantir que todas as exceções sejam tratadas de forma uniforme e que o cliente receba mensagens de erro claras e úteis. Isso melhora a experiência do usuário e facilita a depuração de problemas. Além disso, centralizar o tratamento de exceções em um único local torna o código mais limpo e mais fácil de manter.

## Funcionalidades

- **Tratamento Centralizado de Exceções**: Captura exceções lançadas em qualquer controlador e as trata de maneira consistente.
- **Respostas HTTP Padronizadas**: Retorna respostas HTTP com status codes apropriados e mensagens de erro detalhadas.
- **Log de Erros**: Registra erros no log da aplicação para facilitar a depuração e monitoramento.
- **Suporte a Diferentes Tipos de Exceções**: Trata uma variedade de exceções, incluindo validação de argumentos, violações de integridade de dados, erros de autenticação e mais.

## Exemplos de Exceções Tratadas

- **MethodArgumentNotValidException**: Trata erros de validação de argumentos em métodos de controladores.
- **DataIntegrityViolationException**: Captura violações de integridade de dados, como tentativas de inserir valores duplicados em campos únicos.
- **AccessDeniedException**: Lida com erros de acesso negado, retornando um status HTTP 403.
- **NoSuchElementException**: Trata casos onde um elemento esperado não é encontrado, retornando um status HTTP 404.
- **JWTCreationException**: Captura erros relacionados à criação de tokens JWT.

## Estrutura da Resposta de Erro

Todas as respostas de erro seguem uma estrutura padrão, encapsulada na classe `ErrorMessage`. Essa estrutura inclui:

- **timestamp**: O momento em que o erro ocorreu.
- **status**: O código de status HTTP.
- **error**: A descrição do erro.
- **message**: Uma mensagem detalhada sobre o erro.
- **path**: O caminho da requisição que causou o erro.

## Como Usar

Para adicionar novas exceções ao `ApiExceptionHandler`, basta criar um novo método anotado com `@ExceptionHandler` e especificar o tipo de exceção que ele deve tratar. Por exemplo:

```java
@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                        HttpServletRequest request,
                                                                        BindingResult result){

        log.error("Api Error -", ex);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY, "Invalid Arguments", result));

    }

    @ExceptionHandler(EmailUniqueViolationException.class)
    public ResponseEntity<ErrorMessage> emailUniqueViolationException(RuntimeException ex,
                                                                      HttpServletRequest request){
        log.error("Api Error", ex);

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT,ex.getMessage()));

    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityNotFoundException(RuntimeException ex,
                                                                HttpServletRequest request){
        log.error("Api Error", ex);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND,ex.getMessage()));

    }
}
```
