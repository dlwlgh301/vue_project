<template>
    <div>
<<<<<<< HEAD
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

        <md-table v-model="users" md-sort="name" md-sort-order="asc" md-card md-fixed-header>
            <md-table-toolbar>
                <h1 class="md-title">Users</h1>
            </md-table-toolbar>
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
                    <md-button class="md-icon-button md-list-action">
                        <md-icon class="md-primary" v-show="item.isLike">star</md-icon>
                        <md-icon class="md-primary" v-show="!item.isLike">star_border</md-icon>
                    </md-button>
                </md-table-cell>
            </md-table-row>
        </md-table>

        <!-- <md-table v-model="likeList" md-sort="name" md-sort-order="asc" md-card md-fixed-header style="width:20%">
            <md-table-row slot="md-table-row">
                <md-table-cell md-label="Like" width="10%">
                    <md-button class="md-icon-button md-list-action">
                        <md-icon class="md-primary" v-show="likeList">star</md-icon>
                        <md-icon class="md-primary" v-show="!likeList">star_border</md-icon>
                    </md-button></md-table-cell
                >
            </md-table-row>
        </md-table> -->

        <!-- <md-table v-model="users" md-sort="name" md-sort-order="asc" md-card md-fixed-header>
            <md-table-toolbar>
                <h1 class="md-title">Users</h1>
            </md-table-toolbar>
            <md-table style="margin-top : 50px">
                <md-table-row>
                    <md-table-head md-label="Image" width="100px">Image</md-table-head>
                    <md-table-head md-label="ProductName" md-sort-by="productName" width="50%">ProductName</md-table-head>
                    <md-table-head md-label="Price" md-sort-by="price" width="20%">Price</md-table-head>
                    <md-table-head md-label="Like" md-sort-by="Like" width="10%">Like</md-table-head>
                </md-table-row>

                <md-table-row slot="md-table-row" v-for="(item, index) in product" v-bind:key="index">
                    {{ item.image }}   
                    <md-table-cell md-label="Image" width="100px"><img v-bind:src="item.image" alt class="People"/></md-table-cell>
                    <md-table-cell md-label="ProductName" md-sort-by="productName" width="50%"
                        >{{ item.productName }} <br />
                        <a v-bind:href="item.link">{{ item.link }}</a>
                    </md-table-cell>
                    <md-table-cell md-label="Price" md-sort-by="price" width="20%">{{ item.price }}</md-table-cell>
                    <md-table-cell md-label="Like" md-sort-by="Like" width="10%">
                        <md-button class="md-icon-button md-list-action">
                            <md-icon class="md-primary" v-show="likeList[index] == true">star</md-icon>
                            <md-icon class="md-primary" v-show="likeList[index] == false">star_border</md-icon>
                        </md-button>
                    </md-table-cell>
                </md-table-row>
            </md-table>
        </md-table> -->
