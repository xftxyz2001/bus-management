<template>
  <div class="forgot-password">
    <h1>重置密码</h1>
    <form @submit.prevent="submitEmail">
      <label for="email" style="color: #fff">电子邮件：</label>
      <input id="email" v-model="email" type="email" placeholder="请输入您的电子邮件" required />
      <button type="submit">发送重置邮件</button>
    </form>
    <br />
    <p v-if="message">{{ message }}</p>

    <button @click="back">返回</button>
  </div>
</template>

<script setup>
import userApi from "@/api/userApi";
import { ref } from "vue";
import { useRouter } from "vue-router";

const email = ref("");
const message = ref("");
const router = useRouter();

function back() {
  router.push("/login");
}

function submitEmail() {
  userApi
    .forgetPassword({ email: email.value })
    .then(() => {
      message.value = "如果您的电子邮件存在于我们的数据库中，一封重置邮件已发送到您的邮箱。";
    })
    .catch(error => {
      message.value = "发送邮件失败，请稍后重试。";
      console.error("Error:", error);
    });
}
</script>

<style scoped>
.forgot-password {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
}
input[type="email"] {
  width: 100%;
  padding: 8px;
  margin: 10px 0;
}
button {
  width: 100%;
  padding: 10px;
  background-color: #42b983;
  color: white;
  border: none;
  cursor: pointer;
}
button:hover {
  background-color: #333;
}
</style>
