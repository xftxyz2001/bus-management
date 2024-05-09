import request from "@/utils/request";

export default {
  getAllDrivers() {
    return request.get("/driver/getAllDrivers");
  },
  addDriver(data) {
    return request.post("/driver/addDriver", data);
  },
  getDriverById(id) {
    return request.get(`/driver/${id}`);
  },
  getByDriverName(name) {
    return request.get("/driver/getByDriverName", { params: { driverName: name } });
  },
  updateDriver(data) {
    return request.put("/driver/update", data);
  },
  deleteDriver(id) {
    return request.delete(`/driver/${id}`);
  }
};
