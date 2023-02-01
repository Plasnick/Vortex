import axios from 'axios';

export default {


    getForumPosts(id) {
        return axios.get(`/forum/${id}/posts`)
    },

    createPost(id){
        return axios.post(`/posts/${id}`)
    }


}