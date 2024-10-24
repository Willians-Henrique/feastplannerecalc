package feastplannerecalc.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.util.List;
import feastplannerecalc.config.MainWindowConfig;
import feastplannerecalc.database.HibernateUtil;
import feastplannerecalc.model.ComidaQuantidadePadrao;
import feastplannerecalc.model.ComidaTipo;
import feastplannerecalc.model.ResultadoSimulacao;
import feastplannerecalc.models.SimulacaoChurrasco;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;


public class SimulacaoBebidasChurrasco extends JPanel {
	private static final long serialVersionUID = 1L;
	private MainWindow mainWindow; // Referência à MainWindow
	private SimulacaoChurrasco simulacao;

    // Adiciona a variável de lista como campo da classe
    private List<ComidaQuantidadePadrao> listaQuantidades;
    

	public SimulacaoBebidasChurrasco(MainWindow mainWindow, SimulacaoChurrasco simulacao) {
        this.mainWindow = mainWindow; // Armazena a referência 
        
     // Painel "mãe" que vai conter todos os componentes
        setLayout(new BorderLayout()); // Usaremos BorderLayout para estruturar os componentes principais
        JPanel simulacaoPanel = new JPanel(new BorderLayout());
        simulacaoPanel.setBorder(BorderFactory.createTitledBorder("Simulação de Bebidas - Churrasco"));

        // Painel central que vai conter os 2 painéis (leftPanel, rightPanel)
        JPanel centralPanel = new JPanel(new GridLayout(1, 2)); // Divide em 2 colunas
        simulacaoPanel.add(centralPanel, BorderLayout.CENTER);

        // Painel da Esquerda (Bebidas Não Alcoólicas)
        JPanel nonAlcoholicPanel = new JPanel();
        nonAlcoholicPanel.setLayout(new BoxLayout(nonAlcoholicPanel, BoxLayout.Y_AXIS));
        nonAlcoholicPanel.setBorder(BorderFactory.createTitledBorder("Bebidas Não Alcoólicas"));
        
        // Adiciona os itens de bebidas não alcoólicas
        nonAlcoholicPanel.add(new JCheckBox("Cerveja Sem Álcool"));
        nonAlcoholicPanel.add(new JCheckBox("Água"));
        nonAlcoholicPanel.add(new JCheckBox("Suco"));
        nonAlcoholicPanel.add(new JCheckBox("Refrigerante"));

        // Painel da Direita (Bebidas Alcoólicas)
        JPanel alcoholicPanel = new JPanel();
        alcoholicPanel.setLayout(new BoxLayout(alcoholicPanel, BoxLayout.Y_AXIS));
        alcoholicPanel.setBorder(BorderFactory.createTitledBorder("Bebidas Alcoólicas"));
        
        // Adiciona os itens de bebidas alcoólicas
        alcoholicPanel.add(new JCheckBox("Cerveja"));
        alcoholicPanel.add(new JCheckBox("Vinho"));
        alcoholicPanel.add(new JCheckBox("Vodka"));
        alcoholicPanel.add(new JCheckBox("Drink"));

        // Adiciona os subpainéis ao painel central
        centralPanel.add(nonAlcoholicPanel);
        centralPanel.add(alcoholicPanel);

        // Adiciona o painel "simulação" ao painel principal
        add(simulacaoPanel, BorderLayout.CENTER);

        // Adicionar botões Voltar, Cancelar, Simular no final
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton voltarButton = new JButton("Voltar");
        JButton cancelarButton = new JButton("Cancelar");
        JButton simularButton = new JButton("Simular");

        painelBotoes.add(voltarButton);
        painelBotoes.add(cancelarButton);
        painelBotoes.add(simularButton);

        // Adiciona os paineis ao painel principal
        add(simulacaoPanel, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        // Lógica para os botões

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.showPanel(new NovaSimulacaoChurrasco(mainWindow));
            }
        });

	     // Botão Cancelar: retorna para a tela inicial
	        cancelarButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                System.out.println("Botão Cancelar clicado!"); // Para verificar se o botão está funcionando
	                mainWindow.showPanel(new MainWindowConfig().getMainPanel()); // Chama o método para mostrar o painel inicial
	            }
	        });


	     // Botão Simular: lógica para salvar os dados no banco
	        simularButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // Aqui vamos criar uma instância de ResultadoSimulacao e preencher os dados
	                ResultadoSimulacao resultadoSimulacao = new ResultadoSimulacao();
	                
	             // Carrega a lista de quantidades de comida padrão
	                listaQuantidades = ComidaQuantidadePadrao.carregarQuantidadeCarnePorPessoa();
	                
	                System.out.println("Lista de Quantidade de Comida Padrão:");
	                for (ComidaQuantidadePadrao comida : listaQuantidades) {
	                    System.out.println(comida); // Agora imprimirá apenas os valores de quantidade_carne
	                }
	                
	                // Calcula e imprime a quantidade total de comida para cada categoria de pessoa
	                simulacao.calcularQuantidadeTotalComida(listaQuantidades);

	                // Preenche os dados do objeto simulacao para o banco de dados
	                resultadoSimulacao.setQuantidadeHomens(simulacao.getHomens());
	                resultadoSimulacao.setQuantidadeMulheres(simulacao.getMulheres());
	                resultadoSimulacao.setQuantidadeCriancas(simulacao.getCriancas());
	                resultadoSimulacao.setQuantidadeComiloes(simulacao.getComiloes());
	                resultadoSimulacao.setQuantidadeNaoComemCarne(simulacao.getVegetarianos());
	                
	                // Defina o tipo de comida (por exemplo, Salgado) para a simulação atual
	                ComidaTipo tipoComida = new ComidaTipo(); // Assumindo que você tenha uma lógica para definir o tipo
	                tipoComida.setId(1L); // chave estrangeira do tipo de construção
	                resultadoSimulacao.setTipoComida(tipoComida);

	                // Envia os dados para o banco de dados usando Jakarta Persistence
	                try {
	                    EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager(); // Obtém o EntityManager
	                    EntityTransaction transaction = entityManager.getTransaction(); // Obtém a transação

	                    transaction.begin(); // Inicia a transação

	                    entityManager.persist(resultadoSimulacao); // Salva o resultado da simulação no banco

	                    transaction.commit(); // Confirma a transação
	                    System.out.println("Simulação salva com sucesso no banco de dados.");
	                    
	                    entityManager.close(); // Fecha o EntityManager
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                    System.out.println("Erro ao salvar a simulação no banco: " + ex.getMessage());
	                }

	                // Trocar para o painel de resultado da simulação de Salgado
	                mainWindow.showPanel(new ResultadoSimulacaoChurrasco(mainWindow).getPanel());
	            }
	        });

    }
	
	 // Getter para acessar a lista em outra parte do código, se necessário
    public List<ComidaQuantidadePadrao> getListaQuantidades() {
        return listaQuantidades;
    }
    
    
	public JPanel getPanel() {
        return this; // Retorna o painel para exibição
    }
}
