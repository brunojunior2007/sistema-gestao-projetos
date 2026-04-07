package br.com.brunojr.sistemagestao.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Entidade que representa um Time (antiga Equipe) no sistema.
 * Gerencia o grupo de colaboradores designados e os projetos nos quais operam.
 */
public class Time {
    private String nome;
    private String descricao;
    private List<Colaborador> membros;
    private List<GestaoProjeto> projetos;

    /**
     * Construtor principal para criação de um Time.
     * 
     * @param nome      Nome oficial do time.
     * @param descricao Breve descrição do propósito ou especialidade do time.
     */
    public Time(String nome, String descricao) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do time deve ser providenciado.");
        }
        this.nome = nome;
        this.descricao = descricao;
        this.membros = new ArrayList<>();
        this.projetos = new ArrayList<>();
    }

    /**
     * Adiciona um novo colaborador ao quadro de membros deste time.
     * 
     * @param colaboradorAtual Instância de colaborador a ser vinculada.
     */
    public void adicionarMembro(Colaborador colaboradorAtual) {
        if (colaboradorAtual == null) {
            throw new IllegalArgumentException("Colaborador inválido fornecido para integração.");
        }
        if (!membros.contains(colaboradorAtual)) {
            membros.add(colaboradorAtual);
        }
    }

    /**
     * Associa um projeto ativo às responsabilidades deste time.
     * 
     * @param projetoAtual O projeto cuja execução será de responsabilidade deste time.
     */
    public void vincularProjeto(GestaoProjeto projetoAtual) {
        if (projetoAtual == null) {
            throw new IllegalArgumentException("Projeto inválido para associação estratégica.");
        }
        if (!projetos.contains(projetoAtual)) {
            projetos.add(projetoAtual);
        }
    }

    public String getNome() { 
        return nome; 
    }
    
    public String getDescricao() { 
        return descricao; 
    }
    
    public List<Colaborador> getMembros() { 
        return Collections.unmodifiableList(membros); 
    }
    
    public List<GestaoProjeto> getProjetos() { 
        return Collections.unmodifiableList(projetos); 
    }

    @Override
    public String toString() {
        return "Time de Operação: " + nome + " (Efetivo: " + membros.size() + ", Projetos Alocados: " + projetos.size() + ")";
    }
}
