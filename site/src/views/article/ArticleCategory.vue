<script setup>
import { getAllbus } from "@/api/user.js";
import { Edit, Delete } from "@element-plus/icons-vue";
import { ref } from "vue";

const busDatas = ref([]);
const getData1 = async () => {
  let res = await getAllbus();
  busDatas.value = res.data;
  // this.total=res.data.length;
  console.log(busDatas.value);
};
getData1();
</script>

<template>
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <span>公交车信息</span>
        <div class="extra">
          <el-button type="primary">添加信息</el-button>
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
          <el-button :icon="Edit" circle plain type="primary"></el-button>
          <el-button :icon="Delete" circle plain type="danger"></el-button>
        </template>
      </el-table-column>
      <template #empty>
        <el-empty description="没有数据" />
      </template>
    </el-table>
  </el-card>
</template>

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

<!-- <template>
    <el-card class="page-container">
      <template #header>
        <div class="header">
          <span>公交车信息</span>
          <div class="extra">
            <el-button type="primary">添加信息</el-button>
          </div>
        </div>
      </template>
  
      <el-table :data="pagedBusDatas" style="width: 100%">
       
            <el-table-column label="序号" width="100" prop="id"> </el-table-column>
            <el-table-column label="公交线路" prop="busid"></el-table-column>
            <el-table-column label="起点" prop="startstation"></el-table-column>
            <el-table-column label="终点" prop="endstation"></el-table-column>
            <el-table-column label="运营时间" prop="runtime"></el-table-column>
            <el-table-column label="参考票价" prop="price"></el-table-column>
            <el-table-column label="路线" prop="routesite"></el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <el-button :icon="Edit" circle plain type="primary"></el-button>
                    <el-button :icon="Delete" circle plain type="danger"></el-button>
                </template>
            </el-table-column>
            <template #empty>
                <el-empty description="没有数据" />
            </template>
      

      </el-table>
  
      <el-pagination
        :total="total"
        :page-size="pageSize"
        @current-change="handleCurrentChange"
      />
    </el-card>
</template>
  
<script setup>
  import { getAllbus } from '@/api/user.js';
  import { Edit, Delete } from '@element-plus/icons-vue';
  import { ref, onMounted } from 'vue';
  
  const busDatas = ref([]);
  const total = ref(0);
  const pageSize = 10; // 每页显示的条目数
  let currentPage = 1; // 当前页码
  
  const getData = async (page = 1) => {
    try {
      const res = await getAllbus(page, pageSize); // 根据页码和每页显示的条目数获取数据
      busDatas.value = res.data;
      total.value = res.total; // 总条目数
    } catch (error) {
      console.error('Error fetching bus data:', error);
    }
  };
  
  const handleCurrentChange = (page) => {
    currentPage = page;
    getData(page);
  };
  
  onMounted(() => {
    getData();
  });
  
  // 计算当前页的数据
  const pagedBusDatas = ref([]);
//   watchEffect(() => {
//     const startIndex = (currentPage - 1) * pageSize;
//     const endIndex = currentPage * pageSize;
//     pagedBusDatas.value = busDatas.value.slice(startIndex, endIndex);
//   });
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
</style>  -->
