<template>
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <span>公交车信息</span>
        <div class="extra">
          <el-button type="primary" @click="showAddDialog">添加信息</el-button>
        </div>
      </div>
    </template>

    <el-table :data="busDatas" style="width: 100%">
      <el-table-column label="序号" width="100" prop="id"></el-table-column>
      <el-table-column label="公交线路" prop="busid"></el-table-column>
      <el-table-column label="起点" prop="startstation"></el-table-column>
      <el-table-column label="终点" prop="endstation"></el-table-column>
      <el-table-column label="运营时间" prop="runtime"></el-table-column>
      <el-table-column label="参考票价" prop="price"></el-table-column>
      <el-table-column label="路线" prop="routesite"></el-table-column>
      <el-table-column label="操作" width="100">
        <template #default="{ row }">
          <el-button :icon="Edit" circle plain type="primary" @click="showUpdateDialog(row)"></el-button>
          <el-button :icon="Delete" circle plain type="danger" @click="deleteBus(row.id)"></el-button>
        </template>
      </el-table-column>
      <template #empty>
        <el-empty description="没有数据" />
      </template>
    </el-table>
  </el-card>

  <!-- 新增/编辑弹窗 -->
  <el-dialog v-model="dialogVisible" title="新增/编辑公交车信息">
    <el-form :model="bus" label-width="80px">
      <el-form-item label="公交线路" prop="busid">
        <el-input v-model="bus.busid" />
      </el-form-item>
      <el-form-item label="起点" prop="startstation">
        <el-input v-model="bus.startstation" />
      </el-form-item>
      <el-form-item label="终点" prop="endstation">
        <el-input v-model="bus.endstation" />
      </el-form-item>
      <el-form-item label="运营时间" prop="runtime">
        <el-input v-model="bus.runtime" />
      </el-form-item>
      <el-form-item label="参考票价" prop="price">
        <el-input v-model="bus.price" />
      </el-form-item>
      <el-form-item label="路线" prop="routesite">
        <el-input v-model="bus.routesite" />
      </el-form-item>
      <el-form-item label="城市" prop="city">
        <el-input v-model="bus.city" />
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="dialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="addOrUpdateBus">确 定</el-button>
    </span>
  </el-dialog>
</template>

<script setup>
import busLineApi from "@/api/busLineApi";
import { Delete, Edit } from "@element-plus/icons-vue";
import { ref } from "vue";

const busDatas = ref([]);

// 新增/编辑弹窗数据
const dialogVisible = ref(false);
const dialogTitle = ref("");

const bus = ref({
  id: "",
  busid: "",
  startstation: "",
  endstation: "",
  runtime: "",
  price: "",
  routesite: "",
  city: ""
});

function getBusData() {
  busLineApi.getAll().then(res => {
    busDatas.value = res.data;
  });
}

function addBus() {
  busLineApi.add(bus.value).then(res => {
    getBusData();
  });
}

function updateBus() {
  busLineApi.update(bus.value).then(res => {
    getBusData();
  });
}

function deleteBus(id) {
  busLineApi.delete(id).then(res => {
    getBusData();
  });
}

function showUpdateDialog(row) {
  dialogVisible.value = true;
  dialogTitle.value = "编辑公交车信息";
  bus.value = { ...row };
}

function showAddDialog() {
  dialogVisible.value = true;
  dialogTitle.value = "新增公交车信息";
  bus.value = {
    id: "",
    busid: "",
    startstation: "",
    endstation: "",
    runtime: "",
    price: "",
    routesite: "",
    city: ""
  };
}

function addOrUpdateBus() {
  if (dialogTitle.value === "新增公交车信息") {
    addBus();
  } else {
    updateBus(bus.value);
  }
  dialogVisible.value = false;
}

getBusData();
</script>

<style lang="scss" scoped>
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
