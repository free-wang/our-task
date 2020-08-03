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

        <el-menu :default-openeds="['1', '3']">
          <el-menu-item index="1">
            <i class="el-icon-menu"></i>
            <span slot="title">今天</span>
          </el-menu-item>
          <el-menu-item index="2">
            <i class="el-icon-menu"></i>
            <span slot="title">明天</span>
          </el-menu-item>
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
        </el-menu>
      </el-aside>

      <!-- <el-container> -->
      <!-- <el-header style="text-align: right; font-size: 18px">
          <el-dropdown>
            <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
            <span>our-task</span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>添加分类</el-dropdown-item>
              <el-dropdown-item>新增</el-dropdown-item>
              <el-dropdown-item>删除</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
      </el-header>-->

      <el-main>
        <el-header style="text-align: right; font-size: 18px width: 100%">
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

        <el-table :data="taskList" style="width: 100%">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="left" inline class="demo-table-expand">
                <el-form-item label="清单名称">
                  <!-- <el-input
                      type="text"
                      placeholder="请输入内容"
                      maxlength="10"
                      show-word-limit
                      value={{props.row.name}}
                  ></el-input>-->
                  <span>{{ props.row.name }}</span>
                </el-form-item>
                <el-form-item label="所属分类">
                  <span>{{ currentCategory.name }}</span>
                </el-form-item>-->
                <el-form-item label="创建时间">
                  <span>{{ props.row.createTime }}</span>
                </el-form-item>-->
                <el-form-item label="修改时间">
                  <span>{{ props.row.updateTime }}</span>
                </el-form-item>-->
                <el-form-item label="清单描述">
                  <span>{{ props.row.description }}</span>
                </el-form-item>
                <el-form-item label="标记完成">
                  <el-button type="text" @click="deleteTaskById(props.row.id)">标记完成</el-button>
                </el-form-item>
              </el-form>
            </template>
          </el-table-column>
          <el-table-column label="清单名称" prop="name"></el-table-column>
          <el-table-column label="创建时间" prop="createTime"></el-table-column>
          <el-table-column label="清单描述" prop="description"></el-table-column>
        </el-table>
      </el-main>
      <!-- </el-container> -->

      <el-aside width="30%" style="background-color: rgb(238, 241, 246)">
        <el-menu :default-openeds="['1', '3']">
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-message"></i>展开分类
            </template>
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
          </el-submenu>
        </el-menu>
      </el-aside>
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
      newCategory: {
        id: 1,
        userId: 1,
        name: "",
        createTime: null,
        updateTime: null,
      },
    };
  },
  methods: {
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
    addCategory() {
      const _this = this;
      _this.newCategory.userId = this.user.id;
      _this.$axios
        .post(`category/add/${this.user.id}`, this.newCategory)
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
          this.newCategory.name = value;
          this.addCategory();
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