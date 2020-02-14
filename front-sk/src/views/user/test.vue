<template>
    <div>
        <header>
            <br /><br /><br />
            <div class="container2">
                <div class="profile2">
                    <div class="profile2-image">
                        <img src="http://192.168.100.90:8080/image/프사6.jpg" style="width:150px; height:150px" alt class="portrait" />
                    </div>

                    <div class="profile2-user-settings">
                        <h1 class="profile2-user-name">남 현 준</h1>

                        <button class="btn2 profile2-edit-btn">Edit Profile</button>
                    </div>

                    <div class="profile2-stats">
                        <ul>
                            <li><span class="profile2-stat-count">164</span> posts</li>
                            <li><span class="profile2-stat-count">188</span> followers</li>
                            <li><span class="profile2-stat-count">206</span> following</li>
                        </ul>
                    </div>

                    <div class="profile2-bio">
                        <p><span class="profile2-real-name">Jane Doe</span> Lorem ipsum dolor sit, amet consectetur adipisicing elit</p>
                    </div>
                </div>
                <!-- End of profile section -->
            </div>
            <!-- End of container -->
        </header>
    </div>
</template>

<style scoped>
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
}

.container2 {
    max-width: 93.5rem;
    margin: 0 auto;
    padding: 0 2rem;
}

.btn2 {
    display: inline-block;
    font: inherit;
    background: none;
    border: none;
    color: inherit;
    padding: 0;
    cursor: pointer;
}

.btn2:focus {
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

.container2 {
    max-width: 93.5rem;
    margin: 0 auto;
    padding: 0 2rem;
}

.profile2 {
    padding: 5rem 0;
}

.profile2::after {
    content: '';
    display: block;
    clear: both;
}

.profile2-image {
    float: left;
    width: calc(33.333% - 1rem);
    display: flex;
    justify-content: center;
    align-items: center;
    margin-right: 3rem;
}

.profile2-image img {
    margin-right: 3rem;
    margin-left: 3rem;
    border-radius: 50%;
}

.profile2-user-settings,
.profile2-stats,
.profile2-bio {
    float: left;
    width: calc(66.666% - 2rem);
}

.profile2-user-settings {
    margin-top: 1.1rem;
}

.profile2-user-name {
    display: inline-block;
    font-size: 2.1rem;
    /* font-size: 5vw;
    font-size: 5wh; */
    font-weight: 300;
}

.profile2-edit-btn {
    font-size: 1.4rem;
    line-height: 1.8;
    border: 0.1rem solid #dbdbdb;
    border-radius: 0.3rem;
    padding: 0 2.4rem;
    margin-left: 2rem;
}

.profile2-settings-btn {
    font-size: 2rem;
    margin-left: 1rem;
}

.profile2-stats {
    margin-top: 2.3rem;
}

.profile2-stats li {
    display: inline-block;
    font-size: 1.3rem;
    line-height: 1.5;
    margin-right: 4rem;
    cursor: pointer;
}

.profile2-stats li:last-of-type {
    margin-right: 0;
}

.profile2-bio {
    font-size: 1.3rem;
    font-weight: 400;
    line-height: 1.5;
    margin-top: 2.3rem;
}

.profile2-real-name,
.profile2-stat-count,
.profile2-edit-btn {
    font-weight: 100;
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

@media screen and (max-width: 30rem) {
    .profile2 {
        display: flex;
        flex-wrap: wrap;
        padding: 4rem 0;
    }

    .profile2::after {
        display: none;
    }

    .profile2-image,
    .profile2-user-settings,
    .profile2-bio,
    .profile2-stats {
        float: none;
        width: auto;
    }

    .profile2-image img {
        width: 7.7rem;
    }

    .profile2-user-settings {
        flex-basis: calc(100% - 10.7rem);
        display: flex;
        flex-wrap: wrap;
        margin-top: 1rem;
    }

    .profile2-user-name {
        font-size: 2.2rem;
    }

    .profile2-edit-btn {
        order: 1;
        padding: 0;
        text-align: center;
        margin-top: 1rem;
    }

    .profile2-edit-btn {
        margin-left: 0;
    }

    .profile2-bio {
        font-size: 1.4rem;
        margin-top: 1.5rem;
    }

    .profile2-edit-btn,
    .profile2-bio,
    .profile2-stats {
        flex-basis: 100%;
    }

    .profile2-stats {
        order: 1;
        margin-top: 1.5rem;
    }

    .profile2-stats ul {
        display: flex;
        text-align: center;
        padding: 1.2rem 0;
        border-top: 0.1rem solid #dadada;
        border-bottom: 0.1rem solid #dadada;
    }

    .profile2-stats li {
        font-size: 1.4rem;
        flex: 1;
        margin: 0;
    }

    .profile2-stat-count {
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
    .profile2 {
        display: grid;
        grid-template-columns: 1fr 2fr;
        grid-template-rows: repeat(3, auto);
        grid-column-gap: 3rem;
        align-items: center;
    }

    .profile2-image {
        grid-row: 1 / -1;
    }

    .gallery {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(22rem, 1fr));
        grid-gap: 2rem;
    }

    .profile2-image,
    .profile2-user-settings,
    .profile2-stats,
    .profile2-bio,
    .gallery-item,
    .gallery {
        width: auto;
        margin: 0;
    }

    @media (max-width: 40rem) {
        .profile2 {
            grid-template-columns: auto 1fr;
            grid-row-gap: 1.5rem;
        }

        .profile2-image {
            grid-row: 1 / 2;
        }

        .profile2-user-settings {
            display: grid;
            grid-template-columns: auto 1fr;
            grid-gap: 1rem;
        }

        .profile2-edit-btn,
        .profile2-stats,
        .profile2-bio {
            grid-column: 1 / -1;
        }

        .profile2-user-settings,
        .profile2-edit-btn,
        .profile2-settings-btn,
        .profile2-bio,
        .profile2-stats {
            margin: 0;
        }
    }
}
</style>
