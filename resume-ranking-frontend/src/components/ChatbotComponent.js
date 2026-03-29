import React, { useState } from "react";
import { getRanking } from "../services/api";

function ChatbotComponent({ onClose }) {

  const [messages, setMessages] = useState([
    { text: "👋 Hello! I can analyze your resume.", sender: "bot" }
  ]);

  const addMessage = (text, sender) => {
    setMessages(prev => [...prev, { text, sender }]);
  };

  // ✅ SCORE USING RANKING
  const checkScore = async () => {
    addMessage("Check my resume score", "user");

    try {
      const data = await getRanking();

      let score = data.length > 0 ? data[0].score : 0;

      addMessage(`📊 Your score is ${score}`, "bot");

      if (score > 80) {
        addMessage("🔥 Excellent! You are a strong candidate.", "bot");
      } 
      else if (score > 60) {
        addMessage("👍 Good, but you can improve some skills.", "bot");
      } 
      else {
        addMessage("⚠️ You need to improve your resume significantly.", "bot");
      }

    } catch {
      addMessage("❌ Error connecting to backend", "bot");
    }
  };

  // ❌ REMOVED (not implemented)
  const improveResume = () => {
    addMessage("How to improve resume?", "user");
    addMessage("💡 Feature coming soon...", "bot");
  };

  const howRankingWorks = () => {
    addMessage("How ranking works?", "user");
    addMessage("📊 Based on skills, experience & keywords.", "bot");
  };

  return (
    <div className="chat-overlay">
      <div className="chat-modal">

        <div className="chat-header">
          Resume AI Assistant
          <span onClick={onClose} className="close-btn">❌</span>
        </div>

        <div className="chat-body">

          {messages.map((msg, i) => (
            <p key={i}>{msg.text}</p>
          ))}

          <button onClick={checkScore}>Check Resume Score</button>
          <button onClick={improveResume}>Improve Resume</button>
          <button onClick={howRankingWorks}>How Ranking Works</button>

        </div>

      </div>
    </div>
  );
}

export default ChatbotComponent;