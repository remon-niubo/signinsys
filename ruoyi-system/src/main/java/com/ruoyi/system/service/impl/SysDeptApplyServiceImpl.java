package com.ruoyi.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.SysDeptApply;
import com.ruoyi.system.mapper.SysDeptApplyMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.service.ISysDeptApplyService;

@Service
public class SysDeptApplyServiceImpl implements ISysDeptApplyService {

    @Resource
    private SysDeptApplyMapper deptApplyMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public SysDeptApply selectDeptApplyById(Long applyId) {
        return deptApplyMapper.selectDeptApplyById(applyId);
    }

    @Override
    public List<SysDeptApply> selectDeptApplyList(SysDeptApply query) {
        return deptApplyMapper.selectDeptApplyList(query);
    }

    @Override
    public int insertDeptApply(SysDeptApply apply) {
        apply.setStatus("0"); // 待审核
        apply.setCreateBy(SecurityUtils.getUsername());
        apply.setUpdateBy(SecurityUtils.getUsername());
        return deptApplyMapper.insertDeptApply(apply);
    }

    @Override
    public int updateDeptApply(SysDeptApply apply) {
        apply.setUpdateBy(SecurityUtils.getUsername());
        return deptApplyMapper.updateDeptApply(apply);
    }

    @Override
    public int deleteDeptApplyByIds(Long[] applyIds) {
        return deptApplyMapper.deleteDeptApplyByIds(applyIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int approve(Long applyId) {
        SysDeptApply apply = deptApplyMapper.selectDeptApplyById(applyId);
        if (apply == null) return 0;
        // 更新用户所在部门
        sysUserMapper.updateUserDept(apply.getUserId(), apply.getDeptId());
        // 更新申请状态
        return deptApplyMapper.updateStatus(applyId, "1", SecurityUtils.getUsername());
    }

    @Override
    public int reject(Long applyId) {
        return deptApplyMapper.updateStatus(applyId, "2", SecurityUtils.getUsername());
    }
}
