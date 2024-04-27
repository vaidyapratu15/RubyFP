//package com.fairplay.library;
//
//import java.io.IOException;
//
//import com.mongodb.BasicDBObject;
//import com.mongodb.DB;
//import com.mongodb.DBCollection;
//import com.mongodb.DBCursor;
//import com.mongodb.MongoClient;
//import com.mongodb.MongoClientURI;
//
//public class MongoDatabaseAllData {
//
//	public static String otp;
//
//	
//	@SuppressWarnings({ "deprecation", "finally" })
//	public static String main() throws IOException {
//		
//		
//		MongoClientURI url = new MongoClientURI(
//				"mongodb://fairplaytest:wohlig123@fairplaydev-shard-00-00.dzuqc.mongodb.net:27017,fairplaydev-shard-00-01.dzuqc.mongodb.net:27017,fairplaydev-shard-00-02.dzuqc.mongodb.net:27017/fairplay?ssl=true&replicaSet=atlas-g10hr2-shard-0&authSource=admin&retryWrites=true&w=majority");
//
//		try (
//				// Connecting to the mongoDB instance
//				MongoClient mongoclient = new MongoClient(url)) {
//			
//			// Selecting the database
//			DB database = mongoclient.getDB(GsonTestData.getPropertyData().getDatabase());
//
//			// Selecting the collection
//			DBCollection dbCollection = database.getCollectionFromString(GsonTestData.getPropertyData().getCollection());
//
//			// Setting search query with the required key-value pair
//			BasicDBObject searchQuery = new BasicDBObject();
//			searchQuery.put(GsonTestData.getPropertyData().getKey(), "+917249153969");
//
//			// DBCursor with the find query result
//			DBCursor cursor = dbCollection.find(searchQuery);
//
//			// Fetching the response
//			String response = null;
//			String otp = null;
//			try {
//				while (cursor.hasNext()) {
////					
////					otp = cursor.next().get(Utilities.getPropertyData("field")).toString();	
////					System.out.println(otp);
//					response = cursor.next().toString();
//					System.out.println(response);
//				}
//			} finally {
//				MongoDatabaseAllData.otp = otp;
//				System.out.println(otp);
//				cursor.close();
//				return otp;
//			}
//		}
//		
//	}
//	
//	
//}
