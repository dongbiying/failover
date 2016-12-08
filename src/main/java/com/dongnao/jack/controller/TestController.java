package com.dongnao.jack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dongnao.jack.dao.MongoUserDao;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	MongoTemplate mongoTemplate;	
	@Autowired
	MongoUserDao mongoUserDao;
	
//	@Autowired
//	JedisCluster jedisCluster;
//
//	@RequestMapping("/invoke")
//	public @ResponseBody String test() {
//		try{
//			for(int i = 0 ; i < 100 ; i++) {
//				jedisCluster.set("key" + i, "val" + i);
//			}
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
//		return "OK";
//	}
//	
	
	@RequestMapping("/mongodb")
	@ResponseBody 
	public String mongodb(){
		
		for (int i = 0; i < 10; i++) {
			JSONObject jo = new JSONObject();
			jo.put("name", "jack"+i);
			jo.put("age", 19);
			mongoTemplate.insert(jo, "mapTestJava");
		}
		
		for (int i = 0; i < 10; i++) {
			JSONObject jo = new JSONObject();
			jo.put("name", "start"+i);
			jo.put("age", 12);
			mongoTemplate.insert(jo, "mapTestJava");
		}
		
		for (int i = 0; i < 10; i++) {
			JSONObject jo = new JSONObject();
			jo.put("name", "senvon"+i);
			jo.put("age", 43);
			mongoTemplate.insert(jo, "mapTestJava");
		}
		
		
		return "mongodb ok";
	}
	
	@RequestMapping("/mongodb/mapreduce")
	@ResponseBody
	public String mongodbMapReduce(){
		
		return mongoUserDao.sum();
	}
	
	@RequestMapping("/mongodb/upload")
	@ResponseBody
	public String mongodbUpload(){
		return mongoUserDao.upload();
	}
	
	
	@RequestMapping("/mongodb/download")
	@ResponseBody
	public String mongodbDownload(){
		return mongoUserDao.download();
	}
}
