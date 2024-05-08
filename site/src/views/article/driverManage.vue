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
    </el-row>

    <el-table :data="newDriver" style="width: 100%">
      <el-table-column prop="name" label="姓名"></el-table-column>
      <el-table-column prop="age" label="年龄"></el-table-column>
      <el-table-column prop="gender" label="性别"></el-table-column>
      <el-table-column prop="licenseNumber" label="驾驶证号"></el-table-column>
      <el-table-column prop="phone" label="电话号码"></el-table-column>
      <el-table-column prop="workLocation" label="工作区间"></el-table-column>
      <el-table-column label="操作">
        <template #default="{ row }">
          <el-button size="mini" type="danger" @click="handleDelete(row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-row style="margin-top: 20px">
      <el-col :span="8">
        <el-input v-model="newDriver.name" placeholder="姓名"></el-input>
      </el-col>
      <el-col :span="8">
        <el-input v-model="newDriver.age" placeholder="年龄"></el-input>
      </el-col>
      <el-col :span="8">
        <el-input v-model="newDriver.gender" placeholder="性别"></el-input>
      </el-col>
      <el-col :span="8">
        <el-input v-model="newDriver.licenseNumber" placeholder="驾驶证号"></el-input>
      </el-col>
      <el-col :span="8">
        <el-input v-model="newDriver.phone" placeholder="电话号码"></el-input>
      </el-col>
      <el-col :span="8">
        <el-input v-model="newDriver.worklocation" placeholder="工作区间"></el-input>
      </el-col>
      <el-col :span="8">
        <el-button @click="addDriver" type="primary">添加司机</el-button>
      </el-col>
    </el-row>
    <!-- <el-row style="margin-top: 20px;">
        <el-col :span="8">
          <el-input v-model="newDriver.name" placeholder="姓名"></el-input>
        </el-col>
    </el-row> -->
  </div>
</template>

<script>
import { reactive, ref, onMounted } from "vue";
import axios from "axios";

export default {
  //  setup() {
  //   const state = ref({
  //     drivers: [],
  //     searchQuery: '',
  //     newDriver: {
  //       name: '',
  //       age: '',
  //       gender: '',
  //       licenseNumber: '',
  //       phone:'',
  //       worklocation:''
  //     },
  //   });
  setup() {
    const newDriver = ref([]);

    // const newDriver= ref({
    //     name: '',
    //   age: '',
    //   gender: '',
    //   licenseNumber: '',
    //   phone:'',
    //   worklocation:''
    // });

    // 在组件加载时获取司机数据
    onMounted(async () => {
      await fetchDrivers();
    });

    axios.defaults.baseURL = "http://localhost:8080";
    // 获取司机数据
    const fetchDrivers = async () => {
      try {
        const response = await axios.get("/driver/getAllDrivers"); // 假设后端API路径为/api/drivers
        newDriver.value = response.data.data;
        console.log(newDriver);
      } catch (error) {
        console.error("Failed to fetch drivers:", error);
      }
    };

    // 搜索司机
    const searchDrivers = async () => {
      try {
        const response = await axios.get(`/driver/id?search=${state.searchQuery}`);
        state.drivers = response.data.data;
      } catch (error) {
        console.error("Failed to search drivers:", error);
      }
    };

    // 重置搜索
    const resetSearch = () => {
      state.searchQuery = "";
      fetchDrivers();
    };

    // 删除司机
    const handleDelete = async driver => {
      try {
        await axios.delete(`/driver/id/${driver.id}`);
        await fetchDrivers();
      } catch (error) {
        console.error("Failed to delete driver:", error);
      }
    };

    // 添加司机
    const addDriver = async () => {
      try {
        await axios.post("/driver/add", state.newDriver);
        state.newDriver = {
          name: "",
          age: "",
          gender: "",
          licenseNumber: "",
          phone: "",
          worklocation: ""
        };
        await fetchDrivers();
      } catch (error) {
        console.error("Failed to add driver:", error);
      }
    };

    return {
      // state,
      newDriver,
      searchDrivers,
      resetSearch,
      handleDelete,
      addDriver
    };
  }
};
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
