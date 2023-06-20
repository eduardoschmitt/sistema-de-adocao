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
	

	public Tela() {
		telas.add(telaEstado);
		telas.add(telaCidade);
		telas.add(telaBairro);
		setLayout(null);
		this.setBounds(0, 0, 1200, 600);
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		carregarTelas();
		
		this.setVisible(true);
	}
	
	public void carregarTelas() {
		
		menuBar = new JMenuBar();
		JMenu menuEndereco = getMenu("Estado", telaEstado);
		JMenu menuCidade = getMenu("Cidade", telaCidade);
		JMenu menuBairro = getMenu("Bairro", telaBairro);
		
		menuBar.add(menuEndereco);
		menuBar.add(menuCidade);
		menuBar.add(menuBairro);
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
