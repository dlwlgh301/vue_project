<template>
    <div class="bottom-nav">
        <ul>
            <li>
                <button class="menu" v-if="!isBack"></button>
                <button v-if="isBack" class="back" @click="$router.push('/components')">
                    <i class="fas fa-home"></i>
                </button>
            </li>
            <li>
                <button class="menu" v-if="!isBack"></button>
                <button v-if="isBack" class="back" @click="$router.push('/user/join')">
                    <i class="fas fa-home"></i>
                </button>
            </li>
            <li>
                <button class="menu" v-if="!isBack"></button>
                <button v-if="isBack" class="back" @click="$router.push('/user/find')">
                    <i class="fas fa-home"></i>
                </button>
            </li>
            <li>
                <button class="menu" v-if="!isBack"></button>
                <button v-if="isBack" class="back" @click="$router.push('/user/change')">
                    <i class="fas fa-home"></i>
                </button>
            </li>
        </ul>
        <button class="menu" v-if="!isBack"></button>
        <button v-if="isBack" class="back" @click="$router.push('/404')">
            <i class="fas fa-home"></i>
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
    </div>
</template>

<script>
export default {
    name: 'header',
    props: ['headerTitle', 'isBack', 'isSearch', 'rightText', 'isDisabled']
};
</script>
