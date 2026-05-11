package br.com.brunojr.sistemagestao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import br.com.brunojr.sistemagestao.controllers.GerenciadorFluxoController;
import br.com.brunojr.sistemagestao.domain.Colaborador;
import br.com.brunojr.sistemagestao.domain.GestaoProjeto;
import br.com.brunojr.sistemagestao.domain.Time;

public class Application {
        private static Scanner scanner = new Scanner(System.in);
        private static GerenciadorFluxoController fluxoExecutivo = new GerenciadorFluxoController();
        private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        public static void main(String[] args) {
                long tempoInicio = System.currentTimeMillis();
                boolean rodando = true;

                System.out.println("=========================================================================");
                System.out.println("ERP E WORKFLOW - SISTEMA DE GESTÃO DE CRONOGRAMAS E EQUIPES");
                System.out.println("=========================================================================");

                while (rodando) {
                        System.out.println("\n----------- MENU PRINCIPAL DE GESTÃO ESTRATÉGICA -----------");
                        System.out.println("1. GESTÃO DE ACESSOS (Cadastrar Colaborador)");
                        System.out.println("2. CADASTRO ESTRATÉGICO (Novo Projeto)");
                        System.out.println("3. ALOCAÇÃO DE EQUIPES (Novo Time)");
                        System.out.println("4. VINCULAR COLABORADOR A TIME");
                        System.out.println("5. VINCULAR PROJETO A TIME");
                        System.out.println("6. ATUALIZAR STATUS DE PROJETO");
                        System.out.println("7. GERENCIAR TAREFAS (Adicionar Tarefa)");
                        System.out.println("8. EMITIR RELATÓRIOS DE DESEMPENHO (Projetos)");
                        System.out.println("9. LISTAR COLABORADORES");
                        System.out.println("10. LISTAR TIMES");
                        System.out.println("11. EXIBIR LOGS DE SISTEMA");
                        System.out.println("0. SAIR");
                        System.out.print("Escolha uma opção: ");

                        String opcao = scanner.nextLine();

                        switch (opcao) {
                                case "1":
                                        cadastrarColaborador();
                                        break;
                                case "2":
                                        cadastrarProjeto();
                                        break;
                                case "3":
                                        cadastrarTime();
                                        break;
                                case "4":
                                        vincularColaboradorTime();
                                        break;
                                case "5":
                                        vincularProjetoTime();
                                        break;
                                case "6":
                                        atualizarStatusProjeto();
                                        break;
                                case "7":
                                        cadastrarTarefa();
                                        break;
                                case "8":
                                        fluxoExecutivo.listarProjetos();
                                        break;
                                case "9":
                                        fluxoExecutivo.listarColaboradores();
                                        break;
                                case "10":
                                        fluxoExecutivo.listarTimes();
                                        break;
                                case "11":
                                        exibirLogs(tempoInicio);
                                        break;
                                case "0":
                                        rodando = false;
                                        System.out.println("Encerrando sistema executivo...");
                                        break;
                                default:
                                        System.out.println("Opção inválida.");
                        }
                }
        }

        private static void cadastrarColaborador() {
                System.out.println("\n--- CADASTRO DE COLABORADOR ---");
                System.out.print("Nome Completo: ");
                String nome = scanner.nextLine();
                System.out.print("CPF: ");
                String cpf = scanner.nextLine();
                System.out.print("Email: ");
                String email = scanner.nextLine();
                System.out.print("Cargo: ");
                String cargo = scanner.nextLine();
                System.out.print("Login: ");
                String login = scanner.nextLine();
                System.out.print("Senha: ");
                String senha = scanner.nextLine();
                System.out.println("Perfil (1-ADMINISTRADOR, 2-GERENTE, 3-COLABORADOR): ");
                String p = scanner.nextLine();
                Colaborador.PerfilColaborador perfil = switch (p) {
                        case "1" -> Colaborador.PerfilColaborador.ADMINISTRADOR;
                        case "2" -> Colaborador.PerfilColaborador.GERENTE;
                        default -> Colaborador.PerfilColaborador.COLABORADOR;
                };

                fluxoExecutivo.registrarColaborador(nome, cpf, email, cargo, login, senha, perfil);
        }

        private static void cadastrarProjeto() {
                System.out.println("\n--- CADASTRO ESTRATÉGICO DE PROJETO ---");
                System.out.print("Nome do Projeto: ");
                String nome = scanner.nextLine();
                System.out.print("Descrição/Escopo: ");
                String descricao = scanner.nextLine();

                LocalDate inicio = lerData("Data Início (dd/mm/aaaa): ");
                LocalDate fim = lerData("Data Término Prevista (dd/mm/aaaa): ");

                System.out.print("Orçamento (R$): ");
                double orcamento = 0;
                try {
                        orcamento = Double.parseDouble(scanner.nextLine());
                } catch (NumberFormatException e) {
                        System.out.println("Valor inválido para orçamento. Definido como 0.");
                }

                System.out.println("Prioridade (1-ALTA, 2-MEDIA, 3-BAIXA): ");
                String prio = scanner.nextLine();
                GestaoProjeto.Prioridade prioridade = switch (prio) {
                        case "1" -> GestaoProjeto.Prioridade.ALTA;
                        case "3" -> GestaoProjeto.Prioridade.BAIXA;
                        default -> GestaoProjeto.Prioridade.MEDIA;
                };

                System.out.print("Login do Responsável (Gerente/Admin): ");
                String loginResp = scanner.nextLine();
                Colaborador gerente = fluxoExecutivo.buscarColaborador(loginResp);

                if (gerente == null) {
                        System.out.println("[!] Erro: Gerente não encontrado. Cadastre o gerente primeiro.");
                        return;
                }

                fluxoExecutivo.registrarProjeto(nome, descricao, inicio, fim, gerente, orcamento, prioridade);
        }

