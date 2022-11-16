package duan1.dao;

import java.util.ArrayList;
import org.bson.Document;
import com.mongodb.client.*;
import duan1.models.*;

public class UserDAO {
    public static void add(UserModel user) {
        UserModel.collection.insertOne(user.toDocument());
    }

    public static ArrayList<UserModel> getAll(UserModel... user) {
        ArrayList<UserModel> users = new ArrayList<UserModel>();

        Document findQuery = user.length > 0 ? user[0] : new Document();
        MongoCursor<Document> documents = UserModel.collection.find(findQuery).cursor();

        while(documents.hasNext()) {
            UserModel userData = new UserModel();
            userData.fromDocument(documents.next());
            users.add(userData);
        }

        return users;
    }

    public static UserModel get(UserModel user) {
        Document document = UserModel.collection.find(user.toDocument()).first();

        if(document == null) return null; //Not exists
        
        UserModel userData = new UserModel();

        userData.fromDocument(document);

        return userData;
    }
}
