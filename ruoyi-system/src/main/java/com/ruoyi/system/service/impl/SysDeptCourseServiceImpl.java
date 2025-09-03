package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.domain.SysDeptCourse;
import com.ruoyi.system.mapper.SysDeptCourseMapper;
import com.ruoyi.system.service.ISysDeptCourseService;

/**
 * 部门课程 Service 业务层处理
 * 
 * @author must
 * @date 2025-08-28
 */
@Service
public class SysDeptCourseServiceImpl implements ISysDeptCourseService
{
    @Autowired
    private SysDeptCourseMapper deptCourseMapper;

    /**
     * 查询课程
     * 
     * @param courseId 课程ID
     * @return 课程
     */
    @Override
    public SysDeptCourse selectDeptCourseById(Long courseId)
    {
        return deptCourseMapper.selectDeptCourseById(courseId);
    }

    /**
     * 查询课程列表
     * 
     * @param deptCourse 查询条件
     * @return 课程集合
     */
    @Override
    public List<SysDeptCourse> selectDeptCourseList(SysDeptCourse deptCourse)
    {
        return deptCourseMapper.selectDeptCourseList(deptCourse);
    }

    /**
     * 新增课程
     * 
     * @param deptCourse 课程
     * @return 结果
     */
    @Override
    public int insertDeptCourse(SysDeptCourse deptCourse)
    {
        return deptCourseMapper.insertDeptCourse(deptCourse);
    }

    /**
     * 修改课程
     * 
     * @param deptCourse 课程
     * @return 结果
     */
    @Override
    public int updateDeptCourse(SysDeptCourse deptCourse)
    {
        return deptCourseMapper.updateDeptCourse(deptCourse);
    }

    /**
     * 批量删除课程
     * 
     * @param courseIds 需要删除的课程ID
     * @return 结果
     */
    @Override
    public int deleteDeptCourseByIds(Long[] courseIds)
    {
        return deptCourseMapper.deleteDeptCourseByIds(courseIds);
    }

    /**
     * 删除课程信息
     * 
     * @param courseId 课程ID
     * @return 结果
     */
    @Override
    public int deleteDeptCourseById(Long courseId)
    {
        return deptCourseMapper.deleteDeptCourseById(courseId);
    }
}
