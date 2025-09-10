<template>
  <div class="app-container">
    <el-tabs type="border-card">
      <el-tab-pane label="进行中">
        <el-table :data="ongoingClasses" style="width: 100%" v-loading="loadingOngoing">
          <el-table-column prop="deptName" label="班级名称" width="180"></el-table-column>
          <el-table-column prop="startTime" label="开始时间" width="180"></el-table-column>
          <el-table-column prop="endTime" label="结束时间" width="180"></el-table-column>
          <el-table-column prop="size" label="学员人数" width="100"></el-table-column>
          <el-table-column prop="status" label="班级状态"></el-table-column>
        </el-table>
        <div v-if="!loadingOngoing && ongoingClasses.length === 0" class="no-data">
          <p>暂无进行中的班级数据</p>
        </div>
      </el-tab-pane>
      <el-tab-pane label="已完结">
        <el-table :data="finishedClasses" style="width: 100%" v-loading="loadingFinished">
          <el-table-column prop="deptName" label="班级名称" width="180"></el-table-column>
          <el-table-column prop="startTime" label="开始时间" width="180"></el-table-column>
          <el-table-column prop="endTime" label="结束时间" width="180"></el-table-column>
          <el-table-column prop="size" label="学员人数" width="100"></el-table-column>
          <el-table-column prop="status" label="班级状态"></el-table-column>
        </el-table>
        <div v-if="!loadingFinished && finishedClasses.length === 0" class="no-data">
          <p>暂无已完结的班级数据</p>
        </div>
      </el-tab-pane>
      <el-tab-pane label="待发布">
        <el-table :data="unpublishedClasses" style="width: 100%" v-loading="loadingUnpublished">
          <el-table-column prop="deptName" label="班级名称" width="180"></el-table-column>
          <el-table-column prop="startTime" label="开始时间" width="180"></el-table-column>
          <el-table-column prop="endTime" label="结束时间" width="180"></el-table-column>
          <el-table-column prop="size" label="学员人数" width="100"></el-table-column>
          <el-table-column prop="status" label="班级状态"></el-table-column>
        </el-table>
        <div v-if="!loadingUnpublished && unpublishedClasses.length === 0" class="no-data">
          <p>暂无待发布的班级数据</p>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import { listOngoingClass, listFinishedClass, listUnpublishedClass } from '@/api/system/class'
import { getToken } from '@/utils/auth'

export default {
  name: 'Index',
  data() {
    return {
      // 进行中的班级
      ongoingClasses: [],
      // 已完结的班级
      finishedClasses: [],
      // 待发布的班级
      unpublishedClasses: [],
      // 加载状态
      loadingOngoing: false,
      loadingFinished: false,
      loadingUnpublished: false
    }
  },
  created() {
    // 检查用户是否已登录
    if (getToken()) {
      this.loadAllClasses()
    } else {
      this.$message.warning('请先登录系统')
    }
  },
  methods: {
    // 加载所有班级数据
    async loadAllClasses() {
      await this.loadOngoingClasses()
      await this.loadFinishedClasses()
      await this.loadUnpublishedClasses()
    },

    // 加载进行中的班级
    async loadOngoingClasses() {
      this.loadingOngoing = true
      try {
        const response = await listOngoingClass()
        console.log('进行中的班级数据:', response)
        this.ongoingClasses = response.rows || response.data || []
      } catch (error) {
        console.error('加载进行中的班级失败:', error)
        // 检查是否是认证错误
        if (error.response && error.response.status === 401) {
          this.$message.error('认证失败，请重新登录')
        } else {
          this.$message.error('加载进行中的班级失败')
        }
      } finally {
        this.loadingOngoing = false
      }
    },

    // 加载已完结的班级
    async loadFinishedClasses() {
      this.loadingFinished = true
      try {
        const response = await listFinishedClass()
        console.log('已完结的班级数据:', response)
        this.finishedClasses = response.rows || response.data || []
      } catch (error) {
        console.error('加载已完结的班级失败:', error)
        // 检查是否是认证错误
        if (error.response && error.response.status === 401) {
          this.$message.error('认证失败，请重新登录')
        } else {
          this.$message.error('加载已完结的班级失败')
        }
      } finally {
        this.loadingFinished = false
      }
    },

    // 加载待发布的班级
    async loadUnpublishedClasses() {
      this.loadingUnpublished = true
      try {
        const response = await listUnpublishedClass()
        console.log('待发布的班级数据:', response)
        this.unpublishedClasses = response.rows || response.data || []
      } catch (error) {
        console.error('加载待发布的班级失败:', error)
        // 检查是否是认证错误
        if (error.response && error.response.status === 401) {
          this.$message.error('认证失败，请重新登录')
        } else {
          this.$message.error('加载待发布的班级失败')
        }
      } finally {
        this.loadingUnpublished = false
      }
    }
  }
}
</script>

<style scoped>
.app-container {
  padding: 20px;
}

.no-data {
  text-align: center;
  padding: 20px;
  color: #909399;
}
</style>