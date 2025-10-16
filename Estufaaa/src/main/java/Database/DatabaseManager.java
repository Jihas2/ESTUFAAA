package Database;

import Model.Temperatura;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DatabaseManager {
    private static final String TEMPERATURAS_FILE = "temperaturas.txt";
    private static final String MONITORAMENTOS_FILE = "monitoramentos.txt";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public DatabaseManager() {
        System.out.println("✓ Sistema de persistência de dados inicializado.\n");
        criarArquivos();
    }

    private void criarArquivos() {
        try {
            File tempFile = new File(TEMPERATURAS_FILE);
            File monFile = new File(MONITORAMENTOS_FILE);

            if (!tempFile.exists()) {
                tempFile.createNewFile();
            }
            if (!monFile.exists()) {
                monFile.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Erro ao criar arquivos: " + e.getMessage());
        }
    }

    public void salvarTemperatura(Temperatura temperatura) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEMPERATURAS_FILE, true))) {
            String dataHora = LocalDateTime.now().format(formatter);
            String linha = String.format("%s|%.2f|%d%n", dataHora, temperatura.getValor(), temperatura.getHora());
            writer.write(linha);
        } catch (IOException e) {
            System.err.println("Erro ao salvar temperatura: " + e.getMessage());
        }
    }

    public void salvarMonitoramento(double tempMin, double tempMax, double media,
                                    double menor, double maior, int acimaMax, int abaixoMin) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MONITORAMENTOS_FILE, true))) {
            String dataHora = LocalDateTime.now().format(formatter);
            String linha = String.format("%s|%.2f|%.2f|%.2f|%.2f|%.2f|%d|%d%n",
                    dataHora, tempMin, tempMax, media, menor, maior, acimaMax, abaixoMin);
            writer.write(linha);
            System.out.println("✓ Monitoramento salvo no banco de dados.\n");
        } catch (IOException e) {
            System.err.println("Erro ao salvar monitoramento: " + e.getMessage());
        }
    }

    public ArrayList<Temperatura> listarTemperaturas() {
        ArrayList<Temperatura> temperaturas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(TEMPERATURAS_FILE))) {
            String linha;
            int id = 1;

            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split("\\|");
                if (partes.length == 3) {
                    Temperatura temp = new Temperatura(
                            id++,
                            Double.parseDouble(partes[1]),
                            Integer.parseInt(partes[2]),
                            partes[0]
                    );
                    temperaturas.add(temp);
                }
            }

            // Retorna apenas as últimas 24
            int inicio = Math.max(0, temperaturas.size() - 24);
            return new ArrayList<>(temperaturas.subList(inicio, temperaturas.size()));

        } catch (IOException e) {
            System.err.println("Erro ao listar temperaturas: " + e.getMessage());
        }

        return temperaturas;
    }

    public void exibirHistorico() {
        ArrayList<String> monitoramentos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(MONITORAMENTOS_FILE))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                monitoramentos.add(linha);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler histórico: " + e.getMessage());
            return;
        }

        if (monitoramentos.isEmpty()) {
            return;
        }

        System.out.println("\n" + "=".repeat(70));
        System.out.println("   HISTÓRICO DE MONITORAMENTOS (Últimos 5)");
        System.out.println("=".repeat(70));

        int inicio = Math.max(0, monitoramentos.size() - 5);
        int count = 1;

        for (int i = inicio; i < monitoramentos.size(); i++) {
            String[] partes = monitoramentos.get(i).split("\\|");

            if (partes.length == 8) {
                System.out.println("\nMonitoramento #" + (inicio + count) + " - " + partes[0]);
                System.out.println("  Limites: " + partes[1] + "°C a " + partes[2] + "°C");
                System.out.println("  Média: " + partes[3] + "°C");
                System.out.println("  Menor: " + partes[4] + "°C | Maior: " + partes[5] + "°C");
                System.out.println("  Acima do máximo: " + partes[6] + " | Abaixo do mínimo: " + partes[7]);
                count++;
            }
        }

        System.out.println("=".repeat(70) + "\n");
    }

    public void fecharConexao() {
        System.out.println("✓ Sistema de persistência finalizado.");
    }
}