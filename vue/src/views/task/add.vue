<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px">
      <el-form-item label="清单名称">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="清单分类">
        <el-select v-model="selectCategory" placeholder="请选择...">
          <el-option v-for="category in categoryList" :key="category.id" :label="category.name" :value="category.name" />
        </el-select>
      </el-form-item>
      <el-form-item label="所属标签">
        <el-checkbox-group v-model="form.type">
          <el-checkbox label="Online activities" name="type" />
          <el-checkbox label="Promotion activities" name="type" />
          <el-checkbox label="Offline activities" name="type" />
          <el-checkbox label="Simple brand exposure" name="type" />
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="清单描述">
        <el-input v-model="form.desc" type="textarea" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">添加/修改</el-button>
        <el-button @click="onCancel">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      selectCategory: '',
      categoryList: [],
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
  },
  methods: {
    onSubmit() {
      this.$message('submit!')
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
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
</style>

