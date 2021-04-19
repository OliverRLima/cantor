import axios from "axios";

const api = axios.create({
  baseURL: "https://back-container:8080"
  // "https://backend-musicall.azurewebsites.net"
});

export default api;
