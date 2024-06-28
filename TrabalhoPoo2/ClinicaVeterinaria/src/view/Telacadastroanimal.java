package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import dao.AnimalDAO;
import dao.AnimalDAOImpl;
import model.Animalmodel;
import java.awt.*;
import java.util.List;

public class Telacadastroanimal extends JFrame {
    private JPanel contentPane;
    private JTextField tfNome;
    private JComboBox<String> cbIdade;
    private JComboBox<String> cbEspecie;
    private JComboBox<String> cbRaca;
    private JTextField tfPeso;
    private JTextField tfIdProprietario;
    private JTable table;
    private DefaultTableModel tableModel;
    private AnimalDAO animalDAO = new AnimalDAOImpl();
    private String[] racasGato = {"Siamês", "Persa", "Maine Coon", "Sphynx"};
    private String[] racasCachorro = {"Labrador", "Poodle", "Bulldog", "Beagle"};
    private Animalmodel selectedAnimal;
    private ButtonGroup grupoSexo;
	private JComboBox cbIdade_1;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Telacadastroanimal frame = new Telacadastroanimal();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Telacadastroanimal() {
        setTitle("Gerenciamento de Animais");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNome = new JLabel("Nome :");
        lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNome.setBounds(39, 30, 45, 20);
        contentPane.add(lblNome);

        tfNome = new JTextField();
        tfNome.setBounds(39, 50, 112, 20);
        contentPane.add(tfNome);
        tfNome.setColumns(10);

        JLabel lblIdade = new JLabel("Idade :");
        lblIdade.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblIdade.setBounds(173, 30, 45, 20);
        contentPane.add(lblIdade);

        cbIdade = new JComboBox<>();
        cbIdade.setModel(new DefaultComboBoxModel<>(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"}));
        cbIdade.setBounds(173, 50, 77, 20);
        contentPane.add(cbIdade);

        JLabel lblEspecie = new JLabel("Espécie :");
        lblEspecie.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblEspecie.setBounds(288, 30, 64, 20);
        contentPane.add(lblEspecie);

        cbEspecie = new JComboBox<>();
        cbEspecie.setModel(new DefaultComboBoxModel<>(new String[]{"Gato", "Cachorro"}));
        cbEspecie.setBounds(288, 50, 86, 20);
        cbEspecie.addActionListener(e -> atualizarRacas());
        contentPane.add(cbEspecie);

        JLabel lblRaca = new JLabel("Raça :");
        lblRaca.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblRaca.setBounds(401, 30, 45, 20);
        contentPane.add(lblRaca);

        cbRaca = new JComboBox<>();
        cbRaca.setModel(new DefaultComboBoxModel<>(racasGato));
        cbRaca.setBounds(401, 50, 112, 20);
        contentPane.add(cbRaca);

        JLabel lblPeso = new JLabel("Peso :");
        lblPeso.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPeso.setBounds(39, 90, 45, 20);
        contentPane.add(lblPeso);

        tfPeso = new JTextField();
        tfPeso.setBounds(39, 110, 45, 20);
        contentPane.add(tfPeso);
        tfPeso.setColumns(10);

        JLabel lblSexo = new JLabel("Sexo :");
        lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblSexo.setBounds(173, 90, 45, 20);
        contentPane.add(lblSexo);

        cbIdade_1 = new JComboBox<>();
        cbIdade_1.setModel(new DefaultComboBoxModel(new String[]{"M", "F"}));
        cbIdade_1.setSelectedIndex(0);
        cbIdade_1.setBounds(173, 109, 45, 20);
        contentPane.add(cbIdade_1);

        grupoSexo = new ButtonGroup();

        JLabel lblIdProprietario = new JLabel("ID Proprietário:");
        lblIdProprietario.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblIdProprietario.setBounds(39, 150, 112, 20);
        contentPane.add(lblIdProprietario);

        tfIdProprietario = new JTextField();
        tfIdProprietario.setEnabled(false);
        tfIdProprietario.setBounds(39, 170, 123, 20);
        contentPane.add(tfIdProprietario);
        tfIdProprietario.setColumns(10);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(39, 210, 112, 23);
        btnSalvar.addActionListener(e -> {
            if (selectedAnimal == null) {
                adicionarAnimal();
            } else {
                atualizarAnimal();
            }
        });
        contentPane.add(btnSalvar);

        JButton btnChecarProprietario = new JButton("Checar Proprietário");
        btnChecarProprietario.setBounds(200, 210, 150, 23);
        btnChecarProprietario.addActionListener(e -> abrirJanelaProprietarios());
        contentPane.add(btnChecarProprietario);

        JButton btnListar = new JButton("Listar");
        btnListar.setBounds(400, 210, 150, 23);
        btnListar.addActionListener(e -> listarAnimais());
        contentPane.add(btnListar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(39, 250, 700, 300);
        contentPane.add(scrollPane);

        tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Nome", "Idade", "Sexo", "Raça", "Espécie", "Peso", "ID Proprietário"}
        );

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(table);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                    int selectedRow = table.getSelectedRow();
                    selectedAnimal = new Animalmodel(
                            (String) tableModel.getValueAt(selectedRow, 1),
                            (Integer) tableModel.getValueAt(selectedRow, 2),
                            (String) tableModel.getValueAt(selectedRow, 3),
                            (String) tableModel.getValueAt(selectedRow, 4),
                            (String) tableModel.getValueAt(selectedRow, 5),
                            (Double) tableModel.getValueAt(selectedRow, 6),
                            (Integer) tableModel.getValueAt(selectedRow, 7)
                    );
                    preencherCampos(selectedAnimal);
                }
            }
        });
    }

    private void preencherCampos(Animalmodel animal) {
        tfNome.setText(animal.getNome());
        cbIdade.setSelectedItem(String.valueOf(animal.getIdade()));
        cbEspecie.setSelectedItem(animal.getEspecie());
        cbRaca.setSelectedItem(animal.getRaca());
        tfPeso.setText(String.valueOf(animal.getPeso()));
        String sexo = (String) cbIdade_1.getSelectedItem();

        tfIdProprietario.setText(String.valueOf(animal.getIdProprietario()));
    }

    private void atualizarRacas() {
        String especieSelecionada = (String) cbEspecie.getSelectedItem();
        if ("Gato".equals(especieSelecionada)) {
            cbRaca.setModel(new DefaultComboBoxModel<>(racasGato));
        } else if ("Cachorro".equals(especieSelecionada)) {
            cbRaca.setModel(new DefaultComboBoxModel<>(racasCachorro));
        }
    }

    private void adicionarAnimal() {
        try {
            String nome = tfNome.getText();
            int idade = Integer.parseInt((String) cbIdade.getSelectedItem());
            String raca = (String) cbRaca.getSelectedItem();
            String especie = (String) cbEspecie.getSelectedItem();
            double peso = Double.parseDouble(tfPeso.getText());
            int idProprietario = Integer.parseInt(tfIdProprietario.getText());

            String sexo = (String) cbIdade_1.getSelectedItem();

            Animalmodel animal = new Animalmodel(nome, idade, sexo, raca, especie, peso, idProprietario);
            animalDAO.adicionarAnimal(animal);
            listarAnimais();
            limparCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar animal: " + ex.getMessage());
        }
    }

    private void atualizarAnimal() {
        try {
            String nome = tfNome.getText();
            int idade = Integer.parseInt((String) cbIdade.getSelectedItem());
            String raca = (String) cbRaca.getSelectedItem();
            String especie = (String) cbEspecie.getSelectedItem();
            double peso = Double.parseDouble(tfPeso.getText());
            int idProprietario = Integer.parseInt(tfIdProprietario.getText());

            String sexo = (String) cbIdade_1.getSelectedItem();

            Animalmodel animalAtualizado = new Animalmodel(nome, idade, sexo, raca, especie, peso, idProprietario);
            animalDAO.atualizarAnimal(selectedAnimal.getNome(), animalAtualizado);
            listarAnimais();
            limparCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar animal: " + ex.getMessage());
        }
    }

    private void listarAnimais() {
        List<Animalmodel> animais = animalDAO.listarAnimais();
        tableModel.setRowCount(0);
        for (Animalmodel animal : animais) {
            tableModel.addRow(new Object[]{
                    animal.getIdAnimal(),
                    animal.getNome(),
                    animal.getIdade(),
                    animal.getSexo(),
                    animal.getRaca(),
                    animal.getEspecie(),
                    animal.getPeso(),
                    animal.getIdProprietario()
            });
        }
    }

    private void limparCampos() {
        tfNome.setText("");
        cbIdade.setSelectedIndex(0);
        cbEspecie.setSelectedIndex(0);
        cbRaca.setSelectedIndex(0);
        tfPeso.setText("");
        grupoSexo.clearSelection();
        tfIdProprietario.setText("");
        selectedAnimal = null;
    }

    private void abrirJanelaProprietarios() {
        Checarpropview checarProprietarioView = new Checarpropview(this);
        checarProprietarioView.setVisible(true);
    }

    public void setIdProprietario(int idProprietario) {
        tfIdProprietario.setText(String.valueOf(idProprietario));
    }
}
