import request from "@/utils/request.js";

export const userRegisterService = registerData => {
  const params = new URLSearchParams();
  for (let key in registerData) {
    params.append(key, registerData[key]);
  }
  return request.post("/user/register", params);
};

export const userLoginService = loginData => {
  const params = new URLSearchParams();
  for (let key in loginData) {
    params.append(key, loginData[key]);
  }

  return request.post("/user/login", params);
};

export const getAllusers = () => {
  return request.get("/user/getAll");
};

export const deleteUser = id => {
  return request.delete("/user/delete/" + id);
};

export const addUser = UserData => {
  const params = new URLSearchParams();
  for (let key in UserData) {
    params.append(key, UserData[key]);
  }
  return request.post("/user/add", params);
};

export const getList = UserData => {
  return request.get("/user/getAll");
};

export const getAllbus = () => {
  return request.get("/bus/getAll");
};

export const deleteBoard = id => {
  return request.delete("/board/delete/" + id);
};
