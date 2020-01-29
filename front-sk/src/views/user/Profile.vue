<template>
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
