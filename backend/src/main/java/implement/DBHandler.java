package implement;

import com.mongodb.client.MongoCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoCredential;
import com.mongodb.MongoClientOptions;
import com.mongodb.DBObject;

public class DBHandler {
    public static final String USER_COLLECTION = "userCollection";
    public static final String TRASH_COLLECTION = "trashColllection";
    public static final String MONGO_URL = "mongodb://localhost:27017";

    public static DBHandler instance;

    public DBHandler(){
        instance = this;
    }

    public static DBHandler getInstance(){
        try{
             if(instance == null){
                 return new DBHandler();
             }
             return instance;
        }catch(Exception e){
            return new DBHandler();
        }
    }

    public MongoCollection getDBcollection(final String collectionName){
        return getMongoDatabase().getCollection(collectionName);
    }

    private MongoDatabase getMongoDatabase(){
        return new MongoClient(new MongoClientURI(MONGO_URL)).getDatabase("trashclub");
    }
}
