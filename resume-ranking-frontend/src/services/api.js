import axios from "axios";

const USER_URL = "https://user-service-5k6b.onrender.com";
const RANKING_URL = "https://resume-ranking-system-ux0j.onrender.com";
const RESUME_URL = "https://resume-service-y9za.onrender.com";

// ✅ CREATE USER
export const createUser = async (user) => {
  const res = await axios.post(`${USER_URL}/users`, user);
  return res.data;
};

// ✅ UPLOAD RESUME
export const uploadResume = async (formData) => {
  const res = await axios.post(
    `${RESUME_URL}/resume/upload`,
    formData,
    {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    }
  );
  return res.data;
};

// ✅ GET RANKING
export const getRanking = async () => {
  const res = await axios.get(`${RANKING_URL}/ranking`);
  return res.data;
};

// ✅ DOWNLOAD REPORT
export const downloadReportAPI = async () => {
  const response = await fetch(`${RANKING_URL}/ranking/download-report`);
  return await response.blob();
};