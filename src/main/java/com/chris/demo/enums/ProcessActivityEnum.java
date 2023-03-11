package com.chris.demo.enums;

/**
 * @Author Lilun
 * @Date 2020-11-12 22:30
 * @Description
 **/
public enum ProcessActivityEnum {

    HardwareAct("硬件领域活动"),
    StructureAct("结构领域活动"),
    SoftwareAct("软件领域活动"),
    ImageAct("影像领域活动"),
    AuthAct("认证活动"),
    GTMAct("GTM经理活动"),
    MKTAct("MKT经理活动"),
    PurchaseAct("采购代表活动"),
    ProductPMAct("整机项目经理活动"),
    IndonesianInterpreterAct("印尼翻译员活动"),
    HardwarePMReviewAct("硬件PM审核活动"),
    StructurePMReviewAct("结构PM审核活动"),
    SoftwarePMReviewAct("软件PM审核活动"),
    ImagePMReviewAct("影像PM审核活动"),
    HardwareAccepter("硬件验收人活动"),
    StructureAccepter("结构验收人活动"),
    SoftwareAccepter("软件验收人活动"),
    ImageAccepter("影像验收人活动"),
    AuthAccepter("认证验收人活动"),
    GTMAccepter("GTM验收人活动"),
    MKTAccepter("MKT验收人活动"),
    PurchaseAccepter("采购代表验收人活动"),
    ProductPMAccepter("整机项目经理验收人活动"),
    IndonesianHrbpAccepter("印尼HRBP验收人活动"),
    ProductPMCollect("整机项目经理汇总活动"),
    Countersign("会签");


    private String actDisplay;

    ProcessActivityEnum(String actDisplay) {
        this.actDisplay = actDisplay;
    }

    public String getActDisplay() {
        return actDisplay;
    }

    public void setActDisplay(String actDisplay) {
        this.actDisplay = actDisplay;
    }

    public static void main(String[] args) {
        System.out.println(ProcessActivityEnum.valueOf("硬件领域活动").name());
    }
}
