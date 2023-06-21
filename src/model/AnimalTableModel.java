package model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class AnimalTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	
	private List<Animal> lista;
    private String[] colunas = {"id", "Nome", "Sexo", "Descricao", "Cor", "Raça", "Espécie", "Nascimento", "Adotado"};

    public AnimalTableModel(List<Animal> lista) {
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
    	Animal objeto = lista.get(row);

        // Retorne o valor correspondente à célula na linha e coluna especificadas
        switch (column) {
            case 0:
                return objeto.getId();
            case 1:
                return objeto.getNome();
            case 2:
                return objeto.getSexo();
            case 3:
            	return objeto.getDescricao();
            case 4:
            	return objeto.getCor();
            case 5:
            	return objeto.getRaca();
            case 6:
            	return objeto.getRaca().getEspecies();
            case 7:
            	return objeto.getNascimento();
            case 8:
            	return objeto.isAdotado();
            default:
                return null;
        }
    }
}
