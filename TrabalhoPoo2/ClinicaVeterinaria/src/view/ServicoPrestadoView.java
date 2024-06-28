package view;


import javax.swing.*;
import javax.swing.text.MaskFormatter;

import dao.ServicoPrestadoDAO;
import dao.ServicoPrestadoDAOImpl;
import model.ServicoPrestadoModel;
import util.ConexaoBD;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class ServicoPrestadoView extends JFrame {
    private JTextField idServicoPrestadoField;
    private JTextField observacoesField;
    private JTextField valorField;
    private JFormattedTextField dataServicoPrestadoField;
    private JTextField idServicoField;
    private JTextField idAnimalField;
    private JButton adicionarButton;
    private JButton atualizarButton;
    private JButton removerButton;
    private JButton buscarButton;

    private ServicoPrestadoDAO servicoPrestadoDAO;

    public ServicoPrestadoView() {
        servicoPrestadoDAO = new ServicoPrestadoDAOImpl();

        setTitle("Gerenciamento de Serviços Prestados");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));

        panel.add(new JLabel("ID Serviço Prestado:"));
        idServicoPrestadoField = new JTextField();
        panel.add(idServicoPrestadoField);

        panel.add(new JLabel("Observações:"));
        observacoesField = new JTextField();
        panel.add(observacoesField);

        panel.add(new JLabel("Valor:"));
        valorField = new JTextField();
        panel.add(valorField);

        panel.add(new JLabel("Data do Serviço (DD/MM/AAAA):"));
        try {
            MaskFormatter dateMask = new MaskFormatter("##/##/####");
            dateMask.setPlaceholderCharacter('_');
            dataServicoPrestadoField = new JFormattedTextField(dateMask);
            panel.add(dataServicoPrestadoField);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        panel.add(new JLabel("ID Serviço:"));
        idServicoField = new JTextField();
        panel.add(idServicoField);

        panel.add(new JLabel("ID Animal:"));
        idAnimalField = new JTextField();
        panel.add(idAnimalField);

        adicionarButton = new JButton("Adicionar");
        atualizarButton = new JButton("Atualizar");
        removerButton = new JButton("Remover");
        buscarButton = new JButton("Buscar");

        panel.add(adicionarButton);
        panel.add(atualizarButton);
        panel.add(removerButton);
        panel.add(buscarButton);

        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarServico();
            }
        });

        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarServico();
            }
        });

        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerServico();
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarServico();
            }
        });

        add(panel);
    }

    private void adicionarServico() {
        ServicoPrestadoModel servico = new ServicoPrestadoModel(
            Integer.parseInt(idServicoPrestadoField.getText()),
            observacoesField.getText(),
            Double.parseDouble(valorField.getText()),
            dataServicoPrestadoField.getText(),
            Integer.parseInt(idServicoField.getText()),
            Integer.parseInt(idAnimalField.getText())
        );

        servicoPrestadoDAO.adicionarServicoPrestado(servico);
        JOptionPane.showMessageDialog(this, "Serviço adicionado com sucesso!");
    }

    private void atualizarServico() {
        ServicoPrestadoModel servico = new ServicoPrestadoModel(
            Integer.parseInt(idServicoPrestadoField.getText()),
            observacoesField.getText(),
            Double.parseDouble(valorField.getText()),
            dataServicoPrestadoField.getText(),
            Integer.parseInt(idServicoField.getText()),
            Integer.parseInt(idAnimalField.getText())
        );

        servicoPrestadoDAO.atualizarServicoPrestado(servico);
        JOptionPane.showMessageDialog(this, "Serviço atualizado com sucesso!");
    }

    private void removerServico() {
        int idServicoPrestado = Integer.parseInt(idServicoPrestadoField.getText());
        servicoPrestadoDAO.removerServicoPrestado(idServicoPrestado);
        JOptionPane.showMessageDialog(this, "Serviço removido com sucesso!");
    }

    private void buscarServico() {
        int idServicoPrestado = Integer.parseInt(idServicoPrestadoField.getText());
        ServicoPrestadoModel servico = servicoPrestadoDAO.buscarServicoPrestadoPorId(idServicoPrestado);

        if (servico != null) {
            observacoesField.setText(servico.getObservacoes());
            valorField.setText(String.valueOf(servico.getValor()));
            dataServicoPrestadoField.setText(servico.getDataServicoPrestado());
            idServicoField.setText(String.valueOf(servico.getIdServico()));
            idAnimalField.setText(String.valueOf(servico.getIdAnimal()));
        } else {
            JOptionPane.showMessageDialog(this, "Serviço não encontrado!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ServicoPrestadoView().setVisible(true);
            }
        });
    }
}
