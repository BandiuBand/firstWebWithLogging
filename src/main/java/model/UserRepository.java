package model;


import com.mongodb.MongoCommandException;
import com.mongodb.MongoSecurityException;
import com.mongodb.MongoTimeoutException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.Date;

public class UserRepository{
    private static UserRepository userRepository;
    private static final String DBLINK=Config.getDblink();
    private static final String DBNAME=Config.getDbname();
    private static MongoClient mongoClient;
    private static MongoDatabase mongoDatabase;
    private static final String USERS_COLLECTION_NAME = Config.getUsersCollectionName();
    private static MongoCollection<Document> usersCollection;

        private UserRepository(String link,String name) {
            try {
                mongoClient = MongoClients.create(DBLINK);
                mongoDatabase = mongoClient.getDatabase(DBNAME);
                usersCollection = mongoDatabase.getCollection(USERS_COLLECTION_NAME);
            } catch (IllegalArgumentException e) {
                System.out.println("Illegal URI format");
                e.printStackTrace();
            } catch (MongoSecurityException e) {
                System.out.println("Auth problem");
                e.printStackTrace();
            } catch (MongoTimeoutException e) {
                System.out.println("Problem with connection to server");
                e.printStackTrace();
            } catch (MongoCommandException e) {
                System.out.println("Command error");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Unusual error with DB");
                e.printStackTrace();
            }
        }

        public static UserRepository getUserRepository() {
            init();
            return userRepository;
        }

        public static void init(){
        if (userRepository==null)
            userRepository=new UserRepository(DBLINK,DBNAME);
    }

        public void updateUser(User user){
            String username = user.getUsername();
            String passwordHash = user.getPasswordHash();
            String email = user.getEmail();
            String validationCode = user.getVerificationToken();
            boolean isValidated = user.isValidated();
            Date creationDate;
            try {
                Document newValueOfUser = new Document("username",username).append("passwordHash",passwordHash).append("email",email).append("validationCode",validationCode).append("isValidated",isValidated);
                usersCollection.updateOne(Filters.eq("username",username),newValueOfUser);
            } catch (Exception e){
                System.out.println("saving user error");
                e.printStackTrace();
            }
        }
        public void deleteUser(User user){
            try {
                usersCollection.deleteOne(new Document("username",user.getUsername()));
            } catch (Exception e){
                System.out.println("saving user error");
                e.printStackTrace();
            }
        }

        public void newUser(User user){
            String username = user.getUsername();
            String passwordHash = user.getPasswordHash();
            String email = user.getEmail();
            String validationCode = user.getVerificationToken();
            boolean isValidated = user.isValidated();
            Date creationDate;
            try {
            Document newUser = new Document("username",username).append("passwordHash",passwordHash).append("email",email).append("validationCode",validationCode).append("isValidated",isValidated).append("creationDate",creationDate=new Date());
            usersCollection.insertOne(newUser);
            user.setCreateDate(creationDate);
            } catch (Exception e){
                System.out.println("saving user error");
                e.printStackTrace();
            }
        }

        private User getUserByDocument(Document document){
            Document userDoc = getUserDocByDocument(document);
            if (userDoc==null) {
                return null;
            }
            String username = (String)userDoc.get("username");
            String passwordHash = (String) userDoc.get("passwordHash");
            String email = (String) userDoc.get("email");
            String validationCode = (String) userDoc.get("validationCode");
            boolean isValidated = (boolean) userDoc.get("isValidated");
            Date creationDate= (Date) userDoc.get("creationDate");;
            User user = new User(username,passwordHash,email, creationDate, validationCode, isValidated);

            return user;
        }
        private Document getUserDocByDocument(Document document){
            Document res;

            try {
                res = usersCollection.find(Filters.eq("username", document.getString("username"))).first();
            } catch (Exception e){
                System.out.println("getting user error");
                e.printStackTrace();
                throw new RuntimeException("Cant get user from DB");
            }
            return res;
        }

        public User getUserByUsername (String username){
            return getUserByDocument(new Document("username",username));
        }
        public User getUserByEmail (String email){
            return getUserByDocument(new Document("email",email));
        }
        public User getUserByVerificationCode(String verificationToken){
            return getUserByDocument(new Document("validationCode",verificationToken));
        }


}
