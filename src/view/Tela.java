package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	TelaEstado telaEstado = new TelaEstado("Estado");

	public Tela() {
		setLayout(null);
		this.setBounds(0, 0, 1200, 600);
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		carregarTelas();
		
		this.setVisible(true);
	}
	
	public void carregarTelas() {
		
		menuBar = new JMenuBar();
		JMenu menuEndereco = getMenu("Estado", telaEstado);
		
		menuBar.add(menuEndereco);
		setJMenuBar(menuBar);
		
		telaInicial = new JPanel();
		telaInicial.setBounds(0, 0, 1200, 600);
		
		telaInicial.setVisible(true);
		
		this.add(telaInicial);

		telaEstado.setBounds(0,0,1200,600);
		telaInicial.add(telaEstado);
	}
	
	public JMenu getMenu(String texto, JPanel tela){
		JMenu menu = new JMenu(texto);
		JMenuItem item = new JMenuItem("Lista");
		item.addActionListener(getActionTela(tela));
		menu.add(item);
		
		return menu;
	}
	
	public ActionListener getActionTela(JPanel tela) {
		ActionListener action = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tela.setVisible(true);
			}
		};
		return action;
	}
	
}
