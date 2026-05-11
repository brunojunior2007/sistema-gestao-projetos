package br.com.brunojr.sistemagestao.repositories;

import br.com.brunojr.sistemagestao.domain.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Estrutura de persistência e repositório operacional de dados para a entidade
 * Time.
 */
public class TimeRepository {
    private List<Time> armazenamentoEmMemoria = new ArrayList<>();

    /**
     * Persiste o objeto Time na base.
     */
    public void salvarTime(Time timeAtual) {
        armazenamentoEmMemoria.add(timeAtual);
    }

    /**
     * Recuperação de Time via nome único.
     */
    public Optional<Time> buscarPorNome(String nome) {
        return armazenamentoEmMemoria.stream()
                .filter(timeAtual -> timeAtual.getNome().equalsIgnoreCase(nome))
                .findFirst();
    }

    /**
     * Retorna visualização das equipes formalizadas.
     */
    public List<Time> listarTimes() {
        return Collections.unmodifiableList(armazenamentoEmMemoria);
    }
}
