package model;

import java.time.LocalDate;

public class Projeto {
    // Requisito: nome, descrição, data início, data término prevista, status, gerente responsável
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataTerminoPrevista;
    private StatusProjeto status;
    private Usuario gerente;

    public enum StatusProjeto {
        PLANEJADO, EM_ANDAMENTO, CONCLUIDO, CANCELADO
    }

    public Projeto(String nome, String descricao, LocalDate dataInicio,
                   LocalDate dataTerminoPrevista, Usuario gerente) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome do projeto não pode ser vazio.");
        if (gerente == null)
            throw new IllegalArgumentException("Todo projeto precisa ter um Gerente Responsável.");
        if (gerente.getPerfil() != Usuario.PerfilUsuario.GERENTE &&
            gerente.getPerfil() != Usuario.PerfilUsuario.ADMINISTRADOR)
            throw new IllegalArgumentException("O responsável deve ter perfil de Gerente ou Administrador.");
        if (dataInicio != null && dataTerminoPrevista != null &&
            dataTerminoPrevista.isBefore(dataInicio))
            throw new IllegalArgumentException("Data de término não pode ser anterior à data de início.");

        this.nome                 = nome;
        this.descricao            = descricao;
        this.dataInicio           = dataInicio;
        this.dataTerminoPrevista  = dataTerminoPrevista;
        this.status               = StatusProjeto.PLANEJADO; // começa sempre como PLANEJADO
        this.gerente              = gerente;
    }

    // Permite avançar o status do projeto
    public void atualizarStatus(StatusProjeto novoStatus) {
        this.status = novoStatus;
    }

    public String getNome()                     { return nome; }
    public String getDescricao()               { return descricao; }
    public LocalDate getDataInicio()           { return dataInicio; }
    public LocalDate getDataTerminoPrevista()  { return dataTerminoPrevista; }
    public StatusProjeto getStatus()           { return status; }
    public Usuario getGerente()                { return gerente; }

    @Override
    public String toString() {
        return "[" + status + "] " + nome + " | Gerente: " + gerente.getNome();
    }
}