=======
        <div class="wrapC" style="margin-top: 5rem;">
            <!-- <v-row>
                <v-col cols="12" style="border: 1px solid blue;">
                    <v-toolbar flat dense color="white">
                        <v-text-field hide-details prepend-icon="search" single-line v-model="myText"></v-text-field>
                    </v-toolbar>
                </v-col>
            </v-row> -->

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
                        <v-chip-group column active-class="blue darken-1 white--text" v-model="sub1">
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
                        <v-chip-group column active-class="blue darken-1 white--text" style="padding: 0" v-model="sub2">
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
                <md-list class="md-triple-line">
                    <md-list-item>
                        <md-avatar>
                            <img src="https://placeimg.com/40/40/people/1" alt="People" />
                        </md-avatar>

                        <div class="md-list-item-text">
                            <span>Ali Connors</span>
                            <span>Brunch this weekend?</span>
                            <p>I'll be in your neighborhood doing errands this week. Do you want to meet?</p>
                        </div>

                        <md-button class="md-icon-button md-list-action">
                            <md-icon class="md-primary">star</md-icon>
                        </md-button>
                    </md-list-item>

                    <md-divider class="md-inset"></md-divider>

                    <md-list-item>
                        <md-avatar>
                            <img src="https://placeimg.com/40/40/people/6" alt="People" />
                        </md-avatar>

                        <div class="md-list-item-text">
                            <span>me, Scott, Jennifer</span>
                            <span>Summer BBQ</span>
                            <p>Wish I could come, but I'm out of town this week. :(</p>
                        </div>

                        <md-button class="md-icon-button md-list-action">
                            <md-icon>star_border</md-icon>
                        </md-button>
                    </md-list-item>

                    <md-divider class="md-inset"></md-divider>

                    <md-list-item>
                        <md-avatar>
                            <img src="https://placeimg.com/40/40/people/5" alt="People" />
                        </md-avatar>

                        <div class="md-list-item-text">
                            <span>Sandra Adams</span>
                            <span>Oui oui</span>
                            <p>Do you have Paris recommendations? Have you visited good places?</p>
                        </div>

                        <md-button class="md-icon-button md-list-action">
                            <md-icon>star_border</md-icon>
                        </md-button>
                    </md-list-item>

                    <md-divider class="md-inset"></md-divider>

                    <md-list-item>
                        <md-avatar>
                            <img src="https://placeimg.com/40/40/people/8" alt="People" />
                        </md-avatar>

                        <div class="md-list-item-text">
                            <span>Trevor Hansen</span>
                            <span>Order confirmation</span>
                            <p>Thank you for your recent order from Amazon</p>
                        </div>

                        <md-button class="md-icon-button md-list-action">
                            <md-icon>star_border</md-icon>
                        </md-button>
                    </md-list-item>
                </md-list>

                <md-list class="md-triple-line md-dense">
                    <md-list-item>
                        <md-avatar>
                            <img src="https://placeimg.com/40/40/people/1" alt="People" />
                        </md-avatar>

                        <div class="md-list-item-text">
                            <span>Ali Connors</span>
                            <span>Brunch this weekend?</span>
                            <p>I'll be in your neighborhood doing errands this week. Do you want to meet?</p>
                        </div>

                        <md-button class="md-icon-button md-list-action">
                            <md-icon class="md-primary">star</md-icon>
                        </md-button>
                    </md-list-item>

                    <md-divider class="md-inset"></md-divider>

                    <md-list-item>
                        <md-avatar>
                            <img src="https://placeimg.com/40/40/people/6" alt="People" />
                        </md-avatar>

                        <div class="md-list-item-text">
                            <span>me, Scott, Jennifer</span>
                            <span>Summer BBQ</span>
                            <p>Wish I could come, but I'm out of town this week. :(</p>
                        </div>

                        <md-button class="md-icon-button md-list-action">
                            <md-icon>star_border</md-icon>
                        </md-button>
                    </md-list-item>

                    <md-divider class="md-inset"></md-divider>

                    <md-list-item>
                        <md-avatar>
                            <img src="https://placeimg.com/40/40/people/5" alt="People" />
                        </md-avatar>

                        <div class="md-list-item-text">
                            <span>Sandra Adams</span>
                            <span>Oui oui</span>
                            <p>Do you have Paris recommendations? Have you visited good places?</p>
                        </div>

                        <md-button class="md-icon-button md-list-action">
                            <md-icon>star_border</md-icon>
                        </md-button>
                    </md-list-item>

                    <md-divider class="md-inset"></md-divider>

                    <md-list-item>
                        <md-avatar>
                            <img src="https://placeimg.com/40/40/people/8" alt="People" />
                        </md-avatar>

                        <div class="md-list-item-text">
                            <span>Trevor Hansen</span>
                            <span>Order confirmation</span>
                            <p>Thank you for your recent order from Amazon</p>
                        </div>

                        <md-button class="md-icon-button md-list-action">
                            <md-icon>star_border</md-icon>
                        </md-button>
                    </md-list-item>
                </md-list>
            </div>
            <!-- <div class="search-bar" style="display: block;">
                <input v-model="keyword" type="text" placeholder="검색어를 입력해주세요." @keyup.enter="search" />
                <button @click="search">
                    <md-icon>search</md-icon>
                </button>
                <label for="search-bar">
                    <select v-model="selected_option">
                        <option v-for="option in options" v-bind:key="option.text">{{ option.text }}</option>
                    </select>
                    <v-select :items="options" single-line></v-select>
                </label>
            </div>-->
        </div>
