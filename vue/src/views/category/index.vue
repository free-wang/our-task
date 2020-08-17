<template>
  <div>
    <el-container height="100%">
      <el-main>
        <el-table :data="categoryParamList" stripe style="width: 100%" border>
          <el-table-column prop="name" label="分类名称" align="center" />
          <el-table-column prop="count" label="所含清单总数" align="center" />
          <el-table-column width="300" label="操作" align="center">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="changeTaskById(scope.row)">编辑修改</el-button>
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
          <el-button type="text">添加清单</el-button>
        </div></el-col>
      </el-row>
    </el-footer>
  </div>
</template>

<style>
.el-header {
  background-color: #b3c0d1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}

.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
.el-container{
  height: 100%;
}
</style>

<script>
export default {
  data() {
    return {
      currentPage: 1,
      selectLabel: '',
      labelList: [],
      selectCategory: '',
      searchName: '',
      user: {
        id: 1,
        username: 'user'
      },
      taskList: [],
      categoryParamList: [],
      currentCategory: {
        id: 1,
        userId: 1,
        name: '今天',
        createTime: null,
        updateTime: null
      },
      currentTask: {
        id: null,
        name: '',
        description: ''
      },
      activeName: 'first',
      newTask: {
        userId: null,
        categoryId: null,
        name: '',
        description: ''
      }
    }
  },
  created() {
    this.getTaskListByUserId()
    this.getUserCategoryParamList()
  },
  methods: {
    getTaskListByUserId() {
      this.$axios.get(`task/getAllList/${this.user.id}`).then((res) => {
        this.taskList = res.data.data
      })
    },
    getUserCategoryParamList() {
      this.$axios.get(`category/listAll/${this.user.id}`).then((res) => {
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
    }

  }
}
</script>
