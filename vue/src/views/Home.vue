<template>
  <el-container style="height: 500px; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      
      <el-menu :default-openeds="['1', '3']">
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-message"></i>展开分类
          </template>
          <el-menu-item
            v-for="itemCategory in categoryList"
            :index="itemCategory.id.toString()"
            :key="itemCategory.id.toString()"
            @click="getUserCategoryTaskList(1,itemCategory.id)"
          >{{itemCategory.name}}</el-menu-item>
          <el-menu-item>
            <el-button type="text" @click="open">添加分类</el-button>
          </el-menu-item>
          <!-- <el-menu-item-group v-for="category in categoryList" :key="category.id">
          <el-menu-item index="category.id">{{category.name}}</el-menu-item>
          </el-menu-item-group>-->
        </el-submenu>
      </el-menu>




      <el-menu :default-openeds="['1', '3']">
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-message"></i>展开分类
          </template>
          <el-menu-item
            v-for="itemCategory in categoryList"
            :index="itemCategory.id.toString()"
            :key="itemCategory.id.toString()"
            @click="getUserCategoryTaskList(1,itemCategory.id)"
          >{{itemCategory.name}}</el-menu-item>
          <el-menu-item>
            <el-button type="text" @click="open">添加分类</el-button>
          </el-menu-item>
          <!-- <el-menu-item-group v-for="category in categoryList" :key="category.id">
          <el-menu-item index="category.id">{{category.name}}</el-menu-item>
          </el-menu-item-group>-->
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="text-align: right; font-size: 18px">
        <el-dropdown>
          <!-- <i class="el-icon-setting" style="margin-right: 15px"></i> -->
          <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
          <span>our-task</span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>添加分类</el-dropdown-item>
            <el-dropdown-item>新增</el-dropdown-item>
            <el-dropdown-item>删除</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
        
      </el-header>

      <el-main>
        <el-table :data="taskList">
          <el-table-column prop="name" label="清单" width="140"></el-table-column>
          <el-table-column prop="description" label="描述" width="300"></el-table-column>
        </el-table>
      </el-main>
    </el-container>
  </el-container>
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
</style>

<script>
export default {
  data() {
    const item = {
      date: "2016-05-02",
      name: "王小虎",
      address: "上海市普陀区金沙江路 1518 弄",
    };
    return {
      taskList: [],
      categoryList: [],
      category: {
        id: -1,
        userId: 1,
        name: "",
        createTime: null,
        updateTime: null,
      },
    };
  },
  methods: {
    getUserCategoryList(userId) {
      const _this = this;
      _this.$axios.get(`category/listAll/${userId}`).then((res) => {
        this.categoryList = res.data.data;
      });
    },
    getUserCategoryTaskList(userId, categoryId) {
      const _this = this;
      _this.$axios
        .get(`task/getAllList/${userId}/${categoryId}`)
        .then((res) => {
          this.taskList = res.data.data;
        });
    },
    addCategory(userId, category) {
      const _this = this;
      _this.category.userId = userId;
      _this.$axios.post(`category/add/${userId}`, category).then((res) => {
        console.log(_this.category);
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
          this.category.name = value;
          this.addCategory(1, this.category);
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
    this.getUserCategoryList(1);
    this.getUserCategoryTaskList(1, 1);
  },
};
</script>