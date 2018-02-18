package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;

public class ResWindow extends JFrame {

	private JPanel contentPane;
	private JTable table;

    private ModeleDynamiqueObjet modele ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResWindow frame = new ResWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ResWindow() {
		modele = new ModeleDynamiqueObjet();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable(modele);
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(sorter);

		List<RowSorter.SortKey> sortKeys = new ArrayList<>(20);
		sortKeys.add(new RowSorter.SortKey(1, SortOrder.DESCENDING));
		sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
		sorter.setSortKeys(sortKeys);
		scrollPane.setViewportView(table);
		
		
	}
	
	public void calculate(Map<String,Double> map) {
		for(String s : map.keySet()) {
			this.modele.addPlayer(s, map.get(s));
		}
	}
	public class ModeleDynamiqueObjet extends AbstractTableModel {
	    private Map<String,Double> players = new HashMap<String,Double>();
	    private List<String> list = new ArrayList<String>();
	 
	    private final String[] entetes = {"Name","Score"};
	 
	    public ModeleDynamiqueObjet() {
	        super();
	    }
	 
	    public int getRowCount() {
	        return players.size();
	    }
	 
	    public int getColumnCount() {
	        return entetes.length;
	    }
	 
	    public String getColumnName(int columnIndex) {
	        return entetes[columnIndex];
	    }
	 
	    public Object getValueAt(int rowIndex, int columnIndex) {
	        switch(columnIndex){
	            case 0:
	                return list.get(rowIndex);
	            case 1:
	                return players.get(list.get(rowIndex));
	            default:
	                return null; //Ne devrait jamais arriver
	        }
	    }
	 
	    public void addPlayer(String name,Double score) {
	        players.put(name,score);
	        list.add(name);
	 
	        fireTableRowsInserted(players.size() -1, players.size() -1);
	    }
	 
	    public void removePlayer(int rowIndex) {
	    	players.remove(list.get(rowIndex));
	    	list.remove(rowIndex);
	 
	        fireTableRowsDeleted(rowIndex, rowIndex);
	    }
	}

}
