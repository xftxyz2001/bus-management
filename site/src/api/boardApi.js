import request from "@/utils/request";

export default {
  getAllBoard() {
    return request.get("/board/getAllboard");
  },
  declare(data) {
    return request.post("/board/declare", data);
  },
  delete(id) {
    return request.delete(`/board/delete/${id}`);
  },
  revise(data) {
    return request.post("/board/revise", data);
  },
  searchNotice(keyword) {
    return request.get("/board/search", { params: { keyword: keyword } });
  }
};
