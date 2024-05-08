<!-- <template>
  <div class="personal-info">
    <h1 style="color: #fff;">个人信息</h1>
    <el-card class="info-card">
      <div slot="header">
        <span>用户信息</span>
      </div>
      <div class="info-content">
        <el-row>
          <el-col :span="8">
            <p><b>用户名：</b>{{ userInfo.username }}</p>
          </el-col>
          <el-col :span="8">
            <p><b>邮箱：</b>{{ userInfo.gender }}</p>
          </el-col>
          <el-col :span="8">
            <p><b>电话：</b>{{ userInfo.phone }}</p>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <p><b>地址：</b>{{ userInfo.age }}</p>
          </el-col>
        </el-row>
      </div>
    </el-card>
  </div>
</template>-->
<template>
  <div class="profile">
    <el-card shadow="hover" class="profile-card">
      <div slot="header" class="profile-header">
        <el-avatar :size="120" :src="avatarUrl"></el-avatar>
        <div class="profile-info">
          <h2 class="profile-name">{{ id }}</h2>
          <p class="profile-email">{{ email }}</p>
        </div>
      </div>
      <div class="profile-body">
        <el-divider><span class="divider-text">个人信息</span></el-divider>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form label-position="top" class="profile-form">
              <el-form-item label="性别">
                <el-tag>{{ gender }}</el-tag>
              </el-form-item>
              <el-form-item label="姓名">
                <span>{{ username }}</span>
              </el-form-item>
            </el-form>
          </el-col>
          <el-col :span="12">
            <el-form label-position="top" class="profile-form">
              <el-form-item label="电话">
                <span>{{ phone }}</span>
              </el-form-item>
              <el-form-item label="年龄">
                <span>{{ age }}</span>
              </el-form-item>
            </el-form>
          </el-col>
        </el-row>
      </div>
    </el-card>
    <el-row style="text-align: left">
      <el-button type="danger" @click="logout" style="margin: 2px 0 10px 830px; font-size: 22px">退出登录</el-button>
    </el-row>
  </div>
</template>

<script>
import axios from "axios";
axios.defaults.baseURL = "http://localhost:8080";
export default {
  data() {
    return {
      id: "",
      email: "",
      username: "",
      gender: "",
      phone: "",
      age: ""
    };
  },
  methods: {
    fetchProfile() {
      axios
        .get("/user/current")
        .then(response => {
          const data = response.data;
          this.id = data.id;
          this.email = data.email;
          this.username = data.username;
          this.gender = data.gender;
          this.phone = data.phone;
          this.age = data.age;
        })
        .catch(error => {
          console.error("Failed to fetch profile:", error);
        });
    },

    logout() {
      this.$confirm("您确定要退出登录吗？", "提示", {
        confirmButtonText: "确定", //确认按钮的文字显示
        type: "warning",
        center: true //文字居中显示
      })
        .then(() => {
          this.$message({
            type: "success",
            message: "退出登录成功！"
          });

          this.$router.push("/");
          sessionStorage.clear();
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消退出登录！"
          });
        });
    },

    created() {
      this.fetchProfile();
    }
  }
};
</script>

<!-- <style scoped>
.personal-info {
  margin: 20px;
}

.info-card {
  width: 400px;
}

.info-content {
  padding: 20px;
}
</style> -->

<!--
 * @Description: 
 * @Author: Rabbiter
 * @Date: 2023-03-04 15:31:03
-->
<!-- <template>
    <div
        style="
            text-align: center;
            height: 100%;
            padding: 20px 0 0 0;
            margin: 0px;
        "
    >
    <div class="userInfo">
        <b> 当前登录信息</b>
    </div>
        
        <el-descriptions
            title=""
            :column="1"
            size="40"
            border
            style="padding: 20px 0 20px 0"
        >
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-s-custom"></i>
                    账号
                </template>

                <el-tag type="info">165060</el-tag>
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-user"></i>
                    姓名
                </template>
                <el-tag type="info">白眉鹰王</el-tag>
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-mobile-phone"></i>
                    电话
                </template>
                <el-tag type="info">13365815762</el-tag>
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-location-outline"></i>
                    性别
                </template>
                <el-tag type="info">男</el-tag>
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="iconfont icon-r-mark1"></i>
                    角色
                </template>
                <el-tag type="info">用户</el-tag>
            </el-descriptions-item>
        </el-descriptions>
        <hr />

        <el-row style="text-align: left">
            <el-button
                type="danger"
                @click="logout"
                style="margin: 25px 0 0 90px; font-size: 22px;"
                >
                 退出登录</el-button
            >
        </el-row>
    </div>
