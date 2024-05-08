<template>
  <div id="message">
    <el-form ref="form" :model="form" label-width="80px" class="form_msg">
      <!-- <el-form-item label="留 言 者" class="item">
        <el-input prefix-icon="el-icon-s-promotion"></el-input>
      </el-form-item>
      <el-form-item label="留言时间" class="item">
        <el-col :span="11">
          <el-date-picker type="date" placeholder="选择日期" style="width: 100%"></el-date-picker>
        </el-col>
        <el-col class="line" :span="2">-</el-col>
        <el-col :span="11">
          <el-time-picker placeholder="选择时间" style="width: 100%"></el-time-picker>
        </el-col>
      </el-form-item> -->

      <el-form-item label="留言内容" class="item">
        <el-input type="textarea"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="warning" @click="publish" class="buttonCla" icon="el-icon-upload" plain>发布留言</el-button>
      </el-form-item>
    </el-form>

    <!-- <div id="messageSub">
      <div v-for="message in messages">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>留言</span>
            <el-button
              style="float: right; padding: 0; width: 35px; height: 35px"
              type="danger"
              icon="el-icon-delete"
              circle
              @click="deleteMsg"
            ></el-button>
          </div>
          <div class="text item">
            {{ f.name }}
            <br />
            {{ f.date1 }}
            <br />
            {{ f.date2 }}
            <br />
            {{ f.cont }}
          </div>
        </el-card>
      </div>
    </div> -->
  </div>
</template>

<script setup>
import { ref } from "vue";
import { ElMessage } from "element-plus";
import request from '@/utils/request.js'


</script>
