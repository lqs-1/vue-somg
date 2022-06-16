<template>
  <div class="user_list">
    <el-button type="primary" icon="el-icon-circle-plus" circle class="addUser" @click="showUserAddForm"></el-button>

    <el-dialog
        title="用户添加"
        :visible="userAddVisible"
        width="30%"
        :before-close="addHandleClose">

      <span>
          <el-form ref="addForm" :model="userForm" label-width="80px" :rules="rules">
            <el-form-item label="用户名" prop="username">
              <el-input type="text" v-model="userForm.username"></el-input>
            </el-form-item>

            <el-form-item label="密码" prop="password">
              <el-input type="password" v-model="userForm.password"></el-input>
            </el-form-item>

            <el-form-item label="性别" prop="sex">
              <el-select v-model="userForm.sex" placeholder="请选择">
                <el-option label="男" value="男"></el-option>
                <el-option label="女" value="女"></el-option>
              </el-select>
            </el-form-item>
          </el-form>
      </span>

      <span slot="footer" class="dialog-footer">
    <el-button @click="userAddVisible = false">取 消</el-button>
    <el-button type="primary" @click="addUser">添加</el-button>
  </span>
    </el-dialog>


    <el-button type="success" class="queryButton" @click="queryByName">用户查询</el-button>
    <el-input v-model="username" placeholder="请输入用户名" class="queryUser"></el-input>
    <el-divider></el-divider>
    <el-table
        :data="userList"
        style="width: 100%">
      <el-table-column
          label="用户编号"
          width="250"
          align="center">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column
          label="姓名"
          width="300"
          align="center">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.username }}</span>
        </template>
      </el-table-column>
      <el-table-column
          label="密码"
          width="300"
          align="center">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.password }}</span>
        </template>
      </el-table-column>
      <el-table-column
          label="性别"
          width="300"
          align="center">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium">{{ scope.row.sex }}</el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column
          label="操作"
          width="500"
          align="center">
        <template slot-scope="scope">
          <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)">编辑
          </el-button>
          <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--  用户修改-->
    <el-dialog
        title="用户修改"
        :visible="userEditVisible"
        width="30%"
        :before-close="editHandleClose">

      <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
        <el-tab-pane label="基本信息" name="first">
                <span>
          <el-form ref="editForm" :model="userForm" label-width="80px" :rules="rules">
            <el-form-item label="用户id" prop="id">
              <el-input v-model="userForm.id" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="用户名" prop="username">
              <el-input type="text" v-model="userForm.username"></el-input>
            </el-form-item>

            <el-form-item label="密码" prop="password">
<!--              <el-input type="password" v-model="userForm.password"></el-input>-->
              <el-input v-model="userForm.password" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item label="性别" prop="sex">
              <el-select v-model="userForm.sex" placeholder="请选择">
                <el-option label="男" value="男"></el-option>
                <el-option label="女" value="女"></el-option>
              </el-select>
            </el-form-item>
          </el-form>
      </span>

        </el-tab-pane>
        <el-tab-pane label="角色管理" name="second">
          <el-table
              ref="multipleTable"
              :data="roleList"
              tooltip-effect="dark"
              style="width: 100%"
              @selection-change="handleSelectionChange">
            <el-table-column
                type="selection"
                width="55">
            </el-table-column>
            <el-table-column
                label="角色id"
                width="120">
              <template slot-scope="scope">{{ scope.row.id }}</template>
            </el-table-column>
            <el-table-column
                prop="roleName"
                label="角色名称"
                width="120">
            </el-table-column>
          </el-table>
          <div style="margin-top: 20px">
            <el-button @click="toggleSelection()">取消选择</el-button>
          </div>
        </el-tab-pane>
      </el-tabs>



      <span slot="footer" class="dialog-footer">
    <el-button @click="userEditVisible = false">取 消</el-button>
    <el-button type="primary" @click="editUser">修改</el-button>
  </span>
    </el-dialog>

    <el-pagination
        @current-change="changeCurrentPageHandler"
        :page-size="pagination.pageSize"
        :page-count="pagination.totalPage"
        layout="prev, pager, next"
        :total="pagination.total">
    </el-pagination>
  </div>
