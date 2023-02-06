import axios from 'axios';

// const cloudinary = axios.create();

export default {


    getForumPosts(id) {
        return axios.get(`/forum/${id}/posts`)
    },
    getTop10() {
        return axios.get(`/posts/top10`)
    },
    getPost(id) {
        return axios.get(`/posts/${id}`)
    },
    createPost(post) {
        return axios.post(`/posts`, post)
    },
    updatePost(id, post){
        return axios.put(`/posts/${id}`, post)
    },
    uploadPicture(data){
        axios.post(`/post`, data)
    },
    // uploadImage(file){
    //     cloudinary.post('https://api.cloudinary.com/v1_1/dmdwwopva/image/upload', 'file', file, 'upload_preset', 'wmfapvvo')
    // },
    // fetchImageUrl(cloudinaryPublicId, response){
    //     return cloudinary.get(`https://res.cloudinary.com/your-cloud-name/image/upload/${cloudinaryPublicId}.${response.data.format}`)
    // },
    deletePost(id){
        return axios.delete(`/posts/${id}`)
    }


}