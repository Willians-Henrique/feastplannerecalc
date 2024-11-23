package feastplannerecalc.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import feastplannerecalc.model.ResultadoChurrasco;
import feastplannerecalc.model.ResultadoSalgado;
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
        // Carrega os resultados de simulação
        List<ResultadoSimulacao> simulacoes = ResultadoSimulacao.carregarResultadosSimulacao();
        List<ResultadoChurrasco> churrascos = ResultadoChurrasco.carregarResultadosChurrasco();
        List<ResultadoSalgado> salgados = ResultadoSalgado.carregarResultadosSalgado();

        // Imprimir os dados carregados no console
        System.out.println("Dados reais carregados do banco:");
        
        for (ResultadoSimulacao simulacao : simulacoes) {
        	
            System.out.println("ID: " + simulacao.getId());
            
            // Corrigir a comparação do tipo de comida usando o ID
            Long tipoComidaId = simulacao.getTipoComida().getId();
            String tipoSimulacao = (tipoComidaId == 1L) ? "Churrasco" : (tipoComidaId == 2L) ? "Salgado" : "Desconhecido";
            System.out.println("Tipo de Simulação: " + tipoSimulacao);
            
            System.out.println("Quantidade de Pessoas: " +  + (simulacao.getQuantidadeHomens() + simulacao.getQuantidadeMulheres() + simulacao.getQuantidadeCriancas()+ simulacao.getQuantidadeComiloes()));

            // Buscar informações específicas de comida e bebida para cada tipo de simulacao
            if (tipoComidaId == 1L) { // Churrasco
                for (ResultadoChurrasco churrasco : churrascos) {
                    if (churrasco.getSimulacao().getId().equals(simulacao.getId())) {
                        System.out.println("Quantidade de Comida (Churrasco): " + churrasco.getTotalComida() + " kg");
                        System.out.println("Quantidade de Bebida (Churrasco): " + churrasco.getTotalBebida() + " L");
                    }
                }
            } else if (tipoComidaId == 2L) { // Salgado
                for (ResultadoSalgado salgado : salgados) {
                    if (salgado.getSimulacao().getId().equals(simulacao.getId())) {
                        System.out.println("Quantidade de Comida (Salgados): " + salgado.getTotalComida() + " unidades");
                        System.out.println("Quantidade de Bebida (Salgados): " + salgado.getTotalBebida() + " L");
                    }
                }
            }

            System.out.println("-----------------------------");
        }
    }
}
