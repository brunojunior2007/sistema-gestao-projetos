package br.com.brunojr.sistemagestao.repositories;

import br.com.brunojr.sistemagestao.domain.Colaborador;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Persistência vinculada ao cadastro geral de pessoal (Colaboradores).
 */
public class ColaboradorRepository {
    private List<Colaborador> armazenamentoEmMemoria = new ArrayList<>();

    /**
     * Acopla os dados de perfil no repositório persistente local.
     */
    public void salvarNoBanco(Colaborador colaboradorAtual) {
        armazenamentoEmMemoria.add(colaboradorAtual);
    }

    /**
     * Query de mapeamento singular da credencial de integração.
     */
    public Optional<Colaborador> buscarPorLogin(String login) {
        return armazenamentoEmMemoria.stream()
                    .filter(colaboradorAtual -> colaboradorAtual.getLogin().equals(login))
                    .findFirst();
    }
    
    public List<Colaborador> listarTodos() {
        return Collections.unmodifiableList(armazenamentoEmMemoria);
    }
}
