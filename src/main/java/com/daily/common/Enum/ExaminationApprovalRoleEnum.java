package com.daily.common.Enum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by json on 2018/3/19.
 * Describe:
 */
public enum ExaminationApprovalRoleEnum {


    role_1("项目经理","1","项目经理 "),
    role_2("投资组长","2","投资组长"),
    role_3("法务经理","3","法务经理"),
    role_4("风控部负责人","4","风控部负责人"),
    role_5("投资副总","5","投资副总"),
    role_6("总经理","6","总经理"),
    role_7("董事长","7","董事长"),
    ;

    private ExaminationApprovalRoleEnum(String roleName, String stepFlag, String node){
        this.roleName = roleName;
        this.stepFlag=stepFlag;
        this.note = node;
    }
    private String roleName;
    private String stepFlag;
    private String note;

    public String getRoleName(){
        return this.roleName;
    }

    public String getStepFlag(){
        return this.stepFlag;
    }

    public String getNote(){
        return this.note;
    }


    public static String getRoleNameByStep(String stepFlag){
        for(ExaminationApprovalRoleEnum e : ExaminationApprovalRoleEnum.values()){
            if(e.getStepFlag().equals(stepFlag)){
                return e.getRoleName();
            }
        }
        return null;
    }
    public static List<String> getStepByRoleName(String roleName){
        List<String> list = new ArrayList<>();
        for(ExaminationApprovalRoleEnum e : ExaminationApprovalRoleEnum.values()){
            if(e.getRoleName().equals(roleName)){
                list.add(e.getStepFlag());
            }
        }
        return list;
    }

    public static List<String> getSysRoleName(){
        List<String> list = new ArrayList<>();
        for(ExaminationApprovalRoleEnum e : ExaminationApprovalRoleEnum.values()){
            list.add(e.getRoleName());
        }
        return list;
    }
}

