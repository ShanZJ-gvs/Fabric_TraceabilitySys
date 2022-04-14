const vm = new Vue({
    data() {
        return {
            pickerOptions: {
                shortcuts: [{
                    text: '今天',
                    onClick(picker) {
                        picker.$emit('pick', new Date());
                    }
                }, {
                    text: '昨天',
                    onClick(picker) {
                        const date = new Date();
                        date.setTime(date.getTime() - 3600 * 1000 * 24);
                        picker.$emit('pick', date);
                    }
                }, {
                    text: '一周前',
                    onClick(picker) {
                        const date = new Date();
                        date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
                        picker.$emit('pick', date);
                    }
                }]
            },
            value1: '',
            datatime: '',
            value3: '',
            company1:document.getElementById("company").innerHTML,
            employee:{ //
                id:"test",
                name:"test",
                sex:"test"
            },
            pojo:{// 茶园茶区通用
                id:"",
                name:"",
                address:"",
                area:"",
                altitude:"jinweidu1",
                areaid2:"",
                gardenid2:"",
                height:"",
                kind:"",
                state:"",
                cultivate:"",
                genv:""

            },
            tea:{
                id:"",//茶叶批id
                id2:"",//所属茶树id
                output:"0",//产量
                quality:"合格", // 鲜叶质量
                makeway:"炒制", //制茶工艺
                rank:"优",  // 茶叶等级
            },
            pojo2:{
                smallBoxId:"", //小盒编号
                bigBoxId:"",   //大盒编号
                testingId:"",  //质检编号
                testingQ:"",   //质检结果
            }
        };
    },
    method:{

    }
});

vm.$mount("#root");