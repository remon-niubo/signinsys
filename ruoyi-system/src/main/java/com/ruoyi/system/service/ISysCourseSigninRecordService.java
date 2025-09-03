package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysCourseSigninRecord;
import java.util.List;

/**
 * 课程签到记录 服务层
 * 
 * @author Must77
 */
public interface ISysCourseSigninRecordService {
    /**
     * 查询单个ID下的签到活动
     * 
     * @param userId 用户ID
     * @return 签到活动列表
     */
    List<SysCourseSigninRecord> selectByUserId(Long userId);

    /**
     * 用户签到
     * 
     * @param recordId 签到记录ID
     * @param userId 用户ID
     * @return 操作影响的行数
     */
    int doSignin(Long recordId, Long userId);

    /**
     * 批量插入签到记录。admin在创建签到元数据的时候，预生成该班级下所有用户的签到活动
     * 
     * @param records 需要插入的签到记录列表
     * @return 插入操作影响的行数
     */
    int batchInsert(List<SysCourseSigninRecord> records);
}
