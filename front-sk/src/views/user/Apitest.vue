<template>
    <div></div>
</template>

<script>
export default {
    data: function() {
        return {
            request_body: {
                startDate: '2019-08-01',
                endDate: '2019-09-30',
                timeUnit: 'month',
                category: [
                    { name: '패션의류', param: ['50000000'] },
                    { name: '화장품/미용', param: ['50000002'] }
                ],
                device: 'pc',
                ages: ['20', '30'],
                gender: 'f'
            }
        };
    },
    created: function() {
        fetch('https://openapi.naver.com/v1/search/shop', {
            method: 'GET',
            mode: 'no-cors',
            body: JSON.stringify(this.request_body),
            headers: {
                'X-Naver-Client-Id': 'SWUyt16NYZU6MvQrluEV',
                'X-Naver-Client-Secret': 'zPy366mvC9',
                'Content-Type': 'application/json'
            }
        }).then(response => {
            console.log(response);
        });
    }
};
</script>
