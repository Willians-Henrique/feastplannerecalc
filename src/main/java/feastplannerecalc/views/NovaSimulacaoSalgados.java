package feastplannerecalc.views;

import javax.swing.*;

import feastplannerecalc.config.MainWindowConfig;
import feastplannerecalc.models.SimulacaoChurrasco;
import feastplannerecalc.models.SimulacaoSalgado;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class NovaSimulacaoSalgados extends JPanel {
	
    private static final long serialVersionUID = 1L;
    private MainWindow mainWindow; // Referência à MainWindow
    
    // JTextFields para capturar os dados das pessoas
    private JTextField homensTextField;
    private JTextField mulheresTextField;
    private JTextField comiloesTextField;
    private JTextField criancasTextField;
   // private JTextField vegetarianosTextField;

  //JCheckBoxes para capturar as opcoes de agregados
    private JCheckBox arrozCheckbox;
    private JCheckBox farofaCheckBox;
    private JCheckBox vinagreteCheckBox;
    private JCheckBox paodeAlhoCheckBox;
    private JCheckBox paoFrancesCheckBox;
    private JCheckBox queijoCoalhoCheckBox;
    
    // JCheckBoxes para capturar as opções de salgados
    private JCheckBox pizzaCheckBox;
    private JCheckBox esfirraCheckBox;
    private JCheckBox hamburgerCheckBox;
    private JCheckBox empadinhaCheckBox;
    private JCheckBox pizzaDeQueijoCheckBox;
    private JCheckBox esfirraDeQueijoCheckBox;
    private JCheckBox empadinhaDeQueijoCheckBox;
    private JCheckBox coxinhaCheckBox;
    private JCheckBox kibeCheckBox;
    private JCheckBox pastelCheckBox;
    private JCheckBox risolesCheckBox;
    private JCheckBox cigarreteCheckBox;
    private JCheckBox croqueteCheckBox;
    private JCheckBox pastelDeQueijoCheckBox;
    private JCheckBox bolinhaDeQueijoCheckBox;
    private JCheckBox cigarreteDeQueijoCheckBox;

    public NovaSimulacaoSalgados(MainWindow mainWindow) {
        this.mainWindow = mainWindow; // Armazena a referência
        
    	 // Painel "mãe" que vai conter todos os componentes
        setLayout(new BorderLayout()); // Usaremos BorderLayout para estruturar os componentes principais
        JPanel simulacaoPanel = new JPanel(new BorderLayout());
        simulacaoPanel.setBorder(BorderFactory.createTitledBorder("Simulação de Salgados"));
        simulacaoPanel.setBackground(new Color(173, 216, 230)); // Azul claro

        // Painel central que vai conter os 3 painéis (leftPanel, middlePanel, rightPanel)
        JPanel centralPanel = new JPanel(new GridLayout(1, 3)); // Divide em 3 colunas
        centralPanel.setBackground(new Color(173, 216, 230));
        simulacaoPanel.add(centralPanel, BorderLayout.CENTER);
        
        // Configurações de fonte e estilo
        Font labelFont = new Font("Arial", Font.PLAIN, 24);
        Font titleFont = new Font("Arial", Font.BOLD, 14);

        // Painel da Esquerda (com 2 seções: dados de pessoas e agregados)
        JPanel leftPanel = new JPanel(new GridLayout(2, 1)); // Divide em 2 partes iguais
        leftPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 1), 
                " Pessoas e Acompanhamentos", 0, 0, titleFont));
        leftPanel.setBackground(new Color(173, 216, 230));

        // Parte superior: Dados das Pessoas
        JPanel pessoasPanel = new JPanel(new GridBagLayout());
        pessoasPanel.setBorder(BorderFactory.createTitledBorder("Pessoas"));
        pessoasPanel.setBackground(new Color(173, 216, 230));
        
        GridBagConstraints gbcPessoas = new GridBagConstraints();
        gbcPessoas.insets = new Insets(10, 10, 10, 10);
        gbcPessoas.gridx = 0;
        gbcPessoas.gridy = 0;
        gbcPessoas.fill = GridBagConstraints.HORIZONTAL; // Ajusta a largura dos componentes
        gbcPessoas.weightx = 0.5; // Define o peso para distribuir o espaço horizontalmente


        // Configurar rótulos e campos de texto
        JLabel homensLabel = new JLabel("Homens:");
        homensLabel.setFont(labelFont);
        pessoasPanel.add(homensLabel, gbcPessoas);

        gbcPessoas.gridx = 1;
        JLabel mulheresLabel = new JLabel("Mulheres:");
        mulheresLabel.setFont(labelFont);
        pessoasPanel.add(mulheresLabel, gbcPessoas);

        gbcPessoas.gridy = 1;
        gbcPessoas.gridx = 0;
        this.homensTextField = new JTextField(10);
        this.homensTextField.setPreferredSize(new Dimension(50, 30));
        pessoasPanel.add(this.homensTextField, gbcPessoas);

        gbcPessoas.gridx = 1;
        this.mulheresTextField = new JTextField(10);
        this.mulheresTextField.setPreferredSize(new Dimension(50, 30));
        pessoasPanel.add(this.mulheresTextField, gbcPessoas);

        // Segunda linha: Comilões e Crianças
        gbcPessoas.gridy = 2; 
        gbcPessoas.gridx = 0;
        JLabel comiloesLabel = new JLabel("Comilões:");
        comiloesLabel.setFont(labelFont);
        pessoasPanel.add(comiloesLabel, gbcPessoas);

        gbcPessoas.gridx = 1;
        JLabel criancasLabel = new JLabel("Crianças:");
        criancasLabel.setFont(labelFont);
        pessoasPanel.add(criancasLabel, gbcPessoas);

        gbcPessoas.gridy = 3; 
        gbcPessoas.gridx = 0;
        this.comiloesTextField = new JTextField(10);
        this.comiloesTextField.setPreferredSize(new Dimension(50, 30));
        pessoasPanel.add(this.comiloesTextField, gbcPessoas);
        

        gbcPessoas.gridx = 1;
        this.criancasTextField = new JTextField(10);
        this.criancasTextField.setPreferredSize(new Dimension(50, 30));
        pessoasPanel.add(this.criancasTextField, gbcPessoas);

        // Adicionando o painel de pessoas ao painel esquerdo
        leftPanel.add(pessoasPanel);

        // Parte inferior: Agregados
        JPanel agregadosPanel = new JPanel(new GridBagLayout());
        agregadosPanel.setBorder(BorderFactory.createTitledBorder("Agregados"));
        agregadosPanel.setBackground(new Color(173, 216, 230));
        GridBagConstraints gbcAgregados = new GridBagConstraints();
        gbcAgregados.insets = new Insets(5, 5, 5, 5);
        gbcAgregados.gridx = 0;
        gbcAgregados.gridy = GridBagConstraints.RELATIVE;
        gbcAgregados.anchor = GridBagConstraints.WEST;

     // Adiciona checkboxes para agregados
     // Definir fonte maior
        Font checkBoxFont = new Font("Arial", Font.PLAIN, 16); // Tamanho de fonte maior

        // Adiciona checkboxes para agregados
        this.arrozCheckbox = new JCheckBox("Arroz");
        this.arrozCheckbox.setFont(checkBoxFont); // Definir fonte maior
        this.arrozCheckbox.setBackground(new Color(173, 216, 230)); // Cor de fundo igual ao painel
        this.arrozCheckbox.setOpaque(true); // Torna a cor de fundo visível
        agregadosPanel.add(this.arrozCheckbox, gbcAgregados);

        this.farofaCheckBox = new JCheckBox("Farofa");
        this.farofaCheckBox.setFont(checkBoxFont); // Definir fonte maior
        this.farofaCheckBox.setBackground(new Color(173, 216, 230)); // Cor de fundo igual ao painel
        this.farofaCheckBox.setOpaque(true); // Torna a cor de fundo visível
        agregadosPanel.add(this.farofaCheckBox, gbcAgregados);

        this.vinagreteCheckBox = new JCheckBox("Vinagrete");
        this.vinagreteCheckBox.setFont(checkBoxFont); // Definir fonte maior
        this.vinagreteCheckBox.setBackground(new Color(173, 216, 230)); // Cor de fundo igual ao painel
        this.vinagreteCheckBox.setOpaque(true); // Torna a cor de fundo visível
        agregadosPanel.add(this.vinagreteCheckBox, gbcAgregados);

        this.paodeAlhoCheckBox = new JCheckBox("Pão de Alho");
        this.paodeAlhoCheckBox.setFont(checkBoxFont); // Definir fonte maior
        this.paodeAlhoCheckBox.setBackground(new Color(173, 216, 230)); // Cor de fundo igual ao painel
        this.paodeAlhoCheckBox.setOpaque(true); // Torna a cor de fundo visível
        agregadosPanel.add(this.paodeAlhoCheckBox, gbcAgregados);

        this.paoFrancesCheckBox = new JCheckBox("Pão Francês");
        this.paoFrancesCheckBox.setFont(checkBoxFont); // Definir fonte maior
        this.paoFrancesCheckBox.setBackground(new Color(173, 216, 230)); // Cor de fundo igual ao painel
        this.paoFrancesCheckBox.setOpaque(true); // Torna a cor de fundo visível
        agregadosPanel.add(this.paoFrancesCheckBox, gbcAgregados);

        this.queijoCoalhoCheckBox = new JCheckBox("Queijo Coalho");
        this.queijoCoalhoCheckBox.setFont(checkBoxFont); // Definir fonte maior
        this.queijoCoalhoCheckBox.setBackground(new Color(173, 216, 230)); // Cor de fundo igual ao painel
        this.queijoCoalhoCheckBox.setOpaque(true); // Torna a cor de fundo visível
        agregadosPanel.add(this.queijoCoalhoCheckBox, gbcAgregados);

        leftPanel.add(agregadosPanel);
        centralPanel.add(leftPanel);

        // Painel do Meio (opções do banco de dados)
        JPanel middlePanel = new JPanel(new GridLayout(2, 1)); // Divide em 2 partes iguais
        middlePanel.setBackground(new Color(173, 216, 230));
        middlePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 1), 
                " Salgados Assados", 0, 0, titleFont));

        // Adiciona checkboxes para salgados assados
        JPanel assadosPanel = new JPanel(new GridBagLayout());
        assadosPanel.setBorder(BorderFactory.createTitledBorder("Com Carne"));
        assadosPanel.setBackground(new Color(173, 216, 230));
        GridBagConstraints gbcAssados = new GridBagConstraints();
        gbcAssados.insets = new Insets(5, 5, 5, 5);
        gbcAssados.gridx = 0;
        gbcAssados.gridy = GridBagConstraints.RELATIVE;
        gbcAssados.anchor = GridBagConstraints.WEST;

        Color fundoPainel = new Color(173, 216, 230); // Cor de fundo azul claro
        
     // Adiciona checkboxes para assados
        this.pizzaCheckBox = new JCheckBox("Pizza");
        this.pizzaCheckBox.setFont(checkBoxFont); // Definir fonte maior
        this.pizzaCheckBox.setBackground(fundoPainel); // Cor de fundo igual ao painel
        this.pizzaCheckBox.setOpaque(true); // Torna a cor de fundo visível
        assadosPanel.add(this.pizzaCheckBox, gbcAssados);

        this.esfirraCheckBox = new JCheckBox("Esfirra");
        this.esfirraCheckBox.setFont(checkBoxFont); // Definir fonte maior
        this.esfirraCheckBox.setBackground(fundoPainel); // Cor de fundo igual ao painel
        this.esfirraCheckBox.setOpaque(true); // Torna a cor de fundo visível
        assadosPanel.add(this.esfirraCheckBox, gbcAssados);

        this.hamburgerCheckBox = new JCheckBox("Hamburger");
        this.hamburgerCheckBox.setFont(checkBoxFont); // Definir fonte maior
        this.hamburgerCheckBox.setBackground(fundoPainel); // Cor de fundo igual ao painel
        this.hamburgerCheckBox.setOpaque(true); // Torna a cor de fundo visível
        assadosPanel.add(this.hamburgerCheckBox, gbcAssados);

        this.empadinhaCheckBox = new JCheckBox("Empadinha");
        this.empadinhaCheckBox.setFont(checkBoxFont); // Definir fonte maior
        this.empadinhaCheckBox.setBackground(fundoPainel); // Cor de fundo igual ao painel
        this.empadinhaCheckBox.setOpaque(true); // Torna a cor de fundo visível
        assadosPanel.add(this.empadinhaCheckBox, gbcAssados);
        
        JPanel assadosSemCarnePanel = new JPanel(new GridBagLayout());
        assadosSemCarnePanel.setBorder(BorderFactory.createTitledBorder("Sem Carne"));
        assadosSemCarnePanel.setBackground(new Color(173, 216, 230));
        GridBagConstraints gbcAssadosSemCarne = new GridBagConstraints();
        gbcAssadosSemCarne.insets = new Insets(5, 5, 5, 5);
        gbcAssadosSemCarne.gridx = 0;
        gbcAssadosSemCarne.gridy = GridBagConstraints.RELATIVE;
        gbcAssadosSemCarne.anchor = GridBagConstraints.WEST;

        this.pizzaDeQueijoCheckBox = new JCheckBox("Pizza De Queijo");
        this.pizzaDeQueijoCheckBox.setFont(checkBoxFont); // Definir fonte maior
        this.pizzaDeQueijoCheckBox.setBackground(fundoPainel); // Cor de fundo igual ao painel
        this.pizzaDeQueijoCheckBox.setOpaque(true); // Torna a cor de fundo visível
        assadosSemCarnePanel.add(this.pizzaDeQueijoCheckBox, gbcAssadosSemCarne);

        this.esfirraDeQueijoCheckBox = new JCheckBox("Esfirra de Queijo");
        this.esfirraDeQueijoCheckBox.setFont(checkBoxFont); // Definir fonte maior
        this.esfirraDeQueijoCheckBox.setBackground(fundoPainel); // Cor de fundo igual ao painel
        this.esfirraDeQueijoCheckBox.setOpaque(true); // Torna a cor de fundo visível
        assadosSemCarnePanel.add(this.esfirraDeQueijoCheckBox, gbcAssadosSemCarne);

        this.empadinhaDeQueijoCheckBox = new JCheckBox("Empadinha de Queijo");
        this.empadinhaDeQueijoCheckBox.setFont(checkBoxFont); // Definir fonte maior
        this.empadinhaDeQueijoCheckBox.setBackground(fundoPainel); // Cor de fundo igual ao painel
        this.empadinhaDeQueijoCheckBox.setOpaque(true); // Torna a cor de fundo visível
        assadosSemCarnePanel.add(this.empadinhaDeQueijoCheckBox, gbcAssadosSemCarne);;

        middlePanel.add(assadosPanel);
        middlePanel.add(assadosSemCarnePanel);
        
        // Adiciona o middlePanel ao centralPanel
        centralPanel.add(middlePanel);

        // Painel da Direita (opções do banco de dados)
        JPanel rightPanel = new JPanel(new GridLayout(2, 1)); // Divide em 2 partes
        rightPanel.setBackground(new Color(173, 216, 230));
        rightPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 1), 
                "Salgados Fritos ", 0, 0, titleFont));

        // Adiciona checkboxes para salgados fritos
        JPanel fritosPanel = new JPanel(new GridBagLayout());
        fritosPanel.setBorder(BorderFactory.createTitledBorder("Com Carne"));
        fritosPanel.setBackground(new Color(173, 216, 230));
        GridBagConstraints gbcFritos = new GridBagConstraints();
        gbcFritos.insets = new Insets(5, 5, 5, 5);
        gbcFritos.gridx = 0;
        gbcFritos.gridy = GridBagConstraints.RELATIVE;
        gbcFritos.anchor = GridBagConstraints.WEST;

     // Adiciona checkboxes para fritos
        this.coxinhaCheckBox = new JCheckBox("Coxinha");
        this.coxinhaCheckBox.setFont(checkBoxFont); // Definir fonte maior
        this.coxinhaCheckBox.setBackground(fundoPainel); // Cor de fundo igual ao painel
        this.coxinhaCheckBox.setOpaque(true); // Torna a cor de fundo visível
        fritosPanel.add(this.coxinhaCheckBox, gbcFritos);

        this.kibeCheckBox = new JCheckBox("Kibe");
        this.kibeCheckBox.setFont(checkBoxFont); // Definir fonte maior
        this.kibeCheckBox.setBackground(fundoPainel); // Cor de fundo igual ao painel
        this.kibeCheckBox.setOpaque(true); // Torna a cor de fundo visível
        fritosPanel.add(this.kibeCheckBox, gbcFritos);

        this.pastelCheckBox = new JCheckBox("Pastel");
        this.pastelCheckBox.setFont(checkBoxFont); // Definir fonte maior
        this.pastelCheckBox.setBackground(fundoPainel); // Cor de fundo igual ao painel
        this.pastelCheckBox.setOpaque(true); // Torna a cor de fundo visível
        fritosPanel.add(this.pastelCheckBox, gbcFritos);

        this.risolesCheckBox = new JCheckBox("Risóles");
        this.risolesCheckBox.setFont(checkBoxFont); // Definir fonte maior
        this.risolesCheckBox.setBackground(fundoPainel); // Cor de fundo igual ao painel
        this.risolesCheckBox.setOpaque(true); // Torna a cor de fundo visível
        fritosPanel.add(this.risolesCheckBox, gbcFritos);

        this.cigarreteCheckBox = new JCheckBox("Cigarrete");
        this.cigarreteCheckBox.setFont(checkBoxFont); // Definir fonte maior
        this.cigarreteCheckBox.setBackground(fundoPainel); // Cor de fundo igual ao painel
        this.cigarreteCheckBox.setOpaque(true); // Torna a cor de fundo visível
        fritosPanel.add(this.cigarreteCheckBox, gbcFritos);

        this.croqueteCheckBox = new JCheckBox("Croquete");
        this.croqueteCheckBox.setFont(checkBoxFont); // Definir fonte maior
        this.croqueteCheckBox.setBackground(fundoPainel); // Cor de fundo igual ao painel
        this.croqueteCheckBox.setOpaque(true); // Torna a cor de fundo visível
        fritosPanel.add(this.croqueteCheckBox, gbcFritos);

        JPanel fritosSemCarnePanel = new JPanel(new GridBagLayout());
        fritosSemCarnePanel.setBorder(BorderFactory.createTitledBorder("Sem Carne"));
        fritosSemCarnePanel.setBackground(new Color(173, 216, 230));
        GridBagConstraints gbcFritosSemCarne = new GridBagConstraints();
        gbcFritosSemCarne.insets = new Insets(5, 5, 5, 5);
        gbcFritosSemCarne.gridx = 0;
        gbcFritosSemCarne.gridy = GridBagConstraints.RELATIVE;
        gbcFritosSemCarne.anchor = GridBagConstraints.WEST;

     // Adiciona checkboxes para fritos sem carne
        this.pastelDeQueijoCheckBox = new JCheckBox("Pastel de Queijo");
        this.pastelDeQueijoCheckBox.setFont(checkBoxFont); // Definir fonte maior
        this.pastelDeQueijoCheckBox.setBackground(fundoPainel); // Cor de fundo igual ao painel
        this.pastelDeQueijoCheckBox.setOpaque(true); // Torna a cor de fundo visível
        fritosSemCarnePanel.add(this.pastelDeQueijoCheckBox, gbcFritosSemCarne);

        this.bolinhaDeQueijoCheckBox = new JCheckBox("Bolinha de Queijo");
        this.bolinhaDeQueijoCheckBox.setFont(checkBoxFont); // Definir fonte maior
        this.bolinhaDeQueijoCheckBox.setBackground(fundoPainel); // Cor de fundo igual ao painel
        this.bolinhaDeQueijoCheckBox.setOpaque(true); // Torna a cor de fundo visível
        fritosSemCarnePanel.add(this.bolinhaDeQueijoCheckBox, gbcFritosSemCarne);

        this.cigarreteDeQueijoCheckBox = new JCheckBox("Cigarrete de Queijo");
        this.cigarreteDeQueijoCheckBox.setFont(checkBoxFont); // Definir fonte maior
        this.cigarreteDeQueijoCheckBox.setBackground(fundoPainel); // Cor de fundo igual ao painel
        this.cigarreteDeQueijoCheckBox.setOpaque(true); // Torna a cor de fundo visível
        fritosSemCarnePanel.add(this.cigarreteDeQueijoCheckBox, gbcFritosSemCarne);

        rightPanel.add(fritosPanel);
        rightPanel.add(fritosSemCarnePanel);
        
     // Adiciona o rightPanel ao centralPanel
        centralPanel.add(rightPanel);
        
     // Painel para os botões "Simular" e "Cancelar"
        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Posiciona os botões no canto direito
        botoesPanel.setBackground(new Color(173, 216, 230));
        JButton voltarButton = new JButton("Voltar");
        JButton cancelarButton = new JButton("Cancelar");
        JButton avancarButton = new JButton("Avançar");
        
        // Botão Avancar: vai para tela de bebidas
        avancarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	// Cria a instância da SimulacaoChurrasco e armazena os dados
    	        SimulacaoSalgado simulacao = new SimulacaoSalgado();
    	        // Captura os dados dos JTextFields
    	        try {
    	            simulacao.setHomens(Integer.parseInt(homensTextField.getText()));
    	            simulacao.setMulheres(Integer.parseInt(mulheresTextField.getText()));
    	            simulacao.setComiloes(Integer.parseInt(comiloesTextField.getText()));
    	            simulacao.setCriancas(Integer.parseInt(criancasTextField.getText()));
    	            //simulacao.setVegetarianos(Integer.parseInt(vegetarianosTextField.getText()));
    	        } catch (NumberFormatException ex) {
    	            System.out.println("Erro ao converter número: " + ex.getMessage());
    	        }

    	        // Adicionar carnes selecionadas
    	        List<String> salgadosAssados = new ArrayList<>();
    	        if (pizzaCheckBox.isSelected()) salgadosAssados.add("Pizza");
    	        if (esfirraCheckBox.isSelected()) salgadosAssados.add("Esfirra");
    	        if (hamburgerCheckBox.isSelected()) salgadosAssados.add("Hambúrguer");
    	        if (empadinhaCheckBox.isSelected()) salgadosAssados.add("Empadinha");
    	        if (pizzaDeQueijoCheckBox.isSelected()) salgadosAssados.add("Pizza de Queijo");
    	        if (esfirraDeQueijoCheckBox.isSelected()) salgadosAssados.add("Esfirra de Queijo");
    	        if (empadinhaDeQueijoCheckBox.isSelected()) salgadosAssados.add("Empadinha de Queijo");
    	        
    	        simulacao.setSalgadosAssados(salgadosAssados);

    	        List<String> salgadosFritos = new ArrayList<>();
    	        if (coxinhaCheckBox.isSelected()) salgadosFritos.add("Coxinha");
    	        if (kibeCheckBox.isSelected()) salgadosFritos.add("Kibe");
    	        if (pastelCheckBox.isSelected()) salgadosFritos.add("Pastel");
    	        if (risolesCheckBox.isSelected()) salgadosFritos.add("Risóles");
    	        if (cigarreteCheckBox.isSelected()) salgadosFritos.add("Cigarrete");
    	        if (croqueteCheckBox.isSelected()) salgadosFritos.add("Croquete");
    	        if (pastelDeQueijoCheckBox.isSelected()) salgadosFritos.add("Pastel de Queijo");
    	        if (bolinhaDeQueijoCheckBox.isSelected()) salgadosFritos.add("Bolinha de Queijo");
    	        if (cigarreteDeQueijoCheckBox.isSelected()) salgadosFritos.add("Cigarrete de Queijo");
    	        
    	        
    	        simulacao.setSalgadosFritos(salgadosFritos);
    	     
    	        List<String> agregados = new ArrayList<>();
    	        if (arrozCheckbox.isSelected()) agregados.add("Arroz");
    	        if (farofaCheckBox.isSelected()) agregados.add("Farofa");
    	        if (vinagreteCheckBox.isSelected()) agregados.add("Vinagrete");
    	        if (paodeAlhoCheckBox.isSelected()) agregados.add("Pão de Alho");
    	        if (paoFrancesCheckBox.isSelected()) agregados.add("Pão Francês");
    	        if (queijoCoalhoCheckBox.isSelected()) agregados.add("Queijo Coalho");
    	        
    	        simulacao.setAgregados(agregados);
    	        
    	        // Exibir os valores capturados no console
    	        System.out.println("Homens: " + simulacao.getHomens());
    	        System.out.println("Mulheres: " + simulacao.getMulheres());
    	        System.out.println("Comilões: " + simulacao.getComiloes());
    	        System.out.println("Crianças: " + simulacao.getCriancas());
    	      //  System.out.println("Vegetarianos: " + simulacao.getVegetarianos());

    	        System.out.println("Salgados Selecionadas:");
    	        for (String salgado : salgadosAssados) {
    	            System.out.println(salgado);
    	        }
    	        for (String salgadofrito : salgadosFritos) {
    	            System.out.println(salgadofrito);
    	        }
    	        for (String agregs : agregados) {
    	            System.out.println(agregs);
    	        }
    	        

            	
            	// Aqui você pode passar o objeto "simulacao" para a próxima tela ou armazená-lo em uma estrutura de dados apropriada
    	        mainWindow.showPanel(new SimulacaoBebidas(mainWindow, simulacao));

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
        
        // Botão voltar: retorna para a tela de simulação
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.showPanel(new NovaSimulacao());
            }
        });

        botoesPanel.add(voltarButton);
        botoesPanel.add(cancelarButton);
        botoesPanel.add(avancarButton);

        // Adiciona o painel de botões na parte inferior do painel principal
        simulacaoPanel.add(botoesPanel, BorderLayout.SOUTH);

        // Adiciona o painel "simulação" ao painel principal
        add(simulacaoPanel, BorderLayout.CENTER);
        
    }

    public JPanel getPanel() {
        return this;
    }
}
