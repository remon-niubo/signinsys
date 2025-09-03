package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class SysDeptApply extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 申请ID */
    private Long applyId;

    /** 申请用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 申请部门ID */
    @Excel(name = "部门ID")
    private Long deptId;

    /** 申请状态（0待审核 1通过 2拒绝） */
    @Excel(name = "状态", readConverterExp = "0=待审核,1=通过,2=拒绝")
    private String status;

    /** 仅用于列表展示的扩展字段 */
    private String userName;  // 申请人用户名
    private String nickName;  // 申请人昵称
    private String deptName;  // 申请部门名

    // getter/setter
    public Long getApplyId() { return applyId; }
    public void setApplyId(Long applyId) { this.applyId = applyId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getDeptId() { return deptId; }
    public void setDeptId(Long deptId) { this.deptId = deptId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getNickName() { return nickName; }
    public void setNickName(String nickName) { this.nickName = nickName; }
    public String getDeptName() { return deptName; }
    public void setDeptName(String deptName) { this.deptName = deptName; }
}
