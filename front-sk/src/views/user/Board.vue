<template>
    <div>
        <h1 class="main-title">게시물</h1>

        <div class="child-page-listing">
            <div class="grid-container">
                <article class="location-listing" v-for="i in 9" v-bind:key="i" @click="showDialog = true">
                    <a class="location-title" href="#">
                        <ul>
                            <li class="gallery-item-likes">
                                <span class="visually-hidden">Likes:</span>
                                <i class="fas fa-heart" aria-hidden="true"></i> 47
                            </li>
                            <li class="gallery-item-comments">
                                <span class="visually-hidden">Comments:</span>
                                <i class="fas fa-comment" aria-hidden="true"></i> 1
                            </li>
                        </ul>
                    </a>

                    <div class="location-image">
                        <img style="width:400px; height:300px;" :src="i | getUrl" />
                    </div>

                    <md-dialog :md-active.sync="showDialog">
                        <!-- <section class="wrapper"> -->
                        <div class="divide">
                            <article>
                                <div>
                                    <img style="width:100%; height:300px;" :src="i | getUrl" />
                                </div>
                            </article>
                        </div>

                        <article style="float : left">
                            <div>
                                <i class="far fa-heart" aria-hidden="true" style="display : none ; margin: 10px; color:black; width:10%; height:10%"></i>
                                <button class="wpO6b " type="button">
                                    <svg aria-label="좋아요 취소" class="_8-yf5 " fill="#ed4956" height="24" viewBox="0 0 48 48" width="24">
                                        <path
                                            clip-rule="evenodd"
                                            d="M35.3 35.6c-9.2 8.2-9.8 8.9-11.3 8.9s-2.1-.7-11.3-8.9C6.5 30.1.5 25.6.5 17.8.5 9.9 6.4 3.5 13.7 3.5 20.8 3.5 24 8.8 24 8.8s3.2-5.3 10.3-5.3c7.3 0 13.2 6.4 13.2 14.3 0 7.8-6.1 12.3-12.2 17.8z"
                                            fill-rule="evenodd"
                                        ></path>
                                    </svg>
                                </button>

                                <button class="wpO6b " type="button">
                                    <svg aria-label="좋아요" class="_8-yf5 " fill="#262626" height="24" viewBox="0 0 48 48" width="24">
                                        <path
                                            clip-rule="evenodd"
                                            d="M34.3 3.5C27.2 3.5 24 8.8 24 8.8s-3.2-5.3-10.3-5.3C6.4 3.5.5 9.9.5 17.8s6.1 12.4 12.2 17.8c9.2 8.2 9.8 8.9 11.3 8.9s2.1-.7 11.3-8.9c6.2-5.5 12.2-10 12.2-17.8 0-7.9-5.9-14.3-13.2-14.3zm-1 29.8c-5.4 4.8-8.3 7.5-9.3 8.1-1-.7-4.6-3.9-9.3-8.1-5.5-4.9-11.2-9-11.2-15.6 0-6.2 4.6-11.3 10.2-11.3 4.1 0 6.3 2 7.9 4.2 3.6 5.1 1.2 5.1 4.8 0 1.6-2.2 3.8-4.2 7.9-4.2 5.6 0 10.2 5.1 10.2 11.3 0 6.7-5.7 10.8-11.2 15.6z"
                                            fill-rule="evenodd"
                                        ></path>
                                    </svg>
                                </button>

                                <button v-on:click="check()" class="fas fa-heart" aria-hidden="true" style="margin: 10px; color:black; width:10%; height:10%"></button>
                                <i class="fas fa-comments" aria-hidden="true" style="margin: 10px ;color:black; width:10%; height:10%"></i>
                            </div>
                        </article>
                        <div class="divi">
                            안녕하세요 테스트 입스트안녕하세요안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요안녕하세안녕하세요 테스트
                            입스트안녕하세요안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세안녕하세요 테스트 입스트안녕하세요안녕하세요 안녕하세요
                            안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세안녕하세요 테스트 입스트안녕하세요안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세안녕하세요
                            테스트 입스트안녕하세요안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세안녕하세요 테스트 입스트안녕하세요안녕하세요
                            안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세안녕하세요 테스트 입스트안녕하세요안녕하세요 안녕하세요 안녕하세요 안녕하세요
                            안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세안녕하세요 테스트 입스트안녕하세요안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요
                            안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 테스트 입스트안녕하세요안녕하세요 안녕하세 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요
                            안녕하세요 안녕하세안녕하세요 테스트 입스트안녕하세요안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세안녕하세요 테스트
                            입스트안녕하세요안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요
                            안녕하세요 안녕하세요 안녕하세요 테스트 입스트 입스트 입스트 입스트 입스트 입니다.안녕하세요 테스트 입니다. 안녕하세요 테스트 입니다. 안녕하세요 테스트 입니다.
                        </div>
                        <!-- </section> -->
                        <!-- <button @click="showDialog = false">뒤로</button> -->
                    </md-dialog>
                </article>
            </div>
        </div>

        <md-button class="md-primary md-raised" @click="showDialog = true">Show Dialog</md-button>
    </div>
