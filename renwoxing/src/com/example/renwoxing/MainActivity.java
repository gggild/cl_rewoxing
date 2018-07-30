package com.example.renwoxing;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MapViewLayoutParams;
import com.baidu.mapapi.map.MapViewLayoutParams.ELayoutMode;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

public class MainActivity extends Activity {
	ImageView searchimage,location_iv;
	double latitude;// γ��
	double longitude;// ����
	String city="";//��λ����
	TextView tvLocationPoint;//��λ��ؼ�
	EditText search;//����������
	// ��λ���
	LocationClient mLocClient;
	public MyLocationListenner myListener = new MyLocationListenner();
	private LocationMode mCurrentMode;// ��λģʽ
	BitmapDescriptor mCurrentMarker;// Markerͼ��
//			Button send;
	private MapView mapView;
	BaiduMap mBaiduMap;
	boolean isFirstLoc = true;// �Ƿ��״ζ�λ
	private View pop;
	private TextView tv_title;//pop����
	private String addr ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchimage= (ImageView) findViewById(R.id.searchimage);
        location_iv= (ImageView) findViewById(R.id.location_iv);
        tvLocationPoint= (TextView) findViewById(R.id.my_location_point);
        search= (EditText) findViewById(R.id.search);
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
		if (city=="") {
			location_iv.setVisibility(View.VISIBLE);//ͼ��ɼ�
			tvLocationPoint.setVisibility(View.GONE);//textview����
			
		} else {
			
		}
        searchimage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				Intent intent =new Intent(MainActivity.this,GeoCoderActivity.class);
				Intent intent =new Intent(MainActivity.this,GeoCoderActivity.class);
				startActivity(intent);
			}
		});
        location_iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent =new Intent(MainActivity.this,MyLocation.class);
				startActivity(intent);
			}
		});
        tvLocationPoint.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent =new Intent(MainActivity.this,MyLocation.class);
				startActivity(intent);
			}
		});
    }

    /**
	 * �ڵ�ͼ�����Marker������ʾһ�����ݣ���ʾ�ı���Ϣ
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
		MarkerOptions option=new MarkerOptions()
				.position(point)
				.icon(bitmap)
				.title(""+addr);
		
		// �ڵ�ͼ�����Marker������ʾ
		baiduMap.addOverlay(option);
		baiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {
			
			@Override
			public boolean onMarkerClick(Marker marker) {
				if(pop==null){
					pop= View.inflate(MainActivity.this, R.layout.pop, null);
					tv_title= (TextView)pop.findViewById(R.id.tv_title);
					//��ʾһ������
					mapView.addView(pop,createLayoutParams(marker.getPosition()));
				}else{
					mapView.updateViewLayout(pop, createLayoutParams(marker.getPosition()));
				}
				tv_title.setText(marker.getTitle());
				
				return true;
			}

		});
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
				city=location.getCity();
				addr = location.getAddress().address;
				tvLocationPoint.setVisibility(View.VISIBLE);//textview�ɼ�
				location_iv.setVisibility(View.GONE);//����
				tvLocationPoint.setText(city);
				overlay(ll, mCurrentMarker, mBaiduMap);//�ڵ�ͼ�����Marker������ʾһ�����ݣ���ʾ�ı���Ϣ
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
/**
 * ����һ�����ֲ���
 * @param position
 * @return
 */
	private LayoutParams createLayoutParams(LatLng position) {
		MapViewLayoutParams.Builder builder =new MapViewLayoutParams.Builder();
		builder.layoutMode(ELayoutMode.mapMode);//ָ����������Ϊ��γ��
		builder.position(position);//���ñ�־��λ��
		LayoutParams params = builder.build();
		return params;
	}
    
}
