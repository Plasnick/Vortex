import axios from 'axios';

export default {


    getForumPosts(id) {
        return axios.get(`/forum/${id}/posts`)
    },

    createPost(post){
        return axios.post(`/posts`, post)
    }


}