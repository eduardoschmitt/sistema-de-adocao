package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.EspeciesDao;
import model.Especies;

public class TelaEspeciesCadastro extends JDialog {
	JLabel lblNome;
	JTextField txtNome;
	JButton salvar;
	JPanel tela;
	Integer id;

	public TelaEspeciesCadastro(Integer id, JPanel tela) {
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
					Especies especies = new Especies(0, txtNome.getText());
					try {
						if (id != null) {
							//EspeciesDao.update(id, especies);
							System.out.println("caiu aqui");
						} else {
							EspeciesDao.insert(especies);
						}
						setVisible(false);
					}catch(Exception error) {		
					}
				}
			}
		};
	}
	
}
