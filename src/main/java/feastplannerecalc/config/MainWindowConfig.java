package feastplannerecalc.config;


import javax.swing.*;
import java.awt.*;

public class MainWindowConfig {
	private JPanel mainPanel;

    public MainWindowConfig() {
        mainPanel = new JPanel(new GridBagLayout());
        setupInitialPanel(mainPanel);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void setupInitialPanel(JPanel mainPanel) {
        // Configuração da imagem
        GridBagConstraints gbcImage = new GridBagConstraints();
        gbcImage.fill = GridBagConstraints.BOTH;
        gbcImage.insets = new Insets(5, 5, 5, 5);
        gbcImage.gridx = 0;
        gbcImage.gridy = 0;
        gbcImage.weightx = 0.3;
        gbcImage.weighty = 1.0;

        JLabel imageLabel = new JLabel(new ImageIcon("src/main/java/feastplannerecalc/views/images/sorteador_resized.jpg"));
        mainPanel.add(imageLabel, gbcImage);

        // Configuração do texto
        GridBagConstraints gbcText = new GridBagConstraints();
        gbcText.fill = GridBagConstraints.BOTH;
        gbcText.insets = new Insets(5, 5, 5, 5);
        gbcText.gridx = 1;
        gbcText.gridy = 0;
        gbcText.weightx = 0.7;
        gbcText.weighty = 1.0;

        JTextArea textArea = new JTextArea("SORTEADOR - A sua calculadora personalizada de eventos \n\n\n\n" +
                "Este é um sorteador que calcula a quantidade de comida e bebida necessária para seu evento. " +
                "Pode ser uma comemoração com salgados ou um churrasco. Você poderá escolher bebidas " +
                "alcoólicas ou não alcoólicas, de acordo com suas preferências.\n\n" +
                "O sistema considera duas categorias de pessoas: aquelas que comem carne e aquelas que não comem. Para " +
                "aqueles que não comem carne, serão oferecidas opções vegetarianas. Para quem consome carne, há quatro " +
                "categorias: comilão, homem, mulher e crianças. Isso ajudará a estimar com maior precisão a quantidade " +
                "necessária de comida e bebida para o evento com base nas opções selecionadas.");
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 22));

        mainPanel.add(new JScrollPane(textArea), gbcText);
    }
}
