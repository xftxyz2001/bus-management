<template>
  <div class="profile">
    <el-card shadow="hover" class="profile-card">
      <div slot="header" class="profile-header">
        <el-avatar :size="120" :src="avatarUrl"></el-avatar>
        <div class="profile-info">
          <h2 class="profile-name">{{ id }}</h2>
          <p class="profile-email">{{ email }}</p>
        </div>
      </div>
      <div class="profile-body">
        <el-divider><span class="divider-text">个人信息</span></el-divider>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form label-position="top" class="profile-form">
              <el-form-item label="性别">
                <el-tag v-if="gender === 0" type="danger">女</el-tag>
                <el-tag v-else-if="gender === 1">男</el-tag>
                <el-tag v-else type="info">保密</el-tag>
              </el-form-item>
              <el-form-item label="姓名">
                <span>{{ username }}</span>
              </el-form-item>
            </el-form>
          </el-col>
          <el-col :span="12">
            <el-form label-position="top" class="profile-form">
              <el-form-item label="电话">
                <span>{{ phone }}</span>
              </el-form-item>
              <el-form-item label="年龄">
                <span>{{ age }}</span>
              </el-form-item>
            </el-form>
          </el-col>
        </el-row>
      </div>
      <!-- 分割线 -->
      <el-divider></el-divider>
      <el-button type="danger" @click="logout" style="float: right;margin-right: -20px;margin-top: -10px;">退出登录</el-button>
    </el-card>
    
    <!-- <div>
      <el-button type="danger" @click="logout" style="float: right;">退出登录</el-button>
    </div> -->
    <!-- <el-row style="text-align: left"> -->
    <!-- </el-row> -->
  </div>
</template>

<script setup>
import request from "@/utils/request";
import { ElMessage, ElMessageBox } from "element-plus";

import { ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();

const id = ref("");
const email = ref("");
const username = ref("");
const gender = ref("");
const phone = ref("");
const age = ref("");

function fetchProfile() {
  console.log(12312312312312312);
  request
    .get("/user/current")
    .then(res => {
      const data = res.data;
      id.value = data.id;
      email.value = data.email;
      username.value = data.username;
      gender.value = data.gender;
      phone.value = data.phone;
      age.value = data.age;
    })
    .catch(error => {
      console.error("Failed to fetch profile:", error);
    });
}

function logout() {
  ElMessageBox.confirm("您确定要退出登录吗？", "提示", {
    confirmButtonText: "确定",
    type: "warning",
    center: true
  })
    .then(() => {
      ElMessage({
        type: "success",
        message: "退出登录成功！"
      });

      router.push("/");
      localStorage.clear();
    })
    .catch(() => {
      ElMessage({
        type: "info",
        message: "已取消退出登录！"
      });
    });
}

fetchProfile();
</script>

<style scoped>
.profile {
  margin: 20px;
}

.profile-card {
  max-width: 600px;
  margin: 0 auto;
}

.profile-header {
  display: flex;
  align-items: center;
}

.profile-info {
  margin-left: 20px;
}

.profile-name {
  font-size: 24px;
  margin-bottom: 5px;
}

.profile-email {
  color: #666;
}

.profile-body {
  margin-top: 20px;
}

.profile-form {
  margin-bottom: 20px;
}

.divider-text {
  font-weight: bold;
}
</style>
