package com.light.springboot.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.light.springboot.model.OCRResult;
import com.light.springboot.model.WordResult;
import com.light.tool.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.light.springboot.service.IBaiduOCRService;
import com.light.tool.Base64Util;
import com.light.tool.FileUtil;

@RestController
public class OCRController {

	@Autowired
	private IBaiduOCRService baiduOCRService;
	
	// https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic 百度图片中的文字识别
	// POST URL参数：access_token 通过API Key和Secret Key获取的access_token,参考“Access Token获取
    /*
     * 
     * Header如下：
     * Content-Type	
     * application/x-www-form-urlencoded
     * 
     * 
     * 请求参数
     * 
     * image  和url二选一       string   图像数据，base64编码后进行urlencode，要求base64
     * 编码和urlencode后大小不超过4M，最短边至少15px，最长边最大4096px,支持jpg/png/bmp格式，当image字段存在时url字段失效.
     * 
     * url    和image二选一 string   图片完整URL，URL长度不超过1024字节，
     * URL对应的图片base64编码后大小不超过4M，最短边至少15px，最长边最大4096px,支持jpg/png/bmp格式，
     * 当image字段存在时url字段失效，不支持https的图片链接
     * 
     * */
	@GetMapping("/baidu_ocr")
	public Object Baidu_OCR() {
		String result = null;
        List<WordResult> words_result = new ArrayList<WordResult>();
        List<String> words = new ArrayList<String>();
        try {
            byte[] imgData = FileUtil.readFileByBytes("d:\\test.jpg");
            String imgStr = Base64Util.encode(imgData);
            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
            result = baiduOCRService.OCR_request(params);
            OCRResult OcrResult = new OCRResult();
            OcrResult = GsonUtil.parseJsonWithClass(result, OCRResult.class);
            words_result  = OcrResult.getWords_result();
            for (WordResult wordResult : words_result) {
                words.add(wordResult.getWords());
            }
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
		return words;
	}

    public static String toPrettyFormat(String json) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(jsonObject);
    }

	public void postBaiduOCR(String httpUrl, String httpArg) {
	    BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();

        try {  
            URL url = new URL(httpUrl);  
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();  
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            connection.setRequestProperty("apikey", "您自己的apikey");  
            connection.setDoOutput(true);  
            connection.getOutputStream().write(httpArg.getBytes("UTF-8"));  
            connection.connect();  
            InputStream is = connection.getInputStream();  
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));  
            String strRead = null;  
            while ((strRead = reader.readLine()) != null) {  
                sbf.append(strRead);  
                sbf.append("\r\n");  
            }  
            reader.close();  
            result = sbf.toString();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  	
	}
	
	@GetMapping("/baidu_ocr_test")
	public String Baidu_test() { 
		return "test";
	}
	
}
