package feastplannerecalc.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import feastplannerecalc.config.MainWindowConfig;


public class SimulacaoBebidas extends JPanel {
	private static final long serialVersionUID = 1L;
	private MainWindow mainWindow; // Referência à MainWindow

	public SimulacaoBebidas(MainWindow mainWindow) {
        this.mainWindow = mainWindow; // Armazena a referência 
        
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



	     // Botão Simular: lógica para exibir o painel de Simulação de Salgado
	        simularButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // Aqui você pode adicionar a lógica para salvar os dados no banco
	                System.out.println("Simulação finalizada. Dados enviados para o banco.");

	                // Trocar para o painel de resultado da simulação de Salgado
	                mainWindow.showPanel(new ResultadoSimulacaoSalgado(mainWindow).getPanel());
	            }
	        });

    }
	public JPanel getPanel() {
        return this; // Retorna o painel para exibição
    }
}
