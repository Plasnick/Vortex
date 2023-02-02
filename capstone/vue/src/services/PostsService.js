import axios from 'axios';

export default {


    getForumPosts(id) {
        return axios.get(`/forum/${id}/posts`)
    },
    getTop10() {
        return axios.get(`/posts/top10`)
    },
    getPost(id) {
        return axios.get(`/posts/${id}`)
    }


}