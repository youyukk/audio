package com.chian.audio.service;

import java.util.List;
import java.util.Map;

public interface FanyiService {

	public String add(String chinaWords,String english,String chinaContent,String englishContent,String audioAddress);
	
	public Map<String,Object> search(String chinaWords);
	
	public List<Map<String,Object>> selectAllFanyi();
	
	public String deleteById(int id);
	
	public Map<String,Object> selectById(int id);
	
}
