<template>
  <div class="create-post">
      
       <h3>Create Post here</h3>
      <!-- <form id="new-post-form"> -->
          <input type="text" placeholder="Post Title" v-model="newPost.title" required />
          <textarea placeholder="Write your post here!" v-model="newPost.body" required></textarea>
          
          <button v-on:click="uploadImage">Upload Image</button>
          <!-- <img v-if="newPost.img_url" :src="newPost.img_url" alt="Post Image" /> -->
          
          <button v-on:click="submitPost">Submit</button>
      <!-- </form> -->
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
            // image: {
            //     file: null,
            //     publicId: '',
            //     imgUrl: null
            // },
            imageWidget : {}
        }
    },

    methods: {
        uploadImage(){
            this.imageWidget.open()

            
        },

        // async submitImage(){
        //     const response = await PostsService.uploadImage(this.file);
        //     this.image.publicId = PostsService.uploadImage(this.image.file).data.public_id
        //     this.newPost.img_url = PostsService.fetchImageUrl(this.image.publicId, response)
        //  },

        submitPost(){
            PostsService.createPost(this.newPost).then((response) => {
                if (response.status === 201){
                    this.$router.push({name: 'forum', params:{id: this.$route.params.id}})
                }
            })
        }
    },

    mounted() {
       this.imageWidget = window.cloudinary.createUploadWidget(
      {
        cloudName: 'dmdwwopva', 
        uploadPreset: 'wmfapvvo'
      }, 
      (error, result) => { 
        if (!error && result && result.event === "success") {   
          console.log('Done! Here is the image info: ', result.info); 
          console.log("Image URL: " + result.info.url);
          this.newPost.img_url = result.info.url;
        }
      }
    );

  }

}
</script>

<style scoped>
#new-post-form {
    display: flex;
    flex-direction: column;
}

</style>