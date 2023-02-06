import axios from "axios";

export default {
    getFavorites(id) {
        return axios.get(`/favorites/${id}`)
    },
    addFavorite(favorite) {
        return axios.post(`/favorites`, favorite)
    },
    deleteFavorite(id) {
        return axios.delete(`/favorites/${id}`)
    }
}