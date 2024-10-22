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

        JLabel label = new JLabel("Resultado da Simulação", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label, BorderLayout.CENTER);

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
