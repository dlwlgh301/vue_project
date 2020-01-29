<template>
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
