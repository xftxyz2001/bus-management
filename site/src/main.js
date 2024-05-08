import "./assets/main.scss";

import { createApp } from "vue";
import locale from "element-plus/dist/locale/zh-cn.mjs";
// import {ceeatePinia, createPinia} from 'pinia'
import ElementPlus from "element-plus";
import "element-plus/dist/index.css";
import App from "./App.vue";
import router from "@/router";

const app = createApp(App);
// const pinia = createPinia();
// app.use(pinia)
app.use(ElementPlus, { locale });
app.use(router);
app.mount("#app");
