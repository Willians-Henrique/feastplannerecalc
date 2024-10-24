package feastplannerecalc.views;

import javax.swing.*;

import feastplannerecalc.config.MainWindowConfig;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultadoSimulacaoSalgado {
    private JPanel panel;
    private MainWindow mainWindow; // Referência à MainWindow para navegação entre telas

    public ResultadoSimulacaoSalgado(MainWindow mainWindow) {
        this.mainWindow = mainWindow; // Armazena a referência para troca de painéis

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Resultado da Simulação de Salgados", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new GridLayout(1, 2, 20, 20));

        // Quadro esquerdo: Salgados Assados e Fritos com Carne (Exemplos visuais)
        JPanel salgadosComCarnePanel = new JPanel();
        salgadosComCarnePanel.setLayout(new BorderLayout());
        salgadosComCarnePanel.setBorder(BorderFactory.createTitledBorder("Salgados Assados e Fritos com Carne"));

        JTextArea salgadosComCarneText = new JTextArea(10, 30);
        salgadosComCarneText.setEditable(false);
        salgadosComCarneText.append("Coxinha: 30 unidades\n");
        salgadosComCarneText.append("Kibe: 25 unidades\n");
        salgadosComCarneText.append("Esfirra de Carne: 40 unidades\n");
        salgadosComCarneText.append("Hamburger: 20 unidades\n");
        salgadosComCarneText.append("Pastel de Carne: 35 unidades\n");
        salgadosComCarnePanel.add(new JScrollPane(salgadosComCarneText), BorderLayout.CENTER);

        // Quadro direito: Salgados Sem Carne + Bebidas + Acessórios (Exemplos visuais)
        JPanel salgadosSemCarnePanel = new JPanel();
        salgadosSemCarnePanel.setLayout(new BorderLayout());
        salgadosSemCarnePanel.setBorder(BorderFactory.createTitledBorder("Salgados Sem Carne + Bebidas + Acessórios"));

        JTextArea salgadosSemCarneText = new JTextArea(10, 30);
        salgadosSemCarneText.setEditable(false);
        salgadosSemCarneText.append("Pizza: 15 unidades\n");
        salgadosSemCarneText.append("Bolinha de Queijo: 30 unidades\n");
        salgadosSemCarneText.append("Risóles de Queijo: 20 unidades\n");
        salgadosSemCarneText.append("Cerveja: 10 L\n");
        salgadosSemCarneText.append("Refrigerante: 5 L\n");
        salgadosSemCarneText.append("Copo: 50 unidades\n");
        salgadosSemCarneText.append("Prato: 30 unidades\n");
        salgadosSemCarneText.append("Papel Toalha: 3 rolos\n");
        salgadosSemCarnePanel.add(new JScrollPane(salgadosSemCarneText), BorderLayout.CENTER);

        // Adiciona os quadros ao painel principal
        contentPanel.add(salgadosComCarnePanel);
        contentPanel.add(salgadosSemCarnePanel);
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
                mainWindow.showPanel(new NovaSimulacaoSalgados(mainWindow)); // Exemplo: volta para a tela de edição da simulação
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
