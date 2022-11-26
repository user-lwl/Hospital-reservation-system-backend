package com.lwl.yygh.enums;

public enum AuthStatusEnum {

    /**
     * 未认证
     */
    NO_AUTH(0, "未认证"),
    /**
     * 认证中
     */
    AUTH_RUN(1, "认证中"),
    /**
     * 认证成功
     */
    AUTH_SUCCESS(2, "认证成功"),
    /**
     * 认证失败
     */
    AUTH_FAIL(-1, "认证失败");

    private Integer status;
    private String name;

    AuthStatusEnum(Integer status, String name) {
        this.status = status;
        this.name = name;
    }

    public static String getStatusNameByStatus(Integer status) {
        AuthStatusEnum arrObj[] = AuthStatusEnum.values();
        for (AuthStatusEnum obj : arrObj) {
            if (status.intValue() == obj.getStatus().intValue()) {
                return obj.getName();
            }
        }
        return "";
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
