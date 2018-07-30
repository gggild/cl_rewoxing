package com.example.renwoxing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

public class ShowMyLocation extends Activity implements
OnGetGeoCoderResultListener {
	GeoCoder mSearch = null; // ����ģ�飬Ҳ��ȥ����ͼģ�����ʹ��
	BaiduMap mBaiduMap = null;
	MapView mMapView = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_my_location);
		// ��ͼ��ʼ��
		mMapView = (MapView) findViewById(R.id.show_my_location_bmapView);
		mBaiduMap = mMapView.getMap();
		// ��ʼ������ģ�飬ע���¼�����
		mSearch = GeoCoder.newInstance();
		mSearch.setOnGetGeoCodeResultListener(this);
		Intent intent = getIntent();
		if (intent.hasExtra("x") && intent.hasExtra("y")) {
			// ����intent����ʱ���������ĵ�Ϊָ����
			Bundle b = intent.getExtras();
			LatLng point = new LatLng(b.getDouble("x"), b.getDouble("y"));
			// �������ű���,���µ�ͼ״̬
			float f = mBaiduMap.getMaxZoomLevel();// 19.0
			MapStatusUpdate u = MapStatusUpdateFactory.zoomTo(f - 2);
			mBaiduMap.animateMapStatus(u);
			// ��Geo����
			mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(point));
		} else {
			Toast.makeText(this, "��Ǹ��δ���ҵ����", Toast.LENGTH_LONG).show();
			this.finish();
		}
	}
	@Override
	protected void onPause() {
		mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		mMapView.onDestroy();
		mSearch.destroy();
		super.onDestroy();
	}
	
	@Override
	public void onGetGeoCodeResult(GeoCodeResult result) {
		// ���ݵ���λ�ý�������
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(ShowMyLocation.this, "��Ǹ��δ���ҵ����", Toast.LENGTH_LONG)
					.show();
			return;
		}
		mBaiduMap.clear();
		mBaiduMap.addOverlay(new MarkerOptions().position(result.getLocation())
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.icon_marka)));
		mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(result
				.getLocation()));
		String strInfo = String.format("γ�ȣ�%f ���ȣ�%f",
				result.getLocation().latitude, result.getLocation().longitude);
		Toast.makeText(ShowMyLocation.this, strInfo, Toast.LENGTH_LONG).show();

	}

	@Override
	public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
		// ����γ�Ⱥ;��Ƚ�������
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(ShowMyLocation.this, "��Ǹ��δ���ҵ����", Toast.LENGTH_LONG)
					.show();
			return;
		}
		mBaiduMap.clear();
		mBaiduMap.addOverlay(new MarkerOptions().position(result.getLocation())
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.icon_marka)));
		mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(result
				.getLocation()));
		if (!result.getAddress().equals(""))
			Toast.makeText(ShowMyLocation.this, result.getAddress(),
					Toast.LENGTH_LONG).show();
		else {
			Toast toast = Toast.makeText(ShowMyLocation.this, "��Ǹ���޷���ȡ����λ�ã�����",
					Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
			// �������ű���,���µ�ͼ״̬
			float f = mBaiduMap.getMinZoomLevel();// 19.0
			MapStatusUpdate u = MapStatusUpdateFactory.zoomTo(f);
			mBaiduMap.animateMapStatus(u);

		}
	}
}
