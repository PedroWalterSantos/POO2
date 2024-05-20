package view;



import controller.ProdutoController;
import model.ProdutoAlimenticio;
import model.ProdutoEletronico;
import model.ProdutoLimpeza;
import model.ProdutoModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProdutoView extends JFrame {
    private ProdutoController produtoController;
    private JTextField nomeField;
    private JTextField descricaoField;
    private JTextField precoCustoField;
    private JTextField precoVendaField;
    private JTextField quantidadeEstoqueField;
    private JTextField categoriaField;
    private JTextField idField;
    private JTextArea resultArea;

    public ProdutoView() {
        produtoController = new ProdutoController();
        initialize();
    }

    private void initialize() {
        setTitle("Gerenciamento de Produtos");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(10, 2));

        add(new JLabel("ID:"));
        idField = new JTextField();
        add(idField);

        add(new JLabel("Nome:"));
        nomeField = new JTextField();
        add(nomeField);

        add(new JLabel("Descrição:"));
        descricaoField = new JTextField();
        add(descricaoField);

        add(new JLabel("Preço de Custo:"));
        precoCustoField = new JTextField();
        add(precoCustoField);

        add(new JLabel("Preço de Venda:"));
        precoVendaField = new JTextField();
        add(precoVendaField);

        add(new JLabel("Quantidade em Estoque:"));
        quantidadeEstoqueField = new JTextField();
        add(quantidadeEstoqueField);

        add(new JLabel("Categoria:"));
        categoriaField = new JTextField();
        add(categoriaField);

        JButton addButton = new JButton("Adicionar Produto");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarProduto();
            }
        });
        add(addButton);

        JButton updateButton = new JButton("Atualizar Produto");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarProduto();
            }
        });
        add(updateButton);

        JButton removeButton = new JButton("Remover Produto");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerProduto();
            }
        });
        add(removeButton);

        JButton searchButton = new JButton("Consultar Produto");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarProduto();
            }
        });
        add(searchButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea));

        setVisible(true);
    }

    private void adicionarProduto() {
        String nome = nomeField.getText();
        String descricao = descricaoField.getText();
        double precoCusto = Double.parseDouble(precoCustoField.getText());
        double precoVenda = Double.parseDouble(precoVendaField.getText());
        int quantidadeEstoque = Integer.parseInt(quantidadeEstoqueField.getText());
        String categoria = categoriaField.getText();

        ProdutoModel produto = null;
        switch (categoria) {
            case "Alimenticio":
                produto = new ProdutoAlimenticio(nome, descricao, precoCusto, precoVenda, quantidadeEstoque, categoria);
                break;
            case "Eletronico":
                produto = new ProdutoEletronico(nome, descricao, precoCusto, precoVenda, quantidadeEstoque, categoria);
                break;
            case "Limpeza":
                produto = new ProdutoLimpeza(nome, descricao, precoCusto, precoVenda, quantidadeEstoque, categoria);
                break;
        }

        if (produto != null) {
            produtoController.cadastrarProduto(produto);
            resultArea.setText("Produto adicionado com sucesso!");
        } else {
            resultArea.setText("Categoria inválida!");
        }
    }

    private void atualizarProduto() {
        int id = Integer.parseInt(idField.getText());
        ProdutoModel produto = produtoController.consultarProdutoPorId(id);
        if (produto != null) {
            produto.setNome(nomeField.getText());
            produto.setDescricao(descricaoField.getText());
            produto.setPrecoCusto(Double.parseDouble(precoCustoField.getText()));
            produto.setPrecoVenda(Double.parseDouble(precoVendaField.getText()));
            produto.setQuantidadeEstoque(Integer.parseInt(quantidadeEstoqueField.getText()));
            produto.setCategoria(categoriaField.getText());

            produtoController.atualizarProduto(produto);
            resultArea.setText("Produto atualizado com sucesso!");
        } else {
            resultArea.setText("Produto não encontrado!");
        }
    }

    private void removerProduto() {
        int id = Integer.parseInt(idField.getText());
        produtoController.removerProduto(id);
        resultArea.setText("Produto removido com sucesso!");
    }

    private void consultarProduto() {
        int id = Integer.parseInt(idField.getText());
        ProdutoModel produto = produtoController.consultarProdutoPorId(id);
        if (produto != null) {
            resultArea.setText("Produto encontrado: \n" + produto.toString());
        } else {
            resultArea.setText("Produto não encontrado!");
        }
    }

    public static void main(String[] args) {
        new ProdutoView();
    }
}
