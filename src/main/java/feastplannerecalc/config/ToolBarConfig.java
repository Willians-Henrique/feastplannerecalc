package feastplannerecalc.config;

import javax.swing.*;
import java.awt.*;

public class ToolBarConfig {
    public static JButton homeButton = new JButton("Home");
    public static JButton newSimButton = new JButton("Nova Simulação");
    public static JButton listSimButton = new JButton("Listar Simulações");
    public static JButton settingsButton = new JButton("Configurações");
    public static JButton manualButton = new JButton("Manual");

    public static JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        // Usando BoxLayout com espaçamento automático
        toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.X_AXIS));

        // Configura a altura da toolbar e largura total
        toolBar.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, 50));

        // Definir a cor dourada no fundo da toolbar
        toolBar.setBackground(new Color(0, 51, 102)); // Azul-marinho (#003366)

        // Configurações dos botões
        configureButton(homeButton);
        configureButton(newSimButton);
        configureButton(listSimButton);
        configureButton(settingsButton);
        configureButton(manualButton);

        // Adiciona o primeiro botão no canto esquerdo
        toolBar.add(homeButton);

        // Adiciona espaçamento entre os botões para espalhá-los uniformemente
        toolBar.add(Box.createHorizontalGlue());
        toolBar.add(newSimButton);
        toolBar.add(Box.createHorizontalGlue());
        toolBar.add(listSimButton);
        toolBar.add(Box.createHorizontalGlue());
        toolBar.add(settingsButton);
        toolBar.add(Box.createHorizontalGlue());
        toolBar.add(manualButton);

        return toolBar;
    }

    // Método para configurar cor e estilo dos botões
    private static void configureButton(JButton button) {
        button.setBackground(new Color(51, 102, 153)); // Azul médio (#336699)
        button.setForeground(Color.WHITE); // Texto preto para contraste
        button.setFocusPainted(false); // Remove o efeito de foco padrão
        button.setBorderPainted(false); // Remove borda padrão
        button.setFont(new Font("Arial", Font.BOLD, 20)); // Estilo do texto
        button.setOpaque(true); // Garante a cor de fundo
    }
}
