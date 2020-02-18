<template>
    <div>
        <!-- <form action="#" @submit.prevent="searchProduct()" enctype="multipart/form-data" id="insertMemberForm"> -->
        <div class="search-bar" style="display: block;">
            <input type="text" placeholder="검색어를 입력해주세요." v-model="keyword" />
            <button @click="searchProduct()">
                <md-icon>search</md-icon>
            </button>
        </div>
        <div class="quest">검색할 상품에 대한 키워드를 선택해주세요.</div>
        <div>
            <v-row justify="space-around">
                <v-col cols="24">
                    <v-chip-group column active-class="blue darken-1 white--text" id="sub1" name="sub1" v-model="sub1">
                        <v-chip value="10대">10대</v-chip>
                        <v-chip value="20대">20대</v-chip>
                        <v-chip value="30대">30대</v-chip>
                        <v-chip value="부모님">부모님</v-chip>
                        <v-chip value="심플한">심플한</v-chip>
                        <v-chip value="귀여운">귀여운</v-chip>
                        <v-chip value="센스있는">센스있는</v-chip>
                        <v-chip value="가성비 좋은">가성비 좋은</v-chip>
                        <v-chip value="여자">여자</v-chip>
                        <v-chip value="냠자">남자</v-chip>
                        <v-chip value="커플">커플</v-chip>
                    </v-chip-group>
                </v-col>
            </v-row>
            <v-row justify="space-around">
                <v-col cols="24">
                    <v-chip-group column active-class="blue darken-1 white--text" id="sub2" name="sub2" style="padding: 0" v-model="sub2">
                        <v-chip value="생일선물">생일선물</v-chip>
                        <v-chip value="기념일">기념일</v-chip>
                        <v-chip value="가방">가방</v-chip>
                        <v-chip value="신발">신발</v-chip>
                        <v-chip value="상의">상의</v-chip>
                        <v-chip value="하의">하의</v-chip>
                        <v-chip value="운동기구">운동기구</v-chip>
                        <v-chip value="건강식품">건강식품</v-chip>
                    </v-chip-group>
                </v-col>
            </v-row>
        </div>

        <div>
            <md-progress-spinner md-mode="indeterminate"></md-progress-spinner>
        </div>

        <md-table v-model="users" md-sort="name" md-sort-order="asc" md-card md-fixed-header>
            <div style="text-align:center;" v-if="users.length == 0 && isTime">
                <md-progress-spinner md-mode="indeterminate"></md-progress-spinner>
            </div>
            <md-table-row slot="md-table-row" slot-scope="{ item }">
                <!-- <md-table-cell md-label="ID" md-sort-by="id" md-numeric>{{ item.id }}</md-table-cell> -->
                <!-- <td><img v-bind:src="item.image" /></td> -->
                <md-table-cell md-label="Image" md-sort-by="image"><img v-bind:src="item.image"/></md-table-cell>
                <md-table-cell md-label="ProductName" md-sort-by="productName" width="50%"
                    >{{ item.productName }} <br />
                    <a v-bind:href="item.link">{{ item.link }}</a></md-table-cell
                >
                <!-- <md-table-cell md-label="Link" md-sort-by="link">{{ item.link }}</md-table-cell> -->
                <md-table-cell md-label="Price" md-sort-by="price" width="20%">{{ item.price }}</md-table-cell>
                <md-table-cell md-sort-by="Like" md-label="Like" width="10%">
                    <md-button @click="addProduct(item.productName)" class="md-icon-button md-list-action" v-show="!item.isLike">
                        <md-icon class="md-primary">star_border</md-icon>
                    </md-button>

                    <md-button @click="deleteProduct(item.productName)" class="md-icon-button md-list-action" v-show="item.isLike">
                        <md-icon class="md-primary">star</md-icon>
                    </md-button>
                </md-table-cell>
            </md-table-row>
        </md-table>
    </div>
</template>
<script>
import ProductApi from '../../apis/ProductApi';
export default {
    name: 'TableFixed',
    data() {
        return {
            isTime: false,
            product: [], // 물건 리스트
            likeList: [], //찜 한거 목록 리스트
            selected: '',
            sub1: '',
            sub2: '',
            keyword: '',
            email: '',
            image1: '3.jpg',
            users: []
        };
    },
    watch: {
        sub1: function() {
            if (this.sub1 == null) {
                this.keyword = this.sub2;
            } else if (this.sub2 == null) {
                this.keyword = this.sub1;
            } else {
                this.keyword = this.sub1 + ' ' + this.sub2;
            }
        },
        sub2: function() {
            if (this.sub1 == null) {
                this.keyword = this.sub2;
            } else if (this.sub2 == null) {
                this.keyword = this.sub1;
            } else {
                this.keyword = this.sub1 + ' ' + this.sub2;
            }
        },
        keyword: function() {
            if (this.keyword == null) {
                this.sub1 = '';
                this.sub2 = '';
            }
        }
    },
    methods: {
        searchProduct() {
            this.users = [];
            this.isTime = true;
            if (this.sub1 == '' && this.sub2 == '') {
                this.keyword;
            } else {
                this.keyword = this.sub1 + ' ' + this.sub2;
            }
            this.email = sessionStorage.getItem('email');
            let { keyword, email } = this;
            let data = {
                keyword,
                email
            };
            ProductApi.getAPI(
                data,
                res => {
                    this.users = res.data.object.list;
                    console.log('user: ', this.users);
                    this.likeList = res.data.object.likeCheckList;
                    console.log('likeList: ', this.likeList);

                    for (var i = 0; i < this.likeList.length; i++) {
                        // this.users[i].isLike = this.likeList[i];
                        this.users[i]['isLike'] = this.likeList[i];
                    }
                    console.log(this.users);
                },
                error => {
                    console.log(error);
                }
            );
            this.sub1 = '';
            this.sub2 = '';

            console.log('user ==>');
            console.log(this.users);
        },
        getImgUrl(pic) {
            return require('../../assets/images/' + pic);
        },
        addProduct(name) {
            var data = {
                email: this.email,
                productName: name
            };

            ProductApi.addBookmark(
                data,
                res => {
                    console.log(res);
                },
                error => {
                    console.log(error);
                }
            );
        },
        deleteProduct(name) {
            var data = {
                email: this.email,
                productName: name
            };

            ProductApi.deleteBookmark(
                data,
                res => {
                    console.log(res);
                },
                error => {
                    console.log(error);
                }
            );
        }
    },
    create() {
        this.email = sessionStorage.getItem('email');
    }
};
</script>
