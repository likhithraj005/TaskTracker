// src/apiClient.ts
import axios from "axios";

const apiClient = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL,
    headers: {
        "Content-Type": "application/json",
    },
});

console.log("API Base URL:", import.meta.env.VITE_API_BASE_URL);

export default apiClient;
