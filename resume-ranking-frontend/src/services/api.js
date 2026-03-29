import axios from "axios";

const BASE_URL = "https://api-gateway-357r.onrender.com?v=2";

const USER_URL = `${BASE_URL}/api/users`;
const RESUME_URL = `${BASE_URL}/resume`;
const RANKING_URL = `${BASE_URL}/ranking`;

export const createUser = async (user) => {
  const res = await axios.post(USER_URL, user);
  return res.data;
};

export const uploadResume = async (formData) => {
  const res = await axios.post(`${RESUME_URL}/upload`, formData, {
    headers: { "Content-Type": "multipart/form-data" },
  });
  return res.data;
};

export const getRanking = async () => {
  const res = await axios.get(RANKING_URL);
  return res.data;
};

export const downloadReportAPI = async () => {
  const response = await fetch(`${RANKING_URL}/download-report`);
  return await response.blob();
};