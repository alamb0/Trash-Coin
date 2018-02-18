package implement;

import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ParallelScanOptions;
import com.mongodb.ServerAddress;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.util.JSON;

import implement.User;
import implement.utility.*;

public class DBHandler {
    public static final String USER_COLLECTION = "userCollection";
    public static final String TRASH_COLLECTION = "trashColllection";

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

    public void findUserandUpdate(int id, double x, double y) throws Exception{
        DBCollection dbCollection = getDBcollection(USER_COLLECTION);
        BasicDBObject query = new BasicDBObject("id", id);
        DBObject dbObject = findUser(query, dbCollection);
        MongoTemplate mongoTemplate = new MongoTemplate(new MongoClient( "localhost" ),"trashclub");
        User user = mongoTemplate.getConverter().read(User.class, dbObject);;
        user.setX(x);
        user.setY(y);
        dbObject = objectToDBObject(user);
        dbCollection.findAndModify(query, dbObject);
    }

    private DBObject findUser(BasicDBObject query, DBCollection collection){
        Cursor cursor = collection.find(query);
        try {
           return cursor.next();
        } catch(Exception e){
            return null;
        }finally {
            cursor.close();
        }
    }

     public void addPointToUser(double x, double y) throws Exception{
        Cursor cursor = getDBcollection(USER_COLLECTION).find();
        BasicDBObject query = new BasicDBObject();
        DBObject dbUser;
        User user;
        MongoTemplate mongoTemplate = new MongoTemplate(new MongoClient( "localhost" ),"trashclub");
        while(cursor.hasNext()){
            dbUser = cursor.next();
            user = mongoTemplate.getConverter().read(User.class, dbUser);
            user.setPoints(user.getPoints() + 5);
            dbUser = objectToDBObject(user);
            query.put("id", user.getId());
            getDBcollection(USER_COLLECTION).findAndModify(query, dbUser);
        }
    }

    public void addUser(User user){
        DBObject converted = objectToDBObject(user);
        getDBcollection(USER_COLLECTION).insert(converted);
    }

    public void deleteUser(long id){
        BasicDBObject query = new BasicDBObject("id", id);
        DBCollection dbCollection = getDBcollection(USER_COLLECTION);
        DBObject foundUser = findUser(query, dbCollection);
        dbCollection.remove(foundUser);
    }

    public DBCollection getDBcollection(final String collectionName){
        return getMongoDatabase().getCollection(collectionName);
    }

    private DB getMongoDatabase(){
        try{
            MongoClient mongoClient = new MongoClient( "localhost" );
            return mongoClient.getDB( "trashclub" );
        }catch(Exception e){
            return null;
        }
    }

    public static DBObject objectToDBObject(Object target){
        ObjectMapper mapper = null;
        DBObject obj = null;
        String converted = "";
        try{
            mapper = new ObjectMapper();
            converted = mapper.writeValueAsString(target);
            obj = (DBObject) JSON.parse(converted);
        }catch(Exception e){}
        return obj;
    }



}
