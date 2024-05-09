import { createRouter, createWebHistory } from "vue-router";

import LoginVue from "@/views/login.vue";
import LayoutVue from "@/views/layout.vue";
import ArticleCategoryVue from "@/views/article/ArticleCategory.vue";
import ArticleManageVue from "@/views/article/ArticleManage.vue";
import ArticleIndexpage from "@/views/article/ArticleIndexpage.vue";
import boardMessage from "@/views/article/boardMessage.vue";
import personImformation from "@/views/article/personImformation.vue";
import messageBoard from "@/views/article/messageBoard.vue";
import driverManage from "@/views/article/driverManage.vue";
import busSearch from "@/views/article/busSearch.vue";
import forgetPassword from "@/views/article/forgetPassword.vue";

const routes = [
  { path: "/", redirect: "/login" },
  { path: "/login", component: LoginVue },
  { path: "/forget", component: forgetPassword },
  {
    path: "/success",
    component: LayoutVue,
    redirect: "/success/indexpage",
    children: [
      { path: "/success/category", component: ArticleCategoryVue },
      { path: "/success/manage", component: ArticleManageVue },
      { path: "/success/indexpage", component: ArticleIndexpage },
      { path: "/success/notice", component: boardMessage },
      { path: "/success/personal", component: personImformation },
      { path: "/success/message", component: messageBoard },
      { path: "/success/driver", component: driverManage },
      { path: "/success/bussearch", component: busSearch }
    ]
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

export default router;
