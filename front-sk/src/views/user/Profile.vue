<template>
<<<<<<< HEAD
    <div>
        <h3 class="title">My Profile</h3>

        <section class="profile">
            <img src="../../assets/images/tm-easy-profile.jpg" alt="" class="portrait" />

            <button class="followBtn" style="color:white">팔로우</button>
            <div class="left_col">
                <div class="followers">
                    <div class="follow_count">18,541</div>
                    Followers
                </div>
                <div class="following">
                    <div class="follow_count">181</div>
                    Following
                </div>
            </div>
            <div class="right_col">
                <h2 class="name">남 현 준</h2>
                <h3 class="location">부산광역시</h3>
                <ul class="contact_information">
                    <li class="work">Developer</li>
                    <li class="website"><a class="nostyle" href="https://www.instagram.com/nam_vv2/?hl=ko">nam_vv2</a></li>
                    <li class="mail">wns4773@gmail.com</li>
                    <li class="phone">010-2979-6768</li>
                    <li class="resume" v-on:click="board">게시글 보기</li>
                </ul>
            </div>

            <ul class="tags">
                <li><a href="https://www.instagram.com/explore/tags/의류/">#의류</a></li>
                <li><a href="https://www.instagram.com/explore/tags/치킨/">#식품</a></li>
                <li><a href="https://www.instagram.com/explore/tags/가전제품/">#가전제품</a></li>
                <li><a href="https://www.instagram.com/explore/tags/생활용품/">#생활용품</a></li>
                <li><a href="https://www.instagram.com/explore/tags/스포츠용품/">#스포츠용품</a></li>
                <li><a href="https://www.instagram.com/explore/tags/의약품/">#의약품</a></li>
            </ul>
        </section>

        <!-- <body>
            <div class="portfoliocard">
                <div class="coverphoto"></div>
                <div class="profile_picture"></div>
                <div class="left_col">
                    <div class="followers">
                        <div class="follow_count">18,541</div>
                        Followers
                    </div>
                    <div class="following">
                        <div class="follow_count">181</div>
                        Following
                    </div>
                </div>
                <div class="right_col">
                    <h2 class="name">남 현 준</h2>
                    <h3 class="location">부산광역시</h3>
                    <ul class="contact_information">
                        <li class="work">Developer</li>
                        <li class="website"><a class="nostyle" href="https://www.instagram.com/nam_vv2/?hl=ko">nam_vv2</a></li>
                        <li class="mail">wns4773@gmail</li>
                        <li class="phone">010-2979-6768</li>
                        <li class="resume" v-on:click="board">게시글 보기</li>
                    </ul>
                </div>
            </div>
        </body> -->
    </div>
</template>
<script>
import '../../assets/css/style.scss';
import '../../assets/css/user.scss';
import '../../assets/css/profile.scss';
import PV from 'password-validator';

=======
    <div class="container">
        <div class="row">
            <div class="col-md-12 col-sm-12" style="text-align:center">
                <img src="../../assets/images/tm-easy-profile.jpg" width="300" />

                <h1 style="text-align:center">Your Profile</h1>
            </div>
        </div>

        <form method="POST">
            <div class="input-with-label">
                <input id="name" value="남현준" type="text" readonly />
                <label for="name">이름</label>
            </div>

            <div class="input-with-label">
                <input id="nickname" value="nam_vv2" type="text" readonly />
                <label for="nickname">닉네임</label>
            </div>

            <div class="input-with-label">
                <input id="phone" value="010-2979-6768" type="text" readonly />
                <label for="name">전화번호</label>
            </div>

            <div class="input-with-label">
                <input id="email" value="wns4773@gmail.com" type="text" readonly />
                <label for="name">이메일</label>
            </div>

            <input type="checkbox" name="chk_info" value="HTML" />
            HTML
            <fieldset>테두리의 두께, 모양, 색상을 지정합니다.</fieldset>
            <fieldset>
                <legend>관심분야</legend>
                <input type="checkbox" name="gender" value="남성" checked />
            </fieldset>

            <label for="name">소개</label>
            <div class="input-with-label">
                <textarea rows="15" cols="50" value="테스트" border="solid" />
            </div>

            <button class="btn btn--back btn--login" v-on:click="login">뒤로가기</button>
        </form>
        <div>
            <md-checkbox v-model="array" value="1">Array</md-checkbox>
            <md-checkbox v-model="array" value="2">Array</md-checkbox>
            <md-checkbox v-model="boolean">Boolean</md-checkbox>
            <md-checkbox v-model="string" value="my-checkbox">String</md-checkbox>
            <md-checkbox v-model="novalue">No Value</md-checkbox>
            <md-checkbox v-model="disabled" disabled>Disabled</md-checkbox>
            <md-checkbox v-model="obj" :value="obj1">Object 1</md-checkbox>
            <md-checkbox v-model="obj" :value="obj2">Object 2</md-checkbox>

            <md-checkbox v-model="indeterminate" indeterminate>Indeterminate</md-checkbox>

            <table>
                <tr>
                    <th>Array</th>
                    <th>Boolean</th>
                    <th>String</th>
                    <th>No Value</th>
                    <th>Object</th>
                </tr>

                <tr>
                    <td>{{ array }}</td>
                    <td>{{ boolean }}</td>
                    <td>{{ string }}</td>
                    <td>{{ novalue }}</td>
                    <td>{{ obj }}</td>
                </tr>
            </table>
        </div>
    </div>
