package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.ServicoDAO;
import dao.ServicoDAOImpl;
import model.Servicomodel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Telacadastroservico extends JFrame {
    private JPanel contentPane;
    private JTextField tfNomeServico;
    private JTextField tfDescricaoServico;
    private JTextField tfPreco;
    private JTable table;
    private DefaultTableModel tableModel;
    private ServicoDAO servicoDAO = new ServicoDAOImpl();
    private Servicomodel selectedServico;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Telacadastroservico frame = new Telacadastroservico();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Telacadastroservico() {
        setTitle("Cadastro de Serviços");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNomeServico = new JLabel("Nome do Serviço:");
        lblNomeServico.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNomeServico.setBounds(39, 30, 150, 20);
        contentPane.add(lblNomeServico);

        tfNomeServico = new JTextField();
        tfNomeServico.setBounds(39, 50, 200, 20);
        contentPane.add(tfNomeServico);
        tfNomeServico.setColumns(10);

        JLabel lblDescricaoServico = new JLabel("Descrição do Serviço:");
        lblDescricaoServico.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDescricaoServico.setBounds(39, 80, 150, 20);
        contentPane.add(lblDescricaoServico);

        tfDescricaoServico = new JTextField();
        tfDescricaoServico.setBounds(39, 100, 300, 20);
        contentPane.add(tfDescricaoServico);
        tfDescricaoServico.setColumns(10);

        JLabel lblPreco = new JLabel("Preço:");
        lblPreco.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPreco.setBounds(39, 130, 45, 20);
        contentPane.add(lblPreco);

        tfPreco = new JTextField();
        tfPreco.setBounds(39, 150, 100, 20);
        contentPane.add(tfPreco);
        tfPreco.setColumns(10);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(39, 180, 100, 23);
        btnSalvar.addActionListener(e -> {
            if (selectedServico == null) {
                adicionarServico();
            } else {
                atualizarServico();
            }
        });
        contentPane.add(btnSalvar);

        JButton btnListar = new JButton("Listar");
        btnListar.setBounds(150, 180, 100, 23);
        btnListar.addActionListener(e -> listarServicos());
        contentPane.add(btnListar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(39, 220, 700, 300);
        contentPane.add(scrollPane);

        tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] {"ID", "Nome do Serviço", "Descrição", "Preço"}
        );

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(table);

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                int selectedRow = table.getSelectedRow();
                selectedServico = new Servicomodel(
                    (Integer) tableModel.getValueAt(selectedRow, 0),
                    (String) tableModel.getValueAt(selectedRow, 1),
                    (String) tableModel.getValueAt(selectedRow, 2),
                    (Double) tableModel.getValueAt(selectedRow, 3)
                );
                preencherCampos(selectedServico);
            }
        });

        listarServicos();
    }

    private void adicionarServico() {
        try {
            String nomeServico = tfNomeServico.getText();
            String descricaoServico = tfDescricaoServico.getText();
            Double preco = Double.parseDouble(tfPreco.getText());

            Servicomodel servico = new Servicomodel(nomeServico, descricaoServico, preco);
            boolean sucesso = servicoDAO.adicionarServico(servico);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Serviço adicionado com sucesso!");
                listarServicos();
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao adicionar serviço.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar serviço: preço inválido.");
        }
    }

    private void atualizarServico() {
        try {
            String nomeServico = tfNomeServico.getText();
            String descricaoServico = tfDescricaoServico.getText();
            Double preco = Double.parseDouble(tfPreco.getText());

            selectedServico.setNomeServico(nomeServico);
            selectedServico.setDescricaoServico(descricaoServico);
            selectedServico.setPreco(preco);

            boolean sucesso = servicoDAO.atualizarServico(selectedServico.getIdServico(), selectedServico);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Serviço atualizado com sucesso!");
                listarServicos();
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar serviço.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar serviço: preço inválido.");
        }
    }

    private void listarServicos() {
        List<Servicomodel> servicos = servicoDAO.listarServicos();
        tableModel.setRowCount(0);
        for (Servicomodel servico : servicos) {
            tableModel.addRow(new Object[]{
                servico.getIdServico(),
                servico.getNomeServico(),
                servico.getDescricaoServico(),
                servico.getPreco()
            });
        }
    }

    private void preencherCampos(Servicomodel servico) {
        tfNomeServico.setText(servico.getNomeServico());
        tfDescricaoServico.setText(servico.getDescricaoServico());
        tfPreco.setText(String.valueOf(servico.getPreco()));
    }

    private void limparCampos() {
        tfNomeServico.setText("");
        tfDescricaoServico.setText("");
        tfPreco.setText("");
        selectedServico = null;
    }
}
