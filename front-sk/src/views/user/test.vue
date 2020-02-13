<template>
    <div>
        <div class="container_q">
            <div class="profile">
                <div class="profile-image">
                    <img src="http://192.168.100.90:8080/image/프사6.jpg" style="width:150px; height:150px" alt class="portrait" />
                </div>

                <div class="profile-user-settings">
                    <h1 class="profile-user-name">janedoe_</h1>

                    <button class="btn profile-edit-btn">Edit Profile</button>

                    <button class="btn profile-settings-btn" aria-label="profile settings"><i class="fas fa-cog" aria-hidden="true"></i></button>
                </div>

                <div class="profile-stats">
                    <ul>
                        <li><span class="profile-stat-count">164</span> posts</li>
                        <li><span class="profile-stat-count">188</span> followers</li>
                        <li><span class="profile-stat-count">206</span> following</li>
                    </ul>
                </div>

                <div class="profile-bio">
                    <p><span class="profile-real-name">Jane Doe</span> Lorem ipsum dolor sit, amet consectetur adipisicing elit 📷✈️🏕️</p>
                </div>
            </div>
            <!-- End of profile section -->

            <!-- End of container -->
        </div>
    </div>
</template>

<style scoped>
#rig {
    max-width: 1000px;
    margin-top: 5px;
    margin: 20px auto; /*center aligned*/
    padding: 0;
    font-size: 0; /* Remember to change it back to normal font size if have captions */
    list-style: none;
    /*background-color: #000;*/
}
#rig li {
    display: inline-block;
    *display: inline; /*for IE6 - IE7*/
    width: 75%;
    vertical-align: middle;
    box-sizing: border-box;
    margin: 0;
    padding: 0;
}

/* The wrapper for each item */
.rig-cell {
    margin: 10px 9px 10px 9px;
    /*box-shadow: 0 0 6px rgba(0, 0, 0, 0.3);*/
    display: block;
    position: relative;
    overflow: hidden;
}

/* If have the image layer */
.rig-img {
    display: block;
    width: 100%;
    height: auto;
    border: none;
    transform: scale(1);
    transition: all 1s;
}

#rig li:hover .rig-img {
    transform: scale(1.05);
}

/* If have the overlay layer */
.rig-overlay {
    position: absolute;
    display: block;
    top: 0;
    left: 0;
    bottom: 0;
    right: 0;
    margin: auto;
    background: #3dc0f1 no-repeat center 20%;
    background-size: 550px 550px;
    opacity: 0;
    filter: alpha(opacity=0); /*For IE6 - IE8*/
    transition: all 0.6s;
}
#rig li:hover .rig-overlay {
    opacity: 0.8;
}

/* If have captions */
.rig-text {
    display: block;
    padding: 0 30px;
    box-sizing: border-box;
    position: absolute;
    left: 0;
    width: 100%;
    text-align: center;
    text-transform: capitalize;
    font-size: 18px;
    font-weight: bold;
    font-family: 'Oswald', sans-serif;
    font-weight: normal !important;
    top: 40%;
    color: white;
    opacity: 0;
    filter: alpha(opacity=0); /*For older IE*/
    transform: translateY(-20px);
    transition: all 0.3s;
}
#rig li:hover .rig-text {
    transform: translateY(0px);
    opacity: 0.9;
}

@media (max-width: 9000px) {
    #rig li {
        width: 25%;
    }
}

@media (max-width: 700px) {
    #rig li {
        width: 33.33%;
    }
}

@media (max-width: 550px) {
    #rig li {
        width: 40%;
    }
}

/* Base Styles */
:root {
    font-size: 10px;
}

*,
*::before,
*::after {
    box-sizing: border-box;
}

body {
    font-family: 'Open Sans', Arial, sans-serif;
    min-height: 100vh;
    background-color: #fafafa;
    color: #262626;
    padding-bottom: 3rem;
}

img {
    display: block;
    max-width: 250px;
    max-height: 250px;
}

.container_p {
    max-width: 93.5rem;
    margin: 0 auto;
    padding: 0 2rem;
}

.btn {
    display: inline-block;
    font: inherit;
    background: none;
    border: none;
    color: inherit;
    padding: 0;
    cursor: pointer;
}

.btn:focus {
    outline: 0.5rem auto #4d90fe;
}

.visually-hidden {
    position: absolute !important;
    height: 1px;
    width: 1px;
    overflow: hidden;
    clip: rect(1px, 1px, 1px, 1px);
}

/* Profile Section */

.profile {
    padding: 5rem 0;
    max-width: 1000px;
    margin: 0 auto;
    border-bottom: 1px solid rgba(0, 0, 0, 0.3);
    /* background-color: rgba(0, 0, 0, 0.3); */
}

