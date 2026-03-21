import axios from "axios";

// ✅ LIVE URLs
const USER_URL = "https://user-service-5k6b.onrender.com";
const RANKING_URL = "https://resume-ranking-system-ux0j.onrender.com";
const RESUME_URL = "https://resume-service-y9za.onrender.com"; // 👈 तुम्हारा live URL

// ✅ CREATE USER
export const createUser = async (user) => {
  try {
    const res = await axios.post(`${USER_URL}/users`, user);
    return res.data;
  } catch (error) {
    console.error("CREATE USER ERROR:", error);
    throw new Error("Error creating user");
  }
};

// ✅ UPLOAD RESUME
export const uploadResume = async (formData) => {
  try {
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
  } catch (error) {
    console.error("UPLOAD ERROR:", error);
    throw new Error("Error uploading resume");
  }
};

// ✅ GET RANKING
export const getRanking = async () => {
  try {
    const res = await axios.get(`${RANKING_URL}/ranking`);
    return res.data;
  } catch (error) {
    console.error("RANKING ERROR:", error);
    return [];
  }
};

// ❌ OPTIONAL (अगर UI में है तो safe fallback)
export const deleteAllData = async () => {
  try {
    await axios.delete(`${RESUME_URL}/resume/deleteAll`);
  } catch (error) {
    console.error("DELETE ERROR:", error);
  }
};

// ❌ OPTIONAL (future use)
export const getResumeScore = async () => {
  try {
    const res = await axios.get(`${RESUME_URL}/resume/score`);
    return res.data;
  } catch {
    return { score: 0 };
  }
};

// ❌ OPTIONAL (future use)
export const getResumeSuggestion = async () => {
  try {
    const res = await axios.get(`${RESUME_URL}/resume/improve`);
    return res.data;
  } catch {
    return { suggestion: "No suggestion available" };
  }
};

// ✅ DOWNLOAD REPORT
export const downloadReportAPI = async () => {
  try {
    const response = await fetch(
      `${RANKING_URL}/ranking/download-report`
    );
    return await response.blob();
  } catch (error) {
    console.error("DOWNLOAD ERROR:", error);
  }
};