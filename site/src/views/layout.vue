<template>
  <div class="com-page">
    <div class="maintitle">公交车信息管理系统</div>
    <el-container style="height: 94%">
      <el-menu
        default-active="/main/index"
        :router="true"
        :collapse="isCollapse"
        background-color="#545c64"
        text-color="#fff"
      >
        <div class="fold">
          <i class="icon" :class="foldphoto" @click="isfold"></i>
        </div>
        <el-submenu v-for="(item, index) in menuItems" :index="index.toString()" :key="index">
          <template slot="title">
            <i :class="item.icon"></i>
            <span>{{ item.title }}</span>
          </template>
          <el-menu-item :index="item.route">{{ item.name }}</el-menu-item>
        </el-submenu>
      </el-menu>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </div>
  <el-footer class="bottitle">公交车信息管理系统 ©2024 Created by 段朝辉</el-footer>
</template>

<script setup>
import { ref } from "vue";

const isCollapse = ref(false);
const foldphoto = ref("el-icon-s-unfold");

function isfold() {
  isCollapse.value = !isCollapse.value;
  if (isCollapse.value) {
    foldphoto.value = "el-icon-s-fold";
  } else {
    foldphoto.value = "el-icon-s-unfold";
  }
}

const menuItems = [
  { title: "主页面", name: "首页", route: "/main/index", icon: "el-icon-s-home icon" },
  { title: "数据显示", name: "个人信息", route: "/main/personal", icon: "el-icon-s-data icon" },
  { title: "公告栏", name: "公告栏", route: "/main/notice", icon: "el-icon-s-data icon" },
  { title: "公交车信息", name: "公交车信息", route: "/main/businfo", icon: "el-icon-s-data icon" },
  { title: "用户管理", name: "用户管理", route: "/main/user", icon: "el-icon-s-custom icon" },
  { title: "司机管理", name: "司机管理", route: "/main/driver", icon: "el-icon-s-custom icon" },
  { title: "公交查询", name: "公交查询", route: "/main/bussearch", icon: "el-icon-s-custom icon" },
  { title: "留言板", name: "留言板", route: "/main/message", icon: "el-icon-s-custom icon" },
  { title: "设置", name: "退出登录", route: "/logout", icon: "el-icon-s-tools icon" }
];
</script>

<style scoped>
.maintitle {
  height: 6%;
  /* border: 1px solid green; */
  padding-left: 1.2rem;
  display: flex;
  align-items: center;
  font-size: 1.6rem;
  letter-spacing: 0.2rem;
  /* font-weight: bold; */
  background-color: #545c64;
  color: white;
}

.bottitle {
  /* position: absolute; */
  bottom: 0;
  text-align: center;
  width: 100%;
  color: #fff;
}

/* 图标样式 */
.icon {
  color: white;
}

/* 折叠图标 */
.fold {
  height: 3rem;
  /* border-bottom: 1px solid rgba(255, 255, 255, 0.3); */
  display: flex;
  align-items: center;
  justify-content: flex-end;
  padding-right: 0.3rem;
  margin-bottom: 0.6rem;
  background-color: rgba(0, 0, 0, 0.5);
}
</style>
