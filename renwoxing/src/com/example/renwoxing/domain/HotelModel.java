package com.example.renwoxing.domain;

import java.util.List;

public class HotelModel {
	private float ave_price;//ƽ���۸�
	private float entertainment_distance;//���������ľ���
	private int entertainment_id;//����id
	private String hotelAddress;//�Ƶ��ַ
	private String hotelDescribe;//�Ƶ�����
	private int hotelLevel;//�Ƶ꼶��
	private String hotelName;//�Ƶ�����
	private String hotelScore;//�Ƶ�����
	private int id;//�Ƶ�id
	private List<ShopModel> shopModels;//������
	private float shop_distance;//����̾���
	private int shop_id;//����id
	private float spot_distance;//�뾰�����
	private int spot_id;//����id 
	public float getAve_price() {
		return ave_price;
	}
	public void setAve_price(float ave_price) {
		this.ave_price = ave_price;
	}
	public float getEntertainment_distance() {
		return entertainment_distance;
	}
	public void setEntertainment_distance(float entertainment_distance) {
		this.entertainment_distance = entertainment_distance;
	}
	public int getEntertainment_id() {
		return entertainment_id;
	}
	public void setEntertainment_id(int entertainment_id) {
		this.entertainment_id = entertainment_id;
	}
	public String getHotelAddress() {
		return hotelAddress;
	}
	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}
	public String getHotelDescribe() {
		return hotelDescribe;
	}
	public void setHotelDescribe(String hotelDescribe) {
		this.hotelDescribe = hotelDescribe;
	}
	public int getHotelLevel() {
		return hotelLevel;
	}
	public void setHotelLevel(int hotelLevel) {
		this.hotelLevel = hotelLevel;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getHotelScore() {
		return hotelScore;
	}
	public void setHotelScore(String hotelScore) {
		this.hotelScore = hotelScore;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<ShopModel> getShopModels() {
		return shopModels;
	}
	public float getShop_distance() {
		return shop_distance;
	}
	public void setShop_distance(float shop_distance) {
		this.shop_distance = shop_distance;
	}
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public float getSpot_distance() {
		return spot_distance;
	}
	public void setSpot_distance(float spot_distance) {
		this.spot_distance = spot_distance;
	}
	public int getSpot_id() {
		return spot_id;
	}
	public void setSpot_id(int spot_id) {
		this.spot_id = spot_id;
	}
	
}
