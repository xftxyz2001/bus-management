<script setup>
import { useRouter } from "vue-router";
import { ElMessage } from "element-plus";
const router = useRouter();

localStorage.clear();
ElMessage.success("退出登录成功");
router.push("/login");
</script>