package feastplannerecalc.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import feastplannerecalc.model.Comida;
import feastplannerecalc.model.ComidaQuantidadePadrao;

public class SimulacaoSalgado {

    private int homens;
    private int mulheres;
    private int comiloes;
    private int criancas;
    private int vegetarianos;
    
    private List<String> salgadosAssados;
    private List<String> salgadosFritos;
    private List<String> agregados;
    private List<String> bebidasSemAlcool;
    private List<String> bebidasComAlcool;
    
    private HashMap<String, Integer> salgadosComCarneQuantidades = new HashMap<>();
    private HashMap<String, Integer> salgadosSemCarneQuantidades = new HashMap<>();


    // Construtores, getters e setters
    public SimulacaoSalgado() {}

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

    public List<String> getSalgadosAssados() {
        return salgadosAssados;
    }

    public void setSalgadosAssados(List<String> salgadosAssados) {
        this.salgadosAssados = salgadosAssados;
    }

    public List<String> getSalgadosFritos() {
        return salgadosFritos;
    }

    public void setSalgadosFritos(List<String> salgadosFritos) {
        this.salgadosFritos = salgadosFritos;
    }
    
    public List<String> getAgregados() {
        return agregados;
    }

    public void setAgregados(List<String> agregados) {
        this.agregados = agregados;
    }
    
    public List<String> getBebidasSemAlcool() {
        return bebidasSemAlcool;
    }

    public void setBebidasSemAlcool(List<String> bebidasSemAlcool) {
        this.bebidasSemAlcool = bebidasSemAlcool;
    }
    
    public List<String> getBebidasComAlcool() {
        return bebidasComAlcool;
    }

    public void setBebidasComAlcool(List<String> bebidasComAlcool) {
        this.bebidasComAlcool = bebidasComAlcool;
    }
    
    public double calcularQuantidadeTotalComida(List<ComidaQuantidadePadrao> listaQuantidades) {
        // Obtém a quantidade de salgado por tipo de pessoa
        double quantidadeSalgadoHomem = 0;
        double quantidadeSalgadoMulher = 0;
        double quantidadeSalgadoComilao = 0;
        double quantidadeSalgadoCrianca = 0;

        // Itera sobre a lista de quantidades padrão para encontrar as categorias
        for (ComidaQuantidadePadrao quantidade : listaQuantidades) {
            switch (quantidade.getPessoasCategoria().getCategoria().toLowerCase()) {
                case "homem":
                	quantidadeSalgadoHomem = quantidade.getQuantidadeSalgado();
                    break;
                case "mulher":
                	quantidadeSalgadoMulher = quantidade.getQuantidadeSalgado();
                    break;
                case "comilão":
                	quantidadeSalgadoComilao = quantidade.getQuantidadeSalgado();
                    break;
                case "criança":
                	quantidadeSalgadoCrianca = quantidade.getQuantidadeSalgado();
                    break;
            }
        }

        // Multiplicar a quantidade de carne por número de pessoas de cada categoria
        double totalSalgadoHomem = quantidadeSalgadoHomem * getHomens();
        double totalSalgadoMulher = quantidadeSalgadoMulher * getMulheres();
        double totalSalgadoComilao = quantidadeSalgadoComilao * getComiloes();
        double totalSalgadoCrianca = quantidadeSalgadoCrianca * getCriancas();
        double totalSagados = (totalSalgadoCrianca+totalSalgadoComilao+totalSalgadoMulher+totalSalgadoHomem);

        // Imprimir os resultados no console
        System.out.println("Total de Salgados para Homens: " + totalSalgadoHomem + " un");
        System.out.println("Total de Salgados para Mulheres: " + totalSalgadoMulher + " un");
        System.out.println("Total de Salgados para Comilões: " + totalSalgadoComilao + " un");
        System.out.println("Total de Salgados para Crianças: " + totalSalgadoCrianca + " un");
        System.out.println("Total de Salgados geral: " + totalSagados + " un");
        
        // Retornar o total de carne geral
        return totalSagados;
    }
    
    public void calcularDistribuicaoSalgados(double quantidadeTotal) {
        // Arredonda a quantidade total de salgados para o próximo múltiplo de 100
        int totalSalgados = (int) Math.ceil(quantidadeTotal / 100) * 100;

        // Calcula a quantidade total de centros de 100 salgados
        int centros = totalSalgados / 100;

        // Define a quantidade total de salgados a ser dividida
        int quantidadePorCentro = centros * 100;
        int quantidadeFritos = 0;
        int quantidadeAssados = 0;

        // Verifica quais categorias foram selecionadas e define a divisão entre fritos e assados
        boolean fritosSelecionados = !salgadosFritos.isEmpty();
        boolean assadosSelecionados = !salgadosAssados.isEmpty();

        if (fritosSelecionados && assadosSelecionados) {
            // Divide igualmente entre fritos e assados
            quantidadeFritos = quantidadePorCentro / 2;
            quantidadeAssados = quantidadePorCentro / 2;
        } else if (fritosSelecionados) {
            // Todos os salgados são fritos
            quantidadeFritos = quantidadePorCentro;
        } else if (assadosSelecionados) {
            // Todos os salgados são assados
            quantidadeAssados = quantidadePorCentro;
        }

        // Distribui a quantidade de fritos entre os tipos selecionados
        distribuirSalgados(salgadosFritos, quantidadeFritos, salgadosComCarneQuantidades);

        // Distribui a quantidade de assados entre os tipos selecionados
        distribuirSalgados(salgadosAssados, quantidadeAssados, salgadosSemCarneQuantidades);
    }

    private void distribuirSalgados(List<String> salgados, int quantidade, HashMap<String, Integer> hashMapDestino) {
        if (!salgados.isEmpty()) {
            int porSalgado = quantidade / salgados.size();

            for (String salgado : salgados) {
                hashMapDestino.put(salgado, porSalgado);
            }
        }
    }

    // Métodos para obter os HashMaps com os resultados
    public HashMap<String, Integer> obterSalgadosComCarne() {
        return salgadosComCarneQuantidades;
    }

    public HashMap<String, Integer> obterSalgadosSemCarne() {
        return salgadosSemCarneQuantidades;
    }

    public List<String> obterAcessoriosEBebidas() {
        List<String> acessoriosEBebidas = new ArrayList<>();
        // Adicione lógica para adicionar bebidas e acessórios formatados, exemplo:
        // acessoriosEBebidas.add("Cerveja: " + quantidadeCerveja + " L");
        return acessoriosEBebidas;
    }
    
    public Map<String, Double> calcularQuantidadesAgregadosSalgado() {
    	List<Comida> comidasSelecionadas = Comida.carregarTodasComidas(); // Carrega todas as comidas do banco
        Map<String, Double> quantidadesAgregados = new HashMap<>();
        int totalPessoas = getTotalPessoas(); // Utiliza o método para obter o total de pessoas

        for (Comida agregado : comidasSelecionadas) {
            if (getAgregados().contains(agregado.getNome())) { // Verifica se o item é um agregado
                double quantidadeAjustada = totalPessoas / agregado.getAproveitamento();
                quantidadesAgregados.put(agregado.getNome(), quantidadeAjustada);
            }
        }

        return quantidadesAgregados;
    }
  
    public Map<String, Double> obterQuantidadesAgregadosSalgado() {
        return calcularQuantidadesAgregadosSalgado(); // Chama o método que calcula as quantidades
    }

   

    // Método para calcular o total de pessoas em SimulacaoSalgado
    public int getTotalPessoas() {
        return homens + mulheres + comiloes + criancas + vegetarianos;
    }


}