</template>

<script>
export default {
    name: "Home",
    data() {
        return {
            user: {},
        };
    },
    computed: {},
    methods: {
        init() {
            this.user = JSON.parse(sessionStorage.getItem("CurUser"));
        },
        logout() {
            this.$confirm("您确定要退出登录吗？", "提示", {
                confirmButtonText: "确定", //确认按钮的文字显示
                type: "warning",
                center: true, //文字居中显示
            })
                .then(() => {
                    this.$message({
                        type: "success",
                        message: "退出登录成功！",
                    });

                    this.$router.push("/");
                    sessionStorage.clear();
                })
                .catch(() => {
                    this.$message({
                        type: "info",
                        message: "已取消退出登录！",
                    });
                });
        },
    },
    created() {
        this.init();
    },
};
</script>

<style scoped>
.el-descriptions {
    width: 90%;

    margin: 0 auto;
    text-align: center;
}

.userInfo {
    font-size: 32px;
}
</style> -->

<!-- <template>
    <div class="profile">
      <el-card shadow="hover" class="profile-card">
        <div slot="header" class="profile-header">
          <el-avatar :size="120" :src="avatarUrl"></el-avatar>
          <div class="profile-info">
            <h2 class="profile-name">{{ fullName }}</h2>
            <p class="profile-email">{{ email }}</p>
          </div>
        </div>
        <div class="profile-body">
          <el-divider><span class="divider-text">个人信息</span></el-divider>
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form label-position="top" class="profile-form">
                <el-form-item label="性别">
                  <el-tag>{{ gender }}</el-tag>
                </el-form-item>
                <el-form-item label="生日">
                  <span>{{ birthday }}</span>
                </el-form-item>
              </el-form>
            </el-col>
            <el-col :span="12">
              <el-form label-position="top" class="profile-form">
                <el-form-item label="电话">
                  <span>{{ phone }}</span>
                </el-form-item>
                <el-form-item label="地址">
                  <span>{{ address }}</span>
                </el-form-item>
              </el-form>
            </el-col>
          </el-row>
        </div>

       
      </el-card>
      <el-row style="text-align: left">
            <el-button
                type="danger"
                @click="logout"
                style="margin: 2px 0 10px 830px; font-size: 22px; "
                >
                 退出登录</el-button
            >
        </el-row>
    </div>
</template> -->

<!-- <script>
  export default {
    data() {
      return {
        fullName: '管理员',
        email: 'admin@123.com',
        avatarUrl: 'http://localhost:8080/doctor/lyf.jpg',
        gender: '男',
        birthday: '2002-09-18',
        phone: '12345678910',
        address: '中国'
      };
    },
    methods:{
        logout() {
            this.$confirm("您确定要退出登录吗？", "提示", {
                confirmButtonText: "确定", //确认按钮的文字显示
                type: "warning",
                center: true, //文字居中显示
            })
                .then(() => {
                    this.$message({
                        type: "success",
                        message: "退出登录成功！",
                    });

                    this.$router.push("/");
                    sessionStorage.clear();
                })
                .catch(() => {
                    this.$message({
                        type: "info",
                        message: "已取消退出登录！",
                    });
                });
        }
    }
  };


</script> -->

<style scoped>
.profile {
  margin: 20px;
}

.profile-card {
  max-width: 600px;
  margin: 0 auto;
}

.profile-header {
  display: flex;
  align-items: center;
}

.profile-info {
  margin-left: 20px;
}

.profile-name {
  font-size: 24px;
  margin-bottom: 5px;
}

.profile-email {
  color: #666;
}

.profile-body {
  margin-top: 20px;
}

.profile-form {
  margin-bottom: 20px;
}

.divider-text {
  font-weight: bold;
}
</style>
