package br.com.brunojr.sistemagestao.controllers;

import java.time.LocalDate;
import br.com.brunojr.sistemagestao.domain.Colaborador;
import br.com.brunojr.sistemagestao.domain.GestaoProjeto;
import br.com.brunojr.sistemagestao.domain.Tarefa;
import br.com.brunojr.sistemagestao.domain.Time;
import br.com.brunojr.sistemagestao.repositories.ColaboradorRepository;
import br.com.brunojr.sistemagestao.repositories.GestaoProjetoRepository;
import br.com.brunojr.sistemagestao.repositories.TimeRepository;
import br.com.brunojr.sistemagestao.ui.InterfaceConsole;

/**
 * Módulo Orquestrador e Gerenciador de Fluxos de Negócio. Estabelece a ponte
 * executiva entre a UI e
 * a Camada de Repositórios e Entidades.
 */

public class GerenciadorFluxoController {
    private ColaboradorRepository colaboradorRepo;
    private GestaoProjetoRepository projetoRepo;
    private TimeRepository timeRepo;
    private InterfaceConsole interfaceUsuario;

    public GerenciadorFluxoController() {
        this.colaboradorRepo = new ColaboradorRepository();
        this.projetoRepo = new GestaoProjetoRepository();
        this.timeRepo = new TimeRepository();
        this.interfaceUsuario = new InterfaceConsole();
    }

    // ── COLABORADORES ──────────────────────────────────────────────
    /**
     * Coordena o fluxo de designação de Colaboradores ao quadro interno da
     * companhia.
     */
    public Colaborador registrarColaborador(String nome, String cpf, String email, String cargo,
            String login, String senha, Colaborador.PerfilColaborador perfil) {
        try {
            Colaborador colaboradorAtual = new Colaborador(nome, cpf, email, cargo, login, senha, perfil);
            colaboradorRepo.salvarNoBanco(colaboradorAtual);
            interfaceUsuario.exibirMensagem("Colaborador deferido na base de integração: " + nome
                    + " | Privilégio: [" + perfil + "]");
            interfaceUsuario.exibirPainelColaborador(colaboradorAtual);
            return colaboradorAtual;
        } catch (IllegalArgumentException e) {
            interfaceUsuario.exibirAlerta(
                    "Desvio operacional reportado na adesão sistêmica: " + e.getMessage());
            return null;
        }
    }

    // ── PROJETOS ──────────────────────────────────────────────
    /**
     * Instrumenta o nascimento do ciclo de vida de uma intervenção de Gestão de
     * Projetos.
     */
    public GestaoProjeto registrarProjeto(String nome, String descricao, LocalDate inicio,
            LocalDate terminoPrevisto, Colaborador gerente, double orcamento,
            GestaoProjeto.Prioridade prioridade) {
        try {
            GestaoProjeto projetoAtual = new GestaoProjeto(nome, descricao, inicio, terminoPrevisto,
                    gerente, orcamento, prioridade);
            projetoRepo.salvarProjeto(projetoAtual);
            interfaceUsuario.exibirMensagem("Escopo de projeto chancelado com presteza.");
            interfaceUsuario.exibirPainelProjeto(projetoAtual);
            return projetoAtual;
        } catch (IllegalArgumentException e) {
            interfaceUsuario.exibirAlerta(
                    "Bloqueio impeditivo no comitê de formação de projeto: " + e.getMessage());
            return null;
        }
    }

    /**
     * Promove uma migração estrutural nas fases do ciclo ativo.
     */
    public void atualizarStatusProjeto(GestaoProjeto projetoAtual,
            GestaoProjeto.StatusProjeto novoStatus) {
        if (projetoAtual == null) {
            interfaceUsuario.exibirAlerta("Ativo de projeto não mapeado na malha analítica.");
            return;
        }
        projetoAtual.atualizarStatus(novoStatus);
        interfaceUsuario.exibirMensagem("Dashboard revisado. Diretriz macro do projeto '"
                + projetoAtual.getNome() + "' impulsionada para a fase de " + novoStatus);
    }

