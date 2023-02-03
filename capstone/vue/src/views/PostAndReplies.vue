<template>
  <div class="post-and-replies">
    <button v-on:click="deletePost">Delete Post</button>
    <post v-for="post in posts" v-bind:key="post.postId" v-bind:post="post" />
    <replies-feed />
  </div>
</template>

<script>
import Post from "../components/Post.vue";
import postsService from "../services/PostsService";
import interactionsService from '../services/InteractionsService'
import RepliesFeed from "../components/RepliesFeed.vue";

export default {
  components: { Post, RepliesFeed },
  name: "post-and-replies",
  data() {
    return {
      posts: []
    };
  },
  methods: {
    deletePost(){
      
      if(confirm("Are you sure you wan to delete this post?")){
        postsService.deletePost(this.$route.params.id).then((response) =>{
          if(response.status === 204){
            this.$router.push({name: 'home'})
          }
        })
      }
      
    }
  },
  created() {
    postsService.getPost(this.$route.params.id).then((response) => {
      this.posts = response.data;
      this.$store.commit("SET_POSTS", response.data)
    });
    interactionsService.getInteractions(this.$store.state.user.id).then(response =>{
      this.$store.commit("SET_INTERACTIONS", response.data)
    })
  },
};
</script>

<style>
</style>