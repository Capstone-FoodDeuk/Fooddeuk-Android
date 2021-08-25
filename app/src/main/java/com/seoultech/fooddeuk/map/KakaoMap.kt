package com.seoultech.fooddeuk.map

import android.app.Activity
import android.view.ViewGroup
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

object KakaoMap {
    lateinit var kakaoMapView: MapView

    fun init(activity: Activity, view: ViewGroup, latitude: Double, longitude: Double) {
        kakaoMapView = MapView(activity)

        // 지도 중심점 현재 위치로 세팅(줌 레벨 낮을 수록 Zoom In)
        kakaoMapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(latitude, longitude), 1,true)
        view.addView(kakaoMapView)
    }

    fun addMarker(latitude: Double, longitude: Double, itemName: String, tag: Int, image: Int) {
        val marker = MapPOIItem()
        marker.itemName = itemName
        marker.tag = tag
        marker.mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude)
        marker.markerType = MapPOIItem.MarkerType.CustomImage
        marker.customImageResourceId = image
        marker.isCustomImageAutoscale = false // 커스텀 이미지 크기 자동 조절
        marker.setCustomImageAnchor(0.5f, 1.0f) // 커스텀 이미지에서 앵커 포인트 지정
        kakaoMapView.addPOIItem(marker)
    }
}