package feastplannerecalc.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import feastplannerecalc.model.Comida;
import feastplannerecalc.model.ComidaQuantidadePadrao;

public class SimulacaoChurrasco {

    private int homens;
    private int mulheres;
    private int comiloes;
    private int criancas;
    private int vegetarianos;
    
    private List<String> bovinoComOsso;
    private List<String> bovinoSemOsso;
    private List<String> suinoComOsso;
    private List<String> suinoSemOsso;
    private List<String> frangoComOsso;
    private List<String> agregados;
    
    private List<Double> quantidadesAjustadasTotais = new ArrayList<>();
    private Map<String, Double> carnesComOssoMap = new HashMap<>();
    private Map<String, Double> carnesSemOssoMap = new HashMap<>();
    private Map<String, Double> carnesFrangoMap = new HashMap<>(); // Novo mapa para frango

    // Construtores, getters e setters
    public SimulacaoChurrasco() {}

    public int getHomens() {
        return homens;
    }

    public void setHomens(int homens) {
        this.homens = homens;
    }

    public int getMulheres() {
        return mulheres;
    }

    public void setMulheres(int mulheres) {
        this.mulheres = mulheres;
    }

    public int getComiloes() {
        return comiloes;
    }

    public void setComiloes(int comiloes) {
        this.comiloes = comiloes;
    }

    public int getCriancas() {
        return criancas;
    }

    public void setCriancas(int criancas) {
        this.criancas = criancas;
    }

    public int getVegetarianos() {
        return vegetarianos;
    }

    public void setVegetarianos(int vegetarianos) {
        this.vegetarianos = vegetarianos;
    }

    public List<String> getBovinoComOsso() {
        return bovinoComOsso;
    }

    public void setBovinoComOsso(List<String> bovinoComOsso) {
        this.bovinoComOsso = bovinoComOsso;
    }

    public List<String> getBovinoSemOsso() {
        return bovinoSemOsso;
    }

    public void setBovinoSemOsso(List<String> bovinoSemOsso) {
        this.bovinoSemOsso = bovinoSemOsso;
    }
    
    public List<String> getSuinoComOsso() {
        return suinoComOsso;
    }

    public void setSuinoComOsso(List<String> suinoComOsso) {
        this.suinoComOsso = suinoComOsso;
    }
    
    public List<String> getSuinoSemOsso() {
        return suinoSemOsso;
    }

    public void setSuinoSemOsso(List<String> suinoSemOsso) {
        this.suinoSemOsso = suinoSemOsso;
    }
    
    public List<String> getFrangoComOsso() {
        return frangoComOsso;
    }

    public void setFrangoComOsso(List<String> frangoComOsso) {
        this.frangoComOsso = frangoComOsso;
    }
    
    public List<String> getAgregados() {
        return agregados;
    }
    
    public void setAgregados(List<String> agregados) {
        this.agregados = agregados;
    }
    
    public double calcularQuantidadeTotalComida(List<ComidaQuantidadePadrao> listaQuantidades) {
        // Obtém a quantidade de carne por tipo de pessoa
        double quantidadeCarneHomem = 0;
        double quantidadeCarneMulher = 0;
        double quantidadeCarneComilao = 0;
        double quantidadeCarneCrianca = 0;

        // Itera sobre a lista de quantidades padrão para encontrar as categorias
        for (ComidaQuantidadePadrao quantidade : listaQuantidades) {
            switch (quantidade.getPessoasCategoria().getCategoria().toLowerCase()) {
                case "homem":
                    quantidadeCarneHomem = quantidade.getQuantidadeCarne();
                    break;
                case "mulher":
                    quantidadeCarneMulher = quantidade.getQuantidadeCarne();
                    break;
                case "comilão":
                    quantidadeCarneComilao = quantidade.getQuantidadeCarne();
                    break;
                case "criança":
                    quantidadeCarneCrianca = quantidade.getQuantidadeCarne();
                    break;
            }
        }

        // Multiplicar a quantidade de carne por número de pessoas de cada categoria
        double totalCarneHomem = quantidadeCarneHomem * getHomens();
        double totalCarneMulher = quantidadeCarneMulher * getMulheres();
        double totalCarneComilao = quantidadeCarneComilao * getComiloes();
        double totalCarneCrianca = quantidadeCarneCrianca * getCriancas();
        double totalCarnes = (totalCarneCrianca+totalCarneComilao+totalCarneMulher+totalCarneHomem);

        // Imprimir os resultados no console
        System.out.println("Total de Carne para Homens: " + totalCarneHomem + " kg");
        System.out.println("Total de Carne para Mulheres: " + totalCarneMulher + " kg");
        System.out.println("Total de Carne para Comilões: " + totalCarneComilao + " kg");
        System.out.println("Total de Carne para Crianças: " + totalCarneCrianca + " kg");
        System.out.println("Total de Carne geral: " + totalCarnes + " kg");
        
        // Retornar o total de carne geral
        return totalCarnes;
    }
    

