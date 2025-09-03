package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class SysDeptCourse extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 课程ID */
    private Long courseId;

    /** 部门ID */
    @Excel(name = "部门ID")
    private Long deptId;
    
    /** 部门名称 **/
    @Excel(name = "部门名称")
    private String deptName;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String courseName;

    /** 课程描述 */
    @Excel(name = "课程描述")
    private String courseDesc;

    // getter/setter
    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }

    public Long getDeptId() { return deptId; }
    public void setDeptId(Long deptId) { this.deptId = deptId; }
    
    public String getDeptName() { return deptName; }
    public void setDeptName(String deptName) { this.deptName = deptName; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getCourseDesc() { return courseDesc; }
    public void setCourseDesc(String courseDesc) { this.courseDesc = courseDesc; }
}
