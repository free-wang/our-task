<template>
  <div>
    <el-container height="100%">
      <el-main>
        <el-table :data="labelParamList" stripe style="width: 100%" border>
          <el-table-column prop="name" label="标签名称" align="center" />
          <el-table-column prop="count" label="所含清单总数" align="center" />
          <el-table-column width="300" label="操作" align="center">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="changeLabel(scope.row)">编辑修改</el-button>
              <el-divider direction="vertical" />
              <el-button type="text" size="small" @click="deleteLabelById(scope.row.id)">删除标签</el-button>
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
          <el-button type="text" @click="addLabel()">添加标签</el-button>
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
      labelParamList: []
    }
  },
  created() {
    this.getLabelParamList()
  },
  methods: {
    getLabelParamList() {
      this.$axios.get(`label/listAll/${this.global.user.id}`).then((res) => {
        this.labelParamList = res.data.data
      })
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`)
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`)
    },
    deleteLabelById(labelId) {
      this.$axios.get(`label/delete/${labelId}`).then((res) => {
        this.labelParamList.some((item, i) => {
          if (item.id === labelId) {
            this.labelParamList.splice(i, 1)
            return true
          }
        })
      })
    },
    changeLabel(label) {
      this.$prompt('请输入新的标签名称', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        // inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
        inputErrorMessage: '邮箱格式不正确'
      }).then(({ value }) => {
        label.name = value
        this.$axios.post(`category/update/${label.id}`, label).then((res) => {
          this.$message({
            type: 'success',
            message: '标签名修改成功: ' + value
          })
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消输入'
        })
      })
    },
    addLabel() {
      this.$prompt('请输入新的标签名称', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        // inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
        inputErrorMessage: '邮箱格式不正确'
      }).then(({ value }) => {
        var label = { id: null, userId: this.global.user.id, name: value }
        this.$axios.post('label/add/', label).then((res) => {
          label.id = res.data.data.id
          label.count = 0
          this.labelParamList.push(label)
          this.$message({
            type: 'success',
            message: '添加标签成功: ' + value
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
