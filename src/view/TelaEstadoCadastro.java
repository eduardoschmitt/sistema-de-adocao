package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.EstadoDao;
import model.Estado;

public class TelaEstadoCadastro extends JDialog {
	JLabel lblSigla;
	JTextField txtSigla;
	JLabel lblNome;
	JTextField txtNome;
	JButton salvar;
	JPanel tela;
	Integer id;

	public TelaEstadoCadastro(Integer id, JPanel tela) {
		this.id = id;
		this.tela = tela;
		setLayout(null);
		this.setBounds(200, 100, 500, 500);
		montaComponentes();
		this.setVisible(true);
	}

	public void montaComponentes() {
		lblSigla = new JLabel("Sigla");
		lblSigla.setBounds(10, 10, 40, 20);
		this.add(lblSigla);

		txtSigla = new JTextField();
		txtSigla.setBounds(70, 10, 40, 20);
		this.add(txtSigla);

		lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 40, 40, 20);
		this.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(70, 40, 150, 20);
		this.add(txtNome);

		salvar = new JButton(id == null ? "Inserir" : "Alterar");
		salvar.setBounds(10, 70, 90, 20);
		salvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtSigla.getText() != null && !txtSigla.getText().equals("") && txtNome.getText() != null && !txtNome.getText().equals("")) {
					Estado estado = new Estado(0, txtSigla.getText(), txtNome.getText());
					try {
						if (id != null) {
							EstadoDao.update(id, estado);
						} else {
							EstadoDao.insert(estado);
						}
						setVisible(false);
					}catch(Exception error) {
						
					}
					
				}

			}
		});
		this.add(salvar);
	}
	

}
