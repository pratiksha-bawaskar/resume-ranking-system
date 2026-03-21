import axios from "axios";

// ✅ अलग-अलग services के URLs
const USER_URL = "https://user-service-5k6b.onrender.com";
const RANKING_URL = "https://resume-ranking-system-ux0j.onrender.com";

// ✅ CREATE USER
export const createUser = async (user) => {
  const res = await axios.post(`${USER_URL}/users`, user);
  return res.data;
};

// ❌ Resume अभी deploy नहीं हुआ
export const uploadResume = async () => {
  throw new Error("Resume service not deployed yet");
};

// ✅ GET RANKING
export const getRanking = async () => {
  const res = await axios.get(`${RANKING_URL}/ranking`);
  return res.data;
};

// ❌ अभी APIs available नहीं
export const deleteAllData = async () => {
  throw new Error("Delete API not available");
};

export const getResumeScore = async () => {
  throw new Error("Score API not available");
};

export const getResumeSuggestion = async () => {
  throw new Error("Suggestion API not available");
};

// ✅ DOWNLOAD REPORT
export const downloadReportAPI = async () => {
  const response = await fetch(`${RANKING_URL}/ranking/download-report`);
  return await response.blob();
};