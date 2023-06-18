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
import dao.EstadoDao;
import model.Cidade;
import model.Estado;

public class TelaCidadeCadastro extends JDialog {
	JLabel lblNome;
	JTextField txtNome;
	JLabel lblEstado;
	JComboBox<Estado> cbxEstado;
	JButton salvar;
	JPanel tela;
	Integer id;

	public TelaCidadeCadastro(Integer id, JPanel tela) {
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
		this.add(cbxEstado);

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
					Estado estado = (Estado) cbxEstado.getSelectedItem();
					Cidade cidade = new Cidade(0, estado, txtNome.getText());
					try {
						if (id != null) {
							CidadeDao.update(id, cidade);
						} else {
							CidadeDao.insert(cidade);
						}
						setVisible(false);
					}catch(Exception error) {		
					}
				}
			}
		};
	}

}
