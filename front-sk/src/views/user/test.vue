<template>
    <!-- <v-card class="overflow-hidden" color="purple lighten-1" dark> -->
    <!-- <v-toolbar flat color="purple"> -->
    <!-- <v-icon>mdi-account</v-icon>
            <v-toolbar-title class="font-weight-light">User Profile</v-toolbar-title> -->
    <!-- <v-spacer></v-spacer> -->
    <!-- <v-btn color="purple darken-3" fab small @click="isEditing = !isEditing">
                <v-icon v-if="isEditing">mdi-close</v-icon>
                <v-icon v-else>mdi-pencil</v-icon>
            </!-->

    <!-- <v-card-text> -->
    <!-- <v-text-field :disabled="isEditing" color="white" label="Name"></v-text-field> -->
    <v-autocomplete
        :disabled="isEditing"
        :items="states"
        :filter="customFilter"
        color="white"
        item-text="name"
        label="State"
        @keyup="searchProduct"
    ></v-autocomplete>
    <!-- </v-card-text> -->
    <!-- <v-divider></v-divider>
        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn :disabled="isEditing" color="success" @click="searchProduct">
                Save
            </v-btn>
        </v-card-actions>
        <v-snackbar v-model="hasSaved" :timeout="2000" absolute bottom left>
            Your profile has been updated
        </v-snackbar> -->
    <!-- </v-card> -->
</template>

<script>
import ProductApi from '../../apis/ProductApi';
export default {
    data() {
        return {
            hasSaved: false,
            isEditing: null,
            model: null,
            states: [
                // { name: 'Florida', abbr: 'FL', id: 1 },
                // { name: 'Georgia', abbr: 'GA', id: 2 },
                // { name: 'Nebraska', abbr: 'NE', id: 3 },
                // { name: 'California', abbr: 'CA', id: 4 },
                // { name: 'New York', abbr: 'NY', id: 5 }
            ],
            keyword: ''
        };
    },
    watch: {},

    methods: {
        customFilter(item, queryText) {
            const textOne = item.name.toLowerCase();
            // const textTwo = item.abbr.toLowerCase();
            const searchText = queryText.toLowerCase();

            return textOne.indexOf(searchText) > -1;
        },
        save() {
            this.hasSaved = true;
        },
        searchProduct(e) {
            console.log(e.target.value);

            this.keyword = e.target.value;
            let data = {
                keyword: this.keyword,
                email: sessionStorage.getItem('email')
            };

            ProductApi.getAPI(
                data,
                res => {
                    this.users = res.data.object.list;
                    console.log('user: ', this.users);
                    console.log(this.users);
                    var autoCom = [];

                    for (let i = 0; i < this.users.length; i++) {
                        autoCom.push({ name: this.users[i].productName, id: i + 1 });
                    }
                    this.states = autoCom;

                    this.likeList = res.data.object.likeCheckList;

                    console.log('likeList: ', this.likeList);
                },
                error => {
                    console.log(error);
                }
            );
            this.sub1 = '';
            this.sub2 = '';
            console.log('user ==>');
            console.log(this.users);
        }
    }
};
</script>
