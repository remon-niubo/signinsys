<template>
  <div class="app-container">
    <el-card>
      <div slot="header" class="clearfix">
        <span>部门课程管理</span>
      </div>
      
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            v-hasPermi="['system:deptCourse:add']"
            type="primary"
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd"
          >新增</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            v-hasPermi="['system:deptCourse:edit']"
            type="success"
            icon="el-icon-edit"
            size="mini"
            :disabled="single"
            @click="handleUpdate"
          >修改</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            v-hasPermi="['system:deptCourse:remove']"
            type="danger"
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
          >删除</el-button>
        </el-col>
      </el-row>

      <el-table 
        :data="courseList" 
        v-loading="loading" 
        style="width: 100%"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column prop="courseId" label="ID" width="80" />
        <el-table-column prop="deptId" label="部门ID" width="120" />
        <el-table-column prop="deptName" label="部门名称" width="120" />
        <el-table-column prop="courseName" label="课程名称" />
        <el-table-column prop="courseDesc" label="课程描述" />
        <el-table-column label="操作" width="150" v-hasPermi="['system:deptCourse:edit','system:deptCourse:remove']">
          <template slot-scope="scope">
            <el-button 
              size="mini" 
              type="text" 
              icon="el-icon-edit" 
              v-hasPermi="['system:deptCourse:edit']"
              @click="handleEdit(scope.row)"
            >编辑</el-button>
            <el-button 
              size="mini" 
              type="text" 
              icon="el-icon-delete" 
              v-hasPermi="['system:deptCourse:remove']"
              @click="handleDelete(scope.row)"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog :title="title" :visible.sync="dialogVisible" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="所属部门" prop="deptId" v-if="!form.courseId">
          <treeselect
            v-model="form.deptId"
            :options="deptOptions"
            :normalizer="normalizer"
            :show-count="true"
            placeholder="请选择所属部门"
          />
        </el-form-item>
        <el-form-item label="课程名称" prop="courseName">
          <el-input v-model="form.courseName" placeholder="请输入课程名称" />
        </el-form-item>
        <el-form-item label="课程描述" prop="courseDesc">
          <el-input type="textarea" v-model="form.courseDesc" placeholder="请输入课程描述" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "DeptCourse",
  data() {
    return {
      loading: false,
      single: true,
      multiple: true,
      title: "",
      dialogVisible: false,
      courseList: [
        { courseId: 1, deptId: 101, deptName: '一部', courseName: '数学', courseDesc: '基础数学课程' },
        { courseId: 2, deptId: 102, deptName: '二部', courseName: '英语', courseDesc: '基础英语课程' },
        { courseId: 3, deptId: 103, deptName: '三部', courseName: '物理', courseDesc: '基础物理课程' }
      ],
      deptOptions: [],
      form: {
        courseId: undefined,
        deptId: undefined,
        courseName: "",
        courseDesc: ""
      },
      rules: {
        deptId: [
          { required: true, message: "所属部门不能为空", trigger: "blur" }
        ],
        courseName: [
          { required: true, message: "课程名称不能为空", trigger: "blur" }
        ]
      }
    }
  },
  methods: {
    handleAdd() {
      this.dialogVisible = true;
      this.title = "添加课程";
    },
    handleUpdate() {},
    handleDelete() {},
    handleSelectionChange() {},
    handleEdit() {},
    submitForm() {},
    cancel() {
      this.dialogVisible = false;
    }
  }
}
</script>