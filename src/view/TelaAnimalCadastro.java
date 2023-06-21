package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.AnimalDao;
import dao.BairroDao;
import dao.CidadeDao;
import dao.CorDao;
import dao.EspeciesDao;
import dao.EstadoDao;
import dao.RacasDao;
import model.Animal;
import model.Bairro;
import model.Cidade;
import model.Cor;
import model.Especies;
import model.Estado;
import model.Racas;

public class TelaAnimalCadastro extends JDialog {
	JLabel lblNome;
	JTextField txtNome;
	JLabel lblSexo;
	JTextField txtSexo;
	JLabel lblDescricao;
	JTextField txtDescricao;
	JLabel lblCor;
	JComboBox<Cor> cbxCor;
	JLabel lblEspecie;
	JComboBox<Especies> cbxEspecie;
	JLabel lblRaca;
	JComboBox<Racas> cbxRaca;
	JButton salvar;
	JPanel tela;
	Integer id;

	public TelaAnimalCadastro(Integer id, JPanel tela) {
		this.id = id;
		this.tela = tela;
		setLayout(null);
		this.setBounds(200, 100, 500, 500);
		montaComponentes();
		this.setVisible(true);
	}

	public void montaComponentes() {

		lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 40, 70, 20);
		this.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(100, 40, 150, 20);
		this.add(txtNome);
		
		lblSexo = new JLabel("Sexo");
		lblSexo.setBounds(10, 70, 70, 20);
		this.add(lblSexo);

		txtSexo = new JTextField();
		txtSexo.setBounds(100, 70, 150, 20);
		this.add(txtSexo);
		
		lblDescricao = new JLabel("Descrição");
		lblDescricao.setBounds(10, 100, 70, 20);
		this.add(lblDescricao);

		txtDescricao = new JTextField();
		txtDescricao.setBounds(100, 100, 150, 20);
		this.add(txtDescricao);
		
		lblCor = new JLabel("Cor");
		lblCor.setBounds(10, 130, 70, 20);
		this.add(lblCor);

		List<Cor> cores = new ArrayList<>();
		try {
			cores = CorDao.get();
		}catch(Exception e) {
			
		}
		cbxCor = new JComboBox<Cor>(cores.toArray(new Cor[0]));
		cbxCor.setBounds(100, 130, 150, 20);
		this.add(cbxCor);
		
		lblEspecie = new JLabel("Espécie");
		lblEspecie.setBounds(10, 160, 70, 20);
		this.add(lblEspecie);
		
		List<Especies> especies = new ArrayList<>();
		try {
			especies = EspeciesDao.get();
		}catch(Exception e) {
			
		}
		
		cbxEspecie = new JComboBox<Especies>(especies.toArray(new Especies[0]));
		cbxEspecie.setBounds(100, 160, 150, 20);
		cbxEspecie.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cbxEspecie.getSelectedItem()!=null) {
					List<Racas> racas = new ArrayList<>();
					try {
						racas = RacasDao.get(((Especies)cbxEspecie.getSelectedItem()).getId());
						cbxRaca.removeAllItems();
						for(Racas raca:racas) {
							cbxRaca.addItem(raca);
						}
					}catch(Exception error) {
						
					}
				}
				
			}
		});
		this.add(cbxEspecie);
		
		lblRaca = new JLabel("Raça");
		lblRaca.setBounds(10, 190, 70, 20);
		this.add(lblRaca);
		
		List<Racas> racas = new ArrayList<>();
		try {
			racas = RacasDao.get();
		}catch(Exception e) {
			
		}
		
		cbxRaca = new JComboBox<Racas>(racas.toArray(new Racas[0]));
		cbxRaca.setBounds(100, 190, 150, 20);
		this.add(cbxRaca);

		salvar = new JButton(id == null ? "Inserir" : "Alterar");
		salvar.setBounds(10, 220, 90, 20);
		salvar.addActionListener(salvarClick());
		this.add(salvar);
	}
	
	public ActionListener salvarClick() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtNome.getText() != null && !txtNome.getText().equals("")) {
					Calendar calendar = Calendar.getInstance();
					Cor cor = (Cor) cbxCor.getSelectedItem();
					Racas raca = (Racas) cbxRaca.getSelectedItem();
					Animal animal = new Animal(0, txtNome.getText(), txtSexo.getText(), txtDescricao.getText(), cor, raca, false, new Date(calendar.getTimeInMillis())); 
					try {
						if (id != null) {
							AnimalDao.update(id, animal);
						} else {
							AnimalDao.insert(animal);
						}
						setVisible(false);
					}catch(Exception error) {		
					}
				}
			}
		};
	}

}
