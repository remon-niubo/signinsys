package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysDeptApply;

public interface ISysDeptApplyService {

    SysDeptApply selectDeptApplyById(Long applyId);

    List<SysDeptApply> selectDeptApplyList(SysDeptApply query);

    int insertDeptApply(SysDeptApply apply);

    int updateDeptApply(SysDeptApply apply);

    int deleteDeptApplyByIds(Long[] applyIds);

    int approve(Long applyId); // 审批通过

    int reject(Long applyId);  // 审批拒绝
}
