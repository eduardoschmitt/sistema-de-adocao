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
import model.Bairro;
import model.Cidade;
import model.Estado;

public class TelaBairroCadastro extends JDialog {
	JLabel lblNome;
	JTextField txtNome;
	JLabel lblEstado;
	JComboBox<Estado> cbxEstado;
	JLabel lblCidade;
	JComboBox<Cidade> cbxCidade;
	JButton salvar;
	JPanel tela;
	Integer id;

	public TelaBairroCadastro(Integer id, JPanel tela) {
		this.id = id;
		this.tela = tela;
		setLayout(null);
		this.setBounds(200, 100, 500, 500);
		montaComponentes();
		this.setVisible(true);
	}

	public void montaComponentes() {

		lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 40, 40, 20);
		this.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(70, 40, 150, 20);
		this.add(txtNome);
		
		lblEstado = new JLabel("Estado");
		lblEstado.setBounds(10, 70, 40, 20);
		this.add(lblEstado);

		List<Estado> estados = new ArrayList<>();
		try {
			estados = EstadoDao.get();
		}catch(Exception e) {
			
		}
		
		cbxEstado = new JComboBox<Estado>(estados.toArray(new Estado[0]));
		cbxEstado.setBounds(70, 70, 150, 20);
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
		lblCidade.setBounds(10, 100, 40, 20);
		this.add(lblCidade);
		
		List<Cidade> cidades = new ArrayList<>();
		try {
			cidades = CidadeDao.get(((Estado)cbxEstado.getSelectedItem()).getId());
		}catch(Exception e) {
			
		}
		
		cbxCidade = new JComboBox<Cidade>(cidades.toArray(new Cidade[0]));
		cbxCidade.setBounds(70, 100, 150, 20);
		this.add(cbxCidade);

		salvar = new JButton(id == null ? "Inserir" : "Alterar");
		salvar.setBounds(10, 130, 90, 20);
		salvar.addActionListener(salvarClick());
		this.add(salvar);
	}
	
	public ActionListener salvarClick() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtNome.getText() != null && !txtNome.getText().equals("")) {
					Estado estado = (Estado) cbxEstado.getSelectedItem();
					Cidade cidade = (Cidade) cbxCidade.getSelectedItem();
					Bairro bairro = new Bairro(0, txtNome.getText(), cidade, estado);
					try {
						if (id != null) {
							BairroDao.update(id, bairro);
						} else {
							BairroDao.insert(bairro);
						}
						setVisible(false);
					}catch(Exception error) {		
					}
				}
			}
		};
	}

}
