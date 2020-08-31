<template>
  <div>
    <el-card>
      <div slot="header" class="clearfix">
        <span>使用统计</span>
      </div>
      <div>
        <el-row>
          <el-col :span="6">haha</el-col>
          <el-col :span="6">haha</el-col>
          <el-col :span="6">haha</el-col>
          <el-col :span="6">haha</el-col>
        </el-row>
        <el-row>
          <el-col :span="6">已完成清单</el-col>
          <el-col :span="6">待完成清单</el-col>
          <el-col :span="6">一周内新建清单</el-col>
          <el-col :span="6">一周内完成清单</el-col>
        </el-row>
      </div>
    </el-card>
    <el-divider />
    <el-row>
      <el-col :span="12">
        <div>
          <ve-line :data="dayData" :settings="chartSettings" />
        </div>
      </el-col>
      <el-col :span="12">
        <div>
          <ve-line :data="weekData" :settings="chartSettings" />
        </div>
      </el-col>
    </el-row>
  </div>

</template>

<script>
export default {
  data() {
    this.chartSettings = {
      labelMap: {
        day: '每日完成数量',
        week: '每周完成数量'
      }
    }
    return {
      dayData: {
        columns: ['date', 'day'],
        rows: null
      },
      weekData: {
        columns: ['date', 'week'],
        rows: null
      },
      statistics: {
        totalFinished: null,
        unFinished: null,
        weekFinished: null,
        weekUnFinished: null
      }
    }
  },
  created() {
    this.getDayData()
    this.getWeekData()
  },
  methods: {
    getDayData() {
      this.$axios.get(`task/countTaskForDay/${this.global.user.id}`).then((res) => {
        this.dayData.rows = res.data.data
      })
    },
    getWeekData() {
      this.$axios.get(`task/countTaskForWeek/${this.global.user.id}`).then((res) => {
        this.weekData.rows = res.data.data
      })
    }
  }
}
</script>

<style>
  .el-col {
    border-radius: 4px;
    text-align:center
  }
</style>
