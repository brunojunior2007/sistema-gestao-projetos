package model;

public class Usuario {
    // Requisito: Nome completo, CPF, e-mail, cargo, login, senha
    // Requisito: perfil = administrador, gerente ou colaborador
    private String nome;
    private String cpf;
    private String email;
    private String cargo;
    private String login;
    private String senha;
    private PerfilUsuario perfil;

    public enum PerfilUsuario {
        ADMINISTRADOR, GERENTE, COLABORADOR
    }

    public Usuario(String nome, String cpf, String email, String cargo,
                   String login, String senha, PerfilUsuario perfil) {
        if (nome == null || nome.isBlank())   throw new IllegalArgumentException("Nome não pode ser vazio.");
        if (cpf == null || cpf.isBlank())     throw new IllegalArgumentException("CPF não pode ser vazio.");
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("E-mail inválido.");
        if (login == null || login.isBlank()) throw new IllegalArgumentException("Login não pode ser vazio.");
        if (senha == null || senha.length() < 4) throw new IllegalArgumentException("Senha deve ter ao menos 4 caracteres.");
        if (perfil == null)                   throw new IllegalArgumentException("Perfil é obrigatório.");

        this.nome   = nome;
        this.cpf    = cpf;
        this.email  = email;
        this.cargo  = cargo;
        this.login  = login;
        this.senha  = senha;
        this.perfil = perfil;
    }

    public String getNome()            { return nome; }
    public String getCpf()             { return cpf; }
    public String getEmail()           { return email; }
    public String getCargo()           { return cargo; }
    public String getLogin()           { return login; }
    public PerfilUsuario getPerfil()   { return perfil; }

    // Senha não tem getter — nunca exposta diretamente
    public boolean validarSenha(String tentativa) { return this.senha.equals(tentativa); }

    @Override
    public String toString() {
        return nome + " [" + perfil + "] <" + email + ">";
    }
}
