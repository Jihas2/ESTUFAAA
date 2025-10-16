package Controller;

import Model.Estufa;
import Model.Temperatura;
import View.EstufaView;
import Database.DatabaseManager;
import java.util.Scanner;

public class EstufaController {
    private Estufa estufa;
    private EstufaView view;
    private Scanner scanner;
    private DatabaseManager dbManager;

    public EstufaController(EstufaView view) {
        this.view = view;
        this.scanner = new Scanner(System.in);
        this.dbManager = new DatabaseManager();
    }

    public void iniciar() {
        view.exibirCabecalho();

        dbManager.exibirHistorico();

        double temperaturaMinima = solicitarTemperaturaMinima();
        double temperaturaMaxima = solicitarTemperaturaMaxima();

        while (temperaturaMinima >= temperaturaMaxima) {
            view.exibirErroLimites();
            temperaturaMinima = solicitarTemperaturaMinima();
            temperaturaMaxima = solicitarTemperaturaMaxima();
        }

        estufa = new Estufa(temperaturaMinima, temperaturaMaxima, 24);
        view.exibirLimites(temperaturaMinima, temperaturaMaxima);

        coletarTemperaturas();
        gerarRelatorios();
        salvarDados();

        scanner.close();
        dbManager.fecharConexao();
    }

    private double solicitarTemperaturaMinima() {
        System.out.print("Digite a temperatura MÍNIMA ideal (°C): ");
        return scanner.nextDouble();
    }

    private double solicitarTemperaturaMaxima() {
        System.out.print("Digite a temperatura MÁXIMA ideal (°C): ");
        return scanner.nextDouble();
    }

    private void coletarTemperaturas() {
        view.exibirInicioColeta();

        for (int hora = 0; hora < estufa.getTotalHoras(); hora++) {
            System.out.print("Hora " + String.format("%02d", hora) + ":00 - Temperatura (°C): ");
            double temperatura = scanner.nextDouble();

            estufa.adicionarTemperatura(temperatura, hora);

            Temperatura temp = new Temperatura(temperatura, hora);
            dbManager.salvarTemperatura(temp);

            if (estufa.estaAcimaDoMaximo(temperatura)) {
                view.exibirAlertaAcimaMaximo();
            } else if (estufa.estaAbaixoDoMinimo(temperatura)) {
                view.exibirAlertaAbaixoMinimo();
            } else {
                view.exibirTemperaturaOk();
            }

            System.out.println();
        }
    }

    private void gerarRelatorios() {
        view.exibirRelatorioFinal(estufa);
        view.exibirDiagnostico(estufa);
        view.exibirAnaliseMedia(estufa);
        view.exibirAnaliseVariacao(estufa);
        view.exibirRodape();
    }

    private void salvarDados() {
        dbManager.salvarMonitoramento(
                estufa.getTemperaturaMinima(),
                estufa.getTemperaturaMaxima(),
                estufa.calcularMedia(),
                estufa.obterMenorTemperatura(),
                estufa.obterMaiorTemperatura(),
                estufa.contarTemperaturasAcimaMaximo(),
                estufa.contarTemperaturasAbaixoMinimo()
        );
    }
}