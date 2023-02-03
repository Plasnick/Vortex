<template>
  <div class="create-post" v-on:submit.prevent="submitPost">
      
       <h3>Create Post here</h3>
      <form id="new-post-form">
          <input type="text" placeholder="Post Title" v-model="newPost.title" required />
          <textarea placeholder="Write your post here!" v-model="newPost.body" required></textarea>
          

          <input type="file" placeholder="select image" @change="uploadImage" />
          <button @click="submitImage">Upload Image</button>
          <img v-if="newPost.img_url" :src="newPost.img_url" alt="Post Image" />
          
          <input type="submit" />
      </form>
  </div>
</template>

<script>
import PostsService from '../services/PostsService';

export default {
    data(){
        return{
            newPost:{
                userId: this.$store.state.user.id,
                forumId: this.$route.params.id,
                title: '',
                body: '',
                img_url: ''
            },
            image: {
                file: null,
                publicId: '',
                imgUrl: null
            }
        }
    },

    methods: {
        async uploadImage(event){
            this.file = event.target.files[0]
        },

        async submitImage(){
            const response = await PostsService.uploadImage(this.file);
            this.image.publicId = PostsService.uploadImage(this.image.file).data.public_id
            this.newPost.img_url = PostsService.fetchImageUrl(this.image.publicId, response)
         },

        submitPost(){
            PostsService.createPost(this.newPost).then((response) => {
                if (response.status === 201){
                    this.$router.push({name: 'forum', params:{id: this.$route.params.id}})
                }
            })
        }
    }

}
</script>

<style scoped>
#new-post-form {
    display: flex;
    flex-direction: column;
}

</style>