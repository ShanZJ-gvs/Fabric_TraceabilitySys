
Vue.config.productionTip = false
const vm = new Vue({
    el: '#root',
    data:{
        kinds:[],
        kinds2:[],
        perkey:["一月", "二月", "三月", "四月", "五月", "六月","七月","八月","九月","十月","十一月","十二月"],
        rankkey:["特级", "1级", "2级", "3级", "4级", "5级","6级","7级","8级","9级"],
        pickpersumvalue:[],
        makepersumvalue:[],
        rankpersumvalue:[],
        kindsum:-1,
        areasum:-1,
        gardensum:-1,
        treesum:-1,
        company1:document.getElementById("company").innerHTML,

    },
    mounted () {
        axios.get('/Fabric_TraceabilitySys_war_exploded/kinds',
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
            },function (error) {
                console.log(error);
            });

        axios.get('/Fabric_TraceabilitySys_war_exploded/kindsum',
            {
                params: {
                    companyName:document.getElementById("company").innerHTML
                }
            })
            .then((response) =>{ // 这里如果不用箭头函数的话，this指的Windows

            const jsonData = JSON.parse(JSON.stringify(response.data));
            console.log("kindsum 返回数据的json===》",jsonData);


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
            canvas2();
           /* canvas1();*/
        }, function (error) {
            console.log(error);
        });

        axios.get('/Fabric_TraceabilitySys_war_exploded/pickper',
            {
                params: {
                    companyName:document.getElementById("company").innerHTML
                }
            })
            .then((response) =>{ // 这里如果不用箭头函数的话，this指的Windows

            const jsonData = JSON.parse(JSON.stringify(response.data));
            console.log("pickper 返回数据的json===》",jsonData);

            this.company1 = document.getElementById("company").innerHTML;

                for (let i = 0; i < this.perkey.length; i++) {
                    if (jsonData[this.pickpersumvalue[i]] !== 0){
                        this.pickpersumvalue[i] = jsonData[this.perkey[i]];
                    }else {
                        this.pickpersumvalue[i] = 0;
                    }
                };

        }, function (error) {
            console.log(error);
        });

        axios.get('/Fabric_TraceabilitySys_war_exploded/makeper',
            {
                params: {
                    companyName:document.getElementById("company").innerHTML
                }
            })
            .then((response) =>{ // 这里如果不用箭头函数的话，this指的Windows

                const jsonData = JSON.parse(JSON.stringify(response.data));
                console.log("makeper 返回数据的json===》",jsonData);

                this.company1 = document.getElementById("company").innerHTML;

                for (let i = 0; i < this.perkey.length; i++) {
                    if (jsonData[this.pickpersumvalue[i]] !== 0){
                        this.makepersumvalue[i] = jsonData[this.perkey[i]];
                    }else {
                        this.makepersumvalue[i] = 0;
                    }
                };

            }, function (error) {
                console.log(error);
            });

        axios.get('/Fabric_TraceabilitySys_war_exploded/rankper',  // 获取当前公司各级茶叶的量
            {
                params: {
                    companyName:document.getElementById("company").innerHTML
                }
            })
            .then((response) =>{ // 这里如果不用箭头函数的话，this指的Windows

                const jsonData = JSON.parse(JSON.stringify(response.data));
                console.log("rankper 返回数据的json===》",jsonData);

                this.company1 = document.getElementById("company").innerHTML;

                for (let i = 0; i < this.rankkey.length; i++) {
                    if (jsonData[this.rankpersumvalue[i]] !== 0){
                        this.rankpersumvalue[i] = jsonData[this.rankkey[i]];
                    }else {
                        this.rankpersumvalue[i] = 0;
                    }
                };

            }, function (error) {
                console.log(error);
            });

       /* function canvas1() {
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
            let data1 = vm.kinds2;  /!* let data1 = [25, 10, 15],*!/

            ctx = document.getElementById("categoriesChart");
            if (ctx) {
                ctx.getContext("2d");

                const categoriesChart = new Chart(ctx, {
                    // The type of chart we want to create
                    type: "polarAreaWithShadow",

                    // The data for our dataset
                    data: {
                        labels:la1,  /!*labels: ["白茶1", "碧螺春1", "西湖龙井1"],*!/
                        datasets: [
                            {
                                backgroundColor: [
                                    "rgba(" + colors.primary + ", .1)",
                                    "rgba(" + colors.primary + ", .5)",
                                    "rgba(" + colors.primary + ", .25)",
                                ],
                                borderColor: "rgba(" + colors.primary + ")",
                                borderWidth: 2,
                                data: data1, /!*data: [25, 10, 15],*!/
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
        }*/

       /* this.$nextTick(canvas1());*/


    },
    methods:{
    },
    computed:{
        iscompany(){
            if (this.company1!=""){/*有公司名，怎么说明已登录，不进行渲染消费者查询*/
                return true
            }else {
                return false
            }
        }
    }


})

