package com.example.renwoxing;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MarkerOptions.MarkerAnimateType;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

public class GeoCoder4Activity extends Activity implements
OnGetGeoCoderResultListener {
	GeoCoder mSearch1 = null; // ����ģ�飬Ҳ��ȥ����ͼģ�����ʹ��
	GeoCoder mSearch2 = null;
	GeoCoder mSearch3 = null;
	GeoCoder mSearch4 = null;
	BaiduMap mBaiduMap = null;
	MapView mMapView = null;
	LatLng stLatlng;
	LatLng enLatlng;
	LatLng latlng1;
	LatLng latlng2;
	LatLng latlng3;
	LatLng latlng4;

	//��ͨ���ߣ����ʱ�ı���
	Polyline mPolyline;
	private Marker mMarkerA;
	private Marker mMarkerB;
	private Marker mMarkerC;
	private Marker mMarkerD;
	//��ʼ��ȫ�� bitmap ��Ϣ������ʱ��ʱ recycle
	BitmapDescriptor bdA = BitmapDescriptorFactory
			.fromResource(R.drawable.icon_marka);
	BitmapDescriptor bdB = BitmapDescriptorFactory
			.fromResource(R.drawable.icon_markb);
	BitmapDescriptor bdC = BitmapDescriptorFactory
			.fromResource(R.drawable.icon_markc);
	BitmapDescriptor bdD = BitmapDescriptorFactory
			.fromResource(R.drawable.icon_markd);

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_geocoder);
		CharSequence titleLable = "������빦��";
		setTitle(titleLable);

		// ��ͼ��ʼ��
		mMapView = (MapView) findViewById(R.id.bmapGeocoderView);
		mBaiduMap = mMapView.getMap();

		// ��ʼ������ģ�飬ע���¼�����
		mSearch1 = GeoCoder.newInstance();
		mSearch2 = GeoCoder.newInstance();
		mSearch3 = GeoCoder.newInstance();
		mSearch4 = GeoCoder.newInstance();

		mSearch1.setOnGetGeoCodeResultListener(this);
		mSearch2.setOnGetGeoCodeResultListener(this);
		mSearch3.setOnGetGeoCodeResultListener(this);
		mSearch4.setOnGetGeoCodeResultListener(this);
		MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(14.0f);
		mBaiduMap.setMapStatus(msu);

		mSearch1.geocode(new GeoCodeOption().city("����").address("������ѧ"));

		mSearch2.geocode(new GeoCodeOption().city("����").address("�������պ����ѧ"));

		mSearch3.geocode(new GeoCodeOption().city("����").address("�й������ѧ"));

		mSearch4.geocode(new GeoCodeOption().city("����").address("�й�ũҵ��ѧ"));

	}


	@Override
	public void onGetGeoCodeResult(GeoCodeResult result) {
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(GeoCoder4Activity.this, "��Ǹ��δ���ҵ����", Toast.LENGTH_LONG)
			.show();
			return;
		}

		latlng1 = new LatLng(result.getLocation().latitude,
				result.getLocation().longitude);
		latlng2 = new LatLng(result.getLocation().latitude,
				result.getLocation().longitude);
		latlng3 = new LatLng(result.getLocation().latitude,
				result.getLocation().longitude);
		latlng4 = new LatLng(result.getLocation().latitude,
				result.getLocation().longitude);

		// add marker overlay

		MarkerOptions ooA = new MarkerOptions().position(latlng1).icon(bdA)
				.zIndex(9).draggable(true);
		// ���¶���
		ooA.animateType(MarkerAnimateType.drop);
		mMarkerA = (Marker) (mBaiduMap.addOverlay(ooA));

		// MarkerOptions ooB = new MarkerOptions().position(latlng2).icon(bdB)
		// .zIndex(5);
		// // ���¶���
		// ooB.animateType(MarkerAnimateType.drop);
		// mMarkerB = (Marker) (mBaiduMap.addOverlay(ooB));
		//
		//MarkerOptions ooC = new MarkerOptions().position(latlng3).icon(bdC)
		//		.zIndex(9).draggable(true);
		//ooC.animateType(MarkerAnimateType.drop);
		//mMarkerC = (Marker) (mBaiduMap.addOverlay(ooC));
		//
		// MarkerOptions ooD = new MarkerOptions().position(latlng4).icon(bdD)
		// .zIndex(5).draggable(true);
		// ooD.animateType(MarkerAnimateType.drop);
		// mMarkerD = (Marker) (mBaiduMap.addOverlay(ooD));


		//TextView textView1 = (TextView) findViewById(R.id.textview1);
		//textView1.setText("1:" + Double.toString(latlng1.latitude) + " "
		//		+ Double.toString(latlng1.longitude) + "\n" + "2:"
		//		+ Double.toString(latlng1.latitude) + " "
		//		+ Double.toString(latlng1.longitude) + "\n" + "3:"
		//		+ Double.toString(latlng1.latitude) + " "
		//		+ Double.toString(latlng1.longitude) + "\n" + "4:"
		//		+ Double.toString(latlng1.latitude) + " "
		//		+ Double.toString(latlng1.longitude) + "\n******");
		// add ground overlay
		LatLng southwest = new LatLng(latlng1.latitude, latlng1.longitude);
		LatLng northeast = new LatLng(latlng1.latitude, latlng1.longitude);
		LatLngBounds bounds = new LatLngBounds.Builder().include(northeast)
				.include(southwest).build();

		MapStatusUpdate u = MapStatusUpdateFactory
				.newLatLng(bounds.getCenter());
		mBaiduMap.setMapStatus(u);

		// // �������ʱ��ӻ���ͼ��
		// addCustomElementsDemo();
		LatLng p1 = new LatLng(39.963175, 116.400244);
		LatLng p2 = new LatLng(39.942821, 116.369199);
		LatLng p3 = new LatLng(39.939723, 116.425541);
		LatLng p4 = new LatLng(39.906965, 116.401394);
		List<LatLng> points = new ArrayList<LatLng>();
		points.add(p1);
		points.add(p2);
		points.add(p3);
		points.add(p4);
		OverlayOptions ooPolyline = new PolylineOptions().width(10)
				.color(0xAAFF0000).points(points);
		mPolyline = (Polyline) mBaiduMap.addOverlay(ooPolyline);

	}



	@Override
	public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(GeoCoder4Activity.this, "��Ǹ��δ���ҵ����", Toast.LENGTH_LONG)
			.show();
			return;
		}
	}

}