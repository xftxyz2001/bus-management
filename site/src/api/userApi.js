import request from "@/utils/request";

export default {
  add(data) {
    return request.post("/user/add", data);
  },
  register(data) {
    return request.post("/user/register", data);
  },
  login(data) {
    return request.post("/user/login", data);
  },
  userInfo(id) {
    return request.get("/user/userInfo", { params: { userId: id } });
  },
  getAll() {
    return request.get("/user/getAll");
  },
  getCurrent(id) {
    return request.get("/user/current", { params: { userId: id } });
  },
  update(data) {
    return request.put("/user/update", data);
  },
  delete(id) {
    return request.delete(`/user/delete/${id}`);
  },
  updatePwd(data) {
    return request.patch("/user/updatePwd", data);
  },
  forgetPassword(data) {
    return request.post("/user/forget", data);
  },
  getRole(id) {
    return request.get("/user/role", { params: { userId: id } });
  }
};
