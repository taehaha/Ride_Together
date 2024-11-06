export default class KakaoMap {
    /* fields */
    map; // 카카오맵
    container; // 카카오맵이 표시되는 Element
    myPositionMarker; // 출발지 marker
    destinationPositionMarker; // 목적지 marker

    /* Handler */
    centerChangedHandler = () => {
        this.destinationPositionMarker.setMap(null);
        this.destinationPositionMarker = new kakao.maps.Marker({
            title: "목적지 위치",
            map: this.map,
            position: this.map.getCenter(),
            draggable: false,
            clickable: false,
        });
    }

    /* constructor */
    constructor(container, options) {
        this.map = new kakao.maps.Map(container, options);
        this.container = container;

        // 내 위치 마커
        this.myPositionMarker = new kakao.maps.Marker({
            title: "현재 위치",
            map: null,
            position: null,
            draggable: false,
            clickable: false,
        });

        // 목적지 위치 마커
        this.destinationPositionMarker = new kakao.maps.Marker({
            title: "목적지 위치",
            map: null,
            position: null,
            draggable: false,
            clickable: false,
        });
    }

    /* methods */
    markMyPosition(kakaoLatLng) {
        this.myPositionMarker.setPosition(kakaoLatLng);
        this.myPositionMarker.setMap(this.map);
    }

    unmarkMyPosition() {
        this.myPositionMarker.setPosition(null);
        this.myPositionMarker.setMap(null);
    }

    markDestinationPosition(kakaoLatLng) {
        this.destinationPositionMarker.setPosition(kakaoLatLng);
        this.destinationPositionMarker.setMap(this.map);
    }

    unmarkDestinationPosition() {
        this.destinationPositionMarker.setPosition(null);
        this.destinationPositionMarker.setMap(null);
    }

    moveDestinationPosition() {
        // kakao Map 상 중앙이 변경될경우 center 마커도 중앙으로 이동
        kakao.maps.event.addListener(this.map, 'center_changed', this.centerChangedHandler);

    }

    stopDestinationPosition() {
        kakao.maps.event.removeListener(this.map, 'center_changed', this.centerChangedHandler);
    }

    getCenterPosition() {
        return this.map.getCenter();
    }
}