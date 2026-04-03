import controller.GestaoController;
import model.Equipe;
import model.Projeto;
import model.Usuario;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        System.out.println("=====================================================");
        System.out.println("🚀 SISTEMA DE GESTÃO DE PROJETOS E EQUIPES");
        System.out.println("=====================================================");

        GestaoController ctrl = new GestaoController();

        // ── 1. CADASTRO DE USUÁRIOS ────────────────────────────
        System.out.println("\n======= 1. CADASTRO DE USUÁRIOS =======");

        Usuario admin = ctrl.registrarUsuario(
            "Ana Lima", "111.111.111-11", "ana@empresa.com",
            "Diretora de TI", "ana.lima", "senha123",
            Usuario.PerfilUsuario.ADMINISTRADOR
        );

        Usuario gerente = ctrl.registrarUsuario(
            "Carlos Mendes", "333.333.333-33", "carlos@empresa.com",
            "Gerente de Projetos", "carlos.mendes", "senha456",
            Usuario.PerfilUsuario.GERENTE
        );

        Usuario colab1 = ctrl.registrarUsuario(
            "Beatriz Souza", "222.222.222-22", "beatriz@empresa.com",
            "Desenvolvedora Backend", "beatriz.souza", "senha789",
            Usuario.PerfilUsuario.COLABORADOR
        );

        Usuario colab2 = ctrl.registrarUsuario(
            "Diego Rocha", "444.444.444-44", "diego@empresa.com",
            "Designer UX", "diego.rocha", "senha321",
            Usuario.PerfilUsuario.COLABORADOR
        );

        // ── 2. CADASTRO DE PROJETOS ────────────────────────────
        System.out.println("\n======= 2. CADASTRO DE PROJETOS =======");

        Projeto projetoA = ctrl.registrarProjeto(
            "Sistema de Gestão de Eventos",
            "Plataforma para gerenciar eventos corporativos com rastreabilidade.",
            LocalDate.of(2026, 4, 1),
            LocalDate.of(2026, 10, 31),
            gerente
        );

        Projeto projetoB = ctrl.registrarProjeto(
            "Portal do Colaborador",
            "Portal interno para gestão de benefícios e RH.",
            LocalDate.of(2026, 5, 1),
            LocalDate.of(2026, 12, 15),
            gerente
        );

        // Teste: projeto sem gerente (deve exibir alerta)
        System.out.println("\n--- Teste: projeto sem gerente ---");
        ctrl.registrarProjeto("Projeto Inválido", "Sem responsável",
                              LocalDate.now(), LocalDate.now().plusMonths(1), null);

        // ── 3. ATUALIZAÇÃO DE STATUS ───────────────────────────
        System.out.println("\n======= 3. ATUALIZAÇÃO DE STATUS =======");
        ctrl.atualizarStatusProjeto(projetoA, Projeto.StatusProjeto.EM_ANDAMENTO);

        // ── 4. CADASTRO DE EQUIPES ─────────────────────────────
        System.out.println("\n======= 4. CADASTRO DE EQUIPES =======");

        Equipe equipeAlpha = ctrl.registrarEquipe(
            "Alpha Dev",
            "Equipe responsável pelo desenvolvimento dos sistemas internos."
        );

        // Adicionando membros
        ctrl.adicionarMembroEquipe(equipeAlpha, gerente);
        ctrl.adicionarMembroEquipe(equipeAlpha, colab1);
        ctrl.adicionarMembroEquipe(equipeAlpha, colab2);

        // Vinculando a múltiplos projetos (requisito: equipe pode atuar em vários projetos)
        ctrl.vincularEquipeProjeto(equipeAlpha, projetoA);
        ctrl.vincularEquipeProjeto(equipeAlpha, projetoB);

        // Exibindo painel completo da equipe
        System.out.println();
        ctrl.exibirEquipe(equipeAlpha);

        System.out.println("\n✅ Todos os requisitos demonstrados com sucesso!");
    }
}
