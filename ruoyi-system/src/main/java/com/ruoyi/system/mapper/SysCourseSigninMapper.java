package com.ruoyi.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.system.domain.SysCourseSignin;

/**
 * 课程签到 数据层
 * 
 * @author Must77
 */
public interface SysCourseSigninMapper {
    /**
     * 根据ID查询课程签到信息
     * 
     * @param signinId 课程签到ID
     * @return 课程签到信息
     */
    SysCourseSignin selectCourseSigninById(Long signinId);

    /**
     * 根据条件查询课程签到列表
     * 
     * @param query 课程签到对象，包含以下字段：
     * TODO: 确定所需查询条件
     * @return 课程签到列表
     */
    List<SysCourseSignin> selectCourseSigninList(SysCourseSignin query);

    /**
     * 新增课程签到
     * 
     * @param courseSignin 课程签到信息
     * @return 结果
     */
    int insertCourseSignin(SysCourseSignin courseSignin);

    /**
     * 修改课程签到
     * 
     * @param courseSignin 课程签到信息
     * @return 结果
     */
    int updateCourseSignin(SysCourseSignin courseSignin);

    /**
     * 删除课程签到（根据ID）
     * 
     * @param signinId 课程签到ID
     * @return 结果
     */
    int deleteCourseSigninById(Long signinId);

    /**
     * 批量删除课程签到
     * 
     * @param signinIds 需要删除的课程签到ID数组
     * @return 结果
     */
    int deleteCourseSigninByIds(Long[] signinIds);
    
    /**
     * 更新签到状态
     * 
     * @param signinId 签到ID
     * @param status 状态
     * @param updateBy 更新人
     * @return 结果
     */
    int updateStatus(@Param("signinId") Long signinId, 
                     @Param("status") String status, 
                     @Param("updateBy") String updateBy);
}