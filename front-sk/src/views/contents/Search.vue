<template>
    <div style=" width:80%; margin:0 auto;">
        <div class="search-bar" style="display: block;">
            <input type="text" placeholder="검색어를 입력해주세요." v-model="keyword" @keyup.enter="searchProduct()" />
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
        <div id="shopping_chart">
            <ul class="product-list">
                <div style="text-align:center;" v-if="users.length == 0 && isTime">
                    <md-progress-spinner md-mode="indeterminate"></md-progress-spinner>
                </div>
                <li v-for="(item, index) in users" v-bind:key="index">
                    <span class="product-img">
                        <img style="width : 150px; height :150px" v-bind:src="item.image" alt="" />
                    </span>

                    <span class="product-title" style="cursor:pointer" @click="moveReaview(item.productName)">{{ item.productName }}</span>
                    <a target="_blank" v-bind:href="item.link"
                        ><span class="product-title">{{ item.link }}</span></a
                    >
                    <div style="font-size : 1em; margin: 2%;" class="product-count">{{ item.price }} 원</div>
                    <div @click="deleteProduct(item)" style="cursor : pointer; margin: 2%;" class="material-icons" v-show="item.isLike">
                        star
                    </div>
                    <div
                        @click="addProduct(item)"
                        style="font-size : 2em; cursor : pointer; margin: 2%;"
                        class="material-icons"
                        v-show="!item.isLike"
                    >
                        star_border
                    </div>
                </li>
            </ul>
        </div>
    </div>
</template>

<style scoped>
.product-list li {
    border: 1px solid #ccc;
    list-style: none;
    overflow: auto;
    border-bottom: 1px dashed #0073ff;
}
.product-list span[class|='product'] {
    font-family: Microsoft JhengHei;
    margin: 2%;
    float: left;
}
.product-list li:last-child {
    border-bottom: 1px solid #ccc;
    padding: 2% 5%;
}
.product-check {
    float: left;
}
.product-img {
    border: 1px solid #eee;
    padding: 2px;
}
.product-title {
    text-decoration: underline;
    color: black;
    width: 60%;
}
.product-price {
    float: right;
    font-weight: bold;
    width: 10%;
}
.product-count input {
    float: left;
    width: 25%;
}
</style>

<script>
import ProductApi from '../../apis/ProductApi';
export default {
    data() {
        return {
            tempsub1: '',
            tempsub2: '',
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
        moveReaview(e) {
            this.$router.push('/contents/ProductList/' + e);
        },
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
                        this.users[i]['isLike'] = this.likeList[i];
                    }
                    console.log(this.users);
                },
                error => {
                    console.log(error);
                }
            );
            this.tempsub1 = this.sub1;
            this.tempsub2 = this.sub2;

            this.sub1 = '';
            this.sub2 = '';

            console.log('user ==>');
            console.log(this.users);
        },
        getImgUrl(pic) {
            return require('../../assets/images/' + pic);
        },
        addProduct(item) {
            console.log('item : ', item.productName);

            var data = {
                email: this.email,
                productName: item.productName,
                link: item.link,
                image: item.image,
                price: item.price
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
            this.researchProduct();
        },
        researchProduct() {
            this.users = [];
            this.isTime = true;
            if (this.tempsub1 == '' && this.tempsub2 == '') {
                this.keyword;
            } else {
                this.keyword = this.tempsub1 + ' ' + this.tempsub2;
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
                        this.users[i]['isLike'] = this.likeList[i];
                    }
                    console.log(this.users);
                },
                error => {
                    console.log(error);
                }
            );

            console.log('user ==>');
            console.log(this.users);
        },
        deleteProduct(item) {
            var data = {
                email: this.email,
                productName: item.productName,
                link: item.link,
                image: item.image,
                price: item.price
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
            this.researchProduct();
        }
    },
    create() {
        this.email = sessionStorage.getItem('email');
        if (sessionStorage.getItem('email') == null) {
            this.$router.push('/');
        }
    }
};
</script>
