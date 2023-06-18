package view;

import javax.swing.JPanel;

public abstract class AbstractTela extends JPanel{
	
	public abstract void montaTable();
	public abstract void remontaTable();
	public abstract void abreTelaCadastro(Integer id);
	
}
