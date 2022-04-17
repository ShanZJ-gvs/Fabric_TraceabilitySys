
Vue.config.productionTip = false
const vm = new Vue({
    el: '#root',
    data:{
        offset:0, // 分页查询起始位
        limit:5, // 分页查询查多少个
        listArea:[],
        listGarden:[],
        listLeft:[],
        listLeft2:[],
        index:1,
        company1:document.getElementById("company").innerHTML, // 公司名

        tableData: [{
            pickId: '12987122',
            areaId: '好滋好味鸡蛋仔',
            gardenId: '江浙小吃、小吃零食',
            AGId: '江浙小吃、小吃零食',
            output:'10000',
            pickDataTime: '荷兰优质淡奶，奶香浓而不腻',
            makeDataTime: '上海市普陀区真北路',
            rankDataTime: '王小虎夫妻店',
            rankR: '特级', //定级结果


        }, {
            pickId: '12987122',
            areaId: '好滋好味鸡蛋仔',
            gardenId: '江浙小吃、小吃零食',
            AGId: '江浙小吃、小吃零食',
            output:'10000',
            pickDataTime: '荷兰优质淡奶，奶香浓而不腻',
            makeDataTime: '上海市普陀区真北路',
            rankDataTime: '王小虎夫妻店',
            rankR: '特级', //定级结果
        }]

    },
    mounted () {
        axios.get('/Fabric_TraceabilitySys_war_exploded/areas', /*数据总览*/
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
        axios.get('/Fabric_TraceabilitySys_war_exploded/gardens',/*数据总览*/
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
        axios.get('/Fabric_TraceabilitySys_war_exploded/leafs',/*数据总览*/
            {
                params: {
                    companyName:this.company1,
                    offset:this.offset,
                    limit:this.limit
                }
            })
            .then((response) =>{ // 这里如果不用箭头函数的话，this指的Windows
                const jsonData = JSON.parse(JSON.stringify(response.data));
                console.log("leafs 返回数据的json===》",jsonData);
                this.listLeft = jsonData;
            },function (error) {
                console.log(error);
            });
        axios.get('/Fabric_TraceabilitySys_war_exploded/leafs2',/*数据总览*/
            {
                params: {
                    companyName:this.company1,
                    offset:0,
                    limit:100
                }
            })
            .then((response) =>{ // 这里如果不用箭头函数的话，this指的Windows
                const jsonData = JSON.parse(JSON.stringify(response.data));
                console.log("leafs2 返回数据的json===》",jsonData);
                this.listLeft2 = jsonData;
            },function (error) {
                console.log(error);
            });
    },

    methods: {
        resetDateFilter() {
            this.$refs.filterTable.clearFilter('date');
        },
        clearFilter() {
            this.$refs.filterTable.clearFilter();
        },
        formatter(row, column) {
            return row.address;
        },
        filterTag(value, row) {
            return row.teaRank.teaRankRank === value;
        },
        filterHandler(value, row, column) {
            const property = column['property'];
            return row[property] === value;
        }

    }



})




