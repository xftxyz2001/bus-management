<template>
  <div style="display: flex; justify-content: space-between; align-items: center">
    <h1>公告栏</h1>
    <el-button @click="openAddDialog" type="success">增加公告</el-button>
  </div>

  <div>
    <div>
      <el-input v-model="searchKeyword" placeholder="请输入搜索关键字" @change="searchNotice"></el-input>
    </div>

    <div style="margin-top: 10px">
      <el-card>
        <template #header>
          <div class="header">
            <span>公告信息</span>
          </div>
        </template>

        <el-table :data="filteredNotice" style="width: 100%">
          <el-table-column prop="createtime" label="时间"></el-table-column>
          <el-table-column prop="title" label="标题"></el-table-column>
          <el-table-column prop="content" label="内容"></el-table-column>
          <el-table-column prop="updatetime" label="修改时间"></el-table-column>

          <el-table-column label="操作" width="100">
            <template #default="{ row }">
              <el-button @click="showEditDialog(row)" type="primary">编辑</el-button>
              <el-button @click="deleteNotice(row)" type="danger">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </div>
  </div>

  <el-dialog title="新增公告" v-model="addDialogVisible" @close="clearAddForm">
    <el-form ref="addForm" :model="newNoticeForm" label-width="80px">
      <el-form-item label-width="100px" label="新公告标题">
        <el-input v-model="newNoticeForm.title"></el-input>
      </el-form-item>
      <el-form-item label-width="100px" label="新公告内容">
        <el-input type="textarea" v-model="newNoticeForm.content"></el-input>
      </el-form-item>
    </el-form>

    <span slot="footer" class="dialog-footer">
      <el-button @click="addDialogVisible = false">取消</el-button>
      <el-button type="primary" @click="addNotice">确认添加</el-button>
    </span>
  </el-dialog>

  <el-dialog v-model="editDialogVisible" title="编辑公告">
    <el-form ref="editForm" :model="editedNotice" label-width="80px">
      <el-form-item label="标题">
        <el-input v-model="editedNotice.title"></el-input>
      </el-form-item>
      <el-form-item label="内容">
        <el-input type="textarea" v-model="editedNotice.content"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="cancelEdit">取 消</el-button>
      <el-button type="primary" @click="updateNotice">确 定</el-button>
    </span>
  </el-dialog>
</template>

<script setup>
import boardApi from "@/api/boardApi";
import { ElMessage } from "element-plus";
import { ref } from "vue";

const currentNotice1 = ref({
  createTime: "",
  title: "",
  content: "",
  updateTime: ""
});

const newTitle = ref(""); // 新公告标题
const newNotice = ref("");
const editDialogVisible = ref(false);
const editedNotice = ref({ id: null, title: "", content: "" });

const currentNotice = ref([]); // 存放当前公告列表
const addDialogVisible = ref(false); // 控制新增公告对话框的显示状态
const newNoticeForm = ref({ title: "", content: "" }); // 存放新增公告表单的数据

const filteredNotice = ref([]);
const searchKeyword = ref("");

// 从后端获取公告信息
function fetchNotice() {
  boardApi.getAllBoard().then(res => {
    currentNotice.value = res.data;
    filteredNotice.value = res.data;
  });
}

//模糊搜索
function searchNotice() {
  // 根据搜索关键字过滤公告列表
  filteredNotice.value = currentNotice.value.filter(
    notice => notice.title.includes(searchKeyword.value) || notice.content.includes(searchKeyword.value)
  );
}

// 增加公告

function openAddDialog() {
  addDialogVisible.value = true;
}

// 清空新增公告表单
function clearAddForm() {
  newNoticeForm.value = { title: "", content: "" };
}

function addNotice() {
  boardApi.declare(newNoticeForm.value).then(res => {
    fetchNotice();
    clearAddForm();
    addDialogVisible.value = false;
    ElMessage.success("添加成功");
    console.log("公告添加成功");
  });
}

//删除公告
function deleteNotice(row) {
  boardApi.delete(row.id).then(res => {
    fetchNotice();
    ElMessage.success("删除成功");
    console.log("公告删除成功");
  });
}

function showEditDialog(row) {
  editedNotice.value = {
    id: row.id,
    title: row.title,
    content: row.content
  };
  editDialogVisible.value = true;
}

function cancelEdit() {
  editDialogVisible.value = false;
}

function updateNotice() {
  boardApi.revise(editedNotice.value).then(res => {
    fetchNotice();
    editDialogVisible.value = false;
    ElMessage.success("修改成功");
    console.log("公告修改成功");
  });
}

// 页面加载时获取公告信息
fetchNotice();
</script>

<style scoped>
/* 在这里可以添加一些局部样式 */
h1 {
  font-size: 24px;
  color: #fff;
}
h2 {
  font-size: 20px;
  color: #666;
}
</style>
