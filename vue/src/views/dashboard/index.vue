<template>
  <div>
    <el-row>
      <el-col :span="12">
        <div>
          <ve-line :data="dayData" />
        </div>
      </el-col>
      <el-col :span="12">
        <div>
          <ve-line :data="weekData" />
        </div>
      </el-col>
    </el-row>
  </div>

</template>

<script>
export default {
  data: function() {
    return {
      dayData: {
        columns: ['日期', '清单完成数'],
        rows: null
      },
      weekData: {
        columns: ['日期', '清单完成数'],
        rows: null
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

