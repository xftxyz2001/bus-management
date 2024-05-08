<template>
  <!-- <div>
        <h1>留言板</h1>
        <el-form :model="messageForm" :rules="rules" ref="messageForm" label-width="100px">
            <el-form-item label="用户名" prop="username">
                <el-input v-model="messageForm.username"></el-input>
            </el-form-item>
            <el-form-item label="留言内容" prop="content">
                <el-input type="textarea" v-model="messageForm.content"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm">提交留言</el-button>
            </el-form-item>
        </el-form>
        <el-divider></el-divider>
        <div v-if="messages.length > 0">
            <h2>留言列表</h2>
            <el-list>
                <el-list-item v-for="message in messages" :key="message.id">
                    <template #content>
                        <div>{{ message.content }}</div>
                        <div>留言人：{{ message.username }}</div>
                        <div>创建时间：{{ message.addtime }}</div>
                    </template>
                </el-list-item>
            </el-list>
        </div>
        <div v-else>
            <p>暂无留言</p>
        </div>
    </div> -->

  <!-- <div id="app">
    <h1>留言板</h1>
    <el-card v-for="message in messages" :key="message.id" style="margin-bottom: 20px;">
      <div slot="header">
        {{ message.username }} - {{ message.addtime }}
      </div>
      <p>{{ message.content }}</p>
    </el-card>
  </div> -->

  <!-- <el-table
        :data="messages"
        style="width: 100%">
        <el-table-column
          prop="username"
          label="姓名">
        </el-table-column>
        <el-table-column
          prop="content"
          label="留言内容">
        </el-table-column>
        <el-table-column
          prop="addtime"
          label="留言时间">
        </el-table-column>
      
        <el-table-column
          prop="userid"
          label="留言者id">
        </el-table-column>
      
      </el-table> -->

  <!-- <div id="app">
        <el-container>
            <el-header>留言板</el-header>
            <el-main>
               
                <el-form :model="messageForm" ref="messageForm" label-width="80px">
                    <el-form-item label="用户名">
                        <el-input v-model="messageForm.username"></el-input>
                    </el-form-item>
                    <el-form-item label="留言内容">
                        <el-input type="textarea" v-model="messageForm.content"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="submitMessage">提交留言</el-button>
                    </el-form-item>
                </el-form>
              
                <el-timeline>
                    <el-timeline-item v-for="(message, index) in displayedMessages" :key="index" :timestamp="message.time">
                        <template #content>
                            <div>
                                <span>{{ message.username }}</span>
                                <p>{{ message.content }}</p>
                                <span v-if="message.reply">回复：{{ message.reply }}</span>
                            </div>
                        </template>
                    </el-timeline-item>
                </el-timeline>
            </el-main>
        </el-container>
    </div> -->

  <div id="message">
    <el-form ref="form" :model="form" label-width="80px" class="form_msg">
      <el-form-item label="留 言 者" class="item">
        <el-input prefix-icon="el-icon-s-promotion"></el-input>
      </el-form-item>
      <el-form-item label="留言时间" class="item">
        <el-col :span="11">
          <el-date-picker type="date" placeholder="选择日期" style="width: 100%"></el-date-picker>
        </el-col>
        <el-col class="line" :span="2">-</el-col>
        <el-col :span="11">
          <el-time-picker placeholder="选择时间" style="width: 100%"></el-time-picker>
        </el-col>
      </el-form-item>

      <el-form-item label="留言内容" class="item">
        <el-input type="textarea"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="warning" @click="onSubmit" class="buttonCla" icon="el-icon-upload" plain>发布留言</el-button>
      </el-form-item>
    </el-form>

    <div id="messageSub">
      <div v-for="f in forms">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>留言</span>
            <el-button
              style="float: right; padding: 0; width: 35px; height: 35px"
              type="danger"
              icon="el-icon-delete"
              circle
              @click="deleteMsg"
            ></el-button>
          </div>
          <div class="text item">
            {{ f.name }}
            <br />
            {{ f.date1 }}
            <br />
            {{ f.date2 }}
            <br />
            {{ f.cont }}
          </div>
        </el-card>
      </div>
    </div>
  </div>
</template>

<script>
// // 使用 Vue 3 Composition API
// import { ref, onMounted } from 'vue';
// import axios from 'axios';
// import { ElMessage } from 'element-plus';

// // 创建 Vue 实例
// export default {
//   setup() {
//     // 使用 ref 创建响应式数据
//     const messages = ref([]);
//     axios.defaults.baseURL = 'http://localhost:8080';
//     // 在组件挂载后获取数据
//     onMounted(() => {
//       axios.get('/message/getAll')
//         .then(response => {
//           // 获取到数据后更新数据
//           messages.value = response.data.data;
//           console.log(messages);
//         })
//         .catch(error => {
//           console.error('Error fetching messages:', error);
//         });
//     });

//     // 返回数据和模板中需要的属性
//     return {
//       messages
//     };
//   }
// };

// const vm = new Vue({
//     methods: {
//         goBack() {
//             window.location.href = './index.html'
//             console.log('go back');
//         },

//     }
// })

// const message = new Vue({
//     data() {
//         return {
//             form: {
//                 name: '',
//                 date1: '',
//                 date2: '',
//                 cont: '',
//             },
//             forms: []
//         }
//     },
//     methods: {
//         onSubmit() {
//             this.$refs.form.validate((v) => {
//                 if (v) {
//                     this.forms.push({...this.form});
//                     this.form = {name: '', date1: '', date2: '', cont: ''};
//                     this.$message({
//                         message: '你已成功发布留言',
//                         type: 'success'
//                     });
//                 } else {
//                     console.log('验证失败');
//                     this.$message.error('错误，你不能这么做');
//                     return false;
//                 }
//             });
//         },
//         deleteMsg(event) {
//             this.forms.splice(event, 1);
//             this.$message('成功删除留言');
//         },
//     }
// })

// vm.$mount('#sub1')
// message.$mount('#message')
</script>
