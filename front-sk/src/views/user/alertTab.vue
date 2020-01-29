<template>
    <div class="components-page">
        <div class="phone-viewport" id="tab-component">
            <!-- <md-tabs md-sync-route md-active-tab="tab-pages" md-alignment="center" md-swipeable="true">
                <md-tab id="tab-home" md-label="Home" to="/user/find" exact>
                    Home Tab
                </md-tab>

                <md-tab id="tab-pages" md-label="Pages" to="#">
                    Pages tab
                    <p>
                        Unde provident nemo reiciendis officia, possimus repellendus. Facere dignissimos dicta quis rem. Aliquam aspernatur dolor
                        atque nisi id deserunt laudantium quam repellat.
                    </p>
                </md-tab>
                <md-tab id="tab-favorites" md-label="Favorites" to="/components/tabs/favorites">
                    Favorites tab
                    <p>
                        Maiores, dolorum. Beatae, optio tempore fuga odit aperiam velit, consequuntur magni inventore sapiente alias sequi odio qui
                        harum dolorem sunt quasi corporis.
                    </p>
                </md-tab>
            </md-tabs> -->
            <v-app>
                <v-tabs slot="extension" v-model="tab" color="cyan" grow>
                    <v-tabs-slider color="yellow"></v-tabs-slider>
                    <v-tab v-for="item in items" :key="item">
                        {{ item }}
                    </v-tab>
                </v-tabs>
            </v-app>
        </div>
    </div>
</template>

<script>
import '../../assets/css/components.scss';

export default {
    name: 'alertTab',
    data() {
        return {
            tab: null,
            items: ['web', 'shopping', 'videos', 'images', 'news']
        };
    }
};
</script>
<style scoped>
.md-tabs {
    margin-top: 57px;
}
</style>
