import axios from 'axios';

export default {

    displayTop5() {
        return axios.post('/forum/top5')
    },
}