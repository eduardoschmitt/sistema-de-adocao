package cadastro;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import model.EstadoTableModel;

public class TablePage extends JPanel {
	
	private AbstractTableModel tableModel;
	JTable tabela;
	JScrollPane barraRolagem;
	
	
	public TablePage(AbstractTableModel tableModel) {
		
		this.tableModel = tableModel;
		montaComponents();
		this.setBounds(0, 0, 800, 600);
		this.setVisible(true);
	}
	
	public void montaComponents() {
		tabela = new JTable(tableModel);
		barraRolagem = new JScrollPane(tabela);
		this.add(barraRolagem);
	}
	
	public JTable getTabela() {
		return tabela;
	}
	
	public void setTableModel(AbstractTableModel tableModel) {
		this.tableModel = tableModel;
	}
	public AbstractTableModel getTableModel() {
		return tableModel;
	}
}
