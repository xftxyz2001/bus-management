<template>
  <div class="bus-search">
    <h1 style="color: #fff">公交车查询</h1>
    <el-form label-width="80px">
      <el-form-item label="当前站点" style="color: aqua">
        <el-input></el-input>
      </el-form-item>
      <el-form-item label="目标站点" style="color: aqua">
        <el-input></el-input>
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
<script></script>
