package feastplannerecalc.views;

import javax.swing.*;
import java.awt.*;

public class ListarSimulacao {
    private JPanel panel;

    public ListarSimulacao() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Painel Listar Simulação", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label, BorderLayout.CENTER);
    }

    public JPanel getPanel() {
        return panel;
    }
}
