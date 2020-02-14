<template>
    <div>
        <div class="phone-viewport">
            <md-bottom-bar md-sync-route>
                <md-bottom-bar-item to="/">
                    <md-icon style="color: #009ff4 ">home</md-icon>
                </md-bottom-bar-item>
                <md-bottom-bar-item to="/contents/search">
                    <md-icon style="color: #009ff4 ">search</md-icon>
                </md-bottom-bar-item>
                <md-bottom-bar-item to="/contents/write">
                    <md-icon style="color: #009ff4 ">create</md-icon>
                </md-bottom-bar-item>
                <md-bottom-bar-item to="/contents/bookmark">
                    <md-icon style="color: #009ff4 ">shopping_cart</md-icon>
                </md-bottom-bar-item>
                <md-bottom-bar-item to="/user/profile">
                    <md-icon style="color: #009ff4 ">account_circle</md-icon>
                </md-bottom-bar-item>
            </md-bottom-bar>
        </div>
    </div>
</template>

<script>
export default {
    name: 'BarRouter'
};
</script>

<style lang="scss" scoped>
.phone-viewport {
    z-index: 1;
    width: 100%;
    height: auto;
    display: inline-flex;
    align-items: flex-end;
    overflow: hidden;
    border-top: 0.01rem solid rgba(#000, 0.26);
    background: rgba(#ffffff, 1);
    md-icon {
        color: #009ff4;
    }
}
.md-bottom-bar.md-type-fixed .md-bottom-bar-item {
    max-width: none;
    min-width: 0 !important;
}
</style>
