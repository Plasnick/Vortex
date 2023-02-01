import axios from 'axios';

export default {


    getForumPosts(id) {
        return axios.get(`/forum/${id}/posts`)
    },

    getPost(postId) {
        return axios.get(`/posts/${postId}`)
    },



}