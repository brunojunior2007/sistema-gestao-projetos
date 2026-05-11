package br.com.brunojr.sistemagestao.domain;

import java.time.LocalDate;

/**
 * Representa uma unidade de trabalho (Tarefa/Entregável) dentro de um projeto.
 */
public class Tarefa {
    private String titulo;
    private String descricao;
    private LocalDate prazo;
    private StatusTarefa status;
    private Colaborador responsavel;

    public enum StatusTarefa {
        PENDENTE, EM_ANDAMENTO, CONCLUIDA
    }

    public Tarefa(String titulo, String descricao, LocalDate prazo, Colaborador responsavel) {
        if (titulo == null || titulo.isBlank())
            throw new IllegalArgumentException("Título da tarefa é obrigatório.");
        if (prazo == null)
            throw new IllegalArgumentException("Prazo de entrega é obrigatório.");

        this.titulo = titulo;
        this.descricao = descricao;
        this.prazo = prazo;
        this.responsavel = responsavel;
        this.status = StatusTarefa.PENDENTE;
    }

    public void atualizarStatus(StatusTarefa novoStatus) {
        this.status = novoStatus;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getPrazo() {
        return prazo;
    }

    public StatusTarefa getStatus() {
        return status;
    }

    public Colaborador getResponsavel() {
        return responsavel;
    }

    @Override
    public String toString() {
        return "Tarefa: " + titulo + " | Prazo: " + prazo + " | Status: " + status + " | Responsável: "
                + (responsavel != null ? responsavel.getNome() : "Não atribuído");
    }
}
