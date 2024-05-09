<template>
  <div>
    <el-row>
      <el-col :span="8">
        <el-input v-model="searchQuery" placeholder="输入姓名进行搜索"></el-input>
      </el-col>
      <el-col :span="4">
        <el-button @click="searchDrivers" type="primary">搜索</el-button>
      </el-col>
      <el-col :span="4">
        <el-button @click="resetSearch" type="info" style="margin: 0 20px 0 -150px">重置</el-button>
      </el-col>

      <el-col :span="8">
        <el-button @click="handleAdd" type="primary">添加司机</el-button>
      </el-col>
    </el-row>

    <el-table :data="drivers" style="width: 100%">
      <el-table-column prop="name" label="姓名"></el-table-column>
      <el-table-column prop="age" label="年龄"></el-table-column>
      <el-table-column prop="gender" label="性别"></el-table-column>
      <el-table-column prop="licensenumber" label="驾驶证号"></el-table-column>
      <el-table-column prop="phone" label="电话号码"></el-table-column>
      <el-table-column prop="worklocation" label="工作区间"></el-table-column>
      <el-table-column label="操作">
        <template #default="{ row }">
          <el-button size="mini" type="primary" @click="handleEdit(row)">修改</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>

  <!-- 新增/编辑弹窗 -->
  <el-dialog v-model="dialogVisible" :title="dialogTitle">
    <el-form :model="newDriver" label-width="80px">
      <el-form-item label="姓名" prop="name">
        <el-input v-model="newDriver.name" />
      </el-form-item>
      <el-form-item label="年龄" prop="age">
        <el-input v-model="newDriver.age" />
      </el-form-item>
      <el-form-item label="性别" prop="gender">
        <el-radio-group v-model="newDriver.gender">
          <el-radio label="男">男</el-radio>
          <el-radio label="女">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="驾驶证号" prop="licensenumber">
        <el-input v-model="newDriver.licensenumber" />
      </el-form-item>
      <el-form-item label="电话号码" prop="phone">
        <el-input v-model="newDriver.phone" />
      </el-form-item>
      <el-form-item label="工作区间" prop="worklocation">
        <el-input v-model="newDriver.worklocation" />
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogVisible = false">取消</el-button>
      <el-button type="primary" @click="addOrUpdateDriver">确定</el-button>
    </div>
  </el-dialog>
</template>

<script setup>
import driverApi from "@/api/driverApi";
import { ref } from "vue";

const searchQuery = ref("");
const drivers = ref([]);

// 获取司机数据
function fetchDrivers() {
  driverApi.getAllDrivers().then(res => {
    drivers.value = res.data;
  });
}
fetchDrivers();

// 搜索司机
function searchDrivers() {
  driverApi.getByDriverName(searchQuery.value).then(res => {
    drivers.value = res.data;
  });
}

// 重置搜索
function resetSearch() {
  searchQuery.value = "";
  fetchDrivers();
}

// 删除司机
function handleDelete(driver) {
  driverApi.deleteDriver(driver.driverid).then(() => {
    fetchDrivers();
  });
}

// 添加司机
function addDriver() {
  driverApi.addDriver(newDriver.value).then(() => {
    fetchDrivers();
  });
}

// 更新司机
function updateDriver() {
  driverApi.updateDriver(newDriver.value).then(() => {
    fetchDrivers();
  });
}

const dialogVisible = ref(false);
const dialogTitle = ref("");
const newDriver = ref({
  driverid: "",
  name: "",
  age: "",
  gender: "",
  licensenumber: "",
  phone: "",
  worklocation: ""
});

function handleAdd() {
  dialogVisible.value = true;
  dialogTitle.value = "添加司机";
  newDriver.value = {
    driverid: "",
    name: "",
    age: "",
    gender: "",
    licensenumber: "",
    phone: "",
    worklocation: ""
  };
}

function handleEdit(driver) {
  dialogVisible.value = true;
  dialogTitle.value = "编辑司机";
  newDriver.value = { ...driver };
}

// 添加或编辑司机
function addOrUpdateDriver() {
  if (dialogTitle.value === "添加司机") {
    addDriver();
  } else {
    updateDriver();
  }
  dialogVisible.value = false;
}
</script>

<style>
/* 表格样式 */
.el-table {
  margin-top: 20px;
}

/* 搜索和重置按钮样式 */
.el-row:nth-child(2) {
  margin-top: 20px;
}

/* 添加司机表单样式 */
.el-row:nth-child(3) {
  margin-top: 20px;
}

/* 添加司机按钮样式 */
.el-button--primary {
  margin-left: 10px;
}

/* 删除按钮样式 */
.el-button--danger {
  margin-right: 10px;
}
</style>
