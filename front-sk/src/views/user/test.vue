<template>
    <div class="wrapC">
        <md-table v-model="users" md-sort="name" md-sort-order="asc" md-card md-fixed-header>
            <md-table-toolbar>
                <h1 class="md-title">Users</h1>
            </md-table-toolbar>

            <md-table-row slot="md-table-row" slot-scope="{ item }">
                <!-- <md-table-cell md-label="ID" md-sort-by="id" md-numeric>{{ item.id }}</md-table-cell> -->
                <!-- <td><img v-bind:src="item.image" /></td> -->
                <md-table-cell md-label="Image" md-sort-by="image" width="20%"><img v-bind:src="getImgUrl(item.image)"/></md-table-cell>
                <md-table-cell md-label="ProductName" md-sort-by="productName" width="50%"
                    >{{ item.productName }} <br />{{ item.link }}
                </md-table-cell>
                <!-- <md-table-cell md-label="Link" md-sort-by="link">{{ item.link }}</md-table-cell> -->
                <md-table-cell md-label="Price" md-sort-by="price" width="20%">{{ item.price }}</md-table-cell>
                <md-table-cell md-label="Like" width="10%">
                    <md-button class="md-icon-button md-list-action">
                        <md-icon class="md-primary">star</md-icon>
                    </md-button></md-table-cell
                >
            </md-table-row>
        </md-table>
        <!-- <img :src="getImgUrl(image1)" /> -->
    </div>
</template>

<script>
export default {
    name: 'TableFixed',
    data: () => {
        return {
            image1: '3.jpg',
            users: [
                {
                    image: '3.jpg',
                    productName: 'sdubbin0@geocities.com',
                    link: 'Male',
                    price: 1000
                },
                {
                    image: '3.jpg',
                    productName: 'odemageard1@spotify.com',
                    link: 'Female',
                    price: 2000
                },
                {
                    image: '3.jpg',
                    productName: 'vtaleworth2@google.ca',
                    link: 'Male',
                    price: 3000
                },
                {
                    image: '3.jpg',
                    productName: 'lizkovitz3@youtu.be',
                    link: 'Female',
                    price: 3500
                },
                {
                    image: '3.jpg',
                    productName: 'tstave4@reference.com',
                    link: 'Male',
                    price: 2500
                },
                {
                    image: '3.jpg',
                    productName: 'kchipping5@scribd.com',
                    link: 'Female',
                    price: 1400
                },
                {
                    image: '3.jpg',
                    productName: 'hholyard6@howstuffworks.com',
                    link: 'Female',
                    price: 5433
                }
            ]
        };
    },
    methods: {
        getImgUrl(pic) {
            return require('../../assets/images/' + pic);
        }
    }
};
</script>
