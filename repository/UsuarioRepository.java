package repository;

import model.Usuario;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UsuarioRepository {
    private List<Usuario> banco = new ArrayList<>();

    public void salvarNoBanco(Usuario usuario) {
        banco.add(usuario);
    }

    public Optional<Usuario> buscarPorLogin(String login) {
        return banco.stream()
                    .filter(u -> u.getLogin().equals(login))
                    .findFirst();
    }

    public List<Usuario> listarTodos() {
        return Collections.unmodifiableList(banco);
    }
}
