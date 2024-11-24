package feastplannerecalc.views;

import javax.swing.*;
import javax.swing.table.*;

import org.hibernate.Session;

import feastplannerecalc.database.HibernateUtil;
import feastplannerecalc.model.ResultadoChurrasco;
import feastplannerecalc.model.ResultadoSalgado;
import feastplannerecalc.model.ResultadoSimulacao;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ListarSimulacao {
    private JPanel panel;
    private DefaultTableModel model;

    public ListarSimulacao() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Painel Listar Simulação", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label, BorderLayout.NORTH);

        // Colunas da tabela
        String[] colunas = {"ID", "Tipo de Simulação", "Quantidade de Pessoas", "Quantidade de Comida", "Quantidade de Bebida", "Excluir"};

        // Modelo de tabela inicial vazio
        model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5; // Apenas a coluna "Excluir" é editável
            }
        };


        // Criação da tabela
        JTable tabela = new JTable(model);

        // Renderizador e editor para a coluna de exclusão
        tabela.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        tabela.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox(), model));

        tabela.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(tabela);
        panel.add(scrollPane, BorderLayout.CENTER);

        carregarDadosReais();
        preencherTabelaComDadosReais();
    }

    public JPanel getPanel() {
        return panel;
    }

    /**
     * Carrega os dados reais do banco.
     */
    private List<ResultadoSimulacao> simulacoes;
    private List<ResultadoChurrasco> churrascos;
    private List<ResultadoSalgado> salgados;

    private void carregarDadosReais() {
        simulacoes = ResultadoSimulacao.carregarResultadosSimulacao();
        churrascos = ResultadoChurrasco.carregarResultadosChurrasco();
        salgados = ResultadoSalgado.carregarResultadosSalgado();
    }

    /**
     * Preenche a tabela com os dados carregados do banco.
     */
    private void preencherTabelaComDadosReais() {
        if (simulacoes == null || simulacoes.isEmpty()) {
            System.out.println("Nenhum dado encontrado no banco.");
            return;
        }

        for (ResultadoSimulacao simulacao : simulacoes) {
            Long tipoComidaId = simulacao.getTipoComida().getId();
            String tipoSimulacao = (tipoComidaId == 1L) ? "Churrasco" : (tipoComidaId == 2L) ? "Salgado" : "Desconhecido";

            int totalPessoas = simulacao.getQuantidadeHomens() + 
                               simulacao.getQuantidadeMulheres() + 
                               simulacao.getQuantidadeCriancas() + 
                               simulacao.getQuantidadeComiloes();

            String quantidadeComida = "Desconhecido";
            String quantidadeBebida = "Desconhecido";

            if (tipoComidaId == 1L) { // Churrasco
                for (ResultadoChurrasco churrasco : churrascos) {
                    if (churrasco.getSimulacao().getId().equals(simulacao.getId())) {
                        quantidadeComida = churrasco.getTotalComida() + " kg";
                        quantidadeBebida = churrasco.getTotalBebida() + " L";
                        break;
                    }
                }
            } else if (tipoComidaId == 2L) { // Salgado
                for (ResultadoSalgado salgado : salgados) {
                    if (salgado.getSimulacao().getId().equals(simulacao.getId())) {
                        quantidadeComida = salgado.getTotalComida() + " unidades";
                        quantidadeBebida = salgado.getTotalBebida() + " L";
                        break;
                    }
                }
            }

            // Adicionar dados à tabela
            model.addRow(new Object[]{
                simulacao.getId(), tipoSimulacao, totalPessoas, quantidadeComida, quantidadeBebida, "Excluir"
            });
        }
    }

 // Classe para renderizar o botão
    static class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setText("Excluir");
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            return this;
        }
    }

    // Classe para editar e lidar com cliques no botão
    static class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private boolean clicked;
        private DefaultTableModel model;
        private int row;

        public ButtonEditor(JCheckBox checkBox, DefaultTableModel model) {
            super(checkBox);
            this.model = model;
            button = new JButton("Excluir");
            button.addActionListener(e -> fireEditingStopped());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.row = row;
            clicked = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (clicked) {
                int confirm = JOptionPane.showConfirmDialog(button, "Deseja realmente excluir esta simulação?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    Long id = (Long) model.getValueAt(row, 0); // Obter o ID como Long
                    String tipoSimulacao = (String) model.getValueAt(row, 1); // Obter o tipo de simulação

                    // Chamar o método de exclusão com os parâmetros
                    excluirSimulacao(id, tipoSimulacao);

                    // Verificar se a linha é válida antes de tentar removê-la
                    if (row >= 0 && row < model.getRowCount()) {
                        model.removeRow(row); // Remover a linha da tabela
                    }
                }
            }
            clicked = false;
            return "Excluir";
        }





        @Override
        public boolean stopCellEditing() {
            clicked = false;
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }

     // Método para excluir a simulação do banco de dados
        private void excluirSimulacao(Long id, String tipoSimulacao) {
            Session session = null;
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();

                if ("Churrasco".equalsIgnoreCase(tipoSimulacao)) {
                    // Excluir registros de ResultadoChurrasco
                    String hqlChurrasco = "DELETE FROM ResultadoChurrasco WHERE simulacao.id = :simulacaoId";
                    session.createQuery(hqlChurrasco)
                           .setParameter("simulacaoId", id)
                           .executeUpdate();
                } else if ("Salgado".equalsIgnoreCase(tipoSimulacao)) {
                    // Excluir registros de ResultadoSalgado
                    String hqlSalgado = "DELETE FROM ResultadoSalgado WHERE simulacao.id = :simulacaoId";
                    session.createQuery(hqlSalgado)
                           .setParameter("simulacaoId", id)
                           .executeUpdate();
                }

                // Excluir a simulação principal
                String hqlSimulacao = "DELETE FROM ResultadoSimulacao WHERE id = :simulacaoId";
                session.createQuery(hqlSimulacao)
                       .setParameter("simulacaoId", id)
                       .executeUpdate();

                // Confirmar a transação
                session.getTransaction().commit();

                JOptionPane.showMessageDialog(null, "Simulação excluída com sucesso!", "Exclusão", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                if (session != null && session.getTransaction() != null) {
                    session.getTransaction().rollback();
                }
                JOptionPane.showMessageDialog(null, "Erro ao excluir simulação: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }



    }

}
