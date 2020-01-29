<template>
    <!-- <div class="header-default">
        <button class="menu" v-if="!isBack"></button>
        <button v-if="isBack" class="back" @click="$router.go(-1)">
            <i class="fas fa-chevron-left"></i>
        </button>

        <h4 class="title">
            {{ headerTitle }}
        </h4>

        <button v-if="rightText" class="right-text" :class="{ disabled: isDisabled }" :disabled="isDisabled">
            {{ rightText }}
        </button>

        <button class="btn-search" v-if="isSearch">
            <i class="fas fa-search"></i>
        </button>
    </div>-->
    <div class="Navbar">
        <md-toolbar>
            <div class="md-toolbar-row">
                <div class="md-toolbar-section-start">
                    <md-button class="md-icon-button" @click="$router.go(-1)">
                        <md-icon>keyboard_arrow_left</md-icon>
                    </md-button>
                    <h3 class="md-title" style="flex: 1">{{ navTitle }}</h3>
                </div>

                <div class="md-toolbar-section-end">
                    <md-button class="md-icon-button">
                        <md-icon>search</md-icon>
                    </md-button>

                    <md-badge class="badge" md-content="1">
                        <md-button class="md-icon-button">
                            <md-icon>notifications</md-icon>
                        </md-button>
                    </md-badge>
                </div>
            </div>
        </md-toolbar>
    </div>
</template>

<script>
export default {
    name: 'Navbar',
    props: ['navTitle']
};
</script>
<style scoped>
#badge .p1[data-count]:after {
    position: absolute;
    right: 10%;
    top: 8%;
    content: attr(data-count);
    font-size: 40%;
    padding: 0.2em;
    border-radius: 50%;
    line-height: 1em;
    color: white;
    background: rgba(255, 0, 0, 0.85);
    text-align: center;
    min-width: 1em;
    /* font-weight: bold; */
}
</style>
