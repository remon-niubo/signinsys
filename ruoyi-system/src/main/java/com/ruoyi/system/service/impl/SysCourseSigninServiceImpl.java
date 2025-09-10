package com.ruoyi.system.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysCourseSigninMapper;
import com.ruoyi.system.domain.SysCourseSignin;
import com.ruoyi.system.service.ISysCourseSigninService;

/**
 * 课程签到 Service业务层处理
 * 
 * @author 
 */
@Service
public class SysCourseSigninServiceImpl implements ISysCourseSigninService 
{
    private static final Logger log = LoggerFactory.getLogger(SysCourseSigninServiceImpl.class);
    
    @Autowired
    private SysCourseSigninMapper signinMapper;

    @Override
    public SysCourseSignin selectCourseSigninById(Long signinId)
    {
        return signinMapper.selectCourseSigninById(signinId);
    }

    @Override
    public List<SysCourseSignin> selectCourseSigninList(SysCourseSignin query)
    {
        return signinMapper.selectCourseSigninList(query);
    }

    @Override
    public int insertCourseSignin(SysCourseSignin signin)
    {
        log.info("准备插入签到记录: {}", signin);
        try {
            int result = signinMapper.insertCourseSignin(signin);
            log.info("插入签到记录结果: {}", result);
            return result;
        } catch (Exception e) {
            log.error("插入签到记录失败", e);
            throw new RuntimeException("插入签到记录失败: " + e.getMessage(), e);
        }
    }

    @Override
    public int updateCourseSignin(SysCourseSignin signin)
    {
        return signinMapper.updateCourseSignin(signin);
    }

    @Override
    public int deleteCourseSigninById(Long signinId)
    {
        return signinMapper.deleteCourseSigninById(signinId);
    }

    @Override
    public int deleteCourseSigninByIds(Long[] signinIds)
    {
        return signinMapper.deleteCourseSigninByIds(signinIds);
    }

    @Override
    public int updateStatus(Long signinId, String status, String updateBy)
    {
        return signinMapper.updateStatus(signinId, status, updateBy);
    }
}