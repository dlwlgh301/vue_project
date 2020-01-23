<template>
    <div class="container">
        <link rel="stylesheet" href="//fonts.googleapis.com/css?family=Roboto:400,500,700,400italic|Material+Icons" />
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
    }
};
</script>
