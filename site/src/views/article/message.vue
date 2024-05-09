<template>
  <el-container>
    <el-header>
      <el-row>
        <el-col :span="18">
          <el-input type="textarea" v-model="newMessage.content" placeholder="请输入留言内容"></el-input>
        </el-col>
        <el-col :span="6">
          <el-button type="primary" @click="postMessage">发布留言</el-button>
        </el-col>
      </el-row>
    </el-header>
    <el-main>
      <el-card v-for="message in messages" :key="message.id" class="box-card" style="margin: 10px 0">
        <el-card>
          <div slot="header" class="clearfix">
            <span style="font-size: 22px; font-weight: bolder; font-family: Gadget, sans-serif">
              {{ message.username }}
            </span>
          </div>
          <div>{{ message.content }}</div>
          <div>
            <el-button style="float: right; padding: 3px 0" type="success" @click="switchShowReply(message)">
              查看回复
            </el-button>
            <span style="float: right; padding: 3px 0; margin-right: 10px">{{ message.addtime }}</span>
          </div>
        </el-card>
        <el-card v-if="message.showReply">
          <el-row>
            <el-col :span="18">
              <el-input type="textarea" v-model="message.newreply" placeholder="请输入回复内容"></el-input>
            </el-col>
            <el-col :span="6">
              <el-button type="primary" @click="postReply(message)">发布回复</el-button>
            </el-col>
          </el-row>
          <div v-if="message.replies.length" v-for="(reply, index) in message.replies" :key="index">
            <el-card>
              <div slot="header" class="clearfix">
                <span style="font-size: 18px; font-weight: bolder; font-family: Gadget, sans-serif">
                  {{ reply.username }}
                </span>
              </div>
              <div>{{ reply.content }}</div>
              <span style="float: right; padding: 3px 0; margin-right: 10px">{{ reply.addtime }}</span>
            </el-card>
          </div>
          <div v-else>暂无回复</div>
        </el-card>
      </el-card>
    </el-main>
  </el-container>
</template>

<script setup>
import request from "@/utils/request";
import { ElButton, ElCard, ElContainer, ElHeader, ElInput, ElMain } from "element-plus";
import { ref } from "vue";

const newMessage = ref({
  content: ""
});
const messages = ref([]);

function fetchMessages() {
  request.get("/message/getAll").then(res => {
    messages.value = res.data;
  });
}
fetchMessages();

function switchShowReply(message) {
  message.showReply = !message.showReply;
  if (message.showReply) {
    if (!message.replies) {
      request.get(`/message/getReply?id=${message.id}`).then(res => {
        message.replies = res.data;
      });
    }
  }
}

function postMessage() {
  request.post("/message/sayliuyan", newMessage.value).then(() => {
    newMessage.value.content = "";
    location.reload();
  });
}

function postReply(message) {
  request
    .post("/message/sayliuyan", {
      reply: message.id,
      content: message.newreply
    })
    .then(() => {
      message.newreply = "";
      request.get(`/message/getReply?id=${message.id}`).then(res => {
        message.replies = res.data;
      });
    });
}
</script>
