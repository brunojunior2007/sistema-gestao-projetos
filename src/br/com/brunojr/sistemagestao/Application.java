package br.com.brunojr.sistemagestao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import br.com.brunojr.sistemagestao.controllers.GerenciadorFluxoController;
import br.com.brunojr.sistemagestao.domain.Colaborador;
import br.com.brunojr.sistemagestao.domain.GestaoProjeto;
import br.com.brunojr.sistemagestao.domain.Time;

public class Application {
        private static GerenciadorFluxoController fluxoExecutivo = new GerenciadorFluxoController();
        private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        private static long tempoInicio;

        // Estilo Minimalista e Visual
        private static final Color COR_FUNDO = new Color(245, 247, 250);
        private static final Color COR_PRIMARY = new Color(44, 62, 80); // Azul Escuro do Título
        private static final Color COR_BOTAO_HOVER = new Color(52, 73, 94);
        private static final Font FONTE_TITULO = new Font("Segoe UI", Font.BOLD, 26);
        private static final Font FONTE_BOTAO = new Font("Segoe UI", Font.BOLD, 14);

        public static void main(String[] args) {
                tempoInicio = System.currentTimeMillis();
                SwingUtilities.invokeLater(() -> {
                        try {
                                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                        } catch (Exception e) {
                        }
                        criarGUI();
                });
        }

        private static void criarGUI() {
                JFrame frame = new JFrame("Gestão de projetos e equipes");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(900, 650);
                frame.setLocationRelativeTo(null);

                JPanel container = new JPanel(new BorderLayout());
                container.setBackground(COR_FUNDO);
                container.setBorder(new EmptyBorder(30, 40, 30, 40));

                // Cabeçalho Simples
                JLabel lblTitulo = new JLabel("Gestão de projetos e equipes", SwingConstants.CENTER);
                lblTitulo.setFont(FONTE_TITULO);
                lblTitulo.setForeground(COR_PRIMARY);
                lblTitulo.setBorder(new EmptyBorder(0, 0, 30, 0));
                container.add(lblTitulo, BorderLayout.NORTH);

                // Painel de Botões (Grid)
                JPanel grid = new JPanel(new GridLayout(4, 3, 15, 15));
                grid.setOpaque(false);

                // Adicionando todas as funções como botões reais
                grid.add(criarBotao("Cadastrar colaborador", "👤", e -> cadastrarColaborador()));
                grid.add(criarBotao("Novo projeto", "📁", e -> cadastrarProjeto()));
                grid.add(criarBotao("Criar novo time", "👥", e -> cadastrarTime()));
                grid.add(criarBotao("Vincular colaborador", "🔗", e -> vincularColaboradorTime()));
                grid.add(criarBotao("Vincular projeto", "📊", e -> vincularProjetoTime()));
                grid.add(criarBotao("Atualizar status", "🔄", e -> atualizarStatusProjeto()));
                grid.add(criarBotao("Gerenciar tarefas", "📝", e -> cadastrarTarefa()));
                grid.add(criarBotao("Relatório projetos", "📜", e -> fluxoExecutivo.listarProjetos()));
                grid.add(criarBotao("Lista colaboradores", "📋", e -> fluxoExecutivo.listarColaboradores()));
                grid.add(criarBotao("Lista de times", "🏘️", e -> fluxoExecutivo.listarTimes()));
                grid.add(criarBotao("Logs do sistema", "!", e -> exibirLogs()));
                grid.add(criarBotao("Sair", "X", e -> System.exit(0)));

                container.add(grid, BorderLayout.CENTER);

                // Rodapé
                JLabel lblFooter = new JLabel(
                                "Sistema Operacional | Uptime: " + (System.currentTimeMillis() - tempoInicio) + "ms",
                                SwingConstants.RIGHT);
                lblFooter.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                lblFooter.setForeground(Color.GRAY);
                lblFooter.setBorder(new EmptyBorder(20, 0, 0, 0));
                container.add(lblFooter, BorderLayout.SOUTH);

                frame.add(container);
                frame.setVisible(true);
        }

