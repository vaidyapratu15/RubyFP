package com.fairplay.library;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.fairplay.library.gson_Model.GetGsonFairplayTestData;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/*
 * @Author: Tejal Gavade.
 * @Since : December 2022
 * @Discription : To fetch the OTP of User from the MongoDB we need to integrate it with Selenium Webdriver.
 */

public class MongoDatabase {
	public static String data;

	@SuppressWarnings({ "resource", "deprecation" })
	public static String getValueFromDB(String databasename, String collection, String key, String value, String field)
			throws IOException {

		String data = null;

		MongoClientURI uri = new MongoClientURI(
				"mongodb://fairplaytest:wohlig123@fairplaydev-shard-00-00.dzuqc.mongodb.net:27017,fairplaydev-shard-00-01.dzuqc.mongodb.net:27017,fairplaydev-shard-00-02.dzuqc.mongodb.net:27017/fairplay?ssl=true&replicaSet=atlas-g10hr2-shard-0&authSource=admin&retryWrites=true&w=majority");
		MongoClient mongoclient = new MongoClient(uri);
		DB database = mongoclient.getDB(databasename);
		DBCollection dbCollection = database.getCollectionFromString(collection);
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put(key, value);

		try (DBCursor cursor = dbCollection.find(searchQuery)) {
			while (cursor.hasNext()) {
				data = cursor.next().get(field).toString();
			}
		}
		MongoDatabase.data = data;
		return data;

	}
	
	public static Boolean getSuspectedField(String databasename, String collection, String key, String value, String field) throws IOException {
		String data = getValueFromDB(databasename,collection,key,value,field);
		if(data.contains("\"suspected\": true")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		//System.out.println(GetGsonTestData.getDBData().getFpDatabase());
		getSuspectedField(GetGsonFairplayTestData.getDBData().getFpDatabase(), GetGsonFairplayTestData.getDBData().getCollection(), GetGsonFairplayTestData.getDBData().getKey(), "+91".concat(GetGsonFairplayTestData.getUserData().getUsername2()), GetGsonFairplayTestData.getDBData().getFpField());
		
	}

}
