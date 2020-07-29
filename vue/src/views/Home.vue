<template>
  <el-container style="height: 500px; border: 1px solid #eee">
  <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
    <el-menu :default-openeds="['1', '3']">
      <el-submenu index="1">
        <template slot="title"><i class="el-icon-message"></i>展开分类</template>
        <el-menu-item v-for="category in categoryList" :index="category.id.toString()" :key="category.id">
          {{category.name}}
        </el-menu-item>
        <!-- <el-menu-item-group v-for="category in categoryList" :key="category.id">
          <el-menu-item index="category.id">{{category.name}}</el-menu-item>
        </el-menu-item-group> -->
      </el-submenu>
    </el-menu>
  </el-aside>
  
  <el-container>
    <el-header style="text-align: right; font-size: 12px">
      <el-dropdown>
        <i class="el-icon-setting" style="margin-right: 15px"></i>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>清单</el-dropdown-item>
          <el-dropdown-item>新增</el-dropdown-item>
          <el-dropdown-item>删除</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <span>our-task</span>
    </el-header>
    
    <el-main>
      <el-table :data="taskList">
        <el-table-column prop="name" label="清单" width="140">
        </el-table-column>
        <el-table-column prop="description" label="描述" width="300">
        </el-table-column>
      </el-table>
    </el-main>
  </el-container>
</el-container>
</template>

<style>
  .el-header {
    background-color: #B3C0D1;
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
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      };
      return {
        taskList: [], 
        categoryList:[], 
        taskList:[]
      }
    }, 
    methods:{
      getUserCategoryList(userId){
        const _this = this
        _this.$axios.get(`category/listAll/${userId}`).then(res => {
          this.categoryList = res.data.data
        })
      }, 
      getUserCategoryTaskList(userId, categoryId){
        const _this = this
        _this.$axios.get(`task/getAllList/${userId}/${categoryId}`).then(res => {
          console.log(res)
          this.taskList = res.data.data
        })
      }
    },
    created(){
      this.getUserCategoryList(1)
      this.getUserCategoryTaskList(1,1)
    }
  };
</script>