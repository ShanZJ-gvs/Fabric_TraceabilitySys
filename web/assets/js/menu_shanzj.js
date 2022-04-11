
Vue.config.productionTip = false
const menu_vm = new Vue({
    el: '#menu',
    data:{
        company1:document.getElementById("company").innerHTML,
    },
    computed:{
        tohome:{
            get(){
                return "./tohome?companyName="+this.company1
            }
        },
        todata:{
            get(){
                return "./todata?companyName="+this.company1
            }
        }
    },
    mounted () {
    },
    methods:{
    }

})





