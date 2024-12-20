package feastplannerecalc.views;

import javax.swing.*;

import feastplannerecalc.config.MainWindowConfig;
import feastplannerecalc.model.Comida;
import feastplannerecalc.model.ComidaQuantidadePadrao;
import feastplannerecalc.models.SimulacaoChurrasco;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResultadoSimulacaoChurrasco extends JPanel {
    private static final long serialVersionUID = 1L;
    private JPanel panel;
    private MainWindow mainWindow; // Referência à MainWindow para navegação entre telas
	private SimulacaoChurrasco simulacao;
    // Adiciona a variável de lista como campo da classe
    private List<ComidaQuantidadePadrao> listaQuantidades;

    public ResultadoSimulacaoChurrasco(MainWindow mainWindow, SimulacaoChurrasco simulacao) {
        this.mainWindow = mainWindow; // Armazena a referência para troca de painéis
        this.simulacao = simulacao; // Inicializa a simulação
        
        // Carrega a lista de quantidades de comida padrão
        listaQuantidades = ComidaQuantidadePadrao.carregarQuantidadeCarnePorPessoa();
        
        // Carrega e exibe no console a lista de comidas selecionadas e seus aproveitamentos
        List<Comida> comidasSelecionadas = SimulacaoChurrasco.carregarAproveitamento(simulacao);
        System.out.println("Lista de Comidas Selecionadas e Aproveitamento:");
        for (Comida comida : comidasSelecionadas) {
            System.out.println("Nome: " + comida.getNome() + ", Aproveitamento: " + comida.getAproveitamento());
        }
        
        
        // Define uma quantidade total de carne para a simulação
        double quantidadeTotalCarne = simulacao.calcularQuantidadeTotalComida(listaQuantidades); 
        
        // Chama a função para calcular e visualizar a distribuição das carnes no console
        simulacao.calcularDistribuicaoCarnes(quantidadeTotalCarne);
        

        // Chama a função para salvar os dados no banco de dados
        simulacao.salvarResultadoNoBancoDeDados();
        
        
        
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
        
     // Exibe carnes com osso
        Map<String, Double> carnesComOsso = simulacao.obterCarnesComOsso();
        carnesComOsso.forEach((nome, quantidade) -> carnesAcompanhamentosText.append(nome + ": " + quantidade + " kg\n"));

        // Exibe carnes sem osso
        Map<String, Double> carnesSemOsso = simulacao.obterCarnesSemOsso();
        carnesSemOsso.forEach((nome, quantidade) -> carnesAcompanhamentosText.append(nome + ": " + quantidade + " kg\n"));
        
     // Exibe frangos
        Map<String, Double> carnesFrango = simulacao.obterCarnesFrango();
        carnesFrango.forEach((nome, quantidade) -> carnesAcompanhamentosText.append(nome + ": " + quantidade + " kg\n"));
        
        carnesAcompanhamentosPanel.add(new JScrollPane(carnesAcompanhamentosText), BorderLayout.CENTER);

        // Quadro direito: Bebidas e Acessórios (Exemplos visuais)
        JPanel bebidasAcessoriosPanel = new JPanel();
        bebidasAcessoriosPanel.setLayout(new BorderLayout());
        bebidasAcessoriosPanel.setBorder(BorderFactory.createTitledBorder("Bebidas e Acessórios"));

        JTextArea bebidasAcessoriosText = new JTextArea(10, 30);
        bebidasAcessoriosText.setEditable(false);

        // Exibe agregados calculados
        Map<String, Double> agregados = simulacao.obterQuantidadesAgregados();
        agregados.forEach((nome, quantidade) -> bebidasAcessoriosText.append(nome + ": " + quantidade + " unidades\n"));
     
        // Exibe bebidas calculados
        Map<String, Double> bebidasNecessarias = simulacao.obterBebidasSelecionadas();
        bebidasNecessarias.forEach((nome, quantidade) -> bebidasAcessoriosText.append(nome + ": " + quantidade + " litros\n"));
        
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
    
	 // Getter para acessar a lista em outra parte do código, se necessário
    public List<ComidaQuantidadePadrao> getListaQuantidades() {
        return listaQuantidades;
    }

    public JPanel getPanel() {
        return panel;
    }
}
