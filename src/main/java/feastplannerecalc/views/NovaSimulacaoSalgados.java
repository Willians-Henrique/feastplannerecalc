package feastplannerecalc.views;

import javax.swing.*;

import feastplannerecalc.config.MainWindowConfig;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NovaSimulacaoSalgados extends JPanel {
	
    private static final long serialVersionUID = 1L;
    private MainWindow mainWindow; // Referência à MainWindow

    public NovaSimulacaoSalgados(MainWindow mainWindow) {
        this.mainWindow = mainWindow; // Armazena a referência
        
    	 // Painel "mãe" que vai conter todos os componentes
        setLayout(new BorderLayout()); // Usaremos BorderLayout para estruturar os componentes principais
        JPanel simulacaoPanel = new JPanel(new BorderLayout());
        simulacaoPanel.setBorder(BorderFactory.createTitledBorder("Simulação de Salgados"));

        // Painel central que vai conter os 3 painéis (leftPanel, middlePanel, rightPanel)
        JPanel centralPanel = new JPanel(new GridLayout(1, 3)); // Divide em 3 colunas
        simulacaoPanel.add(centralPanel, BorderLayout.CENTER);

        // Painel da Esquerda (com 2 seções: dados de pessoas e agregados)
        JPanel leftPanel = new JPanel(new GridLayout(2, 1)); // Divide em 2 partes iguais
        leftPanel.setBorder(BorderFactory.createTitledBorder("Dados de Pessoas"));

        // Parte superior: Dados das Pessoas
        JPanel pessoasPanel = new JPanel(new GridBagLayout());
        pessoasPanel.setBorder(BorderFactory.createTitledBorder("Pessoas"));
        GridBagConstraints gbcPessoas = new GridBagConstraints();
        gbcPessoas.insets = new Insets(10, 10, 10, 10);
        gbcPessoas.gridx = 0;
        gbcPessoas.gridy = 0;
        gbcPessoas.fill = GridBagConstraints.HORIZONTAL; // Ajusta a largura dos componentes
        gbcPessoas.weightx = 0.5; // Define o peso para distribuir o espaço horizontalmente

        // Primeira Linha: Homens / Mulheres
        pessoasPanel.add(new JLabel("Homens:"), gbcPessoas);
        gbcPessoas.gridx = 1;
        pessoasPanel.add(new JLabel("Mulheres:"), gbcPessoas);

        gbcPessoas.gridy = 1;
        gbcPessoas.gridx = 0;
        pessoasPanel.add(new JTextField(10), gbcPessoas);
        gbcPessoas.gridx = 1;
        pessoasPanel.add(new JTextField(10), gbcPessoas);

        // Segunda Linha: Comilões / Crianças
        gbcPessoas.gridy = 2;
        gbcPessoas.gridx = 0;
        pessoasPanel.add(new JLabel("Comilões:"), gbcPessoas);
        gbcPessoas.gridx = 1;
        pessoasPanel.add(new JLabel("Crianças:"), gbcPessoas);

        gbcPessoas.gridy = 3;
        gbcPessoas.gridx = 0;
        pessoasPanel.add(new JTextField(10), gbcPessoas);
        gbcPessoas.gridx = 1;
        pessoasPanel.add(new JTextField(10), gbcPessoas);

        // Terceira Linha: Pessoas que Não Comem Carne
        gbcPessoas.gridy = 4;
        gbcPessoas.gridx = 0;
        gbcPessoas.gridwidth = 2;
        gbcPessoas.insets = new Insets(10, 10, 10, 10); // Adiciona espaço extra
        JLabel vegetarianosLabel = new JLabel("<html>Quantidade de Pessoas<br>que Não Comem Carne:</html>");
        pessoasPanel.add(vegetarianosLabel, gbcPessoas);

        gbcPessoas.gridy = 5;
        gbcPessoas.gridx = 0;
        gbcPessoas.gridwidth = 2;
        gbcPessoas.fill = GridBagConstraints.HORIZONTAL;
        pessoasPanel.add(new JTextField(3), gbcPessoas);

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
        agregadosPanel.add(new JCheckBox("Coração de frango"), gbcAgregados);
        agregadosPanel.add(new JCheckBox("Pão de alho"), gbcAgregados);
        agregadosPanel.add(new JCheckBox("Pão francês"), gbcAgregados);
        agregadosPanel.add(new JCheckBox("Queijo Coalho"), gbcAgregados);

        // Adiciona as duas seções ao painel esquerdo
        leftPanel.add(pessoasPanel);  // Parte superior
        leftPanel.add(agregadosPanel); // Parte inferior
        
     // Adiciona o leftPanel ao centralPanel
        centralPanel.add(leftPanel);

        // Painel do Meio (opções do banco de dados)
        JPanel middlePanel = new JPanel(new GridLayout(2, 1)); // Divide em 2 partes iguais
        middlePanel.setBorder(BorderFactory.createTitledBorder("Salgados Assados"));

        // Adiciona checkboxes para salgados assados
        JPanel assadosPanel = new JPanel(new GridBagLayout());
        assadosPanel.setBorder(BorderFactory.createTitledBorder("Com Carne"));
        GridBagConstraints gbcAssados = new GridBagConstraints();
        gbcAssados.insets = new Insets(5, 5, 5, 5);
        gbcAssados.gridx = 0;
        gbcAssados.gridy = GridBagConstraints.RELATIVE;
        gbcAssados.anchor = GridBagConstraints.WEST;

        assadosPanel.add(new JCheckBox("Pizza"), gbcAssados);
        assadosPanel.add(new JCheckBox("Esfirra"), gbcAssados);
        assadosPanel.add(new JCheckBox("Hamburger"), gbcAssados);
        assadosPanel.add(new JCheckBox("Empadinha"), gbcAssados);

        JPanel assadosSemCarnePanel = new JPanel(new GridBagLayout());
        assadosSemCarnePanel.setBorder(BorderFactory.createTitledBorder("Sem Carne"));
        GridBagConstraints gbcAssadosSemCarne = new GridBagConstraints();
        gbcAssadosSemCarne.insets = new Insets(5, 5, 5, 5);
        gbcAssadosSemCarne.gridx = 0;
        gbcAssadosSemCarne.gridy = GridBagConstraints.RELATIVE;
        gbcAssadosSemCarne.anchor = GridBagConstraints.WEST;

        assadosSemCarnePanel.add(new JCheckBox("Pizza"), gbcAssadosSemCarne);
        assadosSemCarnePanel.add(new JCheckBox("Esfirra"), gbcAssadosSemCarne);
        assadosSemCarnePanel.add(new JCheckBox("Empadinha"), gbcAssadosSemCarne);

        middlePanel.add(assadosPanel);
        middlePanel.add(assadosSemCarnePanel);
        
        // Adiciona o middlePanel ao centralPanel
        centralPanel.add(middlePanel);

        // Painel da Direita (opções do banco de dados)
        JPanel rightPanel = new JPanel(new GridLayout(2, 1)); // Divide em 2 partes
        rightPanel.setBorder(BorderFactory.createTitledBorder("Salgados Fritos"));

        // Adiciona checkboxes para salgados fritos
        JPanel fritosPanel = new JPanel(new GridBagLayout());
        fritosPanel.setBorder(BorderFactory.createTitledBorder("Com Carne"));
        GridBagConstraints gbcFritos = new GridBagConstraints();
        gbcFritos.insets = new Insets(5, 5, 5, 5);
        gbcFritos.gridx = 0;
        gbcFritos.gridy = GridBagConstraints.RELATIVE;
        gbcFritos.anchor = GridBagConstraints.WEST;

        fritosPanel.add(new JCheckBox("Coxinha"), gbcFritos);
        fritosPanel.add(new JCheckBox("Kibe"), gbcFritos);
        fritosPanel.add(new JCheckBox("Pastel"), gbcFritos);
        fritosPanel.add(new JCheckBox("Risóles"), gbcFritos);
        fritosPanel.add(new JCheckBox("Cigarrete"), gbcFritos);
        fritosPanel.add(new JCheckBox("Croquete"), gbcFritos);

        JPanel fritosSemCarnePanel = new JPanel(new GridBagLayout());
        fritosSemCarnePanel.setBorder(BorderFactory.createTitledBorder("Sem Carne"));
        GridBagConstraints gbcFritosSemCarne = new GridBagConstraints();
        gbcFritosSemCarne.insets = new Insets(5, 5, 5, 5);
        gbcFritosSemCarne.gridx = 0;
        gbcFritosSemCarne.gridy = GridBagConstraints.RELATIVE;
        gbcFritosSemCarne.anchor = GridBagConstraints.WEST;

        fritosSemCarnePanel.add(new JCheckBox("Pastel"), gbcFritosSemCarne);
        fritosSemCarnePanel.add(new JCheckBox("Bolinha de Queijo"), gbcFritosSemCarne);
        fritosSemCarnePanel.add(new JCheckBox("Risóles"), gbcFritosSemCarne);
        fritosSemCarnePanel.add(new JCheckBox("Cigarrete"), gbcFritosSemCarne);

        rightPanel.add(fritosPanel);
        rightPanel.add(fritosSemCarnePanel);
        
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
                mainWindow.showPanel(new SimulacaoBebidas(mainWindow));
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
