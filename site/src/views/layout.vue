<template>
  <div class="com-page">
    <div class="maintitle">公交车信息管理系统</div>
    <el-container style="height: 94%">
      <!-- default-active设置进入页面时默认打开的菜单条目路由（这里的/indexpage是首页菜单条目的路由）；:router设置为真，打开路由；:collapse这里联合折叠图标实现菜单折叠与展开 -->
      <el-menu
        default-active="/main/index"
        :router="true"
        :collapse="isCollapse"
        background-color="#545c64"
        text-color="#fff"
      >
        <div class="fold">
          <!--折叠菜单的图标 -->
          <i class="icon" :class="foldphoto" @click="isfold"></i>
        </div>
        <el-submenu index="1">
          <template slot="title">
            <!-- i标签的class值为element-UI官网icon图标值，可直接引用 -->
            <i class="el-icon-s-home icon"></i>
            <span>主页面</span>
          </template>
          <!-- index为对应菜单条目的路由，在router文件夹的index.js中设置 -->
          <el-menu-item index="/main/index">首页</el-menu-item>
        </el-submenu>

        <el-submenu index="2">
          <template slot="title">
            <i class="el-icon-s-data icon"></i>
            <span>数据显示</span>
          </template>
          <el-menu-item index="/main/personal">个人信息</el-menu-item>
        </el-submenu>

        <el-submenu index="3">
          <template slot="title">
            <i class="el-icon-s-data icon"></i>
            <span>公告栏</span>
          </template>
          <el-menu-item index="/main/notice">公告栏</el-menu-item>
        </el-submenu>
        <el-submenu index="4">
          <template slot="title">
            <i class="el-icon-s-data icon"></i>
            <span>公交车信息</span>
          </template>
          <el-menu-item index="/main/businfo">公交车信息</el-menu-item>
        </el-submenu>

        <el-submenu index="5">
          <template slot="title">
            <i class="el-icon-s-custom icon"></i>
            <span>用户管理</span>
          </template>
          <el-menu-item index="/main/user">用户管理</el-menu-item>
        </el-submenu>

        <el-submenu index="6">
          <template slot="title">
            <i class="el-icon-s-custom icon"></i>
            <span>司机管理</span>
          </template>
          <el-menu-item index="/main/driver">司机管理</el-menu-item>
        </el-submenu>

        <el-submenu index="7">
          <template slot="title">
            <i class="el-icon-s-custom icon"></i>
            <span>公交查询</span>
          </template>
          <el-menu-item index="/main/bussearch">公交查询</el-menu-item>
        </el-submenu>

        <el-submenu index="8">
          <template slot="title">
            <i class="el-icon-s-custom icon"></i>
            <span>留言板</span>
          </template>
          <el-menu-item index="/main/message">留言板</el-menu-item>
        </el-submenu>

        <el-submenu index="9">
          <template slot="title">
            <i class="el-icon-s-tools icon"></i>
            <span>设置</span>
          </template>
          <el-menu-item index="/login">退出登录</el-menu-item>
        </el-submenu>
      </el-menu>
      <el-main>
        <!--引入跳转路由-->
        <router-view></router-view>
      </el-main>
    </el-container>
  </div>
  <el-footer class="bottitle">公交车信息管理系统 ©2024 Created by 段朝辉</el-footer>
</template>

<script>
export default {
  data() {
    return {
      isCollapse: false, //默认为不折叠菜单
      foldphoto: "el-icon-s-unfold" //菜单展开图标
    };
  },
  mounted() {},
  methods: {
    isfold() {
      //每次点击折叠图标值取反
      this.isCollapse = !this.isCollapse;
      if (this.isCollapse) {
        //折叠图标
        this.foldphoto = "el-icon-s-fold";
      } else {
        //展开图标
        this.foldphoto = "el-icon-s-unfold";
      }
    }
  }
};
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
