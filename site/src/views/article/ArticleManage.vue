<!-- <script setup>
import axios from 'axios';
 const { createApp } = Vue;
        const app = createApp({
            data() {
                return {
                    users: []
                };
            },
            async mounted() {
                try {
                    const response = await axios.get('http://localhost:8080/user/getAll');
                    this.users = response.data;
                } catch (error) {
                    console.error("Error fetching users:", error);
                }
            }
        });

        // 挂载Vue应用
        app.mount('#app');	

</script>-->

<script setup>
import { Edit, Delete } from "@element-plus/icons-vue";
import { ref, reactive } from "vue";

import { getAllusers, deleteUser, addUser } from "@/api/user.js";
import { ElMessage } from "element-plus";

const Page = ref(1);
const limit = ref(10);
const total = ref(0);
const small = ref(false);
const background = ref(false);
const disabled = ref(false);
const genderData = ref([{ gender: 1 }, { gender: 0 }]);

const userDatas = ref([]);
const getData = async () => {
  let res = await getAllusers();
  userDatas.value = res.data;
  // this.total=res.data.length;
  console.log(userDatas.value);
};
getData();

function handleDelete(row) {
  deleteUser(row.id).then(res => {
    ElMessage.success("删除成功");
    getData();
  });
}
// const userA = ref({});
// const userD = async()=>{
//     let res = await addUser();
//     userA.value = res.data;
// }
// userD()
function handleSizeChange(val) {
  this.Page = val;
}
function handleCurrentChange(val) {
  thin.limit = val;
}
</script>

<template>
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <span>用户信息</span>
        <div class="extra">
          <el-button type="primary">添加信息</el-button>
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
          <el-button :icon="Edit" circle plain type="primary"></el-button>
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
