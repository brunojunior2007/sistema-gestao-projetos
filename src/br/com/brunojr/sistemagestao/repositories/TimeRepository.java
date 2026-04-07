package br.com.brunojr.sistemagestao.repositories;

import br.com.brunojr.sistemagestao.domain.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Estrutura de persistência e repositório operacional de dados para a entidade Time.
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
     * Retorna visualização das equipes formalizadas.
     */
    public List<Time> listarTimes() {
        return Collections.unmodifiableList(armazenamentoEmMemoria);
    }
}