>>>>>>> ad1f8dc2744b2d3bf1a90dd4e4503404f9e40c9d
    </div>
</template>
<script>
import ProductApi from '../../apis/ProductApi';
export default {
<<<<<<< HEAD
    name: 'TableFixed',
    data() {
        return {
            product: [], // 물건 리스트
            likeList: [], //찜 한거 목록 리스트
=======
    data() {
        return {
>>>>>>> ad1f8dc2744b2d3bf1a90dd4e4503404f9e40c9d
            selected: '',
            sub1: '',
            sub2: '',
            keyword: '',
<<<<<<< HEAD
            email: '',
            image1: '3.jpg',
            users: []
=======
            email: ''
>>>>>>> ad1f8dc2744b2d3bf1a90dd4e4503404f9e40c9d
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
<<<<<<< HEAD
=======

>>>>>>> ad1f8dc2744b2d3bf1a90dd4e4503404f9e40c9d
        sub2: function() {
            if (this.sub1 == null) {
                this.keyword = this.sub2;
            } else if (this.sub2 == null) {
                this.keyword = this.sub1;
            } else {
                this.keyword = this.sub1 + ' ' + this.sub2;
            }
        },
<<<<<<< HEAD
=======

>>>>>>> ad1f8dc2744b2d3bf1a90dd4e4503404f9e40c9d
        keyword: function() {
            if (this.keyword == null) {
                this.sub1 = '';
                this.sub2 = '';
            }
        }
    },
<<<<<<< HEAD
    methods: {
        searchProduct() {
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

                    this.likeList[0] = true;
                    for (var i = 0; i < this.likeList.length; i++) {
                        // this.users[i].isLike = this.likeList[i];
                        this.users[i]['isLike'] = this.likeList[i];
                    }
                    console.log(this.users);
=======
    method: {
        searchProduct() {
            ProductApi.getProductListByKeyword(
                this.keyword,
                res => {
                    console.log(res);
>>>>>>> ad1f8dc2744b2d3bf1a90dd4e4503404f9e40c9d
                },
                error => {
                    console.log(error);
                }
            );
<<<<<<< HEAD
            this.sub1 = '';
            this.sub2 = '';
            console.log('user ==>');
            console.log(this.users);
        },
        getImgUrl(pic) {
            return require('../../assets/images/' + pic);
        },
=======
        },

>>>>>>> ad1f8dc2744b2d3bf1a90dd4e4503404f9e40c9d
        addProduct() {
            var data = {
                email: this.email,
                productName: this.productName
            };
<<<<<<< HEAD
=======

>>>>>>> ad1f8dc2744b2d3bf1a90dd4e4503404f9e40c9d
            ProductApi.addProduct(
                data,
                res => {
                    console.log(res);
                },
                error => {
                    console.log(error);
                }
            );
        },
<<<<<<< HEAD
=======

>>>>>>> ad1f8dc2744b2d3bf1a90dd4e4503404f9e40c9d
        deleteProduct() {
            var data = {
                email: this.email,
                productName: this.productName
            };
<<<<<<< HEAD
=======

>>>>>>> ad1f8dc2744b2d3bf1a90dd4e4503404f9e40c9d
            ProductApi.deleteProduct(
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
