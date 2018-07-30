package com.example.renwoxing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

public class MyLocation extends Activity {
	// ��λ���
		LocationClient mLocClient;
		public MyLocationListenner myListener = new MyLocationListenner();
		private LocationMode mCurrentMode;// ��λģʽ
		BitmapDescriptor mCurrentMarker;// Markerͼ��
//		Button send;
		private TextView tv;
		private MapView mapView;
		BaiduMap mBaiduMap;
		boolean isFirstLoc = true;// �Ƿ��״ζ�λ
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_location);
		tv = (TextView) findViewById(R.id.tv);
		mCurrentMode = LocationMode.NORMAL;// ���ö�λģʽΪ��ͨ
		mCurrentMarker = BitmapDescriptorFactory// ����markͼ��
				.fromResource(R.drawable.icon_marka);
		// ��ͼ��ʼ��
		mapView = (MapView) findViewById(R.id.my_location_bmapView);
//		send = (Button) findViewById(R.id.send);

		mBaiduMap = mapView.getMap();
		// ������λͼ��
		// mBaiduMap.setMyLocationEnabled(true);
		// mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
		// mCurrentMode, true, null));
		// ��λ��ʼ��
		mLocClient = new LocationClient(this);
		mLocClient.registerLocationListener(myListener);// ע�����������

		LocationClientOption option = new LocationClientOption();
		option.setCoorType("bd09ll");// ���صĶ�λ����ǰٶȾ�γ��,Ĭ��ֵgcj02
		option.setScanSpan(1000);// ���÷���λ����ļ��ʱ��Ϊ5000ms
		option.setIsNeedAddress(true);// ���صĶ�λ���������ַ��Ϣ
		option.setNeedDeviceDirect(true);// ���صĶ�λ��������ֻ���ͷ�ķ���
		mLocClient.setLocOption(option);
		mLocClient.start();
	}
	double latitude;
	double longitude;
	String addressStr="";
	String district="";
	String street="";
	String address="";
	public void send(View view) {
		Intent intent = new Intent(this, GeoCoderActivity.class);
		Bundle bundle = new Bundle();
		bundle.putDouble("x", latitude);
		bundle.putDouble("y", longitude);
		bundle.putString("addressStr",addressStr);
		bundle.putString("address", address);
//		bundle.putString("street", street);
//		bundle.putAddress("address",address);
		intent.putExtras(bundle);
		startActivity(intent);
	}
	/**
	 * 
	 * @author DONGHUI
	 * @date 2017��6��27�� 09:46:42
	 * @TODO
	 * @param longitude
	 * @param latitude
	 * @param bitmap
	 * @param baiduMap
	 */
	private void overlay(LatLng point, BitmapDescriptor bitmap,
			BaiduMap baiduMap) {
		// ����MarkerOption�������ڵ�ͼ�����Marker
		OverlayOptions option = new MarkerOptions().position(point)
				.icon(bitmap);
		// �ڵ�ͼ�����Marker������ʾ
		baiduMap.addOverlay(option);
	}
	/**
	 * ��λSDK��������
	 */
	public class MyLocationListenner implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// map view ���ٺ��ڴ����½��յ�λ��
			if (location == null || mapView == null)
				return;

			MyLocationData locData = new MyLocationData.Builder()
					.accuracy(location.getRadius())
					// �˴����ÿ����߻�ȡ���ķ�����Ϣ��˳ʱ��0-360
					.direction(100).latitude(location.getLatitude())
					.longitude(location.getLongitude()).build();
			mBaiduMap.setMyLocationData(locData);
			if (isFirstLoc) {
				isFirstLoc = false;
				latitude = location.getLatitude();
				longitude = location.getLongitude();
				LatLng ll = new LatLng(latitude, longitude);
				// �������ű���,���µ�ͼ״̬
				float f = mBaiduMap.getMaxZoomLevel();// 19.0
				MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(ll,
						f - 2);
				mBaiduMap.animateMapStatus(u);
//				System.out.println("location========="+location.getCity());
				address=location.getAddress().address;
				tv.setText(location.getAddress().address);
				addressStr=location.getCity();
				
				overlay(ll, mCurrentMarker, mBaiduMap);
			}
		}

		public void onReceivePoi(BDLocation poiLocation) {
		}
	}
	@Override
	protected void onPause() {
		mapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		mapView.onResume();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// �˳�ʱ���ٶ�λ
		mLocClient.stop();
		// �رն�λͼ��
		mBaiduMap.setMyLocationEnabled(false);
		mapView.onDestroy();
		mapView = null;
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_location, menu);
		return true;
	}

}
