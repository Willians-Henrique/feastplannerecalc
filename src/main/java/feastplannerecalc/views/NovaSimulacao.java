package feastplannerecalc.views;

import javax.swing.*;
import java.awt.*;

public class NovaSimulacao extends JPanel {
    private static final long serialVersionUID = 1L;

    public NovaSimulacao() {
        setLayout(new BorderLayout());
        
        // Criação do painel para a divisão
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(15, 15, 15, 15); // Padding de 15% (ajustável conforme necessário)
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // Painel para a parte esquerda
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        leftPanel.setBackground(Color.WHITE);

        // Configuração do botão e label da parte esquerda
        JButton salgadosButton = new JButton(new ImageIcon("src/main/java/feastplannerecalc/views/images/salgados.jpg"));
        salgadosButton.setBorder(BorderFactory.createEmptyBorder());
        salgadosButton.setContentAreaFilled(false);
        salgadosButton.setPreferredSize(new Dimension(700, 400)); // Ajuste conforme necessário
        JLabel salgadosLabel = new JLabel("Salgados", SwingConstants.CENTER);
        salgadosLabel.setFont(new Font("Arial", Font.BOLD, 50));

        GridBagConstraints gbcLeft = new GridBagConstraints();
        gbcLeft.insets = new Insets(5, 5, 5, 5);
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 0;
        gbcLeft.weightx = 1.0;
        gbcLeft.weighty = 1.0;
        gbcLeft.anchor = GridBagConstraints.CENTER;
        leftPanel.add(salgadosButton, gbcLeft);

        gbcLeft.gridy = 1;
        gbcLeft.weighty = 0.0;
        leftPanel.add(salgadosLabel, gbcLeft);
        
        // Adiciona ação de clique para o botão de "Salgados"
        salgadosButton.addActionListener(e -> {
        // Obtém a instância da janela principal e troca para a nova view
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (topFrame instanceof MainWindow) {
             ((MainWindow) topFrame).showPanel(new NovaSimulacaoSalgados((MainWindow) topFrame)); // Passa a referência do MainWindow.getPanel());
          }
        });

        // Painel para a parte direita
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        rightPanel.setBackground(Color.WHITE);

        // Configuração do botão e label da parte direita
        JButton churrascoButton = new JButton(new ImageIcon("src/main/java/feastplannerecalc/views/images/churrasco.jpg"));
        churrascoButton.setBorder(BorderFactory.createEmptyBorder());
        churrascoButton.setContentAreaFilled(false);
        churrascoButton.setPreferredSize(new Dimension(800, 400)); // Ajuste conforme necessário
        JLabel churrascoLabel = new JLabel("Churrasco", SwingConstants.CENTER);
        churrascoLabel.setFont(new Font("Arial", Font.BOLD, 50));

        GridBagConstraints gbcRight = new GridBagConstraints();
        gbcRight.insets = new Insets(5, 5, 5, 5);
        gbcRight.gridx = 0;
        gbcRight.gridy = 0;
        gbcRight.weightx = 1.0;
        gbcRight.weighty = 1.0;
        gbcRight.anchor = GridBagConstraints.CENTER;
        rightPanel.add(churrascoButton, gbcRight);

        gbcRight.gridy = 1;
        gbcRight.weighty = 0.0;
        rightPanel.add(churrascoLabel, gbcRight);
        
        // Adiciona ação de clique para o botão de "Churrasco"
        churrascoButton.addActionListener(e -> {        		
        // Obtém a instância da janela principal e troca para a nova view
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (topFrame instanceof MainWindow) {
             ((MainWindow) topFrame).showPanel(new NovaSimulacaoChurrasco((MainWindow) topFrame));
          }
        });
        
        // Adiciona os dois painéis ao painel principal
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        
        add(mainPanel, BorderLayout.CENTER);
    }
    public JPanel getPanel() {
        return this;
    }
    
}
