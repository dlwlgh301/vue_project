<template>
    <div>
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
    </div>
</template>

<script>
import ProductApi from '../../apis/ProductApi';
export default {
    data() {
        return {
            selected: '',
            sub1: '',
            sub2: '',
            keyword: '',
            email: ''
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
    method: {
        searchProduct() {
            ProductApi.getProductListByKeyword(
                this.keyword,
                res => {
                    console.log(res);
                },
                error => {
                    console.log(error);
                }
            );
        },

        addProduct() {
            var data = {
                email: this.email,
                productName: this.productName
            };

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

        deleteProduct() {
            var data = {
                email: this.email,
                productName: this.productName
            };

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
