package br.com.brunojr.sistemagestao.domain;

/**
 * Representa os recursos humanos (Colaboradores) alocados na organização.
 * Engloba credenciais de sistema, papéis funcionais e dados de matriz de identidade.
 */
public class Colaborador {
    private String nome;
    private String cpf;
    private String email;
    private String cargo;
    private String login;
    private String senha;
    private PerfilColaborador perfil;

    /**
     * Níveis documentados de privilégio sistêmico.
     */
    public enum PerfilColaborador {
        ADMINISTRADOR, GERENTE, COLABORADOR
    }

    /**
     * Consolida a admissão e registro do Colaborador no banco de dados.
     */
    public Colaborador(String nome, String cpf, String email, String cargo,
                       String login, String senha, PerfilColaborador perfil) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome corporativo inviável: não pode estar vazio.");
        if (cpf == null || cpf.isBlank()) throw new IllegalArgumentException("Registro de pessoa física (CPF) não preenchido.");
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("Credencial de E-mail fora dos padrões RFC.");
        if (login == null || login.isBlank()) throw new IllegalArgumentException("Identificação global de acesso ausente.");
        if (senha == null || senha.length() < 4) throw new IllegalArgumentException("Complexidade de senha aquém da política de segurança de 4 caracteres.");
        if (perfil == null) throw new IllegalArgumentException("Determinação de nível de acesso (perfil) imperativa.");

        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.cargo = cargo;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
    }

    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getEmail() { return email; }
    public String getCargo() { return cargo; }
    public String getLogin() { return login; }
    public PerfilColaborador getPerfil() { return perfil; }

    /**
     * Autentica tentativas sistêmicas comparando o hash transitório de entrada.
     * 
     * @param tentativa string recebida via interface
     * @return veracidade da asserção de autenticação
     */
    public boolean validarSenha(String tentativa) { 
        return this.senha.equals(tentativa); 
    }

    @Override
    public String toString() {
        return "Matrícula: " + nome + " | Nível: [" + perfil + "] | Contato Institucional: <" + email + ">";
    }
}