    public void calcularDistribuicaoCarnes(double quantidadeTotal) {
        // Verificar quais categorias de carnes foram selecionadas
        boolean bovinoSelecionado = !bovinoComOsso.isEmpty() || !bovinoSemOsso.isEmpty();
        boolean suinoSelecionado = !suinoComOsso.isEmpty() || !suinoSemOsso.isEmpty();
        boolean frangoSelecionado = !frangoComOsso.isEmpty();

        // Definir porcentagens baseadas nas seleções
        double porcentagemBovina = 0, porcentagemSuina = 0, porcentagemFrango = 0;
        int categoriasSelecionadas = (bovinoSelecionado ? 1 : 0) + (suinoSelecionado ? 1 : 0) + (frangoSelecionado ? 1 : 0);

        // Ajustar as porcentagens com base nas carnes selecionadas
        switch (categoriasSelecionadas) {
            case 1:
                if (bovinoSelecionado) porcentagemBovina = 1.0;
                if (suinoSelecionado) porcentagemSuina = 1.0;
                if (frangoSelecionado) porcentagemFrango = 1.0;
                break;
            case 2:
                if (bovinoSelecionado && suinoSelecionado) {
                    porcentagemBovina = 0.60;
                    porcentagemSuina = 0.40;
                } else if (bovinoSelecionado && frangoSelecionado) {
                    porcentagemBovina = 0.65;
                    porcentagemFrango = 0.35;
                } else if (suinoSelecionado && frangoSelecionado) {
                    porcentagemSuina = 0.50;
                    porcentagemFrango = 0.50;
                }
                break;
            case 3:
                porcentagemBovina = 0.50;
                porcentagemSuina = 0.375;
                porcentagemFrango = 0.125;
                break;
        }

        // Distribuir as quantidades para cada categoria de carne
        if (bovinoSelecionado) {
            distribuirCarnesPorTipo(bovinoComOsso, bovinoSemOsso, quantidadeTotal * porcentagemBovina, 0.50, 0.50);
        }
        if (suinoSelecionado) {
            distribuirCarnesPorTipo(suinoComOsso, suinoSemOsso, quantidadeTotal * porcentagemSuina, 0.33, 0.67);
        }
        if (frangoSelecionado) {
        	 distribuirCarnes(frangoComOsso, quantidadeTotal * porcentagemFrango, carnesFrangoMap); // Passa o mapa de frango
        }
     // Imprime a quantidade total ajustada de todas as carnes após todas as distribuições
        imprimirECalcularTotal(quantidadesAjustadasTotais);
    }

    // Função para dividir carnes entre tipos com e sem osso, ou aplicar 100% se apenas uma opção estiver disponível
    private void distribuirCarnesPorTipo(List<String> comOsso, List<String> semOsso, double quantidade, double percComOsso, double percSemOsso) {
        if (!comOsso.isEmpty() && !semOsso.isEmpty()) {
            distribuirCarnes(comOsso, quantidade * percComOsso, carnesComOssoMap);
            distribuirCarnes(semOsso, quantidade * percSemOsso, carnesSemOssoMap);
        } else if (!comOsso.isEmpty()) {
            distribuirCarnes(comOsso, quantidade, carnesComOssoMap);
        } else if (!semOsso.isEmpty()) {
            distribuirCarnes(semOsso, quantidade, carnesSemOssoMap);
        }
    }

