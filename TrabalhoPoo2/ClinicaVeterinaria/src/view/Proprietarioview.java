package view;

import dao.ProprietarioDAOimpl;
import model.Proprietariomodel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Proprietarioview extends JFrame {
    private JTextField tfNome;
    private JTextField tfEmail;
    private JTextField tfTelefone;
    private JTextField tfEndereco;
    private JTextField tfIdProprietario; 
    private JTable table;
    private ProprietarioDAOimpl proprietarioDAO;

    public Proprietarioview() {
        proprietarioDAO = new ProprietarioDAOimpl();
        setTitle("Gerenciamento de Proprietários");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        getContentPane().setLayout(null);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(10, 10, 80, 25);
        getContentPane().add(lblNome);

        tfNome = new JTextField();
        tfNome.setBounds(100, 10, 160, 25);
        getContentPane().add(tfNome);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(10, 45, 80, 25);
        getContentPane().add(lblEmail);

        tfEmail = new JTextField();
        tfEmail.setBounds(100, 45, 160, 25);
        getContentPane().add(tfEmail);

        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(10, 80, 80, 25);
        getContentPane().add(lblTelefone);

        tfTelefone = new JTextField();
        tfTelefone.setBounds(100, 80, 160, 25);
        getContentPane().add(tfTelefone);

        JLabel lblEndereco = new JLabel("Endereço:");
        lblEndereco.setBounds(10, 115, 80, 25);
        getContentPane().add(lblEndereco);

        tfEndereco = new JTextField();
        tfEndereco.setBounds(100, 115, 160, 25);
        getContentPane().add(tfEndereco);

        JLabel lblIdProprietario = new JLabel("ID Proprietário:");
        lblIdProprietario.setBounds(10, 150, 100, 25);
        getContentPane().add(lblIdProprietario);

        tfIdProprietario = new JTextField();
        tfIdProprietario.setBounds(120, 150, 140, 25);
        getContentPane().add(tfIdProprietario);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(10, 185, 100, 25);
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarProprietario();
            }
        });
        getContentPane().add(btnSalvar);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setBounds(120, 185, 100, 25);
        btnAtualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atualizarProprietario();
            }
        });
        getContentPane().add(btnAtualizar);

        JButton btnListar = new JButton("Listar");
        btnListar.setBounds(230, 185, 100, 25);
        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarProprietarios();
            }
        });
        getContentPane().add(btnListar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 220, 760, 330);
        getContentPane().add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Nome", "Email", "Telefone", "Endereço"}
        ));
        scrollPane.setViewportView(table);

        listarProprietarios();
    }

    private void adicionarProprietario() {
        String nome = tfNome.getText();
        String email = tfEmail.getText();
        String telefone = tfTelefone.getText();
        String endereco = tfEndereco.getText();

        Proprietariomodel proprietario = new Proprietariomodel(nome, email, telefone, endereco);
        proprietarioDAO.adicionarProprietario(proprietario);
        listarProprietarios();
    }

    private void atualizarProprietario() {
        try {
            int id = Integer.parseInt(tfIdProprietario.getText());
            String nome = tfNome.getText();
            String email = tfEmail.getText();
            String telefone = tfTelefone.getText();
            String endereco = tfEndereco.getText();

            Proprietariomodel proprietario = new Proprietariomodel(id, nome, email, telefone, endereco);
            proprietarioDAO.atualizarProprietario(proprietario);
            listarProprietarios();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarProprietarios() {
        List<Proprietariomodel> proprietarios = proprietarioDAO.listarProprietarios("");
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (Proprietariomodel proprietario : proprietarios) {
            model.addRow(new Object[]{proprietario.getIdProprietario(), proprietario.getNomeProprietario(), proprietario.getEmail(), proprietario.getTelefone(), proprietario.getEndereco()});
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Proprietarioview frame = new Proprietarioview();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
