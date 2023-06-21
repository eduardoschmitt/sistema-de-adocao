package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTable;

import cadastro.TablePage;
import dao.BairroDao;
import dao.CorDao;
import model.Bairro;
import model.BairroTableModel;
import model.Cor;
import model.CorTableModel;

public class TelaCor extends AbstractTela {
	
	private static final long serialVersionUID = 1L;
	
	CorTableModel tableModel;
	JButton btnFechar;
	JButton btnExcluir;
	JButton btnAlterar;
	JButton btnInserir;
	TablePage panelTable;
	
	public TelaCor(String header) {
		
		montaTable();
		
		this.setBounds(0,0,1200,600);
		this.setVisible(false);
	}

	@Override
	public void montaTable() {
		tableModel = getTableModel();
		panelTable = new TablePage(tableModel);
		panelTable.setBounds(0,0,1200,600);
		this.add(panelTable);
		montaBotoes();
		
	}

	@Override
	public void remontaTable() {
		this.setVisible(false);
		this.removeAll();
		this.montaTable();
		this.setVisible(true);
	}
	
	public void montaBotoes() {
		btnExcluir = new JButton("Excluir Registro");
		btnExcluir.setBounds(100,500, 100, 50);
		btnExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTable tabela = panelTable.getTabela();
				int row = tabela.getSelectedRow();
				String idStr = String.valueOf(tableModel.getValueAt(row, 0));
				try{
					CorDao.delete(Integer.parseInt(idStr));
					remontaTable();
				}catch(Exception error) {
					
				}
			}
		});
		this.add(btnExcluir);
		
		btnAlterar = new JButton("Atualizar Registro");
		btnAlterar.setBounds(250,500, 100, 50);
		btnAlterar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTable tabela = panelTable.getTabela();
				int row = tabela.getSelectedRow();
				String idStr = String.valueOf(tableModel.getValueAt(row, 0));
				try{
					abreTelaCadastro(Integer.parseInt(idStr));
				}catch(Exception error) {
					
				}
			}
		});
		this.add(btnAlterar);
		
		btnInserir = new JButton("Inserir Registro");
		btnInserir.setBounds(400,500, 100, 50);
		btnInserir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					abreTelaCadastro(null);
				}catch(Exception error) {
					
				}
			}
		});
		this.add(btnInserir);
		
		btnFechar = new JButton("Fechar");
		btnFechar.setBounds(550,500, 100, 50);
		btnFechar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
		});
		this.add(btnFechar);
	}
	
	@Override
	public void abreTelaCadastro(Integer id) {
		TelaCorCadastro telaCadastro = new TelaCorCadastro(id, this);
		telaCadastro.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {}
			@Override
			public void componentResized(ComponentEvent e) {}
			@Override
			public void componentMoved(ComponentEvent e) {}

			@Override
			public void componentHidden(ComponentEvent e) {
				remontaTable();
				
			}
		});
		this.add(telaCadastro);
		
	}
	
	public CorTableModel getTableModel() {
		List<Cor> cores = new ArrayList<>();
		try {
			cores = CorDao.get();
		}catch(Exception e) {
			
		}
		
		tableModel = new CorTableModel(cores);
		return tableModel;
	}

}
