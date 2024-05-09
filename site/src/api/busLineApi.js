import request from "@/utils/request";

export default {
  add(data) {
    return request.post("/bus/add", data);
  },
  getAll() {
    return request.get("/bus/getAll");
  },
  getByBusid(id) {
    return request.get("/bus/getByBusid", { params: { busid: id } });
  },
  update(data) {
    return request.post("/bus/update", data);
  },
  delete(id) {
    return request.delete("/bus/delete", { params: { id: id } });
  },
  search(data) {
    return request.post("/bus/search", data);
  },
  getStation(keyword) {
    return request.get("/bus/getStation", { params: { keyword } });
  }
};
