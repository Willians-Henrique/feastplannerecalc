package feastplannerecalc.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import feastplannerecalc.model.ResultadoSimulacao;

import java.awt.*;
import java.util.List;

public class ListarSimulacao {
    private JPanel panel;

    public ListarSimulacao() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Painel Listar Simulação", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label, BorderLayout.NORTH);

        // Colunas da tabela
        String[] colunas = {"id", "Data", "Tipo de Simulação", "Quantidade de Pessoas", "Quantidade de Comida", "Quantidade de Bebida"};

        // Dados estáticos de exemplo
        Object[][] dados = {
            {"001", "02/10/2024", "Churrasco", 20, "10 kg", "15 L"},
            {"002", "21/10/2024", "Salgados", 30, "300 unidades", "10 L"}
        };

        // Modelo de tabela para adicionar os dados e colunas
        DefaultTableModel model = new DefaultTableModel(dados, colunas);

        // Criação da tabela
        JTable tabela = new JTable(model);
        tabela.setFillsViewportHeight(true);

        // Configuração para ajustar a largura das colunas
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Adiciona a tabela ao painel com barra de rolagem
        JScrollPane scrollPane = new JScrollPane(tabela);
        panel.add(scrollPane, BorderLayout.CENTER);

        // --- Teste de carregamento dos dados reais do banco ---
        carregarDadosReais();
    }

    public JPanel getPanel() {
        return panel;
    }

    /**
     * Método para carregar os dados reais do banco e imprimir no console.
     */
    private void carregarDadosReais() {
        List<ResultadoSimulacao> simulacoes = ResultadoSimulacao.carregarResultadosSimulacao();

        // Imprimir os dados carregados no console
        System.out.println("Dados reais carregados do banco:");
        for (ResultadoSimulacao simulacao : simulacoes) {
            System.out.println("ID: " + simulacao.getId());
            System.out.println("Tipo de Comida: " + (simulacao.getTipoComida() != null ? simulacao.getTipoComida().getTipo() : "N/A"));
            System.out.println("Homens: " + simulacao.getQuantidadeHomens());
            System.out.println("Mulheres: " + simulacao.getQuantidadeMulheres());
            System.out.println("Crianças: " + simulacao.getQuantidadeCriancas());
            System.out.println("Comilões: " + simulacao.getQuantidadeComiloes());
            System.out.println("-----------------------------");
        }
    }
}
