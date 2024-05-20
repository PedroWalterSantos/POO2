package view;

import controller.ClienteController;
import model.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ClienteView extends JFrame {
    private ClienteController ClienteController;
    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField dataNascimentoField;
    private JTextField emailField;
    private JTextArea resultArea;

    public ClienteView() {
        ClienteController = new ClienteController();
        initialize();
    }

    private void initialize() {
        setTitle("Gerenciamento de Clientes");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 2));

        add(new JLabel("Nome:"));
        nomeField = new JTextField();
        add(nomeField);

        add(new JLabel("CPF:"));
        cpfField = new JTextField();
        add(cpfField);

        add(new JLabel("Data de Nascimento (AAAA-MM-DD):"));
        dataNascimentoField = new JTextField();
        add(dataNascimentoField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        JButton addButton = new JButton("Adicionar Cliente");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarCliente();
            }
        });
        add(addButton);

        JButton updateButton = new JButton("Atualizar Cliente");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarCliente();
            }
        });
        add(updateButton);

        JButton removeButton = new JButton("Remover Cliente");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerCliente();
            }
        });
        add(removeButton);

        JButton searchButton = new JButton("Consultar Cliente");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarCliente();
            }
        });
        add(searchButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea));

        setVisible(true);
    }

    private void adicionarCliente() {
        String nome = nomeField.getText();
        String cpf = cpfField.getText();
        LocalDate dataNascimento = LocalDate.parse(dataNascimentoField.getText(), DateTimeFormatter.ISO_DATE);
        String email = emailField.getText();

        Cliente cliente = new Cliente(nome, cpf, dataNascimento, email);
        ClienteController.cadastrarCliente(cliente);
        resultArea.setText("Cliente adicionado com sucesso!");
    }

    private void atualizarCliente() {
        String cpf = cpfField.getText();
        Cliente cliente = ClienteController.consultarClientePorCpf(cpf);
        if (cliente != null) {
            cliente.setNome(nomeField.getText());
            cliente.setDataNascimento(LocalDate.parse(dataNascimentoField.getText(), DateTimeFormatter.ISO_DATE));
            cliente.setEmail(emailField.getText());

            ClienteController.atualizarCliente(cliente);
            resultArea.setText("Cliente atualizado com sucesso!");
        } else {
            resultArea.setText("Cliente não encontrado!");
        }
    }

    private void removerCliente() {
        String cpf = cpfField.getText();
        ClienteController.removerCliente(cpf);
        resultArea.setText("Cliente removido com sucesso!");
    }

    private void consultarCliente() {
        String cpf = cpfField.getText();
        Cliente cliente = ClienteController.consultarClientePorCpf(cpf);
        if (cliente != null) {
            resultArea.setText("Cliente encontrado: \n" + cliente.toString());
        } else {
            resultArea.setText("Cliente não encontrado!");
        }
    }

    public static void main(String[] args) {
        new ClienteView();
    }
}