</template>

<script>
export default {
    //name: 'Board',
    showModal: false,
    name: 'DialogCustom',
    data: () => ({
        showDialog: false
    }),
    filters: {
        getUrl(picNumber) {
            console.log(picNumber);
            return require(`../../assets/images/${picNumber}.jpg`);
        }
    },
    methods: {
        showDetail() {
            this.$router.push('/user/detailBoard');
        },
        showmodal() {
            alert('asdasd');
            this.showModal = true;
        },
        closemodal() {
            this.showModal = false;
        },
        check() {
            alert('asd');
        }
    }
};
</script>

<style scoped>
@import url(https://fonts.googleapis.com/css?family=Raleway);

*,
*:before,
*:after {
    margin: 0;
    padding: 0;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
}
.wrapper {
    width: 90%;
    height: 60%;
    /* margin: 0 auto; */
    /* text-align: center; */
    /* float: left; */
    background-color: white;
}
.divide {
    display: inline-block;
    width: 100%;
    height: 70%;
}
.divide2 {
    display: inline-block;
    width: 30%;
    background-color: white;
    height: 100%;
}
.divi {
    color: black;
}

body {
    background: #f9f9f9;
    font-size: 16px;
    font-family: 'Raleway', sans-serif;
}

.main-title {
    color: #2d2d2d;
    text-align: center;
    text-transform: capitalize;
    padding: 0.7em 0;
}

/*.container {
    padding: 0.5em 0;
    float: left;
    width: auto;
    height: 300px;
} */
@media screen and (max-width: 640px) {
    .container {
        display: block;
        width: 50%;
    }
}
.md-dialog {
    width: 30%;
    height: 80%;
    background-color: white;
}

.container .title {
    color: #1a1a1a;
    text-align: center;
    margin-bottom: 10px;
}

.content {
    position: relative;
    width: 90%;
    max-width: 400px;
    margin: auto;
    overflow: hidden;
}

.content .content-overlay {
    background: rgba(0, 0, 0, 0.7);
    position: absolute;
    height: 99%;
    width: 100%;
    left: 0;
    top: 0;
    bottom: 0;
    right: 0;
    opacity: 0;
    -webkit-transition: all 0.4s ease-in-out 0s;
    -moz-transition: all 0.4s ease-in-out 0s;
    transition: all 0.4s ease-in-out 0s;
}

.content:hover .content-overlay {
    opacity: 1;
}

.content-image {
    max-width: 100%;
    height: auto;
}

.content-details {
    position: absolute;
    text-align: center;
    padding-left: 1em;
    padding-right: 1em;
    width: 100%;
    top: 50%;
    left: 50%;
    opacity: 0;
    -webkit-transform: translate(-50%, -50%);
    -moz-transform: translate(-50%, -50%);
    transform: translate(-50%, -50%);
    -webkit-transition: all 0.3s ease-in-out 0s;
    -moz-transition: all 0.3s ease-in-out 0s;
    transition: all 0.3s ease-in-out 0s;
}

.content:hover .content-details {
    top: 50%;
    left: 50%;
    opacity: 1;
}

.content-details h3 {
    color: #fff;
    font-weight: 500;
    letter-spacing: 0.15em;
    margin-bottom: 0.5em;
    text-transform: uppercase;
}

.content-details p {
    color: #fff;
    font-size: 0.8em;
}

.fadeIn-bottom {
    top: 80%;
}

.fadeIn-top {
    top: 20%;
}

.fadeIn-left {
    left: 20%;
}

.fadeIn-right {
    left: 80%;
}

body {
    padding: 20px;
    font-family: sans-serif;
    background: #f2f2f2;
}
img {
    width: 100%; /* need to overwrite inline dimensions */
    height: auto;
}
h2 {
    margin-bottom: 0.5em;
}
.grid-container {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
    grid-gap: 1em;
}

/* hover styles */
.location-listing {
    height: auto;
    position: relative;
}

.location-image {
    max-width: 100%;
    height: auto;
    line-height: 0;
    overflow: hidden;
}

.location-image img {
    filter: blur(0px);
    transition: filter 0.3s ease-in;
    transform: scale(1.1);
}

.location-title {
    font-size: 1.5em;
    font-weight: bold;
    text-decoration: none;
    z-index: 1;
    position: absolute;
    height: 100%;
    width: 100%;
    top: 0;
    left: 0;
    opacity: 0;
    transition: opacity 0.5s;
    background: rgba(90, 0, 10, 0.4);
    color: white;

    /* position the text in t’ middle*/
    display: flex;
    align-items: center;
    justify-content: center;
}

.location-listing:hover .location-title {
    opacity: 1;
}

.location-listing:hover .location-image img {
    filter: blur(2px);
}

/* for touch screen devices */
@media (hover: none) {
    .location-title {
        opacity: 1;
    }
    .location-image img {
        filter: blur(2px);
    }
}
/*modal style */
</style>
