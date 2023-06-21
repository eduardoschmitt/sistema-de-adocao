package model;

import java.util.List;

import javax.swing.table.AbstractTableModel;
public class PessoaTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private List<Pessoa> lista;
    private String[] colunas = {"id", "Nome", "Telefone", "Email", "Logradouro", "Complemento", "Bairro", "Cidade", "Estado"};

    public PessoaTableModel(List<Pessoa> lista) {
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
        Pessoa objeto = lista.get(row);

        // Retorne o valor correspondente à célula na linha e coluna especificadas
        switch (column) {
            case 0:
                return objeto.getId();
            case 1:
                return objeto.getNome();
            case 2:
                return objeto.getTelefone();
            case 3:
            	return objeto.getEmail();
            case 4:
            	return objeto.getLogradouro();
            case 5:
            	return objeto.getComplemento();
            case 6:
            	return objeto.getBairro();
            case 7:
            	return objeto.getBairro().getCidade();
            case 8:
            	return objeto.getBairro().getCidade().getEstado();
            default:
                return null;
        }
    }
}
