package repository;

import model.Projeto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProjetoRepository {
    private List<Projeto> banco = new ArrayList<>();

    public void salvarProjeto(Projeto projeto) {
        banco.add(projeto);
    }

    public Optional<Projeto> buscarPorNome(String nome) {
        return banco.stream()
                    .filter(p -> p.getNome().equalsIgnoreCase(nome))
                    .findFirst();
    }

    public List<Projeto> listarProjetos() {
        return Collections.unmodifiableList(banco);
    }
}
