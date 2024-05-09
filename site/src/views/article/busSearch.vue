<template>
  <div class="bus-search">
    <h1 style="color: #fff">公交车查询</h1>
    <el-form label-width="80px">
      <el-form-item label="当前站点" style="color: aqua">
        <el-autocomplete
          v-model="query.start"
          :fetch-suggestions="querySearch"
          placeholder="请输入站点名称"
          @select="handleSelect1"
        ></el-autocomplete>
      </el-form-item>
      <el-form-item label="目标站点" style="color: aqua">
        <el-autocomplete
          v-model="query.end"
          :fetch-suggestions="querySearch"
          placeholder="请输入站点名称"
          @select="handleSelect2"
        ></el-autocomplete>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="searchBuses">查询公交车</el-button>
      </el-form-item>
    </el-form>

    <div>
      <h2 style="color: #fff">查询结果：</h2>
      <ul>
        <li v-for="bus in buses" :key="bus.id">
          <span style="color: aqua">{{ bus.busid }}</span>
          <!-- <span style="color: aqua">{{ bus.routesite }}</span> -->
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import busLineApi from "@/api/busLineApi";
import { ElMessage } from "element-plus";
import { ref } from "vue";

const query = ref({
  start: "",
  end: ""
});

const buses = ref([]);

function querySearch(queryString, cb) {
  if (queryString) {
    busLineApi.getStation(queryString).then(res => {
      const ss = res.data.map(item => {
        return { value: item };
      });
      cb(ss);
    });
  } else {
    cb([]);
  }
}

function handleSelect1(item) {
  query.value.start = item.value;
}

function handleSelect2(item) {
  query.value.end = item.value;
}

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
  busLineApi.search(query.value).then(res => {
    buses.value = res.data;
  });
}
</script>
