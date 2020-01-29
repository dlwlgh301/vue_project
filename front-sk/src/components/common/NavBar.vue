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
