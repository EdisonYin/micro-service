package com.light.springboot.model;

public class OCRResult {

	private String logId;
	private int wordsResultNum;
	private WordResult wordResult;
	
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public int getWordsResultNum() {
		return wordsResultNum;
	}
	public void setWordsResultNum(int wordsResultNum) {
		this.wordsResultNum = wordsResultNum;
	}
	public WordResult getWordResult() {
		return wordResult;
	}
	public void setWordResult(WordResult wordsResult) {
		this.wordResult = wordsResult;
	}
}