package com.example.renwoxing.domain;

import java.util.List;

public class SpotModel {
	private List<HotelModel> hotelModels;//�Ƶ���
	private int id;//����id
	private List<RestaurantModel> restaurantModels;//�͹���
	private List<ShopModel> shopModels;//������
	private String spotDescribe;//��������
	private String spotName;//��������
	private String spotScore;//��������
	private String spotSuggestTime;//���㽨������ʱ��
	private String spotTime;//��������ʱ��
	public List<HotelModel> getHotelModels() {
		return hotelModels;
	}
	public void setHotelModels(List<HotelModel> hotelModels) {
		this.hotelModels = hotelModels;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<RestaurantModel> getRestaurantModels() {
		return restaurantModels;
	}
	public void setRestaurantModels(List<RestaurantModel> restaurantModels) {
		this.restaurantModels = restaurantModels;
	}
	public List<ShopModel> getShopModels() {
		return shopModels;
	}
	public void setShopModels(List<ShopModel> shopModels) {
		this.shopModels = shopModels;
	}
	public String getSpotDescribe() {
		return spotDescribe;
	}
	public void setSpotDescribe(String spotDescribe) {
		this.spotDescribe = spotDescribe;
	}
	public String getSpotName() {
		return spotName;
	}
	public void setSpotName(String spotName) {
		this.spotName = spotName;
	}
	public String getSpotScore() {
		return spotScore;
	}
	public void setSpotScore(String spotScore) {
		this.spotScore = spotScore;
	}
	public String getSpotSuggestTime() {
		return spotSuggestTime;
	}
	public void setSpotSuggestTime(String spotSuggestTime) {
		this.spotSuggestTime = spotSuggestTime;
	}
	public String getSpotTime() {
		return spotTime;
	}
	public void setSpotTime(String spotTime) {
		this.spotTime = spotTime;
	}
	
	
	

}
