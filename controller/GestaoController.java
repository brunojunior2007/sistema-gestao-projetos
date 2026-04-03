package controller;

import model.Equipe;
import model.Projeto;
import model.Usuario;
import repository.EquipeRepository;
import repository.ProjetoRepository;
import repository.UsuarioRepository;
import view.SistemaView;

import java.time.LocalDate;

public class GestaoController {
    private UsuarioRepository usuarioRepo;
    private ProjetoRepository projetoRepo;
    private EquipeRepository  equipeRepo;
    private SistemaView view;

    public GestaoController() {
        this.usuarioRepo = new UsuarioRepository();
        this.projetoRepo = new ProjetoRepository();
        this.equipeRepo  = new EquipeRepository();
        this.view        = new SistemaView();
    }

    // ── USUÁRIOS ──────────────────────────────────────────────
    public Usuario registrarUsuario(String nome, String cpf, String email,
                                    String cargo, String login, String senha,
                                    Usuario.PerfilUsuario perfil) {
        try {
            Usuario u = new Usuario(nome, cpf, email, cargo, login, senha, perfil);
            usuarioRepo.salvarNoBanco(u);
            view.exibirMensagem("Usuário cadastrado: " + nome + " [" + perfil + "]");
            view.exibirPainelUsuario(u);
            return u;
        } catch (IllegalArgumentException e) {
            view.exibirAlerta("Erro ao cadastrar usuário: " + e.getMessage());
            return null;
        }
    }

    // ── PROJETOS ──────────────────────────────────────────────
    public Projeto registrarProjeto(String nome, String descricao,
                                    LocalDate inicio, LocalDate terminoPrevisto,
                                    Usuario gerente) {
        try {
            Projeto p = new Projeto(nome, descricao, inicio, terminoPrevisto, gerente);
            projetoRepo.salvarProjeto(p);
            view.exibirMensagem("Projeto criado com sucesso!");
            view.exibirPainelProjeto(p);
            return p;
        } catch (IllegalArgumentException e) {
            view.exibirAlerta("Erro ao criar projeto: " + e.getMessage());
            return null;
        }
    }

    public void atualizarStatusProjeto(Projeto projeto, Projeto.StatusProjeto novoStatus) {
        if (projeto == null) { view.exibirAlerta("Projeto não encontrado."); return; }
        projeto.atualizarStatus(novoStatus);
        view.exibirMensagem("Status do projeto '" + projeto.getNome() + "' atualizado para: " + novoStatus);
    }

    // ── EQUIPES ───────────────────────────────────────────────
    public Equipe registrarEquipe(String nome, String descricao) {
        try {
            Equipe e = new Equipe(nome, descricao);
            equipeRepo.salvarEquipe(e);
            view.exibirMensagem("Equipe criada: " + nome);
            return e;
        } catch (IllegalArgumentException ex) {
            view.exibirAlerta("Erro ao criar equipe: " + ex.getMessage());
            return null;
        }
    }

    public void adicionarMembroEquipe(Equipe equipe, Usuario usuario) {
        if (equipe == null || usuario == null) return;
        equipe.adicionarMembro(usuario);
        view.exibirMensagem(usuario.getNome() + " adicionado à equipe '" + equipe.getNome() + "'.");
    }

    public void vincularEquipeProjeto(Equipe equipe, Projeto projeto) {
        if (equipe == null || projeto == null) return;
        equipe.vincularProjeto(projeto);
        view.exibirMensagem("Equipe '" + equipe.getNome() + "' vinculada ao projeto '" + projeto.getNome() + "'.");
    }

    public void exibirEquipe(Equipe equipe) {
        if (equipe != null) view.exibirPainelEquipe(equipe);
    }
}
