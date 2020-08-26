<template>
  <div>
    <el-form ref="feedback" :model="feedback" :rules="rules" label-width="100px">
      <el-form-item label="标题" prop="name">
        <el-input v-model="feedback.name" />
      </el-form-item>
      <el-form-item label="反馈描述" prop="desc">
        <el-input v-model="feedback.desc" type="textarea" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('feedback')">立即创建</el-button>
        <el-button @click="resetForm('feedback')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
export default {
  data() {
    return {
      feedback: {
        name: '',
        desc: ''
      },
      rules: {
        name: [
          { required: true, message: '请输入标题', trigger: 'blur' }
        ],
        desc: [
          { required: true, message: '请填写反馈描述', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$message({
            message: '您的反馈我们已经收到，感谢您的支持',
            type: 'success'
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>
