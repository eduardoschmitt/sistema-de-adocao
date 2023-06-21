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

import dao.DoacaoDao;
import dao.EstadoDao;
import dao.PessoaDao;
import model.Doacao;
import model.Estado;
import model.Pessoa;

public class TelaDoacaoCadastro extends JDialog {
	JLabel lblValor;
	JTextField txtValor;
	JLabel lblPessoa;
	JComboBox<Pessoa> cbxPessoa;

	JButton salvar;
	JPanel tela;
	Integer id;

	public TelaDoacaoCadastro(Integer id, JPanel tela) {
		this.id = id;
		this.tela = tela;
		setLayout(null);
		this.setBounds(200, 100, 500, 500);
		montaComponentes();
		this.setVisible(true);
	}

	public void montaComponentes() {

		lblValor = new JLabel("Valor");
		lblValor.setBounds(10, 10, 40, 20);
		this.add(lblValor);

		txtValor = new JTextField();
		txtValor.setBounds(70, 10, 150, 20);
		this.add(txtValor);
		
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
				if (txtValor.getText() != null && !txtValor.getText().equals("")) {
					Calendar calendar = Calendar.getInstance();
					Doacao doacao = new Doacao(0, Double.valueOf(txtValor.getText()), new Date(calendar.getTimeInMillis()), (Pessoa)cbxPessoa.getSelectedItem());
					try {
						DoacaoDao.insert(doacao);

						setVisible(false);
					}catch(Exception error) {
						
					}
					
				}

			}
		});
		this.add(salvar);
	}
	

}
