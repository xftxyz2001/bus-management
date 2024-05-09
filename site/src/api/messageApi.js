import request from "@/utils/request";

export default {
  addcontext(data) {
    return request.post("/message/sayliuyan", data);
  },
  deleteMessage(id) {
    return request.delete(`/message/delete/${id}`);
  },
  getAllMessages() {
    return request.get("/message/getAll");
  },
  getReply(id) {
    return request.get("/message/getReply", { params: { id: id } });
  }
};
