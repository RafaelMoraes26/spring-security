# Spring Security Practice Project

Este projeto está sendo desenvolvido com o objetivo de praticar e aprofundar o conhecimento em Spring Security. A aplicação envolve a implementação de autenticação e autorização utilizando o Spring Boot e o Spring Security, bem como a integração com um banco de dados H2 para testes.

## Funcionalidades Implementadas
Algumas implementações estão sendo feitas e lançadas em releases, a fim de evitar conflitos entre os métodos de autenticação.

1. **Autenticação de Usuários:**
   - Implementação de um sistema de login que autentica os usuários com base em credenciais fornecidas (nome de usuário e senha).
   - Utilização do `AuthenticationManager` para processar a autenticação.
   - Armazenamento do contexto de segurança na sessão HTTP após o login.
   - **Release:** [`1.0.0-auth-by-roles`](https://github.com/RafaelMoraes26/spring-security/tree/1.0.0-auth-by-roles)

2. **Autorização de Acesso:**
   - Configuração de permissões de acesso a diferentes endpoints da aplicação com base nas roles dos usuários.
   - Proteção de endpoints específicos para usuários autenticados com roles `USER` e `ADMIN`.
   - Implementação de um sistema de logout que invalida a sessão e remove os dados de autenticação.
   - **Release:** [`1.0.0-auth-by-roles`](https://github.com/RafaelMoraes26/spring-security/tree/1.0.0-auth-by-roles)

3. **Integração com Banco de Dados H2:**
   - Configuração do H2 como banco de dados embutido para armazenamento de dados de usuários durante os testes.
   - População do banco de dados com usuários de teste antes de cada execução de teste.
   - **Release:** [`1.0.0-auth-by-roles`](https://github.com/RafaelMoraes26/spring-security/tree/1.0.0-auth-by-roles)

4. **Testes de Integração:**
   - Testes de integração para verificar a funcionalidade de login e logout usando `MockMvc`.
   - Uso do `@BeforeEach` para garantir que os dados de teste estejam disponíveis antes de cada teste.
   - Testes para verificar autenticação bem-sucedida e falha de autenticação.
   - **Release:** [`1.0.0-auth-by-roles`](https://github.com/RafaelMoraes26/spring-security/tree/1.0.0-auth-by-roles)

5. **Autenticação com JWT:**
   - Implementação de autenticação baseada em tokens JWT, permitindo que a API funcione de forma stateless.
   - Geração de tokens JWT após a autenticação bem-sucedida.
   - Validação de tokens JWT para proteger endpoints e garantir que apenas usuários autenticados possam acessá-los.
   - **Release:** [`2.0.0-auth-by-jwt`](https://github.com/RafaelMoraes26/spring-security/tree/2.0.0-auth-by-jwt)

6. **Autenticação com OAuth2:**
   - Integração com provedores de autenticação OAuth2 (GitHub) para oferecer login social.
   - Redirecionamento para a página de login do GitHub e tratamento do fluxo de autenticação OAuth2.
   - Proteção de endpoints com autenticação OAuth2 e personalização da página de login.
   - **Release:** [`3.0.0-auth-by-OAuth2`](https://github.com/RafaelMoraes26/spring-security/tree/3.0.0-auth-by-oauth2)

## Funcionalidades a Serem Implementadas

1. **Configuração de Expiração de Sessão:**
   - Configurar expiração de sessão e timeout para melhorar a segurança e a usabilidade.

2. **Auditoria de Acessos:**
   - Implementar auditoria de acessos, registrando logins, logouts e tentativas de acesso mal-sucedidas.

3. **Recuperação de Senha:**
   - Implementar funcionalidade de recuperação de senha para os usuários, permitindo que redefinam suas senhas em caso de esquecimento.

4. **Melhorias na UI:**
   - Implementar uma interface de usuário simples para facilitar a interação com o sistema de autenticação.

## Como Executar o Projeto

1. **Pré-requisitos:**
   - Java 21 ou superior
   - Gradle

2. **Passos para executar:**
   - Clone o repositório: `git clone https://github.com/seu-usuario/seu-projeto.git`
   - Navegue até o diretório do projeto: `cd seu-projeto`
   - Execute o projeto: `./gradlew bootRun`

3. **Acessando a Aplicação:**
   - O projeto estará disponível em `http://localhost:8080`.

## Como Executar os Testes

- Para executar os testes, use o comando: `./gradlew test`.

## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para enviar pull requests ou abrir issues para discutir melhorias.

## Licença

Este projeto é licenciado sob a [LICENSE-CC-BY-NC-ND-4.0 License](LICENSE.md).

## Aviso de Propriedade

Este repositório é de propriedade privada e não deve ser usado, copiado ou redistribuído por terceiros sem autorização prévia do proprietário.
