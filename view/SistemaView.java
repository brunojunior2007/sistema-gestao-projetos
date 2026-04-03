package view;

import model.Equipe;
import model.Projeto;
import model.Usuario;

public class SistemaView {

    private static final String LINHA = "─────────────────────────────────────────────";

    public void exibirMensagem(String msg)  { System.out.println(">> " + msg); }
    public void exibirAlerta(String msg)    { System.out.println("⚠️  [ALERTA] " + msg); }
    public void exibirSeparador(String titulo) {
        System.out.println("\n--- " + titulo + " ---");
    }

    public void exibirPainelUsuario(Usuario u) {
        System.out.println("┌" + LINHA + "┐");
        System.out.println("│ 👤 USUÁRIO");
        System.out.println("│ Nome   : " + u.getNome());
        System.out.println("│ CPF    : " + u.getCpf());
        System.out.println("│ E-mail : " + u.getEmail());
        System.out.println("│ Cargo  : " + u.getCargo());
        System.out.println("│ Login  : " + u.getLogin());
        System.out.println("│ Perfil : " + u.getPerfil());
        System.out.println("└" + LINHA + "┘");
    }

    public void exibirPainelProjeto(Projeto p) {
        System.out.println("┌" + LINHA + "┐");
        System.out.println("│ 📁 PROJETO");
        System.out.println("│ Nome         : " + p.getNome());
        System.out.println("│ Descrição    : " + p.getDescricao());
        System.out.println("│ Início       : " + p.getDataInicio());
        System.out.println("│ Término prev.: " + p.getDataTerminoPrevista());
        System.out.println("│ Status       : " + p.getStatus());
        System.out.println("│ Gerente      : " + p.getGerente().getNome()
                           + " (" + p.getGerente().getPerfil() + ")");
        System.out.println("└" + LINHA + "┘");
    }

    public void exibirPainelEquipe(Equipe e) {
        System.out.println("┌" + LINHA + "┐");
        System.out.println("│ 👥 EQUIPE: " + e.getNome());
        System.out.println("│ Descrição : " + e.getDescricao());
        System.out.println("│ Membros   :");
        e.getMembros().forEach(m -> System.out.println("│   - " + m.getNome() + " [" + m.getPerfil() + "]"));
        System.out.println("│ Projetos vinculados:");
        e.getProjetos().forEach(p -> System.out.println("│   - " + p.getNome() + " [" + p.getStatus() + "]"));
        System.out.println("└" + LINHA + "┘");
    }
}
