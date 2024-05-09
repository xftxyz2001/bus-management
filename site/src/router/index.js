import { createRouter, createWebHistory } from "vue-router";

import LoginAndRegisterVue from "@/views/LoginAndRegister.vue";
import ForgetPasswordVue from "@/views/ForgetPassword.vue";

import LayoutVue from "@/views/layout.vue";

import IndexVue from "@/views/article/inedx.vue";
import PersonalVue from "@/views/article/personal.vue";
import NoticeVue from "@/views/article/notice.vue";
import BusInfoVue from "@/views/article/businfo.vue";
import UserVue from "@/views/article/user.vue";
import DriverVue from "@/views/article/driver.vue";
import BusSearchVue from "@/views/article/bussearch.vue";
import MessageVue from "@/views/article/message.vue";

const routes = [
  { path: "/", redirect: "/login" },
  { path: "/login", component: LoginAndRegisterVue },
  { path: "/forget", component: ForgetPasswordVue },
  {
    path: "/main",
    component: LayoutVue,
    redirect: "/main/index",
    children: [
      // 首页
      { path: "/main/index", component: IndexVue },
      // 个人信息
      { path: "/main/personal", component: PersonalVue },
      // 公告栏
      { path: "/main/notice", component: NoticeVue },
      // 公交车信息
      { path: "/main/businfo", component: BusInfoVue },
      // 用户管理
      { path: "/main/user", component: UserVue },
      // 司机管理
      { path: "/main/driver", component: DriverVue },
      // 公交查询
      { path: "/main/bussearch", component: BusSearchVue },
      // 留言板
      { path: "/main/message", component: MessageVue }
    ]
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

export default router;
