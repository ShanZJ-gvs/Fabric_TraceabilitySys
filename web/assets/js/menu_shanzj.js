
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
        },
        toforms:{
            get(){
                return "./toforms?companyName="+this.company1
            }
        },
        toform:{
            get(){
                return "./toform?companyName="+this.company1
            }
        },
        topeople:{
            get(){
                return "./topeople?companyName="+this.company1
            }
        },
        toauthlogin:{
            get(){
                return "./toauthlogin"
            }
        },
        iscompany(){
            if (this.company1!=""){/*有公司名，怎么说明已登录*/
                return true
            }else {
                return false
            }
        }
    },
    mounted () {
    },
    methods:{
    }

})





