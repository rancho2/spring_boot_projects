import axios from "axios";

const baseUrl = 'http://localhost:8081/api/v1';

export const sendMessagesToServer = async (message, conversationId) => {
    const response=await axios.post(`${baseUrl}/helpdesk`,message,{
        headers: {
            ConversationId: conversationId
        },
    })//non-streaming call
    return response.data;
};