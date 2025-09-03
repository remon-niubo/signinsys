package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysCourseSigninRecord;
import java.util.List;

/**
 * 课程签到记录 数据层
 * 
 * @author Must77
 */
public interface SysCourseSigninRecordMapper {
    /**
     * 查询单个ID下的签到活动
     * 
     * @param userId 用户ID
     * @return 签到活动列表
     */
    List<SysCourseSigninRecord> selectByUserId(Long userId);

    /**
     * 更新签到活动的状态
     * 
     * @param record 需要更新的签到活动
     * @return 更新操作影响的行数
     */
    int updateSigninStatus(SysCourseSigninRecord record);

    /**
     * 批量插入签到记录。admin在创建签到元数据的时候，预生成该班级下所有用户的签到活动
     * 
     * @param records 需要插入的签到记录列表
     * @return 插入操作影响的行数
     */
    int batchInsert(List<SysCourseSigninRecord> records);
}
