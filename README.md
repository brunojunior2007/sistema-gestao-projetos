# 💻 Sistema de gestão de projetos e equipes

Este projeto foi construído para centralizar a operação de eventos, eliminando gargalos de comunicação e garantindo rastreabilidade. A arquitetura foi projetada para proteger dados sensíveis e forçar o cumprimento de regras de negócios essenciais para o sucesso da operação.

## Principais funcionalidades

- **Gestão de acessos**: Perfis blindados (Administrador, Gerente e Colaborador).
- **Cadastro estratégico de projetos**: Inclusão de dados focados em organização e rastreabilidade.
- **Trava de responsabilidade**: Impossibilidade sistêmica de criar um projeto sem alocação de um responsável (Regra de negócio aplicada via Construtor).
- **Alocação de equipes**: Distribuição de colaboradores por projeto e time de forma organizada.

## Estrutura do projeto (Padrão MVC)

O código-fonte é rigorosamente organizado no pacote base `br.com.brunojr.sistemagestao` para separar a interface, as regras de negócio e o controle de dados:

- **domain/**: Classes de negócio e POO (`Colaborador.java`, `GestaoProjeto.java`, `Time.java`) com encapsulamento e modelagem do domínio.
- **repositories/**: Conexão e simulação de persistência de dados em memória.
- **ui/**: Ponto de entrada e interface de interação com o usuário (`Application.java`).
- **controllers/**: Lógica de conexão entre telas e dados/regras de negócio (`GerenciadorFluxoController.java`).

## Tecnologias e padrões utilizados

- **Linguagem**: Java (JDK 17+)
- **Arquitetura**: MVC (Model - View - Controller) + Repository
- **Paradigma**: Programação Orientada a Objetos (POO) - Encapsulamento, Construtores e Instanciação.
- **Versionamento**: Git e GitHub

## Como executar o projeto

1. Faça o download do arquivo .zip ou clone este repositório.
2. Abra a pasta do projeto em sua IDE de preferência (IntelliJ IDEA, Eclipse ou VS Code).
3. Navegue até o pacote `src/br/com/brunojr/sistemagestao/ui` e localize o arquivo `Application.java`.
4. Execute o arquivo `Application.java` para visualizar a simulação do sistema no console.

---
*Projeto desenvolvido como requisito de avaliação para a disciplina de programação de soluções computacionais.*