    private void distribuirCarnes(List<String> carnes, double quantidade, Map<String, Double> mapaCarnes) {
        if (!carnes.isEmpty()) {
            List<Comida> comidasSelecionadas = SimulacaoChurrasco.carregarAproveitamento(this);
            Map<String, Double> mapaAproveitamento = new HashMap<>();
            for (Comida comida : comidasSelecionadas) {
                mapaAproveitamento.put(comida.getNome(), comida.getAproveitamento());
            }

            double porItem = quantidade / carnes.size();

            for (String item : carnes) {
                double aproveitamentoItem = mapaAproveitamento.getOrDefault(item, 1.0);
                double quantidadeAjustada = porItem / aproveitamentoItem;
                quantidadesAjustadasTotais.add(quantidadeAjustada);
                mapaCarnes.put(item, quantidadeAjustada / 1000); // Armazena em kg
            }
        }
    }
    
    public Map<String, Double> obterCarnesComOsso() {
        return carnesComOssoMap;
    }

    public Map<String, Double> obterCarnesSemOsso() {
        return carnesSemOssoMap;
    }
    
    public Map<String, Double> obterCarnesFrango() { // Novo método para obter o mapa de frango
        return carnesFrangoMap;
    }

    private void imprimirECalcularTotal(List<Double> quantidadesAjustadas) {
        double totalAjustado = 0.0;
        System.out.println("Quantidades ajustadas por carne:");

        for (Double quantidade : quantidadesAjustadas) {
            totalAjustado += quantidade;
            System.out.printf("%.3f kg%n", quantidade / 1000); // Divide por 1000 para exibição
        }

        System.out.printf("Quantidade total ajustada de carnes: %.3f kg%n", totalAjustado / 1000);
    }



    
    // Método estático que carrega e filtra os aproveitamentos das comidas selecionadas
    public static List<Comida> carregarAproveitamento(SimulacaoChurrasco simulacao) {
        // Carrega todas as comidas
        List<Comida> listaComidas = Comida.carregarTodasComidas();

        // Combina todas as listas de comidas selecionadas pelo usuário
        List<String> selecionados = new ArrayList<>();
        selecionados.addAll(simulacao.getBovinoComOsso());
        selecionados.addAll(simulacao.getSuinoComOsso());
        selecionados.addAll(simulacao.getFrangoComOsso());
        selecionados.addAll(simulacao.getBovinoSemOsso());
        selecionados.addAll(simulacao.getSuinoSemOsso());
        selecionados.addAll(simulacao.getAgregados());

        // Filtra as comidas selecionadas
        List<Comida> comidasSelecionadas = new ArrayList<>();
        for (Comida comida : listaComidas) {
            if (selecionados.contains(comida.getNome())) {
                comidasSelecionadas.add(comida);
            }
        }
        return comidasSelecionadas;
    }
    
    public Map<String, Double> calcularQuantidadesAgregados(int totalPessoas) {
        List<Comida> comidasSelecionadas = carregarAproveitamento(this); // Usando método que já filtra selecionados
        Map<String, Double> quantidadesAgregados = new HashMap<>();

        for (Comida agregado : comidasSelecionadas) {
            if (getAgregados().contains(agregado.getNome())) { // Verifica se o item é um agregado
                double quantidadeAjustada = totalPessoas / agregado.getAproveitamento();
                quantidadesAgregados.put(agregado.getNome(), quantidadeAjustada);
            }
        }

        return quantidadesAgregados;
    }

    public Map<String, Double> obterQuantidadesAgregados() {
        return calcularQuantidadesAgregados(this.getTotalPessoas()); // Supondo que temos um método `getTotalPessoas`
    }
    
    public int getTotalPessoas() {
        return homens + mulheres + comiloes + criancas + vegetarianos;
    }

    

}
