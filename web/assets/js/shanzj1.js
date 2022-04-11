
Vue.config.productionTip = false
const vm = new Vue({
    el: '#root',
    data:{
    kinds:["null","null"],
    kinds2:[-1,-1],
    kindsum:0,
    areasum:0,
    gardensum:0,
    treesum:0,
    company1:document.getElementById("company").innerHTML
},
mounted () {
    axios
        .get('/Fabric_TraceabilitySys_war_exploded/kinds',
            {
                params: {
                    companyName:document.getElementById("company").innerHTML
                }
            })
        .then((response) =>{ // 这里如果不用箭头函数的话，this指的Windows
            const jsonData = JSON.parse(JSON.stringify(response.data));
            console.log("kinds 返回数据的json===》",jsonData);
            console.log();
            this.kinds = jsonData;
        }, function (error) {
            console.log(error);
        });

    axios.get('/Fabric_TraceabilitySys_war_exploded/kindsum',
        {
            params: {
                companyName:document.getElementById("company").innerHTML
            }
        }).then((response) =>{ // 这里如果不用箭头函数的话，this指的Windows
        // console.log("companyName====》"+document.getElementById("company").innerHTML);
        const jsonData = JSON.parse(JSON.stringify(response.data));
        console.log("kindsum 返回数据的json===》",jsonData);
        // console.log(this);
        console.log(jsonData["茶区数量"]);
        console.log(jsonData["猴魁"]);
        this.company1 = document.getElementById("company").innerHTML;
        this.areasum = jsonData["茶区数量"];
        this.gardensum = jsonData["茶园数量"];
        this.treesum = jsonData["茶树数量"];
        console.log();
        console.log("获取茶种数组",this.kinds);
        for (let i = 0; i < this.kinds.length; i++) {
            this.kinds2[i] = jsonData[this.kinds[i]]
        }

        
        /*this.kindsum = JSON.parse(JSON.stringify(response.data[0]));*/
        canvas1();
    }, function (error) {
        console.log(error);
    });

    function canvas1() {
        console.log("canvas===>");
        console.log("this==>",this);
        console.log("vm==>",vm);

        // Colors
        let colors = {};
        colors.primary = "20, 83, 136";

        // Tooltips
        const tooltips = {
            backgroundColor: "#ffffff",
            titleFontColor: "rgba(" + colors.primary + ")",
            borderColor: "#dddddd",
            borderWidth: 0.5,
            bodyFontColor: "#555555",
            bodySpacing: 8,
            xPadding: 16,
            yPadding: 16,
            cornerRadius: 4,
            displayColors: true,
        };

        // Chart defaults
        Chart.defaults.global.defaultFontFamily = "Nunito Sans";
        Chart.defaults.global.defaultFontColor = "#555555";
        let ctx = "";

        let la1 = vm.kinds; // let la1 = ["白茶1", "碧螺春1", "西湖龙井1"];
        let data1 = vm.kinds2;  /* let data1 = [25, 10, 15],*/

        ctx = document.getElementById("categoriesChart");
        if (ctx) {
            ctx.getContext("2d");

            const categoriesChart = new Chart(ctx, {
                // The type of chart we want to create
                type: "polarAreaWithShadow",

                // The data for our dataset
                data: {
                    labels:la1,  /*labels: ["白茶1", "碧螺春1", "西湖龙井1"],*/
                    datasets: [
                        {
                            backgroundColor: [
                                "rgba(" + colors.primary + ", .1)",
                                "rgba(" + colors.primary + ", .5)",
                                "rgba(" + colors.primary + ", .25)",
                            ],
                            borderColor: "rgba(" + colors.primary + ")",
                            borderWidth: 2,
                            data: data1, /*data: [25, 10, 15],*/
                        },
                    ],
                },

                // Configuration options go here
                options: {
                    layout: {
                        padding: 5,
                    },
                    scale: {
                        ticks: {
                            display: false,
                        },
                    },
                    legend: {
                        position: "bottom",
                        labels: {
                            usePointStyle: true,
                            padding: 20,
                        },
                    },
                    tooltips: tooltips,
                },
            });
        }
    }

   /* this.$nextTick(canvas1());*/


},
methods:{
}

})



