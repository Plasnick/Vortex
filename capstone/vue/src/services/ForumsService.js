import axios from 'axios';

export default {

    displayTop5() {
        return axios.get('/forum/top5')
    },
}