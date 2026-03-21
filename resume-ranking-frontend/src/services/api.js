import axios from "axios";

const BASE_URL = "https://resume-ranking-system-ux0j.onrender.com";

// ❌ अभी ये services deploy नहीं हैं
export const createUser = async () => {
  throw new Error("User service not deployed yet");
};

export const uploadResume = async () => {
  throw new Error("Resume service not deployed yet");
};

// ✅ GET RANKING
export const getRanking = async () => {
  const res = await axios.get(`${BASE_URL}/ranking`);
  return res.data;
};

// ❌ Not available
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
  const response = await fetch(`${BASE_URL}/ranking/download-report`);
  return await response.blob();
};