    public Tarefa registrarTarefa(GestaoProjeto projeto, String titulo, String desc,
            LocalDate prazo, Colaborador responsavel) {
        try {
            Tarefa tarefa = new Tarefa(titulo, desc, prazo, responsavel);
            projeto.adicionarTarefa(tarefa);
            interfaceUsuario.exibirMensagem(
                    "Tarefa '" + titulo + "' acoplada ao projeto '" + projeto.getNome() + "'.");
            return tarefa;
        } catch (IllegalArgumentException e) {
            interfaceUsuario.exibirAlerta("Falha na geração de tarefa: " + e.getMessage());
            return null;
        }
    }

    // ── TIMES DE ALTA PERFORMANCE (EQUIPES)
    // ───────────────────────────────────────────────
    /**
     * Modela a capacidade de times produtivos em portfólio.
     */
    public Time registrarTime(String nome, String descricao) {
        try {
            Time timeAtual = new Time(nome, descricao);
            timeRepo.salvarTime(timeAtual);
            interfaceUsuario.exibirMensagem("Time operacional mobilizado: " + nome);
            return timeAtual;
        } catch (IllegalArgumentException ex) {
            interfaceUsuario.exibirAlerta(
                    "Obstrução sistemática constatada na formação do núcleo: " + ex.getMessage());
            return null;
        }
    }

    /**
     * Consolida a alocação de recursos departamentais.
     */
    public void adicionarMembroTime(Time timeAtual, Colaborador colaboradorAtual) {
        if (timeAtual == null || colaboradorAtual == null)
            return;
        timeAtual.adicionarMembro(colaboradorAtual);
        interfaceUsuario.exibirMensagem("Ativo humano " + colaboradorAtual.getNome()
                + " homologado formalmente à divisão '" + timeAtual.getNome() + "'.");
    }

    /**
     * Cruza times especializados e demandas de negócio complexas.
     */
    public void vincularTimeProjeto(Time timeAtual, GestaoProjeto projetoAtual) {
        if (timeAtual == null || projetoAtual == null)
            return;
        timeAtual.vincularProjeto(projetoAtual);
        interfaceUsuario.exibirMensagem("A Divisão de Elite '" + timeAtual.getNome()
                + "' recebeu a outorga estratégica no projeto '" + projetoAtual.getNome() + "'.");
    }

    public void exibirTime(Time timeAtual) {
        if (timeAtual != null)
            interfaceUsuario.exibirPainelTime(timeAtual);
    }

    // ── MÉTODOS DE CONSULTA ──────────────────────────────────────────

    public Colaborador buscarColaborador(String login) {
        return colaboradorRepo.buscarPorLogin(login).orElse(null);
    }

    public GestaoProjeto buscarProjeto(String nome) {
        return projetoRepo.buscarPorNome(nome).orElse(null);
    }

    public Time buscarTime(String nome) {
        return timeRepo.buscarPorNome(nome).orElse(null);
    }

    public void listarColaboradores() {
        var colaboradores = colaboradorRepo.listarTodos();
        if (colaboradores.isEmpty()) {
            interfaceUsuario.exibirMensagem("Nenhum colaborador cadastrado no sistema.");
        } else {
            interfaceUsuario.exibirSeparador("Lista de Colaboradores");
            colaboradores.forEach(interfaceUsuario::exibirPainelColaborador);
        }
    }

    public void listarProjetos() {
        var projetos = projetoRepo.listarProjetos();
        if (projetos.isEmpty()) {
            interfaceUsuario.exibirMensagem("Nenhum projeto cadastrado no sistema.");
        } else {
            interfaceUsuario.exibirSeparador("Lista de Projetos (Relatório de Desempenho)");
            projetos.forEach(interfaceUsuario::exibirPainelProjeto);
        }
    }

    public void listarTimes() {
        var times = timeRepo.listarTimes();
        if (times.isEmpty()) {
            interfaceUsuario.exibirMensagem("Nenhum time cadastrado no sistema.");
        } else {
            interfaceUsuario.exibirSeparador("Lista de Times");
            times.forEach(interfaceUsuario::exibirPainelTime);
        }
    }
}
