<template>
  <div>
    <el-container>
      <el-header>Header</el-header>
      <el-container>
        <el-aside width="200px">
          <el-menu>
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-location"></i>导航一</template>
            <el-menu-item-group>
              <el-menu-item index="路径">选项1</el-menu-item>
              <el-menu-item index="1-2">选项2</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
        </el-aside>
        <el-main>Main</el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "Main",

  created() {
    this.httpRequest.get("/user/index").then(response => {
      console.log(response)
    }).catch(error => {
      console.log(error)
    })
  }


}
</script>

<style scoped>

</style>