
Vue.config.productionTip = false
const vm = new Vue({
    el: '#root',
    data:{
        offset:0, // 分页查询起始位
        limit:5, // 分页查询查多少个
        listArea:[],
        listGarden:[],
        index:1,
        company1:document.getElementById("company").innerHTML // 公司名
    },
    mounted () {
        axios.get('/Fabric_TraceabilitySys_war_exploded/areas',
            {
                params: {
                    companyName:this.company1,
                    offset:this.offset,
                    limit:this.limit
                }
            })
            .then((response) =>{ // 这里如果不用箭头函数的话，this指的Windows
                const jsonData = JSON.parse(JSON.stringify(response.data));
                console.log("areas 返回数据的json===》",jsonData);
                this.listArea = jsonData;

            },function (error) {
                console.log(error);
            });
        axios.get('/Fabric_TraceabilitySys_war_exploded/gardens',
            {
                params: {
                    companyName:this.company1,
                    offset:this.offset,
                    limit:this.limit
                }
            })
            .then((response) =>{ // 这里如果不用箭头函数的话，this指的Windows
                const jsonData = JSON.parse(JSON.stringify(response.data));
                console.log("gardens 返回数据的json===》",jsonData);
                this.listGarden = jsonData;

            },function (error) {
                console.log(error);
            });
    }
})




