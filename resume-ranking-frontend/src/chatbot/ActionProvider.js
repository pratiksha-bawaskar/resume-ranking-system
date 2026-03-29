import { getRanking } from "../services/api";

class ActionProvider {
  constructor(createChatBotMessage, setStateFunc) {
    this.createChatBotMessage = createChatBotMessage;
    this.setState = setStateFunc;
  }

  addMessageToState = (message) => {
    this.setState((prev) => ({
      ...prev,
      messages: [...prev.messages, message],
    }));
  };

  // ✅ SCORE USING RANKING
  handleCheckScore = async () => {
    try {
      const typing = this.createChatBotMessage("⏳ Checking score...");
      this.addMessageToState(typing);

      const data = await getRanking();

      let score = data.length > 0 ? data[0].score : 0;

      const message = this.createChatBotMessage(
        `📊 Your resume score is ${score}`
      );

      this.addMessageToState(message);

    } catch (error) {
      console.error(error);
      const message = this.createChatBotMessage("❌ Unable to fetch score");
      this.addMessageToState(message);
    }
  };

  // ❌ REMOVED SUGGESTION (not implemented)
  handleImproveResume = () => {
    const message = this.createChatBotMessage(
      "💡 Resume improvement feature coming soon..."
    );
    this.addMessageToState(message);
  };

  handleGeneralQuestion = (text = "") => {
    const lower = text.toLowerCase();

    let reply = "";

    if (lower.includes("hello") || lower.includes("hi")) {
      reply = "👋 Hello! I can help you analyze your resume.";
    } 
    else if (lower.includes("job")) {
      reply = "💼 Focus on Java, Spring Boot, React, Microservices.";
    } 
    else if (lower.includes("skill")) {
      reply = "🧠 Key skills: Java, React, SQL, Spring Boot.";
    }
    else {
      reply = "⚠️ Try: 'check score', 'improve resume', 'ranking'";
    }

    const message = this.createChatBotMessage(reply);
    this.addMessageToState(message);
  };
}

export default ActionProvider;