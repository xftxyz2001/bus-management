<script>
import { Edit, Delete } from "@element-plus/icons-vue";
import { ref } from "vue";
import axios from "axios"; // 需要引入axios
import { deleteBoard } from "@/api/user.js";
import { ElMessage } from "element-plus";

const currentNotice1 = ref({
  createTime: "",
  title: "",
  content: "",
  updateTime: ""
});
export default {
  name: "NoticeBoard",
  setup() {
    //const currentNotice = ref('');
    const newTitle = ref(""); // 新公告标题
    const newNotice = ref("");
    const editDialogVisible = ref(false);
    const editedNotice = ref({ id: null, title: "", content: "" });

    const currentNotice = ref([]); // 存放当前公告列表
    const addDialogVisible = ref(false); // 控制新增公告对话框的显示状态
    const newNoticeForm = ref({ title: "", content: "" }); // 存放新增公告表单的数据

    const filteredNotice = ref([]);
    const searchKeyword = ref("");

    axios.defaults.baseURL = "http://localhost:8080";

    // 从后端获取公告信息
    const fetchNotice = async () => {
      try {
        const response = await axios.get("/board/getAllboard");
        currentNotice1.value = response.data.data;
        currentNotice.value = response.data.data;
        filteredNotice.value = response.data.data;
      } catch (error) {
        console.error("Error fetching notice:", error);
      }
    };

    //模糊搜索
    const searchNotice = () => {
      // 根据搜索关键字过滤公告列表
      filteredNotice.value = currentNotice.value.filter(notice => notice.title.includes(searchKeyword.value));
    };

    // 增加公告

    const openAddDialog = () => {
      console.log("打开新增公告对话框");
      addDialogVisible.value = true;
      console.log(addDialogVisible.value);
    };

    // 清空新增公告表单
    const clearAddForm = () => {
      newNoticeForm.value = { title: "", content: "" };
    };
    const addNotice = async () => {
      try {
        const response = await axios.post("/board/declare", {
          title: newNoticeForm.value.title,
          content: newNoticeForm.value.content
        });
        if (response.data) {
          await fetchNotice(); // 更新公告列表
          clearAddForm(); // 清空新增公告表单
          addDialogVisible.value = false; // 关闭新增公告对话框
          ElMessage.success("添加成功");
          console.log("公告添加成功");
        } else {
          console.error("Failed to add board:", response.data.error);
          console.log("公告添加失败");
        }
      } catch (error) {
        console.error("Error adding board:", error);
        console.log("公告添加失败");
      }
    };

    // const addNotice = async () => {
    //   try {
    //     const response = await axios.post('/board/declare', { title: newTitle.value, content: newNotice.value });
    //     if (response.data) {
    //       console.log(response.data.success);
    //       await fetchNotice(); // 更新公告列表
    //       newNotice.value = ''; // 清空输入框
    //       newTitle.value = ''; // 清空标题输入框
    //       ElMessage.success("添加成功");
    //       console.log('公告添加成功');

    //     } else {
    //       console.log('新公告标题:', newTitle.value);
    //       console.log('新公告内容:', newNotice.value);

    //       console.error('Failed to add board:', response.data.error);
    //       console.log('公告添加失败');
    //     }
    //   } catch (error) {
    //     console.log('新公告标题:', newTitle.value);
    //     console.log('新公告内容:', newNotice.value);

    //     console.error('Error adding board:', error);
    //     console.log('公告添加失败');
    //   }
    // };
    //修改公告
    // const editNotice = async () => {
    //   try {
    //     if (!editedNotice.value) {
    //   console.error('Edited notice is null or undefined');
    //   return;
    // }
    //     const response = await axios.post('/board/revise', { id: editedNotice.value.id, title: editedNotice.value.title, content: editedNotice.value.content });

    //     if (response.data.success) {
    //       await fetchNotice(); // 更新公告列表
    //       console.log('公告修改成功');
    //     } else {
    //       console.error('Failed to update board:', response.data.error);

    //       console.log('公告修改失败');
    //     }
    //   } catch (error) {
    //     console.error('Error updating board:', error);
    //     console.log(editedNotice.value.id);
    //     console.log( editedNotice.value.title);
    //     console.log('公告修改失败');
    //   } finally {
    //     editedNotice.value = null; // 清空正在编辑的公告
    //   }
    // };

    // const deleteNotice = async (id) => {
    //   try {
    //     await axios.delete('/board/delete/${id}');
    //     await fetchNotice(); // 删除成功后刷新公告列表
    //   } catch (error) {
    //     console.error('Error deleting notice:', error);
    //   }
    // };

    //删除公告
    function deleteNotice(row) {
      deleteBoard(row.id).then(res => {
        ElMessage.success("删除成功");
        fetchNotice();
      });
    }

    const showEditDialog = row => {
      editedNotice.value = {
        id: row.id,
        title: row.title,
        content: row.content
      };
      editDialogVisible.value = true;
    };

    const cancelEdit = () => {
      editDialogVisible.value = false;
    };

    const updateNotice = async () => {
      try {
        console.log(editedNotice.value.id);
        console.log(editedNotice.value.title);
        const response = await axios.post("/board/revise", {
          id: editedNotice.value.id,
          title: editedNotice.value.title,
          content: editedNotice.value.content
        });
        console.log(response.data);
        console.log(response.data.success);
        if (response.data) {
          await fetchNotice();
          console.log(response.data);
          editDialogVisible.value = false;
          ElMessage.success("修改成功");
          console.log("公告修改成功");
        } else {
          console.error("Failed to update board:", response.data.error);
          console.log("公告修改失败");
        }
      } catch (error) {
        console.error("Error updating board:", error);
        console.log("公告修改失败");
      }
    };

    // 页面加载时获取公告信息
    fetchNotice();

    return {
      filteredNotice,
      searchKeyword,
      searchNotice,
      addDialogVisible,
      newNoticeForm,
      openAddDialog,
      clearAddForm,
      currentNotice,
      newTitle,
      newNotice,
      editDialogVisible,
      editedNotice,
      addNotice,
      deleteNotice,
      showEditDialog,
      cancelEdit,
      updateNotice
    };
  }
};
</script>

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

        <el-table :data="currentNotice" style="width: 100%">
          <el-table-column prop="createTime" label="时间"></el-table-column>
          <el-table-column prop="title" label="标题"></el-table-column>
          <el-table-column prop="content" label="内容"></el-table-column>
          <el-table-column prop="updateTime" label="修改时间"></el-table-column>

          <el-table-column label="操作" width="100">
            <!-- <el-button @click="deleteNotice(currentNotice.id)" type="danger">删除</el-button> -->
            <template #default="{ row }">
              <el-button @click="showEditDialog(row)" type="primary">编辑</el-button>
              <el-button @click="deleteNotice(row)" type="danger">删除</el-button>

              <!-- <el-button :icon="Edit" circle plain type="primary"></el-button>
                    <el-button :icon="Delete" circle plain type="danger"></el-button> -->
            </template>
          </el-table-column>
        </el-table>
      </el-card>
      <!-- <p>{{ currentNotice }}</p>
        <h2>新公告标题：</h2>
        <el-input v-model="newTitle" placeholder="请输入新公告标题"></el-input>
        <h2>新公告内容：</h2>
        <el-input v-model="newNotice" type="textarea" rows="4" placeholder="请输入新公告内容"></el-input>
         <el-button @click="updateNotice" type="primary">更新公告</el-button> 
        <el-button @click="addNotice" type="success">增加公告</el-button>-->
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
