Vue.config.productionTip = false
const vm = new Vue({
    el: '#root',
    data:{
        activeNames: ['0'],
        UserCode:"24c0e81c573147589634ae12faa32236",
        retr:true,
        msg:"",
        /*AllPojo:{*/
        TeaArea:{
            "teaAreaLongitude": "",
            "teaAreaArea": "",
            "teaGardenId2": "",
            "teaAreaId2": "",
            "teaAreaId1": "",
            "teaAreaAddress": ""
        },
        TeaGarden:{
            teaGardenId1:"",
            teaGardenAddress:"",
            "teaGardenArea":"",
            "teaGardenLongitude":"",
            teaGardenId1:"",
            "teaGardenId2": ""
        },
        TeaTree:{
            "teaTreeAddress": "",
            "teaTreeLongitude": "",
            "teaGardenId2": "",
            "teaTreeCultivate": "",
            "teaAreaId2": "",
            "teaTreeId": "",
            "teaTreeHeight": 0,
            "teaTreeKind": "",
            "teaTreeState": "",
            "teaTreeGrowingEnv": ""
        },
        TeaPick:{
            "teaPickPer": {
                "eid": "",
                "ename": "",
                "esex": ""
            },
            "teaTreeId2": "",
            "teaPickId": "",
            "teaPickQuality": "",
            "teaPickTime": ""
        },
        TeaMake:{
            "teaMakeId": "",
            "teaMakeWay": "",
            "teaMakeTime": "",
            "teaMakePer": {
                "eid": "",
                "ename": "",
                "esex": ""
            }
        },
        TeaRank:{
            "teaRankId": "",
            "teaRankRank": "",
            "teaRankPer": {
                "eid": "",
                "ename": "",
                "esex": ""
            },
            "teaRankTime": ""
        },
        TeaPack:{
            "teaPackTime": "",
            "teaPackSmllBoxId": "",
            "teaPackPer": {
                "eid": "",
                "ename": "",
                "esex": ""
            },
            "teaPackID": "",
            "teaPackBigBoxId": ""
        },
        TeaTesting:{
            "teaTestingId": "",
            "teaTestingSmllBoxId": "",
            "teaTestingTime": "",
            "teaTestingBigBoxId": "",
            "teaTestingResult": ""
        }
        /* }*/
    },
    mounted () {
        /*                axios
                            .get('/Fabric_TraceabilitySys_war_exploded/userCode/key',
                                {
                                    params:{
                                        key:this.UserCode
                                    }
                                }
                            )
                            .then((response) =>{ // ???????????????????????????????????????this??????Windows
                                console.log("response",response);
                                console.log("json:",JSON.parse(JSON.stringify(response.data)));
                                console.log("json:",JSON.parse(JSON.stringify(response.data.teaArea)));
                                console.log(this);
                                /!**
                                 * ???response???.data??????????????????????????????????????????json??????
                                 * JSON.parse() ???????????????????????? JavaScript ?????????
                                 * *!/
                               /!* this.AllPojo = JSON.parse(JSON.stringify(response.data));*!/
                                if (this.TeaArea!= null){
                                    this.retr = true;
                                }
                                this.TeaArea = JSON.parse(JSON.stringify(response.data.teaArea));
                                this.TeaGarden = JSON.parse(JSON.stringify(response.data.teaGarden));
                                this.TeaTree = JSON.parse(JSON.stringify(response.data.teaTree));
                                this.TeaPick = JSON.parse(JSON.stringify(response.data.teaPick));
                                this.TeaPack = JSON.parse(JSON.stringify(response.data.teaPack));
                                this.TeaMake = JSON.parse(JSON.stringify(response.data.teaMake));
                                this.TeaRank = JSON.parse(JSON.stringify(response.data.teaRank));
                                this.TeaTesting = JSON.parse(JSON.stringify(response.data.teaTesting));
                            }, function (error) {
                                console.log(error);
                            });*/
        /*            axios
                        .get('/Fabric_TraceabilitySys_war_exploded/teaarea')
                        .then((response) =>{ // ???????????????????????????????????????this??????Windows
                            console.log("response.data???",response.data.teaAreaId1);
                            console.log("response",response);
                            console.log("json:",JSON.parse(JSON.stringify(response.data)));
                            console.log(this);
                            /!**
                             * ???response???.data??????????????????????????????????????????json??????
                             * JSON.parse() ???????????????????????? JavaScript ?????????
                             * *!/
                            this.TeaArea = JSON.parse(JSON.stringify(response.data));

                        }, function (error) {
                            console.log(error);
                        });
                    axios
                        .get('/Fabric_TraceabilitySys_war_exploded/teagarden')
                        .then((response) =>{ // ???????????????????????????????????????this??????Windows
                            console.log("response.data???",response.data.teaGardenId);
                            console.log("response",response);
                            console.log("json:",JSON.parse(JSON.stringify(response.data)));
                            console.log(this);
                            /!**
                             * ???response???.data??????????????????????????????????????????json??????
                             * JSON.parse() ???????????????????????? JavaScript ?????????
                             * *!/
                            this.TeaGarden = JSON.parse(JSON.stringify(response.data));

                        }, function (error) {
                            console.log(error);
                        });
                    axios
                        .get('/Fabric_TraceabilitySys_war_exploded/teatree')
                        .then((response) =>{ // ???????????????????????????????????????this??????Windows
                            this.TeaTree = JSON.parse(JSON.stringify(response.data));

                        }, function (error) {
                            console.log(error);
                        });
                    axios
                        .get('/Fabric_TraceabilitySys_war_exploded/teapick')
                        .then((response) =>{ // ???????????????????????????????????????this??????Windows
                            this.TeaPick = JSON.parse(JSON.stringify(response.data));
                        }, function (error) {
                            console.log(error);
                        });
                    axios
                        .get('/Fabric_TraceabilitySys_war_exploded/teamake')
                        .then((response) =>{ // ???????????????????????????????????????this??????Windows
                            this.TeaMake = JSON.parse(JSON.stringify(response.data));
                        }, function (error) {
                            console.log(error);
                        });
                    axios
                        .get('/Fabric_TraceabilitySys_war_exploded/tearank')
                        .then((response) =>{ // ???????????????????????????????????????this??????Windows
                            this.TeaRank = JSON.parse(JSON.stringify(response.data));
                        }, function (error) {
                            console.log(error);
                        });
                    axios
                        .get('/Fabric_TraceabilitySys_war_exploded/teapack')
                        .then((response) =>{ // ???????????????????????????????????????this??????Windows
                            this.TeaPack = JSON.parse(JSON.stringify(response.data));
                        }, function (error) {
                            console.log(error);
                        });
                    axios
                        .get('/Fabric_TraceabilitySys_war_exploded/teatesting')
                        .then((response) =>{ // ???????????????????????????????????????this??????Windows
                            this.TeaTesting = JSON.parse(JSON.stringify(response.data));
                        }, function (error) {
                            console.log(error);
                        });*/
    },
    methods:{
        handleChange(val) {
            console.log(val);
        },
        showInfo(a){
            console.log("showInfo???????????????")
            console.log("a:",a)
            axios
                .get('/Fabric_TraceabilitySys_war_exploded/userCode/key',
                    {
                        params:{
                            userCode:this.UserCode
                        }
                    }
                )
                .then((response) =>{ // ???????????????????????????????????????this??????Windows
                    console.log("response",response);
                    if (JSON.parse(JSON.stringify(response.data))=="???????????????"){
                        alert("???????????????")
                    }
                    if(JSON.parse(JSON.stringify(response.data))=="???????????????"){
                        alert("???????????????")
                    }else {
                        console.log("json:",JSON.parse(JSON.stringify(response.data)));
                        console.log("json:",JSON.parse(JSON.stringify(response.data.teaArea)));
                        console.log(this);
                        /**
                         * ???response???.data??????????????????????????????????????????json??????
                         * JSON.parse() ???????????????????????? JavaScript ?????????
                         * */
                        /* this.AllPojo = JSON.parse(JSON.stringify(response.data));*/
                        this.TeaArea = JSON.parse(JSON.stringify(response.data.teaArea));
                        this.TeaGarden = JSON.parse(JSON.stringify(response.data.teaGarden));
                        this.TeaTree = JSON.parse(JSON.stringify(response.data.teaTree));
                        this.TeaPick = JSON.parse(JSON.stringify(response.data.teaPick));
                        this.TeaPack = JSON.parse(JSON.stringify(response.data.teaPack));
                        this.TeaMake = JSON.parse(JSON.stringify(response.data.teaMake));
                        this.TeaRank = JSON.parse(JSON.stringify(response.data.teaRank));
                        this.TeaTesting = JSON.parse(JSON.stringify(response.data.teaTesting));
                        if (this.TeaArea!= null){
                            this.retr = true;
                        }
                    }
                }, function (error) {
                    console.log(error);
                });

        }
    }
})