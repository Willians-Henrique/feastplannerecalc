package feastplannerecalc.views;

import javax.swing.*;
import java.awt.*;

public class Manual extends JPanel {
    private static final long serialVersionUID = 1L;
    private JPanel panel;

    public Manual() {
        setBackground(new Color(227, 242, 253)); // Cor azul claro
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Título do manual
        JLabel label = new JLabel("Manual do Usuário", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label, BorderLayout.NORTH);

        // Texto do manual
        JTextArea manualText = new JTextArea();
        manualText.setText("Bem-vindo ao Manual do Usuário do FeastPlanner&Calc!\n\n" +
            "1. **Nova Simulação**: Aqui você pode criar uma nova simulação, " +
            "onde será possível escolher entre as opções de **Churrasco** ou **Salgado**. " +
            "Com base nessa escolha, o sistema calculará as quantidades de comida e bebida " +
            "necessárias para o evento.\n\n" +
            "2. **Listar Simulações**: Esta opção permite visualizar todas as simulações salvas. " +
            "Você poderá visualizar os detalhes das simulações anteriores e excluí-las, caso necessário.\n\n" +
            "3. **Configurações**: Nesta seção, você pode ajustar as configurações de " +
            "quantidade de comida e bebida para cada tipo de pessoa (Comilão, Homem, Mulher, Criança). " +
            "As configurações podem ser editadas para ajustar de acordo com a sua necessidade.");
        manualText.setEditable(false); // Não permite edição
        manualText.setBackground(new Color(227, 242, 253)); // Cor de fundo da área de texto
        manualText.setFont(new Font("Arial", Font.PLAIN, 16));
        manualText.setWrapStyleWord(true);
        manualText.setLineWrap(true);

        // Adiciona o JTextArea dentro de um JScrollPane
        JScrollPane scrollPane = new JScrollPane(manualText);
        panel.add(scrollPane, BorderLayout.CENTER);
    }

    public JPanel getPanel() {
        return panel;
    }
}
