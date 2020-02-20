<template>
    <div>
        <div id="shopping_chart">
            <ul class="product-list">
                <div style="text-align:center;" v-if="users.length == 0 && isTime">
                    <md-progress-spinner md-mode="indeterminate"></md-progress-spinner>
                </div>
                <li v-for="item in users" v-bind:key="item">
                    <span class="product-img">
                        <img style="width : 150px; height :150px" v-bind:src="item.image" alt="" />
                    </span>

                    <span class="product-title">{{ item.productName }}</span>
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
    font-weight: bold;
    width: 10%;
}
.product-count input {
    width: 25%;
}
</style>

<script>
import ProductApi from '../../apis/ProductApi';
export default {
    name: 'TableFixed',
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
