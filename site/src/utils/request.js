import axios from "axios";

import { ElMessage } from "element-plus";

const baseURL = "/api";
const instance = axios.create({ baseURL });

instance.interceptors.request.use(
  config => {
    //添加token
    const token = localStorage.getItem("token");
    if (token) {
      config.headers["Authorization"] = token;
    }
    return config;
  },
  err => {
    return Promise.reject(err);
  }
);

instance.interceptors.response.use(
  result => {
    // 解构
    const { config, data } = result;
    // 成功
    if (data.code === 0) {
      //登录
      if (config.url === "/user/login") {
        //保存token
        localStorage.setItem("token", data.data);
      }
      //其他业务
      return result.data;
    }

    ElMessage.error(data.msg ? data.msg : "服务异常");
    return Promise.reject(data);
  },

  err => {
    ElMessage.error(data.msg ? data.msg : "服务异常");
    return Promise.reject(err);
  }
);

export default instance;
