package com.app.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.ProducerService;

@RestController
@RequestMapping(path = "/producer")
public class ProducerController {
	
	@Autowired
	private ProducerService producerService;
	
	@GetMapping(path = "/getData")
	public ResponseEntity<Map<String, Object>> getData(HttpServletRequest httpServletRequest){
		
		ResponseEntity<Map<String, Object>> responseEntity = null;
		try {
			Map<String, Object> responseMap = new LinkedHashMap<>();
			responseMap.put("class", "ProducerController");
			responseMap.put("method", "getData");
			responseMap.put("requestedURI", httpServletRequest.getRequestURI());
			responseMap.put("port", String.valueOf(httpServletRequest.getServerPort()));
			responseMap.put("localAddress", httpServletRequest.getLocalAddr());
			responseMap.put("remoteAddress", httpServletRequest.getRemoteAddr());
			
			responseEntity = new ResponseEntity<>(responseMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	
	@GetMapping(path = "/getList")
	public ResponseEntity<Map<String, Object>> getList(@RequestParam Integer fromIndex, @RequestParam Integer toIndex, HttpServletRequest httpServletRequest){
		ResponseEntity<Map<String, Object>> responseEntity = null;
		try {
			Map<String, Object> errorMap = new LinkedHashMap<>();
			if(fromIndex < 1) {
				errorMap.put("fromIndex", "FromIndex should be grearthan 0.");
			}else if(fromIndex > 26) {
				errorMap.put("fromIndex", "FromIndex should be lessthan equals 26.");
			}
			
			if(toIndex < 1) {
				errorMap.put("toIndex", "ToIndex should be grearthan 0.");
			}else if(toIndex > 26) {
				errorMap.put("toIndex", "ToIndex should be lessthan equals 26.");
			}
			
			if(!errorMap.isEmpty()) {
				responseEntity = new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
				return responseEntity;
			}
			
			Map<String, Object> responseMap = new LinkedHashMap<>();
			responseMap.put("class", "ProducerController");
			responseMap.put("method", "getList");
			responseMap.put("requestedURI", httpServletRequest.getRequestURI());
			responseMap.put("port", String.valueOf(httpServletRequest.getServerPort()));
			responseMap.put("localAddress", httpServletRequest.getLocalAddr());
			responseMap.put("remoteAddress", httpServletRequest.getRemoteAddr());
			
			List<String> alphabetsList = producerService.getList(fromIndex, toIndex);
			responseMap.put("list", alphabetsList);
			
			responseEntity = new ResponseEntity<>(responseMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
}
