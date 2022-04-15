const vm = new Vue({
    data() {
        return {
            employee:{
                sex:""
            },
            company1:document.getElementById("company").innerHTML,
        };
    },
    methods: {

    }
});

vm.$mount("#root");