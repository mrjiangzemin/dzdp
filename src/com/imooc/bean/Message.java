package com.imooc.bean;

import java.util.List;

/**
 * 与消息表对应的实体类
 */
public class Message  {
	private String name;
	private String address;
	private String jpgurl;
	private String avgScore;
	private String allCommentNum;
	private String avgPrice;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getJpgURL() {
		return jpgurl;
	}
	public void setJpgURL(String jpgURL) {
		this.jpgurl = jpgURL;
	}
	public String getAvgScore() {
		return avgScore;
	}
	public void setAvgScore(String avgScore) {
		this.avgScore = avgScore;
	}
	public String getAllCommentNum() {
		return allCommentNum;
	}
	public void setAllCommentNum(String allCommentNum) {
		this.allCommentNum = allCommentNum;
	}
	public String getAvgPrice() {
		return avgPrice;
	}
	public void setAvgPrice(String avgPrice) {
		this.avgPrice = avgPrice;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
