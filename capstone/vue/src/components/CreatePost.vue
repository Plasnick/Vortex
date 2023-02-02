<template>
  <div class="create-post" v-on:submit.prevent="submitPost">
      
       <h3>Create Post here</h3>
      <form id="new-post-form">
          <input type="text" placeholder="Post Title" v-model="newPost.title" required />
          <textarea placeholder="Write your post here!" v-model="newPost.body" required></textarea>
          <input type="text" placeholder="upload image" v-model="newPost.img_url" />
          <input type="submit" />
      </form>
  </div>
</template>

<script>
import postsService from "../services/PostsService";

export default {
    data(){
        return{
            newPost:{
                userId: this.$store.state.user.id,
                forumId: this.$route.params.id,
                title: '',
                body: '',
                img_url: null
            }
        }
    },

    methods: {
        submitPost(){
            postsService.createPost(this.newPost).then((response) => {
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