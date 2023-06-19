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

import dao.CidadeDao;
import dao.EspeciesDao;
import dao.EstadoDao;
import dao.RacasDao;
import model.Racas;
import model.Cidade;
import model.Especies;
import model.Estado;

public class TelaRacasCadastro extends JDialog {
	JLabel lblNome;
	JTextField txtNome;
	JLabel lblEspecies;
	JComboBox<Especies> cbxEspecies;
	JButton salvar;
	JPanel tela;
	Integer id;

	public TelaRacasCadastro(Integer id, JPanel tela) {
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
		
		lblEspecies = new JLabel("Espécie");
		lblEspecies.setBounds(10, 70, 40, 20);
		this.add(lblEspecies);

		List<Especies> especies = new ArrayList<>();
		try {
			especies = EspeciesDao.get();
		}catch(Exception e) {
			
		}
		
		cbxEspecies = new JComboBox<Especies>(especies.toArray(new Especies[0]));
		cbxEspecies.setBounds(70, 70, 150, 20);
		this.add(cbxEspecies);

		salvar = new JButton(id == null ? "Inserir" : "Alterar");
		salvar.setBounds(10, 100, 90, 20);
		salvar.addActionListener(salvarClick());
		this.add(salvar);
	}
	
	public ActionListener salvarClick() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtNome.getText() != null && !txtNome.getText().equals("")) {
					Especies especies = (Especies) cbxEspecies.getSelectedItem();
					Racas racas = new Racas(0, especies, txtNome.getText());
					try {
						if (id != null) {
							RacasDao.update(id, racas);
						} else {
							RacasDao.insert(racas);
						}
						setVisible(false);
					}catch(Exception error) {		
					}
				}
			}
		};
	}
	
}