</template>

<script>
import PV from 'password-validator';
import VueMaterial from 'vue-material';
import 'vue-material/dist/vue-material.min.css';
import Vue from 'vue';
import { MdButton, MdContent, MdTabs } from 'vue-material/dist/components';
import 'vue-material/dist/vue-material.min.css';
import 'vue-material/dist/theme/default.css';

Vue.use(MdButton);
Vue.use(MdContent);
Vue.use(MdTabs);
Vue.use(VueMaterial);
>>>>>>> b2eb50bc34fd9b4232fcb3bb45d34a46c9b87931
export default {
    data: () => {
        return {
            name: '',
            nickname: '',
            phone: '',
            email: '',
            interest: '',
            introduce: '',

            passwordSchema: new PV(),

            isSubmit: false,
            component: this
        };
<<<<<<< HEAD
        // },
        // filters: {
        //     salarydecimal(value) {
        //         return value.toFixed(2);
        //     }
        // },

        // methods: {
        //     addEmployee() {
        //         if (this.cname == '') {
        //             alert('아이디를 입력하세요.');
        //             return;
        //         }
        //         if (this.cdept_id == '') {
        //             alert('부서를 선택하세요.');
        //             return;
        //         }
        //         if (this.cmailid == '') {
        //             alert('이메일을 선택하세요.');
        //             return;
        //         }
        //         if (this.cmanager_id == '') {
        //             alert('관리자를 선택하세요.');
        //             return;
        //         }
        //         if (this.csalary == '') {
        //             alert('월급을 입력하세요.');
        //             return;
        //         }
        //         if (this.cstart_date == '') {
        //             alert('입사일을 선택하세요.');
        //             return;
        //         }
        //         if (this.ctitle == '') {
        //             alert('직책을 선택하세요.');
        //             return;
        //         }

        //         http.post('/addEmployee', {
        //             commission_pct: this.ccommission_pct,
        //             dept_id: this.cdept_id,
        //             mailid: this.cmailid,
        //             manager_id: this.cmanager_id,
        //             name: this.cname,
        //             salary: this.csalary,
        //             start_date: this.cstart_date,
        //             title: this.ctitle
        //         }).then(function(response) {
        //             if (response.data.state == 'succ') {
        //                 alert('회원가입 하였습니다.');
        //                 // alert(response.data.name + "이 실행되었습니다.");
        //             } else {
        //                 alert('회원가입 실패 하였습니다.');
        //                 // alert(response.data.name + "에 실패하였습니다.");
        //             }
        //         });
        //     }
    },
    methods: {
        board() {
            //요청 후에는 버튼 비활성화
            this.$router.push('/user/Board');
            // UserApi.requestLogin(
            //     data,
            //     res => {
            //         //통신을 통해 전달받은 값 콘솔에 출력
            //         console.log(res);

            //         //요청이 끝나면 버튼 활성화
            //         this.isSubmit = true;
            //     },
            //     // eslint-disable-next-line no-unused-vars
            //     error => {
            //         //요청이 끝나면 버튼 활성화
            //         this.isSubmit = true;
            //     }
            // );
        }
    }
};
</script>
<style scoped>
@media screen and (min-width: 480px) {
    body {
        background-color: lightgreen;
    }
}
body {
    background-image: url('http://urbanfragment.files.wordpress.com/2012/10/mount-fuji-highest-mountain-in-japan-osx-lion-wallpaper.jpg');
    background-size: 100% 100%;
    font-family: 'Open Sans', sans-serif;
}
a.nostyle {
    color: inherit;
    text-decoration: none;
    padding: 0;
    margin: 0;
}
div.portfoliocard {
    position: relative;
    height: 550%;
    width: 400px;
    background: rgba(255, 255, 255, 1);
    border: 1px solid rgba(0, 0, 0, 0.7);
    box-shadow: 0px -1px 3px rgba(0, 0, 0, 0.1), 0px 2px 6px rgba(0, 0, 0, 0.5);
    border-radius: 6px;
    margin: 10% auto;
    overflow: hidden;
    z-index: 100;
}
div.portfoliocard div.coverphoto {
    width: 100%;
    height: 120px;
    background: url('http://farm8.staticflickr.com/7149/6484148411_baf8d2e934_z.jpg');
    background-position: center center;
    border-top-right-radius: 5px;
    border-top-left-radius: 5px;
    border-bottom: 1px solid rgba(0, 0, 0, 0.1);
    box-shadow: inset 0px 3px 20px rgba(255, 255, 255, 0.3), 1px 0px 2px rgba(255, 255, 255, 0.7);
    z-index: 99;
}
div.portfoliocard div.left_col,
div.portfoliocard div.right_col {
    float: left;
    height: 340px;
}
div.portfoliocard div.left_col {
    width: 40%;
    padding-top: 85px;
    box-sizing: border-box;
}
div.portfoliocard div.right_col {
    width: 60%;
    background: rgba(245, 245, 245, 1);
    border-left: 1px solid rgba(230, 230, 230, 1);
    box-shadow: inset 0px 1px 1px rgba(255, 255, 255, 0.7);
    margin-left: -1px;
    border-bottom-right-radius: 5px;
}
div.portfoliocard div.profile_picture {
    width: 110px;
    height: 110px;
    background: rgba(255, 255, 255, 1);
    position: absolute;
    top: 65px;
    left: 40px;
    border-radius: 100%;
    background-image: url('../../assets/images/tm-easy-profile.jpg');
    background-size: 100% 100%;
    padding: 7px;
    border: 4px solid rgba(255, 255, 255, 1);
}
div.portfoliocard div.right_col h2.name {
    font-size: 30px;
    font-weight: 300;
    color: rgba(30, 30, 30, 1);
    padding: 0;
    margin: 20px 10px 0px 30px;
}
div.portfoliocard div.right_col h3.location {
    font-size: 15px;
    font-weight: 300;
    color: rgba(170, 170, 170, 1);
    padding: 0;
    margin: -5px 10px 10px 30px;
}
div.portfoliocard ul.contact_information {
    margin-top: 20px;
    padding-left: 30px;
    list-style-type: none;
}
div.portfoliocard ul.contact_information li {
    height: 25px;
    width: 180px;
    line-height: 25px;
    font-weight: 300;
    font-size: 15px;
    color: rgba(140, 140, 140, 1);
    text-shadow: 1px 1px 1px rgba(255, 255, 255, 0.8);
    padding: 5px 0px;
    background-repeat: no-repeat;
    background-size: 18px 18px;
    border-bottom: 1px solid rgba(0, 0, 0, 0.1);
    box-shadow: 0px 1px 1px rgba(255, 255, 255, 0.6);
    cursor: default;
}
div.portfoliocard ul.contact_information li:before {
    content: '';
    width: 25px;
    height: 25px;
    display: block;
    float: left;
    background-position: center;
    background-size: 18px 18px;
    background-repeat: no-repeat;
    margin-right: 5px;
    opacity: 0.7;
}
div.portfoliocard ul.contact_information li:hover:before {
    opacity: 1;
}
div.portfoliocard ul.contact_information li.work:before {
    background-image: url('http://schulzmarcel.de/x/icons/case_24.png');
}
div.portfoliocard ul.contact_information li.website:before {
    background-image: url('http://schulzmarcel.de/x/icons/globe_24.png');
}
div.portfoliocard ul.contact_information li.mail:before {
    background-image: url('http://schulzmarcel.de/x/icons/paper_plane_24.png');
}
div.portfoliocard ul.contact_information li.phone:before {
    background-image: url('http://schulzmarcel.de/x/icons/phone_24.png');
}
div.portfoliocard ul.contact_information li.resume:before {
    background-image: url('http://schulzmarcel.de/x/icons/inbox_24.png');
}
div.portfoliocard div.followers,
div.portfoliocard div.following {
    margin: 15px 0px 0px 35px;
    font-weight: 300;
    font-size: 16px;
    color: rgba(30, 30, 30, 1);
}
div.portfoliocard div.follow_count {
    font-weight: 400;
    font-size: 25px;
    color: rgba(140, 140, 140, 1);
}
</style>
=======
    }
};
</script>
>>>>>>> b2eb50bc34fd9b4232fcb3bb45d34a46c9b87931
