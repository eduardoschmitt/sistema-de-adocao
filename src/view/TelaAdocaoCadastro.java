package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.AdocaoDao;
import dao.AnimalDao;
import dao.DoacaoDao;
import dao.EstadoDao;
import dao.PessoaDao;
import model.Adocao;
import model.Animal;
import model.Doacao;
import model.Estado;
import model.Pessoa;

public class TelaAdocaoCadastro extends JDialog {
	JLabel lblAnimal;
	JComboBox<Animal> cbxAnimal;
	JLabel lblPessoa;
	JComboBox<Pessoa> cbxPessoa;

	JButton salvar;
	JPanel tela;
	Integer id;

	public TelaAdocaoCadastro(Integer id, JPanel tela) {
		this.id = id;
		this.tela = tela;
		setLayout(null);
		this.setBounds(200, 100, 500, 500);
		montaComponentes();
		this.setVisible(true);
	}

	public void montaComponentes() {

		lblAnimal = new JLabel("Animal");
		lblAnimal.setBounds(10, 10, 40, 20);
		this.add(lblAnimal);

		List<Animal> animais = new ArrayList<>();
		try {
			animais = AnimalDao.getNaoAdotados();
		}catch(Exception e) {
			
		}
		
		cbxAnimal = new JComboBox<Animal>(animais.toArray(new Animal[0]));
		cbxAnimal.setBounds(70, 10, 150, 20);
		
		this.add(cbxAnimal);

		lblPessoa = new JLabel("Pessoa");
		lblPessoa.setBounds(10, 40, 40, 20);
		this.add(lblPessoa);
		
		List<Pessoa> pessoas = new ArrayList<>();
		try {
			pessoas = PessoaDao.get();
		}catch(Exception e) {
			
		}
		
		cbxPessoa = new JComboBox<Pessoa>(pessoas.toArray(new Pessoa[0]));
		cbxPessoa.setBounds(70, 40, 150, 20);
		
		this.add(cbxPessoa);
		

		salvar = new JButton(id == null ? "Inserir" : "Alterar");
		salvar.setBounds(10, 70, 90, 20);
		salvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Calendar calendar = Calendar.getInstance();
				Adocao adocao = new Adocao(0, (Animal) cbxAnimal.getSelectedItem(), (Pessoa)cbxPessoa.getSelectedItem(), new Date(calendar.getTimeInMillis()));
				try {
					AdocaoDao.insert(adocao);

					setVisible(false);
				}catch(Exception error) {
						
				}
			}
		});
		this.add(salvar);
	}
	

}
