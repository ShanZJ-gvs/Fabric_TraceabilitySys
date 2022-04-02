package com.gvssimux.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;




@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class AllPojo {

    TeaArea teaArea;


    TeaGarden teaGarden;


    TeaTree teaTree;


    TeaPick teaPick;


    TeaMake teaMake;


    TeaRank teaRank;


    TeaPack teaPack;


    TeaTesting teaTesting;

}
