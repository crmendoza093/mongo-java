package fifamongo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.bson.Document;

import javax.swing.JTable;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JDesktopPane;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import java.util.Iterator;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class inicio extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txt_seleccion;
	private JTextField txt_campeon;
	private JTextField txt_subcampeon;
	private JTextField txt_tercer_puesto;
	private JTextField txt_cuarto_puesto;
	JLabel lbl_cantidad_selecciones;

	/**
	 * Launch the application.
	 */

	JDesktopPane desktopPane = new JDesktopPane();

	public static void main(String[] args) {
		ConnectToDB connect = new ConnectToDB();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inicio frame = new inicio();
					frame.setVisible(true);

					connect.ConectarDB();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void LoadTable() {
		// Creating a Mongo client
		MongoClient mongo = new MongoClient("localhost", 27017);

		DB db = mongo.getDB("fifa-db");
		DBCollection collection = db.getCollection("team");

		DBCursor cursor = collection.find();

		String[] columnNames = { "Seleccion", "Campeón", "Subcampeón", "Tercer puesto", "Cuarto puesto" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		int row_count = 0;
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			String seleccion = (String) obj.get("Selección");
			Integer campeon = (Integer) obj.get("Campeón");
			Integer sub = (Integer) obj.get("Subcampeón");
			Integer tercer = (Integer) obj.get("Tercer puesto");
			Integer cuarto = (Integer) obj.get("Cuarto puesto");

			model.addRow(new Object[] { seleccion, campeon, sub, tercer, cuarto });
			row_count++;
		}

		String cantidad = String.valueOf(row_count);
		JTable table_1 = new JTable(model);
		table_1.setPreferredScrollableViewportSize(new Dimension(250, 50));
		JScrollPane scrollPane = new JScrollPane(table_1);
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// get the model from the jtable
				DefaultTableModel model = (DefaultTableModel) table_1.getModel();

				// get the selected row index
				int selectedRowIndex = table_1.getSelectedRow();

				// set the selected row data into jtextfields
				txt_seleccion.setText(model.getValueAt(selectedRowIndex, 0).toString());
				txt_campeon.setText(model.getValueAt(selectedRowIndex, 1).toString());
				txt_subcampeon.setText(model.getValueAt(selectedRowIndex, 2).toString());
				txt_tercer_puesto.setText(model.getValueAt(selectedRowIndex, 3).toString());
				txt_cuarto_puesto.setText(model.getValueAt(selectedRowIndex, 4).toString());
			}
		});
		scrollPane.setBounds(16, 188, 493, 273);
		desktopPane.add(scrollPane);
		lbl_cantidad_selecciones.setText(cantidad);
	}

	/**
	 * Create the frame.
	 */
	public inicio() {
		ConnectToDB connect = new ConnectToDB();
		setTitle("FIFA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 525, 542);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));

		lbl_cantidad_selecciones = new JLabel("0");
		lbl_cantidad_selecciones.setBounds(475, 473, 34, 16);
		desktopPane.add(lbl_cantidad_selecciones);

		contentPane.add(desktopPane);
		LoadTable();

		JLabel lblNewLabel = new JLabel("Seleccion");
		lblNewLabel.setBackground(SystemColor.menu);
		lblNewLabel.setBounds(6, 24, 61, 16);
		desktopPane.add(lblNewLabel);

		txt_seleccion = new JTextField();
		txt_seleccion.setBounds(79, 19, 430, 26);
		desktopPane.add(txt_seleccion);
		txt_seleccion.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Campeon");
		lblNewLabel_1.setBounds(6, 65, 61, 16);
		desktopPane.add(lblNewLabel_1);

		txt_campeon = new JTextField();
		txt_campeon.setBounds(99, 57, 130, 26);
		desktopPane.add(txt_campeon);
		txt_campeon.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Subcampeon");
		lblNewLabel_2.setBounds(241, 65, 99, 16);
		desktopPane.add(lblNewLabel_2);

		txt_subcampeon = new JTextField();
		txt_subcampeon.setBounds(328, 60, 130, 26);
		desktopPane.add(txt_subcampeon);
		txt_subcampeon.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Tercer puesto");
		lblNewLabel_3.setBounds(6, 108, 105, 16);
		desktopPane.add(lblNewLabel_3);

		txt_tercer_puesto = new JTextField();
		txt_tercer_puesto.setBounds(99, 103, 130, 26);
		desktopPane.add(txt_tercer_puesto);
		txt_tercer_puesto.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Cuarto Puesto");
		lblNewLabel_4.setBounds(235, 108, 105, 16);
		desktopPane.add(lblNewLabel_4);

		txt_cuarto_puesto = new JTextField();
		txt_cuarto_puesto.setBounds(328, 103, 130, 26);
		desktopPane.add(txt_cuarto_puesto);
		txt_cuarto_puesto.setColumns(10);

		JButton btnCrearSeleccion = new JButton("Crear seleccion");
		btnCrearSeleccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Creating a Mongo client
				MongoClient mongo = new MongoClient("localhost", 27017);

				// Accessing the database
				MongoDatabase database = mongo.getDatabase("fifa-db");

				// Retrieving a collection
				MongoCollection<Document> collection = database.getCollection("team");

				Document document = new Document("Selección", txt_seleccion.getText())
						.append("Campeón", Integer.parseInt(txt_campeon.getText()))
						.append("Subcampeón", Integer.parseInt(txt_subcampeon.getText()))
						.append("Tercer puesto", Integer.parseInt(txt_tercer_puesto.getText()))
						.append("Cuarto puesto", Integer.parseInt(txt_cuarto_puesto.getText()));

				collection.insertOne(document);
				System.out.println("Document inserted successfully");
				LoadTable();
			}
		});
		btnCrearSeleccion.setBackground(SystemColor.activeCaption);
		btnCrearSeleccion.setBounds(6, 147, 180, 29);
		desktopPane.add(btnCrearSeleccion);

		JButton btnEliminarSeleccion = new JButton("Eliminar seleccion");
		btnEliminarSeleccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Creating a Mongo client
				MongoClient mongo = new MongoClient("localhost", 27017);

				// Accessing the database
				MongoDatabase database = mongo.getDatabase("fifa-db");

				// Retrieving a collection
				MongoCollection<Document> collection = database.getCollection("team");
				// Deleting the documents
				collection.deleteOne(Filters.eq("Selección", txt_seleccion.getText()));
				System.out.println("Document deleted successfully...");
				System.out.println(collection.count());
			}
		});
		btnEliminarSeleccion.setBounds(189, 147, 169, 29);
		desktopPane.add(btnEliminarSeleccion);

		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Creating a Mongo client
				MongoClient mongo = new MongoClient("localhost", 27017);

				// Accessing the database
				MongoDatabase database = mongo.getDatabase("fifa-db");

				// Retrieving a collection
				MongoCollection<Document> collection = database.getCollection("team");

				collection.updateOne(Filters.eq("Selección", txt_seleccion.getText()),
									 Updates.set("Selección", txt_seleccion.getText()));
				System.out.println("Document update successfully...");
			}
		});
		btnActualizar.setBounds(357, 147, 169, 29);
		desktopPane.add(btnActualizar);

		JLabel lblCantidadDeSelecciones = new JLabel("Cantidad de selecciones:");
		lblCantidadDeSelecciones.setBounds(292, 473, 176, 16);
		desktopPane.add(lblCantidadDeSelecciones);

		LoadTable();
	}
}
