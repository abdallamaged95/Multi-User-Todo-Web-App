import axios from "axios";

let userJWTToken = "";

const data_api = axios.create({
  baseURL: `http://localhost:8080/api/tasks`,
  headers: {
    "Content-Type": "application/json",
  },
});

const auth_api = axios.create({
  baseURL: `http://localhost:8080/api/auth`,
});

class ApiService {
  static async getTasks() {
    try {
      const response = await data_api.get("");
      return response;
    } catch (error) {
      return error.response;
    }
  }

  static async createTask(taskData) {
    try {
      const response = await data_api.post("", taskData);
      return response;
    } catch (error) {
      return error.response;
    }
  }

  static async updateTask(taskId, taskData) {
    try {
      const response = await data_api.put(`/${taskId}`, taskData);
      return response;
    } catch (error) {
      return error.response;
    }
  }

  static async deleteTask(taskId) {
    try {
      const response = await data_api.delete(`/${taskId}`);
      return response;
    } catch (error) {
      return error.response;
    }
  }

  static async login(username, password) {
    try {
      const response = await auth_api.post("/login", {
        username: username,
        password: password,
      });
      return response;
    } catch (error) {
      return error.response;
    }
  }

  static async signup(username, email, password) {
    try {
      const response = await auth_api.post("/register", {
        username: username,
        email: email,
        password: password,
      });
      return response;
    } catch (error) {
      return error.response;
    }
  }

  static setUserJWTToken(token) {
    userJWTToken = token;
    data_api.defaults.headers.common[
      "Authorization"
    ] = `Bearer ${userJWTToken}`;
  }
}

export { userJWTToken, ApiService };
