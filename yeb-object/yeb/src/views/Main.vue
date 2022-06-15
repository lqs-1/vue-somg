<template>
  <div>
    <el-container>
      <el-header>
        <div class="header">
          <div @click="toIndex">管理系统</div>
          <div class="user_info">
            <!--            <img :src="user.topic" class="user_topic">-->
              <el-dropdown v-if="user != null" @command="userCommandHandler">
                 <span class="el-dropdown-link">
                    {{ user.username }}<i class="el-icon-arrow-down el-icon--right"></i>
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item command="cleanSession">注销登录</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            <el-button type="primary" round v-else @click="toLogin">去登录</el-button>


          </div>
        </div>
      </el-header>

      <el-container>
        <el-aside width="200px">
          <el-menu @select="userItemHandler">
            <el-submenu index="1">
              <template slot="title"><i class="el-icon-location"></i>用户管理</template>
              <el-menu-item index="/index">系统首页</el-menu-item>
              <el-menu-item index="/userList">用户列表</el-menu-item>
            </el-submenu>
          </el-menu>
        </el-aside>
        <el-main>
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>

export default {
  name: "Main",
  data() {
    return {
      user: null
    }
  },

  created() {
    // this.$router.replace("/userList")
    this.checkLogin()
    // console.log(this.user)
    // console.log("hah")
  },

  methods: {
    userItemHandler(index) {
      this.$router.push(index)
    },

    checkLogin() {
      this.httpRequest.get("/user/session").then(response => {
        // console.log(this)
        if (response.data.code < 20000 ){
          this.$store.commit('STORAGE_USER', response.data.user)
          this.user = response.data.user

        }else{
          this.user = null
        }


        // console.log(response.data)
        // console.log(response)
        // console.log(this.user)

      }).catch(error => {
      })
    },

    toLogin(){
      this.$router.replace("/")
    },


    userCommandHandler(command){
      if (command == 'cleanSession'){
        this.httpRequest.get("/user/cleanSession").then(response => {
          // console.log(this)
          this.user = null
          this.$store.commit('CLEAN_USER', null)
          this.$router.replace("/")

        }).catch(error => {
        })
      }
    },


    toIndex(){
      this.$router.push("/index")
    }

  }


}
</script>

<style scoped>
.header {
  color: black;
  width: 100%;
  height: 100%;
  font-size: 20px;
  display: flex;
  align-items: center;
  background-color: #90d7ec;
}

.user_info {
  position: absolute;
  right: 50px;
}
</style>