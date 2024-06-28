package view;

import model.Loginmodel;

import dao.LoginDAOImpl;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;


import java.awt.Font;


import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;


public class Inicialview extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	LoginDAOImpl logindao = new LoginDAOImpl();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicialview frame = new Inicialview();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	
	}	
	

	  
	public Inicialview() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAnimal = new JButton("Animais");
		btnAnimal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Telacadastroanimal frame = new Telacadastroanimal();
				frame.setVisible(true);
				
			}
			
		});
		btnAnimal.setBounds(10, 75, 115, 44);
		contentPane.add(btnAnimal);
		
		JLabel lblNewLabel = new JLabel("Tela Inicial");
		lblNewLabel.setBounds(159, 11, 116, 23);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 19));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(Inicialview.class.getResource("/img/download.jpg")));
		lblNewLabel_3.setBackground(Color.RED);
		lblNewLabel_3.setToolTipText("");
		lblNewLabel_3.setBounds(251, 75, 137, 177);
		contentPane.add(lblNewLabel_3);
		
		JButton btnProprietarios = new JButton("Proprietarios");
		btnProprietarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Proprietarioview frame = new Proprietarioview();
				frame.setVisible(true);
			}
		});
		btnProprietarios.setBounds(10, 130, 115, 44);
		contentPane.add(btnProprietarios);
		
		JButton btnServios = new JButton("Serviços");
		btnServios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ServicoView frame = new ServicoView();
				frame.setVisible(true);
			}
		});
		btnServios.setBounds(135, 75, 106, 44);
		contentPane.add(btnServios);
		
		JButton btnRelatorios = new JButton("Relatorios");
		btnRelatorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RelatorioPDFView frame = new RelatorioPDFView();
				frame.setVisible(true);
				
			}
		});
		btnRelatorios.setBounds(135, 130, 106, 44);
		contentPane.add(btnRelatorios);
		
		JButton btnAnimal_1 = new JButton("Serviços Prestados");
		btnAnimal_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ServicoPrestadoView frame = new ServicoPrestadoView();
				frame.setVisible(true);
			}
		});
		btnAnimal_1.setBounds(64, 185, 137, 44);
		contentPane.add(btnAnimal_1);
		

	}
}