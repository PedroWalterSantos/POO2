package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import dao.ProprietarioDAO;
import dao.ProprietarioDAOimpl;
import model.Proprietariomodel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Checarpropview extends JFrame {
    private JPanel contentPane;
    private JTextField tfFiltro;
    private JTable table;
    private DefaultTableModel tableModel;
    private ProprietarioDAO proprietarioDAO = new ProprietarioDAOimpl();
    private Telacadastroanimal parent;

    public Checarpropview(Telacadastroanimal parent) {
        this.parent = parent;
        setTitle("Proprietários");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblFiltro = new JLabel("Filtrar por Nome:");
        lblFiltro.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblFiltro.setBounds(10, 11, 120, 20);
        contentPane.add(lblFiltro);

        tfFiltro = new JTextField();
        tfFiltro.setBounds(130, 11, 120, 20);
        contentPane.add(tfFiltro);
        tfFiltro.setColumns(10);

        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBounds(260, 10, 80, 23);
        btnFiltrar.addActionListener(e -> atualizarTabela(tfFiltro.getText()));
        contentPane.add(btnFiltrar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 50, 560, 300);
        contentPane.add(scrollPane);

        tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] {"ID", "Nome", "Email", "Telefone", "Endereço"}
        );

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(table);

        atualizarTabela("");

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow >= 0) {
                        int idProprietario = (int) tableModel.getValueAt(selectedRow, 0);
                        parent.setIdProprietario(idProprietario);
                        dispose();
                    }
                }
            }
        });
    }

    private void atualizarTabela(String filtro) {
        List<Proprietariomodel> proprietarios = proprietarioDAO.listarProprietarios(filtro);
        tableModel.setRowCount(0); // Limpar tabela
        for (Proprietariomodel proprietario : proprietarios) {
            tableModel.addRow(new Object[] {
                proprietario.getIdProprietario(),
                proprietario.getNomeProprietario(),
                proprietario.getEmail(),
                proprietario.getTelefone(),
                proprietario.getEndereco()
            });
        }
    }
}