</template>

<script>
export default {
  name: "UserList",
  data() {
    return {
      userList: [],
      userAddVisible: false,
      userEditVisible: false,
      username: "",
      userForm: {
        id: "",
        username: "",
        password: "",
        sex: ""
      },
      rules: {
        username: [{required: true, message: '请输入用户名', trigger: "blur"}],
        password: [{required: true, message: '请输入密码', trigger: "blur"}],
        sex: [{required: true, message: '请选择性别', trigger: "blur"}]
      },
      pagination: {
        // 每页多找个
        pageSize: 10,
        // 总页数
        totalPage: 20,
        // 获取页码
        activePage: 4,
        // 总条数
        total: 20,
        // 当前页
        currentPage: 1,
      },
      activeName: 'first',
      multipleSelection: [],
      roleList: []
    }
  },

  created() {
    window.document.title = "userList"
    // this.getUserList()
    this.changeCurrentPageHandler(this.pagination.currentPage)
  },

  methods: {
    // getUserList(){
    //   this.httpRequest.get("user/userList")
    //       .then(response => {
    //         // console.log(response.data)
    //         this.userList = response.data.userList
    //       })
    // },

    changeCurrentPageHandler(currentPage) {
      this.httpRequest.get("user/userPage?page=" + currentPage +
          "&limit=" + this.pagination.pageSize +
          "&username=" + this.username +
          "&orderFiled=id" +
          "&orderType=1")
          .then(response => {
            this.pagination.currentPage = response.data.userList.currentPage
            this.pagination.pageSize = response.data.userList.pageSize
            this.pagination.total = response.data.userList.totalSize
            this.pagination.totalPage = response.data.userList.totalPage
            this.userList = response.data.userList.resultList
          })
    },

    queryByName() {
      this.changeCurrentPageHandler(1)
    },

    addHandleClose() {
      this.userAddVisible = false
    },

    showUserAddForm() {
      this.userForm = {}
      this.userAddVisible = true
    },


    addUser() {
      this.$refs.addForm.validate((valid) => {
        if (valid) {
          // console.log(this.userForm)
          this.httpRequest.post("user/addUser", this.userForm).then(response => {
            this.changeCurrentPageHandler(1)
            this.userForm = {}
            this.userAddVisible = false
          })
        } else {
          message.error("请完善数据")
        }
      })
    },


    handleEdit(index, data) {
      console.log(data)
      this.userForm = data
      this.userEditVisible = true

    },

    editHandleClose() {
      this.userEditVisible = false
    },

    editUser() {
      this.$refs.editForm.validate((valid) => {
        if (valid) {
          // console.log(this.userForm)
          this.httpRequest.post("user/editUser", this.userForm).then(response => {
            this.changeCurrentPageHandler(1)
            this.userForm = {}
            this.userEditVisible = false
          })
        } else {
          message.error("请完善数据")
        }
      })
    },


    handleDelete(index, data) {
      this.httpRequest.post("user/deleteUser", {"id": data.id}).then(response => {
        this.changeCurrentPageHandler(1)
      })
    },

    handleClick(tab, event) {
      if (tab.index == 1) {
        this.httpRequest.get("role/roleList")
            .then(response => {
              console.log(response)
              this.roleList = response.data.roleList

            })
      }
    },

    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val;
      console.log(this.multipleSelection)
    },

    toggleSelection(rows) {
      console.log(rows)
      if (rows) {
        rows.forEach(row => {
          this.$refs.multipleTable.toggleRowSelection(row);
        });
      } else {
        this.$refs.multipleTable.clearSelection();
      }
    },

  }


}
</script>

<style scoped>
.addUser {
  margin-left: 20px;
}

.queryUser {
  width: 15%;
  position: absolute;
  right: 50px;
}

.queryButton {
  position: absolute;
  right: 400px;
}


</style>