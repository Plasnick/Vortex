import axios from 'axios';

export default {




    getPostReplies(id) {
        return axios.get(`/post/${id}/replies`)
    }


}