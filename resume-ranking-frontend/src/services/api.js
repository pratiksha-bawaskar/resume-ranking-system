import axios from "axios";

const BASE_URL = "https://resume-ranking-system-ux0j.onrender.com";

// CREATE USER
export const createUser = async (user) => {
  const res = await axios.post(`${BASE_URL}/users`, user);
  return res.data;
};

// UPLOAD RESUME
export const uploadResume = async (formData) => {
  const res = await axios.post(`${BASE_URL}/resumes/upload`, formData, {
    headers: { "Content-Type": "multipart/form-data" },
  });
  return res.data;
};

// GET RANKING
export const getRanking = async () => {
  const res = await axios.get(`${BASE_URL}/resumes/ranking`);
  return res.data;
};

// DELETE
export const deleteAllData = async () => {
  const res = await axios.delete(`${BASE_URL}/resumes/deleteAll`);
  return res.data;
};

// SCORE
export const getResumeScore = async () => {
  const res = await axios.get(`${BASE_URL}/resumes/score`);
  return res.data;
};

// SUGGESTION
export const getResumeSuggestion = async () => {
  const res = await axios.get(`${BASE_URL}/resumes/improve`);
  return res.data;
};

// DOWNLOAD
export const downloadReportAPI = async () => {
  const response = await fetch(`${BASE_URL}/resumes/download-report`);
  return await response.blob();
};