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
        toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.X_AXIS));

        toolBar.add(homeButton);
        toolBar.add(newSimButton);
        toolBar.add(listSimButton);
        toolBar.add(settingsButton);
        toolBar.add(manualButton);

        toolBar.add(Box.createHorizontalGlue());

        return toolBar;
    }
}

