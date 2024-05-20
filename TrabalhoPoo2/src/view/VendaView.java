package view;

import controller.ClienteController;
import controller.ProdutoController;

import controller.VendaController;
import model.Cliente;
import model.ProdutoModel;
import model.Venda;
import model.FormaPagamento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VendaView extends JFrame {
    private VendaController saleController;
    private JTextField clienteCpfField;
    private JTextField produtoIdField;
    private JTextField quantidadeField;
    private JComboBox<String> formaPagamentoComboBox;
    private JTextArea resultArea;

    public VendaView() {
        saleController = new VendaController();
        initialize();
    }

    private void initialize() {
        setTitle("Realizar Venda");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2));

        add(new JLabel("CPF do Cliente:"));
        clienteCpfField = new JTextField();
        add(clienteCpfField);

        add(new JLabel("ID do Produto:"));
        produtoIdField = new JTextField();
        add(produtoIdField);

        add(new JLabel("Quantidade:"));
        quantidadeField = new JTextField();
        add(quantidadeField);

        add(new JLabel("Forma de Pagamento:"));
        formaPagamentoComboBox = new JComboBox<>(new String[]{"Cartao", "Dinheiro", "Cheque"});
        add(formaPagamentoComboBox);

        JButton iniciarButton = new JButton("Iniciar Venda");
        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarVenda();
            }
        });
        add(iniciarButton);

        JButton addButton = new JButton("Adicionar Produto");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarProduto();
            }
        });
        add(addButton);

        JButton finalizeButton = new JButton("Finalizar Venda");
        finalizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarVenda();
            }
        });
        add(finalizeButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea));

        setVisible(true);
    }

    private void iniciarVenda() {
        String cpf = clienteCpfField.getText();
        String formaPagamento = (String) formaPagamentoComboBox.getSelectedItem();
        try {
            saleController.iniciarVenda(cpf, formaPagamento);
            resultArea.setText("Venda iniciada com sucesso!");
        } catch (IllegalArgumentException e) {
            resultArea.setText(e.getMessage());
        }
    }

    private void adicionarProduto() {
        int produtoId = Integer.parseInt(produtoIdField.getText());
        int quantidade = Integer.parseInt(quantidadeField.getText());

        try {
            saleController.adicionarProduto(produtoId, quantidade);
            resultArea.setText("Produto adicionado à venda!");
        } catch (IllegalArgumentException e) {
            resultArea.setText(e.getMessage());
        } catch (IllegalStateException e) {
            resultArea.setText(e.getMessage());
        }
    }

    private void finalizarVenda() {
        try {
            saleController.finalizarVenda();
            resultArea.setText("Venda finalizada com sucesso!");
        } catch (IllegalStateException e) {
            resultArea.setText(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new VendaView();
    }
}
