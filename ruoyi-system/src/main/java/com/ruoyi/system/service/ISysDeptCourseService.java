package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysDeptCourse;
import java.util.List;

public interface ISysDeptCourseService {
    SysDeptCourse selectDeptCourseById(Long courseId);

    List<SysDeptCourse> selectDeptCourseList(SysDeptCourse deptCourse);

    int insertDeptCourse(SysDeptCourse deptCourse);

    int updateDeptCourse(SysDeptCourse deptCourse);

    int deleteDeptCourseById(Long courseId);

    int deleteDeptCourseByIds(Long[] courseIds);
}
