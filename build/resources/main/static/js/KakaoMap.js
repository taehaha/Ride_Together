export default class KakaoMap {
    /* fields */
    map;
    container;
    myPositionMarker;
    destinationPositionMarker;

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
    }

    /* methods */
    markMyPosition(kakaoLatLng) {
        this.myPositionMarker = new kakao.maps.Marker({
            title: "현재 위치",
            map: this.map,
            position: kakaoLatLng,
            draggable: false,
            clickable: false,
        });
    }

    unmarkMyPosition() {
        this.myPositionMarker = null;
    }

    markDestinationPosition(kakaoLatLng) {
        this.destinationPositionMarker = new kakao.maps.Marker({
            title: "목적지 위치",
            map: this.map,
            position: kakaoLatLng,
            draggable: false,
            clickable: false,
        });
    }

    unmarkDestinationPosition() {
        this.destinationPositionMarker = null;
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