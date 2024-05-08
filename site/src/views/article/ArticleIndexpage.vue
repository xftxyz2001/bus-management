<!--
 * @Description: 
 * @Author: Rabbiter
 * @Date: 2023-03-04 15:31:03
-->
<template>
  <div style="text-align: center; height: 100%; padding: 20px 0 0 0; margin: 0px">
    <div class="welcome">
      <b>
        <span>欢迎使用公交车信息管理系统，</span>
        <span style="color: black">
          <!-- {{ user.name }} -->
        </span>
      </b>
    </div>

    <br />
    <br />
    <h5 style="font-size: 26px">
      <span style="color: #fff; font-size: 22px">您当前的角色是：</span>
      <h1 style="color: #fff">{{ isAdministrator ? "用户" : "管理员" }}</h1>
      <!-- {{
                user.roleId == 0
                    ? "超级管理员"
                    : user.roleId == 1
                    ? "管理员"
                    : "用户"
            }} -->
    </h5>
    <DateUtils></DateUtils>

    <!-- <div style="text-align: center; height: 18vw">
            <img
                src="@/assets/login_b.png"
                style="height: 100%"
            />
        </div> -->
  </div>
</template>

<script>
import DateUtils from "./DateUtils.vue";
export default {
  // name: "Home",
  name: "ArticleIndexpage",
  components: { DateUtils },
  data() {
    return {
      user: {}
    };
  },
  computed: {},
  methods: {
    init() {
      this.user = JSON.parse(sessionStorage.getItem("user"));
    }
  },
  created() {
    this.init();
  }
};
</script>

<style scoped>
.welcome {
  font-size: 30px;
  color: #fff;
}
</style>
