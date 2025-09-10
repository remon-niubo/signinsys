<template>
  <div class="app-container">
    <!-- 查询条件 -->
    <el-form :model="queryParams" ref="queryRef" :inline="true" label-width="80px">
      <el-form-item label="签到标题">
        <el-input v-model="queryParams.title" placeholder="请输入签到标题" clearable />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 工具栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['system:signin:add']"
          type="primary"
          plain
          icon="el-icon-plus"
          @click="handleAdd"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['system:signin:edit']"
          type="success"
          plain
          icon="el-icon-edit"
          :disabled="single"
          @click="handleUpdate"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['system:signin:remove']"
          type="danger"
          plain
          icon="el-icon-delete"
          :disabled="multiple"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
    </el-row>

    <!-- 表格 -->
    <el-table v-loading="loading" :data="signinList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" />
      <el-table-column prop="signinId" label="签到ID" width="80" />
      <el-table-column prop="title" label="签到标题" />
      <el-table-column prop="courseId" label="课程ID" />
      <el-table-column prop="deptId" label="部门ID" />
      <el-table-column prop="startTime" label="开始时间" />
      <el-table-column prop="endTime" label="结束时间" />
      <el-table-column label="操作" align="center" width="220">
        <template slot-scope="scope">
          <el-button
            v-hasPermi="['system:signin:do']"
            size="mini"
            type="text"
            @click="handleSignin(scope.row)"
          >签到</el-button>
          <el-button
            v-hasPermi="['system:signin:result']"
            size="mini"
            type="text"
            @click="handleResult(scope.row)"
          >查看结果</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 新增/修改弹窗 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="签到ID" prop="signinId" v-if="form.signinId">
          <el-input v-model="form.signinId" disabled />
        </el-form-item>
        <el-form-item label="签到标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入签到标题" />
        </el-form-item>
        <el-form-item label="课程ID" prop="courseId">
          <el-input v-model.number="form.courseId" placeholder="请输入课程ID" />
        </el-form-item>
        <el-form-item label="部门ID" prop="deptId">
          <el-input v-model.number="form.deptId" placeholder="请输入部门ID" />
        </el-form-item>
        <el-form-item label="开始时间" prop="startTime">
          <el-date-picker
            v-model="form.startTime"
            type="datetime"
            placeholder="选择开始时间"
            value-format="yyyy-MM-dd HH:mm:ss"
            style="width: 100%">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="endTime">
          <el-date-picker
            v-model="form.endTime"
            type="datetime"
            placeholder="选择结束时间"
            value-format="yyyy-MM-dd HH:mm:ss"
            style="width: 100%">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="open = false">取消</el-button>
        <el-button type="primary" @click="submitForm">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listSignin,
  getSignin,
  addSignin,
  updateSignin,
  delSignin,
  doSignin,
  getResult
} from "@/api/system/signin";

export default {
  name: "Signin",
  data() {
    return {
      loading: false,
      signinList: [],
      total: 0,
      title: "",
      open: false,
      single: true,
      multiple: true,
      ids: [],
      form: {
        signinId: undefined,
        title: undefined,
        courseId: undefined,
        deptId: undefined,
        startTime: undefined,
        endTime: undefined
      },
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: ""
      },
      // 表单校验规则
      rules: {
        title: [{ required: true, message: "签到标题不能为空", trigger: "blur" }],
        courseId: [
          { required: true, message: "课程ID不能为空", trigger: "blur" },
          { type: "number", message: "课程ID必须为数字值", trigger: "blur" }
        ],
        deptId: [
          { required: true, message: "部门ID不能为空", trigger: "blur" },
          { type: "number", message: "部门ID必须为数字值", trigger: "blur" }
        ],
        startTime: [{ required: true, message: "开始时间不能为空", trigger: "change" }],
        endTime: [{ required: true, message: "结束时间不能为空", trigger: "change" }]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    // 查询列表
    getList() {
      this.loading = true;
      listSignin(this.queryParams).then(res => {
        this.signinList = res.rows || [];
        this.total = res.total || 0;
        this.loading = false;
      }).catch(() => { this.loading = false; });
    },
    // 搜索
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    // 重置
    resetQuery() {
      this.queryParams.title = "";
      this.handleQuery();
    },
    // 多选
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.signinId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加签到";
    },
    // 修改（支持工具栏与行内）
    handleUpdate(row) {
      const signinId = (row && row.signinId) || this.ids[0];
      if (!signinId) return;
      getSignin(signinId).then(res => {
        this.form = Object.assign({}, res.data || {});
        this.open = true;
        this.title = "修改签到";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          // 确保数值字段正确转换
          const submitForm = {
            ...this.form,
            courseId: this.form.courseId ? Number(this.form.courseId) : undefined,
            deptId: this.form.deptId ? Number(this.form.deptId) : undefined
          };
          
          if (this.form.signinId != undefined) {
            updateSignin(submitForm).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            }).catch(err => {
              this.$modal.msgError("修改失败: " + (err.message || '未知错误'));
            });
          } else {
            // 新增时删除signinId字段
            delete submitForm.signinId;
            
            addSignin(submitForm).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            }).catch(err => {
              this.$modal.msgError("新增失败: " + (err.message || '未知错误'));
            });
          }
        } else {
          this.$modal.msgError("表单校验失败，请检查输入信息");
        }
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        signinId: undefined,
        title: undefined,
        courseId: undefined,
        deptId: undefined,
        startTime: undefined,
        endTime: undefined
      };
      this.resetForm("form");
    },
    // 删除
    handleDelete(row) {
      const ids = row && row.signinId ? [row.signinId] : this.ids;
      if (!ids.length) return;
      this.$confirm("确认删除选中的签到记录吗？", "提示", { type: "warning" })
        .then(() => {
          // 循环删除每个ID
          const deletePromises = ids.map(id => delSignin(id));
          return Promise.all(deletePromises);
        })
        .then(() => {
          this.$message.success("删除成功");
          this.getList();
        })
        .catch(() => {});
    },
    // 用户签到
    handleSignin(row) {
      if (!row || !row.signinId) {
        this.$modal.msgWarning("请选择要签到的记录");
        return;
      }
      
      console.log("执行用户签到，签到ID:", row.signinId);
      doSignin(row.signinId).then(() => {
        this.$modal.msgSuccess("签到成功");
      }).catch((error) => {
        console.error("签到失败:", error);
        this.$modal.msgError("签到失败: " + (error.message || '未知错误'));
      });
    },
    // 查看结果
    handleResult(row) {
      if (!row || !row.signinId) {
        this.$modal.msgWarning("请选择要查看结果的记录");
        return;
      }
      
      console.log("查看签到结果，签到ID:", row.signinId);
      getResult(row.signinId).then(res => {
        const data = res.data || {};
        this.$alert(
          `已签到用户: ${JSON.stringify(data.signed || [])}\n未签到用户: ${JSON.stringify(data.unsigned || [])}`,
          "签到结果",
          { dangerouslyUseHTMLString: false }
        );
      }).catch((error) => {
        console.error("查看签到结果失败:", error);
        this.$modal.msgError("查看签到结果失败: " + (error.message || '未知错误'));
      });
    }
  }
};
</script>

<style scoped>
.mb8 { margin-bottom: 8px; }
</style>