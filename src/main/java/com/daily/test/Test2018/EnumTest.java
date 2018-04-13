package com.daily.test.Test2018;

import com.alibaba.fastjson.JSON;
import com.daily.common.Enum.AgencyfeeApprovalRoleEnum;
import com.daily.common.Enum.ExaminationApprovalRoleEnum;
import org.json.JSONObject;

import java.util.*;

/**
 * Created by json on 2018/3/19.
 * Describe:
 */
public class EnumTest {

    public static void main(String[] args) {
        List<Map> nodes=new ArrayList<>();
        for (AgencyfeeApprovalRoleEnum e:AgencyfeeApprovalRoleEnum.values()){
            Map map=new HashMap();
            map.put("stepFlag", e.getStepFlag());
            map.put("roleName",e.getRoleName());
            nodes.add(map);
        }
        for (Map node : nodes) {
            System.out.println(node.toString());
        }

    }



}
