package feastplannerecalc.views;

import javax.swing.*;

import feastplannerecalc.config.MainWindowConfig;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultadoSimulacaoChurrasco {
    private JPanel panel;
    private MainWindow mainWindow; // Referência à MainWindow para navegação entre telas

    public ResultadoSimulacaoChurrasco(MainWindow mainWindow) {
        this.mainWindow = mainWindow; // Armazena a referência para troca de painéis

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Resultado da Simulação de Churrasco", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new GridLayout(1, 2, 20, 20));

        // Quadro esquerdo: Carnes e Acompanhamentos (Exemplos visuais)
        JPanel carnesAcompanhamentosPanel = new JPanel();
        carnesAcompanhamentosPanel.setLayout(new BorderLayout());
        carnesAcompanhamentosPanel.setBorder(BorderFactory.createTitledBorder("Carnes e Acompanhamentos"));

        JTextArea carnesAcompanhamentosText = new JTextArea(10, 30);
        carnesAcompanhamentosText.setEditable(false);
        carnesAcompanhamentosText.append("Picanha: 2 kg\n");
        carnesAcompanhamentosText.append("Maminha: 1.5 kg\n");
        carnesAcompanhamentosText.append("Linguiça: 1 kg\n");
        carnesAcompanhamentosText.append("Farofa: 0.5 kg\n");
        carnesAcompanhamentosText.append("Vinagrete: 0.7 kg\n");
        carnesAcompanhamentosPanel.add(new JScrollPane(carnesAcompanhamentosText), BorderLayout.CENTER);

        // Quadro direito: Bebidas e Acessórios (Exemplos visuais)
        JPanel bebidasAcessoriosPanel = new JPanel();
        bebidasAcessoriosPanel.setLayout(new BorderLayout());
        bebidasAcessoriosPanel.setBorder(BorderFactory.createTitledBorder("Bebidas e Acessórios"));

        JTextArea bebidasAcessoriosText = new JTextArea(10, 30);
        bebidasAcessoriosText.setEditable(false);
        bebidasAcessoriosText.append("Cerveja: 10 L\n");
        bebidasAcessoriosText.append("Refrigerante: 5 L\n");
        bebidasAcessoriosText.append("Copo: 50 unidades\n");
        bebidasAcessoriosText.append("Prato: 30 unidades\n");
        bebidasAcessoriosText.append("Papel Toalha: 3 rolos\n");
        bebidasAcessoriosPanel.add(new JScrollPane(bebidasAcessoriosText), BorderLayout.CENTER);

        // Adiciona os quadros ao painel principal
        contentPanel.add(carnesAcompanhamentosPanel);
        contentPanel.add(bebidasAcessoriosPanel);
        panel.add(contentPanel, BorderLayout.CENTER);

        // Painel para os botões "Editar", "Home", "+Simulação"
        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Posiciona os botões no canto direito

        JButton editarButton = new JButton("Editar");
        JButton homeButton = new JButton("Home");
        JButton novaSimulacaoButton = new JButton("+Simulação");

        // Ações dos botões
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exemplo de ação para o botão "Editar"
                System.out.println("Botão Editar clicado!"); 
                // Aqui você pode redirecionar para a tela de edição da simulação
                mainWindow.showPanel(new NovaSimulacaoChurrasco(mainWindow)); // Exemplo: volta para a tela de edição da simulação
            }
        });

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exemplo de ação para o botão "Home"
                mainWindow.showPanel(new MainWindowConfig().getMainPanel()); // Redireciona para a tela inicial
            }
        });

        novaSimulacaoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exemplo de ação para o botão "+Simulação"
                mainWindow.showPanel(new NovaSimulacao()); // Redireciona para iniciar uma nova simulação
            }
        });

        // Adiciona os botões ao painel
        botoesPanel.add(editarButton);
        botoesPanel.add(homeButton);
        botoesPanel.add(novaSimulacaoButton);

        // Adiciona o painel de botões na parte inferior do layout principal
        panel.add(botoesPanel, BorderLayout.SOUTH);
    }

    public JPanel getPanel() {
        return panel;
    }
}
