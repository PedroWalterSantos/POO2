package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        initialize();
    }

    private void initialize() {
        setTitle("Sistema de Vendas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JButton produtoButton = new JButton("Gerenciar Produtos");
        produtoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaProdutos();
            }
        });
        panel.add(produtoButton);

        JButton vendaButton = new JButton("Realizar Venda");
        vendaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaVendas();
            }
        });
        panel.add(vendaButton);

        JButton clienteButton = new JButton("Gerenciar Clientes");
        clienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaClientes();
            }
        });
        panel.add(clienteButton);

        add(panel);
        setVisible(true);
    }

    private void abrirTelaProdutos() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProdutoView();
            }
        });
    }

    private void abrirTelaVendas() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VendaView();
            }
        });
    }

    private void abrirTelaClientes() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClienteView();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaPrincipal();
            }
        });
    }
}
