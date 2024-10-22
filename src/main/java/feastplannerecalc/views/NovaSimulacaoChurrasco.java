package feastplannerecalc.views;

import javax.swing.*;

import feastplannerecalc.config.MainWindowConfig;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NovaSimulacaoChurrasco extends JPanel {
	
    private static final long serialVersionUID = 1L;
    private MainWindow mainWindow; // Referência à MainWindow

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
        pessoasPanel.add(new JTextField(10), gbc);
        gbc.gridx = 1;
        pessoasPanel.add(new JTextField(10), gbc);

        // Segunda Linha: Comilões / Crianças
        gbc.gridy = 2;
        gbc.gridx = 0;
        pessoasPanel.add(new JLabel("Comilões:"), gbc);
        gbc.gridx = 1;
        pessoasPanel.add(new JLabel("Crianças:"), gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        pessoasPanel.add(new JTextField(10), gbc);
        gbc.gridx = 1;
        pessoasPanel.add(new JTextField(10), gbc);

        // Terceira Linha: Pessoas que Não Comem Carne
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10); // Adiciona espaço extra
        JLabel vegetarianosLabel = new JLabel("<html>Quantidade de Pessoas<br>que Não Comem Carne:</html>");
        pessoasPanel.add(vegetarianosLabel, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pessoasPanel.add(new JTextField(3), gbc);

        // Parte inferior: Agregados
        JPanel agregadosPanel = new JPanel(new GridBagLayout());
        agregadosPanel.setBorder(BorderFactory.createTitledBorder("Agregados"));
        GridBagConstraints gbcAgregados = new GridBagConstraints();
        gbcAgregados.insets = new Insets(5, 5, 5, 5);
        gbcAgregados.gridx = 0;
        gbcAgregados.gridy = GridBagConstraints.RELATIVE;
        gbcAgregados.anchor = GridBagConstraints.WEST;

        // Adiciona checkboxes para agregados
        agregadosPanel.add(new JCheckBox("Arroz"), gbcAgregados);
        agregadosPanel.add(new JCheckBox("Farofa"), gbcAgregados);
        agregadosPanel.add(new JCheckBox("Vinagrete"), gbcAgregados);
        agregadosPanel.add(new JCheckBox("Coração de Frango"), gbcAgregados);
        agregadosPanel.add(new JCheckBox("Pão de Alho"), gbcAgregados);
        agregadosPanel.add(new JCheckBox("Pão Francês"), gbcAgregados);
        agregadosPanel.add(new JCheckBox("Queijo Coalho"), gbcAgregados);

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

        bovinosPanel.add(new JCheckBox("Costela Bovina"), gbcBovinos);
        bovinosPanel.add(new JCheckBox("Prime Rib"), gbcBovinos);
        bovinosPanel.add(new JCheckBox("Chuleta Paulista"), gbcBovinos);

        JPanel suinosPanel = new JPanel(new GridBagLayout());
        suinosPanel.setBorder(BorderFactory.createTitledBorder("Suínos"));
        GridBagConstraints gbcSuinos = new GridBagConstraints();
        gbcSuinos.insets = new Insets(5, 5, 5, 5);
        gbcSuinos.gridx = 0;
        gbcSuinos.gridy = GridBagConstraints.RELATIVE;
        gbcSuinos.anchor = GridBagConstraints.WEST;

        suinosPanel.add(new JCheckBox("Costela Suína"), gbcSuinos);
        suinosPanel.add(new JCheckBox("Bisteca"), gbcSuinos);
        suinosPanel.add(new JCheckBox("Pernil com Osso"), gbcSuinos);

        JPanel frangoPanel = new JPanel(new GridBagLayout());
        frangoPanel.setBorder(BorderFactory.createTitledBorder("Frango"));
        GridBagConstraints gbcFrango = new GridBagConstraints();
        gbcFrango.insets = new Insets(5, 5, 5, 5);
        gbcFrango.gridx = 0;
        gbcFrango.gridy = GridBagConstraints.RELATIVE;
        gbcFrango.anchor = GridBagConstraints.WEST;

        frangoPanel.add(new JCheckBox("Asinha com Coxinha"), gbcFrango);
        frangoPanel.add(new JCheckBox("Tulipa da Asa"), gbcFrango);
        frangoPanel.add(new JCheckBox("Coxa com Sobrecoxa"), gbcFrango);

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

        bovinosSemOssoPanel.add(new JCheckBox("Picanha"), gbcBovinosSemOsso);
        bovinosSemOssoPanel.add(new JCheckBox("Coxão Mole"), gbcBovinosSemOsso);
        bovinosSemOssoPanel.add(new JCheckBox("Alcatra"), gbcBovinosSemOsso);
        bovinosSemOssoPanel.add(new JCheckBox("Fraldinha"), gbcBovinosSemOsso);
        bovinosSemOssoPanel.add(new JCheckBox("Contra Filé"), gbcBovinosSemOsso);
        
        JPanel suinosSemOssoPanel = new JPanel(new GridBagLayout());
        suinosSemOssoPanel.setBorder(BorderFactory.createTitledBorder("Suínos Sem Osso"));
        GridBagConstraints gbcSuinosSemOsso = new GridBagConstraints();
        gbcSuinosSemOsso.insets = new Insets(5, 5, 5, 5);
        gbcSuinosSemOsso.gridx = 0;
        gbcSuinosSemOsso.gridy = GridBagConstraints.RELATIVE;
        gbcSuinosSemOsso.anchor = GridBagConstraints.WEST;

        suinosSemOssoPanel.add(new JCheckBox("Filé Mignon"), gbcSuinosSemOsso);
        suinosSemOssoPanel.add(new JCheckBox("Picanha Suína"), gbcSuinosSemOsso);
        suinosSemOssoPanel.add(new JCheckBox("Lombo Suíno"), gbcSuinosSemOsso);
        suinosSemOssoPanel.add(new JCheckBox("Paleta"), gbcSuinosSemOsso);

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
                mainWindow.showPanel(new SimulacaoBebidasChurrasco(mainWindow));
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
