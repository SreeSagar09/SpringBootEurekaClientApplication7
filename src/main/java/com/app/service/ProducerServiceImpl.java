package com.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProducerServiceImpl implements ProducerService {

	@Override
	public List<String> getList(Integer fromIndex, Integer toIndex) {
		List<String> alphabetsList = List.of("A", "B", "C", "D", "E", "F", 
				"G", "H", "I", "J", "K", "L", "M", "N", "O",
				"P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
		
		List<String> alphabetsSubList = alphabetsList.subList(fromIndex-1, toIndex);
		return alphabetsSubList;
	}
	
}
