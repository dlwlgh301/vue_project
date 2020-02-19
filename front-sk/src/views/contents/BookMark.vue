<template>
    <div>
        <!-- <form action="#" @submit.prevent="searchProduct()" enctype="multipart/form-data" id="insertMemberForm"> -->

        <md-table v-model="users" md-sort="name" md-sort-order="asc" md-card md-fixed-header>
            <div style="text-align:center;" v-if="users.length == 0 && isTime">
                <md-progress-spinner md-mode="indeterminate"></md-progress-spinner>
            </div>
            <md-table-toolbar>
                <h1 class="md-title">찜 목록</h1>
            </md-table-toolbar>
            <md-table-row slot="md-table-row" slot-scope="{ item }">
                <!-- <md-table-cell md-label="ID" md-sort-by="id" md-numeric>{{ item.id }}</md-table-cell> -->
                <!-- <td><img v-bind:src="item.image" /></td> -->
                <md-table-cell md-label="Image" md-sort-by="image"><img v-bind:src="item.image"/></md-table-cell>
                <md-table-cell md-label="ProductName" md-sort-by="productName" width="50%"
                    >{{ item.productName }} <br />
                    <a target="_blank" v-bind:href="item.link">{{ item.link }}</a></md-table-cell
                >
                <!-- <md-table-cell md-label="Link" md-sort-by="link">{{ item.link }}</md-table-cell> -->
                <md-table-cell md-label="Price" md-sort-by="price" width="20%">{{ item.price }}</md-table-cell>
                <md-table-cell md-sort-by="Like" md-label="Like" width="10%">
                    <md-button @click="addProduct(item)" class="md-icon-button md-list-action" v-show="!item.isLike">
                        <md-icon class="md-primary">star_border</md-icon>
                    </md-button>

                    <md-button @click="deleteProduct(item)" class="md-icon-button md-list-action" v-show="item.isLike">
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
            this.searchProduct();
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
            this.searchProduct();
        }
    },
    mounted() {
        this.email = sessionStorage.getItem('email');
        ProductApi.getProductListInterest(
            this.email,
            res => {
                this.users = res.data.object.list;
                console.log('찜 목록 list: ', this.users);
                this.likeList = res.data.object.likeCheckList;
                console.log('찜 목록 likelist: ', this.likeList);

                for (var i = 0; i < this.likeList.length; i++) {
                    this.users[i]['isLike'] = this.likeList[i];
                }
                console.log(this.users);
                console.log(this.users);
            },
            error => {
                console.log(error);
            }
        );
    }
};
</script>
