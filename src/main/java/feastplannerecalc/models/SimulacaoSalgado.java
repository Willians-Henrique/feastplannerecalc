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
import feastplannerecalc.model.ResultadoSalgado;
import feastplannerecalc.model.ResultadoSimulacao;
import feastplannerecalc.views.SimulacaoBebidas;

public class SimulacaoSalgado {

    private int homens;
    private int mulheres;
    private int comiloes;
    private int criancas;
    private int totalSalgadosArredondado;
    //private int vegetarianos;
    
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

   // public int getVegetarianos() {
     //   return vegetarianos;
    //}

   // public void setVegetarianos(int vegetarianos) {
    //    this.vegetarianos = vegetarianos;
   // }

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
        
        // Armazena o total arredondado na variável global
        this.totalSalgadosArredondado = totalSalgados;
        
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


    // Método para calcular o total de pessoas em SimulacaoSalgado
    public int getTotalPessoas() {
        return homens + mulheres + comiloes + criancas;
    }

    public void salvarResultadoNoBancoDeDados() {
    	
        // Obtém o objeto ResultadoSimulacao global
        ResultadoSimulacao resultadoSimulacao = SimulacaoBebidas.getResultadoSimulacaoGlobal();

        if (resultadoSimulacao == null) {
            System.out.println("Erro: resultadoSimulacao não está disponível.");
            return;
        }
        
        // Obtém o ID
        Long idSimulacao = resultadoSimulacao.getId();
        
        if (idSimulacao == null) {
            System.out.println("Erro: ID da simulação não encontrado!");
            return;
        }
        
        // Inicia uma sessão Hibernate
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Cria um novo objeto ResultadoSalgado
            ResultadoSalgado resultado = new ResultadoSalgado();

            // Configura a simulação relacionada
            ResultadoSimulacao simulacao = new ResultadoSimulacao();
            simulacao.setId(idSimulacao); // Usa o ID correto
            resultado.setSimulacao(simulacao);
            

            // Configura o total de salgados arredondado
            resultado.setTotalComida(this.totalSalgadosArredondado);

            // Obtém os dados de salgados com carne e popula os campos
            Map<String, Integer> salgadosComCarne = this.obterSalgadosComCarne();
            resultado.setPizza(salgadosComCarne.getOrDefault("Pizza", 0).doubleValue());
            resultado.setEsfirra(salgadosComCarne.getOrDefault("Esfirra", 0).doubleValue());
            resultado.setHamburger(salgadosComCarne.getOrDefault("Hamburger", 0).doubleValue());
            resultado.setEmpadinha(salgadosComCarne.getOrDefault("Empadinha", 0).doubleValue());
            resultado.setCoxinha(salgadosComCarne.getOrDefault("Coxinha", 0).doubleValue());
            resultado.setKibe(salgadosComCarne.getOrDefault("Kibe", 0).doubleValue());
            resultado.setPastel(salgadosComCarne.getOrDefault("Pastel", 0).doubleValue());
            resultado.setRisoles(salgadosComCarne.getOrDefault("Risoles", 0).doubleValue());
            resultado.setCigarrete(salgadosComCarne.getOrDefault("Cigarrete", 0).doubleValue());
            resultado.setCroquete(salgadosComCarne.getOrDefault("Croquete", 0).doubleValue());


            // Obtém os dados de salgados sem carne e popula os campos
            Map<String, Integer> salgadosSemCarne = this.obterSalgadosSemCarne();
            resultado.setPizzaDeQueijo(salgadosSemCarne.getOrDefault("Pizza de Queijo", 0).doubleValue());
            resultado.setEsfirraDeQueijo(salgadosSemCarne.getOrDefault("Esfirra de Queijo", 0).doubleValue());
            resultado.setEmpadinhaDeQueijo(salgadosSemCarne.getOrDefault("Empadinha de Queijo", 0).doubleValue());
            resultado.setPastelDeQueijo(salgadosSemCarne.getOrDefault("Pastel de Queijo", 0).doubleValue());
            resultado.setBolinhaDeQueijo(salgadosSemCarne.getOrDefault("Bolinha de Queijo", 0).doubleValue());
            resultado.setCigarreteDeQueijo(salgadosSemCarne.getOrDefault("Cigarette de Queijo", 0).doubleValue());

            // Adiciona os agregados
            Map<String, Double> agregados = this.obterQuantidadesAgregadosSalgado();
            resultado.setArroz(agregados.getOrDefault("Arroz", 0.0));
            resultado.setFarofa(agregados.getOrDefault("Farofa", 0.0));
            resultado.setVinagrete(agregados.getOrDefault("Vinagrete", 0.0));
            resultado.setPaoAlho(agregados.getOrDefault("Pão de Alho", 0.0));
            resultado.setPaoFrances(agregados.getOrDefault("Pão Francês", 0.0));
            resultado.setQueijoCoalho(agregados.getOrDefault("Queijo Coalho", 0.0));
            
            // Adiciona as bebidas
            Map<String, Double> bebidas = this.obterBebidasSelecionadas();
            resultado.setCervejaSemAlcool(bebidas.getOrDefault("Cerveja sem Álcool", 0.0));
            resultado.setAgua(bebidas.getOrDefault("Água", 0.0));
            resultado.setSuco(bebidas.getOrDefault("Suco", 0.0));
            resultado.setRefrigerante(bebidas.getOrDefault("Refrigerante", 0.0));
            resultado.setCerveja(bebidas.getOrDefault("Cerveja", 0.0));
            resultado.setVinho(bebidas.getOrDefault("Vinho", 0.0));
            resultado.setVodka(bebidas.getOrDefault("Vodka", 0.0));
            resultado.setDrink(bebidas.getOrDefault("Drink", 0.0));

            // Configura os itens obrigatórios
            resultado.setCopo(100.0); // Quantidade genérica, ajuste conforme a lógica
            resultado.setPrato(100.0);
            resultado.setPapelToalha(2.0); // Exemplo de quantidade de rolos

            double totalBebidas = bebidas.values().stream().mapToDouble(Double::doubleValue).sum();
            resultado.setTotalBebida(totalBebidas);
            
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