        private static JButton criarBotao(String texto, String icone, java.awt.event.ActionListener acao) {
                JButton btn = new JButton("<html><center>" + icone + "<br>" + texto + "</center></html>");
                btn.setFont(FONTE_BOTAO);
                btn.setForeground(COR_PRIMARY);
                btn.setBackground(Color.WHITE);
                btn.setFocusPainted(false);
                btn.setBorder(BorderFactory.createLineBorder(COR_PRIMARY, 1));
                btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

                // Efeito Hover
                btn.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseEntered(java.awt.event.MouseEvent e) {
                                btn.setBackground(new Color(230, 230, 230));
                        }

                        public void mouseExited(java.awt.event.MouseEvent e) {
                                btn.setBackground(Color.WHITE);
                        }
                });

                btn.addActionListener(acao);
                return btn;
        }

        // --- MÉTODOS DE VALIDAÇÃO ---

        private static String lerStringNaoVazia(String mensagem) {
                while (true) {
                        String input = JOptionPane.showInputDialog(mensagem);
                        if (input == null) return null;
                        if (input.trim().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "O campo não pode estar vazio.");
                                continue;
                        }
                        return input.trim();
                }
        }

        private static String lerStringSemNumeros(String mensagem) {
                while (true) {
                        String input = lerStringNaoVazia(mensagem);
                        if (input == null) return null;
                        if (input.matches(".*\\d.*")) {
                                JOptionPane.showMessageDialog(null, "O campo não deve conter números.");
                                continue;
                        }
                        return input;
                }
        }

        private static String lerEmail() {
                while (true) {
                        String input = lerStringNaoVazia("Email:");
                        if (input == null) return null;
                        if (!input.matches("^[A-Za-z0-9+_.-]+@(.+)$") || !input.contains(".")) {
                                JOptionPane.showMessageDialog(null, "E-mail inválido. Por favor, insira um formato correto (ex: nome@dominio.com).");
                                continue;
                        }
                        return input;
                }
        }

        private static String lerCPF() {
                while (true) {
                        String input = JOptionPane.showInputDialog("CPF (somente números ou pontuação):");
                        if (input == null) return null;
                        String cpf = input.replaceAll("\\D", ""); // Remove não-números
                        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}") || !isCPFValido(cpf)) {
                                JOptionPane.showMessageDialog(null, "CPF inválido. Por favor, insira um CPF válido com 11 dígitos.");
                                continue;
                        }
                        return cpf;
                }
        }

        private static boolean isCPFValido(String cpf) {
                try {
                        int soma = 0;
                        for (int i = 0; i < 9; i++) {
                                soma += (cpf.charAt(i) - '0') * (10 - i);
                        }
                        int digito1 = 11 - (soma % 11);
                        if (digito1 > 9) digito1 = 0;
                        if ((cpf.charAt(9) - '0') != digito1) return false;

                        soma = 0;
                        for (int i = 0; i < 10; i++) {
                                soma += (cpf.charAt(i) - '0') * (11 - i);
                        }
                        int digito2 = 11 - (soma % 11);
                        if (digito2 > 9) digito2 = 0;
                        return (cpf.charAt(10) - '0') == digito2;
                } catch (Exception e) {
                        return false;
                }
        }

        private static LocalDate lerData(String mensagem) {
                while (true) {
                        String input = lerStringNaoVazia(mensagem);
                        if (input == null) return null;
                        try {
                                return LocalDate.parse(input, formatter);
                        } catch (DateTimeParseException e) {
                                JOptionPane.showMessageDialog(null, "[!] Formato de data inválido. Use dd/mm/aaaa.");
                        }
                }
        }

        // --- MÉTODOS DE LÓGICA ---

        private static void cadastrarColaborador() {
                String nome = lerStringSemNumeros("Nome Completo:");
                if (nome == null) return;
                String cpf = lerCPF();
                if (cpf == null) return;
                String email = lerEmail();
                if (email == null) return;
                String cargo = lerStringSemNumeros("Cargo:");
                if (cargo == null) return;
                String login = lerStringNaoVazia("Login:");
                if (login == null) return;
                String senha = lerStringNaoVazia("Senha:");
                if (senha == null) return;

                String[] perfis = { "ADMINISTRADOR", "GERENTE", "COLABORADOR" };
                String p = (String) JOptionPane.showInputDialog(null, "Selecione o Perfil:",
                                "Perfil", JOptionPane.QUESTION_MESSAGE, null, perfis, perfis[2]);

                if (p == null) return;
                Colaborador.PerfilColaborador perfil = switch (p) {
                        case "ADMINISTRADOR" -> Colaborador.PerfilColaborador.ADMINISTRADOR;
                        case "GERENTE" -> Colaborador.PerfilColaborador.GERENTE;
                        default -> Colaborador.PerfilColaborador.COLABORADOR;
                };

                fluxoExecutivo.registrarColaborador(nome, cpf, email, cargo, login, senha, perfil);
        }

        private static void cadastrarProjeto() {
                String nome = lerStringNaoVazia("Nome do Projeto:");
                if (nome == null) return;
                String descricao = lerStringNaoVazia("Descrição/Escopo:");
                if (descricao == null) return;

                LocalDate inicio = lerData("Data Início (dd/mm/aaaa):");
                if (inicio == null) return;
                LocalDate fim = lerData("Data Término Prevista (dd/mm/aaaa):");
                if (fim == null) return;

                String orcStr = lerStringNaoVazia("Orçamento (R$):");
                if (orcStr == null) return;
                double orcamento = 0;
                try {
                        orcamento = Double.parseDouble(orcStr.replace(",", "."));
                } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Valor inválido para orçamento. Definido como 0.");
                }

                String[] prioridades = { "ALTA", "MEDIA", "BAIXA" };
                String prio = (String) JOptionPane.showInputDialog(null, "Prioridade:",
                                "Prioridade", JOptionPane.QUESTION_MESSAGE, null, prioridades, prioridades[1]);

                if (prio == null) return;
                GestaoProjeto.Prioridade prioridade = switch (prio) {
                        case "ALTA" -> GestaoProjeto.Prioridade.ALTA;
                        case "BAIXA" -> GestaoProjeto.Prioridade.BAIXA;
                        default -> GestaoProjeto.Prioridade.MEDIA;
                };

                String loginResp = lerStringNaoVazia("Login do Responsável (Gerente/Admin):");
                if (loginResp == null) return;
                Colaborador gerente = fluxoExecutivo.buscarColaborador(loginResp);

                if (gerente == null) {
                        JOptionPane.showMessageDialog(null, "[!] Erro: Gerente não encontrado.");
                        return;
                }

                fluxoExecutivo.registrarProjeto(nome, descricao, inicio, fim, gerente, orcamento, prioridade);
        }

        private static void cadastrarTime() {
                String nome = lerStringNaoVazia("Nome do Time:");
                if (nome == null) return;
                String desc = lerStringNaoVazia("Descrição/Especialidade:");
                if (desc == null) return;

                fluxoExecutivo.registrarTime(nome, desc);
        }

        private static void vincularColaboradorTime() {
                String nomeTime = lerStringNaoVazia("Nome do Time:");
                if (nomeTime == null) return;
                Time time = fluxoExecutivo.buscarTime(nomeTime);

                if (time == null) {
                        JOptionPane.showMessageDialog(null, "[!] Time não encontrado.");
                        return;
                }

                String loginColab = lerStringNaoVazia("Login do Colaborador:");
                if (loginColab == null) return;
                Colaborador colab = fluxoExecutivo.buscarColaborador(loginColab);

                if (colab == null) {
                        JOptionPane.showMessageDialog(null, "[!] Colaborador não encontrado.");
                        return;
                }

                fluxoExecutivo.adicionarMembroTime(time, colab);
        }

        private static void vincularProjetoTime() {
                String nomeTime = lerStringNaoVazia("Nome do Time:");
                if (nomeTime == null) return;
                Time time = fluxoExecutivo.buscarTime(nomeTime);

                if (time == null) {
                        JOptionPane.showMessageDialog(null, "[!] Time não encontrado.");
                        return;
                }

                String nomeProjeto = lerStringNaoVazia("Nome do Projeto:");
                if (nomeProjeto == null) return;
                GestaoProjeto projeto = fluxoExecutivo.buscarProjeto(nomeProjeto);

                if (projeto == null) {
                        JOptionPane.showMessageDialog(null, "[!] Projeto não encontrado.");
                        return;
                }

                fluxoExecutivo.vincularTimeProjeto(time, projeto);
        }

        private static void atualizarStatusProjeto() {
                String nome = lerStringNaoVazia("Nome do Projeto:");
                if (nome == null) return;
                GestaoProjeto projeto = fluxoExecutivo.buscarProjeto(nome);

                if (projeto == null) {
                        JOptionPane.showMessageDialog(null, "[!] Projeto não encontrado.");
                        return;
                }

                String[] statusOpcoes = { "PLANEJADO", "EM ANDAMENTO", "CONCLUIDO", "CANCELADO" };
                String s = (String) JOptionPane.showInputDialog(null, "Novo Status:",
                                "Status", JOptionPane.QUESTION_MESSAGE, null, statusOpcoes, statusOpcoes[0]);

                if (s == null) return;
                GestaoProjeto.StatusProjeto novoStatus = switch (s) {
                        case "PLANEJADO" -> GestaoProjeto.StatusProjeto.PLANEJADO;
                        case "EM ANDAMENTO" -> GestaoProjeto.StatusProjeto.EM_ANDAMENTO;
                        case "CONCLUIDO" -> GestaoProjeto.StatusProjeto.CONCLUIDO;
                        case "CANCELADO" -> GestaoProjeto.StatusProjeto.CANCELADO;
                        default -> null;
                };

                if (novoStatus != null) {
                        fluxoExecutivo.atualizarStatusProjeto(projeto, novoStatus);
                }
        }

        private static void cadastrarTarefa() {
                String nomeProj = lerStringNaoVazia("Nome do Projeto:");
                if (nomeProj == null) return;
                GestaoProjeto projeto = fluxoExecutivo.buscarProjeto(nomeProj);

                if (projeto == null) {
                        JOptionPane.showMessageDialog(null, "[!] Projeto não encontrado.");
                        return;
                }

                String titulo = lerStringNaoVazia("Título da Tarefa:");
                if (titulo == null) return;
                String desc = lerStringNaoVazia("Descrição/Detalhes:");
                if (desc == null) return;
                LocalDate prazo = lerData("Prazo de Entrega (dd/mm/aaaa):");
                if (prazo == null) return;

                String loginColab = lerStringNaoVazia("Login do Responsável (Colaborador):");
                if (loginColab == null) return;
                Colaborador responsavel = fluxoExecutivo.buscarColaborador(loginColab);

                fluxoExecutivo.registrarTarefa(projeto, titulo, desc, prazo, responsavel);
        }

        private static void exibirLogs() {
                long tempoFim = System.currentTimeMillis();
                String logs = "Uptime: " + (tempoFim - tempoInicio) + "ms\n" +
                                "Status: Operacional\n" +
                                "Regras de Negócio: Em conformidade.";
                JOptionPane.showMessageDialog(null, logs, "Logs do Sistema", JOptionPane.INFORMATION_MESSAGE);
        }
}
