package br.com.brunojr.sistemagestao;

import br.com.brunojr.sistemagestao.controllers.GerenciadorFluxoController;
import br.com.brunojr.sistemagestao.domain.Time;
import br.com.brunojr.sistemagestao.domain.GestaoProjeto;
import br.com.brunojr.sistemagestao.domain.Colaborador;

import java.time.LocalDate;

public class Application {
        public static void main(String[] args) {

                System.out.println("=========================================================================");
                System.out.println("ERP E WORKFLOW - SISTEMA DE GESTÃO DE CRONOGRAMAS E EQUIPES");
                System.out.println("=========================================================================");

                GerenciadorFluxoController fluxoExecutivo = new GerenciadorFluxoController();

                // == 1. MATRÍCULA FUNCIONAL E SISTEMA DE RH ============================
                System.out.println("\n======= 1. INJEÇÃO DE RH E FORMULAÇÃO DE PAPÉIS =======");

                Colaborador gestorOperacoes = fluxoExecutivo.registrarColaborador(
                                "Caio Teixeira", "111.111.111-11", "caio@empresa.com.br",
                                "Líder Técnico de Gestão de Projetos", "caioteixeira", "4565436",
                                Colaborador.PerfilColaborador.GERENTE);

                Colaborador desenvolvedorSr = fluxoExecutivo.registrarColaborador(
                                "Luciano Silva da Costa", "444.444.444-44", "luciano@empresa.com.br",
                                "Especialista em Backbone Architecture", "luciano.costa", "9565654",
                                Colaborador.PerfilColaborador.COLABORADOR);

                Colaborador uiDesigner = fluxoExecutivo.registrarColaborador(
                                "Pedro Richard dos Santos", "888.888.888-88", "pedro.santos@empresa.com.br",
                                "Especialista em UX e Pesquisa", "pedro.santos", "9785946",
                                Colaborador.PerfilColaborador.COLABORADOR);

                // == 2. FLUXOS DE NEGÓCIOS DE GERENCIAMENTO (PROJETOS) =================
                System.out.println("\n======= 2. ABERTURA DE ATAS DOS PROJETOS ESTRATÉGICOS =======");

                GestaoProjeto painelEventual = fluxoExecutivo.registrarProjeto(
                                "Plataforma Digital de Eventos",
                                "Gestão holística e escalável para eventos de larga escala e publicações intra-corporativas.",
                                LocalDate.of(2026, 4, 1),
                                LocalDate.of(2026, 10, 31),
                                gestorOperacoes);

                GestaoProjeto dashboardRH = fluxoExecutivo.registrarProjeto(
                                "Módulo e Portal Transparente do Colaborador",
                                "Área logada com informações contratuais para satisfação máxima da força de trabalho.",
                                LocalDate.of(2026, 5, 1),
                                LocalDate.of(2026, 12, 15),
                                gestorOperacoes);

                // Tratamento de inconformidades - Alarme de compliance exigido
                System.out.println("\n--- Homologação Criminosa e Forçada: Análise de Resposta ---");
                fluxoExecutivo.registrarProjeto("Iniciativa Excluída", "Sem patrono designado.",
                                LocalDate.now(), LocalDate.now().plusMonths(1), null);

                // == 3. AVANÇO PROGRESSIVO NOS STATUS OPERACIONAIS =====================
                System.out.println("\n======= 3. RECLASSIFICAÇÃO DE ROADMAP DO PROJETO =======");
                fluxoExecutivo.atualizarStatusProjeto(painelEventual, GestaoProjeto.StatusProjeto.EM_ANDAMENTO);

                // == 4. CONSOLIDAÇÃO DOS POLOS TECNOLÓGICOS (TIMES) ====================
                System.out.println("\n======= 4. COMPOSIÇÃO DE POLOS E HUB DE EQUIPES =======");

                Time divisaoCore = fluxoExecutivo.registrarTime(
                                "Alpha Operations",
                                "Brigada tática engajada na evolução veloz e furiosa de sistemas restritos da empresa.");

                // Anexando contingente ao esquadrão
                fluxoExecutivo.adicionarMembroTime(divisaoCore, gestorOperacoes);
                fluxoExecutivo.adicionarMembroTime(divisaoCore, desenvolvedorSr);
                fluxoExecutivo.adicionarMembroTime(divisaoCore, uiDesigner);

                // Atribuindo portfólio de projetos exigido ao Hub Operacional
                fluxoExecutivo.vincularTimeProjeto(divisaoCore, painelEventual);
                fluxoExecutivo.vincularTimeProjeto(divisaoCore, dashboardRH);

                // Exibição do dossiê confidencial do grupo tático
                System.out.println();
                fluxoExecutivo.exibirTime(divisaoCore);

                System.out.println(
                                "\nO ciclo completo de gestão foi plenamente executado em conformidade com as diretrizes da disciplina 'Soluções Computacionais'.");
        }
}
