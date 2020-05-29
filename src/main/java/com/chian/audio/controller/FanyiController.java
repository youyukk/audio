package com.chian.audio.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.chian.audio.service.FanyiService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/fanyi/")
public class FanyiController {

	@Autowired
	private FanyiService fanyiService;
	
	@PostMapping("upload")
	public void upload(@RequestParam("file") MultipartFile file,String china,String english,
			String chinaContent,String englishContent,
			HttpServletRequest req,HttpServletResponse res) {
		res.setContentType("application/json; charset=utf-8");
        PrintWriter writer = null;
        
		try {
			writer = res.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        if (file.isEmpty()) {
           writer.print("-1");
        }

        String fileName = file.getOriginalFilename();
        String filePath = "/";
        File dest = new File(filePath + fileName);
        System.out.println(fileName);
        String result = null;
        
        try {
            file.transferTo(dest); 
            result = fanyiService.add(china, english,chinaContent,englishContent,filePath+fileName);
            writer.print(result);
            
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
	
	
	@RequestMapping("selectAllFanyi")
	public void selectAllFanyi(HttpServletRequest req,HttpServletResponse res){
		List<Map<String,Object>> allFan = fanyiService.selectAllFanyi();
		
		JSONArray fanList = JSONArray.fromObject(allFan);
		res.setContentType("application/json; charset=utf-8");
		try {
			PrintWriter writer = res.getWriter();
			writer.print(fanList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping("search")
	public void search(String chinaWords,HttpServletRequest req,HttpServletResponse res){
		Map<String,Object> map = fanyiService.search(chinaWords);
		JSONObject mapObj = JSONObject.fromObject(map);
		try {
			res.setContentType("application/json; charset=utf-8");
			PrintWriter writer = res.getWriter();
			System.out.println("mapObj" + mapObj.toString());
			writer.print(mapObj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("deleteById")
	public String deleteById(int id){
		fanyiService.deleteById(id);
		return "fan";
	}
	
	@RequestMapping("test")
	public String test(){
		System.out.println("tiao zhuan ye mian");
		return "fan";
	}
	
	/**
	 * 跳转到查看信息更全的页面
	 * @return
	 */
	@RequestMapping("newPage")
	public String newPage(int id,HttpServletResponse res){
		
		String val = String.valueOf(id);
		Cookie cookie = new Cookie("one",val);
		res.addCookie(cookie);
		return "newPage";
	}
	
	@RequestMapping("newData")
	public void newData(int id,HttpServletResponse res){
		System.out.println("use be");
		Map<String, Object> map = fanyiService.selectById(id);
		JSONObject mapObj = JSONObject.fromObject(map);
		try {
			res.setContentType("application/json; charset=utf-8");
			PrintWriter writer = res.getWriter();
			System.out.println("map" + map);
			writer.print(mapObj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	       
	
}
