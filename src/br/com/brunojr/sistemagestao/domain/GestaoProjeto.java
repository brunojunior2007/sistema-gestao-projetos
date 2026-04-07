package br.com.brunojr.sistemagestao.domain;

import java.time.LocalDate;

/**
 * Entidade central para a estrutura de Gestão de Projetos corporativos.
 * Mantém os dados cronológicos, de status e responsabilidade direta de gerência.
 */
public class GestaoProjeto {
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataTerminoPrevista;
    private StatusProjeto status;
    private Colaborador gerente;

    /**
     * Representação dos estágios do ciclo de vida do projeto.
     */
    public enum StatusProjeto {
        PLANEJADO, EM_ANDAMENTO, CONCLUIDO, CANCELADO
    }

    /**
     * Inicializa a estrutura de Gestão do Projeto de forma validada.
     * 
     * @param nome                Identificação do projeto.
     * @param descricao           Resumo executivo do escopo.
     * @param dataInicio          Data inicial do cronograma operacional.
     * @param dataTerminoPrevista Previsão final do cronograma.
     * @param gerente             Colaborador investido do papel de coordenação/gerência.
     */
    public GestaoProjeto(String nome, String descricao, LocalDate dataInicio,
                         LocalDate dataTerminoPrevista, Colaborador gerente) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("A nomenclatura do projeto não deve ser nula ou em branco.");
        }
        if (gerente == null) {
            throw new IllegalArgumentException("É imperativo designar um Gerente Responsável para o fluxo do projeto.");
        }
        if (gerente.getPerfil() != Colaborador.PerfilColaborador.GERENTE &&
            gerente.getPerfil() != Colaborador.PerfilColaborador.ADMINISTRADOR) {
            throw new IllegalArgumentException("O nível de alçada do responsável deve ser Gerencial ou Administrativo.");
        }
        if (dataInicio != null && dataTerminoPrevista != null && dataTerminoPrevista.isBefore(dataInicio)) {
            throw new IllegalArgumentException("Inconsistência cronológica: O término previsto antecede a data de início.");
        }

        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataTerminoPrevista = dataTerminoPrevista;
        this.status = StatusProjeto.PLANEJADO;
        this.gerente = gerente;
    }

    /**
     * Transita o projeto para a próxima fase estratégica.
     * 
     * @param novoStatus O estágio atualizado de andamento.
     */
    public void atualizarStatus(StatusProjeto novoStatus) {
        this.status = novoStatus;
    }

    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public LocalDate getDataInicio() { return dataInicio; }
    public LocalDate getDataTerminoPrevista() { return dataTerminoPrevista; }
    public StatusProjeto getStatus() { return status; }
    public Colaborador getGerente() { return gerente; }

    @Override
    public String toString() {
        return "Painel Estratégico [" + status + "] " + nome + " | Liderança: " + gerente.getNome();
    }
}
