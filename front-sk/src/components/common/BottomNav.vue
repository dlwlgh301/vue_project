<template>
    <div>
        <div class="phone-viewport">
            <md-bottom-bar md-sync-route>
                <md-bottom-bar-item to="/" exact md-label="Home" md-icon="home"></md-bottom-bar-item>
                <md-bottom-bar-item to="/components/bottom-bar/posts" md-label="Search" md-icon="search"></md-bottom-bar-item>
                <md-bottom-bar-item to="/user/find" md-label="Favorites" md-icon="bookmark"></md-bottom-bar-item>
                <md-bottom-bar-item to="/user/profile" md-label="Profile" md-icon="account_box"></md-bottom-bar-item>
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
    border: 1px solid rgba(#000, 0.26);
    background: rgba(#ffffff, 1);
}
</style>
