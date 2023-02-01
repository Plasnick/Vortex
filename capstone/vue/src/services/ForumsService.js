import axios from 'axios';

export default {

    displayTop5() {
        return axios.get('/forum/top5')
    },

    getAllForums(){
        return axios.get('/forum')
    },

    getForum(id){
        return axios.get(`/forum/${id}`)
    },

    createForum(newForum) {
        return axios.post('/forum', newForum);
    }
}