<template>
    <div>
        <milk-select name="success-select" :options="options" multiple />
    </div>
</template>
<script>
export default {
    data: () => {
        return {
            options: [
                {
                    value: 'Option1',
                    label: 'Option1'
                },
                {
                    value: 'Option2',
                    label: 'Option2'
                },
                {
                    value: 'Option3',
                    label: 'Option3'
                }
            ],
            item: {}
        };
    },
    methods: {}
};
</script>
