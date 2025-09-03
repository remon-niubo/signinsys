<template>
  <div class="app-container">
    <el-table v-loading="loading" :data="courseList">
      <el-table-column label="课程名称" prop="courseName" />
      <el-table-column label="课程描述" prop="courseDesc" />
      <el-table-column label="所属部门" prop="deptName" />
    </el-table>
  </div>
</template>

<script>
import { listMyDeptCourse } from '@/api/system/deptCourse'

export default {
  name: "MyCourse",
  data() {
    return {
      loading: false,
      courseList: []
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.loading = true
      listMyDeptCourse().then(res => {
        this.courseList = res.rows
        this.loading = false
      })
    }
  }
}
</script>
