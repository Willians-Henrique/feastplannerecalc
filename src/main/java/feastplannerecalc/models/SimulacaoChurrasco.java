package feastplannerecalc.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import feastplannerecalc.database.HibernateUtil;
import feastplannerecalc.model.BebidaQuantidadePadrao;
import feastplannerecalc.model.Comida;
import feastplannerecalc.model.ComidaQuantidadePadrao;
import feastplannerecalc.model.ResultadoChurrasco;
import feastplannerecalc.model.ResultadoSimulacao;

public class SimulacaoChurrasco {

    private int homens;
    private int mulheres;
    private int comiloes;
    private int criancas;
   // private int vegetarianos;
    
    private List<String> bovinoComOsso;
    private List<String> bovinoSemOsso;
    private List<String> suinoComOsso;
    private List<String> suinoSemOsso;
    private List<String> frangoComOsso;
    private List<String> agregados;
    private List<String> bebidasSemAlcool;
    private List<String> bebidasComAlcool;
    
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

    //public int getVegetarianos() {
   //     return vegetarianos;
   // }

    //public void setVegetarianos(int vegetarianos) {
  //      this.vegetarianos = vegetarianos;
  //  }

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

        public Map<String, Double> calcularQuantidadesBebidasSelecionadas() {
        // Carrega todas as bebidas e suas quantidades padrão por pessoa
        List<BebidaQuantidadePadrao> listaBebidas = BebidaQuantidadePadrao.carregarTodasBebidas(); // Carrega bebidas
        Map<String, Double> bebidasSelecionadasQuantidade = new HashMap<>();
        
        // Total de pessoas
        int totalPessoas = getTotalPessoas();
        System.out.println("Total de Pessoas: " + totalPessoas); // Debug: Confirma o total de pessoas
        
        // Cria uma lista única com as bebidas selecionadas, sem e com álcool
        List<String> bebidasSelecionadas = new ArrayList<>();
        bebidasSelecionadas.addAll(this.bebidasSemAlcool != null ? this.bebidasSemAlcool : new ArrayList<>());
        bebidasSelecionadas.addAll(this.bebidasComAlcool != null ? this.bebidasComAlcool : new ArrayList<>());
        
        System.out.println("Bebidas Selecionadas: " + bebidasSelecionadas);  // Debug: Verifica bebidas selecionadas
        
        // Itera sobre todas as bebidas carregadas e verifica se estão na lista de selecionadas
        for (BebidaQuantidadePadrao bebida : listaBebidas) {
            String nomeBebida = bebida.getBebida().getBebida();  // Obtém o nome da bebida
            
            // Verifica se a bebida está na lista de selecionadas
            if (bebidasSelecionadas.contains(nomeBebida)) {
                // Calcula a quantidade total de bebida (em litros) para o número total de pessoas
                double quantidadeTotal = (bebida.getQuantidade() * totalPessoas) / 1000.0;
                bebidasSelecionadasQuantidade.put(nomeBebida, quantidadeTotal);  // Armazena a quantidade no Map
                System.out.println("Bebida: " + nomeBebida + ", Quantidade: " + quantidadeTotal + " litros"); // Debug: Exibe a quantidade calculada
            }
        }

        return bebidasSelecionadasQuantidade;
    }


   
   public Map<String, Double> obterBebidasSelecionadas() {
       return calcularQuantidadesBebidasSelecionadas(); // Chama o método que calcula as quantidades
   }
    
    public int getTotalPessoas() {
        return homens + mulheres + comiloes + criancas;
    }