function canvas2(){
    if (typeof Chart !== "undefined") {
        console.log("canvas2===>");
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
        let data1 = vm.kinds2;  // let data1 = [25, 10, 15],



        // Charts with shadows
        const ShadowLineElement = Chart.elements.Line.extend({
            draw: function () {
                const { ctx } = this._chart;

                const originalStroke = ctx.stroke;

                ctx.stroke = function () {
                    ctx.save();
                    ctx.shadowColor = "rgba(0, 0, 0, 0.25)";
                    ctx.shadowBlur = 8;
                    ctx.shadowOffsetX = 0;
                    ctx.shadowOffsetY = 4;
                    originalStroke.apply(this, arguments);
                    ctx.restore();
                };

                Chart.elements.Line.prototype.draw.apply(this, arguments);

                ctx.stroke = originalStroke;
            },
        });

        Chart.defaults.lineWithShadow = Chart.defaults.line;
        Chart.controllers.lineWithShadow = Chart.controllers.line.extend({
            datasetElementType: ShadowLineElement,
        });

        Chart.defaults.radarWithShadow = Chart.defaults.radar;
        Chart.controllers.radarWithShadow = Chart.controllers.radar.extend({
            datasetElementType: ShadowLineElement,
        });

        Chart.defaults.barWithShadow = Chart.defaults.bar;
        Chart.defaults.global.datasets.barWithShadow =
            Chart.defaults.global.datasets.bar;
        Chart.controllers.barWithShadow = Chart.controllers.bar.extend({
            draw: function (ease) {
                Chart.controllers.bar.prototype.draw.call(this, ease);
                const ctx = this.chart.ctx;
                ctx.save();
                ctx.shadowColor = "rgba(0, 0, 0, 0.25)";
                ctx.shadowBlur = 8;
                ctx.shadowOffsetX = 0;
                ctx.shadowOffsetY = 4;
                Chart.controllers.bar.prototype.draw.apply(this, arguments);
                ctx.restore();
            },
        });

        Chart.defaults.pieWithShadow = Chart.defaults.pie;
        Chart.controllers.pieWithShadow = Chart.controllers.pie.extend({
            draw: function (ease) {
                Chart.controllers.pie.prototype.draw.call(this, ease);
                const ctx = this.chart.ctx;
                ctx.save();
                ctx.shadowColor = "rgba(0, 0, 0, 0.25)";
                ctx.shadowBlur = 8;
                ctx.shadowOffsetX = 0;
                ctx.shadowOffsetY = 4;
                Chart.controllers.pie.prototype.draw.apply(this, arguments);
                ctx.restore();
            },
        });

        Chart.defaults.doughnutWithShadow = Chart.defaults.doughnut;
        Chart.controllers.doughnutWithShadow = Chart.controllers.doughnut.extend({
            draw: function (ease) {
                Chart.controllers.doughnut.prototype.draw.call(this, ease);
                const ctx = this.chart.ctx;
                ctx.save();
                ctx.shadowColor = "rgba(0, 0, 0, 0.25)";
                ctx.shadowBlur = 8;
                ctx.shadowOffsetX = 0;
                ctx.shadowOffsetY = 4;
                Chart.controllers.doughnut.prototype.draw.apply(this, arguments);
                ctx.restore();
            },
        });

        Chart.defaults.polarAreaWithShadow = Chart.defaults.polarArea;
        Chart.controllers.polarAreaWithShadow = Chart.controllers.polarArea.extend({
            draw: function (ease) {
                Chart.controllers.polarArea.prototype.draw.call(this, ease);
                const ctx = this.chart.ctx;
                ctx.save();
                ctx.shadowColor = "rgba(0, 0, 0, 0.25)";
                ctx.shadowBlur = 8;
                ctx.shadowOffsetX = 0;
                ctx.shadowOffsetY = 4;
                Chart.controllers.polarArea.prototype.draw.apply(this, arguments);
                ctx.restore();
            },
        });

        Chart.defaults.lineWithLine = Chart.defaults.line;
        Chart.controllers.lineWithLine = Chart.controllers.line.extend({
            draw: function (ease) {
                Chart.controllers.line.prototype.draw.call(this, ease);
                if (this.chart.tooltip._active && this.chart.tooltip._active.length) {
                    const activePoint = this.chart.tooltip._active[0];
                    const ctx = this.chart.ctx;
                    const x = activePoint.tooltipPosition().x;
                    const topY = this.chart.scales["y-axis-0"].top;
                    const bottomY = this.chart.scales["y-axis-0"].bottom;
                    ctx.save();
                    ctx.beginPath();
                    ctx.moveTo(x, topY);
                    ctx.lineTo(x, bottomY);
                    ctx.lineWidth = 1;
                    ctx.strokeStyle = "rgba(0,0,0,0.1)";
                    ctx.stroke();
                    ctx.restore();
                }
            },
        });

        // Line with line
        const lineWithLinePlugin = {
            afterInit: function (chart, options) {
                const info = chart.canvas.parentNode;
                const value = chart.data.datasets[0].data[0];
                const heading = chart.data.datasets[0].label;
                const label = chart.data.labels[0];
                info.querySelector(".chart-value").innerHTML =
                    "$" + value.toLocaleString();
                info.querySelector(".chart-label").innerHTML = heading + ": " + label;
            },
        };

        const lineWithLineOptions = {
            layout: {
                padding: {
                    left: 5,
                    right: 5,
                    top: 10,
                    bottom: 10,
                },
            },
            responsive: true,
            legend: {
                display: false,
            },
            tooltips: {
                intersect: false,
                enabled: false,
                custom: function (tooltipModel) {
                    if (tooltipModel && tooltipModel.dataPoints) {
                        const info = this._chart.canvas.parentNode;
                        const value = tooltipModel.dataPoints[0].yLabel;
                        const heading = tooltipModel.body[0].lines[0].split(":")[0];
                        const label = tooltipModel.dataPoints[0].xLabel;
                        info.querySelector(".chart-value").innerHTML =
                            "$" + value.toLocaleString();
                        info.querySelector(".chart-label").innerHTML = heading + ": " + label;
                    }
                },
            },
            scales: {
                yAxes: [
                    {
                        display: false,
                    },
                ],
                xAxes: [
                    {
                        display: false,
                    },
                ],
            },
        };

        // Charts
        ctx = document.getElementById("areaChart");
        if (ctx) {
            ctx.getContext("2d");
            const areatChart = new Chart(ctx, {
                // The type of chart we want to create
                type: "line",

                // The data for our dataset
                data: {
                    labels: ["January", "February", "March", "April", "May", "June"],
                    datasets: [
                        {
                            backgroundColor: "rgba(" + colors.primary + ", .1)",
                            borderColor: "rgba(" + colors.primary + ")",
                            borderWidth: 2,
                            pointBackgroundColor: "#ffffff",
                            pointBorderColor: "rgba(" + colors.primary + ")",
                            pointHoverBackgroundColor: "rgba(" + colors.primary + ")",
                            pointHoverBorderColor: "#ffffff",
                            pointRadius: 4,
                            pointBorderWidth: 2,
                            pointHoverRadius: 6,
                            pointHoverBorderWidth: 2,
                            data: [5, 10, 15, 10, 15, 10],
                        },
                    ],
                },

                // Configuration options go here
                options: {
                    legend: false,
                    tooltips: tooltips,
                    scales: {
                        yAxes: [
                            {
                                gridLines: {
                                    display: true,
                                    drawBorder: false,
                                },
                                ticks: {
                                    stepSize: 5,
                                    min: 0,
                                    max: 20,
                                },
                            },
                        ],
                        xAxes: [
                            {
                                gridLines: {
                                    display: false,
                                },
                            },
                        ],
                    },
                },
            });
        }

        ctx = document.getElementById("areaWithShadowChart");
        if (ctx) {
            ctx.getContext("2d");
            const areaChartWithShadow = new Chart(ctx, {
                // The type of chart we want to create
                type: "lineWithShadow",

                // The data for our dataset
                data: {
                    labels: ["January", "February", "March", "April", "May", "June"],
                    datasets: [
                        {
                            backgroundColor: "rgba(" + colors.primary + ", .1)",
                            borderColor: "rgba(" + colors.primary + ")",
                            borderWidth: 2,
                            pointBackgroundColor: "#ffffff",
                            pointBorderColor: "rgba(" + colors.primary + ")",
                            pointHoverBackgroundColor: "rgba(" + colors.primary + ")",
                            pointHoverBorderColor: "#ffffff",
                            pointRadius: 4,
                            pointBorderWidth: 2,
                            pointHoverRadius: 6,
                            pointHoverBorderWidth: 2,
                            data: [5, 10, 15, 10, 15, 10],
                        },
                    ],
                },

                // Configuration options go here
                options: {
                    legend: false,
                    tooltips: tooltips,
                    scales: {
                        yAxes: [
                            {
                                gridLines: {
                                    display: true,
                                    drawBorder: false,
                                },
                                ticks: {
                                    stepSize: 5,
                                    min: 0,
                                    max: 20,
                                },
                            },
                        ],
                        xAxes: [
                            {
                                gridLines: {
                                    display: false,
                                },
                            },
                        ],
                    },
                },
            });
        }

        ctx = document.getElementById("lineChart");
        if (ctx) {
            ctx.getContext("2d");
            const lineChart = new Chart(ctx, {
                // The type of chart we want to create
                type: "line",

                // The data for our dataset
                data: {
                    labels: ["January", "February", "March", "April", "May", "June"],
                    datasets: [
                        {
                            borderColor: "rgba(" + colors.primary + ")",
                            borderWidth: 2,
                            pointBackgroundColor: "#ffffff",
                            pointBorderColor: "rgba(" + colors.primary + ")",
                            pointHoverBackgroundColor: "rgba(" + colors.primary + ")",
                            pointHoverBorderColor: "#ffffff",
                            pointRadius: 6,
                            pointBorderWidth: 2,
                            pointHoverRadius: 8,
                            pointHoverBorderWidth: 2,
                            fill: false,
                            data: [5, 10, 15, 10, 15, 10],
                        },
                    ],
                },

                // Configuration options go here
                options: {
                    legend: false,
                    tooltips: tooltips,
                    scales: {
                        yAxes: [
                            {
                                gridLines: {
                                    display: true,
                                    drawBorder: false,
                                },
                                ticks: {
                                    stepSize: 5,
                                    min: 0,
                                    max: 20,
                                },
                            },
                        ],
                        xAxes: [
                            {
                                gridLines: {
                                    display: false,
                                },
                            },
                        ],
                    },
                },
            });
        }

        ctx = document.getElementById("lineWithShadowChart");
        if (ctx) {
            ctx.getContext("2d");
            const lineWithShadowChart = new Chart(ctx, {
                // The type of chart we want to create
                type: "lineWithShadow",

                // The data for our dataset
                data: {
                    labels: ["January", "February", "March", "April", "May", "June"],
                    datasets: [
                        {
                            borderColor: "rgba(" + colors.primary + ")",
                            borderWidth: 2,
                            pointBackgroundColor: "#ffffff",
                            pointBorderColor: "rgba(" + colors.primary + ")",
                            pointHoverBackgroundColor: "rgba(" + colors.primary + ")",
                            pointHoverBorderColor: "#ffffff",
                            pointRadius: 6,
                            pointBorderWidth: 2,
                            pointHoverRadius: 8,
                            pointHoverBorderWidth: 2,
                            fill: false,
                            data: [5, 10, 15, 10, 15, 10],
                        },
                    ],
                },

                // Configuration options go here
                options: {
                    legend: false,
                    tooltips: tooltips,
                    scales: {
                        yAxes: [
                            {
                                gridLines: {
                                    display: true,
                                    drawBorder: false,
                                },
                                ticks: {
                                    stepSize: 5,
                                    min: 0,
                                    max: 20,
                                },
                            },
                        ],
                        xAxes: [
                            {
                                gridLines: {
                                    display: false,
                                },
                            },
                        ],
                    },
                },
            });
        }

        ctx = document.getElementById("barChart");
        if (ctx) {
            ctx.getContext("2d");
            const barChart = new Chart(ctx, {
                // The type of chart we want to create
                type: "bar",

                // The data for our dataset
                data: {
                    labels: ["一月", "二月", "三月", "四月", "五月", "六月","七月","八月","九月","十月","十一月","十二月"],
                    datasets: [
                        {
                            label: "总收入",
                            backgroundColor: "rgba(" + colors.primary + ", .1)",
                            borderColor: "rgba(" + colors.primary + ")",
                            borderWidth: 2,
                            data: [5, 10, 15, 10, 15, 10,10],
                        },
                        {
                            label: "Tomatoes",
                            backgroundColor: "rgba(" + colors.primary + ", .5)",
                            borderColor: "rgba(" + colors.primary + ")",
                            borderWidth: 2,
                            data: [7.5, 10, 17.5, 15, 12.5, 5,10],
                        },
                    ],
                },

                // Configuration options go here
                options: {
                    legend: {
                        position: "bottom",
                        labels: {
                            usePointStyle: true,
                            padding: 20,
                        },
                    },
                    tooltips: tooltips,
                    scales: {
                        yAxes: [
                            {
                                gridLines: {
                                    display: true,
                                    drawBorder: false,
                                },
                                ticks: {
                                    stepSize: 5,
                                    min: 0,
                                    max: 20,
                                },
                            },
                        ],
                        xAxes: [
                            {
                                gridLines: {
                                    display: false,
                                },
                            },
                        ],
                    },
                },
            });
        }

        ctx = document.getElementById("barWithShadowChart"); // 茶区产量
        if (ctx) {
            ctx.getContext("2d");
            const barWithShadowChart = new Chart(ctx, {
                // The type of chart we want to create
                type: "barWithShadow",

                // The data for our dataset
                data: {
                    labels: vm.perkey,
                    datasets: [
                        {
                            label: "制茶量",
                            backgroundColor: "rgba(" + colors.primary + ", .1)",
                            borderColor: "rgba(" + colors.primary + ")",
                            borderWidth: 2,
                            data: vm.makepersumvalue,
                        },
                        {
                            label: "采摘量",
                            backgroundColor: "rgba(" + colors.primary + ", .5)",
                            borderColor: "rgba(" + colors.primary + ")",
                            borderWidth: 2,
                            data: vm.pickpersumvalue,
                        },
                    ],
                },

                // Configuration options go here
                options: {
                    legend: {
                        position: "bottom",
                        labels: {
                            usePointStyle: true,
                            padding: 20,
                        },
                    },
                    tooltips: tooltips,
                    scales: {
                        yAxes: [
                            {
                                gridLines: {
                                    display: true,
                                    drawBorder: false,
                                },
                                ticks: {
                                    stepSize: 1000,
                                    min: 0,
                                    max: 5000,
                                },
                            },
                        ],
                        xAxes: [
                            {
                                gridLines: {
                                    display: false,
                                },
                            },
                        ],
                    },
                },
            });
        }

        ctx = document.getElementById("barWithShadowChartRank"); // 茶叶等级per量
        if (ctx) {
            ctx.getContext("2d");
            const barWithShadowChart = new Chart(ctx, {
                // The type of chart we want to create
                type: "barWithShadow",

                // The data for our dataset
                data: {
                    labels: vm.rankkey,
                    datasets: [
                        {
                            label: "等级",
                            backgroundColor: "rgba(" + colors.primary + ", .1)",
                            borderColor: "rgba(" + colors.primary + ")",
                            borderWidth: 2,
                            data: vm.rankpersumvalue,
                        }
                    ],
                },

                // Configuration options go here
                options: {
                    legend: {
                        position: "bottom",
                        labels: {
                            usePointStyle: true,
                            padding: 20,
                        },
                    },
                    tooltips: tooltips,
                    scales: {
                        yAxes: [
                            {
                                gridLines: {
                                    display: true,
                                    drawBorder: false,
                                },
                                ticks: {
                                    stepSize: 1000,
                                    min: 0,
                                    max: 5000,
                                },
                            },
                        ],
                        xAxes: [
                            {
                                gridLines: {
                                    display: false,
                                },
                            },
                        ],
                    },
                },
            });
        }

        ctx = document.getElementById("pieChart");
        if (ctx) {
            ctx.getContext("2d");
            const pieChart = new Chart(ctx, {
                // The type of chart we want to create
                type: "pie",

                // The data for our dataset
                data: {
                    labels: ["Potatoes", "Tomatoes", "Onions"],
                    datasets: [
                        {
                            backgroundColor: [
                                "rgba(" + colors.primary + ", .1)",
                                "rgba(" + colors.primary + ", .5)",
                                "rgba(" + colors.primary + ", .25)",
                            ],
                            borderColor: "rgba(" + colors.primary + ")",
                            borderWidth: 2,
                            data: [25, 10, 15],
                        },
                    ],
                },

                // Configuration options go here
                options: {
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

        ctx = document.getElementById("pieWithShadowChart");
        if (ctx) {
            ctx.getContext("2d");
            const pieWithShadowChart = new Chart(ctx, {
                // The type of chart we want to create
                type: "pieWithShadow",

                // The data for our dataset
                data: {
                    labels: ["Potatoes", "Tomatoes", "Onions"],
                    datasets: [
                        {
                            backgroundColor: [
                                "rgba(" + colors.primary + ", .1)",
                                "rgba(" + colors.primary + ", .5)",
                                "rgba(" + colors.primary + ", .25)",
                            ],
                            borderColor: "rgba(" + colors.primary + ")",
                            borderWidth: 2,
                            data: [25, 10, 15],
                        },
                    ],
                },

                // Configuration options go here
                options: {
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

        ctx = document.getElementById("doughnutChart");
        if (ctx) {
            ctx.getContext("2d");
            const doughnutChart = new Chart(ctx, {
                // The type of chart we want to create
                type: "doughnut",

                // The data for our dataset
                data: {
                    labels: ["Potatoes", "Tomatoes", "Onions"],
                    datasets: [
                        {
                            backgroundColor: [
                                "rgba(" + colors.primary + ", .1)",
                                "rgba(" + colors.primary + ", .5)",
                                "rgba(" + colors.primary + ", .25)",
                            ],
                            borderColor: "rgba(" + colors.primary + ")",
                            borderWidth: 2,
                            data: [25, 10, 15],
                        },
                    ],
                },

                // Configuration options go here
                options: {
                    cutoutPercentage: 75,
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
        ctx = document.getElementById("doughnutWithShadowChart"); //茶叶合格率
        if (ctx) {
            ctx.getContext("2d");
            const doughnutWithShadowChart = new Chart(ctx, {
                // The type of chart we want to create
                type: "doughnutWithShadow",

                // The data for our dataset
                data: {
                    labels: ["合格", "不合格"],
                    datasets: [
                        {
                            backgroundColor: [
                                "rgba(" + colors.primary + ", .1)",

                                "rgba(" + colors.primary + ", .5)",
                            ],
                            borderColor: "rgba(" + colors.primary + ")",
                            borderWidth: 2,
                            data: [90, 10],
                        },
                    ],
                },

                // Configuration options go here
                options: {
                    cutoutPercentage: 75,
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
        ctx = document.getElementById("polarChart");
        if (ctx) {
            ctx.getContext("2d");
            const polarChart = new Chart(ctx, {
                // The type of chart we want to create
                type: "polarArea",

                // The data for our dataset
                data: {
                    labels: ["Potatoes", "Tomatoes", "Onions"],
                    datasets: [
                        {
                            backgroundColor: [
                                "rgba(" + colors.primary + ", .1)",
                                "rgba(" + colors.primary + ", .5)",
                                "rgba(" + colors.primary + ", .25)",
                            ],
                            borderColor: "rgba(" + colors.primary + ")",
                            borderWidth: 2,
                            data: [25, 10, 15],
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

        ctx = document.getElementById("polarWithShadowChart");
        if (ctx) {
            ctx.getContext("2d");
            const polarWithShadowChart = new Chart(ctx, {
                // The type of chart we want to create
                type: "polarAreaWithShadow",

                // The data for our dataset
                data: {
                    labels: ["Potatoes", "Tomatoes", "Onions"],
                    datasets: [
                        {
                            backgroundColor: [
                                "rgba(" + colors.primary + ", .1)",
                                "rgba(" + colors.primary + ", .5)",
                                "rgba(" + colors.primary + ", .25)",
                            ],
                            borderColor: "rgba(" + colors.primary + ")",
                            borderWidth: 2,
                            data: [25, 10, 15],
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

        ctx = document.getElementById("radarChart");
        if (ctx) {
            ctx.getContext("2d");
            const radarChart = new Chart(ctx, {
                // The type of chart we want to create
                type: "radar",

                // The data for our dataset
                data: {
                    labels: ["Drinks", "Snaks", "Lunch", "Dinner"],
                    datasets: [
                        {
                            label: "Potatoes",
                            data: [25, 25, 25, 25],
                            backgroundColor: "rgba(" + colors.primary + ", .1)",
                            borderColor: "rgba(" + colors.primary + ")",
                            borderWidth: 2,
                            pointBackgroundColor: "#ffffff",
                            pointBorderColor: "rgba(" + colors.primary + ")",
                            pointHoverBackgroundColor: "rgba(" + colors.primary + ")",
                            pointHoverBorderColor: "#ffffff",
                            pointRadius: 4,
                            pointBorderWidth: 2,
                            pointHoverRadius: 6,
                            pointHoverBorderWidth: 2,
                        },
                        {
                            label: "Tomatoes",
                            data: [10, 10, 0, 20, 20],
                            backgroundColor: "rgba(" + colors.primary + ", .25",
                            borderColor: "rgba(" + colors.primary + ")",
                            borderWidth: 2,
                            pointBackgroundColor: "#ffffff",
                            pointBorderColor: "rgba(" + colors.primary + ")",
                            pointHoverBackgroundColor: "rgba(" + colors.primary + ")",
                            pointHoverBorderColor: "#ffffff",
                            pointRadius: 4,
                            pointBorderWidth: 2,
                            pointHoverRadius: 6,
                            pointHoverBorderWidth: 2,
                        },
                    ],
                },

                // Configuration options go here
                options: {
                    scale: {
                        ticks: {
                            display: false,
                            max: 30,
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

        ctx = document.getElementById("radarWithShadowChart");
        if (ctx) {
            ctx.getContext("2d");
            const radarWithShadowChart = new Chart(ctx, {
                // The type of chart we want to create
                type: "radarWithShadow",

                // The data for our dataset
                data: {
                    labels: ["Drinks", "Snaks", "Lunch", "Dinner"],
                    datasets: [
                        {
                            label: "Potatoes",
                            data: [25, 25, 25, 25],
                            backgroundColor: "rgba(" + colors.primary + ", .1)",
                            borderColor: "rgba(" + colors.primary + ")",
                            borderWidth: 2,
                            pointBackgroundColor: "#ffffff",
                            pointBorderColor: "rgba(" + colors.primary + ")",
                            pointHoverBackgroundColor: "rgba(" + colors.primary + ")",
                            pointHoverBorderColor: "#ffffff",
                            pointRadius: 4,
                            pointBorderWidth: 2,
                            pointHoverRadius: 6,
                            pointHoverBorderWidth: 2,
                        },
                        {
                            label: "Tomatoes",
                            data: [10, 10, 0, 20, 20],
                            backgroundColor: "rgba(" + colors.primary + ", .25",
                            borderColor: "rgba(" + colors.primary + ")",
                            borderWidth: 2,
                            pointBackgroundColor: "#ffffff",
                            pointBorderColor: "rgba(" + colors.primary + ")",
                            pointHoverBackgroundColor: "rgba(" + colors.primary + ")",
                            pointHoverBorderColor: "#ffffff",
                            pointRadius: 4,
                            pointBorderWidth: 2,
                            pointHoverRadius: 6,
                            pointHoverBorderWidth: 2,
                        },
                    ],
                },

                // Configuration options go here
                options: {
                    scale: {
                        ticks: {
                            display: false,
                            max: 30,
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

        ctx = document.getElementById("lineWithLine1");
        if (ctx) {
            ctx.getContext("2d");
            const lineWithLineChart1 = new Chart(ctx, {
                type: "lineWithLine",
                plugins: [lineWithLinePlugin],
                data: {
                    labels: ["星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天"],
                    datasets: [
                        {
                            label: "总订单",
                            borderColor: "rgba(" + colors.primary + ")",
                            borderWidth: 2,
                            pointBorderColor: "rgba(" + colors.primary + ")",
                            pointHoverBackgroundColor: "rgba(" + colors.primary + ")",
                            pointHoverBorderColor: "#ffffff",
                            pointRadius: 2,
                            pointBorderWidth: 4,
                            pointHoverRadius: 2,
                            fill: false,
                            data: [1250, 1300, 1550, 900, 1800, 1100, 1600],
                        },
                    ],
                },
                options: lineWithLineOptions,
            });
        }

        ctx = document.getElementById("lineWithLine2");
        if (ctx) {
            ctx.getContext("2d");
            const lineWithLineChart2 = new Chart(ctx, {
                type: "lineWithLine",
                plugins: [lineWithLinePlugin],
                data: {
                    labels: ["星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天"],
                    datasets: [
                        {
                            label: "有效订单",
                            borderColor: "rgba(" + colors.primary + ")",
                            borderWidth: 2,
                            pointBorderColor: "rgba(" + colors.primary + ")",
                            pointHoverBackgroundColor: "rgba(" + colors.primary + ")",
                            pointHoverBorderColor: "#ffffff",
                            pointRadius: 2,
                            pointBorderWidth: 4,
                            pointHoverRadius: 2,
                            fill: false,
                            data: [100, 150, 300, 200, 100, 50, 50],
                        },
                    ],
                },
                options: lineWithLineOptions,
            });
        }

        ctx = document.getElementById("lineWithLine3");
        if (ctx) {
            ctx.getContext("2d");
            const lineWithLineChart3 = new Chart(ctx, {
                type: "lineWithLine",
                plugins: [lineWithLinePlugin],
                data: {
                    labels: ["星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天"],
                    datasets: [
                        {
                            label: "待处理订单",
                            borderColor: "rgba(" + colors.primary + ")",
                            borderWidth: 2,
                            pointBorderColor: "rgba(" + colors.primary + ")",
                            pointHoverBackgroundColor: "rgba(" + colors.primary + ")",
                            pointHoverBorderColor: "#ffffff",
                            pointRadius: 2,
                            pointBorderWidth: 4,
                            pointHoverRadius: 2,
                            fill: false,
                            data: [350, 400, 750, 900, 600, 50, 50],
                        },
                    ],
                },
                options: lineWithLineOptions,
            });
        }

        ctx = document.getElementById("lineWithLine4");
        if (ctx) {
            ctx.getContext("2d");
            const lineWithLineChart4 = new Chart(ctx, {
                type: "lineWithLine",
                plugins: [lineWithLinePlugin],
                data: {
                    labels: ["星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天"],
                    datasets: [
                        {
                            label: "发货订单",
                            borderColor: "rgba(" + colors.primary + ")",
                            borderWidth: 2,
                            pointBorderColor: "rgba(" + colors.primary + ")",
                            pointHoverBackgroundColor: "rgba(" + colors.primary + ")",
                            pointHoverBorderColor: "#ffffff",
                            pointRadius: 2,
                            pointBorderWidth: 4,
                            pointHoverRadius: 2,
                            fill: false,
                            data: [200, 400, 250, 600, 100, 50, 50],
                        },
                    ],
                },
                options: lineWithLineOptions,
            });
        }

        ctx = document.getElementById("visitorsChart"); // 茶区营收
        if (ctx) {
            ctx.getContext("2d");
            const visitorsChart = new Chart(ctx, {
                // The type of chart we want to create
                type: "lineWithShadow",

                // The data for our dataset
                data: {
                    labels: ["一月", "二月", "三月", "四月", "五月", "六月"],
                    datasets: [
                        {
                            backgroundColor: "rgba(" + colors.primary + ", .1)",
                            borderColor: "rgba(" + colors.primary + ")",
                            borderWidth: 2,
                            pointBackgroundColor: "#ffffff",
                            pointBorderColor: "rgba(" + colors.primary + ")",
                            pointHoverBackgroundColor: "rgba(" + colors.primary + ")",
                            pointHoverBorderColor: "#ffffff",
                            pointRadius: 4,
                            pointBorderWidth: 2,
                            pointHoverRadius: 6,
                            pointHoverBorderWidth: 2,
                            data: [5, 10, 15, 20, 15, 10],
                        },
                    ],
                },

                // Configuration options go here
                options: {
                    legend: false,
                    tooltips: tooltips,
                    scales: {
                        yAxes: [
                            {
                                gridLines: {
                                    display: true,
                                    drawBorder: false,
                                },
                                ticks: {
                                    stepSize: 5,
                                    min: 0,
                                    max: 20,
                                },
                            },
                        ],
                        xAxes: [
                            {
                                gridLines: {
                                    display: false,
                                },
                            },
                        ],
                    },
                },
            });
        }

        ctx = document.getElementById("categoriesChart"); // 茶树种类
        if (ctx) {
            ctx.getContext("2d");
            const categoriesChart = new Chart(ctx, {
                // The type of chart we want to create
                type: "polarAreaWithShadow",

                // The data for our dataset
                data: {
                    labels: la1,
                    datasets: [
                        {
                            backgroundColor: [
                                "rgba(" + colors.primary + ", .1)",
                                "rgba(" + colors.primary + ", .5)",
                                "rgba(" + colors.primary + ", .25)",
                            ],
                            borderColor: "rgba(" + colors.primary + ")",
                            borderWidth: 2,
                            data: data1,
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
}



