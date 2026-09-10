package br.com.observacao.cli;

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
            System.out.println("=== ObservaAção - ODS ===");
            System.out.println("1. Registrar nova solicitação");
            System.out.println("2. Listar solicitações (CRUD básico)");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                System.out.println("Solicitação registrada com sucesso!");
            }
        }
        scanner.close();
    }
}