Projeto criado no IntelliJ IDEA Community Edition 2025.2.1

Equipe: 
Alexandre Alves (https://github.com/alexandrevc4)
Isak Gabriel (https://github.com/IsakGabrielCG)
João Pedro Warmling (https://github.com/jpedr)

 Projeto Wind Filmes, uma plataforma de filmes.
-----------------------------------------------------------------
Requisitos Mínimos do Sistema
Arquitetura
• Aplicar arquitetura em camadas: Controller, Service, Repository, Entity, DTO,
Validation, ExceptionHandler.
• Uso de Spring Boot 3+, Spring Data JPA, e Spring Security com JWT.
• Banco de dados PostgreSQL.
• Utilizar Flyway para versionamento do banco.

-----------------------------------------------------------------
Regras e Entidades (mínimo exigido)
O sistema deve conter no mínimo 5 entidades relacionadas (1:N e N:N), por exemplo:
Exemplo Netflix-like:
Usuário → Perfil → Filme → Categoria → Avaliação
Exemplo Jogo:
Jogador → Partida → Personagem → Item → Conquista
Requisitos funcionais mínimos:
1. CRUD completo de pelo menos 5 entidades.
2. Relacionamentos entre entidades (1:N, N:N).
3. Autenticação e autorização via JWT.
4. Validações com anotações (@NotBlank, @Email, @Size, etc.).
5. Tratamento centralizado de exceções (@ControllerAdvice).
6. DTOs para entrada e saída de dados (evitar expor entidades diretas).
7. Logs básicos de operações importantes (opcional, para bônus).

-----------------------------------------------------------------

Tecnologias
• Backend: Spring Boot 3, Spring Data JPA, Spring Security, JWT, Lombok
• Banco de dados: PostgreSQL 12 a 17
• Versionamento: Git + GitHub (repositório por equipe)
• Documentação: Swagger/OpenAPI ou Readme no Git

-----------------------------------------------------------------
Código-Fonte
• Projeto completo com README.md documentando:
o Instruções para rodar o projeto.
o Estrutura de pastas.
o Exemplo de requests e responses.
o Credenciais.
-----------------------------------------------------------------

