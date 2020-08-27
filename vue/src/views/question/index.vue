<template>
  <el-table
    :data="tableData"
    border
    style="width: 100%"
  >
    <el-table-column
      align="center"
      prop="question"
      label="问题"
      width="180"
    />
    <el-table-column
      align="center"
      prop="description"
      label="描述"
    />
  </el-table>
</template>

<script>
export default {
  data() {
    return {
      tableData: [
        {
          question: '功能',
          description: 'Element的卡片，对我后面有帮助'
        }
      ]
    }
  }
}
</script>
