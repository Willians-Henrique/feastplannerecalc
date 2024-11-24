package feastplannerecalc.views;

import javax.swing.*;
import java.awt.*;

public class Manual {
    private JPanel panel;

    public Manual() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Manual do Usuário", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label, BorderLayout.CENTER);
    }

    public JPanel getPanel() {
        return panel;
    }
}
