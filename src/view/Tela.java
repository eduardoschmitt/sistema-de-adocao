package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class Tela extends JFrame{

	private static final long serialVersionUID = 1L;
	
	JPanel telaInicial;
	JMenuBar menuBar;
	
	List<AbstractTela> telas = new ArrayList<>();
	TelaEstado telaEstado = new TelaEstado("Estado");
	TelaCidade telaCidade = new TelaCidade("Cidade");
	TelaBairro telaBairro = new TelaBairro("Bairro");
	TelaPessoa telaPessoa = new TelaPessoa("Pessoa");
	TelaDoacao telaDoacao = new TelaDoacao("Doação");
	TelaEspecies telaEspecies = new TelaEspecies("Especies");
	TelaRacas telaRacas = new TelaRacas("Racas");
	TelaCor telaCor = new TelaCor("Cor");
	TelaAnimal telaAnimal = new TelaAnimal("Animal");

	public Tela() {
		telas.add(telaEstado);
		telas.add(telaCidade);
		telas.add(telaBairro);
		telas.add(telaPessoa);
		telas.add(telaDoacao);
		telas.add(telaEspecies);
		telas.add(telaRacas);
		telas.add(telaCor);
		telas.add(telaAnimal);
		setLayout(null);
		this.setBounds(0, 0, 1200, 600);
		this.setTitle("Adoção");
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		carregarTelas();
		this.setVisible(true);
	}
	
	public void carregarTelas() {
		
		menuBar = new JMenuBar();
		
		JMenu menuEstado = getMenu("Estado", telaEstado);
		menuBar.add(menuEstado);
		
		JMenu menuCidade = getMenu("Cidade", telaCidade);
		menuBar.add(menuCidade);
		
		JMenu menuBairro = getMenu("Bairro", telaBairro);
		menuBar.add(menuBairro);
		
		JMenu menuPessoa = getMenu("Pessoa", telaPessoa);
		menuBar.add(menuPessoa);
		
		JMenu menuDoacao = getMenu("Doação", telaDoacao);
		menuBar.add(menuDoacao);
		
		JMenu menuEspecies = getMenu("Espécies", telaEspecies);
		menuBar.add(menuEspecies);
		
		JMenu menuRaca = getMenu("Raça", telaRacas);
		menuBar.add(menuRaca);
		
		JMenu menuCor = getMenu("Cor", telaCor);
		menuBar.add(menuCor);
		
		JMenu menuAnimal = getMenu("Animal", telaAnimal);
		menuBar.add(menuAnimal);
		
		setJMenuBar(menuBar);
		
		telaInicial = new JPanel();
		telaInicial.setBounds(0, 0, 1200, 600);
		telaInicial.setVisible(true);
		this.add(telaInicial);

		telaEstado.setBounds(0,0,1200,600);
		telaInicial.add(telaEstado);
		
		telaCidade.setBounds(0,0,1200,600);
		telaInicial.add(telaCidade);
		
		telaBairro.setBounds(0, 0, 1200, 600);
		telaInicial.add(telaBairro);

		telaPessoa.setBounds(0, 0, 1200, 600);
		telaInicial.add(telaPessoa);
		
		telaDoacao.setBounds(0, 0, 1200, 600);
		telaInicial.add(telaDoacao);

		telaCor.setBounds(0, 0, 1200, 600);
        telaInicial.add(telaCor);

		telaEspecies.setBounds(0, 0, 1200, 600);
        telaInicial.add(telaEspecies);
        
        telaRacas.setBounds(0, 0, 1200, 600);
        telaInicial.add(telaRacas);
        
        telaAnimal.setBounds(0, 0, 1200, 600);
        telaInicial.add(telaAnimal);
        
	}
	
	public JMenu getMenu(String texto, AbstractTela tela){
		JMenu menu = new JMenu(texto);
		JMenuItem item = new JMenuItem("Lista");
		item.addActionListener(getActionTela(tela));
		menu.add(item);
		
		return menu;
	}
	
	public ActionListener getActionTela(AbstractTela tela) {
		ActionListener action = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				esconderTelas();
				tela.remontaTable();
				tela.setVisible(true);
			}
		};
		return action;
	}
	
	public void esconderTelas() {
		for(JPanel tela: telas) {
			tela.setVisible(false);
		}
	}
	
}
