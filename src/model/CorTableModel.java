package model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class CorTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	
	private List<Cor> lista;
    private String[] colunas = {"id", "Nome"};

    public CorTableModel(List<Cor> lista) {
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
        Cor objeto = lista.get(row);

        // Retorne o valor correspondente à célula na linha e coluna especificadas
        switch (column) {
            case 0:
                return objeto.getId();
            case 1:
                return objeto.getNome();
            default:
                return null;
        }
    }
}
