package com.cafe24.bitapp.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.cafe24.bitapp.frontend.util.BitappRestTemplate;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@Controller
public class MainController {
	private String accessToken = "rWRgqrycIxclBquKzhGz0A";
	
	
	@RequestMapping({"/", "/main"})
	public String main(
			) {
		return "index";
	}
	
	// 스크립트 리스트
	@RequestMapping("/list")
	public String getList(
			Model model
			) throws JsonParseException, JsonMappingException, IOException {

		String response = BitappRestTemplate.request("/api/v2/admin/scripttags", HttpMethod.GET, null, accessToken);

		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = mapper.readValue(response, Map.class);
		
		model.addAttribute("list", map.get("scripttags"));
		
		return "index";
	}

	// 스크립트 추가
	@RequestMapping(name = "/create", method = RequestMethod.POST)
	public String create(
			@RequestParam(name = "src", required = true, defaultValue = "") String src,
			@RequestParam(name = "displayLocation", required = true, defaultValue = "") String displayLocation
			) throws JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper();

		Map params = new HashMap<>();
		params.put("shop_no", "1");
			Map params2 = new HashMap<>();
	        params2.put("src", src);
	        params2.put("display_location", Arrays.asList(displayLocation));
		params.put("request", params2);
		
		
		// json 으로 변환
		String body = objectMapper.writeValueAsString(params);

		String response = BitappRestTemplate.request("/api/v2/admin/scripttags", HttpMethod.POST, body, accessToken);

		
		return "redirect:/list";
	}
	
	
	

	// 스크립트 삭제.
	@RequestMapping("/delete")
	public String delete(
			@RequestParam(name = "scriptNo", required = true, defaultValue = "") String scriptNo
			) {
		String response = BitappRestTemplate.request("/api/v2/admin/scripttags/"+scriptNo, HttpMethod.DELETE, null, accessToken);
		
		return "redirect:/list";
	}


}
