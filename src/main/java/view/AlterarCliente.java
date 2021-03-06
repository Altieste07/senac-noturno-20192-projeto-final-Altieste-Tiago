package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.ControllerCliente;
import model.entity.Cliente;
import net.miginfocom.swing.MigLayout;

public class AlterarCliente extends JFrame {

	private JPanel contentPane;

	protected static final String SEXO_MASCULINO = "M";
	protected static final String SEXO_FEMININO = "F";
	private JTextField nomecliente;
	private JFormattedTextField txtCpf;
	private JFormattedTextField formattedTel;
	JFormattedTextField formattedNascimento;
	private JRadioButton rdbtnF;
	private JRadioButton rdbtnM;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlterarCliente frame = new AlterarCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AlterarCliente() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[122px,fill][24px,fill][45px][12px][235px]",
				"[16px][26px][26px][23px][26px][26px][29px]"));

		nomecliente = new JTextField();
		add(nomecliente, "cell 2 1 3 1,alignx left,aligny top");
		nomecliente.setColumns(10);

		JLabel lblNome = new JLabel("Nome:");
		add(lblNome, "cell 0 1,alignx center,aligny center");

		JLabel lblCpf = new JLabel("Cpf:");
		add(lblCpf, "cell 0 2,alignx center,aligny center");

		MaskFormatter mascaraCpf;
		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			txtCpf = new JFormattedTextField(mascaraCpf);
		} catch (ParseException e) {
		}
		add(txtCpf, "cell 2 2 3 1,alignx left,aligny top");

		JLabel lblTelefone = new JLabel("Telefone:");
		add(lblTelefone, "cell 0 5,alignx center,aligny center");

		JLabel lblCadastroDeCliente = new JLabel("Alterar Cliente");
		lblCadastroDeCliente.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblCadastroDeCliente.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblCadastroDeCliente, "cell 0 0 5 1,grow");

		JButton btnSalvarCliente = new JButton("Salvar");
		btnSalvarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControllerCliente cliController = new ControllerCliente();
				Cliente cliente = new Cliente();
				cliente.setNome(nomecliente.getText());
				cliente.setCpf(txtCpf.getText());
				if (rdbtnM.isSelected()) {
					cliente.setSexo("Masculino");
				}
				if (rdbtnF.isSelected()) {
					cliente.setSexo("Feminino");
				}
				cliente.setCelular(formattedTel.getText());
				String nascimentoDigitado = formattedNascimento.getText();

				cliController.salvar(cliente);
			}
		});
		add(btnSalvarCliente, "cell 2 6 3 1,alignx left,aligny top");

		JLabel lblSexo = new JLabel("Sexo:");
		add(lblSexo, "cell 0 3,alignx center,aligny center");

		rdbtnF = new JRadioButton("F");
		add(rdbtnF, "cell 2 3,growx,aligny top");

		rdbtnM = new JRadioButton("M");
		add(rdbtnM, "cell 4 3,alignx left,aligny top");

		ButtonGroup radioGroupSexo = new ButtonGroup();
		radioGroupSexo.add(rdbtnM);
		radioGroupSexo.add(rdbtnF);

		MaskFormatter mascaraTel1;
		try {
			mascaraTel1 = new MaskFormatter("(##) #####-####");
			formattedTel = new JFormattedTextField(mascaraTel1);
		} catch (ParseException e) {
		}
		add(formattedTel, "cell 2 5 3 1,alignx left,aligny top");

		JLabel lblNascimento = new JLabel("Nascimento:");
		add(lblNascimento, "cell 0 4,alignx right,aligny center");

		MaskFormatter mascaraNasc;
		try {
			mascaraNasc = new MaskFormatter("##/##/####");
			formattedNascimento = new JFormattedTextField(mascaraNasc);
		} catch (ParseException e) {
		}
		add(formattedNascimento, "cell 2 4 3 1,alignx left,aligny top");

	}
}
