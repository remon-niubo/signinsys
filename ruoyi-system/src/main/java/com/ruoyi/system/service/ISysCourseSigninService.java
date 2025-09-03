package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysCourseSignin;

/**
 * 课程签到 Service接口
 * 
 * @author Must77
 */
public interface ISysCourseSigninService
{
    /**
     * 根据ID查询课程签到
     * 
     * @param id 课程签到ID
     * @return 课程签到
     */
    public SysCourseSignin selectCourseSigninById(Long id);

    /**
     * 查询课程签到列表
     * 
     * @param query 查询条件
     * @return 课程签到集合
     */
    public List<SysCourseSignin> selectCourseSigninList(SysCourseSignin query);

    /**
     * 新增课程签到
     * 
     * @param signin 课程签到
     * @return 结果
     */
    public int insertCourseSignin(SysCourseSignin signin);

    /**
     * 修改课程签到
     * 
     * @param signin 课程签到
     * @return 结果
     */
    public int updateCourseSignin(SysCourseSignin signin);

    /**
     * 删除课程签到
     * 
     * @param id 课程签到ID
     * @return 结果
     */
    public int deleteCourseSigninById(Long id);

    /**
     * 批量删除课程签到
     * 
     * @param ids 需要删除的ID数组
     * @return 结果
     */
    public int deleteCourseSigninByIds(Long[] ids);

    /**
     * 更新签到状态
     * 
     * @param signinId 签到ID
     * @param status 状态
     * @param updateBy 更新人
     * @return 结果
     */
    public int updateStatus(Long signinId, String status, String updateBy);
}
