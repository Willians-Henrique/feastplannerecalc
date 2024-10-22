package feastplannerecalc.views;

import javax.swing.*;
import java.awt.*;

public class Configuracoes {
    private JPanel panel;

    public Configuracoes() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Painel Configurações", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label, BorderLayout.CENTER);
    }

    public JPanel getPanel() {
        return panel;
    }
}
