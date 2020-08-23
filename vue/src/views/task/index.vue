<template>
  <div>
    <el-container>
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
                  v-for="item in categoryParamList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                />
              </el-select>
            </div>
          </el-col>
          <el-col :span="8" align="center">
            <div>
              标签分类:
              <el-select v-model="selectLabel" multiple placeholder="请选择">
                <el-option
                  v-for="label in labelParamList"
                  :key="label.id"
                  :label="label.name"
                  :value="label.id"
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
        <el-table :data="taskData.records" stripe style="width: 100%" border>
          <el-table-column prop="name" width="200" label="清单名称" align="center" />
          <el-table-column prop="description" label="清单描述" align="center" />
          <el-table-column width="200" label="操作" align="center">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="changeTaskById(scope.row)">编辑修改</el-button>
              <el-divider direction="vertical" />
              <el-button type="text" size="small" @click="deleteTaskById(scope.row.id)">标记完成</el-button>
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
            :current-page="taskData.current"
            :page-sizes="[5, 10, 15, 20]"
            :page-size="taskData.size"
            layout="total, sizes, prev, pager, next, jumper"
            :total="taskData.total"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
          />
        </div></el-col>
        <el-col :span="8"><div>
          <el-button type="text" @click="addTask()">添加清单</el-button>
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
</style>

<script>
export default {
  data() {
    return {
      currentPage: 1,
      selectLabel: '',
      selectCategory: '',
      searchName: '',
      labelParamList: [],
      categoryParamList: [],
      taskData: {
        records: [],
        total: null,
        size: null,
        current: null,
        orders: [],
        searchCount: null,
        pages: null
      }
    }
  },
  created() {
    this.getTaskDataByUserId(1, 5)
    this.getUserCategoryParamList()
    this.getLabelParamList()
  },
  methods: {
    getTaskDataByUserId(currentPage, pageSize) {
      this.$axios.get(`task/getPageList/${this.global.user.id}/${currentPage}/${pageSize}`).then((res) => {
        this.taskData = res.data.data
      })
    },
    getUserCategoryParamList() {
      this.$axios.get(`category/listAll/${this.global.user.id}`).then((res) => {
        this.categoryParamList = res.data.data
      })
    },
    getLabelParamList() {
      this.$axios.get(`label/listAll/${this.global.user.id}`).then((res) => {
        this.labelParamList = res.data.data
      })
    },
    handleSizeChange(val) {
      this.getTaskDataByUserId(this.taskData.current, val)
    },
    handleCurrentChange(val) {
      this.getTaskDataByUserId(val, this.taskData.size)
    },
    deleteTaskById(taskId) {
      this.$axios.get(`task/delete/${taskId}`).then((res) => {
        this.taskData.records.some((item, i) => {
          if (item.id === taskId) {
            this.taskData.records.splice(i, 1)
            return true
          }
        })
      })
    },
    changeTaskById(taskParam) {
      this.$router.push({ path: '/add/addTask',
        query: {
          taskParam: taskParam
        }})
    },
    addTask() {
      this.$router.push({ path: '/add/addTask' })
    }

  }
}
</script>
