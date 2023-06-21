package model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class DoacaoTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	
	private List<Doacao> lista;
    private String[] colunas = {"id", "Valor", "Data", "Pessoa"};

    public DoacaoTableModel(List<Doacao> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public Object getValueAt(int row, int column) {
    	Doacao objeto = lista.get(row);

        // Retorne o valor correspondente à célula na linha e coluna especificadas
        switch (column) {
            case 0:
                return objeto.getId();
            case 1:
                return objeto.getValor();
            case 2:
            	return objeto.getData();
            case 3:
            	return objeto.getPessoa();
            default:
                return null;
        }
    }
}
