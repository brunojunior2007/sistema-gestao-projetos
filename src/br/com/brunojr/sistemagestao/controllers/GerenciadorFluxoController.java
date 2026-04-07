package br.com.brunojr.sistemagestao.controllers;

import br.com.brunojr.sistemagestao.domain.Time;
import br.com.brunojr.sistemagestao.domain.GestaoProjeto;
import br.com.brunojr.sistemagestao.domain.Colaborador;
import br.com.brunojr.sistemagestao.repositories.TimeRepository;
import br.com.brunojr.sistemagestao.repositories.GestaoProjetoRepository;
import br.com.brunojr.sistemagestao.repositories.ColaboradorRepository;
import br.com.brunojr.sistemagestao.ui.InterfaceConsole;

import java.time.LocalDate;

/**
 * Módulo Orquestrador e Gerenciador de Fluxos de Negócio.
 * Estabelece a ponte executiva entre a UI e a Camada de Repositórios e
 * Entidades.
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
    public Colaborador registrarColaborador(String nome, String cpf, String email,
            String cargo, String login, String senha,
            Colaborador.PerfilColaborador perfil) {
        try {
            Colaborador colaboradorAtual = new Colaborador(nome, cpf, email, cargo, login, senha, perfil);
            colaboradorRepo.salvarNoBanco(colaboradorAtual);
            interfaceUsuario.exibirMensagem(
                    "Colaborador deferido na base de integração: " + nome + " | Privilégio: [" + perfil + "]");
            interfaceUsuario.exibirPainelColaborador(colaboradorAtual);
            return colaboradorAtual;
        } catch (IllegalArgumentException e) {
            interfaceUsuario.exibirAlerta("Desvio operacional reportado na adesão sistêmica: " + e.getMessage());
            return null;
        }
    }

    // ── PROJETOS ──────────────────────────────────────────────
    /**
     * Instrumenta o nascimento do ciclo de vida de uma intervenção de Gestão de
     * Projetos.
     */
    public GestaoProjeto registrarProjeto(String nome, String descricao,
            LocalDate inicio, LocalDate terminoPrevisto,
            Colaborador gerente) {
        try {
            GestaoProjeto projetoAtual = new GestaoProjeto(nome, descricao, inicio, terminoPrevisto, gerente);
            projetoRepo.salvarProjeto(projetoAtual);
            interfaceUsuario.exibirMensagem("Escopo de projeto chancelado com presteza.");
            interfaceUsuario.exibirPainelProjeto(projetoAtual);
            return projetoAtual;
        } catch (IllegalArgumentException e) {
            interfaceUsuario.exibirAlerta("Bloqueio impeditivo no comitê de formação de projeto: " + e.getMessage());
            return null;
        }
    }

    /**
     * Promove uma migração estrutural nas fases do ciclo ativo.
     */
    public void atualizarStatusProjeto(GestaoProjeto projetoAtual, GestaoProjeto.StatusProjeto novoStatus) {
        if (projetoAtual == null) {
            interfaceUsuario.exibirAlerta("Ativo de projeto não mapeado na malha analítica.");
            return;
        }
        projetoAtual.atualizarStatus(novoStatus);
        interfaceUsuario.exibirMensagem("Dashboard revisado. Diretriz macro do projeto '" + projetoAtual.getNome()
                + "' impulsionada para a fase de " + novoStatus);
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
            interfaceUsuario.exibirAlerta("Obstrução sistemática constatada na formação do núcleo: " + ex.getMessage());
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
}
