package feastplannerecalc.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import feastplannerecalc.config.MainWindowConfig;
import feastplannerecalc.models.SimulacaoChurrasco;

public class NovaSimulacaoChurrasco extends JPanel {
	
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
    private JCheckBox coracaodeFrangoCheckBox;
    private JCheckBox paodeAlhoCheckBox;
    private JCheckBox paoFrancesCheckBox;
    private JCheckBox queijoCoalhoCheckBox;

    
    // JCheckBoxes para capturar as opções de carnes
    private JCheckBox costelaBovinaCheckBox;
    private JCheckBox primeRibCheckBox;
    private JCheckBox chuletaPaulistaCheckBox;
    private JCheckBox costelaSuinaCheckBox;
    private JCheckBox bistecaCheckBox;
    private JCheckBox pernilcomOssoCheckBox;
    private JCheckBox asinhacomCoxinhaCheckBox;
    private JCheckBox tulipadaAsaCheckBox;
    private JCheckBox coxacomSobrecoxaCheckBox;
    private JCheckBox picanhaCheckBox;
    private JCheckBox coxaoMoleCheckBox;
    private JCheckBox alcatraCheckBox;
    private JCheckBox fraldinhaCheckBox;
    private JCheckBox contraFileCheckBox;
    private JCheckBox fileMignonCheckBox;
    private JCheckBox picanhaSuinaCheckBox;
    private JCheckBox lomboSuinoCheckBox;
    private JCheckBox linguicaCheckBox;
    private JCheckBox paletaCheckBox;
   