.profile::after {
    content: '';
    display: block;
    clear: both;
}

.profile-image {
    float: left;
    width: calc(33.333% - 1rem);
    display: flex;
    justify-content: center;
    align-items: center;
    margin-right: 3rem;
}

.profile-image img {
    border-radius: 50%;
}

.profile-user-settings,
.profile-stats,
.profile-bio {
    float: left;
    width: calc(66.666% - 2rem);
}

.profile-user-settings {
    margin-top: 1.1rem;
}

.profile-user-name {
    display: inline-block;
    font-size: 3.2rem;
    font-weight: 300;
}

.profile-edit-btn {
    font-size: 1.4rem;
    line-height: 1.8;
    border: 0.1rem solid white;
    border-radius: 0.3rem;
    padding: 0 2.4rem;
    margin-left: 2rem;
    background-color: #87ceeb;
    color: white;
}

.profile-settings-btn {
    font-size: 2rem;
    margin-left: 1rem;
    text-align: left;
}

.profile-stats {
    margin-top: 2.3rem;
}

.profile-stats li {
    display: inline-block;
    font-size: 1.6rem;
    line-height: 1.5;
    margin-right: 4rem;
    cursor: pointer;
}

.profile-stats li:last-of-type {
    margin-right: 0;
}

.profile-bio {
    font-size: 1.6rem;
    font-weight: 400;
    line-height: 1.5;
    margin-top: 2.3rem;
}

.profile-real-name,
.profile-stat-count,
.profile-edit-btn {
    font-weight: 600;
}

/* Loader */

.loader {
    width: 5rem;
    height: 5rem;
    border: 0.6rem solid #999;
    border-bottom-color: transparent;
    border-radius: 50%;
    margin: 0 auto;
    animation: loader 500ms linear infinite;
}

/* Media Query */

@media screen and (max-width: 40rem) {
    .profile {
        display: flex;
        flex-wrap: wrap;
        padding: 4rem 0;
    }

    .profile::after {
        display: none;
    }

    .profile-image,
    .profile-user-settings,
    .profile-bio,
    .profile-stats {
        float: none;
        width: auto;
    }

    .profile-image img {
        width: 7.7rem;
    }

    .profile-user-settings {
        flex-basis: calc(100% - 10.7rem);
        display: flex;
        flex-wrap: wrap;
        margin-top: 1rem;
    }

    .profile-user-name {
        font-size: 2.2rem;
    }

    .profile-edit-btn {
        order: 1;
        padding: 0;
        text-align: center;
        margin-top: 1rem;
    }

    .profile-edit-btn {
        margin-left: 0;
    }

    .profile-bio {
        font-size: 1.4rem;
        margin-top: 1.5rem;
    }

    .profile-edit-btn,
    .profile-bio,
    .profile-stats {
        flex-basis: 100%;
    }

    .profile-stats {
        order: 1;
        margin-top: 1.5rem;
    }

    .profile-stats ul {
        display: flex;
        text-align: center;
        padding: 1.2rem 0;
        border-top: 0.1rem solid #dadada;
        border-bottom: 0.1rem solid #dadada;
    }

    .profile-stats li {
        font-size: 1.4rem;
        flex: 1;
        margin: 0;
    }

    .profile-stat-count {
        display: block;
    }
}

/* Spinner Animation */

@keyframes loader {
    to {
        transform: rotate(360deg);
    }
}

/*

The following code will only run if your browser supports CSS grid.

Remove or comment-out the code block below to see how the browser will fall-back to flexbox & floated styling. 

*/

@supports (display: grid) {
    .profile {
        display: grid;
        grid-template-columns: 1fr 2fr;
        grid-template-rows: repeat(3, auto);
        grid-column-gap: 3rem;
        align-items: center;
    }

    .profile-image {
        grid-row: 1 / -1;
    }

    .gallery {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(22rem, 1fr));
        grid-gap: 2rem;
    }

    .profile-image,
    .profile-user-settings,
    .profile-stats,
    .profile-bio,
    .gallery-item,
    .gallery {
        width: auto;
        margin: 0;
    }

    @media (max-width: 40rem) {
        .profile {
            grid-template-columns: auto 1fr;
            grid-row-gap: 1.5rem;
        }

        .profile-image {
            grid-row: 1 / 2;
        }

        .profile-user-settings {
            display: grid;
            grid-template-columns: auto 1fr;
            grid-gap: 1rem;
        }

        .profile-edit-btn,
        .profile-stats,
        .profile-bio {
            grid-column: 1 / -1;
        }

        .profile-user-settings,
        .profile-edit-btn,
        .profile-settings-btn,
        .profile-bio,
        .profile-stats {
            margin: 0;
        }
    }
}
</style>
