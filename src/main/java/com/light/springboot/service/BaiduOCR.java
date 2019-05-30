package com.light.springboot.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.light.tool.HttpUtil;

@Service
public class BaiduOCR implements IBaiduOCRService{

    public String OCR_request(String imgStr) {
    	String otherHost = "https://aip.baidubce.com/rest/2.0/ocr/v1/general";
    	String access_token = getAuth("zoWBi2lNrH0VAl46WNauuNXL", "VQLHMwCpoGpCXP7vLCi6SRBLNWHmhT6z");
        try {
            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
            String result = HttpUtil.post(otherHost, access_token, imgStr);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return access_token;
    }
    
     // 获取access code
	 public static String getAuth(String ak, String sk) {
		 // 获取token地址
		 String access_token = "";
	     String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
	     String getAccessTokenUrl = authHost
		        // 1. grant_type为固定参数
		        + "grant_type=client_credentials"
		        // 2. 官网获取的 API Key
		        + "&client_id=" + ak
		        // 3. 官网获取的 Secret Key
		        + "&client_secret=" + sk;
		        try {
		            URL realUrl = new URL(getAccessTokenUrl);
		            // 打开和URL之间的连接
		            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
		            connection.setRequestMethod("GET");
		            connection.connect();
		            // 获取所有响应头字段
		            Map<String, List<String>> map = connection.getHeaderFields();
		            // 遍历所有的响应头字段
		            for (String key : map.keySet()) {
		                System.err.println(key + "--->" + map.get(key));
		            }
		            // 定义 BufferedReader输入流来读取URL的响应
		            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		            String result = "";
		            String line;
		            while ((line = in.readLine()) != null) {
		                result += line;
		            }
		            /**
		             * 返回结果示例
		             */
		            // access_token
		            JsonElement  je = new JsonParser().parse(result);
		            if (je.isJsonObject()) {
			            if (je.isJsonObject()) {
			            	access_token = je.getAsJsonObject().get("access_token").toString();
			            }
		            }
		        } catch (Exception e) {
		            System.err.printf("获取token失败！");
		            e.printStackTrace(System.err);
		        }
	        return access_token;
	    }
	 
	 public static String toPrettyFormat(String json) {
	        JsonParser jsonParser = new JsonParser();
	        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
	        Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        return gson.toJson(jsonObject);
	    }
	
}
