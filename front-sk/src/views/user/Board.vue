<template>
    <div>
        <h1 class="main-title">게시물</h1>

        <div class="child-page-listing">
            <div class="grid-container">
                <article class="location-listing" v-for="i in 9" v-bind:key="i">
                    <a class="location-title" href="#"> 좋아요 {{ i }}++ </a>

                    <div class="location-image">
                        <a href="#">
                            <img style="width:400px; height:300px;" :src="i | getUrl" />
                        </a>
                    </div>
                </article>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Board',
    filters: {
        getUrl(picNumber) {
            console.log(picNumber);
            return require(`../../assets/images/${picNumber}.jpg`);
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

.container {
    padding: 0.5em 0;
    float: left;
    width: auto;
    height: 300px;
}
@media screen and (max-width: 640px) {
    .container {
        display: block;
        width: 50%;
    }
}

@media screen and (min-width: 900px) {
    .container {
        width: 33.33333%;
    }
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
</style>
