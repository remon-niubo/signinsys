package com.ruoyi.system.service.impl;

import java.util.List;
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
    @Autowired
    private SysCourseSigninMapper signinMapper;

    @Override
    public SysCourseSignin selectCourseSigninById(Long id)
    {
        return signinMapper.selectCourseSigninById(id);
    }

    @Override
    public List<SysCourseSignin> selectCourseSigninList(SysCourseSignin query)
    {
        return signinMapper.selectCourseSigninList(query);
    }

    @Override
    public int insertCourseSignin(SysCourseSignin signin)
    {
        return signinMapper.insertCourseSignin(signin);
    }

    @Override
    public int updateCourseSignin(SysCourseSignin signin)
    {
        return signinMapper.updateCourseSignin(signin);
    }

    @Override
    public int deleteCourseSigninById(Long id)
    {
        return signinMapper.deleteCourseSigninById(id);
    }

    @Override
    public int deleteCourseSigninByIds(Long[] ids)
    {
        return signinMapper.deleteCourseSigninByIds(ids);
    }

    @Override
    public int updateStatus(Long signinId, String status, String updateBy)
    {
        return signinMapper.updateStatus(signinId, status, updateBy);
    }
}
