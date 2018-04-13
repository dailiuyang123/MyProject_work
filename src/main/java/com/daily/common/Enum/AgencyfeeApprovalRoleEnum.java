package com.daily.common.Enum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by json on 2018/3/19.
 * Describe:
 */
public enum AgencyfeeApprovalRoleEnum {
    role_1("发起人","1","发起人 "),
    role_2("分管合伙人","2","分管合伙人"),
    role_3("分管运营合伙人","3","分管运营合伙人"),
    role_4("董事长","4","董事长");

    private AgencyfeeApprovalRoleEnum(String roleName, String stepFlag, String node){
        this.roleName = roleName;
        this.stepFlag=stepFlag;
        this.note = node;
    }

    /**
     *
     * roleFlag     7个步骤对应的角色标志 或许为ID
     * stepFlag   步骤标志 1-7
     * node        备注
     */
    private String roleName;
    private String stepFlag;
    private String note;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getStepFlag() {
        return stepFlag;
    }

    public void setStepFlag(String stepFlag) {
        this.stepFlag = stepFlag;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