    public NovaSimulacaoChurrasco(MainWindow mainWindow) {
        this.mainWindow = mainWindow; // Armazena a referência
        
    	 // Painel "mãe" que vai conter todos os componentes
        setLayout(new BorderLayout()); // Usaremos BorderLayout para estruturar os componentes principais
        JPanel simulacaoPanel = new JPanel(new BorderLayout());
        simulacaoPanel.setBorder(BorderFactory.createTitledBorder("Simulação de Churrasco"));

        // Painel central que vai conter os 3 painéis (leftPanel, middlePanel, rightPanel)
        JPanel centralPanel = new JPanel(new GridLayout(1, 3)); // Divide em 3 colunas
        simulacaoPanel.add(centralPanel, BorderLayout.CENTER);

        // Painel da Esquerda (com 2 seções: dados de pessoas e agregados)
        JPanel leftPanel = new JPanel(new GridLayout(2, 1)); // Divide em 2 partes iguais
        leftPanel.setBorder(BorderFactory.createTitledBorder("Dados de Pessoas"));

        // Parte superior: Dados das Pessoas
        JPanel pessoasPanel = new JPanel(new GridBagLayout());
        pessoasPanel.setBorder(BorderFactory.createTitledBorder("Pessoas"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Ajusta a largura dos componentes
        gbc.weightx = 0.5; // Define o peso para distribuir o espaço horizontalmente

     // Primeira Linha: Homens / Mulheres
        pessoasPanel.add(new JLabel("Homens:"), gbc);
        gbc.gridx = 1;
        pessoasPanel.add(new JLabel("Mulheres:"), gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        // Inicializando homensTextField
        this.homensTextField = new JTextField(10);
        pessoasPanel.add(this.homensTextField, gbc);

        gbc.gridx = 1;
        // Inicializando mulheresTextField
        this.mulheresTextField = new JTextField(10);
        pessoasPanel.add(this.mulheresTextField, gbc);

     // Segunda Linha: Comilões / Crianças
        gbc.gridy = 2; // Linha 2
        gbc.gridx = 0;
        pessoasPanel.add(new JLabel("Comilões:"), gbc);
        gbc.gridx = 1;
        pessoasPanel.add(new JLabel("Crianças:"), gbc);

        gbc.gridy = 3; // Linha 3
        gbc.gridx = 0;
        // Inicializando comilõesTextField
        this.comiloesTextField = new JTextField(10);
        pessoasPanel.add(this.comiloesTextField, gbc);

        gbc.gridx = 1;
        // Inicializando criancasTextField
        this.criancasTextField = new JTextField(10);
        pessoasPanel.add(this.criancasTextField, gbc);


     // Terceira Linha: Pessoas que Não Comem Carne
//        gbc.gridy = 4; // Linha 4
  //      gbc.gridx = 0;
    //    gbc.gridwidth = 2;
      //  gbc.insets = new Insets(10, 10, 10, 10); // Adiciona espaço extra
        //JLabel vegetarianosLabel = new JLabel("<html>Quantidade de Pessoas<br>que Não Comem Carne:</html>");
//        pessoasPanel.add(vegetarianosLabel, gbc);

//        gbc.gridy = 5; // Linha 5
  //      gbc.gridx = 0;
    //    gbc.gridwidth = 2;
      //  gbc.fill = GridBagConstraints.HORIZONTAL;
        // Inicializando vegetarianosTextField
        //this.vegetarianosTextField = new JTextField(3);
//        pessoasPanel.add(this.vegetarianosTextField, gbc);


        // Parte inferior: Agregados
        JPanel agregadosPanel = new JPanel(new GridBagLayout());
        agregadosPanel.setBorder(BorderFactory.createTitledBorder("Agregados"));
        GridBagConstraints gbcAgregados = new GridBagConstraints();
        gbcAgregados.insets = new Insets(5, 5, 5, 5);
        gbcAgregados.gridx = 0;
        gbcAgregados.gridy = GridBagConstraints.RELATIVE;
        gbcAgregados.anchor = GridBagConstraints.WEST;

     // Adiciona checkboxes para agregados
        this.arrozCheckbox = new JCheckBox("Arroz");
        agregadosPanel.add(this.arrozCheckbox, gbcAgregados);

        this.farofaCheckBox = new JCheckBox("Farofa");
        agregadosPanel.add(this.farofaCheckBox, gbcAgregados);

        this.vinagreteCheckBox = new JCheckBox("Vinagrete");
        agregadosPanel.add(this.vinagreteCheckBox, gbcAgregados);

        this.coracaodeFrangoCheckBox = new JCheckBox("Coração de Frango");
        agregadosPanel.add(this.coracaodeFrangoCheckBox, gbcAgregados);

        this.paodeAlhoCheckBox = new JCheckBox("Pão de Alho");
        agregadosPanel.add(this.paodeAlhoCheckBox, gbcAgregados);

        this.paoFrancesCheckBox = new JCheckBox("Pão Francês");
        agregadosPanel.add(this.paoFrancesCheckBox, gbcAgregados);

        this.queijoCoalhoCheckBox = new JCheckBox("Queijo Coalho");
        agregadosPanel.add(this.queijoCoalhoCheckBox, gbcAgregados);


        // Adiciona as duas seções ao painel esquerdo
        leftPanel.add(pessoasPanel);  // Parte superior
        leftPanel.add(agregadosPanel); // Parte inferior
        
        // Adiciona o leftPanel ao centralPanel
        centralPanel.add(leftPanel);

        // Painel do Meio (opções do banco de dados)
        JPanel middlePanel = new JPanel(new GridLayout(3, 1)); // Divide em 3 partes iguais
        middlePanel.setBorder(BorderFactory.createTitledBorder("Churrasco com Osso"));

        // Adiciona checkboxes para churrasco com osso
        JPanel bovinosPanel = new JPanel(new GridBagLayout());
        bovinosPanel.setBorder(BorderFactory.createTitledBorder("Bovinos"));
        GridBagConstraints gbcBovinos = new GridBagConstraints();
        gbcBovinos.insets = new Insets(5, 5, 5, 5);
        gbcBovinos.gridx = 0;
        gbcBovinos.gridy = GridBagConstraints.RELATIVE;
        gbcBovinos.anchor = GridBagConstraints.WEST;

        this.costelaBovinaCheckBox = new JCheckBox("Costela Bovina");
        bovinosPanel.add(this.costelaBovinaCheckBox, gbcBovinos );
        this.primeRibCheckBox = new JCheckBox("Prime Rib");
        bovinosPanel.add(this.primeRibCheckBox, gbcBovinos);
        this.chuletaPaulistaCheckBox = new JCheckBox("Chuleta Paulista");
        bovinosPanel.add( this.chuletaPaulistaCheckBox, gbcBovinos);

        JPanel suinosPanel = new JPanel(new GridBagLayout());
        suinosPanel.setBorder(BorderFactory.createTitledBorder("Suínos"));
        GridBagConstraints gbcSuinos = new GridBagConstraints();
        gbcSuinos.insets = new Insets(5, 5, 5, 5);
        gbcSuinos.gridx = 0;
        gbcSuinos.gridy = GridBagConstraints.RELATIVE;
        gbcSuinos.anchor = GridBagConstraints.WEST;

        this.costelaSuinaCheckBox = new JCheckBox("Costela Suína");
        suinosPanel.add(this.costelaSuinaCheckBox, gbcSuinos);
        this.bistecaCheckBox = new JCheckBox("Bisteca Suína");
        suinosPanel.add(this.bistecaCheckBox, gbcSuinos);
        this.pernilcomOssoCheckBox = new JCheckBox("Pernil com Osso");
        suinosPanel.add(this.pernilcomOssoCheckBox, gbcSuinos);

        JPanel frangoPanel = new JPanel(new GridBagLayout());
        frangoPanel.setBorder(BorderFactory.createTitledBorder("Frango"));
        GridBagConstraints gbcFrango = new GridBagConstraints();
        gbcFrango.insets = new Insets(5, 5, 5, 5);
        gbcFrango.gridx = 0;
        gbcFrango.gridy = GridBagConstraints.RELATIVE;
        gbcFrango.anchor = GridBagConstraints.WEST;
        
        this.asinhacomCoxinhaCheckBox = new JCheckBox("Asinha com Coxinha");
        frangoPanel.add(this.asinhacomCoxinhaCheckBox, gbcFrango);
        this.tulipadaAsaCheckBox = new JCheckBox("Tulipa da Asa");
        frangoPanel.add(this.tulipadaAsaCheckBox, gbcFrango);
        this.coxacomSobrecoxaCheckBox = new JCheckBox("Coxa com Sobrecoxa");
        frangoPanel.add( this.coxacomSobrecoxaCheckBox, gbcFrango); 

        middlePanel.add(bovinosPanel);
        middlePanel.add(suinosPanel);
        middlePanel.add(frangoPanel);
        
        // Adiciona o middlePanel ao centralPanel
        centralPanel.add(middlePanel);

        // Painel da Direita (opções do banco de dados)
        JPanel rightPanel = new JPanel(new GridLayout(2, 1)); // Divide em 2 partes
        rightPanel.setBorder(BorderFactory.createTitledBorder("Churrasco sem Osso"));

        // Adiciona checkboxes para churrasco sem osso
        JPanel bovinosSemOssoPanel = new JPanel(new GridBagLayout());
        bovinosSemOssoPanel.setBorder(BorderFactory.createTitledBorder("Bovinos Sem Osso"));
        GridBagConstraints gbcBovinosSemOsso = new GridBagConstraints();
        gbcBovinosSemOsso.insets = new Insets(5, 5, 5, 5);
        gbcBovinosSemOsso.gridx = 0;
        gbcBovinosSemOsso.gridy = GridBagConstraints.RELATIVE;
        gbcBovinosSemOsso.anchor = GridBagConstraints.WEST;

        this.picanhaCheckBox = new JCheckBox("Picanha");
        bovinosSemOssoPanel.add(this.picanhaCheckBox, gbcBovinosSemOsso);
        this.coxaoMoleCheckBox = new JCheckBox("Coxão Mole");
        bovinosSemOssoPanel.add(this.coxaoMoleCheckBox, gbcBovinosSemOsso);
        this.alcatraCheckBox = new JCheckBox("Alcatra");
        bovinosSemOssoPanel.add(this.alcatraCheckBox, gbcBovinosSemOsso);
        this.fraldinhaCheckBox = new JCheckBox("Fraldinha");
        bovinosSemOssoPanel.add(this.fraldinhaCheckBox, gbcBovinosSemOsso);
        this.contraFileCheckBox = new JCheckBox("Contra Filé");
        bovinosSemOssoPanel.add(this.contraFileCheckBox, gbcBovinosSemOsso);
        
        JPanel suinosSemOssoPanel = new JPanel(new GridBagLayout());
        suinosSemOssoPanel.setBorder(BorderFactory.createTitledBorder("Suínos Sem Osso"));
        GridBagConstraints gbcSuinosSemOsso = new GridBagConstraints();
        gbcSuinosSemOsso.insets = new Insets(5, 5, 5, 5);
        gbcSuinosSemOsso.gridx = 0;
        gbcSuinosSemOsso.gridy = GridBagConstraints.RELATIVE;
        gbcSuinosSemOsso.anchor = GridBagConstraints.WEST;

        this.fileMignonCheckBox = new JCheckBox("Filé Mignon");
        suinosSemOssoPanel.add( this.fileMignonCheckBox, gbcSuinosSemOsso);
        this.picanhaSuinaCheckBox = new JCheckBox("Picanha Suína");
        suinosSemOssoPanel.add(this.picanhaSuinaCheckBox, gbcSuinosSemOsso);
        this.lomboSuinoCheckBox = new JCheckBox("Lombo Suíno");
        suinosSemOssoPanel.add(this.lomboSuinoCheckBox, gbcSuinosSemOsso);
        this.linguicaCheckBox = new JCheckBox("Linguiça");
        suinosSemOssoPanel.add(this.linguicaCheckBox, gbcSuinosSemOsso);
        this.paletaCheckBox = new JCheckBox("Paleta");
        suinosSemOssoPanel.add(this.paletaCheckBox, gbcSuinosSemOsso);

        rightPanel.add(bovinosSemOssoPanel);
        rightPanel.add(suinosSemOssoPanel);
        
        // Adiciona o rightPanel ao centralPanel
        centralPanel.add(rightPanel);

        // Painel para os botões "Simular" e "Cancelar"
        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Posiciona os botões no canto direito
        JButton voltarButton = new JButton("Voltar");
        JButton cancelarButton = new JButton("Cancelar");
        JButton avancarButton = new JButton("Avançar");
        
        // Botão Avancar: vai para tela de bebidas
        avancarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
            	        // Cria a instância da SimulacaoChurrasco e armazena os dados
            	        SimulacaoChurrasco simulacao = new SimulacaoChurrasco();
            	        // Captura os dados dos JTextFields
            	        try {
            	            simulacao.setHomens(Integer.parseInt(homensTextField.getText()));
            	            simulacao.setMulheres(Integer.parseInt(mulheresTextField.getText()));
            	            simulacao.setComiloes(Integer.parseInt(comiloesTextField.getText()));
            	            simulacao.setCriancas(Integer.parseInt(criancasTextField.getText()));
            	           // simulacao.setVegetarianos(Integer.parseInt(vegetarianosTextField.getText()));
            	        } catch (NumberFormatException ex) {
            	            System.out.println("Erro ao converter número: " + ex.getMessage());
            	        }

            	        // Adicionar carnes selecionadas
            	        List<String> bovinoComOsso = new ArrayList<>();
            	        if (costelaBovinaCheckBox.isSelected()) bovinoComOsso.add("Costela Bovina");
            	        if (primeRibCheckBox.isSelected()) bovinoComOsso.add("Prime Rib");
            	        if (chuletaPaulistaCheckBox.isSelected()) bovinoComOsso.add("Chuleta Paulista");
            	        simulacao.setBovinoComOsso(bovinoComOsso);
            	        
            	        List<String> suinoComOsso = new ArrayList<>();
            	        if (costelaSuinaCheckBox.isSelected()) suinoComOsso.add("Costela Suína");
            	        if (bistecaCheckBox.isSelected()) suinoComOsso.add("Bisteca Suína");
            	        if (pernilcomOssoCheckBox.isSelected()) suinoComOsso.add("Pernil com Osso");
            	        simulacao.setSuinoComOsso(suinoComOsso);
            	        
            	        List<String> frangoComOsso = new ArrayList<>();
            	        if (asinhacomCoxinhaCheckBox.isSelected()) frangoComOsso.add("Asinha com Coxinha");
            	        if (tulipadaAsaCheckBox.isSelected()) frangoComOsso.add("Tulipa da Asa");
            	        if (coxacomSobrecoxaCheckBox.isSelected()) frangoComOsso.add("Coxa com Sobrecoxa");
            	        simulacao.setFrangoComOsso(frangoComOsso);

            	        List<String> bovinoSemOsso = new ArrayList<>();
            	        if (picanhaCheckBox.isSelected()) bovinoSemOsso.add("Picanha");
            	        if (coxaoMoleCheckBox.isSelected()) bovinoSemOsso.add("Coxão Mole");
            	        if (alcatraCheckBox.isSelected()) bovinoSemOsso.add("Alcatra");
            	        if (fraldinhaCheckBox.isSelected()) bovinoSemOsso.add("Fraldinha");
            	        if (contraFileCheckBox.isSelected()) bovinoSemOsso.add("Contra Filé");
            	        simulacao.setBovinoSemOsso(bovinoSemOsso);
            	        
            	        List<String> suinoSemOsso = new ArrayList<>();
            	        if (fileMignonCheckBox.isSelected()) suinoSemOsso.add("Filé Mignon");
            	        if (picanhaSuinaCheckBox.isSelected()) suinoSemOsso.add("Picanha Suína");
            	        if (lomboSuinoCheckBox.isSelected()) suinoSemOsso.add("Lombo Suíno");
            	        if (linguicaCheckBox.isSelected()) suinoSemOsso.add("Linguiça");
            	        if (paletaCheckBox.isSelected()) suinoSemOsso.add("Paleta");
            	        simulacao.setSuinoSemOsso(suinoSemOsso);
            	     
            	        List<String> agregados = new ArrayList<>();
            	        if (arrozCheckbox.isSelected()) agregados.add("Arroz");
            	        if (farofaCheckBox.isSelected()) agregados.add("Farofa");
            	        if (vinagreteCheckBox.isSelected()) agregados.add("Vinagrete");
            	        if (coracaodeFrangoCheckBox.isSelected()) agregados.add("Coração de Frango");
            	        if (paodeAlhoCheckBox.isSelected()) agregados.add("Pão de Alho");
            	        if (paoFrancesCheckBox.isSelected()) agregados.add("Pão Francês");
            	        if (queijoCoalhoCheckBox.isSelected()) agregados.add("Queijo Coalho");
            	        
            	        simulacao.setAgregados(agregados);
            	        
            	        // Exibir os valores capturados no console
            	        System.out.println("Homens: " + simulacao.getHomens());
            	        System.out.println("Mulheres: " + simulacao.getMulheres());
            	        System.out.println("Comilões: " + simulacao.getComiloes());
            	        System.out.println("Crianças: " + simulacao.getCriancas());
            	     //   System.out.println("Vegetarianos: " + simulacao.getVegetarianos());

            	        System.out.println("Carnes Selecionadas:");
            	        for (String bovinoOsso : bovinoComOsso) {
            	            System.out.println(bovinoOsso);
            	        }
            	        for (String suinoOsso : suinoComOsso) {
            	            System.out.println(suinoOsso);
            	        }
            	        for (String frango : frangoComOsso) {
            	            System.out.println(frango);
            	        }
            	        for (String bovinoSem : bovinoSemOsso) {
            	            System.out.println(bovinoSem);	
            	        }
            	        for (String suinoSem : suinoSemOsso) {
            	            System.out.println(suinoSem);
            	        }
            	        for (String agregs : agregados) {
            	            System.out.println(agregs);
            	        }
            	        
            	// Aqui você pode passar o objeto "simulacao" para a próxima tela ou armazená-lo em uma estrutura de dados apropriada
                mainWindow.showPanel(new SimulacaoBebidasChurrasco(mainWindow, simulacao));
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
