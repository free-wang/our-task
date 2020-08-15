<template>
  <div>
    <el-container height="100%">
      <el-header>
        <el-row>
          <el-col :span="5" align="center">
            <div>
              <el-input
                v-model="searchName"
                placeholder="输入清单名称进行搜索"
                prefix-icon="el-icon-search"
              />
            </div>
          </el-col>
          <el-col :span="8" align="center">
            <div>
              清单分类:
              <el-select v-model="selectCategory" clearable placeholder="请选择">
                <el-option
                  v-for="item in categoryList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.name"
                />
              </el-select>
            </div>
          </el-col>
          <el-col :span="8" align="center">
            <div>
              标签分类:
              <el-select v-model="selectLabel" multiple placeholder="请选择">
                <el-option
                  v-for="label in labelList"
                  :key="label.id"
                  :label="label.name"
                  :value="label.name"
                />
              </el-select>
            </div>
          </el-col>
          <el-col :span="3" align="center">
            <el-row>
              <el-button>重置</el-button>
              <el-button type="primary">查询</el-button>
            </el-row>
          </el-col>
        </el-row>
      </el-header>
      <el-main>
        <el-table :data="taskList" stripe style="width: 100%" border>
          <el-table-column prop="name" width="200" label="清单名称" align="center" />
          <el-table-column prop="description" label="清单描述" align="center" />
          <el-table-column width="200" label="操作" align="center">

            <div>
              <el-button type="text" size="small" @click="deleteTaskById(scope.row.id)">编辑修改</el-button>
              <el-divider direction="vertical" />
              <el-button type="text" size="small" @click="deleteTaskById(scope.row.id)">标记完成</el-button>
            </div>
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
      taskList: [],
      categoryList: [],
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
    this.getUserCategoryList()
    this.getUserLabelList()
  },
  methods: {
    getTaskListByUserId() {
      this.$axios.get(`task/getAllList/${this.global.user.id}`).then((res) => {
        this.taskList = res.data.data
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
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`)
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`)
    }
    // deleteTaskById(taskId) {
    //   const _this = this
    //   _this.$axios.get(`task/delete/${taskId}`).then((res) => {
    //     this.taskList.some((item, i) => {
    //       if (item.id === taskId) {
    //         this.taskList.splice(i, 1)
    //         return true
    //       }
    //     })
    //   })
    // },

  }
}
</script>
