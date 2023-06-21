package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.BairroDao;
import dao.CidadeDao;
import dao.EstadoDao;
import dao.PessoaDao;
import model.Bairro;
import model.Cidade;
import model.Estado;
import model.Pessoa;

public class TelaPessoaCadastro extends JDialog {
	JLabel lblNome;
	JTextField txtNome;
	JLabel lblTelefone;
	JTextField txtTelefone;
	JLabel lblEmail;
	JTextField txtEmail;
	JLabel lblEstado;
	JComboBox<Estado> cbxEstado;
	JLabel lblCidade;
	JComboBox<Cidade> cbxCidade;
	JLabel lblBairro;
	JComboBox<Bairro> cbxBairro;
	JLabel lblLogradouro;
	JTextField txtLogradouro;
	JLabel lblComplemento;
	JTextField txtComplemento;
	JButton salvar;
	JPanel tela;
	Integer id;

	public TelaPessoaCadastro(Integer id, JPanel tela) {
		this.id = id;
		this.tela = tela;
		setLayout(null);
		this.setBounds(200, 100, 500, 500);
		montaComponentes();
		this.setVisible(true);
	}

	public void montaComponentes() {

		lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 40, 80, 20);
		this.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(100, 40, 150, 20);
		this.add(txtNome);
		
		lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(10, 70, 80, 20);
		this.add(lblTelefone);

		txtTelefone = new JTextField();
		txtTelefone.setBounds(100, 70, 150, 20);
		this.add(txtTelefone);
		
		lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 100, 80, 20);
		this.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(100, 100, 150, 20);
		this.add(txtEmail);
		
		lblEstado = new JLabel("Estado");
		lblEstado.setBounds(10, 130, 80, 20);
		this.add(lblEstado);

		List<Estado> estados = new ArrayList<>();
		try {
			estados = EstadoDao.get();
		}catch(Exception e) {
			
		}
		
		cbxEstado = new JComboBox<Estado>(estados.toArray(new Estado[0]));
		cbxEstado.setBounds(100, 130, 150, 20);
		cbxEstado.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cbxEstado.getSelectedItem()!=null) {
					List<Cidade> cidades = new ArrayList<>();
					try {
						cidades = CidadeDao.get(((Estado)cbxEstado.getSelectedItem()).getId());
						cbxCidade.removeAllItems();
						for(Cidade cidade:cidades) {
							cbxCidade.addItem(cidade);
						}
					}catch(Exception error) {
						
					}
				}
				
			}
		});
		this.add(cbxEstado);
		
		lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(10, 160, 80, 20);
		this.add(lblCidade);
		
		List<Cidade> cidades = new ArrayList<>();
		try {
			cidades = CidadeDao.get(((Estado)cbxEstado.getSelectedItem()).getId());
		}catch(Exception e) {
			
		}
		
		cbxCidade = new JComboBox<Cidade>(cidades.toArray(new Cidade[0]));
		cbxCidade.setBounds(100, 160, 150, 20);
		cbxCidade.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cbxCidade.getSelectedItem()!=null) {
					List<Bairro> bairros = new ArrayList<>();
					try {
						bairros = BairroDao.get(((Cidade)cbxCidade.getSelectedItem()).getId());
						cbxBairro.removeAllItems();
						for(Bairro bairro:bairros) {
							cbxBairro.addItem(bairro);
						}
					}catch(Exception error) {
						
					}
				}
				
			}
		});
		this.add(cbxCidade);
		
		lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(10, 190, 80, 20);
		this.add(lblBairro);
		
		List<Bairro> bairros = new ArrayList<>();
		try {
			bairros = BairroDao.get(((Estado)cbxEstado.getSelectedItem()).getId());
		}catch(Exception e) {
			
		}
		
		cbxBairro = new JComboBox<Bairro>(bairros.toArray(new Bairro[0]));
		cbxBairro.setBounds(100, 190, 150, 20);
		this.add(cbxBairro);
		
		lblLogradouro = new JLabel("Logradouro");
		lblLogradouro.setBounds(10, 220, 80, 20);
		this.add(lblLogradouro);

		txtLogradouro = new JTextField();
		txtLogradouro.setBounds(100, 220, 150, 20);
		this.add(txtLogradouro);
		
		lblComplemento = new JLabel("Complemento");
		lblComplemento.setBounds(10, 250, 80, 20);
		this.add(lblComplemento);

		txtComplemento = new JTextField();
		txtComplemento.setBounds(100, 250, 150, 20);
		this.add(txtComplemento);

		salvar = new JButton(id == null ? "Inserir" : "Alterar");
		salvar.setBounds(10, 280, 90, 20);
		salvar.addActionListener(salvarClick());
		this.add(salvar);
	}
	
	public ActionListener salvarClick() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtNome.getText() != null && !txtNome.getText().equals("") && cbxBairro.getSelectedItem() != null) {
					Bairro bairro = (Bairro) cbxBairro.getSelectedItem();
					Pessoa pessoa = new Pessoa(0, txtNome.getText(), txtTelefone.getText(), txtEmail.getText(), txtLogradouro.getText(), txtComplemento.getText(), bairro);
					try {
						if (id != null) {
							PessoaDao.update(id, pessoa);
						} else {
							PessoaDao.insert(pessoa);
						}
						setVisible(false);
					}catch(Exception error) {		
					}
				}
			}
		};
	}

}
