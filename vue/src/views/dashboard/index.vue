<template>
  <div>
    <el-card>
      <div slot="header" class="clearfix">
        <span>使用统计</span>
      </div>
      <div>
        <el-row>
          <el-col :span="6">{{ statistics.unFinished }}</el-col>
          <el-col :span="6">{{ statistics.weekFinished }}</el-col>
          <el-col :span="6">{{ statistics.monthFinished }}</el-col>
          <el-col :span="6">{{ statistics.totalFinished }}</el-col>
        </el-row>
        <el-row>
          <el-col :span="6">待完成清单</el-col>
          <el-col :span="6">上周完成清单</el-col>
          <el-col :span="6">上个月完成清单</el-col>
          <el-col :span="6">已完成清单</el-col>
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
    <el-row>
      <el-col :span="24">
        <div>
          <ve-waterfall :data="today" :settings="chartSettings" />
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
        dayNeed: '每日剩余数量',
        week: '每周完成数量',
        count: '               当日分类统计'
      }
    }
    return {
      dayData: {
        columns: ['date', 'day', 'dayNeed'],
        rows: null
      },
      weekData: {
        columns: ['date', 'week'],
        rows: null
      },
      today: {
        columns: ['categoryName', 'count'],
        rows: null
      },
      statistics: {
        totalFinished: null,
        unFinished: null,
        weekFinished: null,
        monthFinished: null
      }
    }
  },
  created() {
    this.getDayData()
    this.getWeekData()
    this.getStatistics()
    this.getToday()
  },
  methods: {
    getDayData() {
      this.$axios.get(`task/countTaskForDay/${this.global.user.id}`, {
        headers: {
          'Authorization': localStorage.getItem('token')
        }
      }).then((res) => {
        this.dayData.rows = res.data.data
      })
    },
    getWeekData() {
      this.$axios.get(`task/countTaskForWeek/${this.global.user.id}`, {
        headers: {
          'Authorization': localStorage.getItem('token')
        }
      }).then((res) => {
        this.weekData.rows = res.data.data
      })
    },
    getStatistics() {
      this.$axios.get(`task/getStatistics/${this.global.user.id}`, {
        headers: {
          'Authorization': localStorage.getItem('token')
        }
      }).then((res) => {
        this.statistics = res.data.data
      })
    },
    getToday() {
      this.$axios.get(`task/countTodayForCategory/${this.global.user.id}`, {
        headers: {
          'Authorization': localStorage.getItem('token')
        }
      }).then((res) => {
        this.today.rows = res.data.data
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
