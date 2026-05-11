package br.com.brunojr.sistemagestao.domain;

import java.time.LocalDate;

/**
 * Entidade central para a estrutura de Gestão de Projetos corporativos.
 * Mantém os dados cronológicos, de status e responsabilidade direta de
 * gerência.
 */
public class GestaoProjeto {
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataTerminoPrevista;
    private StatusProjeto status;
    private Colaborador gerente;
    private double orcamento;
    private Prioridade prioridade;
    private java.util.List<Tarefa> tarefas;

    /**
     * Representação dos estágios do ciclo de vida do projeto.
     */
    public enum StatusProjeto {
        PLANEJADO, EM_ANDAMENTO, CONCLUIDO, CANCELADO
    }

    /**
     * Níveis de prioridade estratégica para o projeto.
     */
    public enum Prioridade {
        ALTA, MEDIA, BAIXA
    }

    /**
     * Inicializa a estrutura de Gestão do Projeto de forma validada.
     * 
     * @param nome                Identificação do projeto.
     * @param descricao           Resumo executivo do escopo.
     * @param dataInicio          Data inicial do cronograma operacional.
     * @param dataTerminoPrevista Previsão final do cronograma.
     * @param gerente             Colaborador investido do papel de
     *                            coordenação/gerência.
     * @param orcamento           Valor financeiro alocado ao projeto.
     * @param prioridade          Nível de importância estratégica.
     */
    public GestaoProjeto(String nome, String descricao, LocalDate dataInicio,
            LocalDate dataTerminoPrevista, Colaborador gerente,
            double orcamento, Prioridade prioridade) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("A nomenclatura do projeto não deve ser nula ou em branco.");
        }
        // [TRAVA DE RESPONSABILIDADE] - Garantia sistêmica via Construtor
        if (gerente == null) {
            throw new IllegalArgumentException("É imperativo designar um Gerente Responsável para o fluxo do projeto.");
        }
        if (gerente.getPerfil() != Colaborador.PerfilColaborador.GERENTE &&
                gerente.getPerfil() != Colaborador.PerfilColaborador.ADMINISTRADOR) {
            throw new IllegalArgumentException(
                    "O nível de alçada do responsável deve ser Gerencial ou Administrativo.");
        }
        if (dataInicio != null && dataTerminoPrevista != null && dataTerminoPrevista.isBefore(dataInicio)) {
            throw new IllegalArgumentException(
                    "Inconsistência cronológica: O término previsto antecede a data de início.");
        }
        if (orcamento < 0) {
            throw new IllegalArgumentException("O aporte financeiro (orçamento) não pode ser negativo.");
        }

        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataTerminoPrevista = dataTerminoPrevista;
        this.status = StatusProjeto.PLANEJADO;
        this.gerente = gerente;
        this.orcamento = orcamento;
        this.prioridade = prioridade != null ? prioridade : Prioridade.MEDIA;
        this.tarefas = new java.util.ArrayList<>();
    }

    /**
     * Transita o projeto para a próxima fase estratégica.
     * 
     * @param novoStatus O estágio atualizado de andamento.
     */
    public void atualizarStatus(StatusProjeto novoStatus) {
        this.status = novoStatus;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataTerminoPrevista() {
        return dataTerminoPrevista;
    }

    public StatusProjeto getStatus() {
        return status;
    }

    public Colaborador getGerente() {
        return gerente;
    }

    public double getOrcamento() {
        return orcamento;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public java.util.List<Tarefa> getTarefas() {
        return java.util.Collections.unmodifiableList(tarefas);
    }

    public void adicionarTarefa(Tarefa tarefa) {
        if (tarefa == null)
            throw new IllegalArgumentException("Tarefa inválida.");
        this.tarefas.add(tarefa);
    }

    @Override
    public String toString() {
        return "Painel Estratégico [" + status + "] " + nome + " | Liderança: " + gerente.getNome() + " | Prioridade: "
                + prioridade;
    }
}
