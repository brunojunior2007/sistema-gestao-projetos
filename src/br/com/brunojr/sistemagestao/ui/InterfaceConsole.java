package br.com.brunojr.sistemagestao.ui;

import br.com.brunojr.sistemagestao.domain.Colaborador;
import br.com.brunojr.sistemagestao.domain.GestaoProjeto;
import br.com.brunojr.sistemagestao.domain.Time;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Interface Externa de Console e Renderização Base. Modela a comunicação visual
 * com os painéis executivos do portfólio digital.
 * Agora integrada com Swing para uma experiência gráfica profissional.
 */
public class InterfaceConsole {

    private static final String LINHA_EXECUTIVA = "----------------------------------------------------------";
    private static final Color COR_PRIMARY = new Color(44, 62, 80);
    private static final Color COR_ACCENT = new Color(52, 152, 219);

    public void exibirMensagem(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Notificação do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    public void exibirAlerta(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Alerta de Risco", JOptionPane.WARNING_MESSAGE);
    }

    public void exibirSeparador(String titulo) {
        System.out.println("\n*** " + titulo.toUpperCase() + " ***");
    }

    private void exibirEmDialogo(String conteudo, String titulo) {
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBackground(Color.WHITE);
        painel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(COR_PRIMARY);
        painel.add(lblTitulo, BorderLayout.NORTH);

        JTextArea textArea = new JTextArea(conteudo);
        textArea.setEditable(false);
        textArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        textArea.setForeground(new Color(45, 52, 54));
        textArea.setBackground(new Color(248, 249, 250));
        textArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(223, 230, 233)),
                new EmptyBorder(10, 10, 10, 10)));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(550, 450));
        scrollPane.setBorder(null);
        painel.add(scrollPane, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(null, painel, "Relatório Executivo", JOptionPane.PLAIN_MESSAGE);
    }

    public void exibirPainelColaborador(Colaborador colaboradorAtual) {
        StringBuilder sb = new StringBuilder();
        sb.append(" [ FICHA CADASTRAL DE COLABORADOR ]\n");
        sb.append(LINHA_EXECUTIVA).append("\n");
        sb.append(" > MATRÍCULA  : ").append(colaboradorAtual.getNome()).append("\n");
        sb.append(" > CPF DOC    : ").append(colaboradorAtual.getCpf()).append("\n");
        sb.append(" > EMAIL      : ").append(colaboradorAtual.getEmail()).append("\n");
        sb.append(" > CARGO      : ").append(colaboradorAtual.getCargo()).append("\n");
        sb.append(" > LOGIN/ID   : ").append(colaboradorAtual.getLogin()).append("\n");
        sb.append(" > PERFIL     : [").append(colaboradorAtual.getPerfil()).append("]\n");
        sb.append(LINHA_EXECUTIVA);

        exibirEmDialogo(sb.toString(), "Identidade e Credenciais");
    }

    public void exibirPainelProjeto(GestaoProjeto projetoAtual) {
        StringBuilder sb = new StringBuilder();
        sb.append(" [ QUADRO TÁTICO DE PROJETO ]\n");
        sb.append(LINHA_EXECUTIVA).append("\n");
        sb.append(" > PROJETO    : ").append(projetoAtual.getNome()).append("\n");
        sb.append(" > ESCOPO     : ").append(projetoAtual.getDescricao()).append("\n");
        sb.append(" > INÍCIO     : ").append(projetoAtual.getDataInicio()).append("\n");
        sb.append(" > TARGET     : ").append(projetoAtual.getDataTerminoPrevista()).append("\n");
        sb.append(" > ORÇAMENTO  : R$ ").append(String.format("%.2f", projetoAtual.getOrcamento())).append("\n");
        sb.append(" > PRIORIDADE : ").append(projetoAtual.getPrioridade()).append("\n");
        sb.append(" > STATUS     : ").append(projetoAtual.getStatus()).append("\n");
        sb.append(" > LÍDER      : ").append(projetoAtual.getGerente().getNome()).append("\n");
        sb.append(LINHA_EXECUTIVA).append("\n");
        sb.append(" TAREFAS ATIVAS:\n");
        if (projetoAtual.getTarefas().isEmpty()) {
            sb.append("   - Nenhuma tarefa cadastrada no cronograma.\n");
        } else {
            projetoAtual.getTarefas().forEach(t -> sb
                    .append("   - ").append(t.getTitulo()).append(" [").append(t.getStatus()).append("] Prazo: ")
                    .append(t.getPrazo()).append("\n"));
        }
        sb.append(LINHA_EXECUTIVA);

        exibirEmDialogo(sb.toString(), "Gestão de Projetos e Prazos");
    }

    public void exibirPainelTime(Time timeAtual) {
        StringBuilder sb = new StringBuilder();
        sb.append(" [ HUB OPERACIONAL / EQUIPE ]\n");
        sb.append(LINHA_EXECUTIVA).append("\n");
        sb.append(" > TIME       : ").append(timeAtual.getNome()).append("\n");
        sb.append(" > EXPERTISE  : ").append(timeAtual.getDescricao()).append("\n");
        sb.append(LINHA_EXECUTIVA).append("\n");
        sb.append(" RECURSOS HUMANOS ALOCADOS:\n");
        timeAtual.getMembros().forEach(m -> sb
                .append("   - ").append(m.getNome()).append(" [").append(m.getPerfil()).append("]\n"));
        sb.append("\n CARTEIRA DE PROJETOS VINCULADOS:\n");
        timeAtual.getProjetos().forEach(p -> sb.append("   - ")
                .append(p.getNome()).append(" [").append(p.getStatus()).append("]\n"));
        sb.append(LINHA_EXECUTIVA);

        exibirEmDialogo(sb.toString(), "Hub Operacional Unificado");
    }
}
