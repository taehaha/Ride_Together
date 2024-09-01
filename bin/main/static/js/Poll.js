export default class Poll {
    intervalId;
    url;

    // poll recursive function
    #polling = () => {
        return setInterval(async () => {
            fetch(this.url, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                }
            })
                .then(res => {
                    console.log("match polled");
                    switch (res.status) {
                        case 410: break; // Ride가 취소되었을 경우
                        default: return res.json();
                    }
                })
                .then((data) => {
                    if (data.matchId !== null) {
                        console.log(data);
                        console.log("Match Success. user1 : " + data.userId1 + " user2 : " + data.userId2);
                    }
                })
                .catch((err) => {

                });
        }, 1000);
    }

    constructor(url) {
        this.url = url;
    }


    startPoll() {
        this.intervalId = this.#polling();
    }

    stopPoll() {
        if (this.intervalId !== null) {
            clearInterval(this.intervalId);
            this.intervalId = null;}
        }
}