<template>
  <div class="main"
       v-loading="loading"
       element-loading-text="登录中"
       element-loading-spinner="el-icon-loading"
       element-loading-background="rgba(0, 0, 0, 0.8)">
    <el-form ref="form"
             :rules="rules"
             :model="loginForm"
             class="loginFormTable">
      <h3 class="loginTitle">系统登录</h3>
      <el-form-item prop="username">
        <el-input type="text" v-model="loginForm.username" auto-complete="false" placeholder="请输入用户名"/>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="loginForm.password" auto-complete="false" placeholder="请输入密码"/>
      </el-form-item>
      <el-form-item prop="code">
        <el-input type="text" v-model="loginForm.code" placeholder="点击图片更换验证码" style="width: 230px"/>
        <img :src="captchaUrl" @click="updateCaptcha">
      </el-form-item>
      <el-button type="primary" style="width: 100%" @click="loginHandler">登录</el-button>
      <hr>
      <el-button type="primary" style="width: 100%" @click="toRegister">去注册</el-button>
      <hr>
      <el-button type="primary" style="width: 100%" @click="toReset">找回密码</el-button>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "Login",
  data(){
    return{
      loading: false,
      loginForm:{
        username: "",
        password: "",
        code: ''
      },
      captchaUrl: '',

      rules:{
        username: [{required: true, message: '请输入用户名', trigger:"blur"}],
        password: [{required: true, message: '请输入密码', trigger:"blur"}],
        code: [{required: true, message: '请输入验证码', trigger:"blur"}]
      }
    }
  },

  created() {
    window.document.title = "login"
    this.updateCaptcha()
  },

  methods:{
    // 登录请求
    loginHandler(){
      this.$refs.form.validate((valid) => {
        if (valid){
          this.loading = true
          this.httpRequest.post("/user/login?username=" + this.loginForm.username + "&password=" + this.loginForm.password + "&code=" + this.loginForm.code)
              .then((response) => {
                // console.log(response)
                this.loading = false
                if (response.data.code > 20000 ){
                  this.$router.replace("/")
                }
                this.$router.replace("/index")

              }).catch((error) => {
                this.$router.replace("/");
              });
        }else{
          this.$router.replace("/")
        }
      })
    },


    // 这里是填充验证码链接的
    updateCaptcha(){
      this.captchaUrl = 'http://localhost:8081/user/captcha?time=' + new Date().getTime()
    },

    toRegister(){
      this.$router.replace("/register")
    },

    toReset(){
      this.$router.replace("/reset")
    }



  }

}
</script>

<style scoped>
  .main{
    width:100%;
    height:100%;
    position: fixed;
    background-size:100% 100%;
    background-repeat: space;
    background-image: url("../assets/background.jpeg");
  }

  .loginFormTable{
    border-radius: 15px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 15px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
  .loginTitle{
    margin: 0 auto 40px auto;
    text-align: center;
  }
  .remember{
    text-align: left;
    margin: 0 0 15px 0;
  }
</style>