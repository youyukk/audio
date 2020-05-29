package com.chian.audio.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chian.audio.dao.BaseDao;

@Service
public class FanyiServiceImpl implements FanyiService {
	
	@Autowired
	private BaseDao baseDao;
	
	@Override
	public String add(String chinaWords, String english,String chinaContent,String englishContent,String audioAddress) {
		Map<String,Object> map = new HashMap<>();
		if(chinaWords == null || audioAddress == null){
			return null;
		}
		map.put("china",chinaWords);
		map.put("english", english);
		map.put("chinaContent",chinaContent);
		map.put("englishContent",englishContent);
		map.put("audioAddress", audioAddress);
		
		try {
			baseDao.insert("FanyiMapper.addFanyi", map);
			return "1";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "0";
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public Map<String,Object> search(String chinaWords) {
		Map<String,Object> map = null;
		try {
			map = (Map<String,Object>)baseDao.selectOne("FanyiMapper.searchFanyi", chinaWords);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Map<String,Object>> selectAllFanyi() {
		
		List<Map<String,Object>> allUserMap = null;
		try {
			allUserMap = (List)baseDao.selectList("FanyiMapper.selectAllFile",null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allUserMap;
	}


	@Override
	public String deleteById(int id) {

		try {
			baseDao.delete("FanyiMapper.deleteById", id);
			return "1";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "0";
	}


	@SuppressWarnings("unchecked")
	@Override
	public Map<String,Object> selectById(int id) {
		
		Map<String,Object> map = null;
		
		try {
			map = (Map<String,Object>)baseDao.selectOne("FanyiMapper.selectById", id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}
	
	

}
