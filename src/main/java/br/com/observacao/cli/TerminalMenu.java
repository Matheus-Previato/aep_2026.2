package br.com.observacao.cli;

import br.com.observacao.model.Solicitacao;
import br.com.observacao.service.SolicitacaoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class TerminalMenu implements CommandLineRunner {

    private final SolicitacaoService solicitacaoService;

    public TerminalMenu(SolicitacaoService solicitacaoService) {
        this.solicitacaoService = solicitacaoService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== ObservaAcao - ODS ===");
            System.out.println("1. Registrar nova solicitacao");
            System.out.println("2. Listar solicitacoes");
            System.out.println("3. Atualizar status de solicitacao");
            System.out.println("4. Excluir solicitacao");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> registrarSolicitacao(scanner);
                case 2 -> listarSolicitacoes();
                case 3 -> atualizarStatus(scanner);
                case 4 -> excluirSolicitacao(scanner);
                case 0 -> System.out.println("\nPrograma encerrado.");
                default -> System.out.println("\nOpcao invalida.");
            }
        }

        scanner.close();
    }

    private void registrarSolicitacao(Scanner scanner) {
        Solicitacao novaSolic = new Solicitacao();

        System.out.println("\nSelecione a categoria:");
        System.out.println("1. Iluminacao");
        System.out.println("2. Buraco na via");
        System.out.println("3. Limpeza urbana");
        System.out.println("4. Saude");
        System.out.println("5. Seguranca Escolar");
        System.out.println("6. Outros");
        System.out.print("Opcao de categoria: ");

        int opcaoCategoria = scanner.nextInt();
        scanner.nextLine();

        String nomeCategoria = switch (opcaoCategoria) {
            case 1 -> "Iluminacao";
            case 2 -> "Buraco na via";
            case 3 -> "Limpeza urbana";
            case 4 -> "Saude";
            case 5 -> "Seguranca Escolar";
            default -> {
                System.out.print("Digite o nome da categoria (Outros): ");
                yield scanner.nextLine();
            }
        };
        novaSolic.setCategoria(nomeCategoria);

        System.out.print("Digite a prioridade (Alta/Media/Baixa): ");
        novaSolic.setPrioridade(scanner.nextLine());

        System.out.print("Digite a descricao (minimo 20 caracteres): ");
        novaSolic.setDescricao(scanner.nextLine());

        novaSolic.setAnonimo(false);

        try {
            solicitacaoService.criarSolicitacao(novaSolic);
            System.out.println("\nSolicitacao registrada com sucesso!");
        } catch (Exception e) {
            System.out.println("\nErro ao registrar: " + e.getMessage());
        }
    }

    private void listarSolicitacoes() {
        System.out.println("\n--- Lista de Solicitacoes ---");
        solicitacaoService.listarTodas().forEach(s ->
                System.out.println("Protocolo: " + s.getCodigo() + " | Categoria: " + s.getCategoria() + " | Status: " + s.getStatus())
        );
    }

    private void atualizarStatus(Scanner scanner) {
        System.out.println("\n--- Atualizar Status ---");
        System.out.print("Digite o codigo do protocolo: ");
        String codigoAlt = scanner.nextLine();
        System.out.print("Digite o novo status: ");
        String novoStatus = scanner.nextLine();
        try {
            solicitacaoService.atualizarStatus(codigoAlt, novoStatus);
            System.out.println("\nStatus atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println("\nErro ao atualizar: " + e.getMessage());
        }
    }

    private void excluirSolicitacao(Scanner scanner) {
        System.out.println("\n--- Excluir Solicitacao ---");
        System.out.print("Digite o codigo do protocolo: ");
        String codigoDel = scanner.nextLine();
        try {
            solicitacaoService.excluirPorCodigo(codigoDel);
            System.out.println("\nSolicitacao excluida com sucesso!");
        } catch (Exception e) {
            System.out.println("\nErro ao excluir: " + e.getMessage());
        }
    }
}