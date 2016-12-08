package com.dongnao.jack.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MapReduceOutput;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

@Service("mongoUserDao")
public class MongoUserDao implements IMongoUserDao{

	@Autowired 
	MongoTemplate mongoTemplate;
	
	@Autowired
	MongoTemplate mongoTemplate1;
	
	public String insert() {
	 
		return null;
	}

	public String sum() {
		DBCollection db = mongoTemplate.getCollection("mapTestJava");
		
		String map ="function(){if(this.age>10) emit(this.age,this.name)}";
		String reduce = "function(key,values){var count=0;values.forEach(function(){count+=1});var result={names:values,sum:count};return result;}";
		
		MapReduceOutput output =  db.mapReduce(map, reduce, "result2", null);
		
		DBCollection dbc =  output.getOutputCollection();
		
		StringBuffer sb = new StringBuffer();
		
		DBCursor cursor = dbc.find();
		
		while(cursor.hasNext()){
			DBObject dbo = cursor.next();
			sb.append(dbo.toString());
			System.out.println(dbo.toString());
		}
		
		return  sb.toString();
	}

	public String upload() {
		GridFS gridFs = new GridFS(mongoTemplate1.getDb());
		try {
			GridFSInputFile gif =  gridFs.createFile(new File("C:\\Users\\ThinkPad\\Desktop\\jq_test\\img\\00.jpg"));
			gif.put("filename","00.jpg");
			gif.save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "uplood ok ";
	}

	public String download() {
		
		GridFS gridFs = new GridFS(mongoTemplate1.getDb());
		try {
			GridFSDBFile gsf  =  gridFs.findOne("00.jpg");
			
			InputStream is = gsf.getInputStream();
			
			OutputStream  os  =  new FileOutputStream(new File("C:\\Users\\ThinkPad\\Desktop\\jq_test\\img\\00_mongo.jpg"));
			
			byte[] aa = new byte[1024];
			
			while(is.read(aa)!=-1){
				os.write(aa);
			}
			
			os.close();
			is.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "down ok";
	}

}
