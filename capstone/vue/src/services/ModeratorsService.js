import axios from "axios";

export default{
    getModeratorsForForum(forumId){
        return axios.get(`/moderators/forum/${forumId}`)
    }
    
}