<script setup>
import { ElMessage, ElSelect, ElOption } from "element-plus";
import axios from "axios";
import { ref } from "vue";
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
// const rules = {
//     username: [
//         { required: true, message: '请输入用户名', trigger: 'blur' },
//         { min: 5, max: 16, message: '长度为5~16位非空字符', trigger: 'blur' }
//     ],
//     password: [
//         { required: true, message: '请输入密码', trigger: 'blur' },
//         { min: 5, max: 16, message: '长度为5~16位非空字符', trigger: 'blur' }
//     ],
//     rePassword: [
//         { validator: checkRePassword, trigger: 'blur' }
//     ]
// }

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

import { userRegisterService, userLoginService } from "../api/user.js";
import { useRouter } from "vue-router";
// import { useTokenStore } from '@/store/token.js';
// import { userRegisterService} from '@/api/user.js'
const register = async () => {
  //registerData是一个响应式对象,如果要获取值,需要.value
  let result = await userRegisterService(registerData.value);

  ElMessage.success(result.msg ? result.msg : "注册成功");
};

const router = useRouter();
//  const tokenStore = useTokenStore()
const login = async () => {
  let result = await userLoginService(registerData.value);

  ElMessage.success(result.msg ? result.msg : "登录成功");

  // tokenStore.setToken(result.data)
  router.push("/success");
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
<!-- <template>
    <div>
      <div class="login-box">
        <h2>公交车信息管理登录系统</h2>
        <el-form  >
          <div class="user-box">
            <br/>
            <input type="text" name="" required="" v-model="username">
            <label>用户名</label>
          </div>
          <div class="user-box">
            <br/>
        
            <input type="password" name="" required="" v-model="password">
            <label>密码</label>
          </div>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <a @click="handleSubmit">
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            登录
          </a>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <a @click="signUp_asd">注册 </a>
        </el-form>
      </div>
    </div>
  </template> -->

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

/* .login-box .user-box label {
    position: absolute;
    top: 0;
    left: 0;
    padding: 10px 0;
    font-size: 16px;
    color: #fff;
    pointer-events: none;
    transition: .5s;
  }
  
  .login-box .user-box input:focus ~ label,
  .login-box .user-box input:valid ~ label {
    top: -20px;
    left: 0;
    color: #03e9f4;
    font-size: 12px;
  } */

/* .button  {
    position: relative;
    display: inline-block;
    padding: 10px 20px;
    color: #03e9f4;
    font-size: 16px;
    text-decoration: none;
    text-transform: uppercase;
    overflow: hidden;
    transition: .5s;
    margin-top: 40px;
    letter-spacing: 4px
  } */

/* .button hover {
    background: #03e9f4;
    color: #fff;
    border-radius: 5px;
    box-shadow: 0 0 5px #03e9f4,
    0 0 25px #03e9f4,
    0 0 50px #03e9f4,
    0 0 100px #03e9f4;
  } */

/* .flex2  {
    position: relative;
    display: inline-block;
    padding: 10px 20px;
    color: #03e9f4;
    font-size: 16px;
    text-decoration: none;
    text-transform: uppercase;
    overflow: hidden;
    transition: .5s;
    margin-top: 40px;
    letter-spacing: 4px
  } */

/* .flex2 hover {
    background: #03e9f4;
    color: #fff;
    border-radius: 5px;
    box-shadow: 0 0 5px #03e9f4,
    0 0 25px #03e9f4,
    0 0 50px #03e9f4,
    0 0 100px #03e9f4;
  } */
/* 
  .flex {
    position: relative;
    display: inline-block;
    padding: 10px 20px;
    color: #10777c;
    font-size: 16px;
    text-decoration: none;
    text-transform: uppercase;
    overflow: hidden;
    transition: .5s;
    margin-top: 40px;
    letter-spacing: 4px
  } */

/* .flex {
    background: #10777c;
    color: #fff;
    border-radius: 5px;
    box-shadow: 0 0 5px #10777c
    0 0 25px #10777c,
    0 0 50px #10777c,
    0 0 100px #10777c;
    
  } */

/* .flex1 {
    position: relative;
    display: inline-block;
    padding: 10px 20px;
    color: #10777c;
    font-size: 16px;
    text-decoration: none;
    text-transform: uppercase;
    overflow: hidden;
    transition: .5s;
    margin-top: 40px;
    letter-spacing: 4px
  } */

/* .flex1 {
    background: #10777c;
    color: #fff;
    border-radius: 5px;
    box-shadow: 0 0 5px #10777c
    0 0 25px #10777c,
    0 0 50px #10777c,
    0 0 100px #10777c;
  } */
/* .button span {
    position: absolute;
    display: block;
  } */

/* .login-box a span:nth-child(1) {
    top: 0;
    left: -100%;
    width: 100%;
    height: 2px;
    background: linear-gradient(90deg, transparent, #03e9f4);
    animation: btn-anim1 1s linear infinite;
  }
  
  @keyframes btn-anim1 {
    0% {
      left: -100%;
    }
    50%, 100% {
      left: 100%;
    }
  }
  
  .login-box a span:nth-child(2) {
    top: -100%;
    right: 0;
    width: 2px;
    height: 100%;
    background: linear-gradient(180deg, transparent, #03e9f4);
    animation: btn-anim2 1s linear infinite;
    animation-delay: .25s
  }
  
  @keyframes btn-anim2 {
    0% {
      top: -100%;
    }
    50%, 100% {
      top: 100%;
    }
  }
  
  .login-box a span:nth-child(3) {
    bottom: 0;
    right: -100%;
    width: 100%;
    height: 2px;
    background: linear-gradient(270deg, transparent, #03e9f4);
    animation: btn-anim3 1s linear infinite;
    animation-delay: .5s
  }
  
  @keyframes btn-anim3 {
    0% {
      right: -100%;
    }
    50%, 100% {
      right: 100%;
    }
  }
  
  .login-box a span:nth-child(4) {
    bottom: -100%;
    left: 0;
    width: 2px;
    height: 100%;
    background: linear-gradient(360deg, transparent, #03e9f4);
    animation: btn-anim4 1s linear infinite;
    animation-delay: .75s
  }
  
  @keyframes btn-anim4 {
    0% {
      bottom: -100%;
    }
    50%, 100% {
      bottom: 100%;
    }
  } */
</style>
