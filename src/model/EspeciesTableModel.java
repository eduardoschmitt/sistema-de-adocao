package model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class EspeciesTableModel extends AbstractTableModel {
	private List<Especies> lista;
	private String[] colunas = {"ID", "Nome_Especie"};
	
    public EspeciesTableModel(List<Especies> lista) {
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
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
        Especies objeto = lista.get(rowIndex);

        // Retorne o valor correspondente à célula na linha e coluna especificadas
        switch (columnIndex) {
            case 0:
                return objeto.getId();
            case 1:
                return objeto.getNome_especie();
            default:
                return null;
        }
	}

    
}
