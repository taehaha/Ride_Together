export default class KakaoMap {
    map;
    container;
    myPositionMarker;
    centerPositionMarker;

    constructor(container, options) {
        this.map = new kakao.maps.Map(container, options);
        this.container = container;
    }

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

    markCenterPosition() {
        this.centerPositionMarker = new kakao.maps.Marker({
            title: "목적지 위치",
            map: this.map,
            position: this.map.getCenter(),
            draggable: false,
            clickable: false,
        });

        // kakao Map 상 중앙이 변경될경우 center 마커도 중앙으로 이동
        kakao.maps.event.addListener(this.map, 'center_changed', () => {
            this.centerPositionMarker.setMap(null);
            this.centerPositionMarker = new kakao.maps.Marker({
                title: "목적지 위치",
                map: this.map,
                position: this.map.getCenter(),
                draggable: false,
                clickable: false,
            });
        });
    }

    unmarkCenterPosition() {
        this.centerPositionMarker = null;
    }

    getCenterPosition() {
        return this.map.getCenter();
    }
}