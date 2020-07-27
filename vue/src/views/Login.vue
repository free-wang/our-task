<template>
  <div>
    <el-container>
      <el-header>
        <img class="logo" src="../../public/logo.png" alt />
      </el-header>

      <el-main>
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item prop="username">
            <el-input
              placeholder="手机号或邮箱"
              prefix-icon="el-icon-s-custom"
              v-model="ruleForm.username"
            ></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input
              type="password"
              placeholder="密码"
              prefix-icon="el-icon-lock"
              v-model="ruleForm.password"
            ></el-input>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
          </el-form-item>
        </el-form>
        <div>
          <div>
            <span>
              <el-link type="primary">忘记密码</el-link>
            </span>
            <el-divider direction="vertical"></el-divider>

            <span>
              <el-link type="primary">注册</el-link>
            </span>
          </div>

          <div>
            <el-button type="success">微信</el-button>
          </div>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      ruleForm: {
        username: "user",
        password: "123456",
      },
      rules: {
        username: [
          { required: true, message: "请输入手机号或邮箱", trigger: "blur" },
          { min: 3, max: 5, message: "长度在 3 到 5 个字符", trigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "change" },
        ],
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const _this = this;
          this.$axios.post("/login", this.ruleForm).then((res) => {
            console.log(res.data);
            // const jwt = res.headers["authorization"];
            // const userInfo = res.data.data;

            // // 把数据共享出去
            // _this.$store.commit("SET_TOKEN", jwt);
            // _this.$store.commit("SET_USERINFO", userInfo);

            // // 获取
            // console.log(_this.$store.getters.getUser);

            // _this.$router.push("/blogs");
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
  },
};
</script>

<style scoped>
.el-header,
.el-footer {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.logo {
  height: 60%;
  margin-top: 10px;
}
</style>