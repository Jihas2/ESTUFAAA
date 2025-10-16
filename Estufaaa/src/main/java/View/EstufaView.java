package View;

import Model.Estufa;

public class EstufaView {

    public void exibirCabecalho() {
        System.out.println("   SISTEMA DE CONTROLE DE TEMPERATURA - ESTUFA         ");
        System.out.println();
    }

    public void exibirLimites(double minima, double maxima) {
        System.out.println("\n" + "=".repeat(56));
        System.out.println("Limites configurados:");
        System.out.println("  • Temperatura mínima: " + minima + "°C");
        System.out.println("  • Temperatura máxima: " + maxima + "°C");
        System.out.println("=".repeat(56) + "\n");
    }

    public void exibirInicioColeta() {
        System.out.println(" COLETA DE DADOS - 24 HORAS\n");
    }

    public void exibirAlertaAcimaMaximo() {
        System.out.println("     ️  ALERTA: Temperatura acima do máximo!");
    }

    public void exibirAlertaAbaixoMinimo() {
        System.out.println("       ALERTA: Temperatura abaixo do mínimo!");
    }

    public void exibirTemperaturaOk() {
        System.out.println("      Temperatura dentro do padrão");
    }

    public void exibirRelatorioFinal(Estufa estufa) {
        System.out.println("\n" + "=".repeat(56));
        System.out.println(" RELATÓRIO FINAL - MONITORAMENTO DE 24 HORAS");
        System.out.println("=".repeat(56));
        System.out.println();

        System.out.printf(" Temperatura média: %.2f°C%n", estufa.calcularMedia());
        System.out.printf(" Maior temperatura: %.2f°C%n", estufa.obterMaiorTemperatura());
        System.out.printf("  Menor temperatura: %.2f°C%n", estufa.obterMenorTemperatura());
        System.out.println();

        System.out.println(" ANÁLISE DE ALERTAS:");
        System.out.println("  • Temperaturas acima do máximo (" + estufa.getTemperaturaMaxima() + "°C): " +
                estufa.contarTemperaturasAcimaMaximo() + " ocorrência(s)");
        System.out.println("  • Temperaturas abaixo do mínimo (" + estufa.getTemperaturaMinima() + "°C): " +
                estufa.contarTemperaturasAbaixoMinimo() + " ocorrência(s)");
        System.out.println("  • Temperaturas dentro do padrão: " +
                estufa.contarTemperaturasDentroDoPadrao() + " ocorrência(s)");
        System.out.println();
    }

    public void exibirDiagnostico(Estufa estufa) {
        int acimaMaximo = estufa.contarTemperaturasAcimaMaximo();
        int abaixoMinimo = estufa.contarTemperaturasAbaixoMinimo();

        System.out.println(" DIAGNÓSTICO DO SISTEMA:");
        System.out.println("-".repeat(56));

        if (acimaMaximo == 0 && abaixoMinimo == 0) {
            System.out.println(" EXCELENTE! Todas as temperaturas dentro do padrão ideal.");
            System.out.println("   A estufa está funcionando perfeitamente!");
        } else if (acimaMaximo > 12 || abaixoMinimo > 12) {
            System.out.println(" CRÍTICO! Mais da metade do período com temperatura fora do padrão.");
            System.out.println("   Ação imediata necessária no sistema de climatização!");
        } else if (acimaMaximo > 6 || abaixoMinimo > 6) {
            System.out.println("  ATENÇÃO! Frequência elevada de temperaturas fora do padrão.");
            System.out.println("   Recomenda-se ajuste no sistema de climatização.");
        } else {
            System.out.println("✓ ACEITÁVEL. Poucas ocorrências fora do padrão.");
            System.out.println("   Sistema funcionando dentro do esperado.");
        }

        System.out.println();
    }

    public void exibirAnaliseMedia(Estufa estufa) {
        double media = estufa.calcularMedia();

        if (estufa.estaDentroDoPadrao(media)) {
            System.out.println(" A temperatura média está dentro da faixa ideal.");
        } else if (media > estufa.getTemperaturaMaxima()) {
            System.out.println(" A temperatura média está ACIMA da faixa ideal.");
            System.out.println("   Considere aumentar a ventilação ou resfriamento.");
        } else {
            System.out.println(" A temperatura média está ABAIXO da faixa ideal.");
            System.out.println("   Considere aumentar o aquecimento da estufa.");
        }

        System.out.println();
    }

    public void exibirAnaliseVariacao(Estufa estufa) {
        double variacao = estufa.calcularVariacaoTermica();
        System.out.printf("  Variação térmica total: %.2f°C%n", variacao);

        if (variacao > 15) {
            System.out.println("     ALTA variação! Sistema de controle pode estar instável.");
        } else if (variacao > 8) {
            System.out.println("     Variação moderada. Monitorar estabilidade do sistema.");
        } else {
            System.out.println("    Variação baixa. Sistema mantendo temperatura estável.");
        }

        System.out.println();
    }

    public void exibirRodape() {
        System.out.println("=".repeat(56));
        System.out.println("           FIM DO MONITORAMENTO - Sistema Encerrado");
        System.out.println("=".repeat(56));
    }

    public void exibirErroLimites() {
        System.out.println("\n  ERRO: A temperatura mínima deve ser menor que a máxima!");
    }
}