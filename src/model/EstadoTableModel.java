package model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class EstadoTableModel extends AbstractTableModel{
	private List<Estado> lista;
    private String[] colunas = {"id", "Sigla", "Nome"};

    public EstadoTableModel(List<Estado> lista) {
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
        Estado objeto = lista.get(row);

        // Retorne o valor correspondente à célula na linha e coluna especificadas
        switch (column) {
            case 0:
                return objeto.getId();
            case 1:
                return objeto.getSigla();
            case 2:
                return objeto.getNome();
            default:
                return null;
        }
    }
}
