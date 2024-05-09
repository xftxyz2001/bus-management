<script setup>
import { Edit, Delete } from "@element-plus/icons-vue";
import { ref } from "vue";
import request from "@/utils/request";

import userApi from "@/api/userApi";
import { ElMessage } from "element-plus";

const Page = ref(1);
const limit = ref(10);
const total = ref(0);
const small = ref(false);
const background = ref(false);
const disabled = ref(false);
const genderData = ref([{ gender: 1 }, { gender: 0 }]);

const userDatas = ref([]);

function getData() {
  userApi.getAllusers().then(res => {
    userDatas.value = res.data;
  });
}
getData();

function handleDelete(row) {
  userApi.deleteUser(row.id).then(res => {
    ElMessage.success("删除成功");
    getData();
  });
}

function handleSizeChange(val) {
  this.Page = val;
}
function handleCurrentChange(val) {
  limit.value = val;
}

// 新增/编辑弹窗数据
const dialogVisible = ref(false);
const dialogTitle = ref("");

const editUser = ref({
  id: "",
  username: "",
  age: "",
  gender: "",
  phone: ""
});

function addUser() {
  request({
    url: "/user/add",
    method: "post",
    data: editUser.value
  }).then(res => {
    getData();
  });
}

function updateUser() {
  request({
    url: "/user/update",
    method: "put",
    data: editUser.value
  }).then(res => {
    getData();
  });
}

function showUpdateDialog(row) {
  dialogVisible.value = true;
  dialogTitle.value = "编辑用户信息";
  editUser.value = { ...row };
}

function showAddDialog() {
  dialogVisible.value = true;
  dialogTitle.value = "新增用户信息";
  editUser.value = {
    id: "",
    username: "",
    age: "",
    gender: "",
    phone: ""
  };
}

function addOrUpdateUser() {
  if (dialogTitle.value === "新增用户信息") {
    addUser();
  } else {
    updateUser();
  }
  dialogVisible.value = false;
}
</script>

<template>
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <span>用户信息</span>
        <div class="extra">
          <el-button type="primary" @click="showAddDialog">添加信息</el-button>
        </div>
      </div>
    </template>

    <!-- <el-table :data="userDatas.slice((page-1) * limit,page*limit)" style="width: 100%"> -->
    <el-table :data="userDatas" style="width: 100%">
      <el-table-column label="序号" width="100" type="index"></el-table-column>
      <el-table-column label="用户名" prop="username"></el-table-column>
      <el-table-column label="年龄" prop="age"></el-table-column>
      <el-table-column data="genderData" label="性别">
        <template #default="{ row }">
          {{ row.gender === 1 ? "男" : "女" }}
        </template>
      </el-table-column>
      <!-- <el-table-column label="密码" prop="password"></el-table-column> -->
      <!-- <el-table-column label="类型" prop="age"></el-table-column> -->
      <el-table-column label="电话" prop="phone"></el-table-column>

      <el-table-column label="操作" width="100">
        <template #default="{ row }">
          <el-button :icon="Edit" circle plain type="primary" @click="showUpdateDialog(row)"></el-button>
          <el-button :icon="Delete" circle plain type="danger" @click="handleDelete(row)"></el-button>
        </template>
      </el-table-column>
      <template #empty>
        <el-empty description="没有数据" />
      </template>
    </el-table>

    <el-pagination
      class="el-p"
      v-model:current-page="Page"
      v-model:page-size="limit"
      :page-sizes="[5, 10, 15, 20]"
      :small="small"
      :disabled="disabled"
      :background="background"
      layout="jumper,total, sizes, prev, pager, next"
      :total="total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </el-card>

  <!-- 新增/编辑弹窗 -->
  <el-dialog v-model="dialogVisible" :title="dialogTitle">
    <el-form :model="editUser" label-width="80px">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="editUser.username" />
      </el-form-item>
      <el-form-item label="年龄" prop="age">
        <el-input v-model="editUser.age" />
      </el-form-item>
      <el-form-item label="性别" prop="gender">
        <el-radio-group v-model="editUser.gender">
          <el-radio :label="1">男</el-radio>
          <el-radio :label="0">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="电话" prop="phone">
        <el-input v-model="editUser.phone" />
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="addOrUpdateUser">确 定</el-button>
    </div>
  </el-dialog>
</template>
<style lang="scss" scoped>
.el-p {
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
}
.page-container {
  min-height: 100%;
  box-sizing: border-box;

  .header {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
}
</style>
