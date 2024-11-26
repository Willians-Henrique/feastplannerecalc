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

        JTextArea textArea = new JTextArea(
        		"FeastPlanner&Calc - A sua calculadora personalizada de eventos\n\n" +
        			    "Bem-vindo ao FeastPlanner&Calc! Este é uma calculadora para a quantidade de comida e bebida necessária para o seu evento.\n" +
        			    "Você pode escolher entre opções para um evento com salgados ou churrasco, além de selecionar bebidas alcoólicas ou não alcoólicas, conforme suas preferências.\n\n" +
        			    "O sistema considera as seguintes categorias de pessoas:\n" +
        			    "- Comilão\n" +
        			    "- Homem\n" +
        			    "- Mulher\n" +
        			    "- Crianças\n\n" +
        			    "Além disso, é possível personalizar a quantidade de comida e bebida para cada tipo de pessoa, " +
        			    "ajustando a quantidade com base nas preferências e necessidades de cada um.\n\n" +
        			    "Com essas categorias, o FeastPlanner&Calc pode estimar com precisão a quantidade necessária de comida e bebida para seu evento, " +
        			    "garantindo que todos os convidados sejam bem atendidos e a festa seja um sucesso!"
        	);
        	textArea.setWrapStyleWord(true);
        	textArea.setLineWrap(true);
        	textArea.setFont(new Font("Arial", Font.PLAIN, 22));
        	textArea.setBackground(new Color(173, 216, 230));  // Azul claro para o fundo
        	textArea.setEditable(false);
        	textArea.setCaretPosition(0); // Para que o texto comece do topo

        
        mainPanel.add(new JScrollPane(textArea), gbcText);
    }
}
