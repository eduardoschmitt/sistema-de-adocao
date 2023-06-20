package model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class BairroTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	
	private List<Bairro> lista;
    private String[] colunas = {"id", "Nome", "Cidade", "Estado"};

    public BairroTableModel(List<Bairro> lista) {
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
        Bairro objeto = lista.get(row);

        // Retorne o valor correspondente à célula na linha e coluna especificadas
        switch (column) {
            case 0:
                return objeto.getId();
            case 1:
                return objeto.getNome();
            case 2:
            	return objeto.getCidade();
            case 3:
                return objeto.getEstado();
            default:
                return null;
        }
    }
}
