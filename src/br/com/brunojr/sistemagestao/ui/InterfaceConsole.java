package br.com.brunojr.sistemagestao.ui;

import br.com.brunojr.sistemagestao.domain.Time;
import br.com.brunojr.sistemagestao.domain.GestaoProjeto;
import br.com.brunojr.sistemagestao.domain.Colaborador;

/**
 * Interface Externa de Console e Renderização Base.
 * Modela a comunicação visual com os painéis executivos do portfólio digital.
 */
public class InterfaceConsole {

    private static final String LINHA_EXECUTIVA = "==========================================================";

    public void exibirMensagem(String msg)  { System.out.println("> [LOG EXECUTIVO] " + msg); }
    public void exibirAlerta(String msg)    { System.out.println("[!] [RISCO/ALARME] " + msg); }
    public void exibirSeparador(String titulo) {
        System.out.println("\n*** " + titulo.toUpperCase() + " ***");
    }

    public void exibirPainelColaborador(Colaborador colaboradorAtual) {
        System.out.println("+" + LINHA_EXECUTIVA + "+");
        System.out.println("| IDENTIDADE E CREDENCIAIS DE COLABORADOR");
        System.out.println("| MATRÍCULA  : " + colaboradorAtual.getNome());
        System.out.println("| CPF DOC    : " + colaboradorAtual.getCpf());
        System.out.println("| END. INT.  : " + colaboradorAtual.getEmail());
        System.out.println("| PAPEL/CARGO: " + colaboradorAtual.getCargo());
        System.out.println("| LOGIN/ID   : " + colaboradorAtual.getLogin());
        System.out.println("| PERMISSÕES : " + colaboradorAtual.getPerfil());
        System.out.println("+" + LINHA_EXECUTIVA + "+");
    }

    public void exibirPainelProjeto(GestaoProjeto projetoAtual) {
        System.out.println("+" + LINHA_EXECUTIVA + "+");
        System.out.println("| QUADRO TATICO: GESTAO DE PROJETOS E PRAZOS");
        System.out.println("| NOMENCLATURA: " + projetoAtual.getNome());
        System.out.println("| ESCOPO GERAL: " + projetoAtual.getDescricao());
        System.out.println("| DATA START  : " + projetoAtual.getDataInicio());
        System.out.println("| DATA TARGET : " + projetoAtual.getDataTerminoPrevista());
        System.out.println("| PROJEÇÃO    : " + projetoAtual.getStatus());
        System.out.println("| BOARD LEADER: " + projetoAtual.getGerente().getNome()
                           + " (Classe: " + projetoAtual.getGerente().getPerfil() + ")");
        System.out.println("+" + LINHA_EXECUTIVA + "+");
    }

    public void exibirPainelTime(Time timeAtual) {
        System.out.println("+" + LINHA_EXECUTIVA + "+");
        System.out.println("| HUB OPERACIONAL / TIME UNIFICADO: " + timeAtual.getNome());
        System.out.println("| EXPERTISE : " + timeAtual.getDescricao());
        System.out.println("| INFANTARIA DE COLABORADORES:");
        timeAtual.getMembros().forEach(membroA -> System.out.println("|   - " + membroA.getNome() + " [Acesso: " + membroA.getPerfil() + "]"));
        System.out.println("| CARTEIRA DE PROJETOS E INTERVENÇÕES:");
        timeAtual.getProjetos().forEach(projetoAtual -> System.out.println("|   - " + projetoAtual.getNome() + " [Situação Plena: " + projetoAtual.getStatus() + "]"));
        System.out.println("+" + LINHA_EXECUTIVA + "+");
    }
}
