package br.com.brunojr.sistemagestao.repositories;

import br.com.brunojr.sistemagestao.domain.GestaoProjeto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Repositório centralizado de persistência de Projetos Estratégicos.
 */
public class GestaoProjetoRepository {
    private List<GestaoProjeto> armazenamentoEmMemoria = new ArrayList<>();

    /**
     * Persiste as configurações primárias de um novo fluxo de projeto.
     */
    public void salvarProjeto(GestaoProjeto projetoAtual) {
        armazenamentoEmMemoria.add(projetoAtual);
    }

    /**
     * Recuperação em tempo de execução via matriz nominativa do projeto.
     */
    public Optional<GestaoProjeto> buscarPorNome(String nome) {
        return armazenamentoEmMemoria.stream()
                    .filter(projetoAtual -> projetoAtual.getNome().equalsIgnoreCase(nome))
                    .findFirst();
    }

    public List<GestaoProjeto> listarProjetos() {
        return Collections.unmodifiableList(armazenamentoEmMemoria);
    }
}
