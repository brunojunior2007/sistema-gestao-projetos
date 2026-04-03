package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Equipe {
    // Requisito: nome, descrição, membros (usuários vinculados)
    // Requisito: uma equipe pode atuar em vários projetos
    private String nome;
    private String descricao;
    private List<Usuario> membros;
    private List<Projeto> projetos;

    public Equipe(String nome, String descricao) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome da equipe não pode ser vazio.");
        this.nome     = nome;
        this.descricao = descricao;
        this.membros  = new ArrayList<>();
        this.projetos = new ArrayList<>();
    }

    public void adicionarMembro(Usuario usuario) {
        if (usuario == null) throw new IllegalArgumentException("Usuário inválido.");
        if (!membros.contains(usuario)) {
            membros.add(usuario);
        }
    }

    public void vincularProjeto(Projeto projeto) {
        if (projeto == null) throw new IllegalArgumentException("Projeto inválido.");
        if (!projetos.contains(projeto)) {
            projetos.add(projeto);
        }
    }

    public String getNome()                   { return nome; }
    public String getDescricao()             { return descricao; }
    public List<Usuario> getMembros()        { return Collections.unmodifiableList(membros); }
    public List<Projeto> getProjetos()       { return Collections.unmodifiableList(projetos); }

    @Override
    public String toString() {
        return "Equipe: " + nome + " (" + membros.size() + " membros, " + projetos.size() + " projetos)";
    }
}
