package feastplannerecalc.views;

import javax.swing.*;

import feastplannerecalc.config.MainWindowConfig;
import feastplannerecalc.model.ComidaQuantidadePadrao;
import feastplannerecalc.models.SimulacaoSalgado;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class ResultadoSimulacaoSalgado extends JPanel {
    private static final long serialVersionUID = 1L;
    private JPanel panel;
    private MainWindow mainWindow; // Referência à MainWindow para navegação entre telas
    private SimulacaoSalgado simulacao;
    
    private List<ComidaQuantidadePadrao> listaQuantidades;

    

    public ResultadoSimulacaoSalgado(MainWindow mainWindow,SimulacaoSalgado simulacao) {
        this.mainWindow = mainWindow; // Armazena a referência para troca de painéis
        this.simulacao = simulacao; // Inicializa a simulação
        

        
        // Carrega a lista de quantidades de comida padrão
        listaQuantidades = ComidaQuantidadePadrao.carregarQuantidadeSalgadoPorPessoa();
        
        // Define uma quantidade total de carne para a simulação
        double quantidadeTotalSalgado = simulacao.calcularQuantidadeTotalComida(listaQuantidades);
        
        // Chama a função para calcular e visualizar a distribuição das carnes no console
        simulacao.calcularDistribuicaoSalgados(quantidadeTotalSalgado);
        

     // Chama a função para salvar os dados no banco de dados
        simulacao.salvarResultadoNoBancoDeDados();
        
        
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Resultado da Simulação de Salgados", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new GridLayout(1, 2, 20, 20));

        // Quadro esquerdo: Salgados Assados e Fritos com Carne (Exemplos visuais)
        JPanel salgadosComCarnePanel = new JPanel();
        salgadosComCarnePanel.setLayout(new BorderLayout());
        salgadosComCarnePanel.setBorder(BorderFactory.createTitledBorder("Salgados Fritos"));

        JTextArea salgadosComCarneText = new JTextArea(10, 30);
        salgadosComCarneText.setEditable(false);
        salgadosComCarneText.setFont(new Font("Arial", Font.PLAIN, 22)); // Define tamanho da fonte maior
        // Exibe os salgados com carne
     // Carrega e exibe os salgados com carne
        Map<String, Integer> salgadosComCarne = simulacao.obterSalgadosComCarne();
        salgadosComCarne.forEach((nome, quantidade) -> 
            salgadosComCarneText.append(nome + ": " + quantidade + " unidades\n")
        );
        salgadosComCarnePanel.add(new JScrollPane(salgadosComCarneText), BorderLayout.CENTER);


        // Quadro direito: Salgados Sem Carne + Bebidas + Acessórios (Exemplos visuais)
        JPanel salgadosSemCarnePanel = new JPanel();
        salgadosSemCarnePanel.setLayout(new BorderLayout());
        salgadosSemCarnePanel.setBorder(BorderFactory.createTitledBorder("Salgados Assados + Bebidas + Acompanhamentos"));

        JTextArea salgadosSemCarneText = new JTextArea(10, 30);
        salgadosSemCarneText.setEditable(false);
        salgadosSemCarneText.setFont(new Font("Arial", Font.PLAIN, 22)); // Define tamanho da fonte maior

     // Carrega e exibe os salgados sem carne
        Map<String, Integer> salgadosSemCarne = simulacao.obterSalgadosSemCarne();
        salgadosSemCarne.forEach((nome, quantidade) -> 
            salgadosSemCarneText.append(nome + ": " + quantidade + " unidades\n")
        );

     // Exibe agregados de salgados
        Map<String, Double> agregadosSalgados = simulacao.obterQuantidadesAgregadosSalgado();
        agregadosSalgados.forEach((nome, quantidade) -> 
        salgadosSemCarneText.append(nome + ": " + quantidade + " unidades\n"));

        Map<String, Double> bebidasNecessarias = simulacao.obterBebidasSelecionadas();
        bebidasNecessarias.forEach((nome, quantidade) -> 
            salgadosSemCarneText.append(nome + ": " + quantidade + " litros\n")
        );

        
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