    public void salvarResultadoNoBancoDeDados() {
        // Inicia uma sessão Hibernate
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
 
            // Cria um novo objeto ResultadoChurrasco
            ResultadoChurrasco resultado = new ResultadoChurrasco();

            // Configura a simulação relacionada
            ResultadoSimulacao simulacao = new ResultadoSimulacao();
            simulacao.setId(1L); // Atualize com o ID real da simulação, se necessário
            resultado.setSimulacao(simulacao);

            // Obtém os dados de salgados com carne e popula os campos
            Map<String, Double> salgadosComCarne = this.obterCarnesComOsso();
            resultado.setCostelaBovina(salgadosComCarne.getOrDefault("Costela Bovina", 0.0).doubleValue());
            resultado.setPrimeRib(salgadosComCarne.getOrDefault("Prime Rib", 0.0).doubleValue());
            resultado.setChuletaPaulista(salgadosComCarne.getOrDefault("Chuleta Paulista", 0.0).doubleValue());
            resultado.setCostelaSuina(salgadosComCarne.getOrDefault("Costela Suína", 0.0).doubleValue());
            resultado.setBisteca(salgadosComCarne.getOrDefault("Bisteca Suína", 0.0).doubleValue());
            resultado.setPernilComOsso(salgadosComCarne.getOrDefault("Pernil com Osso", 0.0).doubleValue());


            // Obtém os dados de salgados sem carne e popula os campos
            Map<String, Double> carnesSemOsso = this.obterCarnesSemOsso();
            resultado.setPicanha(carnesSemOsso.getOrDefault("Picanha", 0.0).doubleValue());
            resultado.setCoxaoMole(carnesSemOsso.getOrDefault("Coxão Mole", 0.0).doubleValue());
            resultado.setAlcatra(carnesSemOsso.getOrDefault("Alcatra", 0.0).doubleValue());
            resultado.setFraldinha(carnesSemOsso.getOrDefault("Fraldinha", 0.0).doubleValue());
            resultado.setContraFile(carnesSemOsso.getOrDefault("Contra Filé", 0.0).doubleValue());
            resultado.setFileMignon(carnesSemOsso.getOrDefault("Filé Mignon", 0.0).doubleValue());
            resultado.setPicanhaSuina(carnesSemOsso.getOrDefault("Picanha Suína", 0.0).doubleValue());
            resultado.setLombo(carnesSemOsso.getOrDefault("Lombo", 0.0).doubleValue());
            resultado.setLinguica(carnesSemOsso.getOrDefault("Linguiça", 0.0).doubleValue());
            resultado.setPaleta(carnesSemOsso.getOrDefault("Paleta", 0.0).doubleValue());

            
            // Obtém os dados de carnes de frango  e popula os campos
            Map<String, Double> carnesFrango = this.obterCarnesFrango();
            resultado.setAsinhaComCoxinha(carnesFrango.getOrDefault("Asinha com Coxinha", 0.0).doubleValue());
            resultado.setTulipaDaAsa(carnesFrango.getOrDefault("Tulipa da Asa", 0.0).doubleValue());
            resultado.setCoxaComSobrecoxa(carnesFrango.getOrDefault("Coxa com Sobrecoxa", 0.0).doubleValue());
            
            // Adiciona os agregados
            Map<String, Double> agregados = this.obterQuantidadesAgregados();
            resultado.setArroz(agregados.getOrDefault("Arroz", 0.0));
            resultado.setFarofa(agregados.getOrDefault("Farofa", 0.0));
            resultado.setVinagrete(agregados.getOrDefault("Vinagrete", 0.0));
            resultado.setCoracaoFrango(agregados.getOrDefault("Coração de Frango", 0.0));
            resultado.setPaoAlho(agregados.getOrDefault("Pão de Alho", 0.0));
            resultado.setPaoFrances(agregados.getOrDefault("Pão Francês", 0.0));
            resultado.setQueijoCoalho(agregados.getOrDefault("Queijo Coalho", 0.0));

            // Configura os itens obrigatórios
            resultado.setCopo(100.0); // Quantidade genérica, ajuste conforme a lógica
            resultado.setPrato(100.0);
            resultado.setPapelToalha(2.0); // Exemplo de quantidade de rolos
            resultado.setCarvao(2.0);
         

            // Persiste o objeto no banco de dados
            session.persist(resultado);

            // Confirma a transação
            transaction.commit();

            System.out.println("Dados salvos com sucesso no banco de dados!");

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
