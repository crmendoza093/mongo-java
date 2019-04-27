package fifamongo;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import java.util.Iterator;

import org.bson.Document;

public class ConnectToDB {


	public void ConectarDB() {
		// Creating a Mongo client
		MongoClient mongo = new MongoClient("localhost", 27017);
		System.out.println("Connected to the database successfully");
	}

	public Iterator TeamList() {
		MongoClient mongo = new MongoClient("localhost", 27017);
		// Accessing the database
		MongoDatabase database = mongo.getDatabase("fifa-db");

		// Retrieving a collection
		MongoCollection<Document> collection = database.getCollection("team");

		// Getting the iterable object
		FindIterable<Document> iterDoc = collection.find();
		int i = 1;

		// Getting the iterator
		Iterator it = iterDoc.iterator();

		return it;
		
		//while (it.hasNext()) {
		//	System.out.println(it.next());
		//	i++;
		//}
	}

}
