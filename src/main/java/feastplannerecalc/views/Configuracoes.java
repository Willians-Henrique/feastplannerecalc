package feastplannerecalc.views;

import feastplannerecalc.database.HibernateUtil;
import feastplannerecalc.model.ComidaQuantidadePadrao;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Configuracoes {
    private JPanel panel;

    public Configuracoes() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Cabeçalho
        JLabel label = new JLabel("Painel Configurações", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label, BorderLayout.NORTH);

        // Configurar colunas da tabela
        String[] colunas = {"Categoria", "Quantidade Carne (g)", "Quantidade Salgado (un)"};

        // Criar modelo de tabela
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column > 0; // Permitir edição apenas nas colunas de quantidade
            }
        };

        // Criar tabela
        JTable tabela = new JTable(model);
        tabela.setRowHeight(30);

        // Preencher a tabela com os dados do banco de dados
        carregarDadosNaTabela(model);

        // Adicionar tabela ao painel com rolagem
        JScrollPane scrollPane = new JScrollPane(tabela);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Painel de botões
        JPanel botoesPanel = new JPanel();
        botoesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton salvarButton = new JButton("Salvar");

        // Adicionar a funcionalidade de salvar
        salvarButton.addActionListener(e -> salvarDados(tabela));
        

        botoesPanel.add(salvarButton);

        // Adicionar o painel de botões à parte inferior
        panel.add(botoesPanel, BorderLayout.SOUTH);
    }

    /**
     * Preenche a tabela com os dados recuperados do banco de dados.
     */
    private void carregarDadosNaTabela(DefaultTableModel model) {
        try {
            // Mapeamento fixo dos IDs das categorias para os nomes
            Map<Long, String> categoriaMap = new HashMap<>();
            categoriaMap.put(1L, "Comilão");
            categoriaMap.put(2L, "Homem");
            categoriaMap.put(3L, "Mulher");
            categoriaMap.put(4L, "Criança");

            // Carregar os dados do banco de dados
            List<ComidaQuantidadePadrao> listaQuantidades = ComidaQuantidadePadrao.carregarQuantidadeCarnePorPessoa();

            for (ComidaQuantidadePadrao quantidade : listaQuantidades) {
                // Buscar o nome da categoria pelo ID
                Long categoriaId = quantidade.getPessoasCategoria().getId(); // ID da categoria
                String categoriaNome = categoriaMap.getOrDefault(categoriaId, "Desconhecido");

                // Quantidade de carne e salgado
                Integer quantidadeCarne = quantidade.getQuantidadeCarne(); // Quantidade de carne
                Integer quantidadeSalgado = quantidade.getQuantidadeSalgado(); // Quantidade de salgado

                // Adicionar linha à tabela
                model.addRow(new Object[]{categoriaNome, quantidadeCarne, quantidadeSalgado});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panel, "Erro ao carregar dados do banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void salvarDados(JTable tabela) {
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Iterar sobre as linhas da tabela
            for (int i = 0; i < model.getRowCount(); i++) {
                String categoriaNome = (String) model.getValueAt(i, 0);
                String quantidadeCarneStr = model.getValueAt(i, 1).toString();
                String quantidadeSalgadoStr = model.getValueAt(i, 2).toString();

                // Converter valores para Integer
                Integer quantidadeCarne = Integer.parseInt(quantidadeCarneStr);
                Integer quantidadeSalgado = Integer.parseInt(quantidadeSalgadoStr);

                // Mapear o nome da categoria para o ID
                Long categoriaId = null;
                if (categoriaNome.equals("Comilão")) categoriaId = 1L;
                else if (categoriaNome.equals("Homem")) categoriaId = 2L;
                else if (categoriaNome.equals("Mulher")) categoriaId = 3L;
                else if (categoriaNome.equals("Criança")) categoriaId = 4L;

                if (categoriaId != null) {
                    // Carregar o objeto gerenciado pelo Hibernate
                    ComidaQuantidadePadrao quantidade = session.get(ComidaQuantidadePadrao.class, categoriaId);

                    if (quantidade != null) {
                        // Atualizar os valores
                        quantidade.setQuantidadeCarne(quantidadeCarne);
                        quantidade.setQuantidadeSalgado(quantidadeSalgado);

                        // Persistir as alterações
                        session.update(quantidade);
                    }
                }
            }

            // Confirmar a transação
            transaction.commit();
            JOptionPane.showMessageDialog(panel, "Dados salvos com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(panel, "Os valores de quantidade devem ser números inteiros.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(panel, "Erro ao salvar dados no banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public JPanel getPanel() {
        return panel;
    }
}
