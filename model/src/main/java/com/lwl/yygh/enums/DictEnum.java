package com.lwl.yygh.enums;

public enum DictEnum {
    /**
     * 医院等级
     */
    HOSTYPE("Hostype", "医院等级"),
    /**
     * 证件类型
     */
    CERTIFICATES_TYPE("CertificatesType", "证件类型"),
    ;

    private String dictCode;
    private String msg;

    DictEnum(String dictCode, String msg) {
        this.dictCode = dictCode;
        this.msg = msg;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
