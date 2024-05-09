<template>
  <div class="bus-search">
    <h1 style="color: #fff">公交车查询</h1>
    <el-form label-width="80px">
      <el-form-item label="当前站点" style="color: aqua">
        <el-input v-model="query.start"></el-input>
      </el-form-item>
      <el-form-item label="目标站点" style="color: aqua">
        <el-input el-input v-model="query.end"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="searchBuses">查询公交车</el-button>
      </el-form-item>
    </el-form>

    <div>
      <h2 style="color: #fff">查询结果：</h2>
      <ul>
        <li></li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import request from "@/utils/request";
import { ElMessage } from "element-plus";

const query = ref({
  start: "",
  end: ""
});

function searchBuses() {
  if (!query.value.start) {
    ElMessage.warning("请输入起始站点");
    return;
  }
  if (!query.value.end) {
    ElMessage.warning("请输入目标站点");
    return;
  }
  if (query.value.start === query.value.end) {
    ElMessage.warning("起始站点和目标站点不能相同");
    return;
  }
  request.post("/bus/search", query.value).then(res => {
    console.log(res.data);
  });
}
</script>
