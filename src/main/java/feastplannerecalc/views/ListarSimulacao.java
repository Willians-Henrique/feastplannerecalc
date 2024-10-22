package feastplannerecalc.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ListarSimulacao {
    private JPanel panel;

    public ListarSimulacao() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Painel Listar Simulação", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label, BorderLayout.NORTH);

        // Colunas da tabela
        String[] colunas = {"id","Data", "Tipo de Simulação", "Quantidade de Pessoas", "Quantidade de Comida", "Quantidade de Bebida"};

        // Dados estáticos de exemplo
        Object[][] dados = {
            {"001","02/10/2024", "Churrasco", 20, "10 kg", "15 L"},
            {"002","21/10/2024", "Salgados", 30, "300 unidades", "10 L"}
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
    }

    public JPanel getPanel() {
        return panel;
    }
}
