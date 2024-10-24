package feastplannerecalc.models;

import java.util.List;

import feastplannerecalc.model.ComidaQuantidadePadrao;

public class SimulacaoChurrasco {

    private int homens;
    private int mulheres;
    private int comiloes;
    private int criancas;
    private int vegetarianos;
    
    private List<String> carnesComOsso;
    private List<String> carnesSemOsso;
    private List<String> agregados;

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

    public List<String> getCarnesComOsso() {
        return carnesComOsso;
    }

    public void setCarnesComOsso(List<String> carnesComOsso) {
        this.carnesComOsso = carnesComOsso;
    }

    public List<String> getCarnesSemOsso() {
        return carnesSemOsso;
    }

    public void setCarnesSemOsso(List<String> carnesSemOsso) {
        this.carnesSemOsso = carnesSemOsso;
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


}
