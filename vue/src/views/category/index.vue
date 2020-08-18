<template>
  <div>
    <el-container height="100%">
      <el-main>
        <el-table :data="categoryParamList" stripe style="width: 100%" border>
          <el-table-column prop="name" label="分类名称" align="center" />
          <el-table-column prop="count" label="所含清单总数" align="center" />
          <el-table-column width="300" label="操作" align="center">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="changeCategory(scope.row)">编辑修改</el-button>
              <el-divider direction="vertical" />
              <el-button type="text" size="small" @click="deleteCategoryById(scope.row.id)">标记完成</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>
    <el-footer>
      <el-row>
        <el-col :span="16"><div>
          <el-pagination
            background
            :current-page="currentPage"
            :page-sizes="[100, 200, 300, 400]"
            :page-size="100"
            layout="total, sizes, prev, pager, next, jumper"
            :total="400"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
          />
        </div></el-col>
        <el-col :span="8"><div>
          <el-button type="text" @click="addCategory()">添加分类</el-button>
        </div></el-col>
      </el-row>
    </el-footer>
  </div>
</template>

<style>

</style>

<script>
export default {
  data() {
    return {
      currentPage: 1,
      categoryParamList: []
    }
  },
  created() {
    this.getUserCategoryParamList()
  },
  methods: {
    getUserCategoryParamList() {
      this.$axios.get(`category/listAll/${this.global.user.id}`).then((res) => {
        this.categoryParamList = res.data.data
      })
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`)
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`)
    },
    deleteCategoryById(categoryId) {
      this.$axios.get(`category/delete/${categoryId}`).then((res) => {
        this.categoryParamList.some((item, i) => {
          if (item.id === categoryId) {
            this.categoryParamList.splice(i, 1)
            return true
          }
        })
      })
    },
    changeCategory(category) {
      this.$prompt('请输入新的分类名称', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        // inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
        inputErrorMessage: '邮箱格式不正确'
      }).then(({ value }) => {
        console.log(category)
        category.name = value

        this.$axios.post(`category/update/${category.id}`, category).then((res) => {
          this.$message({
            type: 'success',
            message: '分类名修改成功: ' + value
          })
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消输入'
        })
      })
    },
    addCategory() {
      this.$prompt('请输入新的分类名称', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        // inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
        inputErrorMessage: '邮箱格式不正确'
      }).then(({ value }) => {
        var category = { id: null, userId: this.global.user.id, name: value }
        this.$axios.post('category/add/', category).then((res) => {
          category.id = res.data.data.id
          category.count = 0
          this.categoryParamList.push(category)
          this.$message({
            type: 'success',
            message: '添加分类成功: ' + value
          })
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消输入'
        })
      })
    }

  }
}
</script>
