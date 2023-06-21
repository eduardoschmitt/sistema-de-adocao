package model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class AdocaoTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	
	private List<Adocao> lista;
    private String[] colunas = {"id", "Animal", "Pessoa", "Data adoção"};

    public AdocaoTableModel(List<Adocao> lista) {
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
        Adocao objeto = lista.get(row);

        // Retorne o valor correspondente à célula na linha e coluna especificadas
        switch (column) {
            case 0:
                return objeto.getId();
            case 1:
                return objeto.getAnimal();
            case 2:
                return objeto.getPessoa();
            case 3:
            	return objeto.getDataAdocao();
            default:
                return null;
        }
    }
}
