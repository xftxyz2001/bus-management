<template>
  <div style="text-align: center; height: 100%; padding: 20px 0 0 0; margin: 0px">
    <div class="welcome">
      <b>
        <span>欢迎使用公交车信息管理系统，</span>
        <span style="color: black"></span>
      </b>
    </div>

    <br />
    <br />
    <h5 style="font-size: 26px">
      <span style="color: #fff; font-size: 22px">您当前的角色是：</span>
      <h1 style="color: #fff">{{ role }}</h1>
    </h5>
    <DateUtils></DateUtils>
  </div>
</template>

<script setup>
import DateUtils from "./DateUtils.vue";
import { ref } from "vue";
import request from "@/utils/request";

const role = ref("");

request({
  url: "/user/role",
  method: "get"
}).then(res => {
  role.value = res.data;
});
</script>

<style scoped>
.welcome {
  font-size: 30px;
  color: #fff;
}
</style>
