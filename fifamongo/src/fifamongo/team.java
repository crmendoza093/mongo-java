package fifamongo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class team extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					team frame = new team();
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
	public team() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		
		// Creating a Mongo client
				MongoClient mongo = new MongoClient("localhost", 27017);

				DB db = mongo.getDB("fifa-db");
			    DBCollection collection = db.getCollection("team");

				DBCursor cursor = collection.find();

			    String[] columnNames = {"Seleccion", "Campeón", "Subcampeón", "Tercer puesto", "Cuarto puesto"};
			    DefaultTableModel model = new DefaultTableModel(columnNames, 0);

			    while(cursor.hasNext()) {
			        DBObject obj = cursor.next();
			        String seleccion = (String)obj.get("Selección");
			        Integer campeon = (Integer)obj.get("Campeón");
			        Integer sub = (Integer)obj.get("Subcampeón");
			        Integer tercer = (Integer)obj.get("Tercer puesto");
			        Integer cuarto = (Integer)obj.get("Cuarto puesto");
			        
			        model.addRow(new Object[] { seleccion, campeon, sub, tercer, cuarto });
			    }
			    
			    System.out.println(model.getRowCount());
			    
				JTable table = new JTable(model);
				table.setPreferredScrollableViewportSize(new Dimension(250, 100));
				JScrollPane scrollPane = new JScrollPane(table);
				getContentPane().add(scrollPane, BorderLayout.CENTER);
	}

}
