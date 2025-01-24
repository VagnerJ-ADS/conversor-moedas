package br.com.alura.conversor;

import br.com.alura.conversor.modelos.Moeda;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Scanner;

public class ConversorMoedas {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner leitura = new Scanner(System.in); // Scanner para capturar a entrada do usuário
        int opcao = 0; // Variável para armazenar a escolha do usuário

        // Inicializa o objeto Gson com formatação personalizada
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE) // Define a política de nomeação de campos como CamelCase
                .setPrettyPrinting() // Configura o Gson para imprimir o JSON de forma legível
                .create();

        // URL da API de taxas de câmbio com chave de API
        String endereco = "https://v6.exchangerate-api.com/v6/sua_chave_aqui/latest/USD";

        // Cria um cliente HTTP para enviar solicitações
        HttpClient client = HttpClient.newHttpClient();

        // Cria uma solicitação HTTP para o endpoint da API
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco)) // Define a URI da API
                .build();

        // Envia a solicitação e recebe a resposta em formato String
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        // Armazena o corpo da resposta JSON em uma String
        String json = response.body();

        // Converte o JSON recebido para o objeto `Moeda`
        Moeda moeda = gson.fromJson(
                gson.fromJson(json, JsonObject.class) // Converte a String JSON para um JsonObject
                        .getAsJsonObject("conversion_rates") // Obtém o objeto "conversion_rates" que contém as taxas de câmbio
                        .toString(), Moeda.class); // Converte as taxas de câmbio para o objeto `Moeda`

        // Configura o formato decimal para exibir até 14 casas decimais
        DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.ENGLISH); // Define os símbolos decimais para o formato inglês (ponto como separador)
        DecimalFormat decimalFormat = new DecimalFormat("0.00000000000000", dfs); // Define o formato para o valor numérico

        // Loop para exibir o menu até que o usuário escolha sair (opção 7)
        while (opcao != 7) {

            // Exibe o menu de opções ao usuário
            System.out.println("\n");
            System.out.println("***********************************************************");
            System.out.println("Seja bem vindo(a) ao Conversor de Moedas!");
            System.out.println("1) Dólar =>> Peso argentino");
            System.out.println("2) Peso argentino =>> Dólar");
            System.out.println("3) Dólar =>> Real brasileiro");
            System.out.println("4) Real brasileiro =>> Dólar");
            System.out.println("5) Dólar =>> Peso colombiano");
            System.out.println("6) Peso colombiano =>> Dólar");
            System.out.println("7) Sair");
            System.out.println("Escolha uma opção válida:");
            System.out.print("***********************************************************");
            System.out.print("\n");
            opcao = leitura.nextInt(); // Lê a opção escolhida pelo usuário

            if (opcao == 1) {
                // Converte de Dólar para Peso Argentino
                System.out.println("Digite o valor que deseja converter:");
                double quantidade = leitura.nextDouble(); // Lê a quantidade a ser convertida
                double taxaConversao = moeda.ARS(); // Obtém a taxa de conversão do objeto `Moeda`
                double resultado = converterDolarParaDemaisMoedas(quantidade, taxaConversao); // Realiza a conversão

                // Exibe o resultado formatado
                System.out.print("\n");
                System.out.print("Valor " + quantidade + " [USD] corresponde ao valor final de =>>> " + decimalFormat.format(resultado) + " [ARS]");

            } else if (opcao == 2) {
                // Converte de Peso Argentino para Dólar
                System.out.println("Digite o valor que deseja converter:");
                double quantidade = leitura.nextDouble(); // Lê a quantidade a ser convertida
                double taxaConversao = moeda.ARS(); // Obtém a taxa de conversão
                double resultado = converterArsParaUsd(quantidade, taxaConversao); // Realiza a conversão

                // Exibe o resultado formatado
                System.out.print("\n");
                System.out.print("Valor " + quantidade + " [ARS] corresponde ao valor final de =>>> " + decimalFormat.format(resultado) + " [USD]");
            } else if (opcao == 3) {
                // Converte de Dólar para Real Brasileiro
                System.out.println("Digite o valor que deseja converter:");
                double quantidade = leitura.nextDouble(); // Lê a quantidade a ser convertida
                double taxaConversao = moeda.BRL(); // Obtém a taxa de conversão
                double resultado = converterDolarParaDemaisMoedas(quantidade, taxaConversao); // Realiza a conversão

                // Exibe o resultado formatado
                System.out.print("\n");
                System.out.print("Valor " + quantidade + " [USD] corresponde ao valor final de =>>> " + decimalFormat.format(resultado) + " [BRL]");
            } else if (opcao == 4) {
                // Converte de Real Brasileiro para Dólar
                System.out.println("Digite o valor que deseja converter:");
                double quantidade = leitura.nextDouble(); // Lê a quantidade a ser convertida
                double taxaConversao = moeda.BRL(); // Obtém a taxa de conversão
                double resultado = converterBrlParaUsd(quantidade, taxaConversao); // Realiza a conversão

                // Exibe o resultado formatado
                System.out.print("\n");
                System.out.print("Valor " + quantidade + " [BRL] corresponde ao valor final de =>>> " + decimalFormat.format(resultado) + " [USD]");
            } else if (opcao == 5) {
                // Converte de Dólar para Peso Colombiano
                System.out.println("Digite o valor que deseja converter:");
                double quantidade = leitura.nextDouble(); // Lê a quantidade a ser convertida
                double taxaConversao = moeda.COP(); // Obtém a taxa de conversão
                double resultado = converterDolarParaDemaisMoedas(quantidade, taxaConversao); // Realiza a conversão

                // Exibe o resultado formatado
                System.out.print("\n");
                System.out.print("Valor " + quantidade + " [USD] corresponde ao valor final de =>>> " + decimalFormat.format(resultado) + " [COP]");
            } else if (opcao == 6) {
                // Converte de Peso Colombiano para Dólar
                System.out.println("Digite o valor que deseja converter:");
                double quantidade = leitura.nextDouble(); // Lê a quantidade a ser convertida
                double taxaConversao = moeda.COP(); // Obtém a taxa de conversão
                double resultado = converterCopParaUsd(quantidade, taxaConversao); // Realiza a conversão

                // Exibe o resultado formatado
                System.out.print("\n");
                System.out.print("Valor " + quantidade + " [COP] corresponde ao valor final de =>>> " + decimalFormat.format(resultado) + " [USD]");
            } else if (opcao == 7) {
                // Encerra o programa
                System.out.print("\n");
                System.out.println("Programa encerrado!");
            } else {
                // Caso o usuário escolha uma opção inválida
                System.out.print("\n");
                System.out.println("Opção inválida, tente novamente!");

            }

        }
    }

    // Método que realiza a conversão de Dólar para outras moedas
    static double converterDolarParaDemaisMoedas(double quantidade, double taxaConversao) {
        return quantidade * taxaConversao; // Multiplica a quantidade pela taxa de conversão
    }

    // Método que realiza a conversão de Peso Argentino para Dólar
    static double converterArsParaUsd(double quantidade, double taxaConversao) {
        return quantidade / taxaConversao; // Divide a quantidade pela taxa de conversão
    }

    // Método que realiza a conversão de Real Brasileiro para Dólar
    static double converterBrlParaUsd(double quantidade, double taxaConversao) {
        return quantidade / taxaConversao; // Divide a quantidade pela taxa de conversão
    }

    // Método que realiza a conversão de Peso Colombiano para Dólar
    static double converterCopParaUsd(double quantidade, double taxaConversao) {
        return quantidade / taxaConversao; // Divide a quantidade pela taxa de conversão
    }

}
