package feastplannerecalc.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;


import feastplannerecalc.config.MainWindowConfig;
import feastplannerecalc.database.HibernateUtil;
import feastplannerecalc.model.ComidaTipo;
import feastplannerecalc.model.ResultadoSimulacao;
import feastplannerecalc.models.SimulacaoSalgado;
import jakarta.transaction.Transaction;


public class SimulacaoBebidas extends JPanel {
	private static final long serialVersionUID = 1L;
	private MainWindow mainWindow; // Referência à MainWindow
	private SimulacaoSalgado simulacao;
	
	//JCheckBoxes para capturar as opcoes de bebidas nao alcoolicas
    private JCheckBox cervejaSemAlcoolCheckbox;
    private JCheckBox aguaCheckBox;
    private JCheckBox sucoCheckBox;
    private JCheckBox refrigeranteCheckBox;
    
	//JCheckBoxes para capturar as opcoes de bebidas alcoolicas
    private JCheckBox cervejaCheckbox;
    private JCheckBox vinhoCheckBox;
    private JCheckBox vodkaCheckBox;
    private JCheckBox drinkCheckBox;

	public SimulacaoBebidas(MainWindow mainWindow, SimulacaoSalgado simulacao) {
        this.mainWindow = mainWindow; // Armazena a referência à janela principal
        this.simulacao = simulacao;   // Armazena a referência à simulação

        // Painel "mãe" que vai conter todos os componentes
        setLayout(new BorderLayout()); // Usaremos BorderLayout para estruturar os componentes principais
        JPanel simulacaoPanel = new JPanel(new BorderLayout());
        simulacaoPanel.setBorder(BorderFactory.createTitledBorder("Simulação de Bebidas - Salgados"));

        // Painel central que vai conter os 2 painéis (leftPanel, rightPanel)
        JPanel centralPanel = new JPanel(new GridLayout(1, 2)); // Divide em 2 colunas
        simulacaoPanel.add(centralPanel, BorderLayout.CENTER);

        // Painel da Esquerda (Bebidas Não Alcoólicas)
        JPanel nonAlcoholicPanel = new JPanel();
        nonAlcoholicPanel.setLayout(new BoxLayout(nonAlcoholicPanel, BoxLayout.Y_AXIS));
        nonAlcoholicPanel.setBorder(BorderFactory.createTitledBorder("Bebidas Não Alcoólicas"));
        
        // Adiciona os itens de bebidas não alcoólicas
        this.cervejaSemAlcoolCheckbox = new JCheckBox("Cerveja Sem Álcool");
        nonAlcoholicPanel.add(this.cervejaSemAlcoolCheckbox, centralPanel);

        this.aguaCheckBox = new JCheckBox("Água");
        nonAlcoholicPanel.add(this.aguaCheckBox, centralPanel);
        
        this.sucoCheckBox = new JCheckBox("Suco");
        nonAlcoholicPanel.add(this.sucoCheckBox, centralPanel);
        
        this.refrigeranteCheckBox = new JCheckBox("Refrigerante");
        nonAlcoholicPanel.add(this.refrigeranteCheckBox, centralPanel);

        // Painel da Direita (Bebidas Alcoólicas)
        JPanel alcoholicPanel = new JPanel();
        alcoholicPanel.setLayout(new BoxLayout(alcoholicPanel, BoxLayout.Y_AXIS));
        alcoholicPanel.setBorder(BorderFactory.createTitledBorder("Bebidas Alcoólicas"));
        
        // Adiciona os itens de bebidas alcoólicas
        this.cervejaCheckbox = new JCheckBox("Cerveja");
        alcoholicPanel.add(this.cervejaCheckbox, centralPanel);
        
        this.vinhoCheckBox = new JCheckBox("Vinho");
        alcoholicPanel.add(this.vinhoCheckBox, centralPanel);
        
        this.vodkaCheckBox = new JCheckBox("Vodka");
        alcoholicPanel.add(this.vodkaCheckBox, centralPanel);
        
        this.drinkCheckBox = new JCheckBox("Drink");
        alcoholicPanel.add(this.drinkCheckBox, centralPanel);
        
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
                mainWindow.showPanel(new NovaSimulacaoSalgados(mainWindow));
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

	                // Preenche os dados do objeto simulacao para o banco de dados
	                resultadoSimulacao.setQuantidadeHomens(simulacao.getHomens());
	                resultadoSimulacao.setQuantidadeMulheres(simulacao.getMulheres());
	                resultadoSimulacao.setQuantidadeCriancas(simulacao.getCriancas());
	                resultadoSimulacao.setQuantidadeComiloes(simulacao.getComiloes());
	                resultadoSimulacao.setQuantidadeNaoComemCarne(simulacao.getVegetarianos());
	                
	                // Defina o tipo de comida (por exemplo, Salgado) para a simulação atual
	                ComidaTipo tipoComida = new ComidaTipo(); // Assumindo que você tenha uma lógica para definir o tipo
	                tipoComida.setId(2L); // chave estrangeira do tipo de construção
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
	                mainWindow.showPanel(new ResultadoSimulacaoSalgado(mainWindow,simulacao).getPanel());
	            
	             // Adicionar bebidas sem alcool selecionadas
	    	        List<String> bebidasSemAlcool = new ArrayList<>();
	    	        if (cervejaSemAlcoolCheckbox.isSelected()) bebidasSemAlcool.add("Cerveja Sem Álcool");
	    	        if (aguaCheckBox.isSelected()) bebidasSemAlcool.add("Água");
	    	        if (sucoCheckBox.isSelected()) bebidasSemAlcool.add("Suco");
	    	        if (refrigeranteCheckBox.isSelected()) bebidasSemAlcool.add("Refrigerante");
	    	        
	    	        simulacao.setBebidasSemAlcool(bebidasSemAlcool);
	    	     // Loop para exibir bebidas sem álcool no console
	    	        System.out.println("Bebidas Sem Álcool Selecionadas:");
	    	        for (String bebida : bebidasSemAlcool) {
	    	            System.out.println("- " + bebida);
	    	        }
	            
	             // Adicionar bebidas com alcool selecionadas
	    	        List<String> bebidasComAlcool = new ArrayList<>();
	    	        if (cervejaCheckbox.isSelected()) bebidasComAlcool.add("Cerveja");
	    	        if (vinhoCheckBox.isSelected()) bebidasComAlcool.add("Vinho");
	    	        if (vodkaCheckBox.isSelected()) bebidasComAlcool.add("Vodka");
	    	        if (drinkCheckBox.isSelected()) bebidasComAlcool.add("Drink");
	    	        
	    	        simulacao.setBebidasSemAlcool(bebidasComAlcool);
	    	        
	    	     // Loop para exibir bebidas com álcool no console
	    	        System.out.println("Bebidas Com Álcool Selecionadas:");
	    	        for (String bebida : bebidasComAlcool) {
	    	            System.out.println("- " + bebida);
	    	        }
	            }
	        });

    }
	public JPanel getPanel() {
        return this; // Retorna o painel para exibição
    }
}
