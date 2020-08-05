<template>
  <div>
    <el-container style="height: 100%; border: 1px solid #eee">
      <el-aside width="15%" style="background-color: rgb(238, 241, 246)">
        <el-header style="text-align: right; font-size: 18px">
          <el-dropdown>
            <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
            <span>our-task</span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>添加分类</el-dropdown-item>
              <el-dropdown-item>新增</el-dropdown-item>
              <el-dropdown-item>删除</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-header>

        <el-menu default-active="1">
          <!-- 这里定死了显示的东西，后期修改 -->
          <el-menu-item index="1" @click="getUserCategoryTaskList(1, '今天')">
            <i class="el-icon-menu"></i>
            <span slot="title">今天</span>
          </el-menu-item>
          
          <el-menu-item index="2" @click="getUserCategoryTaskList(2, '明天')">
            <i class="el-icon-menu"></i>
            <span slot="title">明天</span>
          </el-menu-item>

          <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="清单" name="first">
              <el-menu-item
                v-for="itemCategory in categoryList"
                :index="itemCategory.id.toString()"
                :key="itemCategory.id.toString()"
                @click="getUserCategoryTaskList(itemCategory.id, itemCategory.name)"
              >
                <i class="el-icon-tickets"></i>
                {{itemCategory.name}}
              </el-menu-item>
              <el-menu-item>
                <i class="el-icon-plus"></i>
                <el-button type="text" @click="open">添加分类</el-button>
              </el-menu-item>
            </el-tab-pane>
            <el-tab-pane label="标签" name="second">标签</el-tab-pane>
          </el-tabs>
        </el-menu>
      </el-aside>

      <el-main>
        <el-form :inline="true" :model="newTask">
          <el-form-item label="清单名称">
            <el-input
              maxlength="10"
              show-word-limit
              placeholder="请输入清单名称"
              suffix-icon="el-icon-date"
              v-model="newTask.name"
              v-on:keyup.enter.native="addTask()"
            ></el-input>
          </el-form-item>
          <el-form-item label="清单描述">
            <el-input
              maxlength="30"
              show-word-limit
              placeholder="请输入清单描述"
              prefix-icon="el-icon-search"
              v-model="newTask.description"
              v-on:keyup.enter.native="addTask()"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="addTask()">添加清单</el-button>
          </el-form-item>
        </el-form>

        <el-table :data="taskList" stripe style="width: 100%">
          <el-table-column prop="name" width="200" label="清单名称" align="center"></el-table-column>
          <el-table-column prop="description" label="清单描述" align="center"></el-table-column>
          <el-table-column width="100">
            <template slot-scope="scope">
              <el-button @click="deleteTaskById(scope.row.id)" type="text" size="small">标记完成</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-main>
      <!-- </el-container> -->
    </el-container>
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
</style>

<script>
export default {
  data() {
    const item = {
      date: "2016-05-02",
      name: "王小虎",
      address: "南京市",
    };
    return {
      user: {
        id: 1,
        username: "user",
      },
      taskList: [],
      categoryList: [],
      currentCategory: {
        id: 1,
        userId: 1,
        name: "今天",
        createTime: null,
        updateTime: null,
      },
      currentTask: {
        id: null,
        name: "",
        description: "",
      },
      activeName: "first",
      newTask: {
        userId: null,
        categoryId: null,
        name: "",
        description: "",
      },
    };
  },
  methods: {
    addTask() {
      const _this = this;
      this.newTask.userId = this.user.id;
      this.newTask.categoryId = this.currentCategory.id;

      _this.$axios.post("task/save", this.newTask).then((res) => {
        this.taskList.push(res.data.data);
        this.newTask.name = "";
        this.newTask.description = "";
      });
    },
    handleClick(tab, event) {
      console.log(tab, event);
    },

    deleteTaskById(taskId) {
      const _this = this;
      _this.$axios.get(`task/delete/${taskId}`).then((res) => {
        this.taskList.some((item, i) => {
          if (item.id === taskId) {
            this.taskList.splice(i, 1);
            return true;
          }
        });
      });
    },
    getUserCategoryList() {
      const _this = this;
      _this.$axios.get(`category/listAll/${this.user.id}`).then((res) => {
        this.categoryList = res.data.data;
      });
    },
    getUserCategoryTaskList(currentCategoryId, currentCategoryName) {
      this.currentCategory.id = currentCategoryId;
      this.currentCategory.name = currentCategoryName;
      const _this = this;
      _this.$axios
        .get(`task/getAllList/${this.user.id}/${currentCategoryId}`)
        .then((res) => {
          this.taskList = res.data.data;
        });
    },
    addCategory(name) {
      const _this = this;
      var newCategory = {};
      newCategory.name = name;
      newCategory.userId = this.user.id;
      _this.$axios
        .post(`category/add/${this.user.id}`, newCategory)
        .then((res) => {
          _this.categoryList.push(res.data.data);
        });
    },
    open() {
      this.$prompt("请输入分类名称", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        inputPattern: /^\S+$/,
        // inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
        inputErrorMessage: "分类名称不能为空",
      })
        .then(({ value }) => {
          this.addCategory(value);
          this.$message({
            type: "success",
            message: "分类添加成功: " + value,
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "取消输入",
          });
        });
    },
  },
  created() {
    this.getUserCategoryList();
    this.getUserCategoryTaskList(1, "今天");
  },
};
</script>