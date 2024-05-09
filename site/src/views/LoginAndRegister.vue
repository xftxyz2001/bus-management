<script setup>
import { ElMessage, ElSelect, ElOption } from "element-plus";
import { ref } from "vue";
import { useRouter } from "vue-router";
import { userRegisterService, userLoginService } from "../api/user.js";

const router = useRouter();

const isRegister = ref(false);
//定义数据模型
const registerData = ref({
  username: "",
  password: "",
  rePassword: "",
  age: "",
  gender: "",
  phone: ""
});

// const age = ref(null)
const ageOptions = Array.from({ length: 101 }, (_, i) => i);

// const gender = ref(null)
const genderOptions = ref([
  { label: "女", value: 0 },
  { label: "男", value: 1 }
]);

//校验密码的函数
const checkRePassword = (rule, value, callback) => {
  if (value === "") {
    callback(new Error("请再次确认密码"));
  } else if (value !== registerData.value.password) {
    callback(new Error("请确保两次输入的密码一样"));
  } else {
    callback();
  }
};

const register = async () => {
  //registerData是一个响应式对象,如果要获取值,需要.value
  let result = await userRegisterService(registerData.value);

  ElMessage.success(result.msg ? result.msg : "注册成功");
  isRegister.value = false;
};

const login = async () => {
  let result = await userLoginService(registerData.value);

  ElMessage.success(result.msg ? result.msg : "登录成功");

  router.push("/main");
};

const gotoforgerPassword = () => {
  router.push("/forget"); // 确保已经在路由配置中定义了 '/forgot-password'
};

const clearRegisterData = () => {
  registerData.value = {
    username: "",
    password: "",
    rePassword: ""
  };
};
</script>

<template>
  <!-- <el-row> -->

  <!-- 注册表单  -->
  <div class="login-box">
    <el-form ref="form" size="large" autocomplete="off" v-if="isRegister" :model="registerData" :rules="rules">
      <el-form-item>
        <h2>注册界面</h2>
      </el-form-item>
      <el-form-item prop="username" class="user-box">
        <el-input :prefix-icon="User" placeholder="请输入用户名" v-model="registerData.username"></el-input>
      </el-form-item>
      <el-form-item prop="password" class="user-box">
        <el-input
          :prefix-icon="Lock"
          type="password"
          placeholder="请输入密码"
          v-model="registerData.password"
        ></el-input>
      </el-form-item>
      <el-form-item prop="rePassword" class="user-box">
        <el-input
          :prefix-icon="Lock"
          type="password"
          placeholder="请输入再次密码"
          v-model="registerData.rePassword"
        ></el-input>
      </el-form-item>
      <!-- <el-form-item prop="rePassword" class="user-box">
                <el-input :prefix-icon="Lock" type="password" placeholder="年龄"
                    v-model="registerData.rePassword"></el-input>
            </el-form-item> -->
      <el-form-item prop="age" class="user-box">
        <el-select v-model="registerData.age" placeholder="请选择年龄">
          <el-option v-for="ageOption in ageOptions" :key="ageOption" :label="ageOption" :value="ageOption"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item prop="phone" class="user-box">
        <el-input placeholder="电话" v-model="registerData.phone"></el-input>
      </el-form-item>

      <el-form-item prop="gender" class="user-box">
        <el-select v-model="registerData.gender" placeholder="请选择性别">
          <el-option
            v-for="option in genderOptions"
            :key="option.value"
            :label="option.label"
            :value="option.value"
          ></el-option>
        </el-select>
      </el-form-item>
      <!-- //注册按钮  -->
      <br />
      <div class="jzw">
        <el-button class="button" type="primary" auto-insert-space @click="register">注册</el-button>
        <el-button
          class="button"
          type="info"
          :underline="false"
          @click="
            isRegister = false;
            clearRegisterData();
          "
        >
          返回
        </el-button>
      </div>
    </el-form>
    <!-- </div> -->
    <!-- <div class="login-box">  -->
    <el-form ref="form" size="large" autocomplete="off" v-if="!isRegister" :model="registerData" :rules="rules">
      <el-form-item>
        <h2>公交车信息管理登录系统</h2>
      </el-form-item>
      <el-form-item prop="username" class="user-box">
        <el-input :prefix-icon="User" placeholder="请输入用户名" v-model="registerData.username"></el-input>
      </el-form-item>
      <el-form-item prop="password" class="user-box">
        <el-input
          name="password"
          :prefix-icon="Lock"
          type="password"
          placeholder="请输入密码"
          v-model="registerData.password"
        ></el-input>
      </el-form-item>
      <!-- <el-form-item> -->
      <div class="jzw">
        <el-checkbox class="flex">记住我</el-checkbox>
        <el-link class="flex1" type="primary" :underline="false" @click="gotoforgerPassword">忘记密码？</el-link>
      </div>
      <!-- </el-form-item> -->
      <!-- 登录按钮   -->
      <br />
      <div class="jzw">
        <el-button class="button" type="primary" auto-insert-space @click="login">登录</el-button>

        <el-button
          class="button"
          type="info"
          :underline="false"
          @click="
            isRegister = true;
            clearRegisterData();
          "
        >
          注册
        </el-button>
      </div>
    </el-form>
  </div>

  <!-- </el-row> -->
</template>

<style>
body {
  background-image: url(@/assets/login_c.jpg);
  background-size: 100%;
  background-position: 0px -50px;
}

.login-box {
  /* position: absolute; */
  /* top: 50%; */
  /* left: 50%; */
  margin: 10% auto;
  /* text-align:center; */
  width: 400px;
  padding: 40px;
  /* transform: translate(-50%, -50%); */
  background: rgba(0, 0, 0, 0.5);
  box-sizing: border-box;
  box-shadow: 0 15px 25px rgba(0, 0, 0, 0.6);
  border-radius: 10px;
}

.login-box h2 {
  margin: 0 0 30px;
  padding: 0;
  color: #fff;
  text-align: center;
}

.login-box .user-box {
  position: relative;
}

.login-box .user-box input {
  width: 100%;
  padding: 10px 0;
  font-size: 16px;
  /* color: #fff; */
  /* margin-bottom: 30px; */
  border: none;
  border-bottom: 1px solid #fff;
  outline: none;
  background: transparent;
}

.jzw {
  display: flex;
  justify-content: space-between;
}
</style>
