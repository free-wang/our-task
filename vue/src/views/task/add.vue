<template>
  <div class="app-container">
    <el-form ref="taskParam" :model="taskParam" label-width="120px">
      <el-form-item label="清单名称">
        <el-input
          v-model="taskParam.name"
          maxlength="6"
          show-word-limit
        />
      </el-form-item>
      <el-form-item label="清单分类">
        <el-select v-model="taskParam.categoryId" placeholder="请选择...">
          <el-option v-for="category in categoryList" :key="category.id" :label="category.name" :value="category.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="所属标签">
        <el-checkbox-group v-model="taskParam.labelList">
          <el-checkbox v-for="label in labelList" :key="label.id" :label="label.name" :value="label.id" name="type" />
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="清单描述">
        <el-input
          v-model="taskParam.description"
          type="textarea"
          maxlength="20"
          show-word-limit
        />
        <el-form-item>
          <el-button type="primary" @click="addTaskParam">添加/修改</el-button>
          <el-button @click="onCancel">取消</el-button>
        </el-form-item>
      </el-form-item></el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      labelList: [],
      selectCategory: '',
      categoryList: [],
      taskParam: {
        userId: this.global.userId,
        categoryId: null,
        name: '',
        labelList: [],
        description: ''
      },
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      }
    }
  },
  created() {
    this.getUserCategoryList()
    this.getUserLabelList()
  },
  methods: {
    addTaskParam() {
      console.log(this.taskParam)
      this.$axios.post('task/save', this.taskParam).then((res) => {
        console.log(res.data)
      })
    },
    onCancel() {
      this.$message({
        message: 'cancel!',
        type: 'warning'
      })
    },
    getUserCategoryList() {
      this.$axios.get(`category/listAll/${this.global.user.id}`).then((res) => {
        this.categoryList = res.data.data
      })
    },
    getUserLabelList() {
      this.$axios.get(`label/getAllList/${this.global.user.id}`).then((res) => {
        this.labelList = res.data.data
      })
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
</style>

