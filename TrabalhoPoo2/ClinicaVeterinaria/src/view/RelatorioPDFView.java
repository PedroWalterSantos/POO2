package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RelatorioPDFView extends JFrame {
    private JComboBox<String> filtroComboBox;
    private JTextField filtroTextField;
    private JButton gerarRelatorioButton;

    public RelatorioPDFView() {
        setTitle("Gerar Relatório PDF");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        String[] opcoesFiltro = {"Por Data", "Por Animal", "Por Serviço"};
        filtroComboBox = new JComboBox<>(opcoesFiltro);
        filtroTextField = new JTextField();
        gerarRelatorioButton = new JButton("Gerar Relatório");

        panel.add(new JLabel("Filtrar por:"));
        panel.add(filtroComboBox);

        panel.add(new JLabel("Valor do Filtro:"));
        panel.add(filtroTextField);

        gerarRelatorioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarRelatorioPDF();
            }
        });

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        add(gerarRelatorioButton, BorderLayout.SOUTH);
    }

    private void gerarRelatorioPDF() {
        String filtro = (String) filtroComboBox.getSelectedItem();
        String valorFiltro = filtroTextField.getText();

        // Chamada para a lógica de geração de relatório em PDF
        GeradorRelatorioPDF gerador = new GeradorRelatorioPDF();
        switch (filtro) {
            case "Por Data":
                gerador.gerarRelatorioPorDataPDF(valorFiltro);
                break;
            case "Por Animal":
                gerador.gerarRelatorioPorAnimalPDF(valorFiltro);
                break;
            case "Por Serviço":
                gerador.gerarRelatorioPorServicoPDF(valorFiltro);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Filtro inválido!");
        }

        JOptionPane.showMessageDialog(this, "Relatório gerado com sucesso!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RelatorioPDFView().setVisible(true);
            }
        });
    }
}