        private static void cadastrarTime() {
                System.out.println("\n--- ALOCAÇÃO DE EQUIPE (HUB OPERACIONAL) ---");
                System.out.print("Nome do Time: ");
                String nome = scanner.nextLine();
                System.out.print("Descrição/Especialidade: ");
                String desc = scanner.nextLine();

                fluxoExecutivo.registrarTime(nome, desc);
        }

        private static void vincularColaboradorTime() {
                System.out.println("\n--- VINCULAR COLABORADOR A TIME ---");
                System.out.print("Nome do Time: ");
                String nomeTime = scanner.nextLine();
                Time time = fluxoExecutivo.buscarTime(nomeTime);

                if (time == null) {
                        System.out.println("[!] Time não encontrado.");
                        return;
                }

                System.out.print("Login do Colaborador: ");
                String loginColab = scanner.nextLine();
                Colaborador colab = fluxoExecutivo.buscarColaborador(loginColab);

                if (colab == null) {
                        System.out.println("[!] Colaborador não encontrado.");
                        return;
                }

                fluxoExecutivo.adicionarMembroTime(time, colab);
        }

        private static void vincularProjetoTime() {
                System.out.println("\n--- VINCULAR PROJETO A TIME ---");
                System.out.print("Nome do Time: ");
                String nomeTime = scanner.nextLine();
                Time time = fluxoExecutivo.buscarTime(nomeTime);

                if (time == null) {
                        System.out.println("[!] Time não encontrado.");
                        return;
                }

                System.out.print("Nome do Projeto: ");
                String nomeProjeto = scanner.nextLine();
                GestaoProjeto projeto = fluxoExecutivo.buscarProjeto(nomeProjeto);

                if (projeto == null) {
                        System.out.println("[!] Projeto não encontrado.");
                        return;
                }

                fluxoExecutivo.vincularTimeProjeto(time, projeto);
        }

        private static void atualizarStatusProjeto() {
                System.out.println("\n--- ATUALIZAR STATUS DE PROJETO ---");
                System.out.print("Nome do Projeto: ");
                String nome = scanner.nextLine();
                GestaoProjeto projeto = fluxoExecutivo.buscarProjeto(nome);

                if (projeto == null) {
                        System.out.println("[!] Projeto não encontrado.");
                        return;
                }

                System.out.println("Novo Status (1-PLANEJADO, 2-EM ANDAMENTO, 3-CONCLUIDO, 4-CANCELADO): ");
                String s = scanner.nextLine();
                GestaoProjeto.StatusProjeto novoStatus = switch (s) {
                        case "1" -> GestaoProjeto.StatusProjeto.PLANEJADO;
                        case "2" -> GestaoProjeto.StatusProjeto.EM_ANDAMENTO;
                        case "3" -> GestaoProjeto.StatusProjeto.CONCLUIDO;
                        case "4" -> GestaoProjeto.StatusProjeto.CANCELADO;
                        default -> null;
                };

                if (novoStatus != null) {
                        fluxoExecutivo.atualizarStatusProjeto(projeto, novoStatus);
                } else {
                        System.out.println("[!] Status inválido.");
                }
        }

        private static void cadastrarTarefa() {
                System.out.println("\n--- GERENCIAR TAREFAS E ENTREGÁVEIS ---");
                System.out.print("Nome do Projeto: ");
                String nomeProj = scanner.nextLine();
                GestaoProjeto projeto = fluxoExecutivo.buscarProjeto(nomeProj);

                if (projeto == null) {
                        System.out.println("[!] Projeto não encontrado.");
                        return;
                }

                System.out.print("Título da Tarefa: ");
                String titulo = scanner.nextLine();
                System.out.print("Descrição/Detalhes: ");
                String desc = scanner.nextLine();
                LocalDate prazo = lerData("Prazo de Entrega (dd/mm/aaaa): ");

                System.out.print("Login do Responsável (Colaborador): ");
                String loginColab = scanner.nextLine();
                Colaborador responsavel = fluxoExecutivo.buscarColaborador(loginColab);

                fluxoExecutivo.registrarTarefa(projeto, titulo, desc, prazo, responsavel);
        }

        private static LocalDate lerData(String mensagem) {
                while (true) {
                        try {
                                System.out.print(mensagem);
                                return LocalDate.parse(scanner.nextLine(), formatter);
                        } catch (DateTimeParseException e) {
                                System.out.println("[!] Formato de data inválido. Use dd/mm/aaaa.");
                        }
                }
        }

        private static void exibirLogs(long tempoInicio) {
                long tempoFim = System.currentTimeMillis();
                System.out.println("\n[LOGS DE DESEMPENHO E SISTEMA]");
                System.out.println("> Uptime do Sistema: " + (tempoFim - tempoInicio) + "ms");
                System.out.println("> Status: Operacional em conformidade com as Regras de Negócio.");
        }
}